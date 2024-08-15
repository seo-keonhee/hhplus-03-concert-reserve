package io.hhp.concertreserve.reservation.interfaces;

import io.hhp.concertreserve.payment.domain.PaymentSuccessEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

public class ReservationConfirmEventListener {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentSavedEvent(PaymentSuccessEvent event) {

    }
}
