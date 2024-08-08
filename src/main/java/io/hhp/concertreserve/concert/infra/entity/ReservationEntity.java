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
@Table(name = "reservation", schema = "hhplus_chepter2")
public class ReservationEntity {
    @Id
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @Column(name = "schedule_id", nullable = false, length = 50)
    private String scheduleId;

    @Column(name = "seat_id", nullable = false, length = 50)
    private String seatId;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "artist_name", nullable = false, length = 50)
    private String artistName;

    @Column(name = "total_fee", nullable = false)
    private Long totalFee;

    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "concert_date", nullable = false)
    private LocalDateTime concertDate;

    @ColumnDefault("current_timestamp()")
    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;

    @ColumnDefault("'N'")
    @Column(name = "confirm_reservation", nullable = false, length = 1)
    private String confirmReservation;

}