/**
 * Author Tom Karlsson
 * Created 2021-10-16
 * 
 * This is a game of Mastermind. 
 * 
 */
import java.util.Scanner;

public class Game {
    private static final int ROUNDS = 12;
    private int COLUMNS;
    private int round;
    private Board board;
    private boolean won;
    private boolean lost;

    public Game(int col){
        this.COLUMNS = col;
        this.round = 0;
        this.board = new Board();
    }
    public Game(){
        this(4);
    }

    public void play(String[] guesses){
        if(!this.won && !this.lost){
            this.won = this.board.checkPlay(guesses);
            int b = this.board.blacks();
            int w = this.board.whites();

            System.out.print("Round " + ++this.round + ": ");
            while(b-- > 0){ System.out.print("[X]"); }
            while(w-- > 0){ System.out.print("[0]"); }

            System.out.println("");
        }
        if(won)
            System.out.println("YOU WON!");
        else if(lost = (this.round >= ROUNDS))
            System.out.println("YOU LOST.");
        System.out.println("");
    }

    public void cheat(){
        this.board.cheatPrint();
    }

    public static void howToPlay(){
        System.out.println("_______________________________________________________");
        System.out.println("WELCOME TO MASTER OF PEGS!");
        System.out.println("Which four pegs are hidden?");
        System.out.println("Choose from Red, Yellow, Green, Blue, Brown and Purple.");
        System.out.println("Enter four pegs like this: Red Green Brown Red");
        System.out.println("[X][0][0] - This means that one peg is in the correct place, \n" +
         "two pegs have the right color but are in the wrong place and" +
         "one peg is of the wrong color.");
        System.out.println("Good luck!");
        System.out.println("_______________________________________________________");

    }

    public static void main(String[] args){
        try{
            Scanner input = new Scanner(System.in);

            howToPlay();
            Game game = new Game();
            int option = 0;
            while(option != 1){
                option = 0;
                String[] guesses = new String[game.COLUMNS];
                for(int i = 0; i < game.COLUMNS; i++){
                    guesses[i] = input.next().toLowerCase();
                    if (guesses[i].equals("q") | guesses[i].equals("quit") | guesses[i].equals("exit")){
                        option = 1;
                        break;
                    }
                    if(guesses[i].equals("cheat")){
                        game.cheat();
                        option = 2;
                        break;
                    }
                }
                // Execute game order
                if(option == 0){
                    game.play(guesses);
                }


                if(game.won | game.lost){
                    while(true){
                        System.out.println("Play again y/n ?");
                        String choice = input.next().toLowerCase();
                        if(choice.equals("y")){
                            game = new Game();
                            howToPlay();
                            option = 0;
                            break;
                        }
                        else if(choice.equals("n")){
                            option = 1;
                            break;
                        }
                        
                    }

                }
            }

            input.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
