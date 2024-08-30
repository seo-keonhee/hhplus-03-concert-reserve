package io.hhp.concertreserve.payment.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum KafkaTopics {
    SUCCESS_PAY("payment-success");

    private final String topic;
}
