package ch.kerbtier.procfx.color;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultRgbCanvasProducer;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.function.RGBFunction;

public class ColorMap extends DefaultRgbCanvasProducer {
  
  private RGBFunction gradient;
  @Param(0)
  private MonoCanvas source;
  
  @Override
  public void calculate() {
    super.calculate();
    source.calculate();
    
    
    for(int cnt = 0; cnt < source.mono().length; cnt++) {
      ColorType col = gradient.get(source.mono()[cnt]);
      
      red[cnt] = col.r;
      green[cnt] = col.g;
      blue[cnt] = col.b;

    }
    
  }

  public int width() {
    return source.width();
  }
  
  public int height() {
    return source.height();
  }
  
}
