package io.hhp.concertreserve.queue.interfaces;

import io.hhp.concertreserve.payment.domain.event.PaymentEvent;
import io.hhp.concertreserve.queue.domain.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class TokenExpiredEventListener {

    private final TokenService tokenService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void expireToken(PaymentEvent event) {
        tokenService.expireToken(event.getUserId());
    }
}