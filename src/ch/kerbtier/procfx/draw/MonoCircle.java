package ch.kerbtier.procfx.draw;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.MonoModify;

import static ch.kerbtier.procfx.Coords.*;

public class MonoCircle extends MonoModify {

  private int x, y, radius;
  private ColorType fillColor = null;

  @Override
  public void apply(MonoCanvas target) {
    
    int width = target.width();
    int height = target.height();
    
    float[] mono = target.mono();
    
    int radiusSquared = radius * radius;
    
    if(fillColor != null) {
      for(int y = -radius; y <= radius; y++) {
        for(int x = -radius; x <= radius; x++) {
          
          if(y * y + x * x <= radiusSquared){
            int pos = repeat(x + this.x, y + this.y, width, height);
            mono[pos] = fillColor.r;
          }
        }
      }
    }
  }
  
}
