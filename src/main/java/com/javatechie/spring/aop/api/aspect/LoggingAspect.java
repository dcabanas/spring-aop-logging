package com.javatechie.spring.aop.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // o value do pointcut pode ir diretamente para dentro do @Around()
    @Pointcut(value = "execution(* com.javatechie.spring.aop.api.*.*.*(..) )")
    public void myPointcut() {
        // Method is empty as this is just a Pointcut, the implementation is in the advice bellow.
    }

    @Around("myPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        if (logger.isInfoEnabled()) {
            logger.info("Enter: {}.{}() with argument[s] = {}", className, methodName, Arrays.toString(args));
        }

        try {
            Object result = joinPoint.proceed();
            if (logger.isInfoEnabled()) {
                logger.info("Exit: {}.{}() with result = {}", className, methodName, result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(args), className, methodName);
            throw e;
        }

    }

}
