package ch.kerbtier.procfx.parameters;

import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;
import ch.kerbtier.procfx.core.MonoCanvas;

public class ParameterMono extends DefaultMonoCanvasProducer {

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
    MonoCanvas source = (MonoCanvas)null; //  getContext().getParameter(name);
    source.calculate();

    for (int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = source.mono()[cnt];
    }
  }
}
