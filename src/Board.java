public class Board extends CardSet{
    private Card[] boardArray = new Card[5];
    
    public Card getCard(int i){
        return boardArray[i];
    }
    public boolean dealFlop(Deck d){
        if(this.getSize() == 0){
        
            this.addCard(boardArray[0]= d.drawCard());
            this.addCard(boardArray[1]= d.drawCard());
            this.addCard(boardArray[2]= d.drawCard());

            return true;
        }
        else return false;
    }
    public boolean dealTurn(Deck d){
        if(this.getSize() == 3){

            this.addCard(boardArray[3]= d.drawCard());

            return true;
        }
        else return false;
    }
    public boolean dealRiver(Deck d){
        if(this.getSize() == 4){

            this.addCard(boardArray[4]= d.drawCard());

            return true;
        }
        else return false;
    }
    public String getStage(){
        if(this.getSize() == 0)
            return "Pre-Flop";
        else if(this.getSize() == 3)
            return "Flop";
        else if(this.getSize() == 4)
            return "Turn";
        else if(this.getSize() == 5)
            return "River";
        else
            return "log : !! Something's wrong on board.";
    }
    public void printBoard(){
        if(this.getSize() == 0)
            System.out.println("There is no board.");
        else if(this.getSize() == 3)
            System.out.println("Flop : [" + boardArray[0].toSimpleString() + ", " + boardArray[1].toSimpleString() + ", " + boardArray[2].toSimpleString() + "]");
        else if(this.getSize() == 4)
            System.out.println("Turn : " + boardArray[0].toSimpleString() + ", " + boardArray[1].toSimpleString() + ", " + boardArray[2].toSimpleString() + ", [" + boardArray[3].toSimpleString() +"]");
        else if(this.getSize() == 5)
            System.out.println("River : " + boardArray[0].toSimpleString() + ", " + boardArray[1].toSimpleString() + ", " + boardArray[2].toSimpleString() + ", "  + boardArray[3].toSimpleString() + ", [" + boardArray[4].toSimpleString() + "]");
        else
            System.out.println("log : !! Something's wrong on board.");
    }
}
