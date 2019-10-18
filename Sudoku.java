import java.util.*;
import java.lang.Math.pow;

public class Sudoku {

    public static int[][] boardToSolve = {
        //Board for solving
        {8, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 3, 6, 0, 0, 0, 0, 0},
        {0, 7, 0, 0, 9, 0, 2, 0, 0},
        {0, 5, 0, 0, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 7, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 3, 0},
        {0, 0, 1, 0, 0, 0, 0, 6, 8},
        {0, 0, 8, 5, 0, 0, 0, 1, 0},
        {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    //Declare 2D array for solver board
    private int[][] board;
    public static final int empty = 0; //Empty will be 0
    public static final int size = 9; //9x9

    public Sudoku(int[][] board) {
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    private boolean isInRow(int row, int number) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == number)
                return true; 
        }
        return false;
    }
    private boolean isInCol(int col, int number) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] == number)
                return true; 
        }
        return false;
    }
    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    //Main solving algorithm
    public boolean solve() {
        //Check in the row
        for (int row = 0; row < size; row++) {
            //Check in the column
            for (int col = 0; col < size; col++) {
                //If the selected cell is empty
                if (board[row][col] == empty) {
                    //Increase the number until it's ok
                    for (int number = 1; number <= size; number++) {
                        if (isOk(row, col, number)) {
                            //If it is ok, put it there
                            board[row][col] = number;
                            if (solve()) { 
                            return true;
                            } else {
                            board[row][col] = empty;
                            }
                        }
                    }
                    return false; 
                }
            }
        }
        return true; 
    }
    
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Start a timer
        double startTime = System.nanoTime();
        //Initialize the solve method
        Sudoku sudoku = new Sudoku(boardToSolve);
        //Print the board that is going to be solved 
        System.out.println("Sudoku grid to be solved.");
        //Display the board unsolved
        sudoku.display();
        //If it is solved, display the new board
        if (sudoku.solve()) {
            //"Stop" the timer
            double endTime = System.nanoTime();
            //Get the time
            double nanoTime = (endTime - startTime);
            double secondsConversion = Math.pow(10, -9); //10^-9
            //Convert from nanoseconds to seconds
            double timeToSolve = nanoTime * secondsConversion;
            System.out.println("Solved in " + timeToSolve + " seconds!");
            sudoku.display();
        }
        //If not solved 
        else {
            System.out.println("Unsolvable");
        }
    }
}