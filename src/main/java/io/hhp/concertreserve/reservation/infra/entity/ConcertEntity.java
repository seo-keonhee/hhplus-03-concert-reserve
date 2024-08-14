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
@Table(name = "concert", schema = "hhplus_chepter2")
public class ConcertEntity {
    @Id
    @Column(name = "concert_id", nullable = false, length = 50)
    private String concertId;

    @Column(name = "artist_name", nullable = false, length = 50)
    private String artistName;

    @Column(name = "concert_fee", nullable = false)
    private Long concertFee;

}