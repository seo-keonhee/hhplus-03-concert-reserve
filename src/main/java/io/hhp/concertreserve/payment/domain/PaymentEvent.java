package io.hhp.concertreserve.payment.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PaymentEvent extends ApplicationEvent {
    private final Payment payment;

    public PaymentEvent(Object source, Payment payment) {
        super(source);
        this.payment = payment;
    }

    public String getUserId() {
        return payment.getUserId();
    }

    public String getReservationId() {
        return payment.getReservationId();
    }
}