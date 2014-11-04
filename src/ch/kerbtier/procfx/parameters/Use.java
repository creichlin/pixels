package ch.kerbtier.procfx.parameters;

import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;
import ch.kerbtier.procfx.core.MonoCanvas;

public class Use extends DefaultMonoCanvasProducer {

  private String name;

  @Override
  public int width() {
    return getSourceCanvas().width();
  }

  private MonoCanvas getSourceCanvas() {
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    MonoCanvas source = (MonoCanvas) getFacade().get(target);
    if(source == null) {
      throw new RuntimeException(target + " not found");
    }
    return source;
  }

  @Override
  public int height() {
    return getSourceCanvas().height();
  }
  
  public void reset() {
    getSourceCanvas().reset();
    super.reset();
  }

  @Override
  public void calculate(boolean force) {
    getSourceCanvas().calculate(true);
    super.calculate(true);
  }

  @Override
  public void calculate() {
    super.calculate();

    MonoCanvas source = getSourceCanvas();
    
    for (int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = source.mono()[cnt];
    }
  }
}
