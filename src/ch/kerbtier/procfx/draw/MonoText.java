package ch.kerbtier.procfx.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.core.MonoCanvas;
import ch.kerbtier.procfx.core.MonoModify;

public class MonoText extends MonoModify {

  private String ttf;
  private float size = 12.0f;
  private int x;
  private int y;
  private String text;
  private ColorType fillColor = null;

  @Override
  public void apply(MonoCanvas target) {
    int width = target.width();
    int height = target.height();
    
    float[] mono = target.mono();

    BufferedImage img = new BufferedImage(width, height,
        BufferedImage.TYPE_BYTE_GRAY);
    Graphics2D g = (Graphics2D)img.getGraphics();
    
    Font font = null;
    
    if(ttf != null) {
      try {
        font = Font.createFont(Font.TRUETYPE_FONT, new File(ttf));
      } catch (FontFormatException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    font = font.deriveFont(size);
    
    g.setFont(font);
    
    if(fillColor != null) {
      g.setColor(new Color(1.0f, 1.0f, 1.0f));
      g.drawString(text, x, y);
    }
    
    WritableRaster r = img.getRaster();
    
    float[] iData = r.getPixels(0, 0, width, height, (float[])null);
    
    for(int c = 0; c < iData.length; c++) {
      float v = iData[c] / 255;
      if(v > 0.5) {
        mono[c] = fillColor.r;
      }
    }
  }
}
