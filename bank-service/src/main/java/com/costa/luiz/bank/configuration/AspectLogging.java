package com.costa.luiz.bank.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class AspectLogging {

    private static final Logger log = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(public String " +
            "com.costa.luiz.bank.model.transfer.TransferService." +
            "transfer(com.costa.luiz.bank.model.transfer.Transfer))")
    public void transferMethodPointcut() {
    }

    @Around("transferMethodPointcut())")
    public Object logNewTransfer(ProceedingJoinPoint joinPoint) throws Throwable {
        var start = Instant.now();
        var correlationId = MDC.get(MDCInterceptor.CORRELATION_ID_KEY);
        log.info("CorrelationId -> {} - Started -> {}",
                correlationId,
                Thread.currentThread());

        var returnedValue = joinPoint.proceed();
        var seconds = Duration.between(start, Instant.now()).toMillis();

        log.info("CorrelationId -> {} - Finished -> {} milliseconds - {}",
                correlationId,
                seconds,
                Thread.currentThread());

        return returnedValue;
    }
}