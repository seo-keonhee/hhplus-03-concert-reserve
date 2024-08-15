package io.hhp.concertreserve.payment.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PaymentEvent{
    private final Payment payment;

    public PaymentEvent(Payment payment) {
        this.payment = payment;
    }

    public String getUserId() {
        return payment.getUserId();
    }

    public String getReservationId() {
        return payment.getReservationId();
    }
}