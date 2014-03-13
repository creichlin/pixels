package ch.kerbtier.procfx.producers;

import java.util.Random;

import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;

public class MonoNoise extends DefaultMonoCanvasProducer {
  
  private int seed = 0;
  
  public void calculate() {
    super.calculate();
    
    Random r = new Random(seed);
    
    for(int cnt = 0; cnt < mono.length; cnt++) {
      mono[cnt] = r.nextFloat();
    }
  }
}
