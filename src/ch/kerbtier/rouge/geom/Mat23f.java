package ch.kerbtier.rouge.geom;

public class Mat23f {

  public float m00, m01, m10, m11, m02, m12;

  /**
   * Constructor for Matrix2f. The matrix is initialised to zero matrix.
   */
  public Mat23f() {
    setZero();
  }


  public Mat23f(Mat23f src) {
    set(src);
  }
  public Mat23f(float m00, float m01, float m02, float m10, float m11, float m12){
    set(m00, m01, m02, m10, m11, m12);
  }

  public Mat23f(Vec3f v1, Vec3f v2) {
    set(v1, v2);
  }


  private Mat23f set(Vec3f v1, Vec3f v2) {
    m00 = v1.x;
    m01 = v1.y;
    m02 = v1.z;
    m10 = v2.x;
    m11 = v2.y;
    m12 = v2.z;
    return this;
  }


  public Mat23f set(float m00, float m01, float m02, float m10, float m11, float m12){
    this.m00 = m00;
    this.m01 = m01;
    this.m02 = m02;
    this.m10 = m10;
    this.m11 = m11;
    this.m12 = m12;
    return this;
  }

  public Mat23f set(Mat23f src) {
    m00 = src.m00;
    m01 = src.m01;
    m02 = src.m02;
    m10 = src.m10;
    m11 = src.m11;
    m12 = src.m12;

    return this;
  }

  public Mat23f add(Mat23f right) {
    m00 += right.m00;
    m01 += right.m01;
    m02 += right.m02;
    m10 += right.m10;
    m11 += right.m11;
    m12 += right.m12;

    return this;
  }

  public Mat23f sub(Mat23f right) {
    m00 -= right.m00;
    m01 -= right.m01;
    m02 -= right.m02;
    m10 -= right.m10;
    m11 -= right.m11;
    m12 -= right.m12;

    return this;
  }

  public String toString() {
    StringBuilder buf = new StringBuilder("|");
    buf.append(m00).append(' ').append(m01).append(' ').append(m02).append("|\n|");
    buf.append(m10).append(' ').append(m11).append(' ').append(m12).append("|\n");
    return buf.toString();
  }

  public Mat23f negate() {
    m00 = -m00;
    m01 = -m01;
    m02 = -m02;
    m10 = -m10;
    m11 = -m11;
    m12 = -m12;
    return this;
  }

  public Mat23f setZero() {
    m00 = 0.0f;
    m10 = 0.0f;
    m01 = 0.0f;
    m11 = 0.0f;
    m02 = 0.0f;
    m12 = 0.0f;
    return this;
  }
  
  public boolean equals(Mat23f o){
    return o.m00 == m00 && o.m01 == m01 && o.m02 == m02 && o.m10 == m10 && o.m11 == m11 && o.m12 == m12;
  }


  public Mat23f mul(float q) {
    m00 *= q;
    m01 *= q;
    m02 *= q;
    m10 *= q;
    m11 *= q;
    m12 *= q;
    return this;
  }


  public Vec3f getRow(int i) {
    if(i == 0) {
      return new Vec3f(m00, m01, m02);
    }else if(i == 1) {
      return new Vec3f(m10, m11, m12);
    }
    throw new ArithmeticException("matrix has 2 rows, cannot get row index " + i);
  };

}
