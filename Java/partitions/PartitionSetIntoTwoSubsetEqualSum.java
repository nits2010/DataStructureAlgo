package Java.partitions;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-25
 * Description:https://leetcode.com/problems/partition-equal-subset-sum
 *
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 */
public class PartitionSetIntoTwoSubsetEqualSum {

    public static void main(String args[]) {
        int nums[] = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int i : nums) total += i; // get total of all nums
        if (total % 2 == 1) return false; // if the total is odd, the array cannot be partitioned

//        return partitionSetIntoTwoSubsetRecursive(nums.length - 1, nums, 0, total / 2);

//        return partitionSetIntoTwoSubsetRecursive(nums, 0, 0, total / 2) == 0;

        return partitionSetIntoTwoSubsetDP(nums);
    }


    //forward
    private static int partitionSetIntoTwoSubsetRecursive(int[] nums, int i, int sum1, int sum2) {
        if (i > nums.length)
            return Integer.MAX_VALUE;

        if (i == nums.length)
            return Math.abs(sum1 - sum2);

        return Math.min(
                partitionSetIntoTwoSubsetRecursive(nums, i + 1, sum1, sum2), //Don't include current item in other set
                partitionSetIntoTwoSubsetRecursive(nums, i + 1, sum1 + nums[i], sum2)); // include current item in other set

    }

    //Backward
    public static boolean partitionSetIntoTwoSubsetRecursive(int i, int[] n, int sum, int target) {
        if (i < 0 || sum > target) return false;

        if (n[i] > target)
            return false; // Magic step. If the single integer is greater than the target, you return false;
        if (sum == target) return true;

        return partitionSetIntoTwoSubsetRecursive(i - 1, n, sum + n[i], target) // test adding the current integer (n[i]) to the sum
                || partitionSetIntoTwoSubsetRecursive(i - 1, n, sum, target); // test by not adding the current integer to the sum
    }


    //O(n*sum) / O(n*sum)
    //can be reduced to O(n*sum) / O(n)
    private static boolean partitionSetIntoTwoSubsetDP(int nums[]) {

        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0)
            return false;

        int n = nums.length;

        boolean dp[][] = new boolean[n + 1][sum + 1]; //is it possible to make sum j with i elements

        for (int i = 0; i <= n; i++)
            dp[i][0] = true; //we can take empty set to make sum = 0

        for (int i = 0; i <= sum; i++)
            dp[0][i] = false;

        dp[0][0] = true; //with 0 element, 0 sum is possible

        //dp[i][j] = dp[i-1][j] | dp[i-1][j-arr[i]]
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sum; j++) {

                //if ith element is not included, make sum j with i-1 elements
                dp[i][j] = dp[i - 1][j];

                if (nums[i - 1] <= j)
                    //if ith element included;
                    dp[i][j] = dp[i][j] | (dp[i - 1][j - nums[i - 1]]);
            }
        }
        return dp[n][sum / 2];

    }

}
