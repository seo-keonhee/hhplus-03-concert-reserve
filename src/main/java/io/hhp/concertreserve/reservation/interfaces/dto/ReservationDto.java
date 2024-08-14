package io.hhp.concertreserve.reservation.interfaces.dto;

import io.hhp.concertreserve.reservation.domain.Reservation;

import java.time.LocalDateTime;

public record ReservationDto(
        Long reservationId
        , String scheduleId
        , String seatId
        , String userId
        , String artistName
        , LocalDateTime concertDate
        , LocalDateTime reservationDate
) {
    public static ReservationDto toReservationDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getReservationId()
                , reservation.getScheduleId()
                , reservation.getSeatId()
                , reservation.getUserId()
                , reservation.getArtistName()
                , reservation.getConcertDate()
                , reservation.getReservationDate());
    }
}
