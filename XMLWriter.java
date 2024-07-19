
/**Java Program to Write XML Using DOM Parser 
 * Method additions to be added as needed
 * 
 * note xml formating restirctions and abide
 * use the commit method to save changes
 */
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.transform.Transformer; 
import javax.xml.transform.TransformerFactory; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element;
import org.w3c.dom.NodeList; 

public class XMLWriter { 
        
    // Create a DocumentBuilder 
    private DocumentBuilderFactory factory; 
    private DocumentBuilder builder;
    
    // Parse the XML file 
    Document document;
    
    private String filepath; 
    private File xmlFile; // Specify the file path as a File object
    /**constuctor for writer object, 
     * @parm- file name
     */
    public XMLWriter(String fileName){
        this.filepath = System.getProperty("user.dir") + "/XML/" + fileName;
        this.xmlFile = new File(this.filepath);
        buildDocReader();
    }

    //initilize fields for document parsing and handling = assist the constructor
    private void buildDocReader(){
        try{
            factory = DocumentBuilderFactory.newInstance(); 
            builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile); // Parse the XML file
        }
        catch(Exception e){
            System.out.println("INITILIZE DOC READER ERROR: POTENTIAL FILE PATH MISSMATCH(REFRENCE FILE) LN40 XMLwriter" + e);
        }
    }

    /**create a new root element, only useful when creating a new file or file is empty - see xml formating 
     * @return root element created
     */
    public Element createNewRootElement(String rootElementName){
        Element root = document.createElement(rootElementName);
        document.appendChild(root);
        return root;
    }
    /**creates a child element of a any parent
     * @param parent to create child of
     * @param elmentName of the child
     * @return the child element
     */
    public Element createNewChildElement(Element parent, String elementName){
        Element child = document.createElement(elementName);
        parent.appendChild(child);
        return child;
    }
    /**creates a text subelement 
     * @param parent element of the new text element
     * @param elementName the new element name of the text element
     * @parm text the text for the specified new element
     * @return the text elment itself
    */
    public Element createTextElement(Element parent, String elementName, String text){
        Element textElement = createNewChildElement(parent, elementName);
        textElement.appendChild(document.createTextNode(text));

        return textElement;
    }
    
    public ArrayList<String> getNodeList(Element element){
        
        ArrayList<String> nodes = new ArrayList<String>();
        NodeList nodeList = document.getElementsByTagName(element.getNodeName());
        
        for(int i = 0; i < nodeList.getLength(); i++){
            nodes.add(nodeList.item(i).toString());
        }

        return nodes;
    }

    public String getChildNodes(Element element){
        return element.getChildNodes().toString();
    }
    
    public String getNodeContent(Element element){
        return element.getTextContent();
    }

    public void removeTextElement(Element parentElement, String elementName){
        parentElement.removeAttribute(elementName);
    }
    
    public void changeNodeText(Element textElement, String newText){
        textElement.setNodeValue(newText);
    }
    
    public Element getRootElement(){
        return document.getDocumentElement();
    }
        // Write to XML file 
    public void commitChanges(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
            Transformer transformer = transformerFactory.newTransformer(); 
            DOMSource source = new DOMSource(document); 
  
            // Specify your local file path 
            StreamResult result = new StreamResult(filepath);
            transformer.transform(source, result); 
        }
        catch(Exception e){
            System.out.println(e + "transformer xml writier exception");
        }
    }

} 
 