package io.hhp.concertreserve.support.interceptor;

import io.hhp.concertreserve.support.exception.ConcertException;
import io.hhp.concertreserve.token.domain.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class CheckTokenInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    /**
     * 컨트롤러가 동작하기 전 토큰 검증
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ConcertException {
        // 요청 헤더에서 userId를 가져옵니다.
        String userId = request.getHeader("userId");
        // 토큰이 유효하면 true를 반환하여 요청 처리를 계속합니다.
        return tokenService.vaildToken(userId);
    }
}
