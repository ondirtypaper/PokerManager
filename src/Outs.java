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

    public double[] calcEquity(CardSet aSet, CardSet bSet, Deck currentDeck, int remainBoard){
        double[] equity = {0.0, 0.0, 0.0}; 
        // [aEquity][chopEquity][bEquity]
        CardSet aCardSet = new CardSet(aSet);
        CardSet bCardSet = new CardSet(bSet);
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
        // }else if(remainBoard == 5){
        //     for(Card c1 : currentDeck){
        //         aCardSet.addCard(c1);
        //         bCardSet.addCard(c1);
        //         Deck tempDeck = (Deck)currentDeck.clone();
        //         tempDeck.removeCard(c1);
        //         for(Card c2 : tempDeck){
                    
        //             aCardSet.addCard(c2);
        //             bCardSet.addCard(c2);
        //             Deck tempDeck2 = (Deck)tempDeck.clone();
        //             tempDeck2.removeCard(c2);
        //             for(Card c3 : tempDeck2){
        //                 aCardSet.addCard(c3);
        //                 bCardSet.addCard(c3);
        //                 Deck tempDeck3 = (Deck)tempDeck2.clone();
        //                 tempDeck3.removeCard(c3);
        //                 for(Card c4 : tempDeck3){
        //                     aCardSet.addCard(c4);
        //                     bCardSet.addCard(c4);
        //                     Deck tempDeck4 = (Deck)tempDeck3.clone();
        //                     tempDeck4.removeCard(c4);
        //                     for(Card c5 : tempDeck4){
        //                         aCardSet.addCard(c5);
        //                         bCardSet.addCard(c5);
                                

        //                         possibleCombo++;
        //                         aValue = new Value(aCardSet);
        //                         bValue = new Value(bCardSet);
        //                         int newValueDiff = aValue.compareTo(bValue);
        //                         if(newValueDiff > 0) aCount++;
        //                         else if (newValueDiff == 0) chopCount++;
        //                         else bCount++;
        //                         aCardSet.removeCard(c5);
        //                         bCardSet.removeCard(c5);
                                
        //                     }
        //                     aCardSet.removeCard(c4);
        //                     bCardSet.removeCard(c4);
                            
        //                 }
        //                 aCardSet.removeCard(c3);
        //                 bCardSet.removeCard(c3);
                        
        //             }
        //             aCardSet.removeCard(c2);
        //             bCardSet.removeCard(c2);
                    
                    
        //         }
        //         aCardSet.removeCard(c1);
        //         bCardSet.removeCard(c1);
                
        //     }
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
