package io.hhp.concertreserve.payment.domain.message;

public interface PaymentMessageSender {
    void send(PaymentMessage message);
}
