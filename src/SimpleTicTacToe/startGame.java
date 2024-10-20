package SimpleTicTacToe;
import java.util.*;

public class startGame {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static int[][] board = new int[ROWS][COLS];
    public static int empty_cell = 2;

    public static void initialize(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                board[row][col] = empty_cell;
            }
        }
    }
}
