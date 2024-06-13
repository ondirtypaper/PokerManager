import java.util.Scanner;

import util.Define.*;

public class CardTest {
    public static void main(String[] args) {
      
      Scanner scan = new Scanner(System.in);
      while(true){
         System.out.println("************************************");
         System.out.println("***** Test Command for CardSet *****");
         System.out.println("************************************");

         Deck myDeck = new Deck();
         
         System.out.println("Current Deck Size : " + myDeck.getSize() + " [Full? : " + myDeck.isFull() +"]");
         System.out.println("> Press Enter...");
         scan.nextLine();

         System.out.println("The hand is dealing...");
         System.out.println("...");
         

         Hand firstHand = new Hand();

         firstHand.addCard(myDeck.drawCard());
         firstHand.addCard(myDeck.drawCard());
         
         System.out.println("Current Deck Size : " + myDeck.getSize() + " [Full? : " + myDeck.isFull() +"]");
         System.out.println("<<" + firstHand.toSimpleString() + ">> is dealted. [Suited? " + firstHand.isSuited() + "]" + "[Pair? " + firstHand.isPocketPair() + "]");
         System.out.println("> Press Enter...");
         scan.nextLine();

         System.out.println("The flop is dealing...");
         System.out.println("...");
         
         Board myBoard = new Board();
         myBoard.dealFlop(myDeck);
         System.out.println("Current Deck Size : " + myDeck.getSize() + " [Full? : " + myDeck.isFull() +"]");
         myBoard.printBoard();
         System.out.print("<<" + firstHand.toSimpleString() +">> ");
         // firstHand 와 myBoard를 합쳐서 ValueFinder 생성
         ValueFinder vf = new ValueFinder(firstHand, myBoard);
         System.out.print(vf.getValueCode() + " ");
         vf.showValue();
         System.out.println("************************************");

         scan.nextLine();

         myBoard.dealTurn(myDeck);
         myBoard.printBoard();
         System.out.print("<<" + firstHand.toSimpleString() +">> ");
         vf.addCard(myBoard.getCard(3));
         System.out.print(vf.getValueCode() + " ");
         vf.showValue();

         scan.nextLine();

         myBoard.dealRiver(myDeck);
         myBoard.printBoard();
         System.out.print("<<" + firstHand.toSimpleString() +">> ");
         vf.addCard(myBoard.getCard(4));
         System.out.print(vf.getValueCode() + " ");
         vf.showValue();

         System.out.print("-------------------------------- again? (Y/N) > ");
         
         String command = scan.nextLine();
         if(command.length() == 0){}
         else if((command.charAt(0) == 'N') || (command.charAt(0) == 'n')) break;
      }
      System.out.println("... ... test is over");
      scan.close();
       

    }
}
