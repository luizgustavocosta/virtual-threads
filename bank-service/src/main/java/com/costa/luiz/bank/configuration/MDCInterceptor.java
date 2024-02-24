package com.costa.luiz.bank.configuration;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@WebFilter
public class MDCInterceptor implements HandlerInterceptor {

    public static final String CORRELATION_ID_KEY = "CorrelationId";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        MDC.put(CORRELATION_ID_KEY, getCorrelationId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception exception) {
        MDC.remove(CORRELATION_ID_KEY);
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
