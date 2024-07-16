public enum CardCatalog {
    Arian("Bibimbap", "fighter", 1),
    Avery("Bibimbap", "fighter", 1),
	Brendan("Bibimbap", "fighter", 1),
	Adam("Bibimbap", "fighter", 1),
	Idin("Bibimbap", "fighter", 1),
	Nic("Bibimbap", "fighter", 1),
	Sebastian("Bibimbap", "fighter", 1),
	Soushyant("Bibimbap", "fighter", 1),
	Hanseo("Bibimbap", "fighter", 1);

    public final String set;
    public final String type;
    public final int cost;

    CardCatalog(String set, String type, int cost){
        this.set = set;
        this.type = type;
        this.cost = cost;
    }
}
