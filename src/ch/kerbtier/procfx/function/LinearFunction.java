package ch.kerbtier.procfx.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LinearFunction implements Function {
  
  private List<Node> nodes = new ArrayList<Node>();
  
  
  @Override
  public float get(float in) {
    Node left = null;
    Node right = null;
    for(Node node: nodes) {
      if(node.position <= in) {
        left = node;
      }
      if(node.position > in) {
        right = node;
        break;
      }
    }
    if(left == null) {
      return right.value;
    }
    if(right == null) {
      return left.value;
    }
    
    float dl = in - left.position;
    float dr = right.position - in;
    
    return (right.value * dl + left.value * dr) / (dl + dr);
  }
  
  public void add(float position, float value) {
    Node node = new Node();
    node.position = position;
    node.value = value;
    nodes.add(node);
    
    Collections.sort(nodes, new Comparator<Node>() {
      @Override
      public int compare(Node o1, Node o2) {
        return Float.compare(o1.position, o2.position);
      }
    });
  }
  
  class Node {
    float position;
    float value;
  }

}
