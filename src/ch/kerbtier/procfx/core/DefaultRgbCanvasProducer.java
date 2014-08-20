package ch.kerbtier.procfx.core;

public class DefaultRgbCanvasProducer extends RgbCanvas {

  protected float[] red;
  protected float[] green;
  protected float[] blue;

  public void calculate(boolean force) {
    try {
      if (red == null) {
        calculate();
      }
    } catch (Exception e) {
      System.out.println("error in " + this);
      throw e;
    }
  }

  public void calculate() {
    red = new float[width() * height()];
    green = new float[width() * height()];
    blue = new float[width() * height()];
  }

  public float[] red() {
    return red;
  }

  public float[] green() {
    return green;
  }

  public float[] blue() {
    return blue;
  }

  @Override
  public void reset() {
    red = green = blue = null;
  }
}
