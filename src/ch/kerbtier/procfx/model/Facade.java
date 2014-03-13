package ch.kerbtier.procfx.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Facade {

  private Root root = new Root();

  private List<Listener> listeners = new ArrayList<Listener>();

  public void set(String subject, Object value) {
    Object old = get(subject);

    if (old == null && value == null) {
      // both are the same, do nothing
      return;
    }

    if (old != null && value != null) {
      if (old.equals(value)) {
        // both are the same, do nothing
        return;
      }
    }

    resolve(subject).set(value);

    triggerUpdate(subject, value);

  }

  public Object get(String subject) {
    return resolve(subject).get();
  }

  private ElementFacade resolve(String subject) {
    try {
      ElementFacade current = new FieldElementFacade(this, "root");
      for (String part : subject.split("\\.")) {
        if (current.get() == null) {
          throw new NoSuchFieldError(subject + " " + current);
        }

        if (part.indexOf("{") != -1) { // it's a map or list
          String field = part.substring(0, part.indexOf("{"));
          String name = part
              .substring(part.indexOf("{") + 1, part.indexOf("}"));
          current = new MapElementFacade(current.get(), field, name);

        } else if (part.indexOf("[") != -1) {
          String field = part.substring(0, part.indexOf("["));
          int index = Integer.parseInt(part.substring(part.indexOf("[") + 1,
              part.indexOf("]")));
          current = new ListElementFacade(current.get(), field, index);

        } else { // it's a field
          current = new FieldElementFacade(current.get(), part);
        }
      }
      return current;
    } catch (NoSuchFieldError e) {
      throw new InvalidPathException("path: " + subject, e);
    }
  }

  public void register(Listener listener) {
    listeners.add(listener);
  }

  // trigger listeners

  public void triggerUpdate(String path, Object value) {
    for (Listener listener : listeners) {
      listener.update(path, value);
    }
  }

  abstract class ElementFacade {
    protected Object subject;
    private String field;

    public ElementFacade(Object subject, String field) {
      this.subject = subject;
      this.field = field;
    }

    abstract Object get();

    abstract void set(Object object);

    protected Field getField() {
      Class<?> current = subject.getClass();

      while (current != null) {
        try {
          Field fieldDeclaration = current.getDeclaredField(field);
          fieldDeclaration.setAccessible(true);
          return fieldDeclaration;
        } catch (NoSuchFieldException e) {

        } catch (SecurityException e) {
          e.printStackTrace();
        }
        current = current.getSuperclass();
      }
      throw new NoSuchFieldError("no field '" + field + "' for element '" + subject + "'");
    }
  }

  class MapElementFacade extends ElementFacade {
    private String name;

    public MapElementFacade(Object subject, String field, String name) {
      super(subject, field);
      this.name = name;
    }

    @Override
    Object get() {
      try {
        Field field = getField();
        Map<String, Object> map = (Map<String, Object>) field.get(subject);
        return map.get(name);
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      throw new NoSuchFieldError("no valid map with entry: (" + name + ")");
    }

    @Override
    void set(Object object) {
      try {
        Field field = getField();
        Map<String, Object> map = (Map<String, Object>) field.get(subject);
        map.put(name, object);
      } catch (Exception e) {
        throw new NoSuchFieldError("no valid map with entry: (" + name + ")");
      }
    }
  }

  class FieldElementFacade extends ElementFacade {
    public FieldElementFacade(Object subject, String field) {
      super(subject, field);
    }

    @Override
    Object get() {
      try {
        Field field = getField();
        return field.get(subject);
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      throw new NoSuchFieldError("should not happen");
    }

    @Override
    void set(Object object) {
      try {
        Field field = getField();
        field.set(subject, object);
      } catch (Exception e) {
        e.printStackTrace();
        throw new NoSuchFieldError("should not happen");
      }
    }
  }

  class ListElementFacade extends ElementFacade {
    private int index;

    public ListElementFacade(Object subject, String field, int index) {
      super(subject, field);
      this.index = index;
    }

    @Override
    Object get() {
      try {
        Field field = getField();
        List<Object> list = (List<Object>) field.get(subject);
        if (index < list.size()) {
          return list.get(index);
        }
        return null;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      throw new NoSuchFieldError("no valid list with entry: (" + index + ")");
    }

    @Override
    void set(Object object) {
      try {
        Field field = getField();
        List<Object> list = (List<Object>) field.get(subject);

        while (index >= list.size()) {
          list.add(null);
        }

        list.set(index, object);
      } catch (Exception e) {
        throw new NoSuchFieldError("no valid list with entry: (" + index + ")");
      }
    }
  }

}
