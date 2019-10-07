package Java.LeetCode.island;

import Java.helpers.GenericPrinter;
import Java.nonleetcode.UnionFindDisjointSets;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-20
 * Description: http://leetcode.liangjiateng.cn/leetcode/number-of-islands-ii/description
 * 305.Number of Islands II
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns
 * the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 * <p>
 * Example:
 * <p>
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * <p>
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * <p>
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * <p>
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * <p>
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * <p>
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * <p>
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * Follow up:
 * <p>
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 * <p>
 * {@link NumberIslands}
 */
public class NumberIslandsII {

    public static void main(String[] args) {
        test(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}});
        test(3, 3, new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 2}});
        test(3, 3, new int[][]{{0, 1}, {0, 1}, {2, 1}, {2, 2}}); //Duplicate land entry
        test(4, 4, new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 2}, {3, 3}});
        test(5, 4, new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 2}, {3, 3}, {4, 3}});

    }

    private static void test(int m, int n, int[][] positions) {
        System.out.println("\nm : " + m + " n: " + n + " position :" + GenericPrinter.toString(positions));
        NumberIslandsIIBuildAndCount buildAndCount = new NumberIslandsIIBuildAndCount();
        NumberIslandsIIUnionFind unionFind = new NumberIslandsIIUnionFind();

        System.out.println("build and count" + buildAndCount.numIslands(m, n, positions));
        System.out.println("Union & Find " + unionFind.numIslands(m, n, positions));
    }
}

/**
 * this is Simple, it utilize the Island counter {@link NumberIslands} as sub-routine
 * Algo:
 * 1. Create a empty grid
 * 2. Iterate over all position of land
 * 3. For each position, create a land and count how many island are there because of this land {either this land create a new island or it is connected to old island} [use island counter]
 * <p>
 * Complexity:
 * k-> size of position
 * <p>
 * Step 1: O(1)/O(m*n)
 * Step 2: O(k)
 * Step 3: O(m*n) / O(m*n) or O(1)
 * <p>
 * Overall:
 * Time: O(k * m * n )
 * Space: O(m*n)
 */
class NumberIslandsIIBuildAndCount {

    public List<Integer> numIslands(int m, int n, int[][] positions) {

        //No island to form
        if (m == 0 || n == 0)
            return Collections.EMPTY_LIST;

        /**
         * No land to make
         */
        if (positions == null || positions.length == 0)
            return Collections.EMPTY_LIST;

        /**
         * Iteratively Build the grid and keep counting
         */
        char[][] grid = new char[m][n];

        NumberIslandsDFS islandCounter = new NumberIslandsDFS();
        List<Integer> solution = new ArrayList<>();

        //O(k)
        for (int pos[] : positions) {

            grid[pos[0]][pos[1]] = '1';
            solution.add(islandCounter.numIslands(grid));

        }

        return solution;


    }
}

/**
 * Above algorithm first build the grid, so land, then count the island. There are two important thing
 * 1. The reason being that for each position either create a new island
 * or
 * 2. get connected to old island.
 * <p>
 * Which turns out, if we somehow efficiently can find that two island are connected or not, then we can simply discard the new land as potential new island.
 * Since, an island can be connected through 4 directions {horizontal and vertical} i.e. means we need to attach those valid position to old island as well to correctly evaluate
 * the new position as potential island.
 * <p>
 * Union-Find {@link UnionFindDisjointSets}
 * -----------
 * Using union-Find we can find out that two points are connected or not by checking there parent.
 * <p>
 * <p>
 * Algorithm:
 * 1. create the possible number of space available as empty parent {our water spaces}
 * 2. For each position evaluate following things
 * *  2.1 : does this land is being connected to any previous island ? if so then we should not count this as island.
 * * 2.2 : Also do same for all 4 direction
 * <p>
 * O(k* log(m*n)) / O (m*n)
 * <p>
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.32.html
 * https://segmentfault.com/a/1190000016587068
 */
class NumberIslandsIIUnionFind {

    private static class Set {
        int id;
        int rank;

        public Set(int id, int rank) {
            this.id = id;
            this.rank = rank;
        }
    }

    class UnionFind {

        private Set parent[];

        public UnionFind(int n) {
            parent = new Set[n];

            for (int i = 0; i < n; i++) {
                parent[i] = new Set(i, 1);
            }
        }

        /**
         * O(log(n)) Because of Path compression
         *
         * @param i
         * @return
         */
        public int find(int i) {
            if (parent[i].id == i)
                return i;

            return parent[i].id = find(parent[i].id); //path compression
        }

        /**
         * O(log(n)) Find x + O(log(n)) Find y +  O(1) union = O(log(n))
         * * @param x
         *
         * @param y
         * @return True : if they have different parent otherwise false
         */
        public void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);

            if (p1 != p2) {
                unionByParent(p1, p2);
            }
        }

        private void unionByParent(int p1, int p2) {
            if (parent[p1].rank > parent[p2].rank)
                parent[p2].id = p1;//make p1 as parent of p2
            else if (parent[p1].rank < parent[p2].rank)
                parent[p1].id = p2;//make p2 as parent of p1
            else {
                parent[p2].id = p1; //make p1 as parent of p2
                parent[p2].rank++;
            }
        }

    }


    public List<Integer> numIslands(int m, int n, int[][] positions) {
        //No island to form
        if (m == 0 || n == 0)
            return Collections.EMPTY_LIST;

        /**
         * No land to make
         */
        if (positions == null || positions.length == 0)
            return Collections.EMPTY_LIST;

        /**
         * Out land grid
         */
        final int grid[][] = new int[m][n];

        final UnionFind unionFind = new UnionFind(m * n);


        final List<Integer> solution = new ArrayList<>();

        int islandCount = 0;

        final int row[] = {0, 0, 1, -1};
        final int col[] = {-1, 1, 0, 0};

        for (int pos[] : positions) { //O(k)

            int i = pos[0];
            int j = pos[1];

            /**
             * If there is already a land; When duplicate entry are there in position metric
             */
            if (grid[i][j] == 1) {
                solution.add(islandCount);
                continue;
            }


            int landIndex = n * i + j; //mxn matrix as 2D translate to i*n + j as 1D

            /**
             * Create an Land
             */
            grid[i][j] = 1;

            boolean isConnectedIsland = false;

            for (int k = 0; k < 4; k++) { //O(4)
                int r = i + row[k];
                int c = j + col[k];

                /**
                 * If this is a valid position in our land grid and is there any land
                 */
                if (isSafe(r, c, m, n) && grid[r][c] == 1) {
                    isConnectedIsland = true;

                    /**
                     * Check does this new land attach to any old island
                     */

                    int neighbourIndex = r * n + c;

                    unionFind.union(landIndex, neighbourIndex); //O(log(m*n))


                }


            }


            if (isConnectedIsland)
                solution.add(islandCount);
            else
                solution.add(++islandCount);


        }
        return solution;


    }

    private boolean isSafe(int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n)
            return false;
        return true;
    }

}