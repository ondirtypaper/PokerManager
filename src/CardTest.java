public class CardTest {
    public static void main(String[] args) {
        Card card1 = new Card(11,'d');
        Card card2 = new Card(11,'h');

        System.out.println(card1.toString());
        System.out.println(card2.toString());

        card1.setValue(12);
        card2.setValue(13);

        System.out.println(card1.toString());
        System.out.println(card2.toString());

        CardSet myHand = new CardSet();
        myHand.addCard(new Card(11, 's'));
        myHand.addCard(new Card(11,'h'));
        myHand.addCard(new Card(11,'d'));
        myHand.addCard(new Card(11,'c'));

        System.out.println(myHand.toString());

        card1 = myHand.getRandomCard();
        System.out.println(card1.toString());
        System.out.println(myHand.toString());


    }
}
