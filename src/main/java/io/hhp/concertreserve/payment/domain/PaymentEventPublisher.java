package io.hhp.concertreserve.payment.domain;

public interface PaymentEventPublisher {
    void successEvent(PaymentSuccessEvent paymentSuccessEvent);
}
