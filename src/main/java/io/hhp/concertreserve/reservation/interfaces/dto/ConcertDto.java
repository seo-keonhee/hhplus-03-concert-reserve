package io.hhp.concertreserve.reservation.interfaces.dto;

import io.hhp.concertreserve.reservation.domain.Concert;

import java.time.LocalDateTime;

public record ConcertDto(
        String concertId
        , String artistName
        , String scheduleId
        , Long concertFee
        , LocalDateTime concertDate
) {
    public static ConcertDto toConcertDto(Concert concert) {
        return new ConcertDto(
                concert.getConcertId()
                , concert.getArtistName()
                , concert.getScheduleId()
                , concert.getConcertFee()
                , concert.getConcertDate());
    }
}
