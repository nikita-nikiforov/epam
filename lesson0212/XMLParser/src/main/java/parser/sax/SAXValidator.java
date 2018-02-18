package parser.sax;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class SAXValidator {
    public static Schema createSchema(File xsd){
        Schema schema = null;
        try{
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            schema = schemaFactory.newSchema(xsd);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return schema;
    }
}
