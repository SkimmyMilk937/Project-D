import org.w3c.dom.Element;
import java.util.ArrayList;

public class Deck extends PreGameDeck {
    public Card[] deck = new Card[40];

    private final Element fighterElement;
    private final Element supportElement;
    private final Element spellElement;
    private final Element defenderElement;


    private ArrayList<String> fighterList; //list of fighters in the xml
    private ArrayList<Element> fighterTextElements; //list of the elment texts in the xml

    private XMLWriter writer;
    
    public Deck(String deckName){
        writer = new XMLWriter(deckName, true);
        writer.createNewRootElement(deckName);
  
        fighterElement = writer.createNewChildElement(writer.getRootElement(), "fighter");
        supportElement = writer.createNewChildElement(writer.getRootElement(), "support");
        spellElement = writer.createNewChildElement(writer.getRootElement(), "spell");
        defenderElement = writer.createNewChildElement(writer.getRootElement(), "defender");

        fighterTextElements = new ArrayList<Element>();

        updateLists();
      }

      public void addCard(CardCatalog card){

        String cardType = card.getType();
        Element element; 

        if(cardType.equals("fighter")){   //expand for other card types
          if(fighterList.contains(card.getName())){
            System.out.println("Does contain");
            for(Element x : fighterTextElements){
                if(x.getNodeName().equals(card.getName())){
                    writer.setElementAttribute(x, "count", String.valueOf(Integer.parseInt(writer.getAtribute(x, "count")) + 1));
                }
            }

          }
          else{
            element = writer.createNewChildElement(fighterElement, card.getName());
            writer.setElementAttribute(element, "count", "1");
            //element = writer.createTextElement(fighterElement, card.getName(), "1");
            fighterTextElements.add(element);
          }
        }
        
        updateLists();
      }

      public void removeCard(CardCatalog card){ //hopefully this work? Im not testing it :) //expand for other card types
        String cardType = card.getType();
        if(cardType.equals("fighter")){   
          if(fighterList.contains(card.getName())){
            System.out.println("Does contain");
            for(Element x : fighterTextElements){
                if(x.getNodeName().equals(card.getName())){
                  if(Integer.valueOf(writer.getAtribute(x, "count")) > 1 )
                    writer.setElementAttribute(x, "count", String.valueOf(Integer.parseInt(writer.getAtribute(x, "count")) - 1));
                  else
                    writer.removeTextElement(fighterElement, card.getName());
                }
                
            }
          } 
        }
      }



      /** 
      public boolean renameDeck(String newName){
        return writer.renameFile(newName + ".xml");
      }
      */

      public void updateLists(){
          fighterList = writer.getChildNodesString(fighterElement);
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