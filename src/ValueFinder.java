import static util.Define.*;

public class ValueFinder extends CardSet{
    
    public char hasFlush(){
        int sCount = 0, cCount = 0, dCount = 0, hCount = 0;
        for(Card c : this.getTreeSet()){
            if(c.getSuit() == SUIT_OF_SPADES) sCount++;
            else if(c.getSuit() == SUIT_OF_CLUBS) cCount++;  
            else if(c.getSuit() == SUIT_OF_DIAMONDS) dCount++;
            else if(c.getSuit() == SUIT_OF_HEARTS) hCount++;
        }

        // if(sCount >= 5 || cCount >= 5 || dCount >= 5 || hCount >= 5){
        //     return true;
        // }
        if(sCount >= 5) return SUIT_OF_SPADES;
        if(cCount >= 5) return SUIT_OF_CLUBS;
        if(dCount >= 5) return SUIT_OF_DIAMONDS;
        if(hCount >= 5) return SUIT_OF_HEARTS;
        // ValueFinder 가 10장이라서 두번째 플러시가 나타는 경우는 고려치 않음. ValueFinder의 Max size는 7 (오마하를 포함하면 9)

        else return 'f'; // means false
    }

    public int[] getValueArray(){
        int[] vArr = new int[13];
        for(int v : vArr){
            v=0;
        }
        for(Card c : this.getTreeSet()){
            vArr[c.getValue()-2]++;
            // for example ,  vArr[4] == count of 6s   
        }
        return vArr;
    }

    public int hasQuads(){
        int[] vArr = this.getValueArray();
        int valueOfQuads = 0;
        for(int i=0; i<vArr.length ; i++){
            if(vArr[i]==4) valueOfQuads = i+2;
        }
        return valueOfQuads; 
        // Just return the highest value of Quadses
    }

    public int hasTriple(){
        int[] vArr = this.getValueArray();
        int valueOfTriple = 0;
        for(int i=0; i<vArr.length ; i++){
            if(vArr[i]==3) valueOfTriple = i+2;
        }
        return valueOfTriple;
        // Just return the highest value of Triples
    }

    public int hasPair(){
        int[] vArr = this.getValueArray();
        int valueOfPair = 0;
        for(int i=0; i<vArr.length ; i++){
            if(vArr[i]==2) valueOfPair = i+2;
        }
        return valueOfPair;
        // Just return the highest value of Triples
    }

    public int hasTwoPair(){
        int[] vArr = this.getValueArray();
        int firstPair = this.hasPair();
        int secondPair = 0;
        if(firstPair != 0){
            for(int i=0; i<vArr.length ; i++){
                if((vArr[i]==2) && (i+2 != firstPair))
                    secondPair = i+2;
            }
        }else{
            secondPair = 0;
        }
        return secondPair; 
        // Just return the value of second pair
    }

    public int hasFullHouse(){
        int[] vArr = this.getValueArray();
        int firstTrips = this.hasTriple();
        int secondPair = 0;
        if(firstTrips != 0){
            for(int i=0; i<vArr.length ; i++){
                if((vArr[i]>=2) && (i+2 != firstTrips))
                    secondPair = i+2;
            }
        }else{
            secondPair = 0;
        }
        return secondPair; 
        // Just return the value of second pair (or trips)
    }

    public int hasStraight(){
        int[] vArr = this.getValueArray();
        int strCount = 0;
        int edgeOfStr = 0;
        // Count Aces as '1' for straight
        if(vArr[12] !=0) strCount++;
        for (int i=0 ; i < vArr.length ; i++){
            if(vArr[i] == 0)
                strCount = 0;
            else
                strCount++;
            if(strCount >= 5)
                edgeOfStr = i + 2;
        }
        return edgeOfStr; // return the value of high card value of higer straight
    }

    public int hasStraightFlush(){
       char f = this.hasFlush();
       if(f == 'f') return 0; // If hasFlush is false. return 0 (means NO)
       ValueFinder vf = new ValueFinder();
       for(Card c : this.getTreeSet().subSet(new Card(2,f), new Card(VALUE_OF_ACE,f))){
        vf.addCard(c);
       }
       return vf.hasStraight();
    }


}
