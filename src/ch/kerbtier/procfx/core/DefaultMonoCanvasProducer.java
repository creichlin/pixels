package ch.kerbtier.procfx.core;

public class DefaultMonoCanvasProducer extends  MonoCanvas {

  protected float[] mono;
  private int width;
  private int height;
  
  
  public void calculate() {
    if(width() == 0 || height() == 0) {
      throw new RuntimeException("need to specify width/height for producers");
    }
    mono = new float[width() * height()];
  }
  
  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  @Override
  public float[] mono() {
    return mono;
  }
}
