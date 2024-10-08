package io.hhp.concertreserve.payment.interfaces.kafka;

import io.hhp.concertreserve.payment.application.DataSendFacade;
import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@RequiredArgsConstructor
public class PaymentMessageConsumer {

    private final DataSendFacade dataSendFacade;
    private String receiveMessage;

    @KafkaListener(topics = "payment-success", groupId = "payment")
    public void paymentSend(String message) {
        receiveMessage = message;
        try {
            dataSendFacade.sendPayment(new PaymentMessage().toPayment());
        } catch (InterruptedException e) {
            log.error("Can't not send the payment", e);
        }
    }
}
