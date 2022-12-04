package DataStructureAlgo.Java.LeetCode;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-05-20
 * Description: https://leetcode.com/problems/maximum-subarray/
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarrayKadans {

    public int maxSubArray(final List<Integer> A) {

        int maxSoFar = Integer.MIN_VALUE;
        int currentMax = 0;

        for (Integer ele : A) {

            currentMax = Math.max(currentMax + ele, ele);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }
}
