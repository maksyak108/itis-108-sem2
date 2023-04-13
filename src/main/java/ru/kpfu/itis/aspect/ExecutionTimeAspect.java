package ru.kpfu.itis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionTimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void loggableMethods() {}

    @AfterReturning(pointcut = "loggableMethods()", returning = "response")
    public void logExecutionTime(JoinPoint joinPoint, Object response) {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        long executionTime = end - start;

        LOGGER.info("{} executed in {} ms", joinPoint.getSignature().toShortString(), executionTime);
    }
}