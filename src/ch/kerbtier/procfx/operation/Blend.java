package ch.kerbtier.procfx.operation;

import static ch.kerbtier.procfx.Coords.repeat;
import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.WrapType;
import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.RgbCanvas;

public class Blend extends DefaultRgbCanvasOperation {

  @Param(1)
  protected RgbCanvas source2;

  @Param(2)
  protected MonoCanvas blend;
  
  private int x;
  private int y;

  public void reset() {
    super.reset();
    blend.reset();
    source2.reset();
  }

  public void calculate(boolean b) {
    source2.calculate(true);
    blend.calculate(true);

    super.calculate(true);
  }

  public void calculate() {
    super.calculate();

    float b[] = blend.mono();

    float rbg[] = source.red();
    float gbg[] = source.green();
    float bbg[] = source.blue();

    float ro[] = source2.red();
    float go[] = source2.green();
    float bo[] = source2.blue();

    int oWidth = source2.width();
    int oHeight = source2.height();

    int bgWidth = width();
    int bgHeight = height();

    for (int x = 0; x < bgWidth; x++) {
      for (int y = 0; y < bgHeight; y++) {
        int bgIndex = repeat(x, y, bgWidth, bgHeight);
        int oIndex = repeat(WrapType.BORDER, x - this.x, y - this.y, oWidth, oHeight);
        
        float f1 = b[oIndex];
        float f2 = 1 - f1;
        
        red[bgIndex] = ro[oIndex] * f1 + rbg[bgIndex] * f2;
        green[bgIndex] = go[oIndex] * f1 + gbg[bgIndex] * f2;
        blue[bgIndex] = bo[oIndex] * f1 + bbg[bgIndex] * f2;
      }
    }
  }

  public void calculateOLD() {
    super.calculate();

    float b[] = blend.mono();

    float r1[] = source.red();
    float g1[] = source.green();
    float b1[] = source.blue();

    float r2[] = source2.red();
    float g2[] = source2.green();
    float b2[] = source2.blue();

    for (int cnt = 0; cnt < red.length; cnt++) {
      float f1 = b[cnt];
      float f2 = 1 - f1;
      red[cnt] = r1[cnt] * f1 + r2[cnt] * f2;
      green[cnt] = g1[cnt] * f1 + g2[cnt] * f2;
      blue[cnt] = b1[cnt] * f1 + b2[cnt] * f2;
    }
  }
}
