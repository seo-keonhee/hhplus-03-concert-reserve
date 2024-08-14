package io.hhp.concertreserve.reservation.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat", schema = "hhplus_chepter2")
public class SeatEntity {
    @Id
    @Column(name = "seat_id", nullable = false, length = 50)
    private String seatId;

    @Column(name = "seat_fee", nullable = false)
    private Long seatFee;

}