package ch.kerbtier.procfx.builder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.kerbtier.procfx.model.Listener;

public class XmlTest {

  public static void main(String[] args) {
    // doFile("walls");
    doFile("valleys");
  }

  private static void doFile(String pname) {
    Loader loader = new Loader();
    
    loader.getFacade().register(new Listener() {
      
      @Override
      public void update(String path, Object value) {
        System.out.println("update " + path + " to " + value);
      }
    });

    loader.add(pname, new File("procfx/" + pname + ".xml"));

    
    BufferedImage bi = loader.renderImage(pname, "out");
    
    try {
      ImageIO.write(bi, "png", new File("procfx/" + pname + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
