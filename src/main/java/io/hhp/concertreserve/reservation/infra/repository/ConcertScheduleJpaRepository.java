package io.hhp.concertreserve.reservation.infra.repository;


import io.hhp.concertreserve.reservation.infra.entity.ConcertScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertScheduleEntity, String> {

    List<ConcertScheduleEntity> findByConcertId(String concertId);

    ConcertScheduleEntity findByScheduleId(String scheduleId);
}
