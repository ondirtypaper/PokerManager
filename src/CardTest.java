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
        Hand secondHand = new Hand();

        firstHand.addCard(myDeck.drawCard());
        secondHand.addCard(myDeck.drawCard());
        firstHand.addCard(myDeck.drawCard());
        secondHand.addCard(myDeck.drawCard());
         
        System.out.println("Current Deck Size : " + myDeck.getSize() + " [Full? : " + myDeck.isFull() +"]");
        System.out.println("Your Hand : " + firstHand.toString());
        System.out.println("His Hand : " + secondHand.toString());

        

        System.out.println("> Press Enter...");
        scan.nextLine();

        System.out.println("The flop is dealing...");
        System.out.println("...");
         
        Board myBoard = new Board();
        
        myBoard.dealFlop(myDeck);
        System.out.println("Current Deck Size : " + myDeck.getSize() + " [Full? : " + myDeck.isFull() +"]");
        myBoard.printBoard();

        System.out.print("Your Hand :\t" + firstHand.toString() + "\t");
        Value myHandValue = new Value(firstHand, myBoard);
        System.out.println(myHandValue.toString());
        
        System.out.print("His Hand :\t" + secondHand.toString() + "\t");
        Value hisHandValue = new Value(secondHand, myBoard);
        System.out.println(hisHandValue.toString());

        System.out.println("************************************");
        Outs myOuts = new Outs();
        double[] equity = myOuts.calcEquity(myHandValue, hisHandValue, myDeck, 1);
        
        System.out.println("Your Equity : " + Double.toString(equity[0]));
        System.out.println("Chop Equity : " + Double.toString(equity[1]));
        System.out.println("His  Equity : " + Double.toString(equity[2]));
        

        System.out.println("> Press Enter...");
        scan.nextLine();

        myBoard.dealTurn(myDeck);
        myBoard.printBoard();
        
        System.out.print("Your Hand :\t" + firstHand.toString() + "\t");
        myHandValue = new Value(firstHand, myBoard);
        System.out.println(myHandValue.toString());
        
        System.out.print("His Hand :\t" + secondHand.toString() + "\t");
        hisHandValue = new Value(secondHand, myBoard);
        System.out.println(hisHandValue.toString());
        
        System.out.println("************************************");
        myOuts = new Outs();
        equity = myOuts.calcEquity(myHandValue, hisHandValue, myDeck, 1);
        
        System.out.println("Your Equity : " + Double.toString(equity[0]));
        System.out.println("Chop Equity : " + Double.toString(equity[1]));
        System.out.println("His  Equity : " + Double.toString(equity[2]));
        System.out.println("> Press Enter...");
        scan.nextLine();

        myBoard.dealRiver(myDeck);
        
        myBoard.printBoard();

        System.out.print("Your Hand :\t" + firstHand.toString() + "\t");
        myHandValue = new Value(firstHand, myBoard);
        System.out.println(myHandValue.toString());
        
        System.out.print("His Hand :\t" + secondHand.toString() + "\t");
        hisHandValue = new Value(secondHand, myBoard);
        System.out.println(hisHandValue.toString());
        
        System.out.println("************************************");
        if(myHandValue.compareTo(hisHandValue) > 0) System.out.print("You're ahead");
        else if(myHandValue.compareTo(hisHandValue) <0) System.out.print("You're behind");
        else System.out.print("SAME VAULE");
        System.out.println("> Press Enter...");
        scan.nextLine();

        System.out.print("-------------------------------- again? (Y/N) > "); 
        String command = scan.nextLine();
        if(command.length() == 0){}
        else if((command.charAt(0) == 'N') || (command.charAt(0) == 'n')) break;

      }
     
      System.out.println("... ... test is over");
      scan.close();

    }
}
