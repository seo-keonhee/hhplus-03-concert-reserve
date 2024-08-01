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
@Table(name = "balance_history", schema = "hhplus_chepter2")
public class BalanceHistoryEntity {
    @Id
    @Column(name = "history_seq", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @ColumnDefault("0")
    @Column(name = "charge_yn", nullable = false)
    private int chargeYn;

    @Column(name = "fee", nullable = false)
    private Long fee;

    @ColumnDefault("current_timestamp()")
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

}