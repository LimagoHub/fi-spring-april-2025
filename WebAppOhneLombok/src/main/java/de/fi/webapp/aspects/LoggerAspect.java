package de.fi.webapp.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class LoggerAspect {

   private System.Logger logger = System.getLogger(LoggerAspect.class.getName());


   // pointcut (sagt an welcher Stelle ein advice eingefuegt wird
   @Before(value = "execution(public * de.fi.webapp.presention.controller.v1.PersonenController.*(..))")
   public void logAdvice(final JoinPoint joinPoint) {
       logger.log(System.Logger.Level.WARNING, String.format(
               "##################### Methode  %s wurde aufgerufen ########################"
               , joinPoint.getSignature().getName()));
   }
    @AfterReturning(value = "execution(public * de.fi.webapp.presention.controller.v1.PersonenController.*(..))", returning="result")
    public void logAfterReturningAdvice(final JoinPoint joinPoint, final Object result) {
        logger.log(System.Logger.Level.WARNING, String.format(
                "##################### Methode  %s wurde erfolgreich beendet ########################"
                , joinPoint.getSignature().getName()));
    }

    @After(value = "execution(public * de.fi.webapp.presention.controller.v1.PersonenController.*(..))")
    public void logAfterAdvice(final JoinPoint joinPoint) {
        logger.log(System.Logger.Level.WARNING, String.format(
                "##################### Methode  %s ist durchgelaufen ########################"
                , joinPoint.getSignature().getName()));
    }

    @AfterThrowing(value="execution(public * de.fi.webapp.presention.controller.v1.PersonenController.*(..))", throwing = "ex")
    public void afterThrowing(final JoinPoint joinPoint, Throwable ex) {

        logger.log(System.Logger.Level.WARNING, String.format(
                "##################### Methode  %s ist gescheitert ########################"
                , joinPoint.getSignature().getName()));
    }
    @Around(value="execution(public * de.fi.webapp.presention.controller.v1.PersonenController.*(..))")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
