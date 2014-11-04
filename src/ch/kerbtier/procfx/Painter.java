package ch.kerbtier.procfx;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.RgbCanvas;

public class Painter {
  
  public static void paint(Canvas canvas, String filename) {
    if (canvas instanceof MonoCanvas) {
      paint((MonoCanvas) canvas, filename);
    } else {
      paint((RgbCanvas) canvas, filename);
    }
  }

  public static void paint(Canvas canvas, BufferedImage bufferedImage) {
    if (canvas instanceof MonoCanvas) {
      paint((MonoCanvas) canvas, bufferedImage);
    } else {
      paint((RgbCanvas) canvas, bufferedImage);
    }
  }

  public static void paint(RgbCanvas canvas, String filename) {
    int width = canvas.width();
    int height = canvas.height();

    BufferedImage img = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    paint(canvas, img);
    
    File outputfile = new File(filename + ".png");
    try {
      ImageIO.write(img, "png", outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void paint(RgbCanvas canvas, BufferedImage img) {
    int width = canvas.width();
    int height = canvas.height();
    
    WritableRaster wr = img.getRaster();

    int bands = wr.getNumBands();


    float[] r = canvas.red();
    float[] g = canvas.green();
    float[] b = canvas.blue();

    float[] data = new float[bands * r.length];

    for (int cnt = 0; cnt < r.length; cnt++) {
      int dataIndex = cnt * bands;
      data[dataIndex] = r[cnt] * 255;
      if(bands > 1) {
        data[dataIndex + 1] = g[cnt] * 255;
        data[dataIndex + 2] = b[cnt] * 255;
        if(bands > 3) {
          data[dataIndex + 3] = 255;
        }
      }
    }

    wr.setPixels(0, 0, width, height, data);
  }

  public static void paint(MonoCanvas canvas, String filename) {
    int width = canvas.width();
    int height = canvas.height();

    BufferedImage img = new BufferedImage(width, height,
        BufferedImage.TYPE_BYTE_GRAY);
    
    paint(canvas, img);
    
    File outputfile = new File(filename + ".png");
    try {
      ImageIO.write(img, "png", outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void paint(MonoCanvas canvas, BufferedImage img) {
    int width = canvas.width();
    int height = canvas.height();

    WritableRaster wr = img.getRaster();
    int bands = wr.getNumBands();

    
    float[] mono = canvas.mono();
    
    float[] data = new float[mono.length * bands];
    
    for (int cnt = 0; cnt < mono.length; cnt++) {
      int dataIndex = cnt * bands;
      data[dataIndex] = mono[cnt] * 255;
      if(bands > 1) {
        data[dataIndex + 1] = mono[cnt] * 255;
        data[dataIndex + 2] = mono[cnt] * 255;
        if(bands > 3) {
          data[dataIndex + 3] = 255;
        }
      }
    }

    img.getRaster().setPixels(0, 0, width, height, data);

  }

  public static void paint(RgbCanvas canvas, MonoCanvas canvasAlpha, BufferedImage img) {
    int width = canvas.width();
    int height = canvas.height();

    float[] r = canvas.red();
    float[] g = canvas.green();
    float[] b = canvas.blue();
    float[] a = canvasAlpha.mono();

    float[] data = new float[4 * r.length];

    for (int cnt = 0; cnt < r.length; cnt++) {
      int cnt3 = cnt * 4;
      data[cnt3] = r[cnt] * 255;
      data[cnt3 + 1] = g[cnt] * 255;
      data[cnt3 + 2] = b[cnt] * 255;
      data[cnt3 + 3] = a[cnt] * 255;
    }
    WritableRaster wr = img.getRaster();

    if(wr.getNumBands() != 4) {
      throw new RuntimeException("need 4 bands to write rgb with alpha image");
    }
    
    wr.setPixels(0, 0, width, height, data);
  }

  public static BufferedImage paint(Canvas cv) {
    BufferedImage target = new BufferedImage(cv.width(), cv.height(), BufferedImage.TYPE_INT_ARGB);
    paint(cv, target);
    return target;
  }

  public static BufferedImage paint(RgbCanvas rgb, MonoCanvas mono) {
    BufferedImage target = new BufferedImage(rgb.width(), rgb.height(), BufferedImage.TYPE_INT_ARGB);
    paint(rgb, mono, target);
    return target;
  }

}
