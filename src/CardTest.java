public class CardTest {
    public static void main(String[] args) {
       
       Deck myDeck = new Deck();
       
       System.out.println("Full Deck : "  + myDeck.isFull());
    //    int i  = 1;
    //    while(myDeck.getSize() > 0){
    //      System.out.println(i++ + " : " + myDeck.drawCard().toString()); 
    //    }

    //   myDeck = new Deck();
       Hand firstHand = new Hand();

       firstHand.addCard(myDeck.drawCard());
       firstHand.addCard(myDeck.drawCard());
       firstHand.addCard(myDeck.drawCard());

       //System.out.println(firstHand.toString());
       //System.out.println("Suited? : " +firstHand.isSuited()+ "| Pair? : " +firstHand.isPocketPair());
       System.out.println(firstHand.toSimpleString());

       Board myBoard = new Board();
       myBoard.dealFlop(myDeck);
       myBoard.printBoard();
       myBoard.dealTurn(myDeck);
       myBoard.printBoard();
       myBoard.dealRiver(myDeck);
       myBoard.printBoard();

       ValueFinder vf = new ValueFinder();

       vf.addCard(new Card(2,'c'));
       vf.addCard(new Card(3,'c'));
       vf.addCard(new Card(4,'c'));
       vf.addCard(new Card(7,'h'));
       vf.addCard(new Card(7,'c'));

       System.out.println(vf.toString());

       System.out.println(vf.hasFlush());

       System.out.println(vf.getValueCode());

       ValueFinder vf2 = new ValueFinder();

      //  vf2.addCard(new Card(5,'s'));
      //  vf2.addCard(new Card(5,'d'));
      //  vf2.addCard(new Card(6,'s'));
      //  vf2.addCard(new Card(7,'c'));
      //  vf2.addCard(new Card(7,'s'));
      //  vf2.addCard(new Card(8,'d'));
      //  vf2.addCard(new Card(9,'s'));
       
      //  System.out.println(vf2.toString());
      //  System.out.println("hasPair ? : " + vf2.hasPair());
      //  System.out.println("hasTwoPair ? : " + vf2.hasTwoPair());
      //  System.out.println("hasTriple ? : " + vf2.hasTriple());
      //  System.out.println("hasFullHouse ? : " + vf2.hasFullHouse());
      //  System.out.println("hasStraight ? : " + vf2.hasStraight());
      //  System.out.println("hasStraightFlush ? : " + vf2.hasStraightFlush());

    }
}
