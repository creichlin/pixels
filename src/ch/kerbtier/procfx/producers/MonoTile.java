package ch.kerbtier.procfx.producers;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;
import ch.kerbtier.procfx.core.MonoCanvas;

import static ch.kerbtier.procfx.Coords.*;

public class MonoTile extends DefaultMonoCanvasProducer {
  @Param(0)
  protected MonoCanvas source;

  @Override
  public void calculate() {
    super.calculate();
    source.calculate();

    int width = width();
    int height = height();
    
    int sourceWidth = source.width();
    int sourceHeight = source.height();
    
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        mono[y * width + x] = source.mono()[repeat(x, y, sourceWidth, sourceHeight)];
      }
    }
  }

}
