package ch.kerbtier.procfx.gui.filter;

import ch.kerbtier.procfx.gui.widgets.ObjectRow;

public class FilterView extends View {

  private ObjectRow source = new ObjectRow("source", this);
  
  public FilterView(String name, String path) {
    super(name, path);
    getRows().add(source);
  }

  public void setSource(Object o) {
    source.setView(o);
  }
  
  public View getSource() {
    return source.getView();
  }

}
