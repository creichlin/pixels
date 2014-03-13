package ch.kerbtier.procfx.parameters;

import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;
import ch.kerbtier.procfx.core.MonoCanvas;

public class Use extends DefaultMonoCanvasProducer {

   private String name;

  @Override
  public int width() {
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    MonoCanvas source = (MonoCanvas) getFacade().get(target);
    return source.width();
  }

  @Override
  public int height() {
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    MonoCanvas source = (MonoCanvas) getFacade().get(target);
    return source.height();
  }

  @Override
  public void calculate() {
    super.calculate();

    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    MonoCanvas source = (MonoCanvas) getFacade().get(target);
    source.calculate();

    for (int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = source.mono()[cnt];
    }
  }
}
