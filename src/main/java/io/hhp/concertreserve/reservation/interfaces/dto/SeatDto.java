package io.hhp.concertreserve.reservation.interfaces.dto;

import io.hhp.concertreserve.reservation.domain.Seat;

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
