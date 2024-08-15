package io.hhp.concertreserve.payment.infra.repository;

import io.hhp.concertreserve.payment.infra.entity.PaymentEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, String> {

    /**
     * 결제 여부 확인 후 결제를 진행,
     * 결제가 되었음에도 동시성 이슈로 재 결제를 방지하기위해 비관적 락 이용
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsByReservationId(Long reservationId);

    List<PaymentEntity> findByUserId(String UserId);
}

