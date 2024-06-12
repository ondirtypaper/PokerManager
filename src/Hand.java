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
}
