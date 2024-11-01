package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.island._695;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.island._200.NumberOfIsland_200;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.island._305.NumberOfIslandII_305;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta
 * Date: 9/13/2024
 * Question Category: 695. Max Area of Island
 * Description: https://leetcode.com/problems/max-area-of-island/description/
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.island.MaxAreaIsland}
 * Similar {@link DataStructureAlgo.Java.companyWise.GroupOn.IslandsSizeCount}
 * extension {@link NumberOfIslandII_305}
 * <p><p>
 *
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
 * <p><p>
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Google
 * @Qualtrics
 * @Adobe
 * @Affirm
 * @Alibaba
 * @Bloomberg
 * @ByteDance
 * @DoorDash
 * @Groupon
 * @Intuit
 * @LinkedIn
 * @Mathworks
 * @Oracle
 * @PalantirTechnologies
 * @Twitch
 * @Uber
 * @WalmartLabs
 * @Wish
 * @Zillow
 *
 *
 *
 * @Editorial https://leetcode.com/problems/max-area-of-island/solutions/380781/simple-easy-with-mxn-using-union-find
 */
public class MaxAreaOfIsland_695 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1,0,1}, {1,0,1}, {1,1,1,}}, 7);
        test &= test(new int[][]
                {
                        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}

                }, 6);


        test &= test(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}, 7);
        test &= test(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}, 14);
        test &= test(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}, 0);

        CommonMethods.printAllTestOutCome(test);


    }


    private static boolean test(int[][] grid, int expected) {
        System.out.println("-------------------------------------");
        System.out.println("Grid:\n" + CommonMethods.toString(grid) + " expected :" + expected);

        Solution solution = new Solution();
        int maxAreaOfIsland = solution.maxAreaOfIsland(CommonMethods.copyOf(grid));
        boolean test = maxAreaOfIsland == expected;
        System.out.println("Obtained :" + maxAreaOfIsland + " test :" + (test?"passed":"failed"));

        SolutionUnionFind solutionUnionFind = new SolutionUnionFind();
        int maxAreaOfIslandUnionFind = solutionUnionFind.maxAreaOfIsland(CommonMethods.copyOf(grid));
        boolean testUnionFind = maxAreaOfIslandUnionFind == expected;
        System.out.println("Obtained UF:" + maxAreaOfIslandUnionFind + " test :" + (testUnionFind?"passed":"failed"));
        return test && testUnionFind;
    }


    static class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;

            int m = grid.length;
            int n = grid[0].length;
            int maxArea = 0;
            for(int i=0; i<m; i++) {
                for (int j = 0; j < n; j++) {

                    if(grid[i][j] == 1) {
                        maxArea = Math.max(maxArea, dfs(grid, i, j, m, n));
                    }
                }
            }
            return maxArea;
        }

        private int dfs(int[][] grid, int i, int j, int m, int n) {
            if(!isSafe(i, j, m, n) || grid[i][j] != 1) {
                return 0;
            }

            //mark visited
            grid[i][j] = 0;

            //try all 4 directions
            return 1 + dfs(grid, i + 1, j, m, n) +
                    dfs(grid, i - 1, j, m, n) +
                    dfs(grid, i, j + 1, m, n) +
                    dfs(grid, i, j - 1, m, n);

        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && j >= 0 && i < m && j < n;
        }

    }

    /**
     * Similar {@link NumberOfIsland_200}
     * Using {@link DataStructureAlgo.Java.nonleetcode.UnionFindDisjointSets}
     *
     *
     * The idea is to create a graph of each connected land and get the size of that graph. Find a graph with the max size.
     * Using union Find, we can create a multi-component graph; each component in a graph would represent an island.
     * T/S: O(m*n * log mn) / O(mn)
     */
    static class SolutionUnionFind {

        int[] row = {0, 0, 1, -1};
        int[] col = {-1, 1, 0, 0};

        static class UnionFind {
            static class Set {
                int rank; //size
                int id;

                public Set(int id, int rank) {
                    this.rank = rank;
                    this.id = id;
                }

            }

            private final Set[] parents;
            Map<Integer,Integer> IdToIndex;
            int maxSize = 1;

            public UnionFind(int n) {
                //each element has size 1 and it is its own parent
                parents = IntStream.range(0, n).mapToObj(i -> new Set(i, 1)).toArray(Set[]::new);
            }

            //this will minimize the slots
            public UnionFind(int [][]grid) {
                int size = 0;

                //find slots that are required for only those, where is land
                IdToIndex = new HashMap<>();
                int m = grid.length;
                int n = grid[0].length;
                for(int i=0; i<m; i++) {
                    for(int j=0; j<n; j++) {
                        if(grid[i][j] == 1) {
                            int id = i * n + j;
                            IdToIndex.put(id, IdToIndex.size());
                        }
                    }
                }

                int totalLands = IdToIndex.size() + 1 ;

                //each element has size 1 and it is its own parent
                parents = IntStream.range(0, totalLands).mapToObj(i -> new Set(i, 1)).toArray(Set[]::new);
            }

            public int find(int i) {
                if (parents[i].id == i)
                    return i;
                return parents[i].id = find(parents[i].id); //path compression
            }

            public boolean union(int i, int j) {
                int ip = find(i);
                int jp = find(j);

                //both are already in the same set
                if (ip == jp)
                    return false;

                unionByRank(i, j, ip, jp);
                maxSize = Math.max(maxSize, Math.max(parents[jp].rank, parents[ip].rank));
                return true; //union was possible
            }

            private void unionByRank(int i, int j, int ip, int jp) {
                int ipRank = parents[ip].rank;
                int jpRank = parents[jp].rank;


                //graph of i as root is bigger as compare to j graph.
                if (ipRank > jpRank) {
                    parents[jp].id = ip; //attach i as parent of j, this makes size no difference in i's graph
                    parents[ip].rank += parents[jp].rank; //connected graph of i and j will be now has more nodes in it.
                }  else {
                    //both graph sizes are equal attached to i and  or j size is higher
                    parents[ip].id = jp; // make jp as parent of i, this makes j size graph increase by 1
                    parents[jp].rank += parents[ip].rank; //connected graph of i and j will be now has more nodes in it.
                }
            }

            public int getMaxSize() {
                return maxSize;
            }

        }



        public int maxAreaOfIsland(int[][] grid) {
            if(grid == null || grid.length == 0)
                return 0;

            int m = grid.length;
            int n = grid[0].length;

             UnionFind unionFind = new UnionFind(m*n);
//            UnionFind unionFind = new UnionFind(grid);
            boolean landFound = false;

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if(grid[i][j] == 1){
                        landFound = true;


                        for(int k=0; k<4; k++) {
                            int r = i + row[k];
                            int c = j + col[k];
                            if(isSafe(r, c, m, n) && grid[r][c] == 1) {
                                int landIndex = _2DPos1DPost(i, j, m, n);
                                int neighbourIndex = _2DPos1DPost(r, c, m, n);
                                unionFind.union(landIndex, neighbourIndex);

                                /*
                                    int landIndex = _2DPos1DPost(i, j, m, n);
                                    int neighbourIndex = _2DPos1DPost(r, c, m, n);

                                    int landSlotIndex = unionFind.IdToIndex.get(landIndex);

                                    int neighbourSlotIndex = unionFind.IdToIndex.get(neighbourIndex);

                                    unionFind.union(landSlotIndex, neighbourSlotIndex);
                                 */
                            }
                        }
                    }
                }
            }
            if(!landFound)
                return 0;
            return unionFind.getMaxSize();

        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && j >= 0 && i < m && j < n;
        }

        //translate 2D position to 1D index
        private int _2DPos1DPost(int i, int j, int m, int n) {
            //2d array has m rows and n columns, that makes it has m*n blocks.
            //if 'i' represent m, then i*n represent the position in 2D of row and addition, j makes column position
            return i * n + j;
        }
    }
}
