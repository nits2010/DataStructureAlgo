package Java.LeetCode.graph;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-06
 * Description: https://leetcode.com/problems/cherry-pickup/
 * 741. Cherry Pickup [Hard]
 * <p>
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 * Your task is to collect maximum number of cherries possible by following the rules below:
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 * Example 1:
 * <p>
 * Input: grid =
 * [[0, 1, -1],
 * [1, 0, -1],
 * [1, 1,  1]]
 * Output: 5
 * Explanation:
 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 * Note:
 * grid is an N by N 2D array, with 1 <= N <= 50.
 * Each grid[i][j] is an integer in the set {-1, 0, 1}.
 * It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 * <p>
 * Combination of {@link MinimumPathSum} & {@link Java.LeetCode.graph.unique.paths.UniquePathsII}
 *
 * [UBER]
 */
public class CherryPickup {

    public static void main(String[] args) {
//        test(new int[][]
//                {
//                        {0, 1, -1},
//                        {1, 0, -1},
//                        {1, 1, 1}
//                }, 5);

        test(new int[][]
                {
                        {0, 1, -1},
                        {1, 5, 1},
                        {1, 1, 8}
                }, 18);
    }

    private static void test(int[][] grid, int expected) {
        System.out.println("\n grid :\n" + Printer.toString(grid) + " expected :" + expected);
        CherryPickupDPBottomUp bottomUp = new CherryPickupDPBottomUp();
        System.out.println(" Bottom up:" + bottomUp.cherryPickup(grid));
    }
}

//https://leetcode.com/problems/cherry-pickup/discuss/109903/Step-by-step-guidance-of-the-O(N3)-time-and-O(N2)-space-solution
class CherryPickupDPBottomUp {


    public int cherryPickup(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == -1 || grid[m - 1][n - 1] == -1)
            return 0;

        return 0;


    }


}