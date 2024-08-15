package io.hhp.concertreserve.payment.support.config;

import io.hhp.concertreserve.payment.support.PaymentLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PaymentWebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<PaymentLogFilter> loggingFilter() {
        FilterRegistrationBean<PaymentLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PaymentLogFilter());
        registrationBean.addUrlPatterns("/payment/**", "/balance/**");

        return registrationBean;
    }
}
