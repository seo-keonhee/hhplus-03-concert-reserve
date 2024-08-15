package io.hhp.concertreserve.payment.infra.event;

import io.hhp.concertreserve.payment.domain.event.PaymentEventPublisher;
import io.hhp.concertreserve.payment.domain.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisherImpl implements PaymentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void successEvent(PaymentEvent paymentEvent) {
        applicationEventPublisher.publishEvent(paymentEvent);
    }
}
