public enum CardCatalog {
    Arian("Bibimbap", "fighter", 1);

    public final String set;
    public final String type;
    public final int cost;

    CardCatalog(String set, String type, int cost){
        this.set = set;
        this.type = type;
        this.cost = cost;
    }
}
