package ch.kerbtier.procfx.builder;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import ch.kerbtier.procfx.model.Facade;

public class BuildThem {

  private Facade facade;
  
  public BuildThem(Facade facade) {
    this.facade = facade;
  }
  
  
  public void parse(File file, String group) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      SaxElementHandler handler = new SaxElementHandler(facade, group);

      saxParser.parse(file, handler);
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
