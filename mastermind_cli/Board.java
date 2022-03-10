import java.util.Scanner;

public class Board {
   private static final int ROWS = 12;
   private static int COLUMNS;
   private static String[] pegsOfMind;
   private int placeOnBoard;
   private int blacks;
   private int whites;

   public Board(int col){
       this.placeOnBoard = 0;
       COLUMNS = col;
       pegsOfMind = new String[COLUMNS];
       Pegs pegs = new Pegs();
       for(int i = 0; i < COLUMNS; i++){
           pegsOfMind[i] = pegs.getPeg(i);
       }
   }
   public Board(){
       this(4);
   }
   public boolean checkPosition(String color, int i){
       return pegsOfMind[i].equalsIgnoreCase(color);
   }
   public boolean checkRow(String color, boolean[] marked){
        for (int i = 0; i < COLUMNS; i++){
            if(pegsOfMind[i].equalsIgnoreCase(color) && !marked[i]){
                marked[i] = true;
                return true;
            }
        }
        return false;

   }
   public int placeOnBoard(){
       return placeOnBoard;
   }
   public boolean checkPlay(String[] play){
       boolean[] marked = new boolean[COLUMNS];
       boolean[] whitesMarked = new boolean[COLUMNS];
       this.blacks = 0;
       this.whites = 0;
       for(int i = 0; i < COLUMNS; i++){
           if(checkPosition(play[i], i)){
               this.blacks++;
               marked[i] = true;
               whitesMarked[i] = true;
           }
           else if(checkRow(play[i], whitesMarked)){
               this.whites++;
           }

       }

       // checking wincondition
       for (int i = 0; i < COLUMNS; i++){
           if (!marked[i])
               return false;
       }
       return true;
   }
   public int whites(){
       return this.whites;
   }
   public int blacks(){
       return this.blacks;
   }
   public void cheatPrint(){
       for(String color : pegsOfMind)
            System.out.println(color);
   }

   public static void main(String[] args){

    Board board = new Board();
    System.out.println("New board created");

    String[] test = new String[4];
    test[0] = "Red";
    test[1] = "Yellow";
    test[2] = "Blue";
    test[3] = "Purple";

    board.checkPlay(test);

    for(String color : test){
        System.out.println(color);
    }


   }
   
}
