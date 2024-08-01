package io.hhp.concertreserve.payment.interfaces.dto;

import io.hhp.concertreserve.payment.domain.Payment;

public record PaymentDto(
        String userId
        , String reservationId
        , Long totalFee
        , Long balance
) {
    public static PaymentDto toPaymentDto(Payment payment)
    {
        return new PaymentDto(
                payment.getUserId()
                , payment.getReservationId()
                , payment.getTotalFee()
                , payment.getBalance()
        );
    }
}