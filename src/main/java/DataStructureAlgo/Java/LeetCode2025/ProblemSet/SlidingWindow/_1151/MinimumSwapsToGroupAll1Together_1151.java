package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1151;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/14/2025
 * Question Title: 1151. Minimum Swaps to Group All 1's Together 🔒
 * Link: https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/description/
 * https://leetcode.ca/2019-01-24-1151-Minimum-Swaps-to-Group-All-1's-Together/
 * https://github.com/doocs/leetcode/blob/main/solution/1100-1199/1151.Minimum%20Swaps%20to%20Group%20All%201's%20Together/README_EN.md
 * Description: Description
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation: There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0] using 2 swaps.
 * [0,0,1,1,1] using 1 swap.
 * The minimum is 1.
 * Example 2:
 * <p>
 * Input: data = [0,0,0,1,0]
 * Output: 0
 * Explanation: Since there is only one 1 in the array, no swaps are needed.
 * Example 3:
 * <p>
 * Input: data = [1,0,1,0,1,0,0,1,1,0,1]
 * Output: 3
 * Explanation: One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= data.length <= 105
 * data[i] is either 0 or 1.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1493.LongestSubarrayOf1AfterDeletingOneElement_1493}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @SlidingWindow
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p><p>
 * Company Tags
 * -----
 * @Expedia
 * @Amazon
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumSwapsToGroupAll1Together_1151 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 0, 1, 0, 1}, 1));
        tests.add(test(new int[]{0, 0, 0, 1, 0}, 0));
        tests.add(test(new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}, 3));
        tests.add(test(new int[]{1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1}, 2));
        tests.add(test(new int[]{0, 0, 0, 0, 0}, 0));


        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().minSwaps(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int minSwaps(int[] data) {
            int n = data.length;
            int onesCount = Arrays.stream(data).sum();

            //for one count either 0 or 1 won't require any swaps
            if (onesCount < 2|| onesCount == n)
                return 0;

            int windowSize = onesCount;

            int start = 0, end = 0;
            int zeroCountInWindow = 0;
            int minSwaps = n + 1;

            while (end < n) {
                if (data[end] == 0) {
                    zeroCountInWindow++;
                }

                if (end - start + 1 == windowSize) {
                    //the number of zero in the window, woould require a swap with a 1
                    minSwaps = Math.min(minSwaps, zeroCountInWindow);

                    //exit the window
                    zeroCountInWindow -= data[start] == 0 ? 1 : 0;
                    start++;
                }

                end++;
            }
            return minSwaps;
        }
    }
}
