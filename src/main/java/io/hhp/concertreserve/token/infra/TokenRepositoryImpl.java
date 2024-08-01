package io.hhp.concertreserve.token.infra;

import io.hhp.concertreserve.token.domain.TokenRepository;
import io.hhp.concertreserve.token.domain.Tokens;
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
     * 마지막으로 활성화된 토큰을 조회
     */
    @Override
    public Tokens lastActiveTokens() {
        return tokenMapper.toDomain(tokenJpaRepository.findLastActiveToken(LocalDateTime.now()));
    }

    /**
     * 비활성화상태의 토큰목록을 조회
     */
    @Override
    public List<Tokens> getWaitingTokens(int counts) {
        return tokenJpaRepository.findWaitingTokens(counts).stream().map(tokenMapper::toDomain).toList();
    }

    /**
     * 토큰을 저장
     */
    @Override
    public void saveTokens(Tokens tokens) {
        tokenJpaRepository.save(tokenMapper.toEntity(tokens));
    }

    /**
     * 토큰을 저장
     */
    @Override
    public void saveAllTokens(List<Tokens> tokens) {
        tokenJpaRepository.saveAll(tokens.stream().map(tokenMapper::toEntity).toList());
    }
}
