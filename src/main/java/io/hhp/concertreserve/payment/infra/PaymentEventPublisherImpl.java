package io.hhp.concertreserve.payment.infra;

import io.hhp.concertreserve.payment.domain.PaymentEventPublisher;
import io.hhp.concertreserve.payment.domain.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisherImpl implements PaymentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void successEvent(PaymentSuccessEvent paymentSuccessEvent) {
        applicationEventPublisher.publishEvent(paymentSuccessEvent);
    }
}
