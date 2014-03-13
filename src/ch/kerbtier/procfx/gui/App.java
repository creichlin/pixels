package ch.kerbtier.procfx.gui;

import java.io.File;
import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;

import ch.kerbtier.procfx.builder.BuildThem;
import ch.kerbtier.procfx.model.Facade;
import ch.kerbtier.procfx.model.Listener;


public class App implements Application {
  private MainWindow window;
  
  public static void main(String[] args) {
    // DesktopApplicationContext.applyStylesheet("/ch/kerbtier/trader/pivot/styles.json");
    DesktopApplicationContext.main(App.class, args);
  }

  @Override
  public void startup(Display display, Map<String, String> properties) {
    BXMLSerializer bxmlSerializer = new BXMLSerializer();
    try {
      window = (MainWindow)bxmlSerializer.readObject(App.class, "window.xml");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SerializationException e) {
      e.printStackTrace();
    }
    
    window.open(display);
    

    BuildThem bt = new BuildThem(GUI.getFacade());
    
    bt.parse(new File("procfx/brabra.xml"), "brabra");

  }
  
  

  @Override
  public boolean shutdown(boolean optional) {
    if (window != null) {
      window.close();
    }
    
    return false;
  }

  @Override
  public void suspend() {
  }

  @Override
  public void resume() {
  }
}
