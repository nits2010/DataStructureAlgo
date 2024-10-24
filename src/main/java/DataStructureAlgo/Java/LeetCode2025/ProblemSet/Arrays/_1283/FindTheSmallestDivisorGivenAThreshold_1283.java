package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1283;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date:24/10/24
 * Question Category: 1283. Find the Smallest Divisor Given a Threshold
 * Description: https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/?envType=problem-list-v2&envId=o2pr208d
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * <p>
 * Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * <p>
 * The test cases are generated so that there will be an answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 * <p>
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 104
 * 1 <= nums[i] <= 106
 * nums.length <= threshold <= 106
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._2560.HouseRobberIV_2560} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1011.CapacityToShipPackagesWithinDDays_1011}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @Array
 * @BinarySearch
 * @medium <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class FindTheSmallestDivisorGivenAThreshold_1283 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 2, 5, 9}, 6, 5);
        test &= test(new int[]{44, 22, 33, 11, 1}, 5, 44);
        test &= test(new int[]{21212,10101,12121}, 1000000, 1);
        test &= test(new int[]{12,50,11,75,57,12,73,4,69,78}, 649, 1);
        test &= test(new int[]{12,50,11,75,57,12,73,4,69,78}, 649, 1);
        CommonMethods.printResult(test);
    }

    private static boolean test(int []nums, int threshold, int expected) {
        CommonMethods.print(new String[]{"Nums", "Threshold", "Expected"}, true, nums, threshold, expected);
        int output;
        boolean pass, finalPass = true;

        Solution sol = new Solution();
        output = sol.smallestDivisor(nums, threshold);
        pass = output == expected;
        CommonMethods.print(new String[]{"Binary Search", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;

        return finalPass;
    }

    static class Solution {
        public int smallestDivisor(int[] nums, int threshold) {
            int low = 1;
            int high = 0;

            for (int ele : nums) {
                high = Math.max(high, ele);
            }

            while (low <= high) {
                int mid = (high + low) >> 1;

                if (isPossible(nums, threshold, mid)) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }
            return low;
        }

        private boolean isPossible(int[] nums, int threshold, int divisior) {
            int divisionSum = 0;
            for (int ele : nums) {
                // divisionSum += (int)Math.ceil(ele / (double)divisior);
                divisionSum += (ele + divisior - 1) / divisior;
            }

            return divisionSum <= threshold;
        }

    }
}
