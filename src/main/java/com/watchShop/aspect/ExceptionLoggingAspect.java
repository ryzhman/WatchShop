package com.watchShop.aspect;

import com.watchShop.exception.GenericEngineException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Ryzhkov on 04.11.2017.
 */
@Aspect
@Component //since there is no app.xml file with all bean, we need to configure manually
public class ExceptionLoggingAspect {
//    @Pointcut("execution(* com.watchShop.controller..*(..))")
    @Pointcut("bean(*Controller)")
    public void controllerMethod() {
    }

    @Pointcut("execution(* com.watchShop.controller.WatchController.getWatchByTitle(..))")
    public void exactControllerMethod() {
    }

    @Around("controllerMethod()")
    public void logBefore(ProceedingJoinPoint pjp) {
        try {
            pjp.proceed();
        } catch (GenericEngineException e) {
            System.out.println("GenericEngineException was caught in controller layer. The message is " + e.getMessage());
        } catch (Throwable e) {
            System.out.println("Exception of type " + e.getClass() + " was caught in controller layer. The message is " + e.getMessage());
        }
        System.out.println("******");
    }
}
