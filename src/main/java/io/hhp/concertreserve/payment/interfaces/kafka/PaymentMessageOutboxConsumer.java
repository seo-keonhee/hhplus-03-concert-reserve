package io.hhp.concertreserve.payment.interfaces.kafka;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
public class PaymentMessageOutboxConsumer {

    private final PaymentMessageOutbox paymentMessageOutbox;

    @KafkaListener(topics = "payment-success", groupId = "payment")
    public void checkPublished(String message){
        paymentMessageOutbox.updateState(new PaymentMessage().toPayment(message).getReservationId());
    }
}
