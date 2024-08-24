package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 13/10/19
 * Description: https://leetcode.com/problems/find-pivot-index/
 * 724. Find Pivot Index
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * <p>
 * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
 * <p>
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * nums = [1, 7, 3, 6, 5, 6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input:
 * nums = [1, 2, 3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * <p>
 * <p>
 * Note:
 * <p>
 * The length of nums will be in the range [0, 10000].
 * Each element nums[i] will be an integer in the range [-1000, 1000].
 */
public class FindPivotIndex {

    public static void main(String[] args) {
        boolean test = test(new int[]{1, 7, 3, 6, 5, 6}, 3)
                && test(new int[]{1, 2, 3}, -1)
                && test(new int[]{1}, 0);
        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("\nNums: " + CommonMethods.toString(nums));

        System.out.println("Expected                            : " + expected);
        int linearObtained = new FindPivotIndexLinear().pivotIndex(nums);
        int linearObtainedConstantMemory = new FindPivotIndexLinearConstantMemory().pivotIndex(nums);
        System.out.println("Linear obtained                     : " + linearObtained);
        System.out.println("Linear obtained Constant Memory     : " + linearObtainedConstantMemory);

        return CommonMethods.equalsValues(expected, linearObtained, linearObtainedConstantMemory);
    }


}

/**
 * Algorithm:
 * 1. Scan the array and build the prefix and suffix sum such that
 * prefixSum[i] = prefixSum[i-1] + nums[i-1]
 * suffixSum[i] = suffixSum[i+1] + nums[i+1]
 * <p>
 * 2. After check where both are equal from left side
 * <p>
 * O(n)/O(n)
 * <p>
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Find Pivot Index.
 * Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Find Pivot Index.
 */
class FindPivotIndexLinear {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 0;
        right[n - 1] = 0;

        for (int i = 1, j = n - 2; i < n; i++, j--) {
            left[i] = left[i - 1] + nums[i - 1];
            right[j] = right[j + 1] + nums[j + 1];
        }

        for (int i = 0; i < n; i++) {
            if (left[i] == right[i])
                return i;
        }

        return -1;

    }
}


/**
 * In above algorithm, we scan and build the array. Essentially the suffix array we don't need as we need
 * leftmost index. Which means, we can compute the sum of all the element on left side of current index
 * and reduce it from total sum to get sum of all the element on right side including this element.
 * Reduce the element to get right side sum only.
 * <p>
 * Algorithm:
 * 1. Compute the overall sum
 * 2. keep computing the sum of element on left side excluding current element
 * 3. compute the right side sum and check
 * <p>
 * O(n)/ O(1)
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Find Pivot Index.
 * Memory Usage: 38.5 MB, less than 100.00% of Java online submissions for Find Pivot Index.
 */
class FindPivotIndexLinearConstantMemory {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int sum = 0;
        int left = 0;

        for (int num : nums)
            sum += num;

        for (int i = 0; i < nums.length; i++) {
            if (i != 0)
                left += nums[i - 1];

            //(sum - left) gives the sum of element on right side including this element
            // reduce nums[i] to get sum of right side element excluding this element
            if (sum - left - nums[i] == left)
                return i;

        }

        return -1;

    }
}