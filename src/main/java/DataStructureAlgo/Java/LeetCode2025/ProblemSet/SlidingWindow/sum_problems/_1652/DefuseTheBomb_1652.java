package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.sum_problems._1652;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 18/11/24
 * Question Title: 1652. Defuse the Bomb
 * Link: https://leetcode.com/problems/defuse-the-bomb/description/
 * Description:
 * You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.
 * <p>
 * To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
 * <p>
 * If k > 0, replace the ith number with the sum of the next k numbers.
 * If k < 0, replace the ith number with the sum of the previous k numbers.
 * If k == 0, replace the ith number with 0.
 * As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
 * <p>
 * Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: code = [5,7,1,4], k = 3
 * Output: [12,10,16,13]
 * Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
 * Example 2:
 * <p>
 * Input: code = [1,2,3,4], k = 0
 * Output: [0,0,0,0]
 * Explanation: When k is zero, the numbers are replaced by 0.
 * Example 3:
 * <p>
 * Input: code = [2,4,9,3], k = -2
 * Output: [12,5,6,13]
 * Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == code.length
 * 1 <= n <= 100
 * 1 <= code[i] <= 100
 * -(n - 1) <= k <= n - 1
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
 * @Array
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */
public class DefuseTheBomb_1652 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{5, 7, 1, 4}, 3, new int[]{12, 10, 16, 13}));
        tests.add(test(new int[]{1, 2, 3, 4}, 0, new int[]{0, 0, 0, 0}));
        tests.add(test(new int[]{2, 4, 9, 3}, -2, new int[]{12, 5, 6, 13}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] code, int k, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Code", "k", "Expected"}, true, code, k, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution_BruteForce solutionBruteForce = new Solution_BruteForce();
        output = solutionBruteForce.decrypt(code, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BruteForce", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution_ForwardBackward_SlidingWindow solutionForwardBackwardSlidingWindow = new Solution_ForwardBackward_SlidingWindow();
        output = solutionForwardBackwardSlidingWindow.decrypt(code, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"ForwardBackward", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution_SlidingWindow solutionSlidingWindow = new Solution_SlidingWindow();
        output = solutionSlidingWindow.decrypt(code, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"SlidingWindow", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Solution_BruteForce {
        public int[] decrypt(int[] code, int k) {
            int[] result = new int[code.length];
            // If k is 0, return the result directly.
            if (k == 0) {
                return result;
            }
            for (int i = 0; i < result.length; i++) {
                if (k > 0) {
                    // If k is greater than 0, store the sum of next k numbers.
                    for (int j = i + 1; j < i + k + 1; j++) {
                        result[i] += code[j % code.length];
                    }
                } else {
                    // If k is less than 0, store the sum of previous -1*k numbers.
                    for (int j = i - Math.abs(k); j < i; j++) {
                        result[i] += code[(j + code.length) % code.length];
                    }
                }
            }
            return result;
        }

    }

    /**
     * [5,7,1,4], k = 3 => [12,10, 16, 13]
     * [5,7,1,4], k = 2 => [8,5,9,12]
     * <p>
     * <p>
     * [5,7,1,4] k = 3  { Expand window to right, shrink from left, sum for k+1 elements (including itself}
     * s = 0, k = 3, i = 0
     * s = 5, k = 2, i = 1
     * s = 12, k = 1, i = 2
     * s = 13, k = 0, i = 3
     * s = 17, k = -1, i = 4-> i = 0 { 17-5 = 12 , }
     * s = 12, k = 0 i = 0
     * s = 17, k = -1, i = 1 { 17-7 = 10} => {12, 10}
     * s = 10, k = 0, i = 1
     * s = 17, k = -1, i = 2 { 17-1 = 16} => {12, 10, 16}
     * s = 16, k = 0, i = 2
     * s = 17, k = -1, i = 3 { 17-4 = 13} => { 12, 10, 16, 13}
     * <p>
     * [2,4,9,3], k = -2 => [12, 5, 6, 13]
     * <p>
     * [2,4,9,3]
     * s = 0, k = -2, i = 3
     * s = 3, k = -1, i = 2
     * s = 12, k = 0, i = 1
     * s = 16, k = 1, i = 0 => {16-3 = 13} => {13}
     * s = 13, k = 0, i = 0
     * s = 15, k = 1, i = -1 -> 3 => {15-9=6} => {,6,13}
     * s = 6 k = 0, i = 3
     * s = 9, k = 1, i = 2 => {9-4 = 5} => {5,6,13}
     * s = 5, k = 0, i = 2
     * s = 14 , k = 1, i = 3 { 14 - 2 = 12} => {12,5,6,13}
     */
    static class Solution_ForwardBackward_SlidingWindow {
        public int[] decrypt(int[] code, int k) {
            int n = code.length;

            if (k == 0) {
                return new int[n];
            }

            return k < 0 ? decodeBackward(code, k) : decodeForward(code, k);
        }

        private int[] decodeBackward(int[] code, int k) {

            int n = code.length;
            int right = n - 1;
            int index = n - 1;
            int s = 0;
            int temp = k;
            int[] result = new int[n];
            while (index >= 0) {

                s += code[right];
                right--;
                right = right < 0 ? n - 1 : right;
                temp++;

                if (temp == 1) {
                    s = s - code[index];
                    result[index] = s;
                    index--;
                    temp--;
                }

            }

            return result;
        }

        private int[] decodeForward(int[] code, int k) {
            int n = code.length;
            int right = 0;
            int index = 0;
            int s = 0;
            int temp = k;
            int[] result = new int[n];

            while (index < n) {

                //include this element in window
                s += code[right];

                //move ahead
                right = (right + 1) % n;

                //reduce count
                temp--;

                //all elements included + itself
                if (temp == -1) {
                    s = s - code[index]; // remove it-self

                    //result it
                    result[index] = s;

                    //move to next element
                    index++;

                    //reduce the window
                    temp++;
                }

            }

            return result;
        }
    }


    static class Solution_SlidingWindow {
        public int[] decrypt(int[] code, int k) {
            int n = code.length;

            if (k == 0) {
                return new int[n];
            }

            // start index of sum for k elements
            int start = 0;

            // end index of sum for k elements
            int end = 0;

            if (k < 0) {
                start = n - Math.abs(k); // shift n by k => n - k
                end = n - 1; // last element
            } else {
                start = 1; // leave 0 th index to exclude in sum
                end = k; // end of code
            }

            // calculate sum of k elements
            int sum = 0;
            for (int i = start; i <= end; i++)
                sum += code[i];

            int index = 0;
            int[] result = new int[n];

            while (index < n) {
                // current sum
                result[index] = sum;

                // leave element that is going out of window, start
                // shrink the window
                sum -= code[start % n];
                start++;

                // add element that is coming in the new window, end
                // expand the window
                end++;
                sum += code[end % n];

                index++;
            }

            return result;

        }
    }

}
