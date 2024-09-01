package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SumProblems;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.HashMap;
import java.util.Map;


/**
 * Author: Nitin Gupta
 * Date: 7/14/2024
 * Question Category: 1. Two Sum [easy]
 * Description: https://leetcode.com/problems/two-sum/description/
 * <p>
 * 1. Two Sum
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 *
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Array
 * @HashTable
 *
 *
 * <p>
 * Company Tags
 * -----

 *
 * @Editorial
 */


/**
 * Author: Nitin Gupta
 * Date: 7/14/2024
 * Description:
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum}
 */
public class TwoSum_1 {

    public static void main(String[] args) {
       test(new int[]{1, 8, 5, 4}, new int[]{1,0}, 9);
       test(new int[]{2,7,11,15}, new int[]{0,1}, 9);
       test(new int[]{3,2,4}, new int[]{1,2}, 6);
       test(new int[]{3,3}, new int[]{0,1}, 6);

    }

    private static void test(int[] nums, int[] expected, int target){
        Solution solution = new Solution();
        int []x = solution.twoSum(nums, target);
        boolean result = CommonMethods.equalsValuesWithoutOrder(x,expected);
        CommonMethods.resultPrint(nums, expected, x,"outcome", result);


    }


    static class Solution {
        /**
         * https://leetcode.com/problems/two-sum/submissions/1320877943/
         * 2 ms
         * Beat; 97.88%
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i=0; i<nums.length; i++){
                int candidate = target - nums[i];

                if(map.containsKey(candidate)){
                    return new int[]{i, map.get(candidate)};
                }
                map.put(nums[i], i);


            }
            return null;

        }
    }
}
