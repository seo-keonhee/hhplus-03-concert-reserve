package io.hhp.concertreserve.support.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ConcertException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String msg;

    @Override
    public String getMessage() {
        return "[%s] %s".formatted(errorCode, msg);
    }
}
