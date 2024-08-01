package io.hhp.concertreserve.concert.infra;

import io.hhp.concertreserve.concert.domain.Concert;
import io.hhp.concertreserve.concert.domain.ConcertRepository;
import io.hhp.concertreserve.concert.domain.Reservation;
import io.hhp.concertreserve.concert.domain.Seat;
import io.hhp.concertreserve.concert.infra.entity.ConcertEntity;
import io.hhp.concertreserve.concert.infra.entity.ConcertScheduleEntity;
import io.hhp.concertreserve.concert.infra.entity.SeatEntity;
import io.hhp.concertreserve.concert.infra.repository.ConcertJpaRepository;
import io.hhp.concertreserve.concert.infra.repository.ConcertScheduleJpaRepository;
import io.hhp.concertreserve.concert.infra.repository.ReservationJpaRepository;
import io.hhp.concertreserve.concert.infra.repository.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;
    private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;
    private final SeatJpaRepository seatJpaRepository;
    private final ConcertMapper concertMapper;

    @Override
    public List<Concert> getAllConcerts() {
        List<ConcertEntity> concertEntities = concertJpaRepository.findAll();
        return concertEntities.stream().map(concertMapper::toDomain).toList();
    }

    @Override
    public List<Concert> getSchedules(String concertId) {
        ConcertEntity concertEntity = concertJpaRepository.findByConcertId(concertId);
        List<ConcertScheduleEntity> concertScheduleEntities =
                concertScheduleJpaRepository.findByConcertId(concertId);
        return concertScheduleEntities.stream()
                .map(ConcertScheduleEntity -> concertMapper.toDomain(concertEntity, ConcertScheduleEntity)).toList();
    }

    @Override
    public List<Seat> getSeats(String scheduleId) {
        ConcertScheduleEntity concertScheduleEntity =
                concertScheduleJpaRepository.findByScheduleId(scheduleId);
        List<SeatEntity> seats = seatJpaRepository.findBySeatIdNotIn(
                reservationJpaRepository.findByScheduleId(scheduleId));
        return seats.stream().map(seatEntity -> concertMapper.toDomain(seatEntity, concertScheduleEntity)).toList();
    }

    @Override
    public List<Reservation> getReservations(String userId) {
        return reservationJpaRepository.findByUserId(userId).stream()
                .map(concertMapper::toDomain).toList();
    }

    @Override
    public Concert getSelectconcert(String scheduleId) {
        ConcertScheduleEntity concertScheduleEntity =
                concertScheduleJpaRepository.findByScheduleId(scheduleId);
        ConcertEntity concertEntity =
                concertJpaRepository.findByConcertId(concertScheduleEntity.getConcertId());
        return concertMapper.toDomain(concertEntity, concertScheduleEntity);
    }

    @Override
    public Seat getSelectSeats(String scheduleId, String seatId) {
        return concertMapper.toDomain(seatJpaRepository.findBySeatId(seatId)
                , concertScheduleJpaRepository.findByScheduleId(scheduleId)) ;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationJpaRepository.save(concertMapper.toEntity(reservation));
    }
}
