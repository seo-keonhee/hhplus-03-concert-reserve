package io.hhp.concertreserve.support.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("Incoming request: method={}, uri={}", req.getMethod(), req.getRequestURI());

        chain.doFilter(request, response);

        logger.info("Outgoing response: status={}", response.isCommitted());
    }
}
