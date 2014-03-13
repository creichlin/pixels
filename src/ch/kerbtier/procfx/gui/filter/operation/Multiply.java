package ch.kerbtier.procfx.gui.filter.operation;

import ch.kerbtier.procfx.gui.filter.FilterView;
import ch.kerbtier.procfx.gui.filter.View;
import ch.kerbtier.procfx.gui.widgets.FloatSliderRow;
import ch.kerbtier.procfx.gui.widgets.ObjectRow;

public class Multiply extends FilterView {

  private ObjectRow other = new ObjectRow("other", this);
  private FloatSliderRow factor = new FloatSliderRow("factor", this, 0, 3, 0.01f);

  public Multiply(String path) {
    super("Multiply", path);
    getRows().add(other);
    getRows().add(factor);
  }
  
  public void setFactor(Float factor) {
    this.factor.setValue(factor);
  }
  
  public void setOther(Object o) {
    other.setView(o);
  }
  
  public View getOther() {
    return other.getView();
  }

}
