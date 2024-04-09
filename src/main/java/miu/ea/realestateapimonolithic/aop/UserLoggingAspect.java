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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoggingAspect.class);

    @Autowired
    private ObjectMapper mapper;

    @Pointcut("execution(* miu.ea.realestateapimonolithic.service.*.*(..))")
    public void allLogger() {
    }

    @Before("allLogger()")
    public void logMethod(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object [] arg = joinPoint.getArgs();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        try {
            LOGGER.info("Started ==> Method(s): {}, Arguments: {}, Timestamp:{}",
                    className + "." + methodName, mapper.writeValueAsString(arg), timestamp);
        } catch (JsonProcessingException e){
            LOGGER.error("Error while processing", e);
        }
    }

    @AfterThrowing(pointcut="allLogger()", throwing = "exception")
    public void logMethodAfterError(JoinPoint joinPoint, RuntimeException exception){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object [] arg = joinPoint.getArgs();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        try {
            LOGGER.info("Error on ==> Method(s): {}, Arguments: {}, Exception details: {}, Timestamp: {}",
                    className + "." + methodName, mapper.writeValueAsString(arg), exception, timestamp);
        } catch (JsonProcessingException e){
            LOGGER.error("Error while processing", e);
        }

    }

    @AfterReturning(pointcut="allLogger()", returning = "result")
    public void logMethodAfterReturning(JoinPoint joinPoint, Object result){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        try {
            LOGGER.info("==> Method(s): {} executed, with result: {}, on timestamp: {}",
                    className + "." + methodName, mapper.writeValueAsString(result), timestamp);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while processing", e);
        }
    }

}
