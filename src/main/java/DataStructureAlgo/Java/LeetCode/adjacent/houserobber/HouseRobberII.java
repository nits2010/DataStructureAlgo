package DataStructureAlgo.Java.LeetCode.adjacent.houserobber;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-28
 * Description: 213. House Robber II
 * https://leetcode.com/problems/house-robber-ii/
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2 numbers in the
 * sequence should be adjacent in the *circular array*.
 * <p>
 * input: [7,3,5,2,9]
 * output: 14 [5,9]
 * <p>
 * input: [1000,3,200,2,400,10000]
 * output: 10200 [200, 10000]
 * <p>
 * input: [1000,3,200,2,400,1000]
 * output: 1600 [1000,200,400]
 * <p>
 * input: [3000,1,1, 1,1,3000]
 * output:3002 [3000,1,1] or [1,1,3000]
 * <p>
 * input: [3000,6,2, 8,9,3000]
 * output:3014 [6,8,3000]  { 3000+2+9=3011 < 3014}
 * <p>
 * Extension of {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI}
 * <p>
 * [Adobe]
 */
public class HouseRobberII {
    public static void main(String[] args) {
        test(new int[]{7, 3, 5, 2, 9}, 14);
        test(new int[]{1000, 3, 200, 2, 400, 10000}, 10200);
        test(new int[]{3000, 1, 1, 1, 1, 300}, 3002);
        test(new int[]{1000, 3, 200, 2, 400, 1000}, 1600);
        test(new int[]{3000, 6, 2, 8, 9, 3000}, 3014);

    }

    private static void test(int[] nums, int expected) {
        System.out.println("\nInput :" + CommonMethods.toString(nums) + " Expected :" + expected);

        System.out.println("Idea 1: Two array DPS :" + HouseRobberIIDP.rob(nums));
        System.out.println("Idea 2: Two array DPS :" + HouseRobberIIDP.rob(nums));
        System.out.println("Idea 3: Linear :" + HouseRobberIILinear.rob(nums));
        System.out.println("Idea 4: Linear :" + HouseRobberIILinear.rob(nums));


    }

}

/**
 * {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI}
 */
class HouseRobberIIDP {

    public static int rob(int[] nums) {
        return maximumSumNoTwoAdjCircularArrayTwoArrayDp(nums);
    }

    /**
     * Apply the same logic as {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI} and build two
     * array
     * 1. Left to Right {dpLR}
     * 2. Right to left {dpRL}
     * <p>
     * Then take the maximum of dpLR[n-2] {skipping nums[n-1]} and dpRL[1]{skipping nums[0]}
     *
     * @param nums
     * @return
     */
    private static int maximumSumNoTwoAdjCircularArrayTwoArrayDp(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;


        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);


        int n = nums.length;
        int dpLR[] = new int[n];
        int dpRL[] = new int[n];

        dpLR[0] = nums[0];
        dpLR[1] = Math.max(nums[0], nums[1]);

        dpRL[n - 1] = nums[n - 1];
        dpRL[n - 2] = Math.max(nums[n - 1], nums[n - 2]);

        for (int i = 2, j = n - 3; i < nums.length; i++, j--) {

            //Build dpLR -> dp[i] = Max ( dp[i-2] + nums[i] , dp[i-1] )
            dpLR[i] = Math.max(dpLR[i - 2] + nums[i], dpLR[i - 1]);

            //Build dpRL -> dp[j] = Max ( dp[j+2] + nums[j] , dp[j+1] )
            dpRL[j] = Math.max(dpRL[j + 2] + nums[j], dpRL[j + 1]);
        }

