package ch.kerbtier.pixels.textures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ch.kerbtier.pixels.MonoProducer;
import ch.kerbtier.pixels.Param;
import ch.kerbtier.rouge.geom.Vec2f;

public class Voronoi implements MonoProducer {
  @Param(0)
  private MonoProducer dx;
  @Param(1)
  private MonoProducer dy;
  @Param(2)
  private MonoProducer value;
  
  public Voronoi() {
    
  }
  
  public Voronoi(MonoProducer dx, MonoProducer dy, MonoProducer value) {
    this.dx = dx;
    this.dy = dy;
    this.value = value;
  }
  
  @Override
  public float get(float x, float y) {
    final Vec2f here = new Vec2f(x, y);
    List<Sector> sectors = new ArrayList<Sector>();
    
    for(float xs = x - 1; xs < x + 1.5f; xs++) {
      for(float ys = y - 1; ys < y + 1.5f; ys++) {
        sectors.add(create(xs, ys));
      }
    }
    
    Collections.sort(sectors, new Comparator<Sector>() {

      @Override
      public int compare(Sector o1, Sector o2) {
        float d1 = here.distance(o1.point);
        float d2 = here.distance(o2.point);
        return Float.compare(d1, d2);
      }
      
    });

    
    return value.get(sectors.get(0).x, sectors.get(0).y);
  }
  
  public Sector create(float x, float y) {
    Sector sector = new Sector();
    sector.x = x;
    sector.y = y;
    sector.point = new Vec2f((float)(Math.floor(x) + dx.get(x, y)), (float)(Math.floor(y) + dy.get(x, y)));
    return sector;
  }

  class Sector {
    float x;
    float y;
    Vec2f point;
  }
}
