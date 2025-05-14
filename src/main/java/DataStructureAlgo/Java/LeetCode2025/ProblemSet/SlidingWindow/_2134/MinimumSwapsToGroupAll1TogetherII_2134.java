package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._2134;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/14/2025
 * Question Title: 2134. Minimum Swaps to Group All 1's Together II
 * Link:
 * Description: A swap is defined as taking two distinct positions in an array and swapping the values in them.
 * <p>
 * A circular array is defined as an array where we consider the first element and the last element to be adjacent.
 * <p>
 * Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,1,1,0,0]
 * Output: 1
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [0,0,1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0,0,0] using 1 swap.
 * [1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
 * There is no way to group all 1's together with 0 swaps.
 * Thus, the minimum number of swaps required is 1.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1,1,0,0,1,1,0]
 * Output: 2
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
 * [1,1,1,1,1,0,0,0,0] using 2 swaps.
 * There is no way to group all 1's together with 0 or 1 swaps.
 * Thus, the minimum number of swaps required is 2.
 * Example 3:
 * <p>
 * Input: nums = [1,1,0,0,1]
 * Output: 0
 * Explanation: All the 1's are already grouped together due to the circular property of the array.
 * Thus, the minimum number of swaps required is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1493.LongestSubarrayOf1AfterDeletingOneElement_1493}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1151.MinimumSwapsToGroupAll1Together_1151}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Expedia
 * @Amazon <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumSwapsToGroupAll1TogetherII_2134 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{0, 1, 0, 1, 1, 0, 0}, 1));
        tests.add(test(new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0}, 2));
        tests.add(test(new int[]{1, 1, 0, 0, 1}, 0)); //since an array is circular, so last 1 and initial ones are together
        tests.add(test(new int[]{1, 0, 0, 1, 1}, 0)); //since an array is circular, so last 1 and initial ones are together
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionSlidingWindow().minSwaps(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionSlidingWindow {
        public int minSwaps(int[] data) {
            int n = data.length;
            int onesCount = 0;
            for (int d : data) {
                onesCount += d;
            }

            //for one count either 0 or 1 won't require any swaps
            if (onesCount < 2 || onesCount == n)
                return 0;

            int windowSize = onesCount;

            int start = 0, end;
            int zeroCountInWindow = 0;
            int minSwaps = n + 1;

            //build the window of windowSize
            for (end = 0; end < windowSize; end++) {
                zeroCountInWindow += data[end] == 0 ? 1 : 0;
            }

            while (start < n) {

                //the number of zero in the window, woould require a swap with a 1
                minSwaps = Math.min(minSwaps, zeroCountInWindow);

                //shrink the window
                zeroCountInWindow -= data[start] == 0 ? 1 : 0;
                start++;

                //expand the window
                zeroCountInWindow += data[end % n] == 0 ? 1 : 0;
                end++;
            }
            return minSwaps;
        }
    }
}
