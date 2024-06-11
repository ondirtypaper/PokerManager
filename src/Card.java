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
        if( suit == 's' || suit == 'd' || suit == 'h' || suit == 'c') // spade or heart or diamond or club
            this.suit = suit;
        else
            System.out.println("Wrong card suit");
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
            default:
                str = Integer.toString(value);
        }
        return switch (suit) {
            case 's' -> str + " of Spades";
            case 'd' -> str + " of Diamonds";
            case 'h' -> str + " of Hearts";
            case 'c' -> str + " of Clubs";
            default -> str;
        };
    }
    public boolean isEqual(Card card){
        return this.suit == card.getSuit() && this.value == card.value;
    }
    @Override
    public int compareTo(Card card){
        return (this.suit *100 + this.value) - (card.getSuit() *100 + card.getValue());
    }

}

