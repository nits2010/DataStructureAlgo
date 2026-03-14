package DataStructureAlgo.Java.LeetCode2025.ProblemSet.backtrackings.combinationalSum._377;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Combination Sum IV
 * Link: https://leetcode.com/problems/combination-sum-iv/
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

class CombinationSumIV_377 {
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