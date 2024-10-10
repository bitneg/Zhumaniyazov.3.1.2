package zhumaniyazov.boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* zhumaniyazov.boot..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Вызов метода: {}", joinPoint.getSignature().getName());
    }

    @After("execution(* zhumaniyazov.boot..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Метод завершен: {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* zhumaniyazov.boot..*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Ошибка в методе {}: {}", joinPoint.getSignature().getName(), error.getMessage());
    }


}