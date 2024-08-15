package io.hhp.concertreserve.reservation.support.config;

import io.hhp.concertreserve.queue.domain.TokenService;
import io.hhp.concertreserve.reservation.support.ReservationLogFilter;
import io.hhp.concertreserve.reservation.support.WaitingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ReservationWebConfig implements WebMvcConfigurer {

    private final TokenService tokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WaitingInterceptor(tokenService))
                .addPathPatterns("/reservations/**");
    }

    @Bean
    public FilterRegistrationBean<ReservationLogFilter> loggingFilter() {
        FilterRegistrationBean<ReservationLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ReservationLogFilter());
        registrationBean.addUrlPatterns("/reservations/**");

        return registrationBean;
    }
}
