package Java.LeetCode.combination.sum;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/combination-sum-iv/
 * <p>
 * 377. Combination Sum IV
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * <p>
 * Example:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * <p>
 * Therefore the output is 7.
 * <p>
 * <p>
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * <p>
 * Similar to {@link CombinationSumI}
 * Difference here is: We need to try every possibility. A number from given candidate array can be placed anywhere in ths solution list
 * <p>
 * Explanation: https://leetcode.com/problems/combination-sum-iv/discuss/372950/TLE-to-100-beat-or-Optimisation-or-step-by-step-or-7-solutions
 */
public class CombinationSumIV {


    public static void main(String[] args) {
        test(new int[]{2, 3, 6, 7}, 7, 4);
        test(new int[]{2, 3, 5}, 7, 5);
        test(new int[]{2, 3, 5}, 0, 1);
        test(new int[]{2, 3, 5}, 22, 912);

    }

    private static void test(int[] candidates, int target, int expected) {
        System.out.println("\n Candidates :" + Printer.toString(candidates) + " Target :" + target + " expected :" + expected);
        CombinationSumIVBacktracking.CombinationSumIVBacktrackingDFS combinationSumIDFS = new CombinationSumIVBacktracking.CombinationSumIVBacktrackingDFS();
        CombinationSumIVBacktracking.CombinationSumIVReverseBacktrackingDFS combinationSumIVDFSV2 = new CombinationSumIVBacktracking.CombinationSumIVReverseBacktrackingDFS();
        CombinationSumIVBacktracking.CombinationSumIVBacktrackingMemo memo = new CombinationSumIVBacktracking.CombinationSumIVBacktrackingMemo();

        CombinationSumIVDP.CombinationSumIVRecursive recursive = new CombinationSumIVDP.CombinationSumIVRecursive();

        CombinationSumIVDP.CombinationSumIVTopDown topDown = new CombinationSumIVDP.CombinationSumIVTopDown();
        CombinationSumIVDP.CombinationSumIVTopDownV2 topDownV2 = new CombinationSumIVDP.CombinationSumIVTopDownV2();
        CombinationSumIVDP.CombinationSumIVBottomUp bottomUp = new CombinationSumIVDP.CombinationSumIVBottomUp();

        System.out.println(" Backtracking DFS : " + combinationSumIDFS.combinationSum4(candidates, target));
        System.out.println(" Backtracking DFS V2 : " + combinationSumIVDFSV2.combinationSum4(candidates, target));
        System.out.println(" Backtracking DFS memo TOP Down : " + memo.combinationSum4(candidates, target));
        System.out.println(" recursive : " + recursive.combinationSum4(candidates, target));
        System.out.println(" DP  TOP Down : " + topDown.combinationSum4(candidates, target));
        System.out.println(" DP  TOP Down V2: " + topDownV2.combinationSum4(candidates, target));
        System.out.println(" DP  Bottom Up : " + bottomUp.combinationSum4(candidates, target));
    }

}


/**
 * Drive recurrence relation
 * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
 * Think about the recurrence relation first. How does the # of combinations of the target related to the # of combinations of numbers
 * that are smaller than the target?
 * <p>
 * So we know that target is the sum of numbers in the array. Imagine we only need one more number to reach target, this number can be any one in the array, right?
 * So the # of combinations of target, comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].
 * <p>
 * In the example given, we can actually find the # of combinations of 4 with the # of combinations of 3(4 - 1), 2(4- 2) and 1(4 - 3).
 * As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
 * <p>
 * Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, we can set comb[0] = 1.
 * <p>
 * EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way.
 * Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.
 * <p>
 * <p>
 * Recurrence relation
 * *     comb[target] = {
 * *         sum(comb[target - nums[i]]) target> 0
 * *         1: when target=0,
 * *     }
 * <p>
 * <p>
 * <p>
 * Similar to {@link Java.LeetCode.CoinChangeProblem} #CoinChangeNumberOfWays
 */

class CombinationSumIVDP {

    /**
     * Recurrence relation
     * *     comb[target] = {
     * *         sum(comb[target - nums[i]]) target> 0
     * *         1: when target=0,
     * *     }
     * <p>
     * Complexity: O(n*2^n)
     */
    static class CombinationSumIVRecursive {


        public int combinationSum4(int[] nums, int target) {

            if (nums == null || nums.length == 0)
                return 0;

            //base case
            if (target == 0)
                return 1;

            int res = 0;
            for (int i = 0; i < nums.length; i++)
                if (target >= nums[i])
                    res += combinationSum4(nums, target - nums[i]);

            return res;
        }


    }


    /**
     * Recurrence relation
     * *     comb[target] = {
     * *         sum(comb[target - nums[i]]) target> 0
     * *         1: when target=0,
     * *     }
     * Overlapping sub-problems; cache it
     * Complexity: O(n*n)
     */
    static class CombinationSumIVTopDown {

        public int combinationSum4(int[] nums, int target) {
            if (nums == null || nums.length == 0)
                return 0;
            int dp[] = new int[target + 1];
            Arrays.fill(dp, -1);
            return combinationSum4(nums, target, dp);
        }


        public int combinationSum4(int[] nums, int target, int dp[]) {
            if (target < 0)
                return 0;

            //base case
            if (target == 0)
                return 1;

            if (dp[target] != -1)
                return dp[target];

            int res = 0;
            for (int i = 0; i < nums.length; i++) //O(n)
                if (target >= nums[i])
                    res += combinationSum4(nums, target - nums[i]); //O(n)

            return dp[target] = res;
        }


    }

