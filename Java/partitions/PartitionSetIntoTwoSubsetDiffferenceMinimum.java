package Java.partitions;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-25
 * Description:
 * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.
 * <p>
 * If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
 * <p>
 * Example:
 * <p>
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 */
public class PartitionSetIntoTwoSubsetDiffferenceMinimum {

    public static void main(String args[]) {

        int nums[] = {3,1,4,2,2,1};
        int difference = partitionSetIntoTwoSubsetMinimumDifference(nums);
        System.out.println(difference);
    }

    private static int partitionSetIntoTwoSubsetMinimumDifference(int[] nums) {

        return partitionSetIntoTwoSubsetMinimumDifferenceDP(nums);
    }

    //Complexity: Exponential
    //-------------------------------------------------------------------------------------
    private static int partitionSetIntoTwoSubsetMinimimDiffferenceRecursive(int[] nums) {

        int sum = Arrays.stream(nums).sum();
        return partitionSetIntoTwoSubsetMinimumDifferenceRecursive(nums, 0, 0, sum);
    }

    private static int partitionSetIntoTwoSubsetMinimumDifferenceRecursive(int[] nums, int i, int sum1, int sum2) {
        if (i > nums.length)
            return Integer.MAX_VALUE;

        if (i == nums.length)
            return Math.abs(sum1 - sum2);

        return Math.min(
                partitionSetIntoTwoSubsetMinimumDifferenceRecursive(nums, i + 1, sum1, sum2),
                partitionSetIntoTwoSubsetMinimumDifferenceRecursive(nums, i + 1, sum1 + nums[i], sum2 - nums[i]));

    }
    //-------------------------------------------------------------------------------------

    /**
     * Same as partition the set with equal sum
     *
     * {@link PartitionSetIntoTwoSubsetEqualSum}
     *
     * @param nums
     * @return
     * O(n*sum) / O(n*sum)
     * //can be reduced to O(n*sum) / O(n)
     */
    private static int partitionSetIntoTwoSubsetMinimumDifferenceDP(int nums[]) {

        int sum = Arrays.stream(nums).sum();
        int n = nums.length;
        boolean dp[][] = new boolean[n + 1][sum + 1]; //is it possible to make sum j with i elements

        for (int i = 0; i <= n; i++)
            dp[i][0] = true; //we can take empty set to make sum = 0

        for (int i = 0; i <= sum; i++)
            dp[0][sum] = false;

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

        //now if we want the difference is minimum as near to 0, then dp[n][sum/2] should be true will make difference 0
        //otherwise we need to keep reducing the sum 1 by 1 and check

        for (int s = sum / 2; s >= 0; s--) {

            if (dp[n][s]) {
                return sum - 2 * s;
            }
        }

        return Integer.MAX_VALUE;

    }


}
