package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;

public class MonoInvert extends DefaultMonoCanvasOperation {
  
  @Override
  public void calculate() {
    super.calculate();
    
    for (int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = 1 - source.mono()[cnt];
    }
  }
  
  
  
  
  
}
