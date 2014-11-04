package ch.kerbtier.procfx.geometry;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import static ch.kerbtier.procfx.Coords.*;

public class ScaleMono extends DefaultMonoCanvasOperation {
  private float factor = 1;
  
  private float x = 0;
  private float y = 0;

  @Override
  public int width() {
    if(x == 0) {
      x = factor;
    }
    
    return (int) (source.width() * x);
  }

  @Override
  public int height() {
    if(y == 0) {
      y = factor;
    }
    
    return (int) (source.height() * y);
  }

  @Override
  public void calculate() {
    super.calculate();

    int width = width();
    int height = height();

    int sourceWidth = source.width();
    int sourceHeight = source.height();

    // TODO scale smaller by integer factors properly, only two is supported currently
    if (x == 0.5f && y == 0.5f) {
      for (int cy = 0; cy < height; cy++) {
        for (int cx = 0; cx < width; cx++) {
          int posTarget = repeat(cx, cy, width, height);
          int posSource1 = repeat((int) (cx / x), (int) (cy / y), sourceWidth, sourceHeight);
          int posSource2 = repeat((int) (cx / x + 1), (int) (cy / y), sourceWidth, sourceHeight);
          int posSource3 = repeat((int) (cx / x + 1), (int) (cy / y + 1), sourceWidth, sourceHeight);
          int posSource4 = repeat((int) (cx / x), (int) (cy / y + 1), sourceWidth, sourceHeight);
          mono[posTarget] = (source.mono()[posSource1] + source.mono()[posSource2] + source.mono()[posSource3] + source.mono()[posSource4]) / 4;
        }
      }
    } else {

      for (int cy = 0; cy < height; cy++) {
        for (int cx = 0; cx < width; cx++) {
          int posTarget = repeat(cx, cy, width, height);
          int posSource = repeat((int) (cx / x), (int) (cy / y), sourceWidth, sourceHeight);
          mono[posTarget] = source.mono()[posSource];
        }
      }
    }

  }

}
