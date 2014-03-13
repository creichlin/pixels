package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;

public class ToMonoRgb extends DefaultRgbCanvasOperation {
  
  @Override
  public void calculate() {
    super.calculate();
    
    for (int cnt = 0; cnt < red.length; cnt++) {
      float mono = source.red()[cnt] * 0.3f + source.green()[cnt] * 0.59f + source.blue()[cnt] * 0.11f;
      red[cnt] = mono;
      green[cnt] = mono;
      blue[cnt] = mono;
    }
  }
}
