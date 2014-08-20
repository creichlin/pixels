package ch.kerbtier.procfx.filter;

import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;

import static ch.kerbtier.procfx.Coords.repeat;

public class BlurRGB extends DefaultRgbCanvasOperation {

  private int times = 1;

  private float PART = 1 / 16f;

  @Override
  public void calculate() {
    super.calculate();

    float[] sr = source.red();
    float[] sg = source.green();
    float[] sb = source.blue();
    float[] destr = red;
    float[] destg = green;
    float[] destb = blue;

    doPass(sr, destr);
    doPass(sg, destg);
    doPass(sb, destb);

    if (times > 1) {
      float[] backr = new float[sr.length];
      float[] backg = new float[sr.length];
      float[] backb = new float[sr.length];

      for (int cnt = 1; cnt < times; cnt++) {
        doPass(destr, backr);
        doPass(destg, backg);
        doPass(destb, backb);

        float[] tmp = backr;
        backr = destr;
        destr = tmp;

        tmp = backg;
        backg = destg;
        destg = tmp;
        
        tmp = backb;
        backb = destb;
        destb = tmp;
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
            + source[repeat(x, y - 1, width, height)] * 2 * PART +

            source[repeat(x + 1, y + 1, width, height)] * PART
            + source[repeat(x - 1, y + 1, width, height)] * PART
            + source[repeat(x - 1, y - 1, width, height)] * PART
            + source[repeat(x + 1, y - 1, width, height)] * PART;
      }
    }
  }
}
