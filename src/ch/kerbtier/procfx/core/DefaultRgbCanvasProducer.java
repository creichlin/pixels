package ch.kerbtier.procfx.core;

public class DefaultRgbCanvasProducer extends RgbCanvas {
  
  protected float[] red;
  protected float[] green;
  protected float[] blue;
  
  private int width;
  private int height;
  
  
  public void calculate() {
    if(width() == 0 || height() == 0) {
      throw new RuntimeException("need to specify width/height for producers");
    }
    red = new float[width() * height()];
    green = new float[width() * height()];
    blue = new float[width() * height()];
  }
  
  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
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
}
