package Java.LeetCode.games.sudoku;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-17
 * Description: https://leetcode.com/problems/sudoku-solver/
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * <p>
 * <p>
 * A sudoku puzzle...
 * <p>
 * <p>
 * ...and its solution numbers marked in red.
 * <p>
 * Note:
 * <p>
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
public class SudokuSolver {

    static char[][] board() {
        char[][] board =
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };

        return board;
    }

    ;

    public static void main(String[] args) {
        test(board());
    }

    private static void test(char[][] board) {
        ISudokuSolver solver = new SudoSolverBacktrackingWithRestriction();
        solver.solveSudoku(board);
        System.out.println(GenericPrinter.toString(board));
    }
}

/**
 * 1. SudokuSolverBacktracking
 * 2. SudokuSolverBacktrackingV2
 * 3. SudoSolverBacktrackingWithRestriction
 */
interface ISudokuSolver {
    void solveSudoku(char[][] board);
}


/**
 * Runtime: 7 ms, faster than 53.81% of Java online submissions for Sudoku Solver.
 * Memory Usage: 38.5 MB, less than 15.79% of Java online submissions for Sudoku Solver.
 */
class SudokuSolverBacktracking implements ISudokuSolver {

    private int[] nextUnusedCell(char board[][]) {

        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                if (board[r][c] == '.')
                    return new int[]{r, c};


        return new int[]{-1, -1};

    }

    @Override
    public void solveSudoku(char[][] board) {

        if (board == null || board.length != 9 || board[0].length != 9)
            return;


        solveSudoku(board, board.length);

    }

    private boolean solveSudoku(char[][] board, int length) {

        int rc[] = nextUnusedCell(board);

        /**
         * Board solved
         */
        if (rc[0] == -1)
            return true;


        for (int n = 1; n <= length; n++) {
            char c = (char) (n + '0');

            if (validState(board, rc, c)) {
                board[rc[0]][rc[1]] = c;

                if (solveSudoku(board, length))
                    return true;

                board[rc[0]][rc[1]] = '.';
            }
        }

        return false;
    }

    private boolean validState(char[][] board, int[] rc, char n) {

        int r = rc[0];
        int c = rc[1];


        return validateRowCol(board, r, c, n) && validateBox(board, r, c, n);
    }

    private boolean validateBox(char[][] board, int r, int c, char n) {

        int boxLength = (int) Math.sqrt(board.length);
        int rowStart = r - r % boxLength;
        int colStart = c - c % boxLength;

        for (int ri = rowStart; ri < rowStart + boxLength; ri++)
            for (int ci = colStart; ci < colStart + boxLength; ci++)
                if (board[ri][ci] == n)
                    return false;


        return true;
    }

    private boolean validateRowCol(char[][] board, int r, int c, char n) {

        for (int i = 0; i < board.length; i++) {
            if (board[i][c] == n)
                return false;

            if (board[r][i] == n)
                return false;

        }

        return true;
    }


}


/**
 * 1. Remove multiple traverse for finding a space
 * 2. Instead of checking row and column and box separately, to it in one go
 * <p>
 * Runtime: 7 ms, faster than 53.81% of Java online submissions for Sudoku Solver.
 * Memory Usage: 38.5 MB, less than 15.79% of Java online submissions for Sudoku Solver.
 */
class SudokuSolverBacktracking2 implements ISudokuSolver {


    @Override
    public void solveSudoku(char[][] board) {

        if (board == null || board.length != 9 || board[0].length != 9)
            return;


        solveSudoku(board, board.length);

    }

    private boolean solveSudoku(char[][] board, int length) {

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == '.') {

                    for (char nc = '1'; nc <= '9'; nc++) {

                        if (validState(board, r, c, nc)) {
                            board[r][c] = nc;

                            if (solveSudoku(board, length))
                                return true;

                            board[r][c] = '.';
                        }
                    }

                    return false;

                }
            }
        }


        return true;
    }

    private boolean validState(char[][] board, int r, int c, char n) {

        return isValid(board, r, c, n);
    }


    private boolean isValid(char[][] board, int row, int col, char num) {
        int boxLength = (int) Math.sqrt(board.length);

        int rowStart = (row / boxLength) * boxLength; // (row / 3) * 3
        int colStart = (col / boxLength) * boxLength; // (col / 3) * 3

        // Block no. is i/3, first element is i/3*3
        for (int i = 0; i < 9; i++)
            if (board[i][col] == num || board[row][i] == num || board[rowStart + i / boxLength][colStart + i % boxLength] == num)
                return false;

        return true;
    }
}


/**
 * In this solution we'll restrict the recursion tree by only trying to valid possible number in a row / col and box
 * 1. Store all rows number , col numbers and box number in a set; This items we need to exclude
 * <p>
 * Runtime: 3 ms, faster than 96.57% of Java online submissions for Sudoku Solver.
 * Memory Usage: 35.1 MB, less than 71.93% of Java online submissions for Sudoku Solver.
 */
class SudoSolverBacktrackingWithRestriction implements ISudokuSolver {

    private static final int length = 9;

    /**
     * d[i][j] represent does j'th number is restricted to use or not at i'th place
     * <p>
     * as there will be 81 state with 9 different numbers.
     */
    private boolean row[][];
    private boolean col[][];
    private boolean box[][];
    private boolean solved = false;


    @Override
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != length || board[0].length != length)
            return;

        row = new boolean[length][length];
        col = new boolean[length][length];
        box = new boolean[length][length];
        solved = false;

        fillRestrictedNumbers(board, board.length);
        solveSudoku(board, 0);

    }

    private void fillRestrictedNumbers(char[][] board, int length) {

        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                if (board[r][c] != '.') {
                    int currentNum = board[r][c] - '1';

                    //row restriction ; we can't use this number in any of the row 0..9
                    row[r][currentNum] = true;

                    //col restriction ; we can't use this number in any of the col 0..9
                    col[c][currentNum] = true;

                    //box restriction ; we can't use this number in any of the box
                    int boxIndex = boxIndex(r, c);
                    box[boxIndex][currentNum] = true;
                }
            }
        }
    }


    private void solveSudoku(char[][] board, int n) {

        /**
         * We have placed all numbers
         */
        if (n == length * length) {
            solved = true;
            return;
        }

        int r = n / length;
        int c = n % length;
        int boxIndex = boxIndex(r, c);

        /**
         * Is this place available
         */
        if (board[r][c] == '.') {

            /**
             * Try all possible number
             */
            for (int num = 0; num < length; num++) {

                /**
                 * Is this number allowed in this row,col and box?
                 */
                if (!row[r][num] && !col[c][num] && !box[boxIndex][num]) {
                    //Allowed, then try

                    board[r][c] = (char) (num + '1');
                    row[r][num] = col[c][num] = box[boxIndex][num] = true;

                    solveSudoku(board, n + 1);

                    if (solved)
                        return;

                    /**
                     * Backtrack
                     */

                    board[r][c] = '.';
                    row[r][num] = col[c][num] = box[boxIndex][num] = false;
                }
            }
        } else
        /**
         * Above row and col is already been occupied
         */
            solveSudoku(board, n + 1);


    }

    private int boxIndex(int r, int c) {
        int boxLength = (int) Math.sqrt(length);

        int rowOfBox = (r / boxLength) * boxLength;
        int colOfBox = (c / boxLength) % boxLength;
        return rowOfBox + colOfBox;
    }
}