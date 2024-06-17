public class Outs extends CardSet{
    CardSet chopOuts = new CardSet();

    public Outs(){}
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

    public double[] calcEquity(CardSet aCardSet, CardSet bCardSet, Deck currentDeck, int remainBoard){
        double[] equity = {0.0, 0.0, 0.0}; 
        // [aEquity][chopEquity][bEquity]

        int possibleCombo = 0;
        int aCount = 0;
        int chopCount = 0;
        int bCount = 0;
        Value aValue = new Value(aCardSet);
        Value bValue = new Value(bCardSet);
        
        if(remainBoard == 1){
            for(Card c : currentDeck){
                aCardSet.addCard(c);
                bCardSet.addCard(c);
                
                possibleCombo++;
                aValue = new Value(aCardSet);
                bValue = new Value(bCardSet);
                int newValueDiff = aValue.compareTo(bValue);
                if(newValueDiff > 0) aCount++;
                else if (newValueDiff == 0) chopCount++;
                else bCount++;
                aCardSet.removeCard(c);
                bCardSet.removeCard(c);   
            }
        }else if(remainBoard == 2){
            for(Card c : currentDeck){
                aCardSet.addCard(c);
                bCardSet.addCard(c);
                Deck tempDeck = (Deck)currentDeck.clone();
                tempDeck.removeCard(c);
                for(Card tC : tempDeck){
                    possibleCombo++;
                    aCardSet.addCard(tC);
                    bCardSet.addCard(tC);
                    aValue = new Value(aCardSet);
                    bValue = new Value(bCardSet);
                    int newValueDiff = aValue.compareTo(bValue);
                    if(newValueDiff > 0) aCount++;
                    else if (newValueDiff == 0) chopCount++;
                    else bCount++;
                    aCardSet.removeCard(tC);
                    bCardSet.removeCard(tC);
                }
                aCardSet.removeCard(c);
                bCardSet.removeCard(c);
            }
        }else{

        }
        equity[0] = aCount / (double)possibleCombo;
        equity[1] = chopCount / (double)possibleCombo;
        equity[2] = bCount /(double)possibleCombo;
        return equity;
    }
    public void print(){
        for (Card c : this){
            System.out.println(c.toString());
        }
    }
}
