package io.hhp.concertreserve.payment.domain.data_send;

import io.hhp.concertreserve.payment.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataSendService {

    public void sendPayment(Payment payment) throws InterruptedException {
        log.debug("Data Send Start");
        log.debug(payment.toString());
        Thread.sleep(3000);

        log.debug("Data Send End");
    }
}
