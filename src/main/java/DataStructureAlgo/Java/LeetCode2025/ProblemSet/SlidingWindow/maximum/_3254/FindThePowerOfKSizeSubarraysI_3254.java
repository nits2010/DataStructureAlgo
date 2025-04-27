package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.maximum._3254;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.maximum._239.SlidingWindowMaximum_239;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/16/2024
 * Question Category: 3254. Find the Power of K-Size Subarrays I
 * Description: https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/description/?envType=daily-question&envId=2024-11-16
 * You are given an array of integers nums of length n and a positive integer k.
 * <p>
 * The power of an array is defined as:
 * <p>
 * Its maximum element if all of its elements are consecutive and sorted in ascending order.
 * -1 otherwise.
 * You need to find the power of all
 * subarrays
 * of nums of size k.
 * <p>
 * Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,3,2,5], k = 3
 * <p>
 * Output: [3,4,-1,-1,-1]
 * <p>
 * Explanation:
 * <p>
 * There are 5 subarrays of nums of size 3:
 * <p>
 * [1, 2, 3] with the maximum element 3.
 * [2, 3, 4] with the maximum element 4.
 * [3, 4, 3] whose elements are not consecutive.
 * [4, 3, 2] whose elements are not sorted.
 * [3, 2, 5] whose elements are not consecutive.
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], k = 4
 * <p>
 * Output: [-1,-1]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,2,3,2,3,2], k = 2
 * <p>
 * Output: [-1,3,-1,3,-1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n == nums.length <= 500
 * 1 <= nums[i] <= 105
 * 1 <= k <= n
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link SlidingWindowMaximum_239 }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class FindThePowerOfKSizeSubarraysI_3254 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{1, 3, 4}, 2, new int[]{-1, 4}));
        tests.add(test(new int[]{1, 2, 3, 4, 3, 4, 5, 6, 7, 2, 8, 9}, 4, new int[]{4, -1, -1, -1, 6, 7, -1, -1, -1}));
        tests.add(test(new int[]{1, 2, 3, 4, 3, 2, 5}, 3, new int[]{3, 4, -1, -1, -1}));
        tests.add(test(new int[]{2, 2, 2, 2, 2}, 4, new int[]{-1, -1}));
        tests.add(test(new int[]{3, 2, 3, 2, 3, 2}, 2, new int[]{-1, 3, -1, 3, -1}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "k", "Expected"}, true, nums, k, expected);

        int[] output;
        boolean pass, finalPass = true;

        SolutionUsingDeque solutionUsingDeque = new SolutionUsingDeque();
        output = solutionUsingDeque.resultsArray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"UsingDeque", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution solution = new Solution();
        output = solution.resultsArray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"UsingArray", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;

    }

    /**
     * T/S: O(n) / O(n)
     */
    static class SolutionUsingDeque {
        public int[] resultsArray(int[] nums, int k) {
            Deque<Integer> max = new LinkedList<>();

            int n = nums.length;
            int[] output = new int[n - k + 1];

            for (int i = 0; i < n; i++) {

                //manage max elements

                //evict, that goes out of window
                while (!max.isEmpty() && max.peekFirst() < i - k + 1)
                    max.pollFirst();

                //check consecutive order
                if (!max.isEmpty() && nums[i] != nums[i - 1] + 1)
                    max.clear(); //does not follow the constraints


                max.offerLast(i);


                if (i - k + 1 >= 0 && !max.isEmpty()) {

                    //if queue has k element, then it must be in increasing order + consequtive
                    int maxEle = -1;
                    if (max.size() == k) {
                        maxEle = nums[max.peekLast()];
                    }
                    output[i - k + 1] = maxEle;

                }


            }

            return output;

        }

    }

    static class Solution {
            public int[] resultsArray(int[] nums, int k) {
                int consecutiveCount = 1;

                int n = nums.length;
                int[] output = new int[n - k + 1];

                for (int i = 0; i < n; i++) {

                    if(i > 0 && nums[i] == nums[i - 1] + 1)
                        consecutiveCount++;
                    else
                        consecutiveCount = 1;

                    if (i - k + 1 >= 0) {

                        //if queue has k element, then it must be in increasing order + consecutive
                        int maxEle = -1;
                        if (consecutiveCount >= k) {
                            maxEle = nums[i];
                        }
                        output[i - k + 1] = maxEle;

                    }


                }

                return output;

            }

    }
}
