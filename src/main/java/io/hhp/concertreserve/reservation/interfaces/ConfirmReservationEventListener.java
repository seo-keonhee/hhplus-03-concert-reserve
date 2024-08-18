package io.hhp.concertreserve.reservation.interfaces;

import io.hhp.concertreserve.payment.domain.event.PaymentEvent;
import io.hhp.concertreserve.reservation.domain.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
public class ConfirmReservationEventListener {

    private final ReservationService reservationService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void confirmReservation(PaymentEvent event) {
        reservationService.confirmReservation(event.getReservationId());
    }
}
