package io.hhp.concertreserve.payment.infra.repository;

import io.hhp.concertreserve.payment.infra.entity.BalanceEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface BalanceJpaRepository extends JpaRepository<BalanceEntity, String> {

    boolean existsByUserId(String userId);

    /**
     * 잔액(계좌) 데이터 갱신시 동시성 이슈 해소를 위해 비관적 락 이용
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BalanceEntity findByUserId(String userId);
}
