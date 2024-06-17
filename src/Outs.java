public class Outs extends CardSet{

    public Outs(CardSet aCardSet, CardSet bCardSet, Deck currentDeck){
        Value aValue = new Value(aCardSet);
        Value bValue = new Value(bCardSet);
        int valueDiff = aValue.compareTo(bValue);
        for(Card c : currentDeck){
            aCardSet.addCard(c);
        }
    }
    
}
