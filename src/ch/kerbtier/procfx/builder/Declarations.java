package ch.kerbtier.procfx.builder;

import java.util.HashMap;
import java.util.Map;

public class Declarations {
  private static Map<String, Classes> declarations = new HashMap<String, Classes>();
  
  static {
    declare("Noise", ch.kerbtier.procfx.producers.MonoNoise.class);
    declare("Draw", ch.kerbtier.procfx.draw.Draw.class);
    declare("Canvas", ch.kerbtier.procfx.producers.MonoColor.class, ch.kerbtier.procfx.producers.ColorRgb.class);
    declare("Threshold", ch.kerbtier.procfx.filter.Threshold.class);
    declare("VoronoiBorderGradient", ch.kerbtier.procfx.producers.VoronoiBorderGradient.class);
    declare("Blur", ch.kerbtier.procfx.filter.Blur.class, ch.kerbtier.procfx.filter.BlurRGB.class);
    declare("Multiply", ch.kerbtier.procfx.operation.Multiply.class, ch.kerbtier.procfx.operation.MultiplyRgb.class);
    declare("Add", ch.kerbtier.procfx.operation.Add.class);
    declare("ColorMap", null, ch.kerbtier.procfx.color.ColorMap.class);
    declare("Invert", ch.kerbtier.procfx.operation.MonoInvert.class, ch.kerbtier.procfx.operation.InvertRgb.class);
    declare("Blend", null, ch.kerbtier.procfx.operation.Blend.class);
    declare("Emboss", ch.kerbtier.procfx.filter.Emboss.class);
    declare("Halo", ch.kerbtier.procfx.filter.Halo.class);
    declare("Tile", ch.kerbtier.procfx.producers.MonoTile.class);
    declare("Mono", null, ch.kerbtier.procfx.operation.ToMonoRgb.class);
    declare("Image", ch.kerbtier.procfx.producers.MonoImage.class, ch.kerbtier.procfx.producers.ImageRgb.class);
    
    // Parameters
    declare("Use", ch.kerbtier.procfx.parameters.Use.class, ch.kerbtier.procfx.parameters.UseRgb.class);
    declare("Parameter", ch.kerbtier.procfx.parameters.ParameterMono.class, ch.kerbtier.procfx.parameters.ParameterRgb.class);
    
    // Modifiers
    declare("Rectangle", ch.kerbtier.procfx.draw.MonoRectangle.class);
    declare("Text", ch.kerbtier.procfx.draw.MonoText.class);

  }
  
  
  private static void declare(String name, Class<?> classDef) {
    declare(name, classDef, null);
  }
  
  private static void declare(String name, Class<?> classDef, Class<?> rgbClassDef) {
    Classes classes = new Classes();
    classes.classDef = classDef;
    classes.rgbClassDef = rgbClassDef;
    declarations.put(name, classes);
  }
  
  public static Class<?> get(String name, boolean rgb) {
    Classes cd = declarations.get(name);
    if(cd == null) {
      throw new RuntimeException("no declaration for element '" + name + "'");
    }
    if(!rgb){
      if(cd.classDef == null) {
        throw new RuntimeException("no mono implementation for element '" + name + "'");
      }
      return cd.classDef;
    } else {
      if(cd.rgbClassDef == null) {
        throw new RuntimeException("no RGB implementation for element '" + name + "'");
      }
      return cd.rgbClassDef;
    }
  } 
  
  
}
class Classes{
  Class<?> classDef;
  Class<?> rgbClassDef;
}
