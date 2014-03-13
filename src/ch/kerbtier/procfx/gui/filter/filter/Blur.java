package ch.kerbtier.procfx.gui.filter.filter;

import ch.kerbtier.procfx.gui.filter.FilterView;
import ch.kerbtier.procfx.gui.widgets.IntSliderRow;

public class Blur extends FilterView {

  private IntSliderRow times = new IntSliderRow("times", this, 1, 32);

  public Blur(String path) {
    super("Blur", path);
    
    getRows().add(times);
  }
  
  public void setTimes(Integer times) {
    this.times.setValue(times);
  }

}
