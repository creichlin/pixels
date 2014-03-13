package ch.kerbtier.procfx.filter;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;

public class Threshold extends DefaultMonoCanvasOperation {

  private float value = 0.5f;

  @Override
  public void calculate() {
    super.calculate();

    float[] sMono = source.mono(); 
    
    for (int cnt = 0; cnt < mono.length; cnt++) {
      if (sMono[cnt] < value) {
        mono[cnt] = 0;
      } else {
        mono[cnt] = 1;
      }
    }

  }
}
