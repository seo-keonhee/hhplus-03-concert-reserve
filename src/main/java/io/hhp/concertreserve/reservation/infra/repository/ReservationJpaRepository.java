package io.hhp.concertreserve.reservation.infra.repository;

import io.hhp.concertreserve.reservation.infra.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = """
        select s.seatId
        from ReservationEntity s
        where s.scheduleId = :scheduleId
        and (s.confirmReservation = 'Y' or s.reservationDate > :thirtyMinAgo)
    """)
    List<String> findSeatByScheduleIdAndReserveOk(String scheduleId, LocalDateTime thirtyMinAgo);

    List<ReservationEntity> findByUserId(String userId);

    @Query(value =  """
        update ReservationEntity s
        set s.confirmReservation = 'Y'
        where s.id = :id
    """)
    void updateById(Long id);
}
