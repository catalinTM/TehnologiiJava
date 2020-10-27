/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.ws.rs.client.Entity.xml;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author catal
 */
public class WordBean {

    private String language, name, definition;

    public String getLanguage() {
        return language;
    }

    public WordBean(String language, String name, String definition) {
        this.language = language;
        this.name = name;
        this.definition = definition;
        //this.checkDuplicate(this.name);
        this.insertIntoXML();
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void insertIntoXML() {
        try {
            //creating a constructor of file class and parsing an XML file
            File file = new File("C:\\Users\\catal\\Documents\\NetBeansProjects\\Laborator2\\src\\main\\webapp\\languages.xml");
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            try {
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();

                Element dataTag = doc.getDocumentElement();
                Element dictionaryTag = (Element) dataTag.getElementsByTagName("dictionary").item(0);

                Element newEntry = doc.createElement("entry");

                Element languageTag = doc.createElement("language");
                languageTag.setTextContent(this.language);

                Element word = doc.createElement("word");
                word.setTextContent(this.name);

                Element definitionTag = doc.createElement("description");
                definitionTag.setTextContent(this.definition);

                newEntry.appendChild(languageTag);
                newEntry.appendChild(word);
                newEntry.appendChild(definitionTag);

                dictionaryTag.appendChild(newEntry);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult console = new StreamResult(System.out);//for Console print
                transformer.transform(source, console);
                StreamResult newFile = new StreamResult(new File("C:\\Users\\catal\\Documents\\NetBeansProjects\\Laborator2\\src\\main\\webapp\\languages.xml"));
                transformer.transform(source, newFile);
            } catch (SAXException | IOException | TransformerException ex) {
                Logger.getLogger(WordBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WordBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkDuplicate(String word) {
        File file = new File("C:\\Users\\catal\\Documents\\NetBeansProjects\\Laborator2\\src\\main\\webapp\\languages.xml");

        try {
            String fileName = "C:\\Users\\catal\\Documents\\NetBeansProjects\\Laborator2\\src\\main\\webapp\\languages.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Element element = (Element) xPath.compile("//property[@name=\"entry\"]").evaluate(doc, XPathConstants.NODE);
            String value = element.getAttribute("value");
            System.out.println(value);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Logger.getLogger(WordBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {
            String fileName = "C:\\Users\\catal\\Documents\\NetBeansProjects\\Laborator2\\src\\main\\webapp\\languages.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            Element dataTag = doc.getDocumentElement();
            Element dictionaryTag = (Element) dataTag.getElementsByTagName("dictionary").item(0);
            System.out.println(dictionaryTag.getChildNodes().item(0));
            
        } catch (ParserConfigurationException | SAXException | IOException  ex) {
            Logger.getLogger(WordBean.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
