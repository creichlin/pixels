package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;
import ch.kerbtier.procfx.core.RgbCanvas;

public class MultiplyRgb extends DefaultRgbCanvasOperation {

  private float factor = Float.NaN;

  @Param(1)
  private RgbCanvas other;

  @Override
  public void calculate() {
    super.calculate();
    if (other != null) {
      other.calculate();
    }

    for (int cnt = 0; cnt < red.length; cnt++) {
      float valr = source.red()[cnt];
      float valg = source.green()[cnt];
      float valb = source.blue()[cnt];
      if (!Float.isNaN(factor)) {
        valr *= factor;
        valg *= factor;
        valb *= factor;
      }
      if (other != null) {
        valr *= other.red()[cnt];
        valg *= other.green()[cnt];
        valb *= other.blue()[cnt];
      }
      red[cnt] = valr;
      green[cnt] = valg;
      blue[cnt] = valb;
    }
  }
}
