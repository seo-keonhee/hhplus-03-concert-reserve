package io.hhp.concertreserve.payment.infra;

import io.hhp.concertreserve.payment.domain.Balance;
import io.hhp.concertreserve.payment.domain.Payment;
import io.hhp.concertreserve.payment.infra.entity.BalanceEntity;
import io.hhp.concertreserve.payment.infra.entity.BalanceHistoryEntity;
import io.hhp.concertreserve.payment.infra.entity.PaymentEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public record PaymentMapper() {


    public Payment toDomain(PaymentEntity paymentEntity) {
        return new Payment(
                paymentEntity.getReservationId()
                , paymentEntity.getUserId()
                , 0L
                , paymentEntity.getTotalFee()
                , paymentEntity.getPaymentDate()
        );
    }

    public Balance toDomain(BalanceEntity balanceEntity) {
        return new Balance(balanceEntity.getUserId()
                , balanceEntity.getBalance());
    }

    public PaymentEntity toEntity(Payment payment) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setReservationId(payment.getReservationId());
        paymentEntity.setUserId(payment.getUserId());
        paymentEntity.setTotalFee(payment.getTotalFee());
        paymentEntity.setPaymentDate(payment.getPaymentDate());

        return paymentEntity;
    }

    public BalanceEntity toEntity(Balance balance) {
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setUserId(balance.getUserId());
        balanceEntity.setBalance(balance.getBalance());

        return balanceEntity;
    }

    public BalanceHistoryEntity toEntity(String userId, Long fee, boolean chargeYn) {
        BalanceHistoryEntity balanceHistoryEntity = new BalanceHistoryEntity();
        balanceHistoryEntity.setUserId(userId);
        balanceHistoryEntity.setFee(fee);
        balanceHistoryEntity.setChargeYn(chargeYn ? 1 : 0);
        balanceHistoryEntity.setUpdateDate(LocalDateTime.now());

        return balanceHistoryEntity;
    }
}
