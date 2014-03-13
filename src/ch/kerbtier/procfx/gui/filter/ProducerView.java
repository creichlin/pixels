package ch.kerbtier.procfx.gui.filter;

import ch.kerbtier.procfx.gui.widgets.IntSliderRow;

public class ProducerView extends View {

  private IntSliderRow width = new IntSliderRow("width", this, 1, 1024);
  private IntSliderRow height = new IntSliderRow("height", this, 1, 1024);
  
  public ProducerView(String name, String path) {
    super(name, path);
    getRows().add(width);
    getRows().add(height);
  }

  public void setWidth(Integer w) {
    width.setValue(w);
  }

  public void setHeight(Integer w) {
    height.setValue(w);
  }
  
  public String createName() {
    return super.createName() + "(" + width.getValue() + "x" + height.getValue() + ")";
  }

}
