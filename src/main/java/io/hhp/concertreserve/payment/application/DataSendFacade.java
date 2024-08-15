package io.hhp.concertreserve.payment.application;

import io.hhp.concertreserve.payment.domain.Payment;
import io.hhp.concertreserve.payment.domain.PaymentService;
import io.hhp.concertreserve.payment.domain.data_send.DataSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSendFacade {

    private final DataSendService dataSendService;
    private final PaymentService paymentService;

    public void sendPayment(Payment payment) throws InterruptedException{
        // 전송할 결제내역 저장
        paymentService.savePayment(payment);
        // 결제내역 전송
        dataSendService.sendPayment(payment);
    }
}
