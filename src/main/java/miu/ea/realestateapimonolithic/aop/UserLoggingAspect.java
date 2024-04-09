package miu.ea.realestateapimonolithic.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class UserLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper mapper;

    @Pointcut("execution(* miu.ea.realestateapimonolithic.service.*.*(..))")
    public void allLogger() {
    }

    @Before("allLogger()")
    public void logMethod(JoinPoint joinPoint) {
        String signature = joinPoint.getSignature().getName();
        Object [] arg = joinPoint.getArgs();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        try {
            logger.info("started ==> method(s): {}, arguments: {}, timestamp:{}", signature, mapper.writeValueAsString(arg), timestamp);
        } catch (JsonProcessingException e){
            logger.error("Error while converting", e);
        }
    }

    @AfterThrowing(pointcut="allLogger()", throwing = "exception")
    public void logMethodAfterError(JoinPoint joinPoint, RuntimeException exception){
        String signature = joinPoint.getSignature().getName();
        Object [] arg = joinPoint.getArgs();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        try {
            logger.info("Error on ==> method(s): {}, arguments: {}, exception details: {}, timestamp: {}", signature, mapper.writeValueAsString(arg), exception,timestamp);
        } catch (JsonProcessingException e){
            logger.error("Error while converting", e);
        }

    }

    @AfterReturning(pointcut="allLogger()", returning = "result")
    public void logMethodAfterReturning(JoinPoint joinPoint, Object result){
        String signature = joinPoint.getSignature().getName();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        try {
            logger.info("==> method(s): {} executed, with result: {}, on timestamp: {}", signature, mapper.writeValueAsString(result),timestamp);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting", e);
        }
    }



}
