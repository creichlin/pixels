package ch.kerbtier.procfx.model;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public aspect EventProducer {

  private Facade currentFacade;

  @Pointcut("execution(Base.new())")
  public void executeBaseConstructor() {
    // empty
  }

  @Pointcut("execution(void Facade.set(..))")
  public void executeFacadeCommand() {
    // empty
  }

  @Pointcut("execution(@NamedParam * Base+.set*(*)) && @annotation(namedParam) && args(param)")
  public void executeSetNamedParameter(NamedParam namedParam, Object param) {
    // empty
  }

  @After("executeBaseConstructor()")
  public void triggerBaseConstructor(JoinPoint joinPoint) {
    try {
      Field f = Base.class.getDeclaredField("facade");
      f.setAccessible(true);
      f.set(joinPoint.getThis(), currentFacade);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Before("executeFacadeCommand()")
  public void triggerFacadeCommand(JoinPoint joinPoint) {
    currentFacade = (Facade) joinPoint.getThis();
  }


}
