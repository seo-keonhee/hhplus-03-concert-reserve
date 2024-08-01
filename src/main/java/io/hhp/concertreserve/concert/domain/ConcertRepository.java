package io.hhp.concertreserve.concert.domain;

import java.util.List;

public interface ConcertRepository {

    List<Concert> getAllConcerts();

    List<Concert> getSchedules(String concertId);

    List<Seat> getSeats(String scheduleId);

    List<Reservation> getReservations(String userId);

    Concert getSelectconcert(String scheduleId);

    Seat getSelectSeats(String scheduleId, String seatId);

    void saveReservation(Reservation reservation);
}
