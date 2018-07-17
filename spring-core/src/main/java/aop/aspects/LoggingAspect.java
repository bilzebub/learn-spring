package aop.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
  private Logger logger = Logger.getLogger(getClass().getName());

  @Before("execution(void aop..*.set*(*))")
  public void callSetters(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    Object arg = joinPoint.getArgs()[0];
    System.out.println("Called " + method + " with arg " + arg
     + " on " + joinPoint.getTarget());
  }

  @Around("execution(aop.beans.Team playGame())")
  public Object playing(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("Starting the game");
    Object result = joinPoint.proceed();
    System.out.println("The game is over");
    return result;
  }

  @Around("@annotation(Timed)")
  public void measureDuration(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    joinPoint.proceed();
    System.out.println("Prepare for " + (System.currentTimeMillis() - start) + " ms");
  }

}
