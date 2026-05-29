package com.example.hackathon012.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.hackathon012.service.impl.CarServiceImpl.create(..)) || "
            + "execution(* com.example.hackathon012.service.impl.CarServiceImpl.update*(..))")
    public void carWriteMethods() {}

    @Before("carWriteMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info(" Có luồng dữ liệu chuẩn bị gọi vào hệ thống tại Method: {}()", methodName);
    }
}
