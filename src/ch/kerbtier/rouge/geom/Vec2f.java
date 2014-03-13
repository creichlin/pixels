package ch.kerbtier.rouge.geom;

import java.nio.FloatBuffer;

public class Vec2f extends Vec {

  public float x, y;

  /**
   * Constructor for Vec2f.
   */
  public Vec2f() {

  }

  /**
   * Constructor
   */
  public Vec2f(Vec2f src) {
    set(src);
  }

  /**
   * Constructor
   */
  public Vec2f(float x, float y) {
    set(x, y);
  }
  
  
  public Vec2f clone(){
    return new Vec2f(this);
  }

  public Vec2f set(float x, float y) {
    this.x = x;
    this.y = y;
    return this;
  }

  /**
   * Load from another Vec2f
   * 
   * @param src
   *          The source vector
   * @return this
   */
  public Vec2f set(Vec2f src) {
    x = src.getX();
    y = src.getY();
    return this;
  }

  /**
   * @return the length squared of the vector
   */
  public float lengthSquared() {
    return x * x + y * y;
  }

  public float length() {
    return (float)Math.sqrt(x * x + y * y);
  }

  public Vec2f add(float x, float y) {
    this.x += x;
    this.y += y;
    return this;
  }

  public Vec2f add(Vec2f vec) {
    x += vec.x;
    y += vec.y;
    return this;
  }

  public Vec2f sub(float x, float y) {
    this.x -= x;
    this.y -= y;
    return this;
  }

  public Vec2f sub(Vec2f vec) {
    x -= vec.x;
    y -= vec.y;
    return this;
  }

  public Vec2f negate() {
    x = -x;
    y = -y;
    return this;
  }

  public Vec2f normalise() {
    float l = length();
    set(x / l, y / l);
    return this;
  }

  public float dot(Vec2f vec) {
    return x * vec.x + y * vec.y;
  }

  public float angle(Vec2f b) {
    float dls = dot(b) / (length() * b.length());
    if (dls < -1f)
      dls = -1f;
    else if (dls > 1.0f)
      dls = 1.0f;

    return (float) Math.acos(dls);
  }

  /**
   * gives angle relative to positive x vector with correct sign
   * @return
   */
  public float angle() {
    return (float) Math.atan2(y, x);
  }


  public Vec2f scale(float scale) {
    x *= scale;
    y *= scale;

    return this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(64);

    sb.append("Vec2f[");
    sb.append(x);
    sb.append(", ");
    sb.append(y);
    sb.append(']');
    return sb.toString();
  }

  public final float getX() {
    return x;
  }

  public final float getY() {
    return y;
  }

  public final void setX(float x) {
    this.x = x;
  }

  public final void setY(float y) {
    this.y = y;
  }

  public boolean equals(Vec2f vec) {
    return x == vec.x && y == vec.y;
  }
  
  public float distance(Vec2f vec) {
    return clone().sub(vec).length();
  }

  public void round() {
    set(Math.round(x), Math.round(y));
  }

  public void appendTo(FloatBuffer fb) {
    fb.put(x);
    fb.put(y);
  }
  
  public Vec2f rot90() {
    this.set(-y, x);
    return this;
  }

}
