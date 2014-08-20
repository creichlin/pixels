package ch.kerbtier.procfx.producers;

import java.util.Arrays;
import java.util.Random;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasProducer;
import ch.kerbtier.procfx.core.MonoCanvas;
import static ch.kerbtier.procfx.Coords.*;

public class DiamondSquareMono extends DefaultMonoCanvasProducer {

  @Param(0)
  protected MonoCanvas source;
  @Param(1)
  protected MonoCanvas factorMap;

  private int seed = 0;
  private int factor = -1;

  private float randomFactor = 0.7f;
  private float randomSummand = 0.02f;
  private float randomStart = 1;

  private Random r;

  private int _width;
  private int _height;

  public int width() {
    if (factor != -1) {
      return factor * source.width();
    }
    return super.width();
  }

  public int height() {
    if (factor != -1) {
      return factor * source.height();
    }
    return super.height();
  }
  
  public void reset() {
    super.reset();
    if(source != null) {
      source.reset();
    }
    if(factorMap != null) {
      factorMap.reset();
    }
  }
  
  public void calculate(boolean force) {
    if(source != null) {
      source.calculate(true);
    }
    if(factorMap != null) {
      factorMap.calculate(true);
    }
    super.calculate(true);
  } 

  public void calculate() {
    super.calculate();

    _width = width();
    _height = height();

    r = new Random(seed);
    if (source == null) {
      if ((_width & (_width - 1)) != 0) {
        throw new RuntimeException("width must be power of two");
      }
      if ((_height & (_height - 1)) != 0) {
        throw new RuntimeException("height must be power of two");
      }
      Arrays.fill(mono, -1);
      mono[0] = 0.5f;
      paint(_width);
    } else {
      int factor = width() / source.width();

      if ((factor & (factor - 1)) != 0) {
        throw new RuntimeException("factor must be power of two");
      }

      int sWidth = source.width();
      int sHeight = source.height();

      for (int y = 0; y < sHeight; y++) {
        for (int x = 0; x < sWidth; x++) {
          mono[(y * factor) * _width + (x * factor)] = source.mono()[y * sWidth
              + x];
        }
      }

      paint(factor);
    }
  }

  /*
   * The color values are positioned like: c1 c2 c3 +-----+-----+ | | | |c4 |c5
   * | +-----+-----+ | | | |c7 | |c9 +-----+-----+
   */
  private void paint(int sideLength) {

    float fac = randomStart;
    while (sideLength >= 2) {

      for (int py = 0; py < _height; py += sideLength) {
        for (int px = 0; px < _width; px += sideLength) {
          // fetch corner colors
          float c1 = mono[repeat(px, py, _width, _height)];
          float c3 = mono[repeat(px + sideLength, py, _width, _height)];
          float c7 = mono[repeat(px, py + sideLength, _width, _height)];
          float c9 = mono[repeat(px + sideLength, py + sideLength, _width,
              _height)];

          int shortX = px / sideLength; 
          int shortY = py / sideLength; 
          
          
          float c2 = (c1 + c3) / 2 + calcFac(fac, shortX, shortY);
          float c4 = (c1 + c7) / 2 + calcFac(fac, shortX, shortY);
          float c5 = (c1 + c3 + c7 + c9) / 4 + calcFac(fac, shortX, shortY);

          mono[repeat(px + sideLength / 2, py, _width, _height)] = c2;
          mono[repeat(px, py + sideLength / 2, _width, _height)] = c4;
          mono[repeat(px + sideLength / 2, py + sideLength / 2, _width, _height)] = c5;
        }
      }

      fac = fac * randomFactor + randomSummand;

      sideLength /= 2;
    }
  }
  
  private float calcFac(float fac, int sx, int sy) {
    
    float mapFac = 1;
    
    if(factorMap != null) {
      mapFac = factorMap.mono()[repeat(sx, sy, factorMap.width(), factorMap.height())];
    }
    
    return (r.nextFloat() * fac - fac / 2f) * mapFac;
  }
}
