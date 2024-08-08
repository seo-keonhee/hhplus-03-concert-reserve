package io.hhp.concertreserve.payment.domain;

import io.hhp.concertreserve.payment.domain.event.PaymentSavedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentSavedEventListener {
    private final PaymentRepository paymentRepository;

    public PaymentSavedEventListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventListener
    public void handlePaymentSavedEvent(PaymentSavedEvent event) {
        paymentRepository.savePayment(event.getPayment());
    }
}
