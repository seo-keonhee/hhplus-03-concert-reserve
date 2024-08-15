package io.hhp.concertreserve.reservation.support;

import io.hhp.concertreserve.queue.domain.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class WaitingInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    /**
     * 컨트롤러가 동작하기 전 토큰 검증
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ReservationException {
        // 요청 헤더에서 userId를 가져옵니다.
        String userId = request.getHeader("userId");
        // 토큰이 유효하면 true를 반환하여 요청 처리를 계속합니다.
        return tokenService.validToken(userId);
    }
}
