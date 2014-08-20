package ch.kerbtier.procfx.operation;

import java.util.ArrayList;
import java.util.List;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;

public class Multiply extends DefaultMonoCanvasOperation {

  private float factor = Float.NaN;

  @Param(1)
  private List<MonoCanvas> others = new ArrayList<MonoCanvas>();

  @Override
  public void calculate() {
    super.calculate();

    for (int cnt = 0; cnt < mono.length; cnt++) {
      float val = source.mono()[cnt];
      if (!Float.isNaN(factor)) {
        val *= factor;
      }
      
      for(MonoCanvas other: others) {
        val *=  other.mono()[cnt];
      }
      mono[cnt] = val;
    }
  }

  @Override
  public void calculate(boolean force) {
    for(MonoCanvas other: others) {
      other.calculate(true);
    }
    super.calculate(force);
  }

  @Override
  public void reset() {
    super.reset();
    for(MonoCanvas other: others) {
      other.reset();
    }
  }

}
