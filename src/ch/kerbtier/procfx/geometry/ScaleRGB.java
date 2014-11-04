package ch.kerbtier.procfx.geometry;

import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;
import static ch.kerbtier.procfx.Coords.*;

public class ScaleRGB extends DefaultRgbCanvasOperation {
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
          {
            int posTarget = repeat(cx, cy, width, height);
            int posSource1 = repeat((int) (cx / x), (int) (cy / y), sourceWidth, sourceHeight);
            int posSource2 = repeat((int) (cx / x + 1), (int) (cy / y), sourceWidth, sourceHeight);
            int posSource3 = repeat((int) (cx / x + 1), (int) (cy / y + 1), sourceWidth, sourceHeight);
            int posSource4 = repeat((int) (cx / x), (int) (cy / y + 1), sourceWidth, sourceHeight);
            red[posTarget] = (source.red()[posSource1] + source.red()[posSource2] + source.red()[posSource3] + source.red()[posSource4]) / 4;
          }
          {
            int posTarget = repeat(cx, cy, width, height);
            int posSource1 = repeat((int) (cx / x), (int) (cy / y), sourceWidth, sourceHeight);
            int posSource2 = repeat((int) (cx / x + 1), (int) (cy / y), sourceWidth, sourceHeight);
            int posSource3 = repeat((int) (cx / x + 1), (int) (cy / y + 1), sourceWidth, sourceHeight);
            int posSource4 = repeat((int) (cx / x), (int) (cy / y + 1), sourceWidth, sourceHeight);
            green[posTarget] = (source.green()[posSource1] + source.green()[posSource2] + source.green()[posSource3] + source.green()[posSource4]) / 4;
          }
          {
            int posTarget = repeat(cx, cy, width, height);
            int posSource1 = repeat((int) (cx / x), (int) (cy / y), sourceWidth, sourceHeight);
            int posSource2 = repeat((int) (cx / x + 1), (int) (cy / y), sourceWidth, sourceHeight);
            int posSource3 = repeat((int) (cx / x + 1), (int) (cy / y + 1), sourceWidth, sourceHeight);
            int posSource4 = repeat((int) (cx / x), (int) (cy / y + 1), sourceWidth, sourceHeight);
            blue[posTarget] = (source.blue()[posSource1] + source.blue()[posSource2] + source.blue()[posSource3] + source.blue()[posSource4]) / 4;
          }
        }
      }
    } else {

      for (int cy = 0; cy < height; cy++) {
        for (int cx = 0; cx < width; cx++) {
          int posTarget = repeat(cx, cy, width, height);
          int posSource = repeat((int) (cx / x), (int) (cy / y), sourceWidth, sourceHeight);
          red[posTarget] = source.red()[posSource];
          green[posTarget] = source.green()[posSource];
          blue[posTarget] = source.blue()[posSource];
        }
      }
    }

  }

}
