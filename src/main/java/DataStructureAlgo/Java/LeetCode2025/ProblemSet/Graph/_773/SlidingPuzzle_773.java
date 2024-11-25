package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._773;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 25/11/24
 * Question Title: 773. Sliding Puzzle
 * Link: https://leetcode.com/problems/sliding-puzzle/description
 * Description: On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Example 2:
 *
 *
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Example 3:
 *
 *
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 *
 *
 * Constraints:
 *
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * Each value board[i][j] is unique.
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @medium
 * @hard <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */
public class SlidingPuzzle_773 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 2, 3}, {4, 0, 5}}, 1));
        tests.add(test(new int[][]{{1, 2, 3}, {5, 4, 0}}, -1));
        tests.add(test(new int[][]{{4, 1, 2}, {5, 0, 3}}, 5));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "Expected"}, true, nums, expected);

        int output;
        boolean pass, finalPass = true;

        Solution_DFS solutionDfs = new Solution_DFS();
        output = solutionDfs.slidingPuzzle(nums);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"DFS", "Pass"}, false, output, pass ? "Pass" : "Fail");


        Solution_BFS solutionBFS = new Solution_BFS();
        output = solutionBFS.slidingPuzzle(nums);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"BFS", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution_BFS {
        // every element can move at most 3 direction because of the layout
        final String finalState = "123450";
        final int R = 2;
        final int C = 3;

        final int[][] direction = new int[][] {
                { 1, 3 }, // 0th guy can move either right or bottom
                { 0, 2, 4 }, // 1st guy can move left, right, bottom
                { 1, 5 }, // 2nd guy can move left and bottom
                { 0, 4 }, // 3rd guy can move up or right
                { 1, 3, 5 }, // 4th guy can move up, left, right
                { 2, 4 } // 5th guy can move up and left
        };

        public int slidingPuzzle(int[][] board) {

            // transform a 2d array to 1d array to manage index easily
            String currentState = getCurrentState(board);

            // state and moves it took to reach this state from original state
            // visited array
            Set<String> visitedState = new HashSet<>();

            // bfs queue on state
            Queue<String> queue = new LinkedList<>();
            queue.offer(currentState);
            visitedState.add(currentState);
            int moves = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();

                while (size-- > 0) {
                    String current = queue.poll();

                    if (finalState.equals(current))
                        return moves;

                    int index = current.indexOf('0');

                    for (int pos : direction[index]) {
                        String newState = getNewState(current, index, pos);

                        if (!visitedState.contains(newState)) {
                            visitedState.add(newState);
                            queue.offer(newState);
                        }

                    }

                }

                moves++;
            }

            return -1;

        }

        private String getNewState(String current, int zero, int pos) {
            char[] temp = current.toCharArray();
            char t = temp[zero];
            temp[zero] = temp[pos];
            temp[pos] = t;
            return new String(temp);
        }

        private String getCurrentState(int[][] board) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    s.append(board[i][j]);
                }
            }
            return s.toString();
        }

    }

    static class Solution_DFS {

        // Direction map for zero's possible moves in a flattened 1D array (2x3 board)
        private final int[][] directions = {
                { 1, 3 },
                { 0, 2, 4 },
                { 1, 5 },
                { 0, 4 },
                { 3, 5, 1 },
                { 4, 2 },
        };

        public int slidingPuzzle(int[][] board) {
            // Convert the 2D board into a string representation to use as state
            StringBuilder startState = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    startState.append(board[i][j]);
                }
            }

            // Map to store the minimum moves for each visited state
            Map<String, Integer> visited = new HashMap<>();

            // Start DFS traversal from initial board state
            dfs(startState.toString(), visited, startState.indexOf("0"), 0);

            // Return the minimum moves required to reach the target state, or -1 if unreachable
            return visited.getOrDefault("123450", -1);
        }

        private void dfs(
                String state,
                Map<String, Integer> visited,
                int zeroPos,
                int moves
        ) {
            // Skip if this state has been visited with fewer or equal moves
            if (visited.containsKey(state) && visited.get(state) <= moves) {
                return;
            }
            visited.put(state, moves);

            // Try moving zero to each possible adjacent position
            for (int nextPos : directions[zeroPos]) {
                String newState = swap(state, zeroPos, nextPos); // Swap to generate new state
                dfs(newState, visited, nextPos, moves + 1); // Recursive DFS with updated state and move count
            }
        }

        // Helper method to swap characters at indices i and j in the string
        private String swap(String str, int i, int j) {
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(i, str.charAt(j));
            sb.setCharAt(j, str.charAt(i));
            return sb.toString();
        }
    }

}
