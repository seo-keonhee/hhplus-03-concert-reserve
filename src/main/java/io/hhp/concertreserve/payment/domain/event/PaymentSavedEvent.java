package io.hhp.concertreserve.payment.domain.event;

import io.hhp.concertreserve.payment.domain.Payment;
import org.springframework.context.ApplicationEvent;

public class PaymentSavedEvent extends ApplicationEvent {
    private final Payment payment;

    public PaymentSavedEvent(Object source, Payment payment) {
        super(source);
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }
}