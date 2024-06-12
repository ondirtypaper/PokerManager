
import static util.Define.*;

public class Deck extends CardSet{
    

    public Deck(){
        super();
        for(int i=2; i<=14 ; i++){
            this.addCard(new Card(i, SUIT_OF_SPADES));
            this.addCard(new Card(i, SUIT_OF_HEARTS));
            this.addCard(new Card(i, SUIT_OF_DIAMONDS));
            this.addCard(new Card(i, SUIT_OF_CLUBS));
        }
    }

    public boolean isFull(){
        // Don't have to duplicate cheking cause THIS IS TreeSet
        return (this.getSize()==52);
    }
    public Card drawCard(){
        Card c = this.getRandomCard();
        this.removeCard(c);
        return c;
    }
    
    
}
