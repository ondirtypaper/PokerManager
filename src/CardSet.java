import java.util.TreeSet;
import java.util.Iterator;

import static util.Define.*;

public class CardSet {
    private TreeSet<Card> tree;

    public CardSet(){
        tree = new TreeSet<Card>();
    }
    public CardSet(CardSet ... sets){
        tree = new TreeSet<Card>();
        for (CardSet cSet : sets) {
            for (Card c : cSet.getTreeSet()){
                this.addCard(c);
            }
        }
    }

    public boolean addCard(Card c){
        return tree.add(c);
    }
    public boolean removeCard(Card c){
        return tree.remove(c);
    }
    public int getSize(){
        return tree.size();
    }
    public Card getCardFirst(){
        return tree.first();
    }
    public Card getCardLast(){
        return tree.last();
    }

    @SuppressWarnings("unchecked")
    public TreeSet<Card> getTreeSet(){
        return (TreeSet<Card>)tree.clone();
    }
   
    public Card getRandomCard(){
        Card[] arr = new Card[tree.size()];
        int i = 0;
        for (Card c : tree){
            arr[i] = c;
            i++;
        }
        return arr[(int)(Math.random()*arr.length)];
    }
    public String toString(){
        String str = "";
        for (Card c : tree){
            str = str.concat(c.toString()+", ");
        }
        return str;
    }
    public String toSimpleString(){
        String str = "";
        for (Card c : tree){
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
    public int hasStraightFlush(){
        char flushSuit = this.hasFlush();
        if(flushSuit == NO_FLUSH) return 0; 
        // ClassCastException !! CardSet flushCardSet = (CardSet)this.getTreeSet().subSet(new Card(2,flushSuit), new Card(VALUE_OF_ACE,flushSuit));
        CardSet flushCardSet = new CardSet();
        for (Card c : this.getTreeSet().subSet(new Card(2,flushSuit), new Card(VALUE_OF_ACE,flushSuit))){
            flushCardSet.addCard(c);
        }
        return flushCardSet.hasStraight();
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
    public char hasFlush(){
        int sCount = 0, cCount = 0, dCount = 0, hCount = 0;
        for(Card c : this.getTreeSet()){
            if(c.getSuit() == SUIT_OF_SPADES) sCount++;
            else if(c.getSuit() == SUIT_OF_CLUBS) cCount++;  
            else if(c.getSuit() == SUIT_OF_DIAMONDS) dCount++;
            else if(c.getSuit() == SUIT_OF_HEARTS) hCount++;
        }

        if(sCount >= 5) return SUIT_OF_SPADES;
        if(cCount >= 5) return SUIT_OF_CLUBS;
        if(dCount >= 5) return SUIT_OF_DIAMONDS;
        if(hCount >= 5) return SUIT_OF_HEARTS;
        // 10장 이상이라 두번째 플러시가 나타나는 경우는 고려치 않음. 

        else return NO_FLUSH; 
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
    public int hasTriple(){
        int[] vArr = this.getValueArray();
        int valueOfTriple = 0;
        for(int i=0; i<vArr.length ; i++){
            if(vArr[i]==3) valueOfTriple = i+2;
        }
        return valueOfTriple;
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
    public int hasPair(){
        int[] vArr = this.getValueArray();
        int valueOfPair = 0;
        for(int i=0; i<vArr.length ; i++){
            if(vArr[i]==2) valueOfPair = i+2;
        }
        return valueOfPair;
        // Just return the highest value of Pairs
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
    public int getKicker(){
        int[] vArr = this.getValueArray();
        int kicker = 0;
        for(int i=0; i<vArr.length ; i++){
            if(vArr[i] > 0) kicker = i +2;
        }
        return kicker;
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
    // public int getKicker(int valueCard1 , int valueCard2){
    //     int[] vArr = this.getValueArray();
    //     int kicker = 0;
    //     for(int i=0; i<vArr.length ; i++){
    //         if(valueCard1 == (i+2)) continue;
    //         if(valueCard2 == (i+2)) continue;
    //         if(vArr[i] > 0) kicker = i + 2; 
    //     }
    //     return kicker;
    // }
    public int getKicker(int ... valueCards)
    {
        int[] vArr = this.getValueArray();
        int kicker = 0;
        for(int i=0; i<vArr.length ; i++){
            for(int j=0; j< valueCards.length; j++){
                if(valueCards[j] == (i+2)) continue;
                if(vArr[i] > 0) kicker = i + 2; 
            }
            //if(valueCards[0] == (i+2)) continue;
            //if(valueCards[1] == (i+2)) continue;
        }
        return kicker;
    }

}

