package io.hhp.concertreserve.payment.domain;

import lombok.Getter;

@Getter
public class PaymentSuccessEvent {
    private final Payment payment;

    public PaymentSuccessEvent(Payment payment) {
        this.payment = payment;
    }

    public String getUserId() {
        return payment.getUserId();
    }

    public Long getReservationId() {
        return payment.getReservationId();
    }
}