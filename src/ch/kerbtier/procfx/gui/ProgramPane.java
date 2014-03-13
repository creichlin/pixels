package ch.kerbtier.procfx.gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.ScrollPane;

import ch.kerbtier.procfx.model.Listener;
import ch.kerbtier.procfx.model.ObjectPath;
import ch.kerbtier.procfx.model.ObjectPath.PathNode;

public class ProgramPane extends ScrollPane implements Bindable, Listener {

  private Map<String, Element> children = new HashMap<String, Element>();

  @BXML
  private BoxPane elementList = null;

  @Override
  public void initialize(org.apache.pivot.collections.Map<String, Object> arg0,
      URL arg1, Resources arg2) {
    GUI.getFacade().register(this);
  }

  @Override
  public void update(String path, Object value) {
    ObjectPath p = new ObjectPath(path);
    if (p.size() >= 2) { // create an element?
      PathNode group = p.get(0);
      PathNode element = p.get(1);

      if (group.getName().equals("groups")
          && element.getName().equals("elements")) {

        if (p.size() == 2) {
          if (value != null) {
            Element e = new Element(element.getKey());
            elementList.add(e);
            children.put(element.getKey(), e);
          } else {
            Element e = children.get(element.getKey());
            elementList.remove(e);
          }

        } else {
          Element e = children.get(element.getKey());
          e.update(p.subPath(2), value, path);
        }
      }
    }
  }
}
