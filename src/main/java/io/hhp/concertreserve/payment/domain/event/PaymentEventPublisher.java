package io.hhp.concertreserve.payment.domain.event;

public interface PaymentEventPublisher {
    void successEvent(PaymentEvent paymentEvent);
}
