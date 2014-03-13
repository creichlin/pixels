package ch.kerbtier.procfx.core;

import ch.kerbtier.procfx.Param;

public class DefaultRgbCanvasOperation extends DefaultRgbCanvasProducer {
  @Param(0)
  protected RgbCanvas source;
  
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

  @Override
  public float[] red() {
    return red;
  }

  @Override
  public float[] green() {
    return green;
  }

  @Override
  public float[] blue() {
    return blue;
  }

}
