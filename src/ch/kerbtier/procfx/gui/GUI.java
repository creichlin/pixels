package ch.kerbtier.procfx.gui;

import ch.kerbtier.procfx.model.Facade;

public class GUI {
  private static Facade facade = new Facade();
  private static ImagePane imagePane;
  
  public static Facade getFacade() {
    return facade;
  }

  public static void setImagePane(ImagePane imagePane) {
    GUI.imagePane = imagePane; 
  }
  
  public static ImagePane getImagePane() {
    return imagePane;
  }
}
