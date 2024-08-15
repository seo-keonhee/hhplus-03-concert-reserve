package io.hhp.concertreserve.reservation.domain;

import java.util.List;

public interface ReservationRepository {

    List<Concert> getAllConcerts();

    List<Concert> getSchedules(String concertId);

    List<Seat> getSeats(String scheduleId);

    List<Reservation> getReservations(String userId);

    Concert getSelectconcert(String scheduleId);

    Seat getSelectSeats(String scheduleId, String seatId);

    void saveReservation(Reservation reservation);

    void confirmReservation(Long id);
}
