package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;

public class ExpandMono extends DefaultMonoCanvasOperation {

  @Override
  public void calculate() {
    super.calculate();

    float[] mono = source.mono();

    float min = Float.MAX_VALUE;
    float max = Float.MIN_VALUE;
    
    for (int cnt = 0; cnt < mono.length; cnt++) {
      float v = mono[cnt];
      if (v < min) {
        min = v;
      }
      if (v > max) {
        max = v;
      }

    }
    expand(mono, this.mono, min, max - min);

  }

  public static void expand(float[] s, float[] t, float delta, float fac) {
    for (int cnt = 0; cnt < s.length; cnt++) {
      
      t[cnt] = (s[cnt] - delta) / fac;
    }
  }

}
