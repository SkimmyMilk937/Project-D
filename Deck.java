public class Deck extends PreGameDeck {
    public Card[] deck = new Card[40];

    public Deck(){

    }
}

/* 
import java.util.*;

public class Deck{

  private Card[] cards;
  private int cardsInDeck; //Indicates the number of cards left in the deck in play

  /*
  * @param cards An ArrayList of cards that makes up the deck

  public Deck(List<Card> cards){
    
    this.cards = cards.toArray(new Card[cards.size()]);
    this.cardsInDeck = cards.size();

  }

  /*
  * Randomize the locations of elements of the deck

  
  public void shuffle(){

    for(int i = 0; i < cards.length; i++){
      int randomIndex = (int)(Math.random() * cards.length);
      Card temp = cards[i];
      cards[i] = cards[randomIndex];
      cards[randomIndex] = temp;
    }
    
  }

  /*
  * Sort the cards in the deck by some characteristic

  public void sort(){
    
  }

  /*
  * @param A card that will be added to the deck
  
  public void addCard(Card card){

    List<Card> tempDeck = Arrays.asList(this.cards);
    tempDeck.add(card);
    this.cards = tempDeck.toArray(new Card[tempDeck.size()]);
    cardsInDeck++;
    
  }

  /*
  * @param An ArrayList of cards that will be added to the deck


  public void addCards(List<Card> cards){

    for(int i = 0; i < cards.size(); i++){
      addCard(cards.get(i));
    }
    
  }

  /*
  * @param A card that will  be removed from the deck

  
  public void removeCard(Card card){

    List<Card> tempDeck = Arrays.asList(this.cards);
    tempDeck.remove(card);
    this.cards = tempDeck.toArray(new Card[tempDeck.size()]);
    cardsInDeck--;
    
  }

  /*
  * @param An ArrayList of cards that will be removed from the deck

  
  public void removeCards(List<Card> cards){
    
    for(int i = 0; i < cards.size(); i++){
      removeCard(cards.get(i));
    }
    
  }

  /*
  * @return The top card of the deck

  
  public Card drawCard() throws IllegalArgumentException{

    if(isEmpty()){
      throw new IllegalArgumentException("Cannot draw card because there are none left in the deck");
    }
    return cards[--cardsInDeck];
    
  }

  /*
  * @return boolean indicating whether or not the deck is empty

  public boolean isEmpty(){
    
    if(cardsInDeck == 0){
      return true;
    }
    return false;
    
  }

  

  
  
}
*/