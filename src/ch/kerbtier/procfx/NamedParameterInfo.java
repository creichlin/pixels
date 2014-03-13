package ch.kerbtier.procfx;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ch.kerbtier.procfx.core.RgbCanvas;
import ch.kerbtier.procfx.function.RGBFunction;

public class NamedParameterInfo {

  private ParameterInfos parameterInfos;
  private Field field;

  public NamedParameterInfo(ParameterInfos parameterInfos, Field field) {
    this.parameterInfos = parameterInfos;
    this.field = field;
  }

  public String name() {
    return field.getName();
  }

  public boolean isString() {
    return field.getType().equals(String.class);
  }
  
  public boolean isFloat() {
    return field.getType().equals(Float.TYPE);
  }
  
  public boolean isInteger() {
    return field.getType().equals(Integer.TYPE);
  }

  public boolean isColor() {
    return field.getType().equals(ColorType.class);
  }

  public boolean isRGBFunction() {
    return field.getType().equals(RGBFunction.class);
  }

  public boolean isEnum() {
    return field.getType().isEnum();
  }

  public Object asEnum(String value) {
    try {
      return Enum.valueOf((Class<Enum>)field.getType(), value);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("illegal enum value '" + value + "' for enum '" + field.getType() + "'");
    }
  }
}
