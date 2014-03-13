package ch.kerbtier.procfx.gui.widgets;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.apache.pivot.wtk.Bounds;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Container;
import org.apache.pivot.wtk.ContainerMouseListener;
import org.apache.pivot.wtk.Panel;
import org.apache.pivot.wtk.Mouse.Button;
import org.apache.pivot.wtk.Mouse.ScrollType;
import org.apache.pivot.wtk.WTKListenerList;
import org.apache.pivot.wtk.effects.Decorator;

public class GreyScaleColorChooser extends Panel {

  private static class GreyScaleColorChooserList extends
      WTKListenerList<GreyScaleColorChooserListener> implements
      GreyScaleColorChooserListener {

    @Override
    public void valueChanged(GreyScaleColorChooser chooser, float oldValue) {
      for (GreyScaleColorChooserListener listener : this) {
        listener.valueChanged(chooser, oldValue);
      }
    }
  }

  private GreyScaleColorChooserList greyScaleColorChooserListeners = new GreyScaleColorChooserList();

  private float value = 0;

  public GreyScaleColorChooser() {
    getDecorators().add(new GrayScaleDecorator());
    setMinimumHeight(10);
    setMinimumWidth(100);

    getContainerMouseListeners().add(new ContainerMouseListener.Adapter() {
      @Override
      public boolean mouseDown(Container container, Button button, int x, int y) {
        setValue(((float) x - 3) / (getWidth() - 6), true);
        return true;
      }

      @Override
      public boolean mouseWheel(Container container, ScrollType scrollType,
          int scrollAmount, int wheelRotation, int x, int y) {
        setValue(value + (float) (wheelRotation / 255.0), true);
        return true;
      }

    });
  }

  public void setValue(float value) {
    setValue(value, false);
  }
  
  public float getValue() {
    return value;
  }
  
  private void setValue(float value, boolean trigger) {
    float oldValue = this.value;
    value = Math.max(value, 0);
    value = Math.min(value, 1);

    this.value = value;
    if (trigger) {
      greyScaleColorChooserListeners.valueChanged(this, oldValue);
    }
    repaint();
  }

  public WTKListenerList<GreyScaleColorChooserListener> getGreyScaleColorChooserListeners() {
    return greyScaleColorChooserListeners;
  }

  class GrayScaleDecorator implements Decorator {
    @Override
    public Graphics2D prepare(Component component, Graphics2D g2d) {
      int width = component.getWidth();
      int height = component.getHeight();

      GradientPaint w2b = new GradientPaint(3, 0, Color.WHITE, width - 3, 0,
          Color.BLACK);
      g2d.setPaint(w2b);
      g2d.fill(new Rectangle2D.Float(0, 0, width, height));

      g2d.setColor(Color.RED);

      int x = (int) ((width - 6) * value) + 3;

      g2d.drawLine(x, 0, x, 3);
      g2d.drawLine(x, height - 4, x, height);

      return g2d;
    }

    @Override
    public void update() {

    }

    @Override
    public Bounds getBounds(Component component) {
      return new Bounds(0, 0, component.getWidth(), component.getHeight());
    }

    @Override
    public AffineTransform getTransform(Component component) {
      return new AffineTransform();
    }
  }
}
