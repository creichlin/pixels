package ch.kerbtier.procfx.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectPath implements Iterable<ObjectPath.PathNode> {
  private List<PathNode> nodes = new ArrayList<PathNode>();

  private ObjectPath() {

  }

  public ObjectPath(String path) {
    for (String part : path.split("\\.")) {

      if (part.indexOf("{") != -1) { // it's a map or list
        String field = part.substring(0, part.indexOf("{"));
        String name = part.substring(part.indexOf("{") + 1, part.indexOf("}"));
        nodes.add(new MapNode(field, name));

      } else if (part.indexOf("[") != -1) {
        String field = part.substring(0, part.indexOf("["));
        int index = Integer.parseInt(part.substring(part.indexOf("[") + 1,
            part.indexOf("]")));
        nodes.add(new ListNode(field, index));

      } else { // it's a field
        nodes.add(new FieldNode(part));
      }
    }
  }

  @Override
  public Iterator<ObjectPath.PathNode> iterator() {
    return nodes.iterator();
  }

  public ObjectPath.PathNode get(int pos) {
    return nodes.get(pos);
  }

  public int size() {
    return nodes.size();
  }

  public ObjectPath subPath(int from) {
    ObjectPath op = new ObjectPath();

    for (int cnt = from; cnt < nodes.size(); cnt++) {
      op.nodes.add(nodes.get(cnt));
    }

    return op;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (PathNode pn : nodes) {
      sb.append(pn + ".");
    }

    return sb.substring(0, sb.length() - 1);
  }

  public interface PathNode {
    boolean isMap();

    boolean isList();

    boolean isAttribute();

    String getName();

    String getKey();

    int getIndex();
  }

  class AbstractNode implements PathNode {
    private String name;

    public AbstractNode(String name) {
      this.name = name;
    }

    @Override
    public boolean isMap() {
      return false;
    }

    @Override
    public boolean isList() {
      return false;
    }

    @Override
    public boolean isAttribute() {
      return false;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getKey() {
      return null;
    }

    @Override
    public int getIndex() {
      return -1;
    }

  }

  class MapNode extends AbstractNode {

    private String key;

    public MapNode(String name, String key) {
      super(name);
      this.key = key;
    }

    @Override
    public boolean isMap() {
      return false;
    }

    @Override
    public String getKey() {
      return key;
    }

    public String toString() {
      return getName() + "{" + key + "}";
    }
  }

  class ListNode extends AbstractNode {
    private int index = -1;

    public ListNode(String name, int index) {
      super(name);
      this.index = index;
    }

    @Override
    public boolean isList() {
      return true;
    }

    @Override
    public int getIndex() {
      return index;
    }

    public String toString() {
      return getName() + "[" + index + "]";
    }
}

  class FieldNode extends AbstractNode {

    public FieldNode(String name) {
      super(name);
    }

    @Override
    public boolean isAttribute() {
      return true;
    }
    
    public String toString() {
      return getName();
    }
  }
}
