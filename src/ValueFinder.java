import static util.Define.*;

public class ValueFinder extends CardSet{
    // Constructors
    public ValueFinder(){}
    public ValueFinder(CardSet aSet, CardSet bSet){
        super();
        for(Card c : aSet.getTreeSet()){
            this.addCard(c);
        } 
        for(Card c : bSet.getTreeSet()){
            this.addCard(c);
        }
    }
    public void showValue(){
        int vCode = this.getValueCode();
        int rankCode = vCode / CODE_OF_HIGH_CARD;
        
        String str = "";
        switch (rankCode) {
            case 9:
                str = "Straight Flush";
                vCode = vCode - (rankCode * CODE_OF_HIGH_CARD);
                rankCode = vCode / CODE_OF_SECOND_VALUE;
                str = Card.intToString(rankCode) + " high " + str + " of " + Card.charToString(this.hasFlush());
                break;
            case 8:
                str = "Four of kind";
                str = str + " of " + Card.intToString(this.hasQuads()) + "s" + " with " + Card.intToString(this.getKicker(this.hasQuads())) + " kicker";
                break;
            case 7:
                str = "Full House";
                str = str + " of " + Card.intToString(this.hasPair()) + " and "  + Card.intToString(this.hasFullHouse()) + "s";
                break;
            case 6:
                str = "Flush";
                vCode = vCode - (rankCode * CODE_OF_HIGH_CARD);
                rankCode = vCode / CODE_OF_SECOND_VALUE;
                str = Card.intToString(rankCode) + " high " + str + " of " + Card.charToString(this.hasFlush());
                break;
            case 5:
                str = "Straight";
                str = Card.intToString(this.hasStraight()) + " high " + str;
                break;
            case 4:
                str = "Three of kind";
                str = str + " of " + Card.intToString(this.hasTriple()) + "s" + " with " + Card.intToString(this.getKicker(this.hasTriple())) + " kicker";
                break;
            case 3:
                str = "Two Pairs";
                str = str + " of " + Card.intToString(this.hasPair()) + " and "  + Card.intToString(this.hasTwoPair()) + "s" + " with " + Card.intToString(this.getKicker(this.hasPair(), this.hasTwoPair())) + " kicker";
                break;
            case 2:
                str = "A Pair";
                str = str + " of " + Card.intToString(this.hasPair()) + "s" + " with " + Card.intToString(this.getKicker(this.hasPair())) + " kicker";
                break;
            case 1:
                str = "Just";
                vCode = vCode - (rankCode * CODE_OF_HIGH_CARD);
                vCode = vCode / CODE_OF_SECOND_VALUE;
                str = str + " " + Card.intToString(vCode + 2) + " high" + " with " + Card.intToString(this.getKicker(vCode+2)) + " kicker";
                break;
            default:
                break;
        }
        System.out.println(str);
    }
    public int getValueCode(){
        int code = 0;
        if(this.hasStraightFlush() > 0){
            code += CODE_OF_STRAIGHT_FLUSH;
            code += this.hasStraightFlush() * CODE_OF_FISRT_VALUE;

        } else if(this.hasQuads() > 0){
            code += CODE_OF_QUADS;
            code += this.hasQuads() * CODE_OF_FISRT_VALUE;
            code += this.getKicker(this.hasQuads()) * CODE_OF_KICKER;

        } else if(this.hasFullHouse() > 0){
            code += CODE_OF_FULL_HOUSE;
            code += this.hasTriple() * CODE_OF_FISRT_VALUE;
            code += this.hasFullHouse() * CODE_OF_SECOND_VALUE;

        } else if(this.hasFlush() != 'f'){
            code += CODE_OF_FLUSH;
            char f = this.hasFlush();
            ValueFinder vf = new ValueFinder();
            for(Card c : this.getTreeSet().subSet(new Card(2,f), new Card(VALUE_OF_ACE,f))){
                vf.addCard(c);
              }
            code += vf.getHighCardCode();

        } else if(this.hasStraight() > 0){
            code += CODE_OF_STRAIGHT;
            code += this.hasStraight() * CODE_OF_FISRT_VALUE;

        } else if(this.hasTriple() > 0){
            code += CODE_OF_TRIPLE;
            code += this.hasTriple() * CODE_OF_FISRT_VALUE;
            int firstKicker = this.getKicker(this.hasTriple());
            code += firstKicker * CODE_OF_SECOND_VALUE;
            int secondKicker = this.getKicker(this.hasTriple(), firstKicker);
            code += secondKicker * CODE_OF_KICKER;

        } else if(this.hasTwoPair() > 0){
            code += CODE_OF_TWO_PAIR;
            code += this.hasPair() * CODE_OF_FISRT_VALUE;
            code += this.hasTwoPair() * CODE_OF_SECOND_VALUE;
            code += this.getKicker(this.hasPair(), this.hasTwoPair()) * CODE_OF_KICKER;

        } else if(this.hasPair() > 0){
            code += CODE_OF_ONE_PAIR;
            code += this.hasPair() * CODE_OF_FISRT_VALUE;
            code += this.getHighCardCode(this.hasPair());
        } else {
            code += CODE_OF_HIGH_CARD;
            code += this.getHighCardCode();
        }

        return code;
    }
    public int getHighCardCode(){
        int code = 0;
        int[] vArr = this.getValueArray(); 
        int i = vArr.length - 1; // last index
        int codeMaker = 10000;
        while(i >= 0){
            if(vArr[i] == 1 && codeMaker >= 1){
                code += i * codeMaker;
                codeMaker /= 10;
            }
            i--;
        }
        
        return code;
    }
    public int getHighCardCode(int pairCard){
        int code = 0;
        int[] vArr = this.getValueArray(); 
        int i = vArr.length - 1; // last index
        int codeMaker = 1000;
        while(i >= 0){
            if(vArr[i] == 1 && codeMaker >= 1){
                code += i * codeMaker;
                codeMaker /= 10;
            }
            i--;
        }
        return code;
    }
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

    public int getKicker(int valueCard){
        int[] vArr = this.getValueArray();
        int kicker = 0;
        for(int i=0; i<vArr.length ; i++){
            if(valueCard == (i+2)) continue;
            if(vArr[i] > 0) kicker = i + 2; 
        }
        return kicker;
    }
    public int getKicker(int valueCard1 , int valueCard2){
        int[] vArr = this.getValueArray();
        int kicker = 0;
        for(int i=0; i<vArr.length ; i++){
            if(valueCard1 == (i+2)) continue;
            if(valueCard2 == (i+2)) continue;
            if(vArr[i] > 0) kicker = i + 2; 
        }
        return kicker;
    }

}
