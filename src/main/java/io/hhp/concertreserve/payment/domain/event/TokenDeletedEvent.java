package io.hhp.concertreserve.payment.domain.event;

import org.springframework.context.ApplicationEvent;

public class TokenDeletedEvent extends ApplicationEvent {
    private final String userId;

    public TokenDeletedEvent(Object source, String userId) {
        super(source);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}