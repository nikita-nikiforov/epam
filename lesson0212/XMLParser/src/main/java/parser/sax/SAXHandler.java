package parser.sax;

import model.Candy;
import model.Ingredient;
import model.Value;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
    private List<Candy> candyList = new ArrayList<>();
    private Candy candy = null;
    private List<Ingredient> ingredients = null;
    private Value value = null;

    private boolean bName = false;
    private boolean bEnergy = false;
    private boolean bType = false;
    private boolean bIngreds = false;
    private boolean bIngred = false;
    private boolean bValue = false;
    private boolean bProteins = false;
    private boolean bFat = false;
    private boolean bCarbo = false;
    private boolean bManuf = false;

    public List<Candy> getCandyList(){
        return candyList;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if(qName.equalsIgnoreCase("candy")){
            candy = new Candy();
        } else if(qName.equalsIgnoreCase("name")){bName = true;}
        else if(qName.equalsIgnoreCase("energy")){bEnergy = true;}
        else if(qName.equalsIgnoreCase("type")){bType = true;}
        else if(qName.equalsIgnoreCase("ingredients")){bIngreds = true;}
        else if(qName.equalsIgnoreCase("ingredient")){bIngred = true;}
        else if(qName.equalsIgnoreCase("value")){bValue = true;}
        else if(qName.equalsIgnoreCase("proteis")){bProteins = true;}
        else if(qName.equalsIgnoreCase("fat")){bFat = true;}
        else if(qName.equalsIgnoreCase("carbohydrates")){bCarbo = true;}
        else if(qName.equalsIgnoreCase("manufacturer")){bManuf = true;}
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("candy")){
            candyList.add(candy);
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException{
        if(bName){
            candy.setName(new String(ch, start, length));
            bName = false;
        } else if(bEnergy){
            candy.setEnergy(Integer.parseInt(new String(ch, start, length)));
            bEnergy = false;
        } else if(bType){
            candy.setType(new String(ch, start, length));
            bType = false;
        } else if(bIngreds){
            ingredients = new ArrayList<>();
            bIngreds = false;
        } else if(bIngred){
            Ingredient ingredient = new Ingredient();
            ingredient.setName(new String(ch, start, length));
            ingredients.add(ingredient);
            bIngred = false;
        } else if(bValue){
            value = new Value();
            bValue = false;
        } else if(bProteins){
            value.setProteins(Integer.parseInt(new String(ch, start, length)));
            bProteins = false;
        } else if(bFat){
            value.setFat(Integer.parseInt(new String(ch, start, length)));
            bFat = false;
        } else if(bCarbo){
            value.setCarbohydrates(Integer.parseInt(new String(ch, start, length)));
            bCarbo = false;
        } else if(bManuf){
            candy.setManufacturer(new String(ch, start, length));
            candy.setIngredients(ingredients);
            candy.setValue(value);
            bManuf = false;
        }
    }
}
