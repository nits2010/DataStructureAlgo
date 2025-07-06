package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming.HouseRobber._2560;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming.HouseRobber._337.HouseRobberIII_337;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/24/2024
 * Question Category: 2560. House Robber IV
 * Description: https://leetcode.com/problems/house-robber-iv/description/
 * There are several consecutive houses along a street, each of which has some money inside. There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.
 * <p>
 * The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.
 * <p>
 * You are given an integer array nums representing how much money is stashed in each house. More formally, the ith house from the left has nums[i] dollars.
 * <p>
 * You are also given an integer k, representing the minimum number of houses the robber will steal from. It is always possible to steal at least k houses.
 * <p>
 * Return the minimum capability of the robber out of all the possible ways to steal at least k houses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,5,9], k = 2
 * Output: 5
 * Explanation:
 * There are three ways to rob at least 2 houses:
 * - Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
 * - Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
 * - Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
 * Therefore, we return min(5, 9, 9) = 5.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1], k = 2
 * Output: 2
 * Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= (nums.length + 1)/2
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.nonleetcode.PainterPartitionProblemBinarySearch}
 * extension {@link HouseRobberIII_337}
 * <p><p>
 * Tags
 * -----
 *
 * @minMax
 * @Array
 * @BinarySearch
 * @medium <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class HouseRobberIV_2560 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{2, 3, 5, 9}, 2, 5);
        test &= test(new int[]{2, 7, 9, 3, 1}, 2, 2);
        test &= test(new int[]{4, 4, 4, 4}, 2, 4);
        CommonMethods.printAllTestOutCome(test);

    }

    private static boolean test(int[] nums, int k, int expected) {
        System.out.println("-------------------------------------------------");
        System.out.println("Nums : " + Arrays.toString(nums) + " | k : " + k + " | Expected : " + expected);

        Solution solution = new Solution();
        int result = solution.minCapability(nums, k);
        boolean pass = result == expected;
        System.out.println("Result : " + result + " | Pass :" + (pass ? " Pass" : " Fail"));
        return pass;
    }

    /**
     * Here we need to find the minimum of maximum capability (the max stolen item from a single robbed house).
     * Now, assume that we have `n` houses but k = 1 then in such cases, the robber can rob only one house at max, and the minimum of all would be our solution, which is nothing but the minimum value of the array.
     * and assume that we have `n` houses but k = n in such cases; Then robber can rob all the houses, and the minimum capability is nothing but the maximum value of the array.
     * However, a robber cannot rob adjacent houses, which makes 1<=k<=n/2, but the terminology is still the same.
     * <p>
     * This means that robber minimum capability lies between [min, max] range of the array element.
     * <p>
     * Now, since we have a range, the best friend of ours to find the solution is Binary Search. However, there is a big challenge to know that, with mid-value, how many houses can be robbed.
     * This can be obtained by seeing that, if the robber starts robbing house such that he cannot rob more than `mid` value, how many houses he can rob at max.
     * 1. If the maximum house is greater than k, then we are way beyond our range , reduce to [low, mid-1]
     * 2. if the maximum house is less than k, then we need more to steal, hence the range will increase [mid+1, high]
     * 3. otherwise, we are within our range. In such cases, where robber will rob exactly `k` houses, then we have to minimize the value of `mid` hence our range will reduce to [low, mid]
     * <p>
     * Complexity
     * Since, we're always doing binary search between [min, max] range; then its complexity will be O(log(max)) that makes total complexity O(n*log(max))
     */
    static class Solution {
        public int minCapability(int[] nums, int k) {

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int val : nums) {
                min = Math.min(min, val);
                max = Math.max(max, val);
            }

            //[min, max] range
            int low = min, high = max;

            while (low < high) {

                int mid = low + (high - low) / 2;

                int requireK = requireK(nums, k, mid);

                if (requireK == k) {
                    high = mid;
                } else if (requireK > k) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }
            return low;
        }

        int requireK(int[] nums, int k, int mid) {
            int currentK = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    currentK++;
                    i++; //skip the next house and then for-loop make it next to next house.
                }

                //we have robbed more than k houses, stop robbing further.
                if (currentK > k)
                    return currentK;
            }
            return currentK;
        }
    }
}
