package io.hhp.concertreserve.payment.interfaces;

import io.hhp.concertreserve.payment.domain.PaymentSuccessEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentCreatedEventListener {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentSavedEvent(PaymentSuccessEvent event) {
        //todo 로그찍기
        //todo 딜래이 걸어서 비동기로 동작하는지 확인

    }
}
