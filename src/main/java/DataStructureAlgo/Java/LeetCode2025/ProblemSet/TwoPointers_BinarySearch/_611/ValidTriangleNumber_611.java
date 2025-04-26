package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._611;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/26/2025
 * Question Title: 611. Valid Triangle Number
 * Link: https://leetcode.com/problems/valid-triangle-number/description/
 * Description: Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 * <p>
 * Input: nums = [4,2,3,4]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.ValidTriangleNumber}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Amazon
 * @Bloomberg
 * @Expedia
 * @Microsoft
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ValidTriangleNumber_611 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{2, 2, 3, 4}, 3));
        tests.add(test(new int[]{4, 2, 3, 4}, 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution s = new Solution();
        output = s.triangleNumber(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int triangleNumber(int[] nums) {

            //a,b,c will form a valid triangle if
            //a+b > c or a+c > b or b + c > a
            //means a sum of two sides is greater than the other.

            //let's fix each side as a,b,c and assume to find a+b>c
            //since we can fix a greater side 'c', we need to find two more elements a,b such that a<c and b<c
            //this is because of either of them is greater than c then their sum also be greater than c

            //this can be done by sorting array and then fix the greater side (last element in array) and search other a,b in between

            // [2,2,3,4]
            //c = 3, value=4
            //search [0..2] i.e. [2,2,3]
            //a=2, b=3 => 2+3 > 4; means all the elements when b moving towards a, will satisfy the condition, we and count them all, and reduce b
            //a=2, b=2 => 2+2 > 4, no, increase a -> break
            if (nums.length < 3) //minimum 3 sides are required
                return 0;

            Arrays.sort(nums);
            int count = 0;
            for (int i = nums.length - 1; i > 1; i--) { // c is at nums.length - 1, b = nums.length - 2 and a = nums.length - 3

                int c = nums[i];

                int l = 0;
                int r = i - 1;

                while (l < r) {

                    int a = nums[l];
                    int b = nums[r];

                    if (a + b > c) {
                        count += r - l;
                        r--;
                    } else {
                        l++;
                    }
                }
            }

            return count;

        }
    }
}
