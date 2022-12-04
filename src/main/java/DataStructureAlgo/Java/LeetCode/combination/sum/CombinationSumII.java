package DataStructureAlgo.Java.LeetCode.combination.sum;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description: https://leetcode.com/problems/combination-sum-ii/
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * This is similar to {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum}
 * but here number of element which can make the sum can be more than two and we also need to find those elements too
 */
public class CombinationSumII {

    public static void main(String[] args) {
        test(new int[]{10, 1, 2, 7, 6, 1, 5}, 8, Arrays.asList(Arrays.asList(1, 7), Arrays.asList(1, 2, 5), Arrays.asList(1, 1, 6)));
        test(new int[]{2, 5, 2, 1, 2}, 5, Arrays.asList(Arrays.asList(1, 2, 2), Arrays.asList(5)));
//        test(new int[]{2, 3, 5}, 0, Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
//        test(new int[]{2, 3, 5}, 22, Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
    }

    private static void test(int[] candidates, int target, List<List<Integer>> expected) {
        System.out.println("\n Candidates :" + GenericPrinter.toString(candidates) + " Target :" + target + " expected :" + expected);
        CombinationSumIIDFS dfs = new CombinationSumIIDFS();
        CombinationSumIIReverseDFS reverseDFS = new CombinationSumIIReverseDFS();
        CombinationSumIIDFSV2 visitedDfs = new CombinationSumIIDFSV2();

        System.out.println(" dfs :" + dfs.combinationSum2(candidates, target));
        System.out.println(" shortestPath dfs :" + reverseDFS.combinationSum2(candidates, target));
        System.out.println(" visited dfs :" + visitedDfs.combinationSum2(candidates, target));
    }
}

/**
 * {@link CombinationSumIDFS}
 * The only thing we need to add, is to avoid duplicate combination now. To avoid duplicate,
 * we can sort the array and avoid taking same element as first element for the solution.
 * Example: [1,1,7]; once we take the first 1 {0'th index} and found 7 as our solution [1,7] . Then we should not take second 1 {1'st index} as our first element
 * <p>
 * Runtime: 3 ms, faster than 81.29% of Java online submissions for Combination Sum II.
 * Memory Usage: 38.7 MB, less than 77.90% of Java online submissions for Combination Sum II.
 */
class CombinationSumIIDFS {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0)
            return Collections.EMPTY_LIST;

        List<List<Integer>> response = new ArrayList<>();
        Arrays.sort(candidates);

        combinationSum2(candidates, 0, 0, target, response, new ArrayList<>());
        return response;
    }

    private void combinationSum2(int[] candidates, int i, int currentSum, int target, List<List<Integer>> response, List<Integer> temp) {

        //3. Our goal: when currentSum = target
        if (currentSum == target) {
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

            //Avoid duplicates [1,1,7]; once we take the first 1 and found 7 as our solution [1,7] . Then we should not take second 1 as our first element
            //and try to find a solution.
            if (s > i && candidates[s] == candidates[s - 1])
                continue;

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (currentSum + candidates[s] <= target) {
                currentSum += candidates[s];
                temp.add(candidates[s]);

                combinationSum2(candidates, s + 1, currentSum, target, response, temp);

                //backtrack
                temp.remove(temp.size() - 1);
                currentSum -= candidates[s];
            }
        }

    }

}

/**
 * {@link CombinationSumIReverseDFS}
 * * The only thing we need to add, is to avoid duplicate combination now. To avoid duplicate,
 * * we can sort the array and avoid taking same element as first element for the solution.
 * * Example: [1,1,7]; once we take the first 1 {0'th index} and found 7 as our solution [1,7] . Then we should not take second 1 {1'st index} as our first element
 * *
 * <p>
 * Runtime: 2 ms, faster than 99.96% of Java online submissions for Combination Sum II.
 * Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Combination Sum II.
 */

class CombinationSumIIReverseDFS {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0)
            return Collections.EMPTY_LIST;

        List<List<Integer>> response = new ArrayList<>();
        Arrays.sort(candidates);

        combinationSum2(candidates, 0, target, response, new ArrayList<>());
        return response;
    }

    private void combinationSum2(int[] candidates, int i, int target, List<List<Integer>> response, List<Integer> temp) {

        //3. Our goal: when currentSum = target
        if (0 == target) {
            response.add(new ArrayList<>(temp));
            return;
        }

        if (i == candidates.length)
            return;

        //1. Our choices: We can choose a number from the list any number of times and all the numbers
        for (int s = i; s < candidates.length; s++) {

            //if this element is greater than target, then subtracting it  make target negetive
            //since,elements are sorted, then all the element after this element are > target
            if (candidates[s] > target)
                break;

            //Avoid duplicates [1,1,7]; once we take the first 1 and found 7 as our solution [1,7] . Then we should not take second 1 as our first element
            //and try to find a solution.
            if (s > i && candidates[s] == candidates[s - 1])
                continue;

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (target - candidates[s] >= 0) {
                target -= candidates[s];
                temp.add(candidates[s]);

                combinationSum2(candidates, s + 1, target, response, temp);

                //backtrack
                temp.remove(temp.size() - 1);
                target += candidates[s];
            }
        }

    }

}


/**
 * Runtime: 3 ms, faster than 81.29% of Java online submissions for Combination Sum II.
 * Memory Usage: 38.7 MB, less than 77.90% of Java online submissions for Combination Sum II.
 */
class CombinationSumIIDFSV2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0)
            return Collections.EMPTY_LIST;

        List<List<Integer>> response = new ArrayList<>();
        Arrays.sort(candidates);

        boolean visited[] = new boolean[candidates.length];

        combinationSum2(candidates, 0, 0, target, response, new ArrayList<>(), visited);
        return response;
    }

    private void combinationSum2(int[] candidates, int i, int currentSum, int target, List<List<Integer>> response, List<Integer> temp, boolean visited[]) {

        //3. Our goal: when currentSum = target
        if (currentSum == target) {
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

            //Avoid duplicates [1,1,7]; once we take the first 1 and found 7 as our solution [1,7] . Then we should not take second 1 as our first element
            //and try to find a solution.
            if (!visited[s] && s > i && candidates[s] == candidates[s - 1])
                continue;

            //Our constraints : We can't go beyond target, we can take more element than available in array
            if (currentSum + candidates[s] <= target) {
                currentSum += candidates[s];
                temp.add(candidates[s]);
                visited[s] = true;

                combinationSum2(candidates, s + 1, currentSum, target, response, temp, visited);
                visited[s] = false;
                //backtrack
                temp.remove(temp.size() - 1);
                currentSum -= candidates[s];
            }
        }

    }

}

