package ch.kerbtier.procfx;

public class Coords {

  
  public static final int repeat(int x, int y, int width, int height) {
    return repeat(WrapType.REPEAT, x, y, width, height);
  }
  
  
  public static final int repeat(WrapType type, int x, int y, int width, int height) {

    if (type == WrapType.REPEAT) {

      while (x < 0) {
        x += width;
      }

      while (y < 0) {
        y += height;
      }

      while (x >= width) {
        x -= width;
      }

      while (y >= height) {
        y -= height;
      }

    } else if (type == WrapType.BORDER) {
      
      if(x >= width) {
        x = width - 1;
      }
      
      if(x < 0) {
        x = 0;
      }
      
      if(y >= height) {
        y = height - 1;
      }
      
      if(y < 0) {
        y = 0;
      }
      
      
    } else {
      throw new RuntimeException();
    }
    
    return y * width + x;
    
  }
}
