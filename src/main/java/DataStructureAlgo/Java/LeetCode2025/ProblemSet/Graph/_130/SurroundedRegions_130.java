package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._130;

import DataStructureAlgo.Java.helpers.CommonMethods;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 9/14/2024
 * Question Category: 130. Surrounded Regions
 * Description: https://leetcode.com/problems/surrounded-regions/description/
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 *
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
 * A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.
 *
 *
 *
 * Example 1:
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * Explanation:
 *
 *
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 *
 * Example 2:
 *
 * Input: board = [["X"]]
 *
 * Output: [["X"]]
 *
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 *
 * File reference
 * -----------
 * Duplicate {@link}
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
 * @Amazon
 * @Facebook
 * @Google
 * @Splunk
 * @Uber
 * @tiktok
 * @Bloomberg
 *
 * @Editorial
 * https://leetcode.com/problems/surrounded-regions/solutions/5786209/neat-easy-to-understand-dfs-bfs-solution-explanations
 * https://leetcode.com/problems/surrounded-regions/solutions/1552435/easy-explained-solution-with-images
 *
 */
public class SurroundedRegions_130 {
    public static void main(String[] args) {
        boolean test = true;

        test &= test(new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}},
                new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}});
        test &= test(new char[][]{{'X', 'O', 'O', 'X', 'O'}, {'O', 'X', 'O', 'O', 'X'}, {'X', 'O', 'X', 'O', 'X'}, {'X', 'X', 'X', 'X', 'X'}},
                new char[][]{{'X', 'O', 'O', 'X', 'O'}, {'O', 'X', 'O', 'O', 'X'}, {'X', 'X', 'X', 'O', 'X'}, {'X', 'X', 'X', 'X', 'X'}});
        test &= test(new char[][]{{'X'}}, new char[][]{{'X'}});
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(char[][] board, char[][] expected) {
        System.out.println("---------------------------------------------");
        System.out.println(" Board : \n" + CommonMethods.toString2D(board) + "\n Expected : \n" + CommonMethods.toString2D(expected));

        SolutionDFS solutionDFS = new SolutionDFS();
        char[][] input = CommonMethods.copyOf(board);
        solutionDFS.solve(input);
        boolean dfsTest = CommonMethods.equals(input, expected);
        System.out.println("\n DFS : \n" + CommonMethods.toString2D(input) + " Result : " + (dfsTest ? "PASS" : "FAIL"));


        SolutionBFS solutionBFS = new SolutionBFS();
        input = CommonMethods.copyOf(board);
        solutionBFS.solve(input);
        boolean bfsTest = CommonMethods.equals(input, expected);
        System.out.println("\n BFS : \n" + CommonMethods.toString2D(input) + " Result : " + (bfsTest ? "PASS" : "FAIL"));
        return dfsTest && bfsTest;

    }

    /**
     * Intuitions: It is certain that all the 'O' cells at the boundary can't be surrounded with X.
     * Which also means that, all the connected region to this Cell would also can't be surrounded.
     * However, all the rest 'O' would be surrounded with X if they form a connected region.
     * <p>
     * Approach: DFS
     * 1. Save all the boundary cells and its connected cells, which can't be surrounded, replace those 'O' with some random value.
     * 2. Post that, all the 'O' cells in the matrix can be surrounded and can be replaced with 'X'
     * 3. Recover the boundary cells.
     * <p>
     * T/S: O(mxn) / O(m*n) - stack space
     */
    static class SolutionDFS {

        final static char REPLACER = '#';

        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0)
                return;

            final int m = board.length;
            final int n = board[0].length;


            saveBoundary(board, m, n);
            recoverBoundary(board, m, n);

        }

        //Save all the boundary's 'O' cells, as they can't be surrounded
        //boundaries are first row, last row, first column and the last column.
        private void saveBoundary(char[][] board, int m, int n) {


            //first row and last row save
            for (int j = 0; j < n; j++) {

                //if a boundary has 'O' then all the connected cells have to be save since they can't be surrounded
                if (board[0][j] == 'O')
                    dfs(board, 0, j, m, n);

                if (board[m - 1][j] == 'O')
                    dfs(board, m - 1, j, m, n);
            }

            //first column and last column save
            for (int i = 0; i < m; i++) {

                //if a boundary has 'O' then all the connected cells have to be save since they can't be surrounded
                if (board[i][0] == 'O')
                    dfs(board, i, 0, m, n);

                if (board[i][n - 1] == 'O')
                    dfs(board, i, n - 1, m, n);

            }

        }


        //now all the remaining 'O' would be surrounded by X only, which can be easily changed to 'X'.
        //additionally, we have recovered all the boundary's 'O' cells
        private void recoverBoundary(char[][] board, int m, int n) {

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    //recovered all the boundary's 'O' cells and its connected regions
                    if (board[i][j] == REPLACER)
                        board[i][j] = 'O';
                    else if (board[i][j] == 'O') //now all the remaining 'O' would be surrounded by X only, which can be easily changed to 'X'.
                        board[i][j] = 'X';

                }
            }
        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && j >= 0 && i < m && j < n;
        }

        private void dfs(char[][] board, int i, int j, int m, int n) {
            if (!isSafe(i, j, m, n) || board[i][j] != 'O')
                return;

            board[i][j] = REPLACER;

            //explore its neighbors
            dfs(board, i + 1, j, m, n);
            dfs(board, i - 1, j, m, n);
            dfs(board, i, j + 1, m, n);
            dfs(board, i, j - 1, m, n);
        }
    }


    /**
     * Intuitions: It is certain that all the 'O' cells at the boundary can't be surrounded with X.
     * Which also means that, all the connected region to this Cell would also can't be surrounded.
     * However, all the rest 'O' would be surrounded with X if they form a connected region.
     * <p>
     * Approach: DFS
     * 1. Save all the boundary cells and its connected cells, which can't be surrounded, replace those 'O' with some random value.
     * 2. Post that, all the 'O' cells in the matrix can be surrounded and can be replaced with 'X'
     * 3. Recover the boundary cells.
     * <p>
     * Save all boundary can be done using BFS as well. Enqueue all the cells having 'O' and its connected cells are at boundary.
     * <p>
     * T/S: O(mxn) / O(m*n) - stack space
     */
    static class SolutionBFS {

        final static char REPLACER = '#';

        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0)
                return;

            final int m = board.length;
            final int n = board[0].length;


            saveBoundary(board, m, n);
            recoverBoundary(board, m, n);

        }

        //Save all the boundary's 'O' cells, as they can't be surrounded
        //boundaries are first row, last row, first column and the last column.
        private void saveBoundary(char[][] board, int m, int n) {

            Queue<Integer[]> queue = new LinkedList<>();

            //first row and last row save
            for (int j = 0; j < n; j++) {

                //if a boundary has 'O' then all the connected cells have to be save since they can't be surrounded
                if (board[0][j] == 'O')
                    queue.offer(new Integer[]{0, j});

                if (board[m - 1][j] == 'O')
                    queue.offer(new Integer[]{m - 1, j});
            }

            //first column and last column save
            for (int i = 0; i < m; i++) {

                //if a boundary has 'O' then all the connected cells have to be save since they can't be surrounded
                if (board[i][0] == 'O')
                    queue.offer(new Integer[]{i, 0});


                if (board[i][n - 1] == 'O')
                    queue.offer(new Integer[]{i, n - 1});


            }

            int[] row = {0, 0, 1, -1};
            int[] col = {1, -1, 0, 0};

            //save all the remaining 'O' cells, connected regions
            while (!queue.isEmpty()) {
                Integer[] cell = queue.poll();
                int i = cell[0];
                int j = cell[1];
                board[i][j] = REPLACER;

                for (int k = 0; k < row.length; k++) {
                    int r = i + row[k];
                    int c = j + col[k];
                    if (isSafe(r, c, m, n) && board[r][c] == 'O')
                        queue.offer(new Integer[]{r, c});
                }

            }

        }


        //now all the remaining 'O' would be surrounded by X only, which can be easily changed to 'X'.
        //additionally, we have recovered all the boundary's 'O' cells
        private void recoverBoundary(char[][] board, int m, int n) {

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    //recovered all the boundary's 'O' cells and its connected regions
                    if (board[i][j] == REPLACER)
                        board[i][j] = 'O';
                    else if (board[i][j] == 'O') //now all the remaining 'O' would be surrounded by X only, which can be easily changed to 'X'.
                        board[i][j] = 'X';

                }
            }
        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && j >= 0 && i < m && j < n;
        }

    }

}
