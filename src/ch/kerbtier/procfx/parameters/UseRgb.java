package ch.kerbtier.procfx.parameters;

import ch.kerbtier.procfx.core.DefaultRgbCanvasProducer;
import ch.kerbtier.procfx.core.RgbCanvas;

public class UseRgb extends DefaultRgbCanvasProducer {

  private String name;

  @Override
  public int width() {
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    RgbCanvas source = (RgbCanvas) getFacade().get(target);
    return source.width();
  }

  @Override
  public int height() {
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    RgbCanvas source = (RgbCanvas) getFacade().get(target);
    return source.height();
  }

  @Override
  public void calculate() {
    super.calculate();
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    RgbCanvas source = (RgbCanvas) getFacade().get(target);
    source.calculate();

    for (int cnt = 0; cnt < red.length; cnt++) {
      red[cnt] = source.red()[cnt];
      green[cnt] = source.green()[cnt];
      blue[cnt] = source.blue()[cnt];
    }
  }

}
