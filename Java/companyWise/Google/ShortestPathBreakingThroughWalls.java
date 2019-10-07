package Java.companyWise.Google;

import Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 21/09/19
 * Description:https://leetcode.com/discuss/interview-question/353827/Google-or-Onsite-or-Shortest-Path-Breaking-Through-Walls/
 * Given a 2D grid of size r * c. 0 is walkable, and 1 is a wall. You can move up, down, left or right at a time.
 * Now you are allowed to break at most 1 wall, what is the minimum steps to walk from the upper left corner (0, 0) to the lower right corner (r-1, c-1)?
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [[0, 1, 0, 0, 0],
 * [0, 0, 0, 1, 0],
 * [1, 1, 0, 1, 0],
 * [1, 1, 1, 1, 0]]
 * <p>
 * Output: 7
 * Explanation: Change `1` at (0, 1) to `0`, the shortest path is as follows:
 * (0, 0) -> (0, 1) -> (0, 2) -> (0, 3) -> (0, 4) -> (1, 4) -> (2, 4) -> (3, 4)
 * There are other options of length 7, not listed here.
 * Example 2:
 * <p>
 * Input:
 * [[0, 1, 1],
 * [1, 1, 0],
 * [1, 1, 0]]
 * <p>
 * Output: -1
 * Explanation: Regardless of which `1` is changed to `0`, there is no viable path.
 * Follow-up:
 * What if you can break k walls?
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2
 * [[0, 1, 0, 0, 0],
 * [0, 0, 0, 1, 0],
 * [0, 1, 1, 1, 1],
 * [0, 1, 1, 1, 1],
 * [1, 1, 1, 1, 0]]
 * <p>
 * Output: 10
 * Explanation: Change (2, 4) and (3, 4) to `0`.
 * Route (0, 0) -> (1, 0) -> (1, 1) -> (1, 2) -> (0, 2) -> (0, 3) -> (0, 4) -> (1, 4) -> (2, 4) -> (3, 4) -> (4, 4)
 * <p>
 * Other way of asking
 * Given a m x n matrix represents a maze, X is the starting point, Y is the destination, . is walkable, and # is a wall.
 * You can walk in four directions: up, down, left and right. Now you are allowed to break at most K walls, what is the minimum steps to walk from X to Y ?
 * For example, we have the following maze, and you can break at most 3 walls:
 * . X . # . .
 * . # . . # .
 * . # . . . .
 * . # # . . .
 * . Y . . . .
 * . . . . . #
 * As you can see, the minimum steps to walk from X to Y is 4, that is we just have to break those 3 walls under X and then go straight to Y .
 * <p>
 * Similar  {@link Java.companyWise.thoughtspot.ShortestPathRoadFlightAtMostNFlight}
 * <p>
 * Explanation: https://medium.com/@jeantimex/3-dimensional-breadth-first-search-62a54596f74a
 */
public class ShortestPathBreakingThroughWalls {

    public static void main(String[] args) {
        test(new int[][]{{0, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 1, 1, 0}}, 1, 7);
        test(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}, 1, Integer.MAX_VALUE);
        test(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}, 2, 4);
        test(new int[][]{{0, 1, 1}, {0, 0, 1}, {1, 1, 0}}, 1, 4);
        test(new int[][]{{0, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {0, 1, 1, 1, 1}, {0, 1, 1, 1, 1}, {1, 1, 1, 1, 0}}, 2, 10);

    }

    private static void test(int[][] grid, int k, int expected) {
        System.out.println("\nGrid:\n" + GenericPrinter.toString(grid) + " Walls :" + k);
        System.out.println("Expected                :" + expected);
        System.out.println("BFS                     :" + ShortestPathBreakingThroughWallsBFS.minDist(grid, 0, 0, grid.length - 1, grid[0].length - 1, k));
        System.out.println("BFS                     :" + ShortestPathBreakingThroughWallsDijkstraAlgo.minDist(grid, 0, 0, grid.length - 1, grid[0].length - 1, k));

    }

}

/**
 * Brute force:
 * We can apply backtracking approach {DFS} and try all the paths and try all possibilities of breaking walls till you can break
 * just like {@link Java.companyWise.thoughtspot.ShortestPathRoadFlightAtMostNFlight} backtracking approach
 * <p>
 * <p>
 * The most interesting part of this question is 'breaking the wall'. In case, this option wasn't there then we can simply apply BFS Algorithm
 * just like {@link Java.companyWise.Amazon.RemoveObstacleRobotMinimumDistance} or many other problem like this.
 * But since we are allowed to break at most 'k' walls, then we need to see through those options too.
 * Which imply that, keeping only distance by going through a path is not enough, we also need to keep a indicator which tells that how many walls we have break in order to reach till this point.
 * Since we are allowed to break at most 'k' walls then this indicator also help us to know do we allowed to break any more walls or not from this point onwards or not.
 * <p>
 * path[i][j] denotes the minimum distance to reach point (i,j) from source
 * extend this to include walls
 * path[i][j][w] denotes the minimum distance to reach point (i,j) by breaking 'w' walls
 */
class ShortestPathBreakingThroughWallsBFS {

    /**
     * it is similar to path[][][]
     */
    private static class DistanceNode {
        int row;
        int column;
        int distance;
        int walls;

