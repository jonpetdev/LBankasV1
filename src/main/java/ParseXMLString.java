import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParseXMLString {


    public Map<String, Float> mapFirst=new HashMap<>();
    public Map<String, Float> mapLast=new HashMap<>();

    public void parseXML(String xmlstring) {
        String xmlRecords = xmlstring;
        ArrayList<String> ccyNotDistinct=new ArrayList<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlRecords));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("CcyAmt");

            for (int i = 1; i < nodes.getLength(); i+=2) {
                Element element = (Element) nodes.item(i);

                NodeList ccy = element.getElementsByTagName("Ccy");
                Element line = (Element) ccy.item(0);
                String ccyString = getCharacterDataFromElement(line);

                NodeList amt = element.getElementsByTagName("Amt");
                line = (Element) amt.item(0);
                float amtFloat = Float.parseFloat(getCharacterDataFromElement(line));
                ccyNotDistinct.add(ccyString);
                if(mapFirst.containsKey(ccyString)){
                    mapLast.put(ccyString,amtFloat);
                }else{
                    mapFirst.put(ccyString,amtFloat);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return null;
    }
}
