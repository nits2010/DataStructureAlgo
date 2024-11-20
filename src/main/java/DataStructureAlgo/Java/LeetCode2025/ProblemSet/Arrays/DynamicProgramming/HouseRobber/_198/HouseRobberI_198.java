package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._198;

import DataStructureAlgo.Java.LeetCode.adjacent.houserobber.HouseRobberI;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.Knapsack;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/20/2024
 * Question Category: 198. House Robber
 * Description: https://leetcode.com/problems/house-robber/description/
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * File reference
 * -----------
 * Duplicate {@link HouseRobberI}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.NumberPattern._746.MinCostClimbingStairs_746}
 * extension {@link }
 * DP-BaseProblem {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DynamicProgramming <p><p>
 *
 * Company Tags
 * -----
 * @Adobe
 * @Airbnb
 * @Amazon
 * @Apple
 * @Bloomberg
 * @ByteDance
 * @Cisco
 * @Expedia
 * @GoldmanSachs
 * @Google
 * @HBO
 * @LinkedIn
 * @Microsoft
 * @Quora
 * @Uber
 * @WalmartLabs <p><p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link SolutionLinear}
 */
public class HouseRobberI_198 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{10000, 400 , 2 , 200}, 10200);
        test &= test(new int[]{5, 5, 10, 100, 10, 5}, 110);
        test &= test(new int[]{1, 2, 3}, 4);
        test &= test(new int[]{1, 20, 3}, 20);
        test &= test(new int[]{1, 20}, 20);
        test &= test(new int[]{20}, 20);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("-------------------------------------------------");
        System.out.println("Input :" + CommonMethods.toString(nums) + " Expected : " + expected);

        int output;
        boolean pass, finalPass = true;

        SolutionRecursive solutionRecursive = new SolutionRecursive();
        output = solutionRecursive.rob(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Recursive : " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        SolutionRecursiveMemoTopDown solutionRecursiveMemoTopDown = new SolutionRecursiveMemoTopDown();
        output = solutionRecursiveMemoTopDown.rob(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Top Down : " + output + " Pass : " + (pass ? "Pass" : "Fail"));


        SolutionBottomUp solutionBottomUp = new SolutionBottomUp();
        output = solutionBottomUp.rob(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Bottom Up : " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        SolutionLinear solutionLinear = new SolutionLinear();
        output = solutionLinear.rob(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Linear 1 : " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        output = solutionLinear.robV2(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Linear 2 : " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        return finalPass;
    }

    static class SolutionRecursive {
        public int rob(int[] nums) {
            int n = nums.length;
            return rob(nums, n, 0);
        }

        public int rob(int[] nums, int n, int i) {
            if (i >= n)
                return 0;


            int include = nums[i] + rob(nums, n, i + 2);
            int exclude = rob(nums, n, i + 1);

            return Math.max(include, exclude);
        }

    }

    static class SolutionRecursiveMemoTopDown {
        public int rob(int[] nums) {
            int n = nums.length;
            int[] memo = new int[n];
            Arrays.fill(memo, -1);
            return rob(nums, n, 0, memo);
        }

        public int rob(int[] nums, int n, int i, int[] memo) {
            if (i >= n)
                return 0;

            if (memo[i] != -1)
                return memo[i];

            int include = nums[i] + rob(nums, n, i + 2, memo);
            int exclude = rob(nums, n, i + 1, memo);

            return memo[i] = Math.max(include, exclude);
        }

    }

    static class SolutionBottomUp {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1)
                return nums[0];
            int[] memo = new int[n];
            Arrays.fill(memo, -1);

            memo[0] = nums[0];
            memo[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < n; i++) {
                memo[i] = Math.max(memo[i - 2] + nums[i], memo[i - 1]);
            }

            return memo[n - 1];
        }


    }

    static class SolutionLinear {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1)
                return nums[0];

            int firstHouse = nums[0];
            int secondHouse = Math.max(firstHouse, nums[1]);
            int max = secondHouse;

            for (int i = 2; i < n; i++) {
                int currentHouse = nums[i];
                //either we do rob on the current house ( they can take firstHouse), or we don't and take lastHouse.
                max = Math.max(firstHouse + currentHouse, secondHouse);
                firstHouse = secondHouse;
                secondHouse = max;
            }
            return max;


        }

        public int robV2(int[] nums) {
            int n = nums.length;
            if (n == 1)
                return nums[0];

            int include = nums[0];
            int exclude = 0;
            int max = 0;

            for (int i = 1; i < n; i++) {
                int currentHouse = nums[i];
                //either we do rob on the current house ( they can take firstHouse), or we don't and take lastHouse.
                max = Math.max(include, exclude);
                include = exclude + currentHouse;
                exclude = max;
            }
            return Math.max(include, exclude);


        }


    }
}