        public DistanceNode(int row, int column, int distance, int walls) {
            this.row = row;
            this.column = column;
            this.distance = distance;
            this.walls = walls;
        }
    }


    /**
     * BFS approach
     *
     * @param grid         given grid
     * @param sourceX      source row
     * @param sourceY      source column
     * @param destinationX destination row
     * @param destinationY destination column
     * @param k            number of walls can be broken
     * @return minimum distance to reach destination from source by breaking at most k walls
     * Time complexity: O(r * c * k).
     * Space complexity: O(r * c * k).
     */
    public static int minDist(int[][] grid, int sourceX, int sourceY, int destinationX, int destinationY, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return Integer.MAX_VALUE;

        //if you are at destination it self
        if (sourceX == destinationX && sourceY == destinationY)
            return 0;

        if (grid[sourceX][sourceY] == 1 && k == 0)
            return Integer.MAX_VALUE; //not possible to reach destination since at source there is a wall and we can't break through it

        Queue<DistanceNode> queue = new ArrayDeque<>();
        queue.offer(new DistanceNode(sourceX, sourceY, 0, grid[sourceX][sourceY] == 1 ? 1 : 0)); //from source you don't break any walls if there is no wall, otherwise you need to break at least one wall

        //all four direction of moment
        final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {

            final DistanceNode node = queue.poll();

            //we reached destination, since it is bfs, then it make sure the distance is minimum
            if (node.row == destinationX && node.column == destinationY)
                return node.distance;

            //try 4 direction
            for (int[] d : dir) {
                final int r = node.row + d[0];
                final int c = node.column + d[1];

                //no move possible by going in 'd' direction
                if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)
                    continue;

                int distance = node.distance + 1; //now moving one direction out of 4

                int walls = node.walls + grid[r][c]; //grid[r][c] == 0 if no wall otherwise 1 if a wall

                if (walls <= k) //if we can pass through; note if there were a wall at grid[r][c] then the count must have been increased by 1 from previous count at node.
                    queue.offer(new DistanceNode(r, c, distance, walls));

            }
        }

        return Integer.MAX_VALUE; //you can't reach destination


    }

}

/**
 * Similar to above algorithm.
 * But using dijkstra algo:
 * Implementation like {@link Java.companyWise.thoughtspot.ShortestPathRoadFlightAtMostNFlight} #ShortestPathRoadFlightAtMostNFlightDijkstraShortestPath
 * <p>
 * We'll apply weighted Dijkstra where weight of each edge =1
 */
class ShortestPathBreakingThroughWallsDijkstraAlgo {

    /**
     * it is similar to path[][][]
     */
    private static class DistanceNode {
        int row;
        int column;
        int distance;
        int walls;

        public DistanceNode(int row, int column, int distance, int walls) {
            this.row = row;
            this.column = column;
            this.distance = distance;
            this.walls = walls;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DistanceNode that = (DistanceNode) o;
            return row == that.row &&
                    column == that.column &&
                    walls == that.walls;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column, walls);
        }
    }


    /**
     * @param grid         given grid
     * @param sourceX      source row
     * @param sourceY      source column
     * @param destinationX destination row
     * @param destinationY destination column
     * @param k            number of walls can be broken
     * @return minimum distance to reach destination from source by breaking at most k walls
     * <p>
     * Time complexity: O(r * c * k).
     * Space complexity: O(r * c * k).
     */
    public static int minDist(int[][] grid, int sourceX, int sourceY, int destinationX, int destinationY, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return Integer.MAX_VALUE;

        //if you are at destination it self
        if (sourceX == destinationX && sourceY == destinationY)
            return 0;

        if (grid[sourceX][sourceY] == 1 && k == 0)
            return Integer.MAX_VALUE; //not possible to reach destination since at source there is a wall and we can't break through it


        DistanceNode sourceNode = new DistanceNode(sourceX, sourceY, 0, grid[sourceX][sourceY] == 1 ? 1 : 0);
        PriorityQueue<DistanceNode> queue = new PriorityQueue<>(Comparator.comparing(o -> o.distance));
        queue.offer(sourceNode); //from source you don't break any walls if there is no wall, otherwise you need to break at least one wall

        Set<DistanceNode> settled = new HashSet<>();
        settled.add(sourceNode);

        //all four direction of moment
        final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {

            DistanceNode node = queue.poll();

            //we reached destination, since it is bfs, then it make sure the distance is minimum
            if (node.row == destinationX && node.column == destinationY)
                return node.distance;

            //try 4 direction
            for (int[] d : dir) {
                final int r = node.row + d[0];
                final int c = node.column + d[1];

                //no move possible by going in 'd' direction
                if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)
                    continue;

                int distance = node.distance + 1; //now moving one direction out of 4

                int walls = node.walls + grid[r][c]; //grid[r][c] == 0 if no wall otherwise 1 if a wall

                DistanceNode next = new DistanceNode(r, c, distance, walls);
                if (settled.contains(next)) //if already visited, skip this
                    continue;


                if (walls <= k) { //if we can pass through; note if there were a wall at grid[r][c] then the count must have been increased by 1 from previous count at node.
                    queue.offer(new DistanceNode(r, c, distance, walls));
                    settled.add(next);
                }
            }

        }
        return Integer.MAX_VALUE; //you can't reach destination
    }


}