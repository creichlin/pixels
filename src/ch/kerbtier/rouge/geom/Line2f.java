package ch.kerbtier.rouge.geom;

import java.awt.geom.Line2D;


public class Line2f {
  public float sx, sy, ex, ey;

  public Line2f(Vec2f start, Vec2f end) {
    set(start, end);
  }

  public Line2f(Line2f line) {
    set(line);
  }

  public Line2f set(Vec2f start, Vec2f end) {
    sx = start.x;
    sy = start.y;
    ex = end.x;
    ey = end.y;
    return this;
  }

  public Line2f set(Line2f line) {
    sx = line.sx;
    sy = line.sy;
    ex = line.ex;
    ey = line.ey;
    return this;
  }

  /**
   * checks if those two lines intersect
   * 
   * @param o
   * @return
   */
  public boolean intersects(Line2f o) {
    Line2D th = new Line2D.Float(sx, sy, ex, ey);
    Line2D oh = new Line2D.Float(o.sx, o.sy, o.ex, o.ey);
    return th.intersectsLine(oh);
  }

  /**
   * gets intersection of lines, also if lines meet not on lines, use
   * intersects() to check if intersection is on lines
   * 
   * @param o
   * @return
   */
  public Vec2f intersection(Line2f o) {
    Vec2f d1 = asVector();
    Vec2f d2 = o.asVector();
    return cr_intersection(sx, sy, d1.x, d1.y, o.sx, o.sy, d2.x, d2.y);

  }

  private Vec2f cr_intersection(float s1x, float s1y, float d1x, float d1y,
      float s2x, float s2y, float d2x, float d2y) {
    float det = d1x * d2y - d1y * d2x;
    if (det == 0) {
      return null;
    }
    float a = ((s1y - s2y) * d2x - d2y * s1x + d2y * s2x) / det;
    return new Vec2f(d1x, d1y).scale(a).add(s1x, s1y);
  }

  public float length() {
    return asVector().length();
  }

  /**
   * shortens the line by amount f on both sides
   */
  public Line2f shorten(float f) {
    Vec2f delta = new Vec2f(ex - sx, ey - sy).normalise().scale(f);
    sx += delta.x;
    sy += delta.y;

    ex -= delta.x;
    ey -= delta.y;

    return this;
  }

  public Vec2f asVector() {
    return new Vec2f(ex - sx, ey - sy);
  }

  public Vec2f normal() {
    return new Vec2f(-(ey - sy), ex - sx);
  }

  public Line2f clone() {
    return new Line2f(this);
  }

  public Vec2f start() {
    return new Vec2f(sx, sy);
  }

  public Vec2f end() {
    return new Vec2f(ex, ey);
  }

  public Line2f translate(Vec2f vec) {
    return new Line2f(start().add(vec), end().add(vec));
  }
  
  public float distToGerade(Vec2f p) {
    
    Vec2f v = new Vec2f(sx, sy);
    Vec2f w = new Vec2f(ex, ey);
    
    float l2 = v.clone().sub(w).lengthSquared();
    float t = p.clone().sub(v).dot(w.clone().sub(v)) / l2;
    
    Vec2f pro = v.clone().add(w.clone().sub(v).scale(t));
    return pro.distance(p);
  }
  

  public static Line2f startAndDirection(Vec2f start, Vec2f dir) {

    return new Line2f(start, start.clone().add(dir));
  }
}
