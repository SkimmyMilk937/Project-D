import org.w3c.dom.Element;

public class classTester {
    public static void main(String args[]){
        XMLWriter writer = new XMLWriter("output.xml");
        //Element element = writer.createNewChildElement(writer.getRootElement(), "mypenis");
        writer.createTextElement(writer.getRootElement(), "Program4", "mypenis is huge");
        //writer.createTextElement(root, "what is it?", "im the goat");
        writer.commitChanges();
        XMLReader xml = new XMLReader("output.xml");
        System.out.println(xml.getFilePath());
       System.out.println(xml.getContentsOfElement("library"));
    }
}
