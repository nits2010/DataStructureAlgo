package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1590;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date:03/10/24
 * Question Category: 1590. Make Sum Divisible by P
 * Description: https://leetcode.com/problems/make-sum-divisible-by-p
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 *
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 *
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= p <= 109
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Array
 * @HashTable
 * @PrefixSum
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 *
 * @Editorial https://leetcode.com/problems/make-sum-divisible-by-p/editorial/?envType=daily-question&envId=2024-10-03
 */

public class MakeSumDivisibleByP_1590 {


    public static void main(String[] args) {
        boolean test = true;
        test &= test (new int[]{3,1,4,2}, 6, 1);
        test &= test (new int[]{6,3,5,2}, 9, 2);
        test &= test (new int[]{1,2,3}, 3, 0);
        test &= test (new int[]{1,2,3}, 7, -1);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] nums, int p, int expected) {
       System.out.println("-----------------------------------------");
       System.out.println("Input: nums = " + Arrays.toString(nums) + ", p = " + p + " Expected : "+expected);

       Solution solution = new Solution();
       int output = solution.minSubarray(nums, p);
       boolean pass = output == expected;
       System.out.println("Output: "+output + " pass :"+(pass ? "Passed" : "Failed"));
       return pass;
    }

    /**
     We need to ensure that the sum of elements in the array is divisible by p, which can be represented as sum % p == 0. This can be achieved in two ways:

     * The entire sum % p is 0 which means no element needs to be removed.
     * The sum of some elements, called sumToBeRemoved, needs to be removed from the sum in order to make it divisible by p, represented as (sum - sumToBeRemoved) % p = 0.

         ( sum - sumToBeRemove ) % p = 0
         ( sum %p - sumToBeRemove %p ) %p = 0
         => sum % p = sumToBeRemove % p
         => sum % p = targetSum

     To find the smallest subarray whose sum is divisible by p, we need to identify the target sum which is subArraySum % p = targetSum. If multiple target sums are observed,
     For example;
     [3,1,4,2] Sum = 10, p = 6 then targetSum = 10 % 6 = 4
     There are two subarray whose sum is 4; [3,1] and [4] but we need to find the smallest one, here the smallest one is the later one [4].

     we need to take the subarray with minimum length. To efficiently keep track of the previously seen target sums, we can use a HashMap.

     The HashMap will store the target sum (subarraySum % p) and when it was seen. Since subArraySum = 0 can also be a choice which means no elements need to be removed,
     the initial value of the HashMap will be 0:-1.

     Example:
     For [3,1,4,2], where the sum is 10 and p is 6, the targetSum would be 10 % 6 = 4.
     The HashMap would store the values:
     map = [3%6: 0] -> [4%6: 1] -> [8%6: 2] -> [10%6: 3], [0:-1]
     => [0:-1, 3:0, 4:1, 2:2, 4:3 ]
     We have seen the target sum two times:

     At index = 0, prefixSum = 3, currentPrefixSumMod = 3 and required = 3 - 4 = -1 + 6 = 5, which is not present in the map.
     At index = 1, prefixSum = 4, currentPrefixSumMod = 4 and required = 4 - 4 = 0, which is present in the map at index -1. The length of the subarray would be 1 - (-1) = 2.
            Means, subarray  from to index (-1,1]  i.e. [3,1] is the sub-array that can be removed to get remainder subarray [4,2] sum %p= 0.
     At index = 2, prefixSum = 8, currentPrefixSumMod = 2 and required = 2 - 4 = -2 + 6 = 4, which is present in the map at index 1. The length of the subarray would be 2 - 1 = 1.
            Means, subarray from to index (1,2] which is [4] is the sub-array that can be removed to get remainder subarray [3,1,2] sum %p= 0.
     At index = 3, prefixSum = 10, currentPrefixSumMod = 4 and required = 4 - 4 = 0, which is present in the map at index -1. The length of the subarray would be 3 - (-1) = 4.

     T/S: O(n) / O(n)

     To know more details: see https://leetcode.com/problems/make-sum-divisible-by-p/editorial/

     */
    static class Solution {
        public int minSubarray(int[] nums, int p) {
            if(nums == null || nums.length == 0)
                return -1;

            long sum = 0;

            //get total sum
            for (int num : nums) sum += num;

            //target sum
            int target = (int) (sum % p);

            //if entire array sum is % p then return 0
            if(target == 0)
                return 0;

            Map<Integer, Integer> prefixSumModulo = new HashMap<>();
            //Sum 0 is always possible when removing no element from the array.
            prefixSumModulo.put(0, -1);


            long prefixSum = 0;
            int minSubArrayLength = nums.length;

            for (int i =0 ; i<nums.length; i++){

                //calculate prefix sum
                prefixSum = prefixSum + nums[i];

                int currentPrefixSumMod = (int) (prefixSum % p);

                //required prefix sum to remove
                int prefixSumToRemove = currentPrefixSumMod - target ;

                //if prefixSumToRemove is negative then add p to it to make it positive, that will make overall under [1,p-1] only.
                if(prefixSumToRemove < 0)
                    prefixSumToRemove += p; //this will always be under [1,p] range

                if(prefixSumModulo.containsKey(prefixSumToRemove)){
                    minSubArrayLength = Math.min(minSubArrayLength, i - prefixSumModulo.get(prefixSumToRemove));
                }

                prefixSumModulo.put(currentPrefixSumMod, i);


            }


            return minSubArrayLength == nums.length ? -1 : minSubArrayLength;
        }
    }

}
