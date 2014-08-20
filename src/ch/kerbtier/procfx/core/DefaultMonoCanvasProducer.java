package ch.kerbtier.procfx.core;

public class DefaultMonoCanvasProducer extends MonoCanvas {

  protected float[] mono;

  public void calculate(boolean force) {

    try {
      if (mono == null) {
        calculate();
      }

    } catch (Exception e) {
      System.out.println("error in " + this);
      throw e;
    }

  }

  public void calculate() {
    mono = new float[width() * height()];
  }

  @Override
  public float[] mono() {
    return mono;
  }

  @Override
  public void reset() {
    mono = null;
  }
}
