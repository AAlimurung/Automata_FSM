package SimpleTicTacToe;
import javax.sound.midi.Soundbank;
import java.util.*;

public class Main {
    //board
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static int[][] board = new int[ROWS][COLS];

    //plays
    /*
     * cross_play is X
     * zero_play is 0
     * */
    public static final int cross_play = 0;
    public static final int zero_play = 1;
    public static final int empty_cell = 2;
    public static int currentPlayer;

    public static final int playerTurn = 0;
    public static final int draw = 1;
    public static final int cross_won = 2;
    public static final int zero_won = 3;
    public static int currentGameState;
    public static boolean gameEnd = false;

    public static Scanner sc = new Scanner(System.in);

    public static void initialize(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                board[row][col] = empty_cell;
            }
        }
        currentPlayer = cross_play; //plays X
        currentGameState = playerTurn;
    } //end

    public static void inGame(){
        boolean input = false;

        do{
            if(currentPlayer == cross_play){
                System.out.println("Player 1's turn");
                System.out.print("Enter move: row[1-3] col[1-3]: ");
            }else{
                System.out.println("Player 1's turn");
                System.out.print("Enter move: row[1-3] col[1-3]: ");
            }
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if(row >=0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == empty_cell){
                currentGameState = update_game_board(currentPlayer, row, col);
                input = true;
            }else{
                System.out.println("Tile is not valid. Choose between X or O.");
            }
        }while(!input);
    }

    public static int update_game_board(int player, int target_row, int target_col){
        board[target_row][target_col] = player;

        if(board[target_row][0]==player && board[target_row][1]==player && board[target_row][2]==player
            || board[0][target_col]==player && board[1][target_col]==player && board[2][target_col]==player
                || target_row == target_col && board[0][0]==player && board[1][1]==player
                    && board[2][2]==player || target_row + target_col == 2 && board[0][2]==player
                    && board[1][1]==player && board[2][0]==player
        ){
            if(player==cross_play)
                return cross_won;
            else
                return zero_won;

//            return (player==cross_play) ? cross_won : zero_won;
        }else{
            for(int row=0; row < ROWS; ++row){
                for(int col=0; col < COLS; ++col){
                    if(board[row][col] == empty_cell){
                        return playerTurn;
                    }
                }
            }
            return draw;
        }
    }

    public static void boardCells(int cell){
        switch (cell){
            case cross_play:
                System.out.println("X"); //play X on board
                break;
            case zero_play:
                System.out.println("O"); //play O on board
                break;
            case empty_cell:
                System.out.println(" ");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cell);
        }
    }

    public static void setBoard(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                boardCells(board[row][col]);
                if(col != COLS-1)
                    System.out.print("|");
            }
            System.out.println();
            if(row != ROWS-1)
                System.out.println("----------");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Just a simple tictactoe");
        //do{
            initialize();
            do{
                inGame();
                setBoard();
                if(currentGameState == cross_won){
                    System.out.println("Player 'X' won!");
                } else if (currentGameState == zero_won) {
                    System.out.println("Player 'O' won!");
                } else if (currentGameState == draw){
                    System.out.println("Draw!");
                }

                currentPlayer = (currentPlayer == cross_play) ? zero_play : cross_play;
            }while(currentGameState==playerTurn);
            //for repeated gameplay
//        System.out.println("Play again? (y/n): ");
//        char ch = sc.next().charAt(0);
//        if(ch!='y' && ch!='Y'){
//            System.out.println("Exiting game...");
//            System.exit(0);
//        }
        //}while(true);
    }
}
