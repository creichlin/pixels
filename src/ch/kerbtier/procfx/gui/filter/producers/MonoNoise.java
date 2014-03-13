package ch.kerbtier.procfx.gui.filter.producers;

import ch.kerbtier.procfx.gui.filter.ProducerView;
import ch.kerbtier.procfx.gui.widgets.IntSliderRow;

public class MonoNoise extends ProducerView {

  private IntSliderRow seed = new IntSliderRow("seed", this, 0, 32);
  
  public MonoNoise(String path) {
    super("Noise", path);
  }

  public void setSeed(Integer i) {
    seed.setValue(i);
  }
  
}
