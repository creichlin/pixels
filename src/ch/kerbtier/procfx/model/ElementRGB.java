package ch.kerbtier.procfx.model;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.core.RgbCanvas;

public class ElementRGB extends Base {
  @Param(0)
  private RgbCanvas canvas;
  
  public Canvas getCanvas() {
    return canvas;
  }
}
