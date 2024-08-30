package io.hhp.concertreserve.reservation.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // Concert 관련 ErrorCode
    CONCERT_IS_NOT_FOUND("콘서트 정보가 존재하지 않습니다."),
    AVAILABLE_DATE_IS_NOT_FOUND("예약 가능한 날짜가 존재하지 않습니다."),
    SEAT_IS_NOT_FOUND("예약 가능한 좌석 정보가 존재하지 않습니다."),

    // Reservation 관련 ErrorCode
    RESERVATION_IS_ALREADY_EXISTED("이미 해당 좌석의 예약 내역이 존재합니다."),
    RESERVATION_IS_NOT_FOUND("해당 예약 내역이 존재하지 않습니다."),

    // Balance 관련 ErrorCode
    CHARGE_AMOUNT_IS_NEGATIVE("0 이상의 포인트를 충전 가능합니다."),

    // Payment 관련 ErrorCode
    NOT_ENOUGH_BALANCE("잔액이 충분하지 않습니다."),
    RESERVATION_EXPIRED("만료된 예약 좌석입니다."),
    PAYMENT_IS_FOUND("이미 결제되었습니다."),

    // Token 관련 ErrorCode
    TOKEN_IS_NOT_FOUND("토큰이 존재하지 않습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    INVALID_TOKEN("토큰이 유효하지 않습니다."),

    // Queue 관련 ErrorCode
    QUEUE_POSITION("%s 님의 현재대기인원수: %d 명, 대기시간: %s 분");

    private final String msg;
}
