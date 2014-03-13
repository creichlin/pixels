package ch.kerbtier.procfx.producers;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.core.DefaultRgbCanvasProducer;


public class ColorRgb extends DefaultRgbCanvasProducer {
  private ColorType color = new ColorType();

  @Override
  public void calculate() {
    super.calculate();
    
    for(int cnt = 0; cnt < red.length; cnt++) {
      red[cnt] = color.r;
      green[cnt] = color.g;
      blue[cnt] = color.b;
    }
  }
  
}
