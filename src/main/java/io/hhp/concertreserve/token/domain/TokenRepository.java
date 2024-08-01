package io.hhp.concertreserve.token.domain;

import java.util.List;

public interface TokenRepository {

    boolean isTokens(String userId);

    Tokens getTokens(String userId);

    Tokens lastActiveTokens();

    List<Tokens> getWaitingTokens(int counts);

    void saveTokens(Tokens tokens);

    void saveAllTokens(List<Tokens> tokens);
}
