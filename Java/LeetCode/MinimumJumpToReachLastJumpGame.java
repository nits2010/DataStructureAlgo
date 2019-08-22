package Java.LeetCode;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-21
 * Description:
 * https://leetcode.com/problems/jump-game/
 * https://leetcode.com/problems/jump-game-ii/
 *
 * https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then we cannot move through that element.
 * <p>
 * Examples:
 * <p>
 * Input :  arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output :  3 (1-> 3 -> 8 -> 9)
 * <p>
 * <p>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class MinimumJumpToReachLastJumpGame {


    public static void main(String args[]) {
        int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};


        System.out.println("Can jump test...........\n");
        System.out.println("......................\n");


        System.out.println("Backtracking...........\n");
        System.out.println(CanJumpToReachLastBackTracking.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(CanJumpToReachLastBackTracking.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(CanJumpToReachLastBackTracking.canJump(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}));


        System.out.println("DP...........\n");
        System.out.println(CanJumpToReachLastDP.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(CanJumpToReachLastDP.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(CanJumpToReachLastDP.canJump(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}));


        System.out.println("Linear...........\n");
        System.out.println(CanJumpToReachLastLinear.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(CanJumpToReachLastLinear.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(CanJumpToReachLastLinear.canJump(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}));


        System.out.println("Min jump test...........\n");
        System.out.println("......................\n");


        System.out.println("DP...........\n");
        System.out.println(MinimumJumpToReachLastDP.minJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(MinimumJumpToReachLastDP.minJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(MinimumJumpToReachLastDP.minJump(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}));


        System.out.println("Linear...........\n");
        System.out.println(MinimumJumpToReachLastLinear.minJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(MinimumJumpToReachLastLinear.minJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(MinimumJumpToReachLastLinear.minJump(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}));
    }


}


/**
 * We can solve this using backtracking.
 * Idea is, we'll try every index and try to reach end, if succeed return true otherwise false
 * <p>
 * Our choices: We can take 1 to n steps depends on the given value of array[current] as n
 * Our Constraints:
 * 1. We can't jump more then a given index value.
 * 2. We can't jump outside the array
 * Our Goal: We need to reach end of the array ( last index element). Hence when current = nums.length - 1 We are done
 * <p>
 * LEETCODE: TLE
 */
class CanJumpToReachLastBackTracking {


    public static boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0)
            return false;


        Printer.print(nums);
        return canJump(nums, 0);

    }

    private static boolean canJump(int[] nums, int current) {

        /**
         * Our Constraints: We can't jump outside the array
         */
        if (current >= nums.length)
            return false;

        /**
         * Our Goal: We need to reach end of the array ( last index element). Hence when current = nums.length - 1 We are done
         */
        if (current == nums.length - 1)
            return true;

        for (int j = 1; j <= nums[current]; j++)
            if (canJump(nums, j + current))
                return true;

        return false;
    }
}


/**
 * We can solve this problem using dynamic programming as you see we are unnecessary evaluating the same index again and again in above solution.
 * <p>
 * Lets
 * Dp[i] is the minimum jump required to reach end from index i
 * <p>
 * Dp[i] = Min { 1+ Min { Dp[j] if nums[j] >= (i-j}}
 * <p>
 * Dp[n-1] will be our solution
 */
class CanJumpToReachLastDP {


    public static boolean canJump(int[] nums) {

        if (null == nums || nums.length == 0)
            return false;
        Printer.print(nums);

        int n = nums.length;
        boolean dp[] = new boolean[n];

        dp[0] = true;

        for (int i = 1; i < n; i++) {

            boolean min = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= (i - j) && dp[j]) {
                    min = dp[j];
                    break;
                }
            }


            dp[i] = min;

        }

        return dp[n - 1];
    }
}

class CanJumpToReachLastLinear {


    public static boolean canJump(int[] nums) {
        Printer.print(nums);
        int reachAble = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= reachAble) {
                reachAble = i;
            }
        }
        return reachAble == 0;
    }
}

/**
 * We can solve this problem using dynamic programming as you see we are unnecessary evaluating the same index again and again in above solution.
 * <p>
 * Lets
 * Dp[i] is the minimum jump required to reach end from index i
 * <p>
 * Dp[i] = Min { 1+ Min { Dp[j] if nums[j] >= (i-j}}
 * <p>
 * Dp[n-1] will be our solution if its -1 then False otherwise true
 */
class MinimumJumpToReachLastDP {


    public static int minJump(int[] nums) {

        if (null == nums || nums.length == 0)
            return -1;

        Printer.print(nums);
        int n = nums.length;
        int dp[] = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {

            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= (i - j) && dp[j] < min)
                    min = dp[j];
            }

            if (min == Integer.MAX_VALUE)
                dp[i] = min;
            else
                dp[i] = min + 1;

        }

        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];
    }
}

class MinimumJumpToReachLastLinear {


    /**
     * Minimum Jump required
     *
     * @param nums
     * @return
     */
    public static int minJump(int[] nums) {

        if (null == nums || nums.length == 0 || nums.length == 1)
            return 0;

        Printer.print(nums);
//        if (nums[0] == 0)
//            return 0;

        int jump = 1;

        int maxReach = nums[0];
        int steps = nums[0];

        int i = 1;
        while (i < nums.length) {

            if (i == nums.length - 1)
                return jump;

            maxReach = Math.max(maxReach, i + nums[i]);
            steps--;

            if (steps == 0) {
                jump++;

                if (i >= maxReach)
                    return -1;

                steps = maxReach - i;
            }
            i++;

        }
        return -1;


    }
}
