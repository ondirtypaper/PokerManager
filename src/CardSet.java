import java.util.TreeSet;
import java.util.Iterator;

import static util.Define.*;

public class CardSet {
    private TreeSet<Card> tree;

    public CardSet(){
        tree = new TreeSet<Card>();

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
            str = switch (c.getValue()) {
                case VALUE_OF_ACE -> str.concat("A");
                case VALUE_OF_KING -> str.concat("K");
                case VALUE_OF_QUEEN -> str.concat("Q");
                case VALUE_OF_JACK -> str.concat("J");
                case 10 -> str.concat("T");
                default -> str.concat(Integer.toString(c.getValue()));
            };
            str = str.concat(Character.toString(c.getSuit()));
        }
        return str;
    }
}

