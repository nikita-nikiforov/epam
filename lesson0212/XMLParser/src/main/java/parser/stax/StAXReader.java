package parser.stax;

import model.Candy;
import model.Ingredient;
import model.Value;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StAXReader {
    public static List<Candy> parseCandies(File xml, File xsd) {
        List<Candy> candyList = new ArrayList<>();
        Candy candy = null;
        Value value = null;
        List<Ingredient> ingredients = null;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory
                    .createXMLEventReader(new FileInputStream(xml));

            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String name = startElement.getName().getLocalPart();
                    switch (name) {
                        case "candy":
                            candy = new Candy();
                            break;
                        case "name":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert candy != null;
                            candy.setName(xmlEvent.asCharacters().getData());
                            break;
                        case "energy":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert candy != null;
                            candy.setEnergy(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            break;
                        case "type":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert candy != null;
                            candy.setType(xmlEvent.asCharacters().getData());
                            break;
                        case "ingredients":
                            xmlEvent = xmlEventReader.nextEvent();
                            ingredients = new ArrayList<>();
                            break;
                        case "ingredient":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert ingredients != null;
                            ingredients.add(new Ingredient(xmlEvent.asCharacters().getData()));
                            break;
                        case "value":
                            xmlEvent = xmlEventReader.nextEvent();
                            value = new Value();
                            break;
                        case "proteins":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert value != null;
                            value.setProteins(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            break;
                        case "fat":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert value != null;
                            value.setFat(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            break;
                        case "carbohydrates":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert value != null;
                            value.setCarbohydrates(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            break;
                        case "manufacturer":
                            xmlEvent = xmlEventReader.nextEvent();
                            assert candy != null;
                            candy.setManufacturer(xmlEvent.asCharacters().getData());
                            break;
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("candy")) {
                        candyList.add(candy);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return candyList;
    }
}