package io.hhp.concertreserve.queue.infra;

import io.hhp.concertreserve.queue.domain.TokenRepository;
import io.hhp.concertreserve.queue.domain.Tokens;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {
    private final TokenJpaRepository tokenJpaRepository;
    private final TokenMapper tokenMapper;

    /**
     * 토큰이 있는지 확인
     */
    @Override
    public boolean isTokens(String userId) {
        return tokenJpaRepository.existsByUserId(userId, LocalDateTime.now());
    }

    /**
     * 유저ID로 토큰을 조회
     */
    @Override
    public Tokens getTokens(String userId) {
        return tokenMapper.toDomain(tokenJpaRepository.findByUserId(userId));
    }

    /**
     * 토큰을 저장
     */
    @Override
    public void saveAllTokens(List<Tokens> tokens) {
        tokenJpaRepository.saveAll(tokens.stream().map(tokenMapper::toEntity).toList());
    }

    /**
     * 토큰을 삭제
     */
    @Override
    public void deleteByUserId(String userId) {
        tokenJpaRepository.deleteByUserId(userId);
    }
}
