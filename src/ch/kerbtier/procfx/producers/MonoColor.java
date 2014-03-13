package ch.kerbtier.procfx.producers;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;


public class MonoColor extends DefaultMonoCanvasProducer {
  private ColorType color = new ColorType();

  @Override
  public void calculate() {
    super.calculate();
    
    for(int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = color.r;
    }
  }
}
