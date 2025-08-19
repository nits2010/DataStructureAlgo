package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.island._305;


import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph.island._200.NumberOfIsland_200;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.UnionFindDisjointSets;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta
 * Date: 9/13/2024
 * Question Category: 305. Number of Islands II
 * Descriptions: https://leetcode.com/problems/number-of-islands-ii/description/ , https://leetcode.ca/all/305.html#google_vignette
 * <p>
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example:
 * <p>
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * <p>
 * Initially, the 2d grid is filled with water. (Assume 0 represents water and 1 represents land).
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
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.island.NumberIslandsII}
 * Similar {@link}
 * extension {@link NumberOfIsland_200}
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @UnionFind
 * @Matrix
 * @Graph
 * @PremiumQuestion <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Google
 * @Snapchat
 * @Uber
 * @Editorial
 */
public class NumberOfIslandII_305 {
    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}}, List.of(1, 1, 2, 3)));
        tests.add(test(3, 3, new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 2}}, List.of(1, 1, 1, 1)));
        tests.add(test(3, 3, new int[][]{{0, 1}, {0, 1}, {2, 1}, {2, 2}}, List.of(1, 1, 2, 2))); //Duplicate land entry
        tests.add(test(4, 4, new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 2}, {3, 3}}, List.of(1, 1, 1, 1, 2)));
        tests.add(test(5, 4, new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 2}, {3, 3}, {4, 3}}, List.of(1, 1, 1, 1, 2, 2)));
        CommonMethods.printAllTestOutCome(tests);

    }

    private static boolean test(int m, int n, int[][] positions, List<Integer> expected) {

        CommonMethods.printTest(new String[]{"M", "N", "Positions", "Expected"}, true, m, n, positions, expected);

        List<Integer> output = null;
        boolean pass, finalPass = true;
        output = new SolutionUsingIslandFinder().numIslands2(m, n, positions);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SolutionUsingIslandFinder", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionUsingUnionFind().numIslands2(m, n, positions);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SolutionUsingUnionFind", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionUsingCachingMap().numIslands2(m, n, positions);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SolutionUsingCachingMap", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * As post of the add operation, the problem reduces to find the number of islands in current matrix; we can use the
     * island finder as subroutine {@link NumberOfIsland_200}.
     * For each operation, the time complexity would be O(mn), where m and n are the dimensions of the matrix.
     * T/S :  O(mn) /  O(mn)
     */
    static class SolutionUsingIslandFinder {
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            if (positions == null || positions.length == 0) {
                return new ArrayList<>();
            }

            char[][] island = new char[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(island[i], '0');
            }
            List<Integer> islands = new ArrayList<>();
            for (int[] pos : positions) {
                if (island[pos[0]][pos[1]] == '0') {
                    island[pos[0]][pos[1]] = '1';
                    islands.add(numIslands(island));
                } else {
                    int lastIslands = islands.isEmpty() ? 0 : islands.get(islands.size() - 1);
                    islands.add(lastIslands);
                }

            }
            return islands;
        }

        final int[] row = {0, 0, 1, -1};
        final int[] col = {1, -1, 0, 0};

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;

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
            if (!isSafe(i, j, n, m) || visited[i][j] || grid[i][j] == '0') return;

            //visit this land
            visited[i][j] = true;

            //explore its neighbors
            for (int k = 0; k < row.length; k++) {
                numIslands(grid, i + row[k], j + col[k], n, m, visited);
            }

        }
    }


    /**
     * Above algorithm first build the grid, so land, then count the island. There are two important things
     * 1. The reason is that for each position either creates a new island
     * or
     * 2. Get connected to old island.
     * <p>
     * Which turns out, if we somehow efficiently can find that two islands are connected or not, then we can simply discard the new land as a potential new island.
     * Since, an island can be connected through 4 directions {horizontal and vertical} i.e., means we need to attach those valid position to old island as well to correctly evaluate
     * the new position as potential island.
     * <p>
     * Union-Find {@link UnionFindDisjointSets}
     * -----------
     * Using union-Find we can find out that two points are connected or not by checking their parent.
     * <p>
     * <p>
     * Algorithm:
     * 1. Create the possible amount of space available as empty parent {our water spaces}
     * 2. For each position, evaluate the following things
     * *  2.1 : does this land be being connected to any previous island ? if so, then we should not count this as an island.
     * * 2.2 : Also do the same for all 4 direction
     * <p>
     * T/S : O(k log mn), / O (m*n) where k is the length of the positions
     * <p>
     * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.32.html
     * https://segmentfault.com/a/1190000016587068
     */
    static class SolutionUsingUnionFind {
        //all 4 directions of given position
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

            public UnionFind(int n) {
                //each element has size 1 and it is its own parent
                parents = IntStream.range(0, n).mapToObj(i -> new Set(i, 1)).toArray(Set[]::new);
            }

            public int find(int i) {
                if (parents[i].id == i) return i;
                return parents[i].id = find(parents[i].id); //path compression
            }

            public boolean union(int i, int j) {
                int ip = find(i);
                int jp = find(j);

                //both are already in the same set
                if (ip == jp) return false;

                unionByRank(i, j, ip, jp);
                return true; //union was possible
            }

            private void unionByRank(int i, int j, int ip, int jp) {
                int ipRank = parents[ip].rank;
                int jpRank = parents[jp].rank;

                //graph of i as root is bigger as compare to j graph.
                if (ipRank > jpRank) {
                    parents[jp].id = ip; //attach i as parent of j, this makes size no difference in i's graph
                } else if (jpRank > ipRank) {
                    parents[ip].id = jp; //attach j as parent of i, this makes size no difference in j's graph
                } else {
                    //both graph sizes are equal attached to i and j.
                    parents[ip].id = jp; // make jp as parent of i, this makes j size graph increase by 1
                    parents[jp].rank++; //increase j's graph size
                }
            }

        }


        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            if (positions == null || positions.length == 0) {
                return new ArrayList<>();
            }

            //island maker
            int[][] island = new int[m][n];

            List<Integer> islands = new ArrayList<>();
            UnionFind unionFind = new UnionFind(m * n); // as set of all the elements being individual

            for (int[] pos : positions) {
                boolean isNewConnectedIslandFound = addLand(pos, m, n, island, unionFind);

                int lastIslandCount = islands.isEmpty() ? 0 : islands.get(islands.size() - 1);

                if (isNewConnectedIslandFound) {
                    islands.add(lastIslandCount + 1);
                } else {
                    islands.add(lastIslandCount);
                }
            }
            return islands;
        }

        private boolean addLand(int[] pos, int m, int n, int[][] island, UnionFind unionFind) {

            boolean isNewConnectedIslandFound = true;

            //there was already a land, hence can't create new land
            if (island[pos[0]][pos[1]] == 1) {
                isNewConnectedIslandFound = false;
            } else {
                //if there is water, then create a land
                island[pos[0]][pos[1]] = 1;

                int landIndex = _2DPos1DPost(pos[0], pos[1], m, n);

                //check its neighbors, if this is an island, than there will be only water around it. otherwise connect all lands around it.
                for (int k = 0; k < row.length; k++) {
                    int i = pos[0] + row[k];
                    int j = pos[1] + col[k];
                    int neighborIndex = _2DPos1DPost(i, j, m, n);

                    //if neighbor is land, then this must have been evaluated in last addLandCall, just union them make in single land with current land
                    if (isSafe(i, j, m, n) && island[i][j] == 1) {
                        isNewConnectedIslandFound = false; //it must be connected in last add call, hence must be calculated
                        unionFind.union(landIndex, neighborIndex);
                    }
                }
            }

            return isNewConnectedIslandFound;

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


    static class SolutionUsingCachingMap {
        //all 4 directions of given position
        int[] dirRow = {0, 0, 1, -1};
        int[] dirCol = {-1, 1, 0, 0};

        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            if (positions == null || positions.length == 0 || positions[0].length == 0) {
                return new ArrayList<>();
            }


            List<Integer> counts = new ArrayList<>();

            Map<Integer, Set<Integer>> positionToLandMap = new HashMap<>();


            // loop through all the positions
            for (int[] pos : positions) {
                int row = pos[0];
                int col = pos[1];
                boolean isConnected = false;

                // check if the land is already visited
                if (!(positionToLandMap.containsKey(row) && positionToLandMap.get(row).contains(col))) {

                    //add land
                    positionToLandMap.computeIfAbsent(row, k -> new HashSet<>()).add(col);


                    if (counts.isEmpty()) {
                        counts.add(1); // first island
                        continue;
                    }

                    // check does this land connected to any other lands
                    for (int i = 0; i < dirRow.length; i++) {
                        int r = row + dirRow[i];
                        int c = col + dirCol[i];

                        if (!positionToLandMap.containsKey(r) || !positionToLandMap.get(r).contains(c)) {
                            continue;
                        }

                        isConnected = true;
                        break;
                    }
                } else {
                    // since this is already visited land, hence already connected
                    isConnected = true;
                }
                if (isConnected) {
                    counts.add(counts.get(counts.size() - 1));
                } else {
                    counts.add(counts.get(counts.size() - 1) + 1);
                }


            }
            return counts;
        }
    }
}

