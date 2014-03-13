package ch.kerbtier.procfx.draw;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.MonoModify;

import static ch.kerbtier.procfx.Coords.*;

public class MonoRectangle extends MonoModify {

  private int x1, x2, y1, y2;
  private ColorType fillColor = null;

  @Override
  public void apply(MonoCanvas target) {
    
    int width = target.width();
    int height = target.height();
    
    float[] mono = target.mono();
    
    if(fillColor != null) {
      for(int y = y1; y <= y2; y++) {
        for(int x = x1; x <= x2; x++) {
          int pos = repeat(x, y, width, height);
          mono[pos] = fillColor.r;
        }
      }
    }
  }
  
}
