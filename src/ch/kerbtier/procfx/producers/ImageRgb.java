package ch.kerbtier.procfx.producers;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.kerbtier.procfx.core.DefaultRgbCanvasProducer;

public class ImageRgb extends DefaultRgbCanvasProducer {
  private String file;
  private BufferedImage bi = null;

  public void loadImage() {
    if (bi == null) {
      try {
        bi = ImageIO.read(new File(file));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void calculate() {
    super.calculate();
    loadImage();

    Raster r = bi.getData();

    int width = width();
    int height = height();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        red[y * width + x] = r.getSampleFloat(x, y, 0) / 255.0f;
        green[y * width + x] = r.getSampleFloat(x, y, 1) / 255.0f;
        blue[y * width + x] = r.getSampleFloat(x, y, 2) / 255.0f;
      }
    }
  }

  @Override
  public int width() {
    loadImage();
    return bi.getWidth();
  }

  @Override
  public int height() {
    loadImage();
    return bi.getHeight();
  }
}
