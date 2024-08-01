package io.hhp.concertreserve.concert.interfaces.dto;

import io.hhp.concertreserve.concert.domain.Seat;

import java.time.LocalDateTime;

public record SeatDto(
        String scheduleId
        , String seatid
        , Long seatFee
        , LocalDateTime concertDate
) {
    public static SeatDto toSeatDto(Seat seat){
        return new SeatDto(
                seat.getScheduleId()
                , seat.getSeatid()
                , seat.getSeatFee()
                , seat.getConcertDate());
    }
}
