package ch.kerbtier.procfx.gui.filter.operation;

import ch.kerbtier.procfx.gui.filter.FilterView;
import ch.kerbtier.procfx.gui.filter.View;
import ch.kerbtier.procfx.gui.widgets.ObjectRow;

public class Blend extends FilterView {

  private ObjectRow source2 = new ObjectRow("source2", this);
  private ObjectRow blend = new ObjectRow("blend", this);


  public Blend(String path) {
    super("Add", path);
  }
  
  public void setSource2(Object o) {
    source2.setView(o);
  }

  public void setBlend(Object o) {
    blend.setView(o);
  }
  
  public View getSource2() {
    return source2.getView();
  }

  public View getBlend() {
    return blend.getView();
  }

}
