import java.util.ArrayList;

public class PreGameDeck {
    private ArrayList<Card> deck = new ArrayList<Card>();

    public PreGameDeck(){
        
    }

    public void add(Card x){
        deck.add(x);
    }
    
    public void remove(Card x){
        deck.remove(x);
    }

    public ArrayList<Card> getMasterDeck(){
        return deck;
    }
}
