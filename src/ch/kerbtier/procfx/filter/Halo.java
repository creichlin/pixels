package ch.kerbtier.procfx.filter;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;

import static ch.kerbtier.procfx.Coords.repeat;

public class Halo extends DefaultMonoCanvasOperation {

  private int radius = 2;
  private Type type = Type.max;

  @Override
  public void calculate() {
    super.calculate();

    int width = width();
    int height = height();
    
    
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        float max = 0;
        float tot = 0;
        for (int lx = -radius; lx <= radius; lx++) {
          for (int ly = -radius; ly <= radius; ly++) {
            float current = source.mono()[repeat(lx + x, ly + y, width,
                height)];
            float factor = (radius + 1) - (float) Math.sqrt(lx * lx + ly * ly);
            if (factor < 0) {
              factor = 0;
            }
            tot += current * factor;
            max += factor;
          }
        }

        int pos = y * width + x;
        switch (type) {
        case min:
          mono[pos] = Math.min(source.mono()[pos], tot / max);
          break;
        case max:
          mono[pos] = Math.max(source.mono()[pos], tot / max);
          break;
        default:
          mono[pos] = tot / max;
        }
      }
    }
  }

  enum Type {
    min, max, current
  }
}
