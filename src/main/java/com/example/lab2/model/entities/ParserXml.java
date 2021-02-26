package com.example.lab2.model.entities;

import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ParserXml {
    private static ParserXml parserXml;

    List<Element> elementList = new ArrayList<>();
    public void parseXML(String xml){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);

            NodeList nodeList = document.getElementsByTagName("exchangerate");
            for (int temp = 0; temp < nodeList.getLength(); temp++)
            {
                Node node = nodeList.item(temp);
                System.out.println("");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    if(eElement.hasAttribute("currency") && eElement.hasAttribute("saleRate")){
                        elementList.add(eElement);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public int amountOfCurrencies(){
        return elementList.size();
    }

    public String currentRate(String name){
        AtomicReference<String> saleRate = new AtomicReference<>("");
        elementList.forEach(element -> {
            if (element.hasAttribute("currency")){
                if(element.getAttribute("currency").equals(name)){
                        saleRate.set(element.getAttribute("saleRate"));
                }
            }
        });
        return saleRate.get();
    }

    public static ParserXml parserXml(){
        if(parserXml == null){
            parserXml = new ParserXml();
        }
        return parserXml;
    }
}
