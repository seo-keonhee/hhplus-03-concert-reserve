package io.hhp.concertreserve.payment.infra.kafka;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentKafkaMessageSender implements PaymentMessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(PaymentMessage message) {
        this.kafkaTemplate.send("payment-success", message.getMessage());
    }
}
