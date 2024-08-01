package io.hhp.concertreserve.payment.infra.repository;

import io.hhp.concertreserve.payment.infra.entity.BalanceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceHistoryJpaRepository extends JpaRepository<BalanceHistoryEntity, String> {
}
