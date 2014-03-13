package ch.kerbtier.procfx.gui.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane;

import ch.kerbtier.procfx.model.ObjectPath;
import ch.kerbtier.procfx.model.ObjectPath.PathNode;

public class View extends TablePane {

  private Row type;
  private String name;
  private Label nameLabel = new Label();
  private String path;

  public View(String name, String path) {
    getColumns().add(new Column(60));
    getColumns().add(new Column());

    this.name = name;
    this.path = path;

    type = new Row();
    type.add(new Label("type: "));
    type.add(nameLabel);

    getRows().add(type);
  }

  public String createName() {
    return name;
  }

  public String getName() {
    return name;
  }

  public void update(ObjectPath subPath, Object value, String path) {
    if (subPath.size() == 1) {
      PathNode node = subPath.get(0);
      if (node.isAttribute() && !node.getName().equals("group")) {
        String name = node.getName();
        String methodName = "set" + name.substring(0, 1).toUpperCase()
            + name.substring(1);
        try {
          Method method = null;
          Class<?> parameterClass = value.getClass();
          while (method == null && parameterClass != null) {
            try {
              method = this.getClass().getMethod(methodName, parameterClass);
            } catch (NoSuchMethodException e) {

            }
            parameterClass = parameterClass.getSuperclass();
          }
          method.invoke(this, value);
        }catch(InvocationTargetException ie) {
          ie.printStackTrace();
        } catch (Exception e) {
          System.out.println("no method " + methodName + "(" + value + ") for "
              + this);
          // e.printStackTrace();
        }
      }
      nameLabel.setText(createName());
    } else {
      PathNode node = subPath.get(0);
      if (node.isAttribute() && !node.getName().equals("group")) {
        String name = node.getName();
        String methodName = "get" + name.substring(0, 1).toUpperCase()
            + name.substring(1);
        try {
          Method method = getClass().getMethod(methodName);
          View fv = (View) method.invoke(this);
          fv.update(subPath.subPath(1), value, path);
        } catch (NoSuchMethodException e) {
          System.out.println("no method " + methodName + "() for " + this);
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (Exception e) {
          System.out.println("no method " + methodName + "() for " + this);
        }
      }
    }
  }

  public String getPath() {
    return path;
  }
}
