/**
 * Java Program to Read XML Using DOM Parser 
 * Method additions to be added as needed
 */
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList;
import org.w3c.dom.Node; 
import java.io.File; 
  
public class XMLReader { 
    
    // Create a DocumentBuilder 
    private DocumentBuilderFactory factory; 
    private DocumentBuilder builder;
    
    // Parse the XML file 
    Document document;
    
    private String filepath; 
    private File xmlFile; // Specify the file path as a File object

    /**Construct a reader object for xml file, specified
     *@param - the filename of the xml file (I dont know if it works if attempting to create a new file)
      */
    public XMLReader(String filename){
        this.filepath = System.getProperty("user.dir") + "/XML/" + filename;
        this.xmlFile = new File(this.filepath);
        buildDocReader();
    }
    
    //initilize fields for document parsing and handling (helper to constructor)
    private void buildDocReader(){
        try{
            factory = DocumentBuilderFactory.newInstance(); 
            builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile); // Parse the XML file
        }
        catch(Exception e){
            System.out.println("INITILIZE DOC READER ERROR: POTENTIAL FILE PATH MISSMATCH(REFRENCE FILE) LN40 XMLREADER" + e);
        }
    }
    

    // Access elements by tag name 
    public String getContentsOfElement(String element){
        String found = "";
        
        try{
            NodeList nodeList = document.getElementsByTagName(element);
            for (int i = 0; i < nodeList.getLength(); i++) { 
                Node node = nodeList.item(i); 
                found += node.getTextContent();
            } 
        }
        catch (Exception e){
            return "Element not found - please check query request XMLREADER";
        }
        
        return found;
    }
    /**return the file path of the xml */
    public String getFilePath(){
        return filepath;
    }




} 
 