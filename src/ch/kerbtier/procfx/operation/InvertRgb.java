package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;

public class InvertRgb extends DefaultRgbCanvasOperation {
  
  @Override
  public void calculate() {
    super.calculate();
    
    for (int cnt = 0; cnt < red.length; cnt++) {
      red[cnt] = 1 - source.red()[cnt];
      green[cnt] = 1 - source.green()[cnt];
      blue[cnt] = 1 - source.blue()[cnt];
    }
  }
}
