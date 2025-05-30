package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._268;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/30/2025
 * Question Title: 268. Missing Number
 * Link: https://leetcode.com/problems/missing-number/description/
 * Description: Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * <p>
 * Output: 8
 * <p>
 * Explanation:
 * <p>
 * n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 * <p>
 * <p>
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._41.FirstMissingPositive_41}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @HashTable
 * @Math
 * @BinarySearch
 * @BitManipulation
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Microsoft
 * @Amazon
 * @Facebook
 * @Adobe
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MissingNumber_268 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 0, 1}, 2));
        tests.add(test(new int[]{0, 1}, 2));
        tests.add(test(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}, 8));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_BitManipulation().missingNumber(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BitManipulation-XOR", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_Sum().missingNumber(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Math-Sum", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_CycleSort().missingNumber(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"CycleSort", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution_BinarySearch().missingNumber(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class Solution_BitManipulation {
        public int missingNumber(int[] nums) {
            // a ^ b ^ b = a
            // let n = 3 [1,2,3]
            // nums      [0,1,3]
            // 1^0^2^1^3^3 RE-WRITE AS 0^1^1^2^3^3 = all which are duplicate will remove hence only 0^2 left which is 2
            // since array nums are [1,n] then we can loop from i ->[1,n] and xor it with array nums.
            // all numbers that are matched will be discarded and remaining would be missing.

            int xor = nums.length;
            for (int i = 0; i < nums.length; i++) {
                xor = xor ^ i;
                xor = xor ^ nums[i];
            }
            return xor;
        }
    }

    /**
     * We can apply math, since numbers are [1,n] hence its total sum = (n*(n+1)/2)
     * calculates the sum of array element.
     * The missing number  = totalSum - arraySum
     */
    static class Solution_Sum {
        public int missingNumber(int[] nums) {
            int n = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int actualSumOf1ToN = n * (n + 1) / 2;

            return actualSumOf1ToN - sum; //since nth couldn't be placed and not at possition then it can only be missing
        }
    }

    /**
     * Numbers are in [0,n] range, however, the array can have at max [0,n-1] range numbers. This means that the largest number "means n" can not be placed at its correct possition if
     * we apply cycle sort. Consider only those which are nums[i] <n in cycle sort and sort the array. FInd the first index at which the elements are not at correct possition
     */
    static class Solution_CycleSort {
        public int missingNumber(int[] nums) {
            int n = nums.length;

            int i = 0;
            while (i < n) {

                int index = nums[i]; //range [0,n]

                if (nums[i] < n && nums[i] != nums[index]) {
                    //swap
                    int temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                } else {
                    i++;
                }
            }

            for (i = 0; i < n; i++) {
                if (nums[i] != i)
                    return i;
            }
            return n; //since nth couldn't be placed and not at position then it can only be missing
        }
    }


    static class Solution_BinarySearch {
        public int missingNumber(int[] nums) {
            Arrays.sort(nums);

            int low = 0, high = nums.length; //since the nth number could be the missing number
            while (low < high) {
                int mid = (low + high) >>> 1;

                if (nums[mid] > mid) {
                    high = mid;
                } else {
                    low = mid + 1;
                }

            }
            return low;
        }
    }


}
