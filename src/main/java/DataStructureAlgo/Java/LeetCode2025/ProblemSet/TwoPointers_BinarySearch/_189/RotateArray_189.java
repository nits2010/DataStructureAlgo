package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._189;


import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 15/04/25
 * Question Title: 189. Rotate Array
 * Link: https://leetcode.com/problems/rotate-array/description/
 * Description:Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @TwoPointers
 * @Math
 *
 * <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Google
 * @Facebook
 * @Yahoo
 * @Adobe
 * @Bloomberg
 * @Cisco
 * @GoldmanSachs
 * @Oracle
 * @Paypal
 * @Snapchat
 * @Uber
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */
public class RotateArray_189 {
    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{5, 6, 7, 1, 2, 3, 4}));
        tests.add(test(new int[]{-1, -100, 3, 99}, 2, new int[]{3, 99, -1, -100}));
        tests.add(test(new int[]{1, 2}, 1, new int[]{2, 1}));
        tests.add(test(new int[]{1, 2}, 2, new int[]{1, 2}));
        tests.add(test(new int[]{1, 2, 3, 4, 5, 6, 7}, 0, new int[]{1, 2, 3, 4, 5, 6, 7}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] numbers, int k, int[] expected) {
        CommonMethods.printTestOutcome(new String[]{"Numbers", "k", "Expected"}, true, numbers, k,  expected);


        boolean pass, finalPass = true;
        int []output ;

        Solution_ExtraArray solutionExtraArray = new Solution_ExtraArray();
        Solution_Last_Half_Reverse solutionLastHalfReverse = new Solution_Last_Half_Reverse();
        Solution solution = new Solution();

        output = Arrays.copyOf(numbers, numbers.length);
        solutionExtraArray.rotate(output, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionExtraArray", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = Arrays.copyOf(numbers, numbers.length);
        solutionLastHalfReverse.rotate(output, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionLastHalfReverse", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = Arrays.copyOf(numbers, numbers.length);
        solution.rotate(output, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"FirstHalfReverse", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_ExtraArray {
        public void rotate(int[] nums, int k) {
            if(nums.length == k)
                return ;
            int n = nums.length;
            k = k % n;
            int []result = new int [n] ;
            int r = 0, i = n-k;

            while(i<n)
                result[r++] = nums[i++];

            i=0;
            while(i<n-k)
                result[r++] = nums[i++];

            for(i = 0; i<n; i++)
                nums[i] = result[i];

        }
    }

    //1,2,3,4,5,6,7
//1,2,3,4,7,6,5  reverse (nums, n-k, n-1);
//4,3,2,1,7,6,5  reverse (nums, 0, n-k-1);
//5,6,7,1,2,3,4  reverse (nums, 0, n-1);
    static class Solution_Last_Half_Reverse {
        public void rotate(int[] nums, int k) {
            if(nums.length == k)
                return ;
            int n = nums.length;
            k = k % n;

            reverse (nums, n-k, n-1);
            reverse (nums, 0, n-k-1);
            reverse (nums, 0, n-1);

        }

        private void reverse(int []nums, int i, int j){
            while (i<j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
    }


    //1,2,3,4,5,6,7
//7,6,5,4,3,2,1  reverse (nums, 0, n-1);
//5,6,7,4,3,2,1  reverse (nums, 0, k-1);
// 5,6,7,1,2,3,4 reverse (nums, k, n-1);
    static class Solution {
        public void rotate(int[] nums, int k) {
            if(nums.length == k)
                return ;
            int n = nums.length;
            k = k % n;

            reverse (nums, 0, n-1);
            reverse (nums, 0, k-1);
            reverse (nums, k, n-1);

        }

        private void reverse(int []nums, int i, int j){
            while (i<j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
    }

}

