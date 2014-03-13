package ch.kerbtier.procfx.gui.filter.producers;

import java.text.DecimalFormat;

import org.apache.pivot.wtk.Label;

import ch.kerbtier.procfx.ColorType;
import ch.kerbtier.procfx.gui.GUI;
import ch.kerbtier.procfx.gui.filter.ProducerView;
import ch.kerbtier.procfx.gui.widgets.GreyScaleColorChooser;
import ch.kerbtier.procfx.gui.widgets.GreyScaleColorChooserListener;

public class MonoColor extends ProducerView {

  private Row color = new Row();
  private GreyScaleColorChooser colorWidget = new GreyScaleColorChooser();
  
  public MonoColor(String path) {
    super("Color", path);
    
    color.add(new Label("color"));
    color.add(colorWidget);
    
    getRows().add(color);
    
    
    colorWidget.getGreyScaleColorChooserListeners().add(new GreyScaleColorChooserListener() {
      
      @Override
      public void valueChanged(GreyScaleColorChooser chooser, float oldValue) {
        float c = chooser.getValue();
        GUI.getFacade().set(getPath() + ".color", new ColorType(c, c, c));
        
      }
    });
    
  }
  
  public void setColor(ColorType color) {
    colorWidget.setValue(color.r);
  }
  
  public String createName() {
    return super.createName() + " " + new DecimalFormat("0.00").format(colorWidget.getValue());
  }

}
