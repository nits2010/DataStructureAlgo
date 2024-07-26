package DataStructureAlgo.Java.LeetCode2025.medium;

import DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.Arrays;

/**
 *  Author: Nitin Gupta
 *  * Date: 7/22/2024
 *  * Description: https://leetcode.com/problems/product-of-array-except-self/description/
 *
 *  Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        test(new int[]{1,2,3,4}, new int[]{24,12,8,6});
        test(new int[]{-1,1,0,-3,3}, new int[]{0,0,9,0,0});



    }

    private static void test(int[] input, int[] expectedOutput) {
        Solution solution = new Solution();
        int [] result = solution.productExceptSelfAvoidFill(input);
        boolean output = GenericPrinter.equalsValues(expectedOutput, result);
        System.out.println("\n Input :" + Arrays.toString(input) + "\n expectedOutput : " + Arrays.toString(expectedOutput) + " returned output : " + Arrays.toString(result) + " result : " + output);

    }

   static class Solution {

        /** Prefix product and suffix product
         * <a href="https://leetcode.com/problems/product-of-array-except-self/submissions/1329904310/">...</a>
         * 3 ms
         * Beats 23.67%
         */
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length ==0)
                return nums;

            int [] output = new int[nums.length];
            Arrays.fill(output,1);

            //prefix product
            int product = 1;
            for (int i=1; i<nums.length; i++){
                product = product*nums[i-1];
                output[i] = product;
            }

            //suffix product
            product = 1;
            for (int i=nums.length-2; i>=0; i--){
                product = product*nums[i+1];
                output[i] = output[i]*product;
            }

            return output;
        }

       /**O(n)/O(1)
        * https://leetcode.com/problems/product-of-array-except-self/submissions/1329914592/
        * 2 ms
        * Beats 86.30%
        * @param nums
        * @return
        */
       public int[] productExceptSelfAvoidFill(int[] nums) {
           if (nums == null || nums.length ==0)
               return nums;

           int [] output = new int[nums.length];

           //prefix product
           // Set value as prev
           // Iterate forward for product of nums and update prev for next interaction
           int product = 1;
           for (int i=0; i<nums.length; i++){
               output[i] = product;
               product = product*nums[i];

           }

           //suffix product
           // Set value as prev*in-place
           // Iterate backward for product of nums and update prev for next interaction
           product = 1;
           for (int i=nums.length-1; i>=0; i--){
               output[i] = output[i]*product;
               product = product*nums[i];

           }

           return output;
       }
    }
}
