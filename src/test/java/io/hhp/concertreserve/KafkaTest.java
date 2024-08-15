package io.hhp.concertreserve;

import io.hhp.concertreserve.payment.domain.Payment;
import io.hhp.concertreserve.payment.domain.message.PaymentMessage;
import io.hhp.concertreserve.payment.domain.message.PaymentMessageOutbox;
import io.hhp.concertreserve.payment.infra.kafka.PaymentKafkaMessageSender;
import io.hhp.concertreserve.payment.interfaces.kafka.OutboxScheduler;
import io.hhp.concertreserve.payment.interfaces.kafka.PaymentMessageConsumer;

import io.hhp.concertreserve.payment.interfaces.kafka.PaymentMessageOutboxConsumer;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"payment-success"})
public class KafkaTest {

    @Autowired
    private PaymentKafkaMessageSender paymentKafkaMessageSender;

    @Autowired
    private PaymentMessageConsumer paymentMessageConsumer;

    @Autowired
    private PaymentMessageOutboxConsumer paymentMessageOutboxConsumer;

    @Autowired
    private OutboxScheduler outboxScheduler;
    @Autowired
    private PaymentMessageOutbox paymentMessageOutbox;

    /**
     * 카프카 메세지 발행 및 소비 테스트
     */
    @Test
    public void testSendAndReceive() throws Exception {
        // given
        Payment payment = new Payment(1L,"u001",20000L, 15000L, LocalDateTime.now());
        PaymentMessage message = new PaymentMessage().toMessage(payment);

        // whne
        paymentKafkaMessageSender.send(message);
        Thread.sleep(2000);

        // then
        String receiveMessage = paymentMessageConsumer.getReceiveMessage();
        assertEquals(message.getMessage(), receiveMessage);
    }

    /**
     * 아웃박스 로직 테스트
     */
    @Test
    public void testOutboxConsumer() throws Exception {
        //given
        Payment payment = new Payment(2L,"u002",20000L, 15000L, LocalDateTime.now());
        PaymentMessage message = new PaymentMessage().toMessage(payment);

        //when
        paymentKafkaMessageSender.send(message);
        Thread.sleep(2000);

        // then
        String receiveMessage = paymentMessageConsumer.getReceiveMessage();
        assertEquals(message.getMessage(), receiveMessage);
        // 컨슈머가 메세지를 받았다면 아웃박스에서 1개이상의 메세지의 상테값이 1(published)로 갱신됬을 것으로 추측
        assertTrue(paymentMessageOutboxConsumer.getUpdateCount() > 0);
    }

    /**
     * 스케줄러 로직 테스트
     */
    @Test
    public void testOutboxScheduler() throws Exception {
        // given
        Payment payment1 = new Payment(3L,"u003",20000L, 15000L, LocalDateTime.now());
        paymentMessageOutbox.write(new PaymentMessage().toMessage(payment1));
        Payment payment2 = new Payment(4L,"u004",20000L, 15000L, LocalDateTime.now());
        paymentMessageOutbox.write(new PaymentMessage().toMessage(payment2));
        Payment payment3 = new Payment(5L,"u005",20000L, 15000L, LocalDateTime.now());
        paymentMessageOutbox.write(new PaymentMessage().toMessage(payment3));

        //when
        outboxScheduler.resend();

        // then
        // 프로듀서를 이용한 것이 아닌 강제로 아웃박스에만 넣은 데이터를 스케줄러의 메소드를 동작시킬 경우 카프카에 메세지가 재발행될 것으로 추측
        assertEquals(3, outboxScheduler.getMessages().size());
    }
}

