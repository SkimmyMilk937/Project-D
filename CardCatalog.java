
import java.io.File;

public enum CardCatalog {
    Arian("Arian", "Bibimbap", "fighter", 1, new File("Images/Arian.png")),
	Avery("Avery", "Bibimbap", "fighter", 1, new File("Images/Avery.png")),
	Brendan("Brendan", "Bibimbap", "fighter", 1, new File("Images/Brendan.png")),
	Adam("Adam", "Bibimbap", "fighter", 1, new File("Images/Adam.png")),
	Idin("Idin", "Bibimbap", "fighter", 1, new File("Images/Idin.png")),
	Nic("Nic", "Bibimbap", "fighter", 1, new File("Images/Nic.png")),
	Sebastian("Sebastian", "Bibimbap", "fighter", 1, new File("Images/Sebastian.png")),
	Soushyant("Soushyant", "Bibimbap", "fighter", 1, new File("Images/Soushyant.png")),
	Hanseo("Hanseo", "Bibimbap", "fighter", 1, new File("Images/Hanseo.png"));

    public final String set;
    public final String type;
    public final int cost;
    public final File image;
    private final String name;

    CardCatalog(String name, String set, String type, int cost, File image){
        this.name = name;
        this.set = set;
        this.type = type;
        this.cost = cost;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
}
