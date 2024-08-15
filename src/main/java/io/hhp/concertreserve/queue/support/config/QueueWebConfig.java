package io.hhp.concertreserve.queue.support.config;

import io.hhp.concertreserve.queue.support.QueueLogFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class QueueWebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<QueueLogFilter> queueLogFilter() {
        FilterRegistrationBean<QueueLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new QueueLogFilter());
        registrationBean.addUrlPatterns("/token/**");

        return registrationBean;
    }
}
