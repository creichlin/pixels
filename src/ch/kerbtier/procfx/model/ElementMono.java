package ch.kerbtier.procfx.model;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.core.MonoCanvas;

public class ElementMono extends Base {
  @Param(0)
  private MonoCanvas canvas;
  
  public Canvas getCanvas() {
    return canvas;
  }
}
