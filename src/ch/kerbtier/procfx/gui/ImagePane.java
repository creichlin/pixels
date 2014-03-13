package ch.kerbtier.procfx.gui;

import java.awt.image.BufferedImage;
import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.media.Picture;

import ch.kerbtier.procfx.Painter;
import ch.kerbtier.procfx.core.Canvas;
import ch.kerbtier.procfx.core.RgbCanvas;
import ch.kerbtier.procfx.model.Listener;

public class ImagePane extends ScrollPane implements Bindable, Listener {

  @BXML
  private ImageView image;
  private Canvas selected = null;
  private String selectedPath;

  @Override
  public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
    GUI.setImagePane(this);

    GUI.getFacade().register(this);
  }

  public void select(String path) {
    selectedPath = path;
    selected = (Canvas) GUI.getFacade().get(path);
    update();
  }

  public void update() {
    if (selected != null) {

      selected.calculate();

      int width = selected.width();
      int height = selected.height();

      BufferedImage bi = null;

      if (selected instanceof RgbCanvas) {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      } else {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
      }

      Painter.paint(selected, bi);

      image.setWidth(width);
      image.setHeight(height);

      image.setImage(new Picture(bi));
    }
  }

  @Override
  public void update(String path, Object value) {
    if (selectedPath != null) {
      if (path.startsWith(selectedPath)) {
        update();
      }
    }

  }

}
