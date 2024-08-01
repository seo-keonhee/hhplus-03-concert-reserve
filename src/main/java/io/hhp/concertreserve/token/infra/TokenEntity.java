package io.hhp.concertreserve.token.infra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "token", schema = "hhplus_chepter2")
public class TokenEntity {
    @Id
    @Column(name = "token_id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "active_date")
    private LocalDateTime activeDate;

    @Column(name = "deactive_date")
    private LocalDateTime deactiveDate;

}