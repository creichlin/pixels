package ch.kerbtier.procfx.gui;

import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Container;
import org.apache.pivot.wtk.ContainerMouseListener;
import org.apache.pivot.wtk.Expander;
import org.apache.pivot.wtk.FillPane;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Mouse.ScrollType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Rollup;
import org.apache.pivot.wtk.TablePane;

import ch.kerbtier.procfx.gui.filter.Create;
import ch.kerbtier.procfx.gui.filter.View;
import ch.kerbtier.procfx.model.ObjectPath;
import ch.kerbtier.procfx.model.ObjectPath.PathNode;

public class Element extends Expander {

  private View canvas = null;

  public Element(String name) {
    
    setTitle(name);
    setExpanded(false);
    
    getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter(){

      @Override
      public boolean mouseClick(Component component,
          org.apache.pivot.wtk.Mouse.Button button, int x, int y, int count) {
        if(count == 1) {
          GUI.getImagePane().select(canvas.getPath());
        }
        return true;
      }
      
    });
  }

  public void update(ObjectPath subPath, Object value, String path) {
    if (subPath.size() > 0) {
      PathNode pn = subPath.get(0);
      if (pn.getName().equals("canvas")) {
        if (subPath.size() == 1) {
          if (value != null) {
            canvas = Create.create(value, path);
            setContent(canvas);
          } else {
            setContent(null);
            canvas = null;
          }
        } else {
          canvas.update(subPath.subPath(1), value, path);
        }
      }
    }
  }
}
