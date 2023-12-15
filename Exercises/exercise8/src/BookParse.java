import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BookParse {
    // Parses an XML file and returns a Document object
    private static Document parseXML(String filePath){
        try{
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            return dbBuilder.parse(inputFile);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // Prints the XML document content to the console
    private static void printXML(Document doc){
        try{
            doc.getDocumentElement().normalize();
            TransformerFactory tfFactory = TransformerFactory.newInstance();
            Transformer transformer = tfFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new StringWriter());
            transformer.transform(source, result);
            String xmlString = result.getWriter().toString();
            System.out.println(xmlString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Adds a new book entry to the XML document
    private static void addBookXML(Document doc){
        Element newBook = doc.createElement("Book");
        doc.getDocumentElement().appendChild(doc.createTextNode("\n    "));
        newBook.appendChild(doc.createTextNode("\n        "));
        newBook.appendChild(createElement(doc,"title","Gone with the Wind"));
        newBook.appendChild(doc.createTextNode("\n        "));
        newBook.appendChild(createElement(doc,"publishedYear","1936"));
        newBook.appendChild(doc.createTextNode("\n        "));
        newBook.appendChild(createElement(doc,"numberOfPages","1037"));
        newBook.appendChild(doc.createTextNode("\n        "));
        Element authors = doc.createElement("authors");
        authors.appendChild(doc.createTextNode("\n            "));
        authors.appendChild(createElement(doc,"author","Margaret Mitchell"));
        authors.appendChild(doc.createTextNode("\n        "));
        newBook.appendChild(authors);
        newBook.appendChild(doc.createTextNode("\n  "));
        doc.getDocumentElement().appendChild(newBook);
        doc.getDocumentElement().appendChild(doc.createTextNode("\n"));
    }
    // Creates an XML element with text content
    private static Element createElement(Document doc, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    // Parses a JSON file and returns a JSONObject
    private static JSONObject parseJSON(String filePath){
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return new JSONObject(content);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // Prints the JSON object content to the console
    private static void printJSON(JSONObject jsonObeject){
        System.out.println(jsonObeject.toString(4));
    }
    // Adds a new book entry to the JSON object
    private static void addBookJSON(JSONObject jsonObeject){
        JSONArray bookShelf = jsonObeject.getJSONArray("BookShelf");
        JSONObject newBook = new JSONObject();
        newBook.put("title","Gone with the Wind");
        newBook.put("publishedYear","1936");
        newBook.put("numberOfPages","1037");
        JSONArray authors = new JSONArray();
        authors.put("Margaret Mitchell");
        newBook.put("authors",authors);
        bookShelf.put(newBook);
    }
    public static void main(String[] args){
        Document xmlDocument = parseXML("D:\\java_work\\INFO5100_002241485_NaDi\\Exercises\\exercise8\\src\\book.xml");
        printXML(xmlDocument);

        JSONObject jsonObject = parseJSON("D:\\java_work\\INFO5100_002241485_NaDi\\Exercises\\exercise8\\src\\book.json");
        printJSON(jsonObject);

        System.out.println("Adding a new book");
        addBookXML(xmlDocument);
        addBookJSON(jsonObject);

        System.out.println("new book added");
        printXML((xmlDocument));
        printJSON(jsonObject);
    }
}
