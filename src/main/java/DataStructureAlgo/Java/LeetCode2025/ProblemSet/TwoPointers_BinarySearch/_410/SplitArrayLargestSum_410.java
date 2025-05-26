package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._410;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/22/2025
 * Question Title: 410. Split Array Largest Sum
 * Link: https://leetcode.com/problems/split-array-largest-sum/description/
 * Description: Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= k <= min(50, nums.length)
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.SplitArrayLargestSum}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BinarySearch
 * @DynamicProgramming
 * @Greedy
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Baidu
 * @Bloomberg
 * @DiDi
 * @Facebook
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SplitArrayLargestSum_410 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{7, 2, 5, 10, 8}, 2, 18));
        tests.add(test(new int[]{1, 2, 3, 4, 5}, 2, 9));
        tests.add(test(new int[]{1, 4, 4}, 3, 4));
        tests.add(test(new int[]{2,3,1,2,4,3}, 5, 4)); // if you do buckets == k this test will fail, hence its requirement buckets <= k

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionBinarySearch().splitArray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BS", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new SolutionBinarySearchV2().splitArray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BS-V2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Since we need to divide the array in definite number of subarray K and with only possible sum [0, totalSum], that makes the search space
     * limited. And with limited search space where we need to minimize the sum of a largest sum such that [0, totalSum] converts to [0,MAX+1] at max, makes it to binary searchable since [0,sum] is sorted.
     * <p>
     * We will do binary search b/w [0,sum] such that we will find that with mid-sum, is it possible to divide an array in k subarray with <= mid-sum
     */
    static class SolutionBinarySearch {
        public int splitArray(int[] nums, int k) {
            int n = nums.length;

            long high = 0;
            for (int num : nums) {
                high += num;
            }

            long low = 0, mid;
            long result = high;

            while (low <= high) {

                mid = (low + high) >>> 1;

                if (isPossible(nums, k, mid)) {
                    result = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }
            return (int) result;
        }

        boolean isPossible(int[] nums, int k, long requiredSum) {

            long currentSum = 0;
            int buckets = 1;
            for (int i = 0; i < nums.length; ) {

                if (currentSum + nums[i] <= requiredSum) {
                    currentSum += nums[i];
                    i++;
                } else {
                    buckets++;
                    currentSum = 0;
                }

                if (buckets > k)
                    return false;
            }
            return buckets <= k;
        }
    }

    /**
     * Since we need to divide the array in definite number of subarray K and with only possible sum [MAX, totalSum], that makes the search space
     * limited. And with limited search space where we need to minimize the sum of a largest sum such that [MAX, totalSum] converts to [MAX,MAX+1] at max, makes it to binary searchable since [0,sum] is sorted.
     * <p>
     * We will do binary search b/w [0,sum] such that we will find that with mid-sum, is it possible to divide an array in k subarray with <= mid-sum
     */
    static class SolutionBinarySearchV2 {
        public int splitArray(int[] nums, int k) {
            int n = nums.length;
            long low = 0, mid;

            long high = 0;
            for (int num : nums) {
                high += num;
                low = Math.max(low, num);
            }

            if (k == 1)
                return (int) high;

            if (k == nums.length)
                return (int) low;


            long result = high;

            while (low <= high) {

                mid = (low + high) >>> 1;

                if (isPossible(nums, k, mid)) {
                    result = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }
            return (int) result;
        }

        boolean isPossible(int[] nums, int k, long requiredSum) {

            long currentSum = 0;
            int buckets = 1;
            for (int i = 0; i < nums.length; ) {

                if (currentSum + nums[i] <= requiredSum) {
                    currentSum += nums[i];
                    i++;
                } else {
                    buckets++;
                    currentSum = 0;
                }

                if (buckets > k)
                    return false;
            }
            return buckets <= k;
        }
    }

}
