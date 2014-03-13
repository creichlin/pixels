package ch.kerbtier.rouge.geom;

public class Matf {
  
  public static Mat23f mul(Mat2f left, Mat23f right){
    return mul(left, right, new Mat23f());
  }
  
  public static Mat23f mul(Mat2f l, Mat23f r, Mat23f d) {
    
    d.m00 = l.m00 * r.m00 + l.m01 * r.m10;
    d.m01 = l.m00 * r.m01 + l.m01 * r.m11;
    d.m02 = l.m00 * r.m02 + l.m01 * r.m12;
    
    d.m10 = l.m10 * r.m00 + l.m11 * r.m10;
    d.m11 = l.m10 * r.m01 + l.m11 * r.m11;
    d.m12 = l.m10 * r.m02 + l.m11 * r.m12;
    
    
    return d;
  }
}
