package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.bitManipulations._3133;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/9/2024
 * Question Category: 3133. Minimum Array End
 * Description: https://leetcode.com/problems/minimum-array-end/description/?envType=daily-question&envId=2024-11-09
 * You are given two integers n and x. You have to construct an array of positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between all elements of nums is x.
 * <p>
 * Return the minimum possible value of nums[n - 1].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, x = 4
 * <p>
 * Output: 6
 * <p>
 * Explanation:
 * <p>
 * nums can be [4,5,6] and its last element is 6.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2, x = 7
 * <p>
 * Output: 15
 * <p>
 * Explanation:
 * <p>
 * nums can be [7,15] and its last element is 15.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n, x <= 108
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
 * @medium
 * @BitManipulation <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MinimumArrayEnd_3133 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(3, 4, 6));
        tests.add(test(2, 7, 15));
        tests.add(test(3, 21, 29));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int n, int x, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"N", "X", "Expected"}, true, n, x, expected);

        long output;
        boolean pass, finalPass = true;
        Solution solution = new Solution();
        output = solution.minEnd(n, x);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionBitManipulation solutionBitManipulation = new SolutionBitManipulation();
        output = solutionBitManipulation.minEnd(n, x);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BitManipulation", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Solution {

        public long minEnd(int n, int x) {
            long output = x;

            while (--n > 0)
                output = (output + 1) | x;

            return output;
        }
    }


    static class SolutionBitManipulation {

        public long minEnd(int n, int x) {
            long output = 0;
            n = n - 1;

            int[] binaryX = new int[64]; // long has 64 bits
            int[] binaryN = new int[64];

            //get binary representation of x and n in 64 bits
            // the MSB most significant bit will change to LSB
            long tempX = x, tempN = n;
            for (int i = 0; i < 64; i++) {

                binaryX[i] = (int) (tempX >> i) & 1;

                binaryN[i] = (int) (tempN >> i) & 1;
            }

            //start finding the first bit set to '0' and change it to 1/0 based on binaryN
            //do this n times
            int xi = 0, ni = 0;

            while (xi < 64) {

                //find the first unset bit
                while (xi < 64 && binaryX[xi] != 0) {
                    xi++;
                }

                if (xi < 64) {
                    binaryX[xi] = binaryN[ni];
                    xi++;
                    ni++;
                }
            }

            //now binaryX will have the number which is our answer.
            //convert it to decimal
            for (int i = 0; i < 64; i++) {
                if (binaryX[i] == 1) {
                    output += (long) Math.pow(2, i);
                }
            }

            return output;


        }


    }

}
