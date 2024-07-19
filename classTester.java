import org.w3c.dom.Element;

public class classTester {
    public static void main(String args[]){
        XMLWriter writer = new XMLWriter("deck4.xml", true);
        Element x = writer.createNewChildElement(writer.getRootElement(), "penisislarge");
        writer.createTextElement(x, "hi", "hi");

        //Element x = writer.createNewRootElement("gayt");
        //writer.createTextElement(writer.getRootElement(), "gaty", "large");
        //Element element = writer.createNewChildElement(writer.getRootElement(), "mypenis");
        //writer.createTextElement(writer.getRootElement(), "Program4", "mypenis is huge");
        //writer.createTextElement(root, "what is it?", "im the goat");
        //XMLWriter newFile = new XMLWriter("deckballs.xml");
        //newFile.createNewRootElement("Cards");
        //XMLReader xml = new XMLReader("output.xml");
        //System.out.println(xml.getFilePath());
       //System.out.println(xml.getContentsOfElement("library"));
    }
}
