package ch.kerbtier.procfx.parameters;

import ch.kerbtier.procfx.core.DefaultRgbCanvasProducer;
import ch.kerbtier.procfx.core.RgbCanvas;

public class ParameterRgb extends DefaultRgbCanvasProducer {

  private String name;

  @Override
  public int width() {
    return 10; // getContext().getParameter(name).width();
  }

  @Override
  public int height() {
    return 10; // getContext().getParameter(name).height();
  }

  @Override
  public void calculate() {
    super.calculate();
    RgbCanvas source = (RgbCanvas)null; // getContext().getParameter(name);
    source.calculate();

    for (int cnt = 0; cnt < red.length; cnt++) {
      red[cnt] = source.red()[cnt];
      green[cnt] = source.green()[cnt];
      blue[cnt] = source.blue()[cnt];
    }
  }
}
