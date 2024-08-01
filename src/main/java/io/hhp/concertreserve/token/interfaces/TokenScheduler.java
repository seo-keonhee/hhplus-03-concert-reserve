package io.hhp.concertreserve.token.interfaces;

import io.hhp.concertreserve.token.domain.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenScheduler {

    private final TokenService tokenService;

    @Scheduled(fixedRate = 1800000)
    public void run() {
        tokenService.saveTokens(tokenService.dequeueBatch());
        log.info("{} 건의 토큰을 활성화,", 50);
    }
}
