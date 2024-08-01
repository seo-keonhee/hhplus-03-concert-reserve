package io.hhp.concertreserve.concert.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Seat {
    private String scheduleId;
    private String seatid;
    private Long seatFee;
    private LocalDateTime concertDate;

    public Seat(
            String scheduleId
            , String seatid
            , Long seatFee
            , LocalDateTime concertDate
    ) {
        this.scheduleId = scheduleId;
        this.seatid = seatid;
        this.seatFee = seatFee;
        this.concertDate = concertDate;
    }

    public static Seat create(
            String scheduleId
            , String seatid
            , Long seatFee
            , LocalDateTime concertDate
    ) {
        return new Seat(
                scheduleId
                , seatid
                , seatFee
                , concertDate
        );
    }
}