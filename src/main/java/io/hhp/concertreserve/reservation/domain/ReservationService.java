package io.hhp.concertreserve.reservation.domain;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RedissonClient redissonClient;

    /**
     * 모든 콘서트 조회
     */
    @Cacheable("concerts")
    public List<Concert> getAllconcerts() {
        return reservationRepository.getAllConcerts();
    }

    /**
     * 콘서트 일정 조회
     */
    @Cacheable(value = "schedules", key = "#concertId")
    public List<Concert> getSchedules(String concertId) {
        return reservationRepository.getSchedules(concertId);
    }

    /**
     * 예약가능 좌석목록 조회
     */
    public List<Seat> getSeats(String scheduleId) {
        return reservationRepository.getSeats(scheduleId);
    }

    /**
     * 좌석예약
     */
    public Reservation applyReservation(String scheduleId, String seatId, String userId) {
        RLock lock = redissonClient.getLock(seatId);
        lock.lock();
        try{
            Reservation reservation = new Reservation();
            return reservation.reserve(scheduleId, seatId, userId, reservationRepository);
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 내 예약 목록 조회
     */
    public List<Reservation> getReservations(String userId) {
        return reservationRepository.getReservations(userId);
    }

    /**
     * 예약 확정
     */
    public void confirmReservation(Long id) {
        reservationRepository.confirmReservation(id);
    }
}
