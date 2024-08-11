package io.hhp.concertreserve.token.domain;

import java.util.List;

public interface TokenRepository {

    boolean isTokens(String userId);

    Tokens getTokens(String userId);

    void saveAllTokens(List<Tokens> tokens);

    void deletebyUserId(String userId);
}
