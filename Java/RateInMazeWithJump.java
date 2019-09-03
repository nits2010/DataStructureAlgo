package Java;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description: https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/
 * <p>
 * This is the variation of Rat in Maze
 * <p>
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 * A rat starts from source and has to reach destination. The rat can move only in two directions: forward and down.
 * In the maze matrix, 0 means the block is dead end and non-zero number means the block can be used in the path from source to destination.
 * The non-zero value of mat[i][j] indicates number of maximum jumps rat can make from cell mat[i][j].
 * <p>
 * In this variation, Rat is allowed to jump multiple steps at a time instead of 1.
 * <p>
 * Examples
 * Examples:
 * <p>
 * Input :
 * { {2, 1, 0, 0},
 * {3, 0, 0, 1},
 * {0, 1, 0, 1},
 * {0, 0, 0, 1}
 * }
 * Output :
 * { {1, 0, 0, 0},
 * {1, 0, 0, 1},
 * {0, 0, 0, 1},
 * {0, 0, 0, 1}
 * }
 * <p>
 * Explanation
 * Rat started with M[0][0] and can jump upto 2 steps right/down.
 * Let's try in horizontal direction -
 * M[0][1] won't lead to solution and M[0][2] is 0 which is dead end.
 * So, backtrack and try in down direction.
 * Rat jump down to M[1][0] which eventually leads to solution.
 * <p>
 * Input : {
 * {2, 1, 0, 0},
 * {2, 0, 0, 1},
 * {0, 1, 0, 1},
 * {0, 0, 0, 1}
 * }
 * Output : Solution doesn't exist
 */
public class RateInMazeWithJump {

    private static boolean isCorrect(int result[][], int solution[][]) {
        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[0].length; j++)
                if (result[i][j] != solution[i][j])
                    return false;
        }

        return true;
    }


    public static void main(String args[]) {

        test(new int[][]{{2, 1, 0, 0}, {3, 0, 0, 1}, {0, 1, 0, 1}, {0, 0, 0, 1}}, new int[][]{{1, 0, 0, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}});


    }

    private static void test(int[][] maze, int[][] expected) {
        int n = maze.length, m = maze[0].length;
        System.out.println("\n Maze :\n" + Printer.toString(maze) + " \n expected :\n" + Printer.toString(expected));
        int result[][] = rateInMazeWithJump(maze, 0, 0, n - 1, m - 1);
        System.out.println("\n Obtained :\n" + Printer.toString(result) + "\nMatch :" + isCorrect(result, expected));

    }


    private static int[][] rateInMazeWithJump(int[][] maze, int sx, int sy, int dx, int dy) {


        int result[][] = new int[maze.length][maze[0].length];

        rateInMazeWithJump(maze, sx, sy, dx, dy, result);
        return result;


    }


    private static boolean rateInMazeWithJump(int[][] maze, int sx, int sy, int dx, int dy, int[][] result) {

        //We reached the destination
        if (sx == dx && sy == dy) {
            result[sx][sy] = 1;
            return true;
        }

        //no more element to process
        if (sx >= maze.length || sy >= maze[0].length) {
            return false;
        }


        //Check if its safe to reach this cell
        if (isSafe(maze, sx, sy, result)) {

            //try this cell to destination
            result[sx][sy] = 1;

            //try all cells from this cell to destination

            int maxJumpLength = maze[sx][sy];
            // try from 1 jump to number of jump defined by maze[sx][sy]
            for (int jumpLength = 1; jumpLength <= maxJumpLength; jumpLength++) {


                //try right cells;; horizontal
                if (rateInMazeWithJump(maze, sx + jumpLength, sy, dx, dy, result))
                    return true;

                //if not success then try down: vertical
                if (rateInMazeWithJump(maze, sx, sy + jumpLength, dx, dy, result))
                    return true;

            }

            //backtrack to other cells if not reached destination
            result[sx][sy] = 0;
            return false;


        }
        return false;

    }

    private static boolean isSafe(int[][] maze, int sx, int sy, int result[][]) {

        if (sx <= maze.length && sy <= maze[0].length && result[sx][sy] == 0)
            return true;
        return false;
    }


}
