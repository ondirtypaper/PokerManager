import static util.Define.*;
public class Hand extends CardSet{
    
    public Hand(){
        super();
    }
    @Override
    public boolean addCard(Card c){
        if(this.getSize() == 2){
            // for other game, it should be fixed
            System.out.println("log : !! Tried to deal a third card in hand !!");
            return false;
        }
        else
            return super.addCard(c);
    }
    public boolean isSuited(){
        if(this.getCardFirst().getSuit() == this.getCardLast().getSuit())
            return true;
        else return false;
    }
    public boolean isPocketPair(){
        if(this.getCardFirst().getValue() == this.getCardLast().getValue())
        return true;
        else
        return false;
    }
    public String toString(){
        String str = "";
        Card[] tCardArr = new Card[2];
        if(this.getCardFirst().getValue() < this.getCardLast().getValue()){
            tCardArr[0] = this.getCardLast();
            tCardArr[1] = this.getCardFirst();
        } else {
            tCardArr[0] = this.getCardFirst();
            tCardArr[1] = this.getCardLast();
        }
        
        for(Card c : tCardArr){
            switch(c.getValue()){
                case VALUE_OF_ACE:
                str = str.concat("A");
                break;
                case VALUE_OF_KING:
                str = str.concat("K");
                break;
                case VALUE_OF_QUEEN:
                str = str.concat("Q");
                break;
                case VALUE_OF_JACK:
                str = str.concat("J");
                break;
                case 10:
                str = str.concat("T");
                break;
                default:
                str = str.concat(Integer.toString(c.getValue()));
            }
            str = str.concat(Character.toString(c.getSuit()));
        }
        return str;
    }
}
