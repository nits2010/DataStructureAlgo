package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._213;

import DataStructureAlgo.Java.LeetCode.adjacent.houserobber.HouseRobberII;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/20/2024
 * Question Category: 213. House Robber II
 * Description: https://leetcode.com/problems/house-robber-ii/description/
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * File reference
 * -----------
 * Duplicate {@link HouseRobberII}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._198.HouseRobberI_198}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon
 * @Microsoft
 * @Salesforce <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class HouseRobberII_213 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{7, 3, 5, 2, 9}, 14);
        test &= test(new int[]{1000, 3, 200, 2, 400, 10000}, 10200);
        test &= test(new int[]{3000, 1, 1, 1, 1, 300}, 3002);
        test &= test(new int[]{1000, 3, 200, 2, 400, 1000}, 1600);
        test &= test(new int[]{3000, 6, 2, 8, 9, 3000}, 3014);

        CommonMethods.printResult(test);

    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("-------------------------------------------------");
        System.out.println("\nInput: nums = " + Arrays.toString(nums) + " Expected: " + expected);

        int output;
        boolean pass, finalPass = true;

        Solution_TwoArraysDP solutionTwoArraysDP = new Solution_TwoArraysDP();
        Solution_TwoPassLinear solutionTwoPassLinear = new Solution_TwoPassLinear();
        Solution_OnePassLinear solutionOnePassLinear = new Solution_OnePassLinear();

        output = solutionTwoArraysDP.rob(nums);
        pass = output == expected;
        System.out.println("Two Arrays DP: " + output + " Pass: " + (pass ? "Pass" : "Fail"));
        finalPass &= pass;

        output = solutionTwoPassLinear.rob(nums);
        pass = output == expected;
        System.out.println("Two Pass Linear : " + output + " Pass: " + (pass ? "Pass" : "Fail"));
        finalPass &= pass;

        output = solutionOnePassLinear.rob(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("One Pass Linear: " + output + " Pass: " + (pass ? "Pass" : "Fail"));


        return finalPass;


    }


    static class Solution_TwoArraysDP {
        public int rob(int[] nums) {
            int n = nums.length;

            if (n == 1)
                return nums[0];

            //letRight denotes the maximum amount of money that can be robbed from house 0 to house n-1
            int[] leftRight = new int[n];

            //rightLeft denotes the maximum amount of money that can be robbed from house n-1 to house 0
            int[] rightLeft = new int[n];

            Arrays.fill(leftRight, -1);
            Arrays.fill(rightLeft, -1);

            //if we have only one house, then it will be max
            leftRight[0] = nums[0];
            rightLeft[n - 1] = nums[n - 1];

            //if we have two houses, then we will take max of it
            leftRight[1] = Math.max(nums[0], nums[1]);
            rightLeft[n - 2] = Math.max(nums[n - 1], nums[n - 2]);

            //for all remaining houses
            for (int i = 2, j = n - 3; i < n; i++, j--) {

                //either we don't rob this house and take last max or rob this house along with previous to previous
                leftRight[i] = Math.max(leftRight[i - 1], leftRight[i - 2] + nums[i]);

                //either we don't rob this house and take last max or rob this house along with next to next
                rightLeft[j] = Math.max(rightLeft[j + 1], rightLeft[j + 2] + nums[j]);
            }

            //since we can't rob adjacent houses,and the last house is adjacent to the first house, hence wwe have to skip them in both cases
            return Math.max(leftRight[n - 2], rightLeft[1]);
        }
    }

    /**
     * Using {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._198.HouseRobberI_198.SolutionLinear#robV2(int[])}
     * as subroutine to solve the problem
     */
    static class Solution_TwoPassLinear {
        public int rob(int[] nums) {
            int n = nums.length;

            if (n == 1)
                return nums[0];

            return Math.max(
                    //exclude the last house and include the first house
                    houseRobberI(nums, 0, n - 2),

                    //exclude the first house and include the last house
                    houseRobberI(nums, 1, n - 1)
            );

        }

        private int houseRobberI(int[] nums, int start, int n) {
            if (n == 1)
                return nums[start];

            int including = nums[start];
            int excluding = 0;

            int max = 0;
            for (int i = start + 1; i <= n; i++) {
                max = Math.max(including, excluding);
                including = excluding + nums[i];
                excluding = max;
            }
            return Math.max(including, excluding);
        }
    }

    /**
     * Using {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._198.HouseRobberI_198.SolutionLinear#robV2(int[])}
     * as subroutine to solve the problem and same as {@link Solution_TwoArraysDP}
     */
    static class Solution_OnePassLinear {
        public int rob(int[] nums) {
            int n = nums.length;

            if (n == 1)
                return nums[0];

            if (n == 2)
                return Math.max(nums[0], nums[1]);

            // exclude last house and include the first house
            int includingLR = nums[0];
            int excludingLR = 0;

            // exclude first house and include last house
            int includingRL = nums[n - 1];
            int excludingRL = 0;

            int maxLR, maxRL;

            int lr = 1;
            int rl = n - 2;

            while (lr <= n - 2 && rl >= 1) {

                //left to right
                maxLR = Math.max(includingLR, excludingLR);
                includingLR = excludingLR + nums[lr];
                excludingLR = maxLR;
                lr++;


                //right to left
                maxRL = Math.max(includingRL, excludingRL);
                includingRL = excludingRL + nums[rl];
                excludingRL = maxRL;
                rl--;
            }
            return Math.max(Math.max(includingLR, excludingLR), Math.max(includingRL, excludingRL));


        }


    }


}
