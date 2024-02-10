package my.homework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@my.homework.aspect.Timer *)")
    public void beansAnnotatedWith() {
    }

    @Pointcut("@annotation(my.homework.aspect.Timer)")
    public void methodsAnnotatedWith() {
    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public void measurement(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        joinPoint.proceed();
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();

        log.info("target = {}, method = {}, time = {}",
                joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), elapsed);
    }
}
