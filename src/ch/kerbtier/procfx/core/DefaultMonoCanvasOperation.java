package ch.kerbtier.procfx.core;

import ch.kerbtier.procfx.Param;

public class DefaultMonoCanvasOperation extends DefaultMonoCanvasProducer {
  @Param(0)
  protected MonoCanvas source;
  
  @Override
  public int width() {
    return source.width();
  }

  @Override
  public int height() {
    return source.height();
  }

  @Override
  public void calculate() {
    super.calculate();
    source.calculate();
  }
}
