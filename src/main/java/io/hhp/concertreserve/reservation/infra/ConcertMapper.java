package io.hhp.concertreserve.reservation.infra;

import io.hhp.concertreserve.reservation.domain.Concert;
import io.hhp.concertreserve.reservation.domain.Reservation;
import io.hhp.concertreserve.reservation.domain.Seat;
import io.hhp.concertreserve.reservation.infra.entity.ConcertEntity;
import io.hhp.concertreserve.reservation.infra.entity.ConcertScheduleEntity;
import io.hhp.concertreserve.reservation.infra.entity.ReservationEntity;
import io.hhp.concertreserve.reservation.infra.entity.SeatEntity;
import org.springframework.stereotype.Component;

@Component
public record ConcertMapper() {
    public Concert toDomain(ConcertEntity concertEntity) {
        return Concert.create(
                concertEntity.getConcertId()
                , concertEntity.getArtistName()
                , null
                , concertEntity.getConcertFee()
                , null
        );
    }
    public Concert toDomain(
            ConcertEntity concertEntity
            , ConcertScheduleEntity concertScheduleEntity
    ) {
        return Concert.create(
                concertEntity.getConcertId()
                , concertEntity.getArtistName()
                , concertScheduleEntity.getScheduleId()
                , concertEntity.getConcertFee()
                , concertScheduleEntity.getConcertDate()
        );
    }

    public Reservation toDomain(ReservationEntity reservationEntity) {
        return Reservation.creat(
                reservationEntity.getId()
                , reservationEntity.getScheduleId()
                , reservationEntity.getSeatId()
                , reservationEntity.getUserId()
                , reservationEntity.getArtistName()
                , reservationEntity.getTotalFee()
                , reservationEntity.getConcertDate()
                , reservationEntity.getReservationDate()
        );
    }

    public Seat toDomain(SeatEntity seatEntity
            , ConcertScheduleEntity concertScheduleEntity) {
        return Seat.create(
                concertScheduleEntity.getScheduleId()
                , seatEntity.getSeatId()
                , seatEntity.getSeatFee()
                , concertScheduleEntity.getConcertDate()
        );
    }

    public ReservationEntity toEntity(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(reservation.getReservationId());
        reservationEntity.setScheduleId(reservation.getScheduleId());
        reservationEntity.setSeatId(reservation.getSeatId());
        reservationEntity.setUserId(reservation.getUserId());
        reservationEntity.setArtistName(reservation.getArtistName());
        reservationEntity.setTotalFee(reservation.getTotalFee());
        reservationEntity.setConcertDate(reservation.getConcertDate());

        return reservationEntity;
    }
}
