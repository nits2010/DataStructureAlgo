package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2563;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.SumProblems._1.TwoSum_1;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:13/11/24
 * Question Category: 2563. Count the Number of Fair Pairs
 * Description: https://leetcode.com/problems/count-the-number-of-fair-pairs/description/
 * Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
 * <p>
 * A pair (i, j) is fair if:
 * <p>
 * 0 <= i < j < n, and
 * lower <= nums[i] + nums[j] <= upper
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
 * Output: 6
 * Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
 * Example 2:
 * <p>
 * Input: nums = [1,7,9,2,5], lower = 11, upper = 11
 * Output: 1
 * Explanation: There is a single fair pair: (2,3).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums.length == n
 * -109 <= nums[i] <= 109
 * -109 <= lower <= upper <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link  TwoSum_1}
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @Sorting <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class CountTheNumberOfFairPairs_2563 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(tests(new int[]{0, 1, 7, 4, 4, 5}, 3, 6, 6));
        tests.add(tests(new int[]{1, 7, 9, 2, 5}, 11, 11, 1));
        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(int[] input, int lower, int upper, long expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"input", "Expected"}, true, input, expected);

        long output;
        boolean pass, finalPass = true;

        SolutionTwoPointer solutionTwoPointer = new SolutionTwoPointer();
        output = solutionTwoPointer.countFairPairs(input, lower, upper);
        pass = output == expected;
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"TwoPointer", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;

    }

    static class SolutionTwoPointer {
        public long countFairPairs(int[] nums, int lower, int upper) {

            Arrays.sort(nums);

            return pairLessThanEqualToTarget(nums, upper) - pairLessThanEqualToTarget(nums, lower - 1);


        }

        private long pairLessThanEqualToTarget(int[] nums, int target) {
            int n = nums.length;
            int i = 0, j = n - 1;
            long pairs = 0;

            while (i < j) {

                if ((nums[i] + nums[j]) <= target) {
                    pairs += (j - i);
                    i++;
                } else {
                    j--;
                }

            }

            return pairs;
        }
    }


    static class SolutionBinarySearch {

        long lower_bound(int[] nums, int low, int high, int element) {
            while (low <= high) {
                int mid = low + ((high - low) / 2);
                if (nums[mid] >= element) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }

        long countFairPairs(int[] nums, int lower, int upper) {
            Arrays.sort(nums);
            long ans = 0;
            for (int i = 0; i < nums.length; i++) {
                // Assume we have picked nums[i] as the first pair element.

                // `low` indicates the number of possible pairs with sum < lower.
                long low = lower_bound(
                        nums,
                        i + 1,
                        nums.length - 1,
                        lower - nums[i]
                );

                // `high` indicates the number of possible pairs with sum <= upper.
                long high = lower_bound(
                        nums,
                        i + 1,
                        nums.length - 1,
                        upper - nums[i] + 1
                );

                // Their difference gives the number of elements with sum in the
                // given range.
                ans += (high - low);
            }
            return ans;
        }
    }


}
