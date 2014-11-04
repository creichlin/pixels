package ch.kerbtier.procfx.builder;

import java.awt.image.BufferedImage;
import java.io.File;

import ch.kerbtier.procfx.Painter;
import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.RgbCanvas;
import ch.kerbtier.procfx.model.Facade;

public class Loader {
  private Facade facade = new Facade();
  
  private BuildThem builder = new BuildThem(facade);
  
  public void add(String group, File file) {
    builder.parse(file, group);
  }
  

  public Facade getFacade() {
    return facade;
  }


  public BufferedImage renderImage(String group, String element) {
    Canvas cv = (Canvas)facade.get("groups{" + group + "}.elements{" + element + "}.canvas");
    cv.process();
    return Painter.paint(cv);
  }

  public BufferedImage renderImage(String group, String element, String alphaElement) {
    RgbCanvas rgb = (RgbCanvas)facade.get("groups{" + group + "}.elements{" + element + "}.canvas");
    MonoCanvas mono = (MonoCanvas)facade.get("groups{" + group + "}.elements{" + alphaElement + "}.canvas");
    rgb.process();
    mono.process();
    return Painter.paint(rgb, mono);
  }


  /**
   * sets an image of an element. the image needs to be the only child of this element.
   * @param group
   * @param element
   * @param image
   */
  public void setImage(String group, String element, BufferedImage image) {
    facade.set("groups{" + group + "}.elements{" + element + "}.canvas.bi", image);
  }


  public void setNode(String group, String element, String path, Object node) {
    facade.set("groups{" + group + "}.elements{" + element + "}." + path, node);
  }

}
