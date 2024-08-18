package io.hhp.concertreserve.payment.interfaces.kafka;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class PaymentMessageOutboxConsumer {

    private final PaymentMessageOutbox paymentMessageOutbox;
    private int updateCount;

    @KafkaListener(topics = "payment-success", groupId = "payment")
    public void checkPublished(String message){
        updateCount = paymentMessageOutbox.updateState(new PaymentMessage().toPayment(message).getReservationId());
    }
}
