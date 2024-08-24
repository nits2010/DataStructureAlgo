package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-25
 * Description: https://leetcode.com/problems/bomb-enemy
 * http://leetcode.liangjiateng.cn/leetcode/bomb-enemy/description
 * <p>
 * 361.Bomb Enemy
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 * <p>
 * Example:
 * <p>
 * Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 * Explanation: For the given grid,
 * <p>
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * <p>
 * Placing a bomb at (1,1) kills 3 enemies.
 * <p>
 * similar idea {@link DataStructureAlgo.Java.LeetCode.LargetstRectangle.MaximumSizeSquareMatrix} to cache row and column
 */
public class BombEnemy {


    public static void main(String[] args) {

        test(new char[][]{
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'}}, 3);

        test(new char[][]{
                {'0', 'E', 'E', '0'},
                {'E', 'W', 'W', 'E'},
                {'0', 'E', 'E', '0'}}, 3);

        test(new char[][]{
                {'0', 'E', 'W', 'E'},
                {'E', 'W', 'W', 'E'},
                {'0', 'E', 'E', '0'}}, 4);


        test(new char[][]{
                {'F', 'E', 'W', '0'},
                {'E', 'W', 'W', 'W'},
                {'F', 'E', 'W', '0'}}, 0);
    }

    private static void test(char[][] grid, int expected) {
        System.out.println("\ngrid :\n" + CommonMethods.toString(grid) + " expected :" + expected);

        BombEnemyBruteForce enemyBruteForce = new BombEnemyBruteForce();
        BombEnemyMemo bombEnemyMemo = new BombEnemyMemo();

        System.out.println("Brute force :" + enemyBruteForce.maxKilledEnemies(grid));
        System.out.println("Optimized :" + bombEnemyMemo.maxKilledEnemies(grid));
    }
}


/**
 * Instead of searching again n again in every row and col, if we pre-compute for Row[i] and Col[j]
 * then for any empty cell in the same row 'i' but different column grid[i][k] can easily use the row[i] to get the Enemy of i'th row
 * Similarly for same column 'j' but different row grid[k][j] can easily use the col[j] to get the Enemy of j'th col
 * <p>
 * This reduce the overall complexity to O(m*n) as we only touch a element single time
 */
class BombEnemyMemo {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int maxKills = Integer.MIN_VALUE;

        int row[] = new int[grid.length];
        Arrays.fill(row, -1); //to indicate that this row[i] has not been explored yet

        int col[] = new int[grid[0].length];
        Arrays.fill(col, -1); //to indicate that this col[i] has not been explored yet

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '0') {

                    if (row[i] == -1)
                        row[i] = rowKill(grid, i, j);

                    if (col[j] == -1)
                        col[j] = colKill(grid, i, j);

                    maxKills = Math.max(maxKills, row[i] + col[j]);


                }
            }
        }

        return maxKills;
    }

    //O(n)
    private int colKill(char[][] grid, int r, int c) {
        int count = 0;

        for (int i = r; i < grid.length; i++)
            if (grid[i][c] == 'W')
                break;
            else if (grid[i][c] == 'E')
                count++;

        if (r != 0) {
            for (int i = r; i >= 0; i--)
                if (grid[i][c] == 'W')
                    break;
                else if (grid[i][c] == 'E')
                    count++;
        }

        return count;
    }

    //O(m)
    private int rowKill(char grid[][], int r, int c) {
        int count = 0;

        for (int i = c; i < grid[0].length; i++)
            if (grid[r][i] == 'W')
                break;
            else if (grid[r][i] == 'E')
                count++;

        if (c != 0) {
            for (int i = c; i >= 0; i--)
                if (grid[r][i] == 'W')
                    break;
                else if (grid[r][i] == 'E')
                    count++;
        }

        return count;
    }

}


/*

   Since we need to find how many 'E' are there in each row and column, one way would be

   Brute Force:
   1. For every empty cell '0' find how many E are in Row and column. Maintain a variable max to find the max number so far
   Complexity:
   In Worst case the grid has only Empty cells which makes run outer loops to (m*n) times and for each empty cell
   it will run (m+n) times to find in row and column.
   Hence overall Complexity O((m*n) * (m+n) )

 */
class BombEnemyBruteForce {

    public int maxKilledEnemies(char[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int maxKills = Integer.MIN_VALUE;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '0') {

                    int rowKill = rowKill(grid, i, j);
                    int colKill = colKill(grid, i, j);
                    maxKills = Math.max(maxKills, rowKill + colKill);

                }

            }
        }
        return maxKills;
    }

    //O(n)
    private int colKill(char[][] grid, int r, int c) {
        int count = 0;

        for (int i = r; i < grid.length; i++)
            if (grid[i][c] == 'W')
                break;
            else if (grid[i][c] == 'E')
                count++;

        if (r != 0) {
            for (int i = r; i >= 0; i--)
                if (grid[i][c] == 'W')
                    break;
                else if (grid[i][c] == 'E')
                    count++;
        }

        return count;
    }

    //O(m)
    private int rowKill(char grid[][], int r, int c) {
        int count = 0;

        for (int i = c; i < grid[0].length; i++)
            if (grid[r][i] == 'W')
                break;
            else if (grid[r][i] == 'E')
                count++;

        if (c != 0) {
            for (int i = c; i >= 0; i--)
                if (grid[r][i] == 'W')
                    break;
                else if (grid[r][i] == 'E')
                    count++;
        }

        return count;
    }


}