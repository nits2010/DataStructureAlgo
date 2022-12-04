package DataStructureAlgo.Java.LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-03
 * Description:https://leetcode.com/problems/rotting-oranges/
 * In a given grid, each cell can have one of three values:
 * <p>
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 */
public class RottingOranges {


    int row[] = {0, 0, -1, 1};
    int col[] = {-1, 1, 0, 0};


    private int getIndex(int r, int c, int n, int m) {
        return r * m + c;
    }


    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        //if only one element in the grid then check it self
        if (n == 1 && m == 1) {
            int x = grid[0][0];
            return (x == 2 || x == 0) ? 0 : -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> timeFrame = new HashMap<>();
        int freshOranges = findAllRotten(grid, n, m, queue, timeFrame);

        int minTime = 0;

        while (!queue.isEmpty()) {

            int index = queue.poll();
            int i = index / m;
            int j = index % m;

            for (int it = 0; it < row.length; it++) {

                int rn = row[it] + i;
                int cn = col[it] + j;

                if (isValid(rn, cn, n, m)) {
                    if (grid[rn][cn] == 1) {
                        grid[rn][cn] = 2;
                        int nextIndex = getIndex(rn, cn, n, m);
                        queue.offer(nextIndex);
                        timeFrame.put(nextIndex, timeFrame.get(index) + 1);
                        minTime = timeFrame.get(nextIndex);
                        freshOranges--;
                    }
                }
            }

        }


        return freshOranges == 0 ? minTime : -1;

    }

    private boolean isValid(int r, int c, int n, int m) {
        if (r >= 0 && c >= 0 && r < n && c < m)
            return true;
        return false;
    }

    public int findAllRotten(int grid[][], int n, int m, Queue<Integer> queue, Map<Integer, Integer> timeFrame) {

        int freshOranges = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    int index = getIndex(i, j, n, m);
                    queue.offer(index);
                    timeFrame.put(index, 0);
                } else if (grid[i][j] == 1)
                    freshOranges++;
            }
        }
        return freshOranges;
    }

    public boolean allRotten(int grid[][], int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 1)
                    return false;
        }
        return true;
    }
}
