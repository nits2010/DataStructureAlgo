package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.island._200;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 9/11/2024
 * Question Category: 200. Number of Islands
 * Description: https://leetcode.com/problems/number-of-islands/description
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.island.NumberIslands}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
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
 * @Adobe
 * @Affirm
 * @Alibaba
 * @Amazon
 * @AppDynamics
 * @Apple
 * @Arista
 * @Networks
 * @Atlassian
 * @Audible
 * @BlackRock
 * @Bloomberg
 * @Cisco
 * @Citadel
 * @Citrix
 * @Coursera
 * @CruiseAutomation
 * @DoorDash
 * @eBay
 * @ElectronicArts
 * @Evernote
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @Houzz
 * @Hulu
 * @caMorgan
 * @LinkedIn
 * @LiveRamp
 * @Lyft
 * @Mathworks
 * @Microsoft
 * @Nutanix
 * @Nvidia
 * @Oracle
 * @PalantirTechnologies
 * @Paypal
 * @Qualtrics
 * @Salesforce
 * @SAP
 * @Snapchat
 * @Splunk
 * @Spotify
 * @Square
 * @Sumologic
 * @Tableau
 * @Twitch
 * @Twitter
 * @Uber
 * @Visa
 * @VMware
 * @WalmartLabs
 * @Wish
 * @Yahoo
 * @Yelp
 * @Zenefits
 * @Zulily
 *
 * @Editorial
 */
