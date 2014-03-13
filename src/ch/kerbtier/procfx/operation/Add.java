package ch.kerbtier.procfx.operation;

import java.util.ArrayList;
import java.util.List;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;

public class Add extends DefaultMonoCanvasOperation {
  
  @Param(1)
  private List<MonoCanvas> sources = new ArrayList<MonoCanvas>();

  @Override
  public void calculate() {
    super.calculate();
    
    for(MonoCanvas mc: sources) {
      mc.calculate();
    }
    
    for (int cnt = 0; cnt < mono.length; cnt++) {
      float val = source.mono()[cnt];
      
      for(MonoCanvas mc: sources) {
        val += mc.mono()[cnt];
      }
      
      mono[cnt] = val;
    }
  }
  
  
  
  
  
}