    /**
     * Recurrence relation
     * *     comb[target] = {
     * *         sum(comb[target - nums[i]]) target> 0
     * *         1: when target=0,
     * *     }
     * Overlapping sub-problems; cache it
     * Complexity: O(n*n)
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum IV.
     * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Combination Sum IV.
     */
    static class CombinationSumIVTopDownV2 {

        public int combinationSum4(int[] nums, int target) {
            if (nums == null || nums.length == 0)
                return 0;
            int dp[] = new int[target + 1];
            Arrays.fill(dp, -1);
            return combinationSum4(nums, 0, target, dp);
        }


        public int combinationSum4(int[] nums, int currSum, int target, int dp[]) {
            if (currSum > target)
                return 0;

            //base case
            if (target == currSum)
                return 1;

            if (dp[currSum] != -1)
                return dp[currSum];

            int res = 0;
            for (int i = 0; i < nums.length; i++) //O(n)
                res += combinationSum4(nums, currSum + nums[i], target, dp); //O(n)

            return dp[currSum] = res;
        }


    }

    /**
     * Recurrence relation
     * *     comb[target] = {
     * *         sum(comb[target - nums[i]]) target> 0
     * *         1: when target=0,
     * *     }
     * =>
     * dp[i] denotes number of ways to make sum=i using all nums
     * <p>
     * dp[i] = {
     * *        Sum { dp[target - nums[j] ; target>= nums[j] ; j<=0<=nums.length}
     * *        1: target = 0
     * * }
     * memory: dp[target+1]
     * Result: dp[target]
     * <p>
     * <p>
     * Complexity: O(n*n)
     * <p>
     * Runtime: 1 ms, faster than 84.17% of Java online submissions for Combination Sum IV.
     * Memory Usage: 34 MB, less than 100.00% of Java online submissions for Combination Sum IV.
     */
    static class CombinationSumIVBottomUp {

        public int combinationSum4(int[] nums, final int Target) {
            if (nums == null || nums.length == 0)
                return 0;


            int dp[] = new int[Target + 1];
            //base case
            dp[0] = 1;

            for (int target = 1; target <= Target; target++) {

                for (int j = 0; j < nums.length; j++) {

                    if (target >= nums[j])
                        dp[target] += dp[target - nums[j]];
                }
            }

            return dp[Target];
        }


    }


}

class CombinationSumIVBacktracking {

    /**
     * {@link CombinationSumIDFS}
     * Leetcode : TLE
     */
    static class CombinationSumIVBacktrackingDFS {

        public int combinationSum4(int[] candidates, int target) {

            if (candidates == null || candidates.length == 0)
                return 0;

            int response[] = {0};

            combinationSum4(candidates, 0, 0, target, response);
            return response[0];
        }

        private void combinationSum4(int[] candidates, int i, int currentSum, int target, int response[]) {

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (i >= candidates.length)
                return;


            //3. Our goal: when currentSum = target
            if (currentSum == target) {
                response[0]++;
                return;
            }

            //1. Our choices: We can choose a number from the list any number of times and all the numbers
            for (int s = 0; s < candidates.length; s++) {

                //Our constraints : We can't go beyond target, we can take more element than available in array
                if (currentSum + candidates[s] <= target) {
                    currentSum += candidates[s];

                    combinationSum4(candidates, s, currentSum, target, response);

                    //backtrack
                    currentSum -= candidates[s];
                }
            }

        }
    }


    /**
     * {@link CombinationSumIDFS}
     * Leetcode : TLE
     */
    static class CombinationSumIVReverseBacktrackingDFS {

        public int combinationSum4(int[] candidates, int target) {

            if (candidates == null || candidates.length == 0)
                return 0;

            return combinationSum4(candidates, 0, target);
        }

        private int combinationSum4(int[] candidates, int i, int target) {

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (i >= candidates.length)
                return 0;


            //3. Our goal: when currentSum = target
            if (0 == target) {

                return 1;
            }

            int res = 0;
            //1. Our choices: We can choose a number from the list any number of times and all the numbers
            for (int s = 0; s < candidates.length; s++) {

                //Our constraints : We can't go beyond target, we can take more element than available in array
                if (target - candidates[s] >= 0) {
                    target -= candidates[s];

                    res += combinationSum4(candidates, s, target);

                    //backtrack
                    target += candidates[s];
                }
            }
            return res;
        }
    }

    /**
     * Repeated problems, cache it
     */
    static class CombinationSumIVBacktrackingMemo {

        public int combinationSum4(int[] candidates, int target) {

            if (candidates == null || candidates.length == 0)
                return 0;

            int memo[] = new int[target + 1];
            Arrays.fill(memo, -1);
            return combinationSum4(candidates, 0, target, memo);
        }

        private int combinationSum4(int[] candidates, int i, int target, int memo[]) {

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (i >= candidates.length)
                return 0;


            //3. Our goal: when currentSum = target
            if (0 == target) {

                return 1;
            }

            if (memo[target] != -1)
                return memo[target];

            int res = 0;
            //1. Our choices: We can choose a number from the list any number of times and all the numbers
            for (int s = 0; s < candidates.length; s++) {

                //Our constraints : We can't go beyond target, we can take more element than available in array
                if (target - candidates[s] >= 0) {
                    target -= candidates[s];

                    res += combinationSum4(candidates, s, target, memo);

                    //backtrack
                    target += candidates[s];
                }
            }
            return memo[target] = res;
        }
    }


}