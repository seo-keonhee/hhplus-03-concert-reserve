package io.hhp.concertreserve.payment.interfaces.dto;

import io.hhp.concertreserve.payment.domain.Balance;

public record BalanceDto(
        String userId
        ,Long balance
) {
    public static BalanceDto toBalanceDto(Balance balance) {
        return new BalanceDto(
                balance.getUserId()
                , balance.getBalance()
        );
    }
}
