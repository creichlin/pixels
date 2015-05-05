package ch.kerbtier.procfx.core;

public abstract class Canvas extends Procfx {
  private int width;
  private int height;
  private String sizeFrom;
  
  public abstract void reset();
  
  
  public void process() {
    reset();
    calculate(true);
  }

  
  public void calculate(boolean force) {
    Canvas sizeFrom = getSizeFrom();
    if((width() == 0 || height() == 0) && sizeFrom == null) {
      throw new RuntimeException("need to specify width/height for producers");
    }
 };
  
  public abstract void calculate();
  
  private Canvas getSizeFrom() {
    Canvas sizeFrom = null;
    if(this.sizeFrom != null) {
      String target = "groups{" + getGroup() + "}.elements{" + this.sizeFrom + "}.canvas";
      sizeFrom = (Canvas) getFacade().get(target);
    }
    return sizeFrom;
  }
  
  public int width() {
    Canvas sizeFrom = getSizeFrom();
    if(sizeFrom != null) {
      return sizeFrom.width();
    }
    return width;
  }

  public int height() {
    Canvas sizeFrom = getSizeFrom();
    if(sizeFrom != null) {
      return sizeFrom.height();
    }
    return height;
  }
  
  public boolean contains(int x, int y) {
    return x > 0 && x < width() && y > 0 && y < height();
  }

}
