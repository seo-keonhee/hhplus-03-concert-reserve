package io.hhp.concertreserve.token.domain;

import io.hhp.concertreserve.payment.domain.event.TokenDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TokenDeletedEventListener {
    private final TokenRepository tokenRepository;

    public TokenDeletedEventListener(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @EventListener
    public void handleTokenDeletedEvent(TokenDeletedEvent event) {
        tokenRepository.deletebyUserId(event.getUserId());
    }
}