package io.hhp.concertreserve.payment.interfaces.kafka;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
public class OutboxScheduler {

    private final PaymentMessageOutbox paymentMessageOutbox;
    private final PaymentMessageSender paymentMessageSender;

    @Scheduled(fixedRate = 5000)
    public void resend() {
        List<PaymentMessage> paymentMessages = paymentMessageOutbox.findAllNotPublished();

        for (PaymentMessage paymentMessage : paymentMessages) {
            paymentMessageSender.send(paymentMessage);
        }
    }
}
