import static util.Define.*;

import java.util.SortedSet;

import util.Rank;

public class Value implements Comparable{
    private int[] bestOfFive;
    private Rank rank;

    public Value(){
        bestOfFive = new int[5];
    }
    public Value(CardSet cSet){
        bestOfFive = new int[5];

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
            for(int v : bestOfFive){
                v = valueCode--;
            }

        } else if((valueCode = cSet.hasQuads()) > 0){
            
            this.rank = Rank.QUADS;
            for(int v : bestOfFive){
                v = valueCode;
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

        } else if(cSet.hasFlush() != NO_FLUSH){
            
            this.rank = Rank.FLUSH;
            char flushCode = cSet.hasFlush();
            SortedSet<Card> sSet = cSet.getTreeSet().subSet(new Card(2,flushCode), new Card(VALUE_OF_ACE,flushCode));
            for(int v : bestOfFive){
                v = sSet.last().getValue();
            }
            
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
    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
    
}
