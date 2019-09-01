package Java.LeetCode.combination.sum;

import Java.HelpersToPrint.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
 *
 * TODO:
 */
public class CombinationSumIV {


    public static void main(String[] args) {
        test(new int[]{2, 3, 6, 7}, 7, 4);
        test(new int[]{2, 3, 5}, 7, 5);
        test(new int[]{2, 3, 5}, 0, 1);
        test(new int[]{2, 3, 5}, 22, 912);
        test(new int[]{2, 1, 3}, 35, 1132436852);

    }

    private static void test(int[] candidates, int target, int expected) {
        System.out.println("\n Candidates :" + Printer.toString(candidates) + " Target :" + target + " expected :" + expected);
        CombinationSumIVDFS combinationSumIDFS = new CombinationSumIVDFS();
        System.out.println(" DFS : " + combinationSumIDFS.combinationSum4(candidates, target));
    }

}

/**
 * {@link CombinationSumIDFS}
 * Leetcode : TLE
 */
class CombinationSumIVDFS {

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