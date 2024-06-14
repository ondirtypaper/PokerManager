import static util.Define.*;

public class Card implements Comparable<Card>{
    private int value;
    private char suit;

    public Card(){};

    public Card(int value, char suit){
        this.value = value;
        this.suit = suit;
    }
    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        if(value >= 2 && value <= 14) // Jack:11,Queen:12,King:13,Ace:14
            this.value = value;
        else
            System.out.println("Wrong card value");
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        if( suit == SUIT_OF_SPADES || suit == SUIT_OF_DIAMONDS || suit == SUIT_OF_HEARTS || suit == SUIT_OF_CLUBS) 
            this.suit = suit;
        else
            System.out.println("Wrong card suit");
    }

    public static String intToString(int value){
        String str;
        switch (value) {
            case VALUE_OF_ACE:
                str = "Ace";
                break;
            case VALUE_OF_KING:
                str = "King";
                break;
            case 12:
                str = "Queen";
                break;
            case 11:
                str = "Jack";
                break;
            case 10:
                str = "Ten";
                break;
            default:
                str = Integer.toString(value);
        }
        return str;
    }
    public static String charToString(char suit){
        String str ="";
        switch (suit) {
            case 's':
            str = str.concat("Spades");
            break;
            case 'd':
            str = str.concat("Diamonds");
            break;
            case 'h':
            str = str.concat("Hearts");
            break;
            case 'c':
            str = str.concat("Clubs");
            break;
            default:
            break;
        }
        return str;
    }
    public String toString()
    {
        String str;
        switch (value) {
            case 14:
                str = "Ace";
                break;
            case 13:
                str = "King";
                break;
            case 12:
                str = "Queen";
                break;
            case 11:
                str = "Jack";
                break;
            case 10:
                str = "Ten";
                break;
            default:
                str = Integer.toString(value);
        }
        // return switch (suit) {
        //     case 's' -> str + " of Spades";
        //     case 'd' -> str + " of Diamonds";
        //     case 'h' -> str + " of Hearts";
        //     case 'c' -> str + " of Clubs";
        //     default -> str;
        // };
        // JAVA 14+ only
        switch (suit) {
            case 's':
            str = str.concat(" of Spades");
            break;
            case 'd':
            str = str.concat(" of Diamonds");
            break;
            case 'h':
            str = str.concat(" of Hearts");
            break;
            case 'c':
            str = str.concat(" of Clubs");
            break;
            default:
            break;
        }
        return str;
    }
    public String toSimpleString()
    {
        String str;
        switch (value) {
            case 14:
                str = "A";
                break;
            case 13:
                str = "K";
                break;
            case 12:
                str = "Q";
                break;
            case 11:
                str = "J";
                break;
            case 10:
                str = "T";
            default:
                str = Integer.toString(value);
        }
        return str + this.suit;
    }
    public boolean isEqual(Card card){
        return this.suit == card.getSuit() && this.value == card.value;
    }
    @Override
    public int compareTo(Card card){
        return (this.suit *100 + this.value) - (card.getSuit() *100 + card.getValue());
    }

}

