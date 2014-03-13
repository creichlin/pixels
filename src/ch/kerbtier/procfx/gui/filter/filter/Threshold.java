package ch.kerbtier.procfx.gui.filter.filter;

import java.text.DecimalFormat;

import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Slider;
import org.apache.pivot.wtk.SliderValueListener;

import ch.kerbtier.procfx.gui.GUI;
import ch.kerbtier.procfx.gui.filter.FilterView;

public class Threshold extends FilterView {

  private Row value = new Row();
  private Slider valueSlider = new Slider();
  
  public Threshold(String path) {
    super("Threshold", path);
    
    value.add(new Label("value"));
    valueSlider.setEnd(1024);
    value.add(valueSlider);
    
    
    
    getRows().add(value);
    
    
    valueSlider.getSliderValueListeners().add(new SliderValueListener() {
      @Override
      public void valueChanged(Slider slider, int previousValue) {
        GUI.getFacade().set(getPath() + ".value", valueSlider.getValue() / 1024f);
      }
    });
    
  }
  
  public void setValue(Float v) {
    valueSlider.setValue((int)(v * 1024));
  }
  
  public String createName() {
    return super.createName() + " (" + new DecimalFormat("0.00").format(valueSlider.getValue() / 1024f) + ")";
  }
  
  
}
