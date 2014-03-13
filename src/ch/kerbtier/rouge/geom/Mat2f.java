package ch.kerbtier.rouge.geom;

public class Mat2f {

  public float m00, m01, m10, m11;

  /**
   * Constructor for Mat2f. The matrix is initialized to the identity.
   */
  public Mat2f() {
    setIdentity();
  }
  
  public Mat2f(Vec2f v1, Vec2f v2){
    set(v1, v2);
  }

  public Mat2f(Vec2f v1, Vec2f v2, boolean horizontal){
    if(horizontal) {
      setHorizontal(v1, v2);
    } else{
      set(v1, v2);
    }
  }

  public Mat2f(float m00, float m01, float m10, float m11){
    set(m00, m01, m10, m11);
  }

  public Mat2f(Mat2f src) {
    set(src);
  }
  
  public Mat2f set(float m00, float m01, float m10, float m11){
    this.m00 = m00;
    this.m01 = m01;
    this.m10 = m10;
    this.m11 = m11;
    return this;
  }

  public Mat2f set(Vec2f v1, Vec2f v2){
    this.m00 = v1.x;
    this.m10 = v1.y;
    this.m01 = v2.x;
    this.m11 = v2.y;
    return this;
  }

  public Mat2f setHorizontal(Vec2f v1, Vec2f v2){
    this.m00 = v1.x;
    this.m01 = v1.y;
    this.m10 = v2.x;
    this.m11 = v2.y;
    return this;
  }

  public Mat2f set(Mat2f src) {
    m00 = src.m00;
    m01 = src.m01;
    m10 = src.m10;
    m11 = src.m11;

    return this;
  }

  public Mat2f add(Mat2f right) {
    m00 += right.m00;
    m01 += right.m01;
    m10 += right.m10;
    m11 += right.m11;

    return this;
  }

  public Mat2f sub(Mat2f right) {
    m00 -= right.m00;
    m01 -= right.m01;
    m10 -= right.m10;
    m11 -= right.m11;

    return this;
  }

  public Mat2f mul(Mat2f right) {
    float m00_ = m00 * right.m00 + m01 * right.m10;
    float m01_ = m00 * right.m01 + m01 * right.m11;
    
    float m10_ = m10 * right.m00 + m11 * right.m10;
    float m11_ = m10 * right.m01 + m11 * right.m11;

    m00 = m00_;
    m01 = m01_;
    m10 = m10_;
    m11 = m11_;

    return this;
  }
  
  public Mat2f clone(){
    return new Mat2f().set(this);
  }

  /*
   * should go into vec2
   * modifies vec and returns itself
  public Mat2f transform(Vec2f right) {
    float x = m00 * right.x + m10 * right.y;
    float y = m01 * right.x + m11 * right.y;

    right.x = x;
    right.y = y;

    return this;
  }*/
  

  public Mat2f transpose() {
    float m01_ = m10;
    float m10_ = m01;
    m01 = m01_;
    m10 = m10_;

    return this;
  }

  public Mat2f invert() {

    float determinant = this.determinant();
    if (determinant != 0) {

      float determinantInv = 1f / determinant;
      float t00 = m11 * determinantInv;
      float t01 = -m01 * determinantInv;
      float t11 = m00 * determinantInv;
      float t10 = -m10 * determinantInv;

      m00 = t00;
      m01 = t01;
      m10 = t10;
      m11 = t11;
    } else {
      throw new ArithmeticException("division by zero. determinant is zero.");
    }

    return this;
  }

  public String toString() {
    StringBuilder buf = new StringBuilder("|");
    buf.append(m00).append(' ').append(m01).append("|\n|");
    buf.append(m10).append(' ').append(m11).append("|\n");
    return buf.toString();
  }

  public Mat2f negate() {
    m00 = -m00;
    m01 = -m01;
    m10 = -m10;
    m11 = -m11;
    return this;
  }
  
  public boolean equals(Mat2f o){
    return o.m00 == m00 && o.m10 == m10 && o.m01 == m01 && o.m11 == m11;
  };

  public boolean aboutEquals(Mat2f o){
    return aboutEquals(o, 0.0001f);
  }
  
  public boolean aboutEquals(Mat2f o, float delta){
    return Math.abs(o.m00 - m00) < delta && Math.abs(o.m10 - m10) < delta && Math.abs(o.m01 - m01) < delta && Math.abs(o.m11 - m11) < delta;
  };

  public Mat2f setIdentity() {
    m00 = 1.0f;
    m01 = 0.0f;
    m10 = 0.0f;
    m11 = 1.0f;
    return this;
  }

  public Mat2f setZero() {
    m00 = 0.0f;
    m01 = 0.0f;
    m10 = 0.0f;
    m11 = 0.0f;
    return this;
  }

  public float determinant() {
    return m00 * m11 - m01 * m10;
  }

  public Mat2f mul(float q) {
    m00 *= q;
    m01 *= q;
    m10 *= q;
    m11 *= q;
    return this;
  }

}
