package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1695;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/6/2025
 * Question Title: 1695. Maximum Erasure Value
 * Link: https://leetcode.com/problems/maximum-erasure-value/description
 * Description: You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 * <p>
 * Return the maximum score you can get by erasing exactly one subarray.
 * <p>
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 * Example 2:
 * <p>
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
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
 * @HashTable
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Cashfree <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumErasureValue_1695 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{4, 2, 4, 5, 6}, 17));
        tests.add(test(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}, 8));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionMap().maximumUniqueSubarray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Map", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionSet().maximumUniqueSubarray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Set", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionMap {
        public int maximumUniqueSubarray(int[] nums) {
            int n = nums.length;
            int windowStart = 0, windowEnd = 0;

            int maxScore = Integer.MIN_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            int currentScore = 0;

            while (windowEnd < n) {

                //extend the window
                map.merge(nums[windowEnd], 1, Integer::sum);
                currentScore += nums[windowEnd];
                // System.out.println(map);

                //shirnk the window
                while (map.size() != windowEnd - windowStart + 1) {

                    if (map.merge(nums[windowStart], -1, Integer::sum) == 0) {
                        map.remove(nums[windowStart]);
                    }
                    currentScore -= nums[windowStart];
                    // System.out.println("removed "+nums[windowStart] + " "+currentScore + " "+map);
                    windowStart++;

                }

                maxScore = Math.max(maxScore, currentScore);

                windowEnd++;
            }
            return maxScore;

        }
    }

    static class SolutionSet {
        public int maximumUniqueSubarray(int[] nums) {
            int n = nums.length;
            int windowStart = 0, windowEnd = 0;

            int maxScore = Integer.MIN_VALUE;
            Set<Integer> set = new HashSet<>();
            int currentScore = 0;

            while (windowEnd < n) {

                //extend the window
                if (!set.contains(nums[windowEnd])) {
                    currentScore += nums[windowEnd];
                    set.add(nums[windowEnd]);
                    maxScore = Math.max(maxScore, currentScore);
                    windowEnd++;
                } else {
                    //shirnk the window
                    set.remove(nums[windowStart]);
                    currentScore -= nums[windowStart];
                    windowStart++;
                }

            }
            return maxScore;

        }
    }
}
