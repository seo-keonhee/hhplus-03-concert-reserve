package io.hhp.concertreserve.concert.interfaces;

import io.hhp.concertreserve.concert.domain.ConcertService;
import io.hhp.concertreserve.concert.interfaces.dto.ConcertDto;
import io.hhp.concertreserve.concert.interfaces.dto.ReservationDto;
import io.hhp.concertreserve.concert.interfaces.dto.SeatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    /**
     * 모든 콘서트 목록을 조회
     */
    @GetMapping("/all")
    public ResponseEntity<List<ConcertDto>> getAllConcerts() {
        return ResponseEntity.status(HttpStatus.OK).body(
                concertService.getAllconcerts().stream().map(ConcertDto::toConcertDto).toList());
    }

    /**
     * 예약가능 일정을 조회
     */
    @GetMapping("/schedule/{concertId}")
    public ResponseEntity<List<ConcertDto>> getAvailableSchedules(@PathVariable String concertId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                concertService.getSchedules(concertId).stream().map(ConcertDto::toConcertDto).toList());
    }

    /**
     * 예약 가능한 좌석목록을 조회
     */
    @GetMapping("/seat/{scheduleId}")
    public ResponseEntity<List<SeatDto>> getAvailableSeats(@PathVariable String scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                concertService.getSeats(scheduleId).stream().map(SeatDto::toSeatDto).toList());
    }

    /**
     * 좌석예약을 요청
     */
    @PostMapping("/apply")
    public ResponseEntity<ReservationDto> applyReservation(@RequestParam(name = "scheduleId") String scheduleId
            , @RequestParam(name = "seatId") String seatId, @RequestParam(name = "userId") String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ReservationDto.toReservationDto(concertService.applyReservation(scheduleId,seatId,userId)));
    }

    /**
     * 요청자의 예약 목록을 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<ReservationDto>> getReservations(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                concertService.getReservations(userId).stream().map(ReservationDto::toReservationDto).toList());
    }
}