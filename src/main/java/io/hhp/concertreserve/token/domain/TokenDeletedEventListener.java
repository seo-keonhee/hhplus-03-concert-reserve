package io.hhp.concertreserve.token.domain;

import io.hhp.concertreserve.payment.domain.event.TokenDeletedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TokenDeletedEventListener {
    private final TokenRepository tokenRepository;

    public TokenDeletedEventListener(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @TransactionalEventListener
    public void handleTokenDeletedEvent(TokenDeletedEvent event) {
        tokenRepository.deletebyUserId(event.getUserId());
    }
}