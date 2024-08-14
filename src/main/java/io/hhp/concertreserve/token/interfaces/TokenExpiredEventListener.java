package io.hhp.concertreserve.token.interfaces;

import io.hhp.concertreserve.payment.domain.PaymentEvent;
import io.hhp.concertreserve.token.domain.TokenRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TokenExpiredEventListener {
    private final TokenRepository tokenRepository;

    public TokenExpiredEventListener(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTokenDeletedEvent(PaymentEvent event) {
        tokenRepository.deletebyUserId(event.getUserId());
    }
}