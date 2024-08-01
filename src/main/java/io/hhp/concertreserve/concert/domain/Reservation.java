package io.hhp.concertreserve.concert.domain;

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
            , ConcertRepository concertRepository
    ) {
        Reservation reservation = new Reservation(
                null
                , scheduleId
                , seatId
                , userId
                , concertRepository.getSelectconcert(scheduleId).getArtistName()
                , concertRepository.getSelectconcert(scheduleId).getConcertFee() + concertRepository.getSelectSeats(scheduleId, seatId).getSeatFee()
                , concertRepository.getSelectconcert(scheduleId).getConcertDate()
                , LocalDateTime.now()
        );
        concertRepository.saveReservation(reservation);
        return reservation;
    }
}
