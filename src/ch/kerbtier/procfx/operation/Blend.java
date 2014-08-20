package ch.kerbtier.procfx.operation;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultRgbCanvasOperation;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.RgbCanvas;

public class Blend extends DefaultRgbCanvasOperation {
  
  @Param(1)
  protected RgbCanvas source2;

  @Param(2)
  protected MonoCanvas blend;
  
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
    
    float r1[] = source.red();
    float g1[] = source.green();
    float b1[] = source.blue();
    
    float r2[] = source2.red();
    float g2[] = source2.green();
    float b2[] = source2.blue();
    
    for(int cnt = 0; cnt < red.length; cnt++) {
      float f1 = b[cnt];
      float f2 = 1 - f1;
      red[cnt] = r1[cnt] * f1 + r2[cnt] * f2; 
      green[cnt] = g1[cnt] * f1 + g2[cnt] * f2; 
      blue[cnt] = b1[cnt] * f1 + b2[cnt] * f2; 
    }
  }
}
