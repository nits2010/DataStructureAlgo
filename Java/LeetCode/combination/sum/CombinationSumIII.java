package Java.LeetCode.combination.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/combination-sum-iii/
 * <p>
 * 216. Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * <p>
 * Note:
 * <p>
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * Similar to {@link CombinationSumII}, only difference is that instead of an explicit array, we are restricted to
 * 1. numbers from 1 to 9 {this is nothing but candidate array}
 * 2. Total k numbers at max
 */
public class CombinationSumIII {

    public static void main(String[] args) {
        test(3, 0, Arrays.asList());
        test(3, 7, Arrays.asList(Arrays.asList(1, 2, 4)));
        test(3, 9, Arrays.asList(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5), Arrays.asList(2, 3, 4)));
    }

    private static void test(int k, int n, List<List<Integer>> expected) {
        System.out.println("\n k : " + k + " n: " + n + " expected :" + expected);
        CombinationSumIIIDFS dfs = new CombinationSumIIIDFS();
        System.out.println("dfs :" + dfs.combinationSum3(k, n));
    }
}


/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum III.
 * Memory Usage: 33.4 MB, less than 8.00% of Java online submissions for Combination Sum III.
 * <p>
 * O(C(9,k))-> O(9^k), space: k
 */
class CombinationSumIIIDFS {

    public List<List<Integer>> combinationSum3(int k, int n) {

        /**
         * You can't make sum n=2 when k=3. to run at least n>=k
         * and using [1,9] you can't make sum more than 45.
         * K can not be more than 9 since we have 9 choices only
         */
        if (n < k || n > 45 || k > 9)
            return Collections.EMPTY_LIST;


        List<List<Integer>> response = new ArrayList<>();
        int candidates[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        combinationSum3(candidates, k, 0, 0, n, response, new ArrayList<>());
        return response;
    }

    private void combinationSum3(int[] candidates, int k, int i, int currentSum, int target, List<List<Integer>> response, List<Integer> temp) {

        //3. Our goal: when currentSum = target & k numbers only
        if (temp.size() == k) {

            if (currentSum == target)
                response.add(new ArrayList<>(temp));


            return;
        }

        if (i == candidates.length)
            return;


        //1. Our choices: We can choose a number from the list any number of times and all the numbers
        for (int s = i; s < candidates.length; s++) {

            //if this element is greater than target, then adding it to current sum make bigger than target
            //since,elements are sorted, then all the element after this element are > target
            if (candidates[s] > target)
                break;

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (currentSum + candidates[s] <= target) {
                currentSum += candidates[s];
                temp.add(candidates[s]);

                combinationSum3(candidates, k, s + 1, currentSum, target, response, temp);

                //backtrack
                temp.remove(temp.size() - 1);
                currentSum -= candidates[s];
            }
        }

    }
}
