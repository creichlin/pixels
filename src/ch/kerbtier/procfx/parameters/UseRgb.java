package ch.kerbtier.procfx.parameters;

import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.core.DefaultRgbCanvasProducer;
import ch.kerbtier.procfx.core.RgbCanvas;

public class UseRgb extends DefaultRgbCanvasProducer {

  private String name;

  @Override
  public int width() {
    return getSourceCanvas().width();
  }

  private Canvas getSourceCanvas() {
    String target = "groups{" + getGroup() + "}.elements{" + name + "}.canvas";
    Canvas source = (Canvas) getFacade().get(target);
    return source;
  }

  @Override
  public int height() {
    return getSourceCanvas().height();
  }
  
  public void reset() {
    Canvas source = getSourceCanvas();
    source.reset();
    super.reset();
  }

  @Override
  public void calculate(boolean b) {
    RgbCanvas source = (RgbCanvas)getSourceCanvas();
    source.calculate(true);
    
    super.calculate(true);
  }
  
  @Override
  public void calculate() {
    super.calculate();

    RgbCanvas source = (RgbCanvas)getSourceCanvas();

    for (int cnt = 0; cnt < red.length; cnt++) {
      red[cnt] = source.red()[cnt];
      green[cnt] = source.green()[cnt];
      blue[cnt] = source.blue()[cnt];
    }
  }

}
