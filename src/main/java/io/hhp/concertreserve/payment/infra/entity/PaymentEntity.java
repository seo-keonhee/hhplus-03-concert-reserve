package io.hhp.concertreserve.payment.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment", schema = "hhplus_chepter2")
public class PaymentEntity {
    @Id
    @Column(name = "reservation_id", nullable = false, length = 50)
    private String reservationId;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @ColumnDefault("0")
    @Column(name = "total_fee", nullable = false)
    private Long totalFee;

    @ColumnDefault("current_timestamp()")
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

}