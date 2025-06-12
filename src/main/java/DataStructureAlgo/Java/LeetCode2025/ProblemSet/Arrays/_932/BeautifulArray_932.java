package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._932;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/12/2025
 * Question Title: 932. Beautiful Array
 * Link: https://leetcode.com/problems/beautiful-array/description
 * Description: An array nums of length n is beautiful if:
 * <p>
 * nums is a permutation of the integers in the range [1, n].
 * For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
 * Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * Output: [2,1,4,3]
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: [3,1,2,5,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1000
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
 * @Array
 * @Math
 * @DivideandConquer <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class BeautifulArray_932 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(4, new int[]{1, 3, 2, 4}));
        tests.add(test(5, new int[]{1, 5, 3, 2, 4}));
        tests.add(test(3, new int[]{1, 3, 2}));
        tests.add(test(7, new int[]{1, 5, 3, 7, 2, 6, 4}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int num, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"num", "Expected"}, true, num, expected);

        int[] output;
        boolean pass, finalPass = true;

        output = new Solution().beautifulArray(num);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * 2 * nums[k] = nums[i] + nums[j]
     * <p>
     * 2 * nums[k] will always produce an even number. Hence if left side is even then right side could be either (odd,odd) or (even,even)
     * => Even = odd + odd
     * Or
     * => Even = even + even
     * <p>
     * To make sure that, above condition never satisfied, we can place odd on left side of equation and evens on right side because
     * Odd = even + odd
     * Odd != even + even
     * <p>
     * This implies that, we can divide the array in two parts, left and right (for odd numbers, keep +1 on left side).
     * <p>
     * Example:
     * input    output
     * [1,2] => [1,2]
     * [1,3] => [1,3,2]
     * [1,4] => [1,3,2,4]
     * <p>
     * [1,n] => [1....(n+1)/2 , 2....n/2] as [ sum of 1...n is n*(n+1)/2]
     */
    static class Solution {
        public int[] beautifulArray(int n) {
            Map<Integer, int[]> mapOfNtoOutput = new HashMap<>();

            //cache base inputs
            mapOfNtoOutput.put(1, new int[]{1});
            mapOfNtoOutput.put(2, new int[]{1, 2});

            if (n <= 2)
                return mapOfNtoOutput.get(n);

            return generateBeautifulArray(mapOfNtoOutput, n);

        }

        private int[] generateBeautifulArray(Map<Integer, int[]> map, int n) {
            if (n <= 2)
                return map.get(n);

            //generate odd numbers
            int[] leftNums = generateBeautifulArray(map, (n + 1) / 2);

            //generate even numbers
            int[] rightNums = generateBeautifulArray(map, n / 2);

            //fill these numbers
            //generate a beautiful array
            int[] output = new int[n];


            int idx = 0;
            //fill odd
            for (int x : leftNums) {
                //extract odd
                output[idx++] = 2 * x - 1;
            }

            //fill even
            for (int x : rightNums) {
                //extract even
                output[idx++] = 2 * x;
            }

            //cache it
            map.put(n, output);
            return output;
        }

    }
}
