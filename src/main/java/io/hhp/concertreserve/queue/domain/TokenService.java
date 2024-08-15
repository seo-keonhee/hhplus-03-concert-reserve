package io.hhp.concertreserve.queue.domain;

import io.hhp.concertreserve.reservation.support.ReservationException;
import io.hhp.concertreserve.reservation.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private final String queueKey = "myQueue";
    private final String queueSetKey = "mySetQueue";

    /**
     * 토큰 유효성 조회(활성화 되어있는 토큰인가?)
     */
    public boolean vaildToken(String userId) {

        boolean check = tokenRepository.isTokens(userId);
        // 토큰이 없는경우
        if(!check) {
            throw new ReservationException(ErrorCode.TOKEN_IS_NOT_FOUND, ErrorCode.TOKEN_IS_NOT_FOUND.getMsg());
        }
        Tokens tokens = tokenRepository.getTokens(userId);
//        // 대기가 끝나지 않았을 경우
//        if (tokens.getActiveDate() == null){
//            throw new ReservationException(ErrorCode.INVALID_TOKEN, ErrorCode.INVALID_TOKEN.getMsg());
//        }
        // 토큰이 만료된 경우
        if(tokens.getDeactivateDate().isBefore(LocalDateTime.now())){
            throw new ReservationException(ErrorCode.TOKEN_EXPIRED, ErrorCode.TOKEN_EXPIRED.getMsg());
        }
        return true;
    }

    /**
     * 대기열 또는 토큰 조회
     */
    public Tokens getToken(String userId) {
        // 토큰이 없는경우
        if(!tokenRepository.isTokens(userId)) {
//            // 토큰을 생성한다.
//            tokenRepository.saveTokens(Tokens.create(userId));
//            log.info("신규토큰 발급: userId={}, startWaitDate={}", userId, LocalDateTime.now());
            if(Boolean.FALSE.equals(redisTemplate.opsForSet().isMember(queueSetKey, userId))){
                redisTemplate.opsForSet().add(queueSetKey, userId);
                redisTemplate.opsForList().rightPush(queueKey, userId);
            }
            Long position = redisTemplate.opsForList().indexOf(queueKey, userId) + 1;
            Duration waiting = Duration.ofMinutes(30).multipliedBy(1 + Math.floorDiv(position,50));
            log.info("{} 님현재대기인원수: {} 명, 예상대기시간: {} 분 "
                    , userId
                    , position
                    , waiting
            );
            throw new ReservationException(ErrorCode.QUEUE_POSITION
                    , String.format(ErrorCode.QUEUE_POSITION.getMsg(), userId, position, waiting.toMinutes()));
        }
        // 토큰 조회
        return tokenRepository.getTokens(userId);
    }

//    /**
//     * 대기중인 토큰을 순차적으로 활성화한다.
//     */
//    public void checkTokens(){
//        tokenRepository.saveAllTokens(Tokens.active(tokenRepository.getWaitingTokens(passCounts)));
//    }

    /**
     * 토큰을 생성한다.
     */
    public void saveTokens(List<String> users) {
        tokenRepository.saveAllTokens(users.stream().map(Tokens::create).toList());
    }

    /**
     * 대기열에서 뺸다.
     */
    public List<String> dequeueBatch() {
        List<String> users = new ArrayList<>();
        // 주기당 50개씩 통과시킨다.
        for (int i = 0; i < 50; i++) {
            String user = (String) redisTemplate.opsForList().leftPop(queueKey);
            if (user != null) {
                redisTemplate.opsForSet().remove(queueSetKey, user);
                users.add(user);
            } else {
                break;
            }
        }
        return users;
    }
}
