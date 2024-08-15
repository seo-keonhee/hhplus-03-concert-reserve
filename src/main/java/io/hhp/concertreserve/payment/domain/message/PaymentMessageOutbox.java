package io.hhp.concertreserve.payment.domain.message;

import java.util.List;

public interface PaymentMessageOutbox {
    void write(PaymentMessage message);

    void updateState(Long messageId);

    List<PaymentMessage> findAllNotPublished();
}
