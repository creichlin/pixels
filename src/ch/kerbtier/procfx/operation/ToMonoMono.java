package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;
import ch.kerbtier.procfx.core.RgbCanvas;

public class ToMonoMono extends DefaultMonoCanvasProducer {
  
  @Param(0)
  private RgbCanvas source;

  public void calculate(boolean f) {
    source.calculate(true);
    super.calculate(true);
  }
  
  public void reset() {
    super.reset();
    source.reset();
  }
  
  @Override
  public void calculate() {
    super.calculate();
    
    for (int cnt = 0; cnt < source.red().length; cnt++) {
      float monoc = source.red()[cnt] * 0.3f + source.green()[cnt] * 0.59f + source.blue()[cnt] * 0.11f;
      mono[cnt] = monoc;
    }
    
  }

  public int width() {
    return source.width();
  }
  
  public int height() {
    return source.height();
  }
}
