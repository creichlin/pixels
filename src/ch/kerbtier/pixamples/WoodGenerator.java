package ch.kerbtier.pixamples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.builder.Loader;
import ch.kerbtier.procfx.function.LinearRGBFunction;

public class WoodGenerator {
  public static void main(String[] args) {
    Random random = new Random();
    Loader loader = new Loader();

    loader.add("merger", new File("pixamples/woodgenerator/merger.xml"));

    
    BufferedImage bg = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
    
    
    int trees = 40;
    
    for (int cnt = 0; cnt < trees; cnt++) {
      
      float factor = cnt / (float)trees;
      
      String type = new String[] { "akazia", "sad", "t1", "t2" }[random.nextInt(4)];

      int variation = random.nextInt(20);

      try {
        BufferedImage img = ImageIO.read(new File("../lsystem/examples/" + type + "/" + variation + ".png"));
        
        
        LinearRGBFunction color = new LinearRGBFunction();
        color.add(0, new ColorType(0, 0, 0));
        float cf = 0.8f + factor * 1f;
        color.add(0.999f, new ColorType(cf, cf, cf));

        loader.setImage("merger", "object", img);
        loader.setNode("merger", "scaled", "canvas.factor", factor * 0.5f + 0.3f );
        loader.setNode("merger", "out", "canvas.x", random.nextInt(1000) - 300);
        loader.setNode("merger", "out", "canvas.y", (int)(200 - factor * 400));
        loader.setNode("merger", "colored", "canvas.gradient", color);
        loader.setImage("merger", "background", bg);

        
        bg = loader.renderImage("merger", "out");
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    
    try {
      ImageIO.write(bg, "png", new File("wood.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
