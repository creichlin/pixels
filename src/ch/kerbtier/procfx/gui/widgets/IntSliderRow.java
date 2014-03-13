package ch.kerbtier.procfx.gui.widgets;

import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Slider;
import org.apache.pivot.wtk.SliderValueListener;
import org.apache.pivot.wtk.TablePane.Row;

import ch.kerbtier.procfx.gui.GUI;
import ch.kerbtier.procfx.gui.filter.View;

public class IntSliderRow extends Row {
  private View view;
  private String name;
  private String label;
  private Slider slider = new Slider();
  

  public IntSliderRow(final String name, final View view, int min, int max) {
    this(name, view, min, max, name);
  }
  
  public IntSliderRow(final String name, final View view, int min, int max, String label) {
    this.name = name;
    this.view = view;
    
    this.add(new Label(label));
    this.add(slider);
    
    slider.setStart(min);
    slider.setEnd(max);
    
    slider.getSliderValueListeners().add(new SliderValueListener() {
      @Override
      public void valueChanged(Slider slider, int previousValue) {
        GUI.getFacade().set(view.getPath() + "." + name, slider.getValue());
      }
    });
  }

  public void setValue(int w) {
    slider.setValue(w);
  }

  public int getValue() {
    return slider.getValue();
  }
}
