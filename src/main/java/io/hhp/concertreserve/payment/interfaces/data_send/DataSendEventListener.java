package io.hhp.concertreserve.payment.interfaces.data_send;

import io.hhp.concertreserve.payment.domain.event.PaymentEvent;
import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSendEventListener {

    private final PaymentMessageSender paymentMessageSender;
    private final PaymentMessageOutbox paymentMessageOutbox;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    void createOutboxMessage(PaymentEvent event) {
        paymentMessageOutbox.write(new PaymentMessage().toMessage(event.getPayment()));
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentSend(PaymentEvent event) {
        paymentMessageSender.send(new PaymentMessage().toMessage(event.getPayment()));
    }
}
