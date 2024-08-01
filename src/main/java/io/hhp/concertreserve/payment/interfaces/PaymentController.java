package io.hhp.concertreserve.payment.interfaces;

import io.hhp.concertreserve.payment.domain.PaymentService;
import io.hhp.concertreserve.payment.interfaces.dto.BalanceDto;
import io.hhp.concertreserve.payment.interfaces.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * 잔액을 조회하여 반환합니다. 잔액(계좌) 정보가 없으면 0원으로 만들어 반환
     */
    @GetMapping("/balance/{userId}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                BalanceDto.toBalanceDto(paymentService.getBalance(userId))
        );
    }

    /**
     * 잔액을 충전한 후 결과를 반환합니다.
     */
    @PostMapping("/balance/charge")
    public ResponseEntity<BalanceDto> chargeBalance(@RequestParam(name = "userId") String userId
            , @RequestParam(name = "chargeFee") Long chargeFee) {
        return ResponseEntity.status(HttpStatus.OK).body(
                BalanceDto.toBalanceDto(paymentService.charge(userId, chargeFee))
        );
    }

    /**
     * 결제를 진행한 후 결과를 반환한다.
     */
    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> processPay(
            @RequestParam(name = "reservationId") String reservationId
            , @RequestParam(name = "reservationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime reservationDate
            , @RequestParam(name = "userId") String userId
            , @RequestParam(name = "totalFee") Long totalFee
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PaymentDto.toPaymentDto(paymentService.pay(reservationId, reservationDate, userId, totalFee))
        );
    }

    /**
     * 결제 완료 목록을 조회한다.
     */
    @GetMapping("/payment/{userId}")
    public ResponseEntity<List<PaymentDto>> getPayments(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                paymentService.getPayments(userId).stream().map(PaymentDto::toPaymentDto).toList()
        );
    }
}
