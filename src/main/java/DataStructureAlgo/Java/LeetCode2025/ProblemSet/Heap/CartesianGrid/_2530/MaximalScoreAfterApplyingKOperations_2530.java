package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap.CartesianGrid._2530;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date:14/10/24
 * Question Category: 2530. Maximal Score After Applying K Operations
 * Description: https://leetcode.com/problems/maximal-score-after-applying-k-operations/description/?envType=daily-question&envId=2024-10-14
 * You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
 *
 * In one operation:
 *
 * choose an index i such that 0 <= i < nums.length,
 * increase your score by nums[i], and
 * replace nums[i] with ceil(nums[i] / 3).
 * Return the maximum possible score you can attain after applying exactly k operations.
 *
 * The ceiling function ceil(val) is the least integer greater than or equal to val.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,10,10,10,10], k = 5
 * Output: 50
 * Explanation: Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.
 * Example 2:
 *
 * Input: nums = [1,10,3,3,3], k = 3
 * Output: 17
 * Explanation: You can do the following operations:
 * Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
 * Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
 * Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
 * The final score is 10 + 4 + 3 = 17.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length, k <= 105
 * 1 <= nums[i] <= 109
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
 * @Greedy
 * @Heap(PriorityQueue)
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */

public class MaximalScoreAfterApplyingKOperations_2530 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{10, 10, 10, 10, 10}, 5, 50);
        test &= test(new int[]{1, 10, 3, 3, 3}, 3, 17);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[] nums, int k, int expected) {
        System.out.println("--------------------------------");
        System.out.println("Input:" + CommonMethods.toString(nums) + " k:" + k + " expected:" + expected);
        Solution solution = new Solution();
        long obtained = solution.maxKelements(nums, k);
        boolean pass = obtained == expected;
        System.out.println("Obtained:" + obtained + " pass :" + (pass?"Passed":"Failed"));
        return pass;
    }

    static class Solution {
        public long maxKelements(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

            for(int n: nums){
                pq.offer(n);
            }

            long score = 0;
            while (k >0 && !pq.isEmpty()) {
                int n = pq.poll();
                score += n;
                // pq.offer((int)Math.ceil(n/3.0));
                pq.add((n + 2) / 3);
                k--;
            }
            return score;
        }

    }
}
