package ch.kerbtier.procfx;

import java.lang.reflect.Field;
import java.util.List;

import ch.kerbtier.procfx.core.RgbCanvas;

public class ParameterInfo {

  private ParameterInfos parameterInfos;
  private Field field;
  private int index = -1;

  public ParameterInfo(ParameterInfos parameterInfos, Field field) {
    this.parameterInfos = parameterInfos;
    this.field = field;
  }

  public ParameterInfo(ParameterInfos parameterInfos, Field field, int index) {
    this(parameterInfos, field);
    this.index = index;
  }

  public boolean isRGB() {
    if (isList()) {
      return false; // TODO
    } else {
      return field.getType().isAssignableFrom(RgbCanvas.class);
    }
  }
  
  public String name() {
    if(index == -1) {
      return field.getName();
    }
    return field.getName() + "[" + index + "]";
  }

  public ParameterInfo indexed(int index) {
    return new ParameterInfo(parameterInfos, field, index);
  }

  public boolean isList() {
    return field.getType().equals(List.class);
  }

}
