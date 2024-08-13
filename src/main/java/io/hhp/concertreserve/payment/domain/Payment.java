package io.hhp.concertreserve.payment.domain;

import io.hhp.concertreserve.payment.domain.event.PaymentSavedEvent;
import io.hhp.concertreserve.payment.domain.event.TokenDeletedEvent;
import io.hhp.concertreserve.support.exception.ConcertException;
import io.hhp.concertreserve.support.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
public class Payment {

    private String reservationId;
    private String userId;
    private Long balance;
    private Long totalFee;
    private LocalDateTime paymentDate;

    public Payment(){}

    public Payment(
            String reservationId
            , String userId
            , Long balance
            , Long totalFee
            , LocalDateTime paymentDate
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.balance = balance;
        this.totalFee = totalFee;
        this.paymentDate = paymentDate;
    }

    @Transactional
    public Payment pay(
            String reservationId
            , LocalDateTime reservationExpiredDate
            , String userId
            , Long totalFee
            , PaymentRepository paymentRepository
            , ApplicationEventPublisher eventPublisher
    ) {
        // 좌석이 예약 가능한지 확인
        if (reservationExpiredDate.isBefore(LocalDateTime.now()))  throw new ConcertException(ErrorCode.RESERVATION_EXPIRED, ErrorCode.RESERVATION_EXPIRED.getMsg());
        // 결제 이력이 있는지 확인
        if (paymentRepository.isPayment(reservationId)) throw new ConcertException(ErrorCode.PAYMENT_IS_FOUND, ErrorCode.PAYMENT_IS_FOUND.getMsg());
        // 잔액이 충분한지 확인
        Long balance = paymentRepository.getBalance(userId).getBalance();
        if (balance < totalFee) throw new ConcertException(ErrorCode.NOT_ENOUGH_BALANCE, ErrorCode.NOT_ENOUGH_BALANCE.getMsg());
        // 결제 진행
        paymentRepository.pay(userId, totalFee);
        Payment payment = new Payment(
                reservationId
                , userId
                , balance
                , totalFee
                , LocalDateTime.now()
        );
        // todo 비동기로 만들기
        // 결제 이력 저장
        eventPublisher.publishEvent(new PaymentSavedEvent(this, payment));
        // 토큰 삭제
        eventPublisher.publishEvent(new TokenDeletedEvent(this, userId));
        return payment;
    }
}
