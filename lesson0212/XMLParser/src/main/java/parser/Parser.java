package parser;

import model.Candy;
import parser.sax.SAXParserUser;
import java.io.File;
import java.util.List;

public class Parser {

    public static void main(String[] args) {
        File xml = new File("src/main/resources/xml/candies.xml");
        File xsd = new File("src/main/resources/xml/candies.xsd");

        List<Candy> candies = SAXParserUser.parseCandies(xml, xsd);
        for(Candy candy : candies){
            System.out.println(candy);
        }
    }
}
