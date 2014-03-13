package ch.kerbtier.procfx.gui.widgets;

import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane.Row;

import ch.kerbtier.procfx.gui.filter.Create;
import ch.kerbtier.procfx.gui.filter.View;

public class ObjectRow extends Row {
  
  private String name;
  
  private View parent;
  private View view;
  private Border border = new Border();
  
  public ObjectRow(String name, View parent) {
    add(new Label(name));
    this.parent = parent;
    this.name = name;
    
    add(border);
  }
  
  public void setView(Object o) {
    view = Create.create(o, parent.getPath() + "." + name);
    border.setContent(view);

  }

  public View getView() {
    return view;
  }
}
