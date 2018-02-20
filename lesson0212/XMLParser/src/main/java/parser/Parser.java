package parser;

import model.Candy;
import parser.dom.DOMParserUser;
import parser.sax.SAXParserUser;
import java.io.File;
import java.util.List;

public class Parser {

    public static void main(String[] args) {
        File xml = new File("src/main/resources/xml/candies.xml");
        File xsd = new File("src/main/resources/xml/candies.xsd");

        System.out.println("SAX");
        List<Candy> candies = SAXParserUser.parseCandies(xml, xsd);
        for(Candy candy : candies){
            System.out.println(candy);
        }

        System.out.println("DOM");
        List<Candy> candiesDom = DOMParserUser.parseCandies(xml, xsd);
        for(Candy candy : candiesDom){
            System.out.println(candy);
        }
    }
}
