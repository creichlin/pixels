package ch.kerbtier.procfx.geometry;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import static ch.kerbtier.procfx.Coords.*;


public class ScaleMono extends DefaultMonoCanvasOperation {
  private float factor;

  @Override
  public int width() {
    return (int)(source.width() * factor);
  }

  @Override
  public int height() {
    return (int)(source.height() * factor);
  }

  @Override
  public void calculate() {
    super.calculate();
    
    int width = width();
    int height = height();
    
    int sourceWidth = source.width();
    int sourceHeight = source.height();
    
    for(int cy = 0; cy < height; cy++) {
      for(int cx = 0; cx < width; cx++) {
        int posTarget = repeat(cx, cy, width, height);
        int posSource = repeat((int)(cx / factor), (int)(cy / factor), sourceWidth, sourceHeight);
        mono[posTarget] = source.mono()[posSource]; 
      }
    }
    
    
    
  }
  
  
  
}
