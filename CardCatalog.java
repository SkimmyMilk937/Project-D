
import java.io.File;

public enum CardCatalog {
    Arian("Bibimbap", "fighter", 1, new File("Images/Arian.png")),
	Avery("Bibimbap", "fighter", 1, new File("Images/Avery.png")),
	Brendan("Bibimbap", "fighter", 1, new File("Images/Brendan.png")),
	Adam("Bibimbap", "fighter", 1, new File("Images/Adam.png")),
	Idin("Bibimbap", "fighter", 1, new File("Images/Idin.png")),
	Nic("Bibimbap", "fighter", 1, new File("Images/Nic.png")),
	Sebastian("Bibimbap", "fighter", 1, new File("Images/Sebastian.png")),
	Soushyant("Bibimbap", "fighter", 1, new File("Images/Soushyant.png")),
	Hanseo("Bibimbap", "fighter", 1, new File("Images/Hanseo.png"));

    public final String set;
    public final String type;
    public final int cost;
    public final File image;

    CardCatalog(String set, String type, int cost, File image){
        this.set = set;
        this.type = type;
        this.cost = cost;
        this.image = image;
    }
}
