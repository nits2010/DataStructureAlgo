package Java.LeetCode;

import Java.HelpersToPrint.HelperToPrint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-01
 * Description: https://leetcode.com/problems/game-of-life/
 * <p>
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by
 * applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * Output:
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * <p>
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area
 * encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {


    public static void main(String[] args) {

        int[][] game = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        testUsingMemory(new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        });

        testMemoryLessProcessingMore(new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        });

        testLessMemoryLessProcessingMore(new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        });

    }

    private static void testUsingMemory(int[][] game) {
        GameOfLifeUsingMemory sol1 = new GameOfLifeUsingMemory();

        System.out.println("Given Game\n");
        HelperToPrint.print(game);


        System.out.println("\n After one iteration\n");
        sol1.gameOfLife(game);
        HelperToPrint.print(game);
    }

    private static void testMemoryLessProcessingMore(int[][] game) {
        GameOfLifeMemoryLessMoreProcessing sol1 = new GameOfLifeMemoryLessMoreProcessing();

        System.out.println("Given Game\n");
        HelperToPrint.print(game);


        System.out.println("\n After one iteration\n");
        sol1.gameOfLife(game);
        HelperToPrint.print(game);
    }

    private static void testLessMemoryLessProcessingMore(int[][] game) {
        GameOfLifeLessMemoryLessLessProcessing sol1 = new GameOfLifeLessMemoryLessLessProcessing();

        System.out.println("Given Game\n");
        HelperToPrint.print(game);


        System.out.println("\n After one iteration\n");
        sol1.gameOfLife(game);
        HelperToPrint.print(game);
    }
}

/**
 * Time: O(m*n*8) = O(m*n)
 * Space: O(mn)
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
 * Memory Usage: 35.1 MB, less than 100.00% of Java online submissions for Game of Life
 */
class GameOfLifeUsingMemory {

    public void gameOfLife(int[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0)
            return;

        int boardCopy[][] = getCopy(board);

        int row[] = {0, 0, -1, 1, -1, -1, 1, 1};
        int col[] = {-1, 1, 0, 0, -1, 1, -1, 1};

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {

                int one = 0;
                for (int k = 0; k < row.length; k++) {

                    int r = i + row[k];
                    int c = j + col[k];

                    if (isSafe(r, c, board.length, board[0].length)) {

                        if (boardCopy[r][c] == 1)
                            one++;

                    }
                }


                if (boardCopy[i][j] == 0) {
                    board[i][j] = (one == 3) ? 1 : board[i][j];
                }

                if (boardCopy[i][j] == 1) {

                    board[i][j] = (one < 2) ? 0 : board[i][j];
                    board[i][j] = (one == 2 || one == 3) ? board[i][j] : 0;
                    board[i][j] = (one > 3) ? 0 : board[i][j];

                }
            }
        }

    }

    private boolean isSafe(int r, int c, int n, int m) {

        if (r >= 0 && r < n && c >= 0 && c < m)
            return true;
        return false;
    }

    private int[][] getCopy(int board[][]) {

        int boardCopy[][] = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)

                boardCopy[i][j] = board[i][j];

        return boardCopy;


    }
}


/**
 * Time: O(2*m*n*8) = O(m*n)
 * Space: O(1)
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
 * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Game of Life.
 */
class GameOfLifeMemoryLessMoreProcessing {

    private final int BIG_ZERO = Integer.MIN_VALUE;
    private final int BIG_ONE = Integer.MAX_VALUE;

    public void gameOfLife(int[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0)
            return;

        set(board);
        reset(board);

    }

    private void set(int board[][]) {
        int row[] = {0, 0, -1, 1, -1, -1, 1, 1};
        int col[] = {-1, 1, 0, 0, -1, 1, -1, 1};

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {

                int one = 0;
                for (int k = 0; k < row.length; k++) {

                    int r = i + row[k];
                    int c = j + col[k];

                    if (isSafe(r, c, board.length, board[0].length)) {

                        if (board[r][c] == 1 || board[r][c] == BIG_ZERO) //as the old 1 might have changed to BIG_ZERO
                            one++;

                    }
                }


                if (board[i][j] == 0) {
                    board[i][j] = (one == 3) ? BIG_ONE : board[i][j];
                }

                if (board[i][j] == 1) {

                    board[i][j] = (one < 2) ? BIG_ZERO : board[i][j];
                    board[i][j] = (one == 2 || one == 3) ? board[i][j] : BIG_ZERO;
                    board[i][j] = (one > 3) ? BIG_ZERO : board[i][j];

                }
            }
        }
    }


    private void reset(int board[][]) {
        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == BIG_ZERO)
                    board[i][j] = 0;

                if (board[i][j] == BIG_ONE)
                    board[i][j] = 1;

            }
        }
    }

    private boolean isSafe(int r, int c, int n, int m) {

        if (r >= 0 && r < n && c >= 0 && c < m)
            return true;
        return false;
    }

}

/**
 * This idea from https://leetcode.com/problems/game-of-life/discuss/73217/Infinite-board-solution/201780
 * <p>
 * Here we first need to count the live cells, then we'll find out does this cell will going to live or not based on rules
 */
class GameOfLifeLessMemoryLessLessProcessing {
    private static class Coord {
        int i;
        int j;
        private Coord(int i, int j) {
            this.i = i;
            this.j = j;
        }
        public boolean equals(Object o) {
            return o instanceof Coord && ((Coord)o).i == i && ((Coord)o).j == j;
        }
        public int hashCode() {
            int hashCode = 1;
            hashCode = 31 * hashCode + i;
            hashCode = 31 * hashCode + j;
            return hashCode;
        }
    }
    public void gameOfLife(int[][] board) {
        Set<Coord> live = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (board[i][j] == 1) {
                    live.add(new Coord(i,j));
                }
            }
        }
        live = gameOfLife(live);
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                board[i][j] = live.contains(new Coord(i,j))?1:0;
            }
        };

    }

    private Set<Coord> gameOfLife(Set<Coord> live) {
        Map<Coord,Integer> neighbours = new HashMap<>();
        for (Coord cell : live) {
            for (int i = cell.i-1; i<cell.i+2; i++) {
                for (int j = cell.j-1; j<cell.j+2; j++) {
                    if (i==cell.i && j==cell.j) continue;
                    Coord c = new Coord(i,j);
                    if (neighbours.containsKey(c)) {
                        neighbours.put(c, neighbours.get(c) + 1);
                    } else {
                        neighbours.put(c, 1);
                    }
                }
            }
        }
        Set<Coord> newLive = new HashSet<>();
        for (Map.Entry<Coord,Integer> cell : neighbours.entrySet())  {
            if (cell.getValue() == 3 || cell.getValue() == 2 && live.contains(cell.getKey())) {
                newLive.add(cell.getKey());
            }
        }
        return newLive;
    }

}