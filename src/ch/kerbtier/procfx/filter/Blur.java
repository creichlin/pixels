package ch.kerbtier.procfx.filter;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;

import static ch.kerbtier.procfx.Coords.repeat;

public class Blur extends DefaultMonoCanvasOperation {

  private int times = 1;

  private float PART = 1 / 16f;

  @Override
  public void calculate() {
    super.calculate();

    float[] sm = source.mono();
    float[] dest = mono;

    doPass(sm, dest);

    if (times > 1) {
      float[] back = new float[mono.length];

      for (int cnt = 1; cnt < times; cnt++) {
        doPass(mono, back);

        float[] tmp = back;
        back = mono;
        mono = tmp;
      }
    }
  }

  private void doPass(float[] source, float[] dest) {
    int width = width();
    int height = height();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        dest[repeat(x, y, width, height)] = source[repeat(x, y, width, height)]
            * 4 * PART + source[repeat(x + 1, y, width, height)] * 2 * PART
            + source[repeat(x - 1, y, width, height)] * 2 * PART
            + source[repeat(x, y + 1, width, height)] * 2 * PART
            + source[repeat(x, y + 1, width, height)] * 2 * PART +

            source[repeat(x + 1, y + 1, width, height)] * PART
            + source[repeat(x - 1, y + 1, width, height)] * PART
            + source[repeat(x - 1, y - 1, width, height)] * PART
            + source[repeat(x + 1, y - 1, width, height)] * PART;
      }
    }
  }
}
