package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;

public class Multiply extends DefaultMonoCanvasOperation {

  private float factor = Float.NaN;

  @Param(1)
  private MonoCanvas other;

  @Override
  public void calculate() {
    super.calculate();
    if (other != null) {
      other.calculate();
    }

    for (int cnt = 0; cnt < mono.length; cnt++) {
      float val = source.mono()[cnt];
      if (!Float.isNaN(factor)) {
        val *= factor;
      }
      if (other != null) {
        val *= other.mono()[cnt];
      }
      mono[cnt] = val;
    }
  }
}
