package DataStructureAlgo.Java.LeetCode2025.ProblemSet.math._1033;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/10/2025
 * Question Title: 1033. Moving Stones Until Consecutive
 * Link: https://leetcode.com/problems/moving-stones-until-consecutive/description/
 * Description: There are three stones in different positions on the X-axis. You are given three integers a, b, and c, the positions of the stones.
 * <p>
 * In one move, you pick up a stone at an endpoint (i.e., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints. Formally, let's say the stones are currently at positions x, y, and z with x < y < z. You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.
 * <p>
 * The game ends when you cannot make any more moves (i.e., the stones are in three consecutive positions).
 * <p>
 * Return an integer array answer of length 2 where:
 * <p>
 * answer[0] is the minimum number of moves you can play, and
 * answer[1] is the maximum number of moves you can play.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2, c = 5
 * Output: [1,2]
 * Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
 * Example 2:
 * <p>
 * Input: a = 4, b = 3, c = 2
 * Output: [0,0]
 * Explanation: We cannot make any moves.
 * Example 3:
 * <p>
 * Input: a = 3, b = 5, c = 1
 * Output: [1,2]
 * Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= a, b, c <= 100
 * a, b, and c have different values.
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
 * @Math
 * @Brainteaser <p><p>
 * Company Tags
 * -----
 * @Facebook <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MovingStonesUntilConsecutive_1033 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(1, 2, 5, new int[]{1, 2}));
        tests.add(test(4, 3, 2, new int[]{0, 0}));
        tests.add(test(3, 5, 1, new int[]{1, 2}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int a, int b, int c, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"a", "b", "c", "Expected"}, true, a, b, c, expected);


        int[] output;
        boolean pass, finalPass = true;

        Solution s = new Solution();
        output = s.numMovesStones(a, b, c);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int[] numMovesStones(int a, int b, int c) {
            int[] stones = new int[]{a, b, c};
            Arrays.sort(stones);

            int min = 0, max = 0;
            int x = stones[0];
            int y = stones[1];
            int z = stones[2];


            //if elements are consecutive, no moves possible
            if (z - x == 2)
                return new int[]{min, max};

            if (Math.min(y - x, z - y) <= 2)
                min = 1;
            else
                min = 2;


            max = z - x - 2; // 2 we can not put back to its own position and any occupied posistion (y)
            return new int[]{min, max};
        }
    }
}
