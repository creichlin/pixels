package ch.kerbtier.rouge.geom;

public class Rect2f {
  
  private Vec2f center = new Vec2f();
  private float width = 1;
  private float height = 1;
  private float angle = 0; 
  
  public Rect2f(Vec2f center, float width) {
    this.center.set(center);
    this.width = width;
    this.height = width;
  }
  
  
  
}
