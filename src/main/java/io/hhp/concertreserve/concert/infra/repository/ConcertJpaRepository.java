package io.hhp.concertreserve.concert.infra.repository;

import io.hhp.concertreserve.concert.infra.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, String> {

    ConcertEntity findByConcertId(String concertId);
}

