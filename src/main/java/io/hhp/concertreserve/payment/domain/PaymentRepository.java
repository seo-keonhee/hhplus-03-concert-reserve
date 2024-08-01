package io.hhp.concertreserve.payment.domain;

import java.util.List;

public interface PaymentRepository {

    boolean isBalance(String userId);

    void saveBalance(Balance balance);

    Balance getBalance(String userId);

    void charge(String userId, Long fee);

    void pay(String userId, Long fee);

    List<Payment> getPayments(String userId);

    boolean isPayment(String reservationId);

    void savePayment(Payment payment);
}
