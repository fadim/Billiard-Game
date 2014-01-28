
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Genetic
 */
public class StaxParser {
  
  static final String BALL= "ball";
  static final String NO= "no";
  static final String MASS = "mass";
  static final String RADIUS = "radius";
  static final String TABLE= "table";
  static final String NUMBEROFPOCKET = "numberOfPocket";
  static final String WIDTH = "width";
  static final String HEIGHT = "height";
  static final String FRICTIONCOEFFICIENT = "frictionCoefficient";

  public List<ReadXMLFile> readConfig(String configFile) {
    List<ReadXMLFile> items = new ArrayList<ReadXMLFile>();
    try {
      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = new FileInputStream(configFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // Read the XML document
      ReadXMLFile item = null;

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          
          // For ball informations
          if (startElement.getName().getLocalPart() == (BALL)) {
            item = new ReadXMLFile();
            Iterator<Attribute> attributes = startElement.getAttributes();
            while (attributes.hasNext()) {
              Attribute attribute = attributes.next();
              if (attribute.getName().toString().equals(NO)) {
                item.setBallNo(Integer.parseInt(attribute.getValue()));
              }

            }
          }
                

          if (event.isStartElement()) {
            if (event.asStartElement().getName().getLocalPart()
                .equals(MASS)) {
              event = eventReader.nextEvent();
              item.setMass(Integer.parseInt(event.asCharacters().getData()));
              continue;
            }
          }
          if (event.asStartElement().getName().getLocalPart()
              .equals(RADIUS)) {
            event = eventReader.nextEvent();
            item.setRadius(Integer.parseInt(event.asCharacters().getData()));
            continue;
          }

         
          //For table informations
          
          if (startElement.getName().getLocalPart() == (TABLE)) {
             item = new ReadXMLFile();
          }
          
          if (event.asStartElement().getName().getLocalPart()
              .equals(NUMBEROFPOCKET)) {
            event = eventReader.nextEvent();
            item.setNumberOfPocket(Integer.parseInt(event.asCharacters().getData()));
            continue;
          }
          if (event.asStartElement().getName().getLocalPart()
              .equals(WIDTH)) {
            event = eventReader.nextEvent();
            item.setWidth(Integer.parseInt(event.asCharacters().getData()));
            continue;
          }
           if (event.asStartElement().getName().getLocalPart()
              .equals(HEIGHT)) {
            event = eventReader.nextEvent();
            item.setHeight(Integer.parseInt(event.asCharacters().getData()));
            continue;
          }
            if (event.asStartElement().getName().getLocalPart()
              .equals(FRICTIONCOEFFICIENT)) {
            event = eventReader.nextEvent();
            item.setFrictionCoefficient(Integer.parseInt(event.asCharacters().getData()));
            continue;
          }
          
        }
        // If we reach the end of an item element we add it to the list
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart() == (BALL)) {
            items.add(item);
          }
        }
        
         if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart() == (TABLE)) {
            items.add(item);
          }
        }
         

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    return items;
  }

    
}
