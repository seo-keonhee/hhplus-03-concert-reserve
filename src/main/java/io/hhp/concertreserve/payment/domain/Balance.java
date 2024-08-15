package io.hhp.concertreserve.payment.domain;

import io.hhp.concertreserve.reservation.support.ReservationException;
import io.hhp.concertreserve.reservation.support.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Balance {
    private String userId;
    private Long balance;

    public Balance() {}

    public Balance(String userId, Long balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public Balance getBalance(String userId, PaymentRepository paymentRepository) {
        Balance balance = new Balance(userId, 0L);
        if(paymentRepository.isBalance(userId)) {
            balance = paymentRepository.getBalance(userId);
        } else {
            paymentRepository.saveBalance(balance);
        }
        return balance;
    }

    @Transactional
    public Balance charge(Balance balance, Long chargeFee, PaymentRepository paymentRepository) {
        if (chargeFee < 0) throw new ReservationException(ErrorCode.CHARGE_AMOUNT_IS_NEGATIVE, ErrorCode.CHARGE_AMOUNT_IS_NEGATIVE.getMsg());
        paymentRepository.charge(balance.getUserId(), chargeFee);
        return new Balance(balance.getUserId(), balance.getBalance() + chargeFee);
    }
}

