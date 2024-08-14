package io.hhp.concertreserve.payment.interfaces;

import io.hhp.concertreserve.payment.domain.PaymentRepository;
import io.hhp.concertreserve.payment.domain.PaymentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentCreatedEventListener {
    private final PaymentRepository paymentRepository;

    public PaymentCreatedEventListener(PaymentRepository paymentRepository) {
        //todo 로그찍기
        this.paymentRepository = paymentRepository;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentSavedEvent(PaymentEvent event) {
        //todo 로그찍기
        //todo 딜래이 걸어서 비동기로 동작하는지 확인
        paymentRepository.savePayment(event.getPayment());
    }
}
