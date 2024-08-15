package io.hhp.concertreserve.payment.infra.kafka_outbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentOutboxJpaRepository extends JpaRepository<PaymentOutboxEntity, Long> {

    @Query(value = """
        update PaymentOutboxEntity p
        set p.status = 1
        where p.id = :messageId
    """)
    void updateByMessageId(Long messageId);

    @Query(value = """
        select p
        from PaymentOutboxEntity p
        where p.status = 0
    """)
    List<PaymentOutboxEntity> findAllByStatusZero();
}
