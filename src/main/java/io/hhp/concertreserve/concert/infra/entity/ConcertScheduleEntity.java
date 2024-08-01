package io.hhp.concertreserve.concert.infra.entity;

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
@Table(name = "concert_schedule", schema = "hhplus_chepter2")
public class ConcertScheduleEntity {
    @Id
    @Column(name = "schedule_id", nullable = false, length = 50)
    private String scheduleId;

    @Column(name = "concert_id", nullable = false, length = 50)
    private String concertId;

    @ColumnDefault("current_timestamp()")
    @Column(name = "concert_date", nullable = false)
    private LocalDateTime concertDate;

}