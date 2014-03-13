package ch.kerbtier.procfx.gui.filter.producers;

import ch.kerbtier.procfx.gui.filter.FilterView;
import ch.kerbtier.procfx.gui.filter.View;
import ch.kerbtier.procfx.gui.widgets.IntSliderRow;
import ch.kerbtier.procfx.gui.widgets.ObjectRow;

public class VoronoiBorderGradient extends FilterView {

  private IntSliderRow scale = new IntSliderRow("scale", this, 1, 128);
  private ObjectRow source2 = new ObjectRow("source2", this);

  
  public VoronoiBorderGradient(String path) {
    super("Voronoi Gradient", path);
    getRows().add(source2);
    getRows().add(scale);
  }

  public void setScale(Integer scale) {
    this.scale.setValue(scale);
  }
  
  public void setSource2(Object o) {
    source2.setView(o);
  }
  
  public View getSource2() {
    return source2.getView();
  }
}
