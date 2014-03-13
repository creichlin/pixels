package ch.kerbtier.procfx.function;

import ch.kerbtier.procfx.ColorType;

public class LinearRGBFunction implements RGBFunction {

  private LinearFunction r = new LinearFunction();
  private LinearFunction g = new LinearFunction();
  private LinearFunction b = new LinearFunction();
  
  @Override
  public ColorType get(float in) {
    return new ColorType(r.get(in), g.get(in), b.get(in));
  }
  
  public void add(float pos, ColorType rgb) {
    r.add(pos, rgb.r);
    g.add(pos, rgb.g);
    b.add(pos, rgb.b);
  }
}
