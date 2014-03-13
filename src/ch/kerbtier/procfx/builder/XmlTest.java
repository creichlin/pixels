package ch.kerbtier.procfx.builder;

import java.io.File;

import ch.kerbtier.procfx.Painter;
import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.model.Facade;
import ch.kerbtier.procfx.model.Listener;

public class XmlTest {

  public static void main(String[] args) {
    // doFile("walls");
    doFile("brabra");
  }

  private static void doFile(String pname) {
    Facade facade = new Facade();
    
    facade.register(new Listener() {
      
      @Override
      public void update(String path, Object value) {
        System.out.println("update " + path + " to " + value);
        
      }
    });

    BuildThem bt = new BuildThem(facade);
    
    bt.parse(new File("procfx/" + pname + ".xml"), pname);

    Canvas cv = (Canvas)facade.get("groups{" + pname + "}.elements{out}.canvas");

    cv.calculate();
    
    Painter.paint(cv, "procfx/" + pname);
  }

}
