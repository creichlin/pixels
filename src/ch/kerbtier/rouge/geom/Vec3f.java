package ch.kerbtier.rouge.geom;

import java.nio.FloatBuffer;


public class Vec3f extends Vec {

  public float x, y, z;

  /**
   * Constructor for Vector3f.
   */
  public Vec3f() {

  }

  /**
   * Constructor
   */
  public Vec3f(Vec3f src) {
    set(src);
  }

  /**
   * Constructor
   */
  public Vec3f(float x, float y, float z) {
    set(x, y, z);
  }
  
  public Vec3f(Vec2f v2) {
    set(v2.x, v2.y);
  }

  public Vec3f(Vec2f v2, float z) {
    set(v2.x, v2.y, z);
  }


  
  public Vec3f clone(){
    return new Vec3f(this);
  }

  public Vec3f set(float x, float y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Vec3f set(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }

  /**
   * Load from another Vec3f
   * 
   * @param src
   *          The source vector
   * @return this
   */
  public Vec3f set(Vec3f src) {
    x = src.getX();
    y = src.getY();
    z = src.getZ();
    return this;
  }

  /**
   * @return the length squared of the vector
   */
  public float lengthSquared() {
    return x * x + y * y + z * z;
  }

  public float length() {
    return (float)Math.sqrt(x * x + y * y + z * z);
  }

  public Vec3f add(float x, float y, float z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }

  public Vec3f add(Vec3f vec) {
    x += vec.x;
    y += vec.y;
    z += vec.z;
    return this;
  }

  public Vec3f sub(float x, float y, float z) {
    this.x -= x;
    this.y -= y;
    this.z -= z;
    return this;
  }

  public Vec3f sub(Vec3f vec) {
    x -= vec.x;
    y -= vec.y;
    z -= vec.z;
    return this;
  }

  public Vec3f cross(Vec3f vec) {
    set(y * vec.z - z * vec.y, z * vec.x - x * vec.z, x * vec.y - y * vec.x);
    return this;
  }

  /**
   * Negate a vector
   * 
   * @return this
   */
  public Vec3f negate() {
    x = -x;
    y = -y;
    z = -z;
    return this;
  }

  public Vec3f normalise() {
    float l = length();
    set(x / l, y / l, z / l);
    return this;
  }

  public float dot(Vec3f vec) {
    return x * vec.x + y * vec.y + z * vec.z;
  }

  public float angle(Vec3f b) {
    float dls = dot(b) / (length() * b.length());
    if (dls < -1f)
      dls = -1f;
    else if (dls > 1.0f)
      dls = 1.0f;

    return (float) Math.acos(dls);
  }

  public Vec3f scale(float scale) {

    x *= scale;
    y *= scale;
    z *= scale;

    return this;

  }

  public Vec3f scale(Vec3f scale) {
    x *= scale.x;
    y *= scale.y;
    z *= scale.z;

    return this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(64);

    sb.append("Vec3f[");
    sb.append(x);
    sb.append(", ");
    sb.append(y);
    sb.append(", ");
    sb.append(z);
    sb.append(']');
    return sb.toString();
  }

  public final float getX() {
    return x;
  }

  public final float getY() {
    return y;
  }

  public final Vec3f setX(float x) {
    this.x = x;
    return this;
  }

  public final Vec3f setY(float y) {
    this.y = y;
    return this;
  }

  public final Vec3f setZ(float z) {
    this.z = z;
    return this;
  }

  public float getZ() {
    return z;
  }

  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vec3f other = (Vec3f) obj;

    if (x == other.x && y == other.y && z == other.z) {
      return true;
    }

    return false;
  }
  
  public float distance(Vec3f vec) {
    return clone().sub(vec).length();
  }

  public void round() {
    set(Math.round(x), Math.round(y), Math.round(z));
  }

  public void appendTo(FloatBuffer fb) {
    fb.put(x);
    fb.put(y);
    fb.put(z);
  }

  public Vec2f getXY() {
    return new Vec2f(x, y);
  }

}
