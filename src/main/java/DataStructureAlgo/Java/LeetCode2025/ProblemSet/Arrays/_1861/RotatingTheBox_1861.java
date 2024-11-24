package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1861;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/24/2024
 * Question Title: 1861. Rotating the Box
 * Link: https://leetcode.com/problems/rotating-the-box/description/?envType=daily-question&envId=2024-11-23
 * Description: You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 * <p>
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 * <p>
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 * <p>
 * Return an n x m matrix representing the box after the rotation described above.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 * ["#"],
 * ["#"]]
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: box = [["#",".","*","."],
 * ["#","#","*","."]]
 * Output: [["#","."],
 * ["#","#"],
 * ["*","*"],
 * [".","."]]
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: box = [["#","#","*",".","*","."],
 * ["#","#","#","*",".","."],
 * ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 * [".","#","#"],
 * ["#","#","*"],
 * ["#","*","."],
 * ["#",".","*"],
 * ["#",".","."]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == box.length
 * n == box[i].length
 * 1 <= m, n <= 500
 * box[i][j] is either '#', '*', or '.'.
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
 * @Array
 * @TwoPointers
 * @Matrix
 * @medium <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class RotatingTheBox_1861 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new char[][]{{'#', '.', '#'}}, new char[][]{{'.'}, {'#'}, {'#'}}));
        tests.add(test(new char[][]{{'#', '.', '*', '.'}, {'#', '#', '*', '.'}}, new char[][]{{'#', '.'}, {'#', '#'}, {'*', '*'}, {'.', '.'}}));
        tests.add(test(new char[][]{{'#', '#', '*', '.', '*', '.'}, {'#', '#', '#', '*', '.', '.'}, {'#', '#', '#', '.', '#', '.'}}, new char[][]{{'.', '#', '#'}, {'.', '#', '#'}, {'#', '#', '*'}, {'#', '*', '.'}, {'#', '.', '*'}, {'#', '.', '.'}}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(char[][] box, char[][] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Box", "Expected"}, true, box, expected);

        char[][] output;
        boolean pass, finalPass = true;

        Solution sol = new Solution();

        output = sol.rotateTheBox(box);
        pass = CommonMethods.compareResultOutCome(output, expected, true);

        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;
    }

    static class Solution {

        public char[][] rotateTheBox(char[][] box) {
            int m = box.length;
            int n = box[0].length;
            char[][] result = new char[n][m];

            // Initialize the result grid with '.'
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] = '.';
                }
            }

            // Apply gravity to let stones fall to the lowest possible empty cell in each column
            for (int i = 0; i < m; i++) {
                int lowestRowWithEmptyCell = n - 1;
                // Process each cell in row `i` in reversed order
                for (int j = n - 1; j >= 0; j--) {
                    // Found a stone - let it fall to the lowest empty cell
                    if (box[i][j] == '#') {
                        // Place it in the correct position in the rotated grid
                        result[lowestRowWithEmptyCell][m - i - 1] = '#';
                        lowestRowWithEmptyCell--;
                    }
                    // Found an obstacle - reset `lowestRowWithEmptyCell` to the row directly above it
                    if (box[i][j] == '*') {
                        // Place the obstacle in the correct position in the rotated grid
                        result[j][m - i - 1] = '*';
                        lowestRowWithEmptyCell = j - 1;
                    }
                }
            }
            return result;
        }
    }

}
