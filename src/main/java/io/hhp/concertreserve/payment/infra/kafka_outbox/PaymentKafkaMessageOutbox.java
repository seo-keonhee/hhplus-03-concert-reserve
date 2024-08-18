package io.hhp.concertreserve.payment.infra.kafka_outbox;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentKafkaMessageOutbox implements PaymentMessageOutbox {

    private final PaymentOutboxJpaRepository paymentOutboxJpaRepository;

    @Override
    public void write(PaymentMessage message) {
        paymentOutboxJpaRepository.save(new PaymentOutboxEntity().toEntity(message));
    }
    @Override
    public int updateState(Long messageId) {
        return paymentOutboxJpaRepository.updateByMessageId(messageId);
    }

    @Override
    public List<PaymentMessage> findAllNotPublished() {
        return paymentOutboxJpaRepository.findAllByStatusZero().stream().map(new PaymentOutboxEntity()::toMessage).toList();
    }
}
