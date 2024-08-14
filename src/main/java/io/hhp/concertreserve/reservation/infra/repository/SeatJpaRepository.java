package io.hhp.concertreserve.reservation.infra.repository;

import io.hhp.concertreserve.reservation.infra.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, String> {

    @Query(value = """
        select s
        from SeatEntity s
        where s.seatId not in (:reservationSeats)
    """)
    List<SeatEntity> findBySeatIdNotIn(List<String> reservationSeats);

    SeatEntity findBySeatId(String seatId);
}
