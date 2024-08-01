package io.hhp.concertreserve.concert.infra.repository;

import io.hhp.concertreserve.concert.infra.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = """
        select s.seatId
        from ReservationEntity s
        where s.scheduleId = :scheduleId
    """)
    List<String> findByScheduleId(String scheduleId);

    List<ReservationEntity> findByUserId(String userId);

}
