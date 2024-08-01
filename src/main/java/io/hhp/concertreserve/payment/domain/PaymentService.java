package io.hhp.concertreserve.payment.domain;

import io.hhp.concertreserve.support.exception.ConcertException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 좌석의 예약 만료시간은 예약 후 2시간이다.
     */
    private int expiredTime = 2;

    /**
     * 잔액 조회
     */
    public Balance getBalance(String userId) {
        Balance balance = new Balance();
        return balance.getBalance(userId, paymentRepository);
    }

    /**
     * 잔액 충전
     */
    public Balance charge(String userId, Long chargeFee) {
        lock.lock();
        try {
            Balance balance = new Balance();
            return balance.charge(balance.getBalance(userId, paymentRepository), chargeFee, paymentRepository);
        }
        catch(ConcertException e) {
            log.error("충전에 실패하였습니다. : userId={}, chargeFee={}", userId, chargeFee);
            throw e;
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 결제 진행 및 결제 이력 추가
     */
    public Payment pay(String reservatoinId, LocalDateTime reservationDate, String userId, Long totalFee) {
        lock.lock();
        try {
            log.info("서비스 진입, 락OK");
            Payment payment = new Payment();
            return payment.pay(reservatoinId, reservationDate.plusHours(expiredTime), userId, totalFee, paymentRepository);
        }
        catch(ConcertException e) {
            log.error("결제에 실패하였습니다. : reservatoinId={}, totalFee={}, userId={}", reservatoinId, totalFee, userId);
            throw e;
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 사용자가 결제한 이력을 조회한다.
     */
    public List<Payment> getPayments(String userId) {
        return paymentRepository.getPayments(userId);
    }
}
