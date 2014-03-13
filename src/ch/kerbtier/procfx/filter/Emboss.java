package ch.kerbtier.procfx.filter;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;

import static ch.kerbtier.procfx.Coords.repeat;

public class Emboss extends DefaultMonoCanvasOperation {

  private float part = 1 / 4f;

  @Override
  public void calculate() {
    super.calculate();

    float[] sm = source.mono();
    float[] dest = mono;

    int width = width();
    int height = height();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        dest[repeat(x, y, width, height)] = sm[repeat(x - 1, y - 1, width,
            height)]
            * -2
            * part
            + sm[repeat(x + 1, y + 1, width, height)]
            * 2
            * part + 0.5f;

      }
    }

  }
}
