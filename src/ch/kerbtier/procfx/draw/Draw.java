package ch.kerbtier.procfx.draw;

import java.util.ArrayList;
import java.util.List;

import ch.kerbtier.procfx.Param;
import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import ch.kerbtier.procfx.core.MonoModify;


public class Draw extends DefaultMonoCanvasOperation {
  @Param(1)
  protected List<MonoModify> operations = new ArrayList<MonoModify>();

  @Override
  public void calculate() {
    super.calculate();
    
    for(MonoModify po: operations) {
      po.apply(this);
    }
  }
}
