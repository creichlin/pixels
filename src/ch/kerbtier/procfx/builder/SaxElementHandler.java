package ch.kerbtier.procfx.builder;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ch.kerbtier.procfx.MiniParsers;
import ch.kerbtier.procfx.NamedParameterInfo;
import ch.kerbtier.procfx.ParameterInfo;
import ch.kerbtier.procfx.ParameterInfos;
import ch.kerbtier.procfx.model.Facade;

public class SaxElementHandler extends DefaultHandler {
  private String path;
  private String group;
  private Facade facade;
  private Stack<Integer> nodeIndex = new Stack<Integer>();

  public SaxElementHandler(Facade facade, String group) {
    this.facade = facade;
    this.group = group;
    nodeIndex.push(0);
  }

  @Override
  public void startElement(String uri, String localName, String tag,
      Attributes attributes) throws SAXException {
    
    if ("procfx".equals(tag)) {
      path = "groups{" + group + "}";
      facade.set(path, new ch.kerbtier.procfx.model.Group());
    } else if ("define".equals(tag)) {
      path += ".elements{" + attributes.getValue("name") + "}";
      
      if("rgb".equals(attributes.getValue("mode"))) {
        facade.set(path, new ch.kerbtier.procfx.model.ElementRGB());
      } else {
        facade.set(path, new ch.kerbtier.procfx.model.ElementMono());
      }
    } else if ("parameter".equals(tag)) {

    } else {
      Object parent = facade.get(path);
      ParameterInfo pi = new ParameterInfos(parent).getParameter(nodeIndex.peek());
      
      Class<?> classDef = Declarations.get(tag, pi.isRGB());
      
      path += "." + pi.name();

      try {
        Object instance = classDef.newInstance();
        facade.set(path, instance);
        facade.set(path + ".group", group);
        
        ParameterInfos npi = new ParameterInfos(instance);
        
        
        for(int c = 0; c < attributes.getLength(); c++) {
          String name = attributes.getQName(c);
          String value = attributes.getValue(c);

          NamedParameterInfo api = npi.getParameter(name);
          
          if(api == null) {
            throw new RuntimeException("no named parameter '" + name + "' for instance '" + instance + "'");
          }
          
          if(api.isString()) {
            facade.set(path + "." + name, value);
          } else if(api.isInteger()) {
            facade.set(path + "." + name, Integer.parseInt(value));
          } else if(api.isFloat()) {
            facade.set(path + "." + name, Float.parseFloat(value));
          } else if(api.isEnum()){
            facade.set(path + "." + name, api.asEnum(value));
          } else if(api.isColor()){
            facade.set(path + "." + name, MiniParsers.color(value));
          } else if(api.isRGBFunction()){
            facade.set(path + "." + name, MiniParsers.rgbFunction(value));
          } else{
            throw new RuntimeException("unknown parameter type '" + name + "' for instance '" + instance + "'");
          }
        }
        
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    
    nodeIndex.push(nodeIndex.pop() + 1);
    nodeIndex.push(0);
  }

  @Override
  public void endElement(String uri, String localName, String tag)
      throws SAXException {
    nodeIndex.pop();
    if ("procfx".equals(tag)) {
      path = null;
    } else if ("define".equals(tag)) {
      path = path.substring(0, path.lastIndexOf("."));
    } else if ("parameter".equals(tag)) {

    } else {
      path = path.substring(0, path.lastIndexOf("."));
    }
  }

  interface ElementContext {
    void add(Object object);

    boolean isNextRGB();

    void end();
  }

}
