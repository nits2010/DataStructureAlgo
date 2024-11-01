package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.WallsGate._286;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 9/14/2024
 * Question Category: 286. Walls and Gates
 * Description:
 * https://leetcode.com/problems/walls-and-gates/ ,
 * https://leetcode.ca/all/286.html
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example:
 *  Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *
 *
 *   Constraints:
 *
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 2^31 - 1.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.WallsGates}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @LeetCodeLockedProblem
 * @PremimumQuestion
 * @Array
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @UnionFind
 * @Matrix
 * @Graph
 *
 * <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @ByteDance
 * @Facebook
 * @Google
 * @Microsoft
 * @Uber
 * <p><p>
 *
 * @Editorial https://leetcode.ca/2016-09-11-286-Walls-and-Gates/
 */
public class WallsAndGates_286 {

    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        boolean test = true;
        test &= test(new int[][]{{INF, -1, 0, INF,}, {INF, INF, INF, -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}},
                new int[][]{{3, -1, 0, 1}, {2, 2, 1, -1}, {1, -1, 2, -1}, {0, -1, 3, 4}});
        test &= test(new int[][]{{-1}}, new int[][]{{-1}});
        test &= test(new int[][]{{-1, 0}}, new int[][]{{-1, 0}});
        test &= test(new int[][]{{-1, 0, INF}}, new int[][]{{-1, 0, 1}});
        test &= test(new int[][]{{0, -1, INF}}, new int[][]{{0, -1, INF}});
        test &= test(new int[][]{{0, -1, INF}, {0, INF, INF}}, new int[][]{{0, -1, 3}, {0, 1, 2}});
        CommonMethods.printAllTestOutCome(test);


    }

    private static boolean test(int[][] rooms, int[][] expected) {
        System.out.println("--------------------------------");
        System.out.println("Input: rooms = \n" + CommonMethods.toString(rooms));
        System.out.println("Expected : \n" + CommonMethods.toString(expected));

        WallsGatesDFS wallsGatesDFS = new WallsGatesDFS();
        int[][] roomsCopy = CommonMethods.copyOf(rooms);
        wallsGatesDFS.wallsAndGates(roomsCopy);
        boolean dfsResult = CommonMethods.equals(roomsCopy, expected);
        System.out.println("\n DFS rooms : \n" + CommonMethods.toString(roomsCopy) + " Result : " + (dfsResult ? "PASS" : "FAIL"));


        WallsGatesBFS wallsGatesBFS = new WallsGatesBFS();
        roomsCopy = CommonMethods.copyOf(rooms);
        wallsGatesBFS.wallsAndGates(roomsCopy);
        boolean bfsResult = CommonMethods.equals(roomsCopy, expected);
        System.out.println("\n BFS rooms : \n" + CommonMethods.toString(roomsCopy) + " Result : " + (dfsResult ? "PASS" : "FAIL"));
        return dfsResult && bfsResult;
    }


    /**
     * Intuitions:
     * To find the gate from an empty room, we need to try all the four directions possible and find the nearest gate. It is worth to note that, if an empty room
     * already found a gate with minimum distance X and if there is another empty room near to it, then the distance of this gate can be calculate from already computed
     * empty room distance.
     * <p>
     * Approach:
     * 1. Start traversing the entire grid, if we find a "gate", then we try all the four directions and find the nearest empty room.
     * 2. As soon as we go in any valid direction, then the distance to the nearest empty room will be increased by 1.
     * 3. As soon as we find an empty room, we update the distance to the gate with the minimum distance found so far.
     * 4. We need to use visited[][] as well to avoid already visited places.
     */
    static class WallsGatesDFS {
        public void wallsAndGates(int[][] rooms) {

            if (null == rooms || rooms.length == 0 || rooms[0].length == 0)
                return;

            int m = rooms.length;
            int n = rooms[0].length;

            final boolean[][] visited = new boolean[m][n];


            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (!visited[i][j] && rooms[i][j] == 0) {
                        dfs(rooms, visited, i, j, m, n, 0);
                    }
                }
            }

        }

        private boolean isSafe(int i, int j, int m, int n, boolean[][] visited, int[][] rooms, int distance) {
            boolean validCord = i >= 0 && i < m && j >= 0 && j < n;


            return validCord
                    && !visited[i][j]  //we don't want to visit this cell again
                    && distance <= rooms[i][j]; //and also don't want to increase the existing distance
            // or if this cell is INF, then it needs to be evaluated
        }

        private void dfs(int[][] rooms, boolean[][] visited, int i, int j, int m, int n, int distance) {
            if (!isSafe(i, j, m, n, visited, rooms, distance))
                return;


            //if min-distance found for this gate with empty room, then update distance
            if (distance < rooms[i][j])
                rooms[i][j] = distance;

            //visit this cell
            visited[i][j] = true;

            //try all four directions, to get minimum distance
            dfs(rooms, visited, i - 1, j, m, n, distance + 1);
            dfs(rooms, visited, i + 1, j, m, n, distance + 1);
            dfs(rooms, visited, i, j - 1, m, n, distance + 1);
            dfs(rooms, visited, i, j + 1, m, n, distance + 1);

            //backtrack, as for the next INF cell, we may need to touch it back
            visited[i][j] = false;
        }
    }

    /**
     * Intuitions:
     * To find the gate from an empty room, we need to try all the four directions possible and find the nearest gate. It is worth to note that, if an empty room
     * already found a gate with minimum distance X and if there is another empty room near to it, then the distance of this gate can be calculate from already computed
     * empty room distance.
     * <p>
     * Approach:
     * To apply BFS, we need to find all the gates and then search for nearest empty room and update the distance.
     * 1. Enqueue all the gates.
     * 2. Start search for a given gate and as soon we hit the empty room, update the distance.
     * 3. The Distance will be calculated as far we go from this gate.
     * <p>
     * https://leetcode.ca/2016-09-11-286-Walls-and-Gates/
     */
    static class WallsGatesBFS {
        public void wallsAndGates(int[][] rooms) {

            if (null == rooms || rooms.length == 0 || rooms[0].length == 0)
                return;

            int m = rooms.length;
            int n = rooms[0].length;

            //first element hold row and second is col of integer
            Queue<Integer[]> queue = new LinkedList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == 0)
                        queue.offer(new Integer[]{i, j});
                }
            }

            int[] row = {0, 0, 1, -1};
            int[] col = {1, -1, 0, 0};
            while (!queue.isEmpty()) {

                Integer[] gate = queue.poll();
                int gateRow = gate[0];
                int gateCol = gate[1];

                //search the neighbors and find the empty room
                for (int k = 0; k < row.length; k++) {
                    int neighRow = gateRow + row[k];
                    int neighCol = gateCol + col[k];

                    //if a neighbor is a wall, then avoid it as well, we should not touch the other gate
                    //hence consider only empty room
                    if (isSafe(neighRow, neighCol, m, n) && rooms[neighRow][neighCol] == INF) {

                        //we found an empty room [neighRow][neighCol] with distance d of the current gate
                        rooms[neighRow][neighCol] = rooms[gateRow][gateCol] + 1; // it will at least 1 distance far from a gate

                        //check other neighbors, now this neighbor is a path to an empty gate for other neighbors
                        queue.offer(new Integer[]{neighRow, neighCol});
                    }
                }
            }

        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }

    }
}
