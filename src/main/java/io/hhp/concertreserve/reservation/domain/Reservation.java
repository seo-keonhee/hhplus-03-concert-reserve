package io.hhp.concertreserve.reservation.domain;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reservation {

    private Long reservationId;
    private String scheduleId;
    private String seatId;
    private String userId;
    private String artistName;
    private Long totalFee;
    private LocalDateTime concertDate;
    private LocalDateTime reservationDate;

    public Reservation() {}

    public Reservation(
            Long reservationId
            , String scheduleId
            , String seatId
            , String userId
            , String artistName
            , Long totalFee
            , LocalDateTime concertDate
            , LocalDateTime reservationDate
    ) {
        this.reservationId = reservationId;
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.userId = userId;
        this.artistName = artistName;
        this.totalFee = totalFee;
        this.concertDate = concertDate;
        this.reservationDate = reservationDate;
    }

    public static Reservation creat(
            Long reservationId
            , String scheduleId
            , String seatId
            , String userId
            , String artistName
            , Long totalFee
            , LocalDateTime concertDate
            , LocalDateTime reservationDate
    ) {
        return new Reservation(
                reservationId
                , scheduleId
                , seatId
                , userId
                , artistName
                , totalFee
                , concertDate
                , reservationDate
        );
    }

    @Transactional
    public Reservation reserve(
            String scheduleId
            , String seatId
            , String userId
            , ReservationRepository reservationRepository
    ) {
        Reservation reservation = new Reservation(
                null
                , scheduleId
                , seatId
                , userId
                , reservationRepository.getSelectconcert(scheduleId).getArtistName()
                , reservationRepository.getSelectconcert(scheduleId).getConcertFee() + reservationRepository.getSelectSeats(scheduleId, seatId).getSeatFee()
                , reservationRepository.getSelectconcert(scheduleId).getConcertDate()
                , LocalDateTime.now()
        );
        reservationRepository.saveReservation(reservation);
        return reservation;
    }
}
