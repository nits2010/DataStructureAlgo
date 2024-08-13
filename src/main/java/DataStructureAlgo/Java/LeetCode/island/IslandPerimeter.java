package DataStructureAlgo.Java.LeetCode.island;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-12
 * Description: https://leetcode.com/problems/island-perimeter/
 * 463. Island Perimeter
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a
 * square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * Output: 16
 * <p>
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public class IslandPerimeter {

    public static void main(String[] args) {
        test(new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}, 16);
        test(new int[][]{{0, 0, 1, 0}, {0, 0, 1, 0}, {1, 1, 1, 1}, {0, 0, 1, 0}}, 16);

    }

    private static void test(int[][] grid, int expected) {
        System.out.println("\n Grid :\n" + CommonMethods.toString(grid) + " expected :" + expected);
        IslandPerimeterNeighbourCount neighbourCount = new IslandPerimeterNeighbourCount();
        IslandPerimeterEdgeCount edgeCount = new IslandPerimeterEdgeCount();
        IslandPerimeterNeighbourCount2 edgeCount2 = new IslandPerimeterNeighbourCount2();
        System.out.println("neighbourCount " + neighbourCount.islandPerimeter(grid));
        System.out.println("edgeCount " + edgeCount.islandPerimeter(grid));
        System.out.println("edgeCount2 " + edgeCount2.islandPerimeter(grid));
    }
}

/**
 * As we know each island can contribute at max 4 edges to perimeter. The only exception is when this island has
 * neighbour, then the neighbour could make less edges to contribute in perimeter.
 * Example:
 * +--+     +--+                   +--+--+
 * |  |  +  |  |          ->       |     |
 * +--+     +--+                   +--+--+
 * A        B                       6
 * <p>
 * A = 4 , B=4 but since both are neighbour thats why 2 edges would disappear (the one which touches each other)
 * <p>
 * Now problem reduce to following
 * [
 * [0,4,0,0],
 * [4,4,4,0],
 * [0,4,0,0],
 * [4,4,0,0]
 * ]
 * We can count how many island are there, and how many are adjacent to each other.
 * Say, count is 'island' for number of island and 'nei' for neighbour count then total island
 * X = 4 * island
 * Y = 2 * neighbour {since 2 edges participate in neighbour}
 * Answer = X - Y
 * <p>
 * To not to count same island as neighbour, we'll look only down and right { or up and left}
 * <p>
 * Runtime: 6 ms, faster than 99.98% of Java online submissions for Island Perimeter.
 * Memory Usage: 59 MB, less than 97.92% of Java online submissions for Island Perimeter.
 */
class IslandPerimeterNeighbourCount {

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;


        int islandCount = 0;
        int neighbourCount = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    islandCount++;

                    if (i + 1 < m && grid[i + 1][j] == 1) neighbourCount++;
                    if (j + 1 < m && grid[i][j + 1] == 1) neighbourCount++;

                }

            }
        }

        return 4 * islandCount - 2 * neighbourCount;

    }
}


/**
 * Apply same as above, but count them on the fly.
 * To avoid neighbour count, we'll add only those edges which are possible to be a part of perimeter.
 * The edges that participate are one which has neighbour as water
 * <p>
 * Runtime: 7 ms, faster than 75.07% of Java online submissions for Island Perimeter.
 * Memory Usage: 58.9 MB, less than 97.92% of Java online submissions for Island Perimeter.
 */
class IslandPerimeterEdgeCount {

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;


        int islandCount = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    //left edge
                    if (j == 0 || grid[i][j - 1] == 0) islandCount++;


                    //right edge
                    if (j == n - 1 || grid[i][j + 1] == 0) islandCount++;


                    //down edge
                    if (i == m - 1 || grid[i + 1][j] == 0) islandCount++;

                    //up edge
                    if (i == 0 || grid[i - 1][j] == 0) islandCount++;


                }

            }
        }

        return islandCount;

    }
}


/**
 * Apply same {@link IslandPerimeterNeighbourCount}, but count them on the fly.
 * To avoid neighbour count, we'll reduce  those edges which are possible to be a part of perimeter multiple times.
 * The edges that participate multiple times are those which are adjacent
 * <p>
 * Runtime: 6 ms, faster than 99.98% of Java online submissions for Island Perimeter.
 * Memory Usage: 58.6 MB, less than 97.92% of Java online submissions for Island Perimeter.
 */
class IslandPerimeterNeighbourCount2 {

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;


        int islandCount = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    islandCount += 4;

                    //up edge
                    if (i > 0 && grid[i - 1][j] == 1) islandCount -= 2;


                    //left edge
                    if (j > 0 && grid[i][j - 1] == 1) islandCount -= 2;


                }

            }
        }

        return islandCount;

    }
}