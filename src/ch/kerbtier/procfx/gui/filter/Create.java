package ch.kerbtier.procfx.gui.filter;

import java.lang.reflect.InvocationTargetException;


public class Create {
  
  public static View create(Object obj, String path) {
    String viewClassName = obj.getClass().getName().replace("ch.kerbtier.procfx", "ch.kerbtier.procfx.gui.filter");
    try {
      Class<?> viewClass = Class.forName(viewClassName);
      
      View c = (View) viewClass.getDeclaredConstructor(String.class).newInstance(path);
      return c;
    } catch (ClassNotFoundException e) {
      System.out.println("no view for " + viewClassName);
      View view = new View(obj.getClass().getName(), path);
      return view;
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    }
    return null;
  }
}
