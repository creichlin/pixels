package ch.kerbtier.procfx.producers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.rouge.geom.Line2f;
import ch.kerbtier.rouge.geom.Vec2f;

public class VoronoiBorderGradient extends DefaultMonoCanvasOperation {

  @Param(1)
  private MonoCanvas source2;

  private int scale = 8;

  @Override
  public int width() {
    return super.width() * scale;
  }

  @Override
  public int height() {
    return super.height() * scale;
  }

  @Override
  public void calculate() {
    super.calculate();

    source2.calculate();

    int width = width();
    int height = height();

    int sourceWidth = source.width();
    int source2Width = source2.width();
    int sourceHeight = source.height();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {

        final Vec2f here = new Vec2f(x / (float) scale, y / (float) scale);

        int xv = x / scale;
        int yv = y / scale;

        List<Vec2f> sectors = new ArrayList<Vec2f>();

        for (int xs = xv - 1; xs <= xv + 1; xs++) {
          for (int ys = yv - 1; ys <= yv + 1; ys++) {
            int cx = xs;
            int cy = ys;

            if (cx < 0) {
              cx += sourceWidth;
            }

            if (cy < 0) {
              cy += sourceHeight;
            }

            if (cx >= sourceWidth) {
              cx -= sourceWidth;
            }

            if (cy >= sourceHeight) {
              cy -= sourceHeight;
            }

            sectors.add(new Vec2f(xs + source.mono()[cy * sourceWidth + cx], ys
                + source2.mono()[cy * source2Width + cx]));

          }
        }

        Collections.sort(sectors, new Comparator<Vec2f>() {

          @Override
          public int compare(Vec2f o1, Vec2f o2) {
            float d1 = here.distance(o1);
            float d2 = here.distance(o2);
            return Float.compare(d1, d2);
          }

        });

        final Vec2f closest = sectors.get(0);

        final Vec2f brother = sectors.get(1);

        Vec2f center = closest.clone().add(brother).scale(0.5f);
        Vec2f dir = closest.clone().sub(brother).rot90();

        Line2f line = Line2f.startAndDirection(center, dir);

        float dist = line.distToGerade(here);

        mono[y * width + x] = dist / 1.4f;

      }
    }
  }
}
