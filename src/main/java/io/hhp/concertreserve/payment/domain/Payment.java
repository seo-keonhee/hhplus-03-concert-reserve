package io.hhp.concertreserve.payment.domain;

import io.hhp.concertreserve.reservation.support.ReservationException;
import io.hhp.concertreserve.reservation.support.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
public class Payment {

    private Long reservationId;
    private String userId;
    private Long balance;
    private Long totalFee;
    private LocalDateTime paymentDate;

    public Payment(){}

    public Payment(
            Long reservationId
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
            Long reservationId
            , LocalDateTime reservationExpiredDate
            , String userId
            , Long totalFee
            , PaymentRepository paymentRepository
            , PaymentEventPublisher paymentEventPublisher
    ) {
        // 좌석이 예약 가능한지 확인
        if (reservationExpiredDate.isBefore(LocalDateTime.now()))  throw new ReservationException(ErrorCode.RESERVATION_EXPIRED, ErrorCode.RESERVATION_EXPIRED.getMsg());
        // 결제 이력이 있는지 확인
        if (paymentRepository.isPayment(reservationId)) throw new ReservationException(ErrorCode.PAYMENT_IS_FOUND, ErrorCode.PAYMENT_IS_FOUND.getMsg());
        // 잔액이 충분한지 확인
        Long balance = paymentRepository.getBalance(userId).getBalance();
        if (balance < totalFee) throw new ReservationException(ErrorCode.NOT_ENOUGH_BALANCE, ErrorCode.NOT_ENOUGH_BALANCE.getMsg());
        // 결제 진행
        paymentRepository.pay(userId, totalFee);

        // 이벤트 발생
        Payment payment = new Payment(reservationId, userId, balance, totalFee, LocalDateTime.now());
        paymentEventPublisher.successEvent(new PaymentSuccessEvent(payment));
        return payment;
    }
}
