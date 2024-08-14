package io.hhp.concertreserve.reservation.infra.repository;

import io.hhp.concertreserve.reservation.infra.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, String> {

    ConcertEntity findByConcertId(String concertId);
}

