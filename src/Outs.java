public class Outs extends CardSet{
    CardSet chopOuts = new CardSet();

    public Outs(CardSet aCardSet, CardSet bCardSet, Deck currentDeck){
        Value aValue = new Value(aCardSet);
        Value bValue = new Value(bCardSet);

        int oldValueDiff = aValue.compareTo(bValue);
        
        for(Card c : currentDeck){
            aCardSet.addCard(c);
            bCardSet.addCard(c);
            aValue = new Value(aCardSet);
            bValue = new Value(bCardSet);
            int newValueDiff = aValue.compareTo(bValue);
            if(oldValueDiff > 0){
                if(newValueDiff < 0) this.addCard(c);
                else if(newValueDiff == 0) this.chopOuts.addCard(c);
            } else if (oldValueDiff == 0){
                if(newValueDiff != 0) this.addCard(c);
            } else {
                if(newValueDiff > 0) this.addCard(c);
                else if(newValueDiff ==0) this.chopOuts.addCard(c);
            }
            aCardSet.removeCard(c);
            bCardSet.removeCard(c);
        }
    }

    public void print(){
        for (Card c : this){
            System.out.println(c.toString());
        }
    }
}
