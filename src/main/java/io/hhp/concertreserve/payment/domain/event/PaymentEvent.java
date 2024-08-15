package io.hhp.concertreserve.payment.domain.event;

import io.hhp.concertreserve.payment.domain.Payment;
import lombok.Getter;

@Getter
public class PaymentEvent {
    private final Payment payment;

    public PaymentEvent(Payment payment) {
        this.payment = payment;
    }

    public String getUserId() {
        return payment.getUserId();
    }

    public Long getReservationId() {
        return payment.getReservationId();
    }
}