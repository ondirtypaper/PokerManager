import static util.Define.*;

import java.util.SortedSet;

import util.Rank;

public class Value implements Comparable<Value>{
    private int[] bestOfFive;
    private Rank rank;
    private char flushCode;

    public Value(){
        bestOfFive = new int[5];
    }
    public Value(CardSet cSet){
        bestOfFive = new int[5];
        this.distValue(cSet);
    }
    public Value(CardSet ... cSets){
        bestOfFive = new int[5];
        CardSet cSet = new CardSet(cSets);
        this.distValue(cSet);
    }

    public void setRank(int rankCode){
        switch(rankCode){
            case 9: this.rank = Rank.STRAIGHT_FLUSH; break;
            case 8: this.rank = Rank.QUADS; break;
            case 7: this.rank = Rank.FULL_HOUSE; break;
            case 6: this.rank = Rank.FLUSH; break;
            case 5: this.rank = Rank.STRAIGHT; break;
            case 4: this.rank = Rank.THREE_OF_KIND; break;
            case 3: this.rank = Rank.TWO_PAIR; break;
            case 2: this.rank = Rank.ONE_PAIR; break;
            case 1: this.rank = Rank.HIGH_CARD; break;
        } 
    }
    public void setRank(Rank rank){
        this.rank = rank;
    }
    public Rank getRank(){
        return this.rank;
    }
    public int getRankCode(){
        switch(this.rank){
            case STRAIGHT_FLUSH: return 9;
            case QUADS: return 8;
            case FULL_HOUSE: return 7;
            case FLUSH: return 6;
            case STRAIGHT: return 5;
            case THREE_OF_KIND: return 4;
            case TWO_PAIR: return 3;
            case ONE_PAIR: return 2;
            case HIGH_CARD: return 1;
            default: return 0;
        }
    }
    public void distValue(CardSet cSet){
        int valueCode;
        if((valueCode = cSet.hasStraightFlush()) > 0){
            
            this.rank = Rank.STRAIGHT_FLUSH;
            this.flushCode = cSet.hasFlush();
            for(int i=0; i<bestOfFive.length ;i++){
                bestOfFive[i] = valueCode--;
            }

        } else if((valueCode = cSet.hasQuads()) > 0){
            
            this.rank = Rank.QUADS;
            for(int i=0; i<bestOfFive.length ;i++){
                bestOfFive[i] = valueCode;
            }
            bestOfFive[4] = cSet.getKicker(valueCode); 
            // bestOfFive of Quads -> [v][v][v][v][k]

        } else if((valueCode = cSet.hasFullHouse()) > 0){
            
            //hasFullHouse() return the pair value of full house
            this.rank = Rank.FULL_HOUSE;
            int tValue = cSet.hasTriple();
            for(int i=0; i < bestOfFive.length ; i++){
                if(i<0) bestOfFive[i] = tValue;
                else bestOfFive[i] = valueCode;
            }
            // bestOfFive of FullHouse -> [f][f][f][f][s]

        } else if((this.flushCode = cSet.hasFlush()) != NO_FLUSH){
            
            this.rank = Rank.FLUSH;
            SortedSet<Card> sSet = cSet.getTreeSet().subSet(new Card(2,flushCode), new Card(VALUE_OF_ACE,flushCode));
            for(int i=0; i<bestOfFive.length ;i++){
                bestOfFive[i] = sSet.last().getValue();
            }

        } else if((valueCode = cSet.hasStraight()) > 0){
            
            this.rank = Rank.STRAIGHT;
            for(int i=0; i<bestOfFive.length ;i++){
                bestOfFive[i] = valueCode--;
            }
            
        } else if((valueCode = cSet.hasTriple()) > 0){
            
            this.rank = Rank.THREE_OF_KIND;
            for(int i=0; i<bestOfFive.length ;i++){
                bestOfFive[i] = valueCode;
            }
            bestOfFive[3] = cSet.getKicker(valueCode); 
            bestOfFive[4] = cSet.getKicker(valueCode, bestOfFive[3]);
            // bestOfFive of triple [v][v][v][first_kicker][second_kicker]

        } else if((valueCode = cSet.hasTwoPair()) > 0){
            
            this.rank = Rank.TWO_PAIR;
            int firstPairValue = cSet.hasPair();
            bestOfFive[0] = firstPairValue;
            bestOfFive[1] = firstPairValue;
            // hasTwoPair() returns value of second-best pair
            bestOfFive[2] = valueCode;
            bestOfFive[3] = valueCode;
            bestOfFive[4] = cSet.getKicker(firstPairValue, valueCode);
            // bestOfFive of TwoPair [first_value][first_value][second_value][second_value][kicker]

        } else if((valueCode = cSet.hasPair()) > 0){
            
            this.rank = Rank.ONE_PAIR;
            bestOfFive[0] = valueCode;
            bestOfFive[1] = valueCode;
            bestOfFive[2] = cSet.getKicker(valueCode);
            bestOfFive[3] = cSet.getKicker(valueCode, bestOfFive[1]);
            bestOfFive[4] = cSet.getKicker(valueCode, bestOfFive[1], bestOfFive[2]);

        } else {
            
            // Just High card
            this.rank = Rank.HIGH_CARD;
            bestOfFive[0] = cSet.getKicker();
            bestOfFive[1] = cSet.getKicker(bestOfFive[0]);
            bestOfFive[2] = cSet.getKicker(bestOfFive[0], bestOfFive[1]);
            bestOfFive[3] = cSet.getKicker(bestOfFive[0], bestOfFive[1], bestOfFive[2]);  
            bestOfFive[4] = cSet.getKicker(bestOfFive[0], bestOfFive[1], bestOfFive[2], bestOfFive[3]);
            
        }
    }
    public String toString(){
        
        switch (this.rank) {
            case STRAIGHT_FLUSH: return Card.intToString(bestOfFive[0]) + " High Straight Flush of " + getflushStr() ;
            case QUADS: return "Four of a kind with " + Card.intToString(bestOfFive[0]) + " and " + Card.intToString(bestOfFive[4]) + " kicker";
            case FULL_HOUSE: return "Full house of " +  Card.intToString(bestOfFive[0]) + " and " + Card.intToString(bestOfFive[4]);
            case FLUSH: return Card.intToString(bestOfFive[0]) + " High Flush of " + getflushStr();
            case STRAIGHT: return Card.intToString(bestOfFive[0]) + " High Straight";
            case THREE_OF_KIND: return "Three of a kind with " + Card.intToString(bestOfFive[0]) + " and " + Card.intToString(bestOfFive[3]) + " kicker";
            case TWO_PAIR: return "Two pairs of " + Card.intToString(bestOfFive[0]) + "s and " + Card.intToString(bestOfFive[2]) + "s and " + Card.intToString(bestOfFive[4])+ " kicker";
            case ONE_PAIR: return "A pair of " + Card.intToString(bestOfFive[0]) + "s and " + Card.intToString(bestOfFive[1]) + " kicker";
            case HIGH_CARD: return "Just " + Card.intToString(bestOfFive[0]) + " High and " + Card.intToString(bestOfFive[1]) + " kicker";
            default: return "";        
        }
    }
    public String getflushStr(){
        switch (flushCode) {
            case 's': return "Spades";
            case 'h': return "Hearts";
            case 'd': return "Diamonds";
            case 'c': return "Clubs";
            default: return "None";
        }
    }
    @Override
    public int compareTo(Value v){
        int diff = this.getRankCode() - v.getRankCode();
        if(diff != 0) return diff * WEIGHT_OF_RANKING_DIFFERENCE;
        else{
            
            diff = this.bestOfFive[0] - v.bestOfFive[0];
            if (diff != 0) return diff * WEIGHT_OF_FIRST_VALUE_DIFFERENCE;
            else{
                switch(this.rank){
                    case STRAIGHT_FLUSH: return 0;
                    case QUADS:
                    diff = this.bestOfFive[4] - v.bestOfFive[4];
                    return diff * WEIGHT_OF_KICKER_DIFFERENCE;
                    case FULL_HOUSE:
                    diff = this.bestOfFive[3] - v.bestOfFive[3];
                    return diff * WEIGHT_OF_SECOND_VALUE_DIFFERENCE;
                    case FLUSH: return 0;
                    case STRAIGHT: return 0;
                    case THREE_OF_KIND:
                    diff = this.bestOfFive[3] - v.bestOfFive[3];
                    if(diff == 0) diff = this.bestOfFive[4] - v.bestOfFive[4];
                    return diff * WEIGHT_OF_KICKER_DIFFERENCE;
                    case TWO_PAIR:
                    diff = this.bestOfFive[2] - v.bestOfFive[2];
                    if(diff == 0){
                        diff = this.bestOfFive[4] - v.bestOfFive[4];
                        return diff * WEIGHT_OF_KICKER_DIFFERENCE;
                    }else{
                        return diff * WEIGHT_OF_SECOND_VALUE_DIFFERENCE;
                    }
                    case ONE_PAIR:
                    diff = this.bestOfFive[2] - v.bestOfFive[2];
                    if(diff == 0){
                        diff = this.bestOfFive[3] - v.bestOfFive[3];
                        if(diff == 0){
                            diff = this.bestOfFive[4] - v.bestOfFive[4];
                        }
                    }
                    return diff * WEIGHT_OF_KICKER_DIFFERENCE;
                    case HIGH_CARD:
                    diff = this.bestOfFive[1] - v.bestOfFive[1];
                    if(diff == 0){
                        diff = this.bestOfFive[2] - v.bestOfFive[2];
                        if(diff == 0){
                            diff = this.bestOfFive[3] - v.bestOfFive[3];
                            if(diff == 0){
                                diff = this.bestOfFive[4] - v.bestOfFive[4];
                            }
                        } 
                    }
                    return diff * WEIGHT_OF_KICKER_DIFFERENCE;
                }
            }

        return diff;
        }   
    }
}