public class NumberOfIsland_200 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}, 3);
        test &= test(new char[][]{{'1', '1', '1', '1', '1'}, {'1', '1', '0', '1', '1'}, {'1', '1', '0', '0', '1'}, {'0', '0', '0', '0', '1'}}, 1);
        test &= test(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}, 1);
        CommonMethods.printResult(test);
    }


    private static boolean test(char[][] grid, int expected) {
        System.out.println("-------------------------------------");
        System.out.println("Grid:\n" + CommonMethods.toStringFlat(grid) + " expected :" + expected);

        SolutionDFS solutiondfs = new SolutionDFS();
        int islandCount = solutiondfs.numIslands(grid);
        boolean result = islandCount == expected;
        System.out.println("SolutionDFS :" + islandCount + " result :" + (result?"PASS":"FAIL"));

        SolutionDFSConstantSpace solutionDFSConstantSpace = new SolutionDFSConstantSpace();
        int islandCountConstantSpace = solutionDFSConstantSpace.numIslands(CommonMethods.copyOf(grid));
        boolean resultConstantSpace = islandCountConstantSpace == expected;
        System.out.println("SolutionDFSConstantSpace :" + islandCountConstantSpace + " result :" + (resultConstantSpace?"PASS":"FAIL"));

        SolutionDFSConstantSpacePathCompression solutionDFSConstantSpacePathCompression = new SolutionDFSConstantSpacePathCompression();
        int islandCountConstantSpacePathCompression = solutionDFSConstantSpacePathCompression.numIslands(CommonMethods.copyOf(grid));
        boolean resultConstantSpacePathCompression = islandCountConstantSpacePathCompression == expected;
        System.out.println("SolutionDFSConstantSpacePathCompression :" + islandCountConstantSpacePathCompression + " result :" + (resultConstantSpacePathCompression?"PASS":"FAIL"));
        return result && resultConstantSpace && resultConstantSpacePathCompression;

    }


    /**
     *T/S ; O(n*m) / O(n*m)
     * This can be done using {@link DataStructureAlgo.Java.nonleetcode.UnionFindDisjointSets}
     */
   static class SolutionDFS {

        final int[] row = {0, 0, 1, -1};
        final int[] col = {1, -1, 0, 0};

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int n = grid.length;
            int m = grid[0].length;
            //O(n*m)
            final boolean[][] visited = new boolean[n][m];
            int islandCount = 0;

            //touch each possible island
            //O(n)
            for (int i = 0; i < n; i++) {
                //O(m)
                for (int j = 0; j < m; j++) {

                    //O(4) - we are visited each pair of indexes 4 times
                    //if this island has never been visited,
                    //then visit it and explore it neighbours
                    if (!visited[i][j] && grid[i][j] == '1') {
                        numIslands(grid, i, j, n, m, visited);
                        islandCount++;
                    }
                }
            }
            return islandCount;
        }
        private boolean isSafe(int i, int j, int n, int m) {
            return i >= 0 && i < n && j >= 0 && j < m;
        }
        private void numIslands(char[][] grid, int i, int j, int n, int m, boolean[][] visited) {
            //if it's not safe to visit, means i and j is out of boundary
            //if this land has already been visited
            //if this land is not land but water
            if (!isSafe(i, j, n, m) || visited[i][j] || grid[i][j] == '0')
                return;

            //visit this land
            visited[i][j] = true;

            //explore its neighbors
            for (int k = 0; k < row.length; k++) {
                numIslands(grid, i + row[k], j + col[k], n, m, visited);
            }

        }
    }

    static class SolutionDFSConstantSpace {

        final int[] row = {0, 0, 1, -1};
        final int[] col = {1, -1, 0, 0};

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int n = grid.length;
            int m = grid[0].length;
            //O(n*m)
            int islandCount = 0;

            //touch each possible island
            //O(n)
            for (int i = 0; i < n; i++) {
                //O(m)
                for (int j = 0; j < m; j++) {

                    //O(4) - we are visited each pair of indexes 4 times
                    //if this island has never been visited,
                    //then visit it and explore it neighbours
                    if (grid[i][j] == '1') {
                        numIslands(grid, i, j, n, m);
                        islandCount++;
                    }
                }
            }
            return islandCount;
        }
        private boolean isSafe(int i, int j, int n, int m) {
            return i >= 0 && i < n && j >= 0 && j < m;
        }
        private void numIslands(char[][] grid, int i, int j, int n, int m) {
            //if it's not safe to visit, means i and j is out of boundary
            //if this land has already been visited
            //if this land is not land but water
            if (!isSafe(i, j, n, m) || grid[i][j] == '0')
                return;

            //make this land as water so that we don't revisit
            grid[i][j] = '0';

            //explore its neighbors
            for (int k = 0; k < row.length; k++) {
                numIslands(grid, i + row[k], j + col[k], n, m);
            }

        }
    }

    static class SolutionDFSConstantSpacePathCompression {


        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int n = grid.length;
            int m = grid[0].length;
            //O(n*m)
            int islandCount = 0;

            //touch each possible island
            //O(n)
            for (int i = 0; i < n; i++) {
                //O(m)
                for (int j = 0; j < m; j++) {

                    //O(4) - we are visited each pair of indexes 4 times
                    //if this island has never been visited,
                    //then visit it and explore it neighbours
                    if (grid[i][j] == '1') {
                        numIslands(grid, i, j, n, m);
                        islandCount++;
                    }
                }
            }
            return islandCount;
        }
        private boolean isSafe(int i, int j, int n, int m) {
            return i >= 0 && i < n && j >= 0 && j < m;
        }
        private void numIslands(char[][] grid, int i, int j, int n, int m) {
            //if it's not safe to visit, means i and j is out of boundary
            //if this land has already been visited
            //if this land is not land but water
            if (!isSafe(i, j, n, m) || grid[i][j] == '0')
                return;

            //make this land as water so that we don't revisit
            grid[i][j] = '0';
            //explore its neighbors
            numIslands(grid, i, j - 1, n, m); // left
            numIslands(grid, i, j + 1, n, m); //right
            numIslands(grid, i - 1, j, n, m); //up
            numIslands(grid, i + 1, j, n, m); //down


           /* grid[i][j] = '0';
            //explore its neighbors
            if (j-1>=0 && grid[i][j-1] == '1')
                numIslands(grid, i, j - 1, n, m); // left

            if (j+1<m && grid[i][j+1] == '1')
                numIslands(grid, i, j + 1, n, m); //right

            if (i-1>=0 && grid[i-1][j] == '1')
                numIslands(grid, i - 1, j, n, m); //up

            if (i+1<n && grid[i+1][j] == '1')
                numIslands(grid, i + 1, j, n, m); //down*/


        }
    }
}
