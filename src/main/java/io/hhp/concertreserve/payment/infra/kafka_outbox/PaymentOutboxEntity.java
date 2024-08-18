package io.hhp.concertreserve.payment.infra.kafka_outbox;

import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment_outbox", schema = "hhplus_chepter2")
public class PaymentOutboxEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", length = 10000)
    private String message;

    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public PaymentOutboxEntity() {}

    public PaymentOutboxEntity toEntity(PaymentMessage message){
        PaymentOutboxEntity paymentOutboxEntity = new PaymentOutboxEntity();
        paymentOutboxEntity.setId(message.getMessageId());
        paymentOutboxEntity.setMessage(message.getMessage());
        paymentOutboxEntity.setCreateDate(LocalDateTime.now());

        return paymentOutboxEntity;
    }

    public PaymentMessage toMessage(PaymentOutboxEntity outboxMessage){
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setMessageId(outboxMessage.getId());
        paymentMessage.setMessage(outboxMessage.getMessage());

        return paymentMessage;
    }
}