package ch.kerbtier.procfx.gui.widgets;

import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Slider;
import org.apache.pivot.wtk.SliderValueListener;
import org.apache.pivot.wtk.TablePane.Row;

import ch.kerbtier.procfx.gui.GUI;
import ch.kerbtier.procfx.gui.filter.View;

public class FloatSliderRow extends Row {
  private Slider slider = new Slider();
  private float factor;

  public FloatSliderRow(final String name, final View view, float min, float max, float step) {
    this(name, view, min, max, step, name);
  }
  
  public FloatSliderRow(final String name, final View view, float min, float max, float step, String label) {
    this.add(new Label(label));
    this.add(slider);
    
    factor = 1/step;
    
    slider.setStart((int)(min * factor));
    slider.setEnd((int)(max * factor));
    
    slider.getSliderValueListeners().add(new SliderValueListener() {
      @Override
      public void valueChanged(Slider slider, int previousValue) {
        GUI.getFacade().set(view.getPath() + "." + name, slider.getValue() / factor);
      }
    });
  }

  public void setValue(float f) {
    slider.setValue((int)(f * factor));
  }

  public float getValue() {
    return slider.getValue() / factor;
  }
}
