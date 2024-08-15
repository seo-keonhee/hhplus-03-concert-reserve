package io.hhp.concertreserve.payment.interfaces.data_send;

import io.hhp.concertreserve.payment.application.DataSendFacade;
import io.hhp.concertreserve.payment.domain.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSendEventListener {

    private final DataSendFacade dataSendFacade;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentSendEvent(PaymentSuccessEvent event) {
        //todo 로그찍기
        //todo 딜래이 걸어서 비동기로 동작하는지 확인
        try {
            dataSendFacade.sendPayment(event.getPayment());
        } catch (InterruptedException e) {
            log.error("Can't not send the paymen", e);
        }

    }
}
