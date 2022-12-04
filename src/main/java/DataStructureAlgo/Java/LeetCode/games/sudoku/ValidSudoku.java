package DataStructureAlgo.Java.LeetCode.games.sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-17
 * Description: https://leetcode.com/problems/valid-sudoku/
 * <p>
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * A partially filled sudoku which is valid.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 * <p>
 * Input:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * <p>
 * <p>
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class ValidSudoku {

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

    static char[][] board2() {
        char[][] board =
                {
                        {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
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

    public static void main(String[] args) {
        test(board());
        test(board2());
    }

    private static void test(char[][] board) {
        ValidSudokuArrayHash arrayHash = new ValidSudokuArrayHash();
        ValidSudokuHash hash = new ValidSudokuHash();
        System.out.println("array hash: " + arrayHash.isValidSudoku(board));
        System.out.println(" hash: " + hash.isValidSudoku(board));
    }
}

/**
 * Runtime: 2 ms, faster than 89.51% of Java online submissions for Valid Sudoku.
 * Memory Usage: 43.2 MB, less than 95.65% of Java online submissions for Valid Sudoku.
 */
class ValidSudokuArrayHash {

    private static final int length = 9;

    /**
     * d[i][j] represent does j'th number is restricted to use or not at i'th place
     * <p>
     * as there will be 81 state with 9 different numbers.
     */
    private boolean row[][];
    private boolean col[][];
    private boolean box[][];

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != length || board[0].length != length)
            return true;

        row = new boolean[length][length];
        col = new boolean[length][length];
        box = new boolean[length][length];

        for (int r = 0; r < length; r++) {

            for (int c = 0; c < length; c++) {

                if (board[r][c] != '.') {

                    int num = board[r][c] - '1';
                    int boxIndex = boxIndex(r, c);

                    /**
                     * Has we saw same number already?
                     */
                    if (row[r][num] || col[c][num] || box[boxIndex][num])
                        return false;

                    /**
                     * Now we have seen this number
                     */
                    row[r][num] = col[c][num] = box[boxIndex][num] = true;
                }
            }
        }

        return true;

    }


    private int boxIndex(int r, int c) {
        int boxLength = (int) Math.sqrt(length);

        int rowOfBox = (r / boxLength) * boxLength;
        int colOfBox = (c / boxLength) % boxLength;
        return rowOfBox + colOfBox;
    }
}


/**
 * Runtime: 4 ms, faster than 48.51% of Java online submissions for Valid Sudoku.
 * Memory Usage: 44.6 MB, less than 55.07% of Java online submissions for Valid Sudoku.
 */
class ValidSudokuHash {

    private static final int length = 9;


    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != length || board[0].length != length)
            return true;
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {

                char number = board[i][j];

                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;

    }


}

