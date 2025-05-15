package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._2379;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/13/2025
 * Question Title: 2379. Minimum Recolors to Get K Consecutive Black Blocks
 * Link: https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/description
 * Description: You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.
 * <p>
 * You are also given an integer k, which is the desired number of consecutive black blocks.
 * <p>
 * In one operation, you can recolor a white block such that it becomes a black block.
 * <p>
 * Return the minimum number of operations needed such that there is at least one occurrence of k consecutive black blocks.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: blocks = "WBBWWBBWBW", k = 7
 * Output: 3
 * Explanation:
 * One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
 * so that blocks = "BBBBBBBWBW".
 * It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
 * Therefore, we return 3.
 * Example 2:
 * <p>
 * Input: blocks = "WBWBBBW", k = 2
 * Output: 0
 * Explanation:
 * No changes need to be made, since 2 consecutive black blocks already exist.
 * Therefore, we return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i] is either 'W' or 'B'.
 * 1 <= k <= n
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
 * @String
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumRecolorsToGetKConsecutiveBlackBlocks_2379 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("WBBWWBBWBW", 7, 3));
        tests.add(test("WBWBBBW", 2, 0));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String blocks, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"blocks", "k", "Expected"}, true, blocks, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionSlidingWindow().minimumRecolors(blocks, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(n)/O(1)
    static class SolutionSlidingWindow {
        public int minimumRecolors(String blocks, int k) {
            int whiteCount = Integer.MAX_VALUE;
            char[] temp = blocks.toCharArray();

            int start = 0, end = 0;
            int currentWhiteCount = 0;
            while (end < blocks.length()) {

                currentWhiteCount += temp[end] == 'W' ? 1 : 0;

                if (end - start + 1 == k) {
                    whiteCount = Math.min(whiteCount, currentWhiteCount);

                    currentWhiteCount -= temp[start] == 'W' ? 1 : 0;
                    start++;
                }


                end++;
            }
            return whiteCount;
        }
    }
}
