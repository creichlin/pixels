package ch.kerbtier.procfx;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParameterInfos {
  private Object subject;

  private Map<String, NamedParameterInfo> namedParams = new HashMap<String, NamedParameterInfo>();
  private List<ParameterInfo> params = new ArrayList<ParameterInfo>();

  public ParameterInfos(Object subject) {
    this.subject = subject;

    // subject.getClass().getClasses()

    Class x = subject.getClass();

    while (x != null) {
      addParams(x);
      x = x.getSuperclass();
    }
  }

  private void addParams(Class x) {
    
    for (Field field : x.getDeclaredFields()) {
      Param p = field.getAnnotation(Param.class);

      if (p != null) {
        int pos = p.value();
        while (params.size() <= pos) {
          params.add(null);
        }
        params.set(pos, new ParameterInfo(this, field));
      } else {
        // assume it's a normal parameter
        
        namedParams.put(field.getName(), new NamedParameterInfo(this, field));
        
        
      }
    }
  }

  public Iterator<String> getNamedParameters() {
    return namedParams.keySet().iterator();
  }

  public ParameterInfo getParameter(int pos) {
    ParameterInfo pi = null;
    boolean mustBeList = false;
    int listIndex = 0;
    try {
      pi = params.get(pos);
    } catch (IndexOutOfBoundsException e) {
      pi = params.get(params.size() - 1);
      mustBeList = true;
      listIndex = pos - params.size();
    }
    
    if(pi.isList()) {
      return pi.indexed(listIndex);
    } else {
      if(mustBeList) {
        throw new RuntimeException("expected list or not enough positioned parameters");
      }
      return pi;
    }
    
  }

  public NamedParameterInfo getParameter(String name) {
    return namedParams.get(name);
  }
}
