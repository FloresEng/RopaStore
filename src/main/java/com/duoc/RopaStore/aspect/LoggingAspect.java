package com.duoc.RopaStore.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;



@Aspect 
@Component 
public class LoggingAspect {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // se ejecuta antes de cualquier método dell paquete de servicio
    @Before("execution(* com.duoc.RopaStore.service.*.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        logger.info("Ejecutando método de servicio -> {}", joinPoint.getSignature().getName());
    }
}
