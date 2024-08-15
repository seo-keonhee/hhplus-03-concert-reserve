package io.hhp.concertreserve.queue.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Tokens {

    private Long tokenId;
    private String userId;
    private LocalDateTime activeDate;
    private LocalDateTime deactivateDate;
//    private Long remainWaitNo;
//    private Duration remainWaitTime;

    public Tokens(
            final Long tokenId
            , final String userId
            , final LocalDateTime activeDate
            , final LocalDateTime deactivateDate
//            , final Long remainWaitNo
//            , final Duration remainWaitTime
    ) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.activeDate = activeDate;
        this.deactivateDate = deactivateDate;
//        this.remainWaitNo = remainWaitNo; //DB에 넣지 않는 정보(너무 자주 변동)
//        this.remainWaitTime = remainWaitTime;  //DB에 넣지 않는 정보(너무 자주 변동)
    }

    public Tokens() {}

    /**
     * 토큰을 최초 생성
     */
    public static Tokens create(
            String userId
    ) {
        return new Tokens(
                0L //AUTO_INCREMENT
                , userId
                , LocalDateTime.now()
                , LocalDateTime.now().plusMinutes(30)
//                , 0L
//                , null
        );
    }

//    /**
//     * 토큰 정보를 받아서 대기열 정보를 추가한다.
//     */
//    public static Tokens setQueue(Tokens tokens, Tokens lastActiveTokens){
//
//
//        Long interval = Math.max(tokens.getTokenId() - lastActiveTokens.getTokenId(), 0);
//
//        return new Tokens(
//                tokens.getTokenId()
//                , tokens.getUserId()
//                , tokens.getActiveDate()
//                , tokens.getDeactivateDate()
//                , interval
//                , Duration.ofMinutes(30).multipliedBy(1 + Math.floorDiv(interval,50)));
//    }

//    /**
//     * 토큰을 활성화한다.
//     */
//    public static List<Tokens> active(List<Tokens> waitingTokens) {
//        List<Tokens> activeTokens = new ArrayList<>();
//        for (Tokens tokens : waitingTokens) {
//            activeTokens.add(
//                    new Tokens(
//                            tokens.getTokenId()
//                            , tokens.getUserId()
//                            , LocalDateTime.now()
//                            , LocalDateTime.now().plusMinutes(30)
//                            , 0L
//                            , null
//                    )
//            );
//        }
//        return activeTokens;
//    }
}
