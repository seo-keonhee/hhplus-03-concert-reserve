package io.hhp.concertreserve.payment.infra;

import io.hhp.concertreserve.payment.domain.Balance;
import io.hhp.concertreserve.payment.domain.Payment;
import io.hhp.concertreserve.payment.domain.PaymentRepository;
import io.hhp.concertreserve.payment.infra.entity.BalanceEntity;
import io.hhp.concertreserve.payment.infra.repository.BalanceHistoryJpaRepository;
import io.hhp.concertreserve.payment.infra.repository.BalanceJpaRepository;
import io.hhp.concertreserve.payment.infra.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final BalanceJpaRepository balanceJpaRepository;
    private final BalanceHistoryJpaRepository balanceHistoryJpaRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public boolean isBalance(String userId) {
        return balanceJpaRepository.existsByUserId(userId);
    }

    /**
     * 잔액(계좌) 정보를 생성
     */
    @Override
    public void saveBalance(Balance balance) {
        balanceJpaRepository.save(paymentMapper.toEntity(balance));
    }

    /**
     * 유저ID로 잔액(계좌)를 조회한다.
     */
    @Override
    public Balance getBalance(String userId) {
        BalanceEntity balanceEntity = balanceJpaRepository.findByUserId(userId);
        return paymentMapper.toDomain(balanceEntity);
    }

    /**
     * 잔액(계좌)정보에 잔액을 추가하여 갱신한다.
     */
    @Override
    public void charge(String userId, Long fee) {
        BalanceEntity balanceEntity = balanceJpaRepository.findByUserId(userId);
        balanceEntity.setBalance(balanceEntity.getBalance() + fee);
        balanceJpaRepository.save(balanceEntity);
        balanceHistoryJpaRepository.save(paymentMapper.toEntity(userId, fee, true));
    }

    /**
     * 결제정보 존재여부를 조회한다.
     */
    @Override
    public boolean isPayment(String reservationId) {
        return paymentJpaRepository.existsByReservationId(reservationId);
    }

    /**
     * 결제 정보를 저장한다.
     */
    @Override
    public void savePayment(Payment payment) {
        paymentJpaRepository.save(paymentMapper.toEntity(payment));
    }

    /**
     * 잔액(계좡) 정보에서 잔액을 차감하여 갱신한다.
     */
    @Override
    public void pay(String userId, Long fee) {
        BalanceEntity balanceEntity = balanceJpaRepository.findByUserId(userId);
        balanceEntity.setBalance(balanceEntity.getBalance() - fee);
        balanceJpaRepository.save(balanceEntity);
        balanceHistoryJpaRepository.save(paymentMapper.toEntity(userId, fee, false));
    }

    @Override
    public List<Payment> getPayments(String userId) {
        return paymentJpaRepository.findByUserId(userId).stream().map(paymentMapper::toDomain).toList();
    }
}
