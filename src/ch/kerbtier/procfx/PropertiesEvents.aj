package ch.kerbtier.procfx;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import ch.kerbtier.procfx.core.Procfx;
import ch.kerbtier.procfx.model.NamedParam;

@Aspect
public class PropertiesEvents {

  @Pointcut("execution(@NamedParam * ch.kerbtier.procfx.core.Procfx+.*(*)) && @annotation(namedParam)")
  public void executeSetNamedParameter(NamedParam namedParam) {
    // empty
  }

  @Before("executeSetNamedParameter(namedParam)")
  public void setParameter(JoinPoint joinPoint, NamedParam namedParam) {
    Procfx subject = (Procfx)joinPoint.getThis();
    System.out.println("set named param: " + namedParam.value() + " of " + subject);
  }
}

