package io.hhp.concertreserve.support.interceptor;

import io.hhp.concertreserve.token.domain.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final TokenService tokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 콘서트관련 API에 대해서만 인터셉터를 적용합니다.
        registry.addInterceptor(new CheckTokenInterceptor(tokenService))
                .addPathPatterns("/reservations/**");
    }

}