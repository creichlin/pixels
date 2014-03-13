package ch.kerbtier.procfx;

public class Coords {
  public static final int repeat(int x, int y, int width, int height) {
    
    while(x < 0) {
      x += width; 
    }
    
    while(y < 0) {
      y += height; 
    }
    
    while(x >= width) {
      x -= width;
    }
    
    while(y >= height) {
      y -= height;
    }
    
    return y * width + x;
  }
}
