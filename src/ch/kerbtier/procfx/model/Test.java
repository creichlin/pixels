package ch.kerbtier.procfx.model;

import ch.kerbtier.procfx.Painter;
import ch.kerbtier.procfx.core.Canvas;

public class Test {

  public static void main(String[] args) {

    Facade facade = new Facade();

    facade.register(new Listener() {

      @Override
      public void update(String path, Object value) {
        System.out.println("update " + path + " to " + value);
      }
    });

    facade.set("groups{group1}", new ch.kerbtier.procfx.model.Group());
    facade.set("groups{group1}.elements{element1}",
        new ch.kerbtier.procfx.model.ElementMono());

    facade.set("groups{group1}.elements{element1}.canvas",
        new ch.kerbtier.procfx.filter.Blur());
    facade.set("groups{group1}.elements{element1}.canvas.source",
        new ch.kerbtier.procfx.producers.MonoNoise());
    facade.set("groups{group1}.elements{element1}.canvas.source.width", 128);
    facade.set("groups{group1}.elements{element1}.canvas.source.height", 128);

    Canvas c = (Canvas) facade.get("groups{group1}.elements{element1}.canvas");

    Painter.paint(c, "dada");

    facade.set("groups{group1}.elements{element2}",
        new ch.kerbtier.procfx.model.ElementMono());

  }

}
