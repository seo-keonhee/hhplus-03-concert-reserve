package io.hhp.concertreserve.payment.domain;

import io.hhp.concertreserve.payment.domain.event.PaymentSavedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentSavedEventListener {
    private final PaymentRepository paymentRepository;

    public PaymentSavedEventListener(PaymentRepository paymentRepository) {
        //todo 로그찍기
        this.paymentRepository = paymentRepository;
    }

    @TransactionalEventListener
    public void handlePaymentSavedEvent(PaymentSavedEvent event) {
        //todo 로그찍기
        //todo 딜래이 걸어서 비동기로 동작하는지 확인
        paymentRepository.savePayment(event.getPayment());
    }
}
