package io.hhp.concertreserve.token.interfaces;

import io.hhp.concertreserve.token.domain.Tokens;

import java.time.LocalDateTime;

public record TokenDto(
        Long tokenId
        , String user_id
        , LocalDateTime activeDate
        , LocalDateTime deactiveDate
) {
    public static TokenDto toTokenDto(Tokens tokens) {
        return new TokenDto(
                tokens.getTokenId()
                , tokens.getUserId()
                , tokens.getActiveDate()
                , tokens.getDeactivateDate()
        );
    }
}
