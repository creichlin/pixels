package ch.kerbtier.procfx;

import ch.kerbtier.procfx.function.LinearRGBFunction;
import ch.kerbtier.procfx.function.RGBFunction;

public class MiniParsers {

  public static ColorType color(String cs) {
    ColorType cot = new ColorType();

    if (cs.startsWith("#") && cs.length() == 2) {
      cot.r = cot.g = cot.b = Integer.parseInt(
          cs.substring(1) + cs.substring(1), 16) / 255f;
    } else if (cs.startsWith("#") && cs.length() == 3) {
      cot.r = cot.g = cot.b = Integer.parseInt(cs.substring(1), 16) / 255f;
    }else if(cs.startsWith("#") && cs.length() == 7) {
      cot.r = Integer.parseInt(cs.substring(1, 3), 16) / 255f;
      cot.g = Integer.parseInt(cs.substring(3, 5), 16) / 255f;
      cot.b = Integer.parseInt(cs.substring(5, 7), 16) / 255f;
    } else {
      throw new RuntimeException("invalid color value '" + cs + "'");
    }

    return cot;
  }

  public static RGBFunction rgbFunction(String gradient) {

    // #f3c5bd 0%,#e86c57 50%,#ea2803 51%,#ff6600 75%,#c72200 100%
    LinearRGBFunction lingrad = new LinearRGBFunction();

    for (String part : gradient.split(",")) {
      float posValue = 0;
      ColorType colorValue = new ColorType(0, 0, 0);

      String color = part.split(" ")[0];
      String pos = part.split(" ")[1];

      if (color.startsWith("#")) {
        if (color.length() == 7) {
          String r = color.substring(1, 3);
          String g = color.substring(3, 5);
          String b = color.substring(5, 7);

          colorValue.r = Integer.parseInt(r, 16) / 256f;
          colorValue.g = Integer.parseInt(g, 16) / 256f;
          colorValue.b = Integer.parseInt(b, 16) / 256f;
        } else {
          throw new java.lang.IllegalArgumentException(
              "color gradient has no valid color part '" + part + "'");
        }

      } else {
        throw new java.lang.IllegalArgumentException(
            "color gradient has no valid color part '" + part + "'");
      }

      if (pos.endsWith("%")) {
        posValue = Float.parseFloat(pos.substring(0, pos.length() - 1)) / 100;
      } else {
        throw new java.lang.IllegalArgumentException(
            "color gradient has no valid position part '" + part + "'");
      }

      lingrad.add(posValue, colorValue);

    }
    return lingrad;
  }
}
