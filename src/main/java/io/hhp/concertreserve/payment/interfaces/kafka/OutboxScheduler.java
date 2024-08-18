package io.hhp.concertreserve.payment.interfaces.kafka;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
public class OutboxScheduler {

    private final PaymentMessageOutbox paymentMessageOutbox;
    private final PaymentMessageSender paymentMessageSender;

    private List<PaymentMessage> messages;

    @Scheduled(fixedRate = 5000)
    public void resend() {
        List<PaymentMessage> paymentMessages = paymentMessageOutbox.findAllNotPublished();

        messages = paymentMessages;

        for (PaymentMessage paymentMessage : paymentMessages) {
            paymentMessageSender.send(paymentMessage);
        }
    }
}
