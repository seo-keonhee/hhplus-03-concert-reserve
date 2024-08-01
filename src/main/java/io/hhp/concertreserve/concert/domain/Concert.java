package io.hhp.concertreserve.concert.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Concert {

    private String concertId;
    private String artistName;
    private String scheduleId;
    private Long concertFee;
    private LocalDateTime concertDate;

    public Concert(
            String concertId
            , String artistName
            , String scheduleId
            , Long concertFee
            , LocalDateTime concertDate
    ) {
        this.concertId = concertId;
        this.artistName = artistName;
        this.scheduleId = scheduleId;
        this.concertFee = concertFee;
        this.concertDate = concertDate;
    }

    public static Concert create(
            String concertId
            , String artistName
            , String scheduleId
            , Long concertFee
            , LocalDateTime concertDate
    ) {
        return new Concert(concertId, artistName, scheduleId, concertFee, concertDate);
    }
}
