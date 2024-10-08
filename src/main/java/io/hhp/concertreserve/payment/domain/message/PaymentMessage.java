package io.hhp.concertreserve.payment.domain.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.hhp.concertreserve.payment.domain.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Getter
@Setter
public class PaymentMessage {

    private Long messageId;
    private String message;

    public PaymentMessage() {}

    public static PaymentMessage from(Payment payment) {
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setMessageId(payment.getReservationId());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            String messageContents = mapper.writeValueAsString(payment);
            paymentMessage.setMessage(messageContents);
        } catch (JsonProcessingException e) {
            log.error("Can't convert payment to message", e);
        }
        return paymentMessage;
    }

    public Payment toPayment() {
        Payment payment = new Payment();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            payment = mapper.readValue(message, Payment.class);
        } catch(IOException e) {
            log.error("Can't convert message to payment", e);
        }
        return payment;
    }
}