        return Math.max(dpLR[n - 2], dpRL[1]);
    }


    /**
     * Apply the same logic as {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI} and build two
     * array
     * 1. Left to Right {dpLR}
     * 2. Right to left {dpRL}
     * <p>
     * Then take the maximum of dpLR[n-2] {skipping nums[n-1]} and dpRL[1]{skipping nums[0]}
     * <p>
     * We can build both array bits differently too.
     * <p>
     * since dpLR discarding n-1 element
     * and
     * dpRL discarding element 0.
     * <p>
     * build dpLR [0..n-2]
     * build dpRL [1...n-1]
     *
     * @param nums
     * @return
     */
    public static int maximumSumNoTwoAdjCircularArrayTwoArrayDpV2(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;


        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);


        int n = nums.length;
        int dpLR[] = new int[n]; //build dpLR [0..n-2]
        int dpRL[] = new int[n]; //build dpRL [1...n-1]

        dpLR[0] = nums[0];
        dpLR[1] = Math.max(nums[0], nums[1]);

        dpRL[n - 1] = nums[n - 1];
        dpRL[n - 2] = Math.max(nums[n - 1], nums[n - 2]);

        for (int i = 2, j = n - 3; i < nums.length - 1 && j > 0; i++, j--) {

            //Build dpLR -> dp[i] = Max ( dp[i-2] + nums[i] , dp[i-1] )
            dpLR[i] = Math.max(dpLR[i - 2] + nums[i], dpLR[i - 1]);

            //Build dpRL -> dp[j] = Max ( dp[j+2] + nums[j] , dp[j+1] )
            dpRL[j] = Math.max(dpRL[j + 2] + nums[j], dpRL[j + 1]);
        }

        return Math.max(dpLR[n - 2], dpRL[1]);
    }
}

/**
 * {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI} #MaximumSumNoTwoElementsAreAdjacentLinear
 */
class HouseRobberIILinear {

    public static int rob(int[] nums) {
        return maximumSumNoTwoAdjCircularArrayTwoArrayLinearSingleScan(nums);
    }

    /**
     * We can do in constant time by using logic as {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI} #MaximumSumNoTwoElementsAreAdjacentLinear
     *
     * @param nums
     * @return
     */
    public static int maximumSumNoTwoAdjCircularArrayTwoArrayLinear(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        //build dpLR [0..n-2] & build dpRL [1...n-1]
        return Math.max(
                maximumSumNoTwoAdjCircularArrayTwoArrayLinear(nums, 0, nums.length - 2),
                maximumSumNoTwoAdjCircularArrayTwoArrayLinear(nums, 1, nums.length - 1));


    }

    private static int maximumSumNoTwoAdjCircularArrayTwoArrayLinear(int nums[], int s, int e) {

        int including = 0;
        int excluding = 0;

        //Base case: n=1; then we'll include only
        including = nums[s];


        int max;

        for (int i = s + 1; i <= e; i++) {

            max = Math.max(including, excluding);

            including = excluding + nums[i]; //dp[i-2] + nums[i]
            excluding = max; //dp[i-1]

        }


        return Math.max(including, excluding);


    }


    /**
     * We can do in constant time by using logic as {@link MaximumSumNoTwoElementsAreAdjacent_HouseRobberI} #MaximumSumNoTwoElementsAreAdjacentLinear
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
     * Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for House Robber II.
     *
     * @param nums
     * @return
     */
    public static int maximumSumNoTwoAdjCircularArrayTwoArrayLinearSingleScan(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        //build dpLR [0..n-2] & build dpRL [1...n-1]

        int n = nums.length;

        int includingLR = nums[0];
        int excludingLR = 0;

        int includingRL = nums[1];
        int excludingRL = 0;
        int maxLR;
        int maxRL;


        int i = 1;
        int j = 2;

        for (; i < n - 1 && j < n; i++, j++) {

            maxLR = Math.max(includingLR, excludingLR);
            includingLR = excludingLR + nums[i]; //dp[i-2] + nums[i]
            excludingLR = maxLR; //dp[i-1]


            maxRL = Math.max(includingRL, excludingRL);
            includingRL = excludingRL + nums[j]; //dp[i-2] + nums[i]
            excludingRL = maxRL; //dp[i-1]

        }

        return Math.max(Math.max(includingLR, excludingLR), Math.max(includingRL, excludingRL));


    }
}