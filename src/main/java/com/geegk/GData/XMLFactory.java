package com.geegk.GData;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLFactory {

    public static Map<String,String> getXMLInfor(String fileName){
        Map<String,String> jdbcMap = new HashMap<String, String>();
        File file = new File(fileName);
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(file);
            System.out.println(doc.asXML());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root =doc.getRootElement();
        Element datasource = root.element("datasource");
        Element jdbc = datasource.element("jdbc");
        List<Element> properties = jdbc.elements();
        for (Element e:properties) {
            String key = e.getName();
            String value = e.getText();
            jdbcMap.put(key,value);
        }

        return jdbcMap;
    }
    public static void main(String[] args){
       String fileName = XMLFactory.class.getResource("/datasource.xml").getFile();
       Map<String,String> map =XMLFactory.getXMLInfor(fileName);
        
    }
}
