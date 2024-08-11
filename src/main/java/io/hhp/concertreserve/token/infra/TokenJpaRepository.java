package io.hhp.concertreserve.token.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {

    @Query(value = """
        select exists(
            select 1
            from TokenEntity t
            where (t.activeDate is null or t.deactiveDate > :now)
            and t.userId = :userId
        )
    """)
    boolean existsByUserId(String userId, LocalDateTime now);

    TokenEntity findByUserId(String userId);

    void deleteByUserId(String userId);
//    @Query(value = """
//        select t
//        from TokenEntity t
//        where t.activeDate is not null
//        and t.deactiveDate > :now
//        order by t.id desc
//        limit 1
//    """)
//    TokenEntity findLastActiveToken(LocalDateTime now);
//
//    @Query(value = """
//        select t
//        from TokenEntity t
//        where t.activeDate is null
//        order by t.id asc
//        limit :counts
//    """)
//    List<TokenEntity> findWaitingTokens(int counts);
}
