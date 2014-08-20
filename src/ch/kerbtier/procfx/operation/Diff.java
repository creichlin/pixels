package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;

public class Diff extends DefaultMonoCanvasOperation {

  @Param(1)
  protected MonoCanvas source2;

  public void reset() {
    super.reset();
    source2.reset();
  }

  @Override
  public void calculate(boolean t) {
   
    source2.calculate(true);

    super.calculate(true);
  }
  
  @Override
  public void calculate() {
    super.calculate();

    for (int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = Math.abs(source.mono()[cnt] - source2.mono()[cnt]);
    }
  }

}
