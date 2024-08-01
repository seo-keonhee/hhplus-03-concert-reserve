package io.hhp.concertreserve.token.infra;

import io.hhp.concertreserve.token.domain.Tokens;
import org.springframework.stereotype.Component;

@Component
public record TokenMapper() {

    public Tokens toDomain(TokenEntity tokenEntity) {
        return new Tokens(
                tokenEntity.getId()
                , tokenEntity.getUserId()
                , tokenEntity.getActiveDate()
                , tokenEntity.getDeactiveDate()
        );
    }

    public TokenEntity toEntity(Tokens tokens) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(tokens.getTokenId());
        tokenEntity.setUserId(tokens.getUserId());
        tokenEntity.setActiveDate(tokens.getActiveDate());
        tokenEntity.setDeactiveDate(tokens.getDeactivateDate());

        return tokenEntity;
    }
}
