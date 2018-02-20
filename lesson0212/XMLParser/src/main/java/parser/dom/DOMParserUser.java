package parser.dom;

import model.Candy;
import org.w3c.dom.Document;

import java.io.File;
import java.util.List;

public class DOMParserUser {
    public static List<Candy> parseCandies(File xml, File xsd) {
        DOMDocCreator creator = new DOMDocCreator(xml);
        Document doc = creator.getDocument();
        DOMDocReader domDocReader = new DOMDocReader();
        return domDocReader.readDoc(doc);
    }
}
