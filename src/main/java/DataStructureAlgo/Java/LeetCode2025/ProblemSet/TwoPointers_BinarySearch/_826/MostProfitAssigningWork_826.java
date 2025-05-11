package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._826;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/26/2025
 * Question Title: 826. Most Profit Assigning Work
 * Link: https://leetcode.com/problems/most-profit-assigning-work/description/
 * Description: You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
 * <p>
 * difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
 * worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 * <p>
 * For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
 * Return the maximum profit we can achieve after assigning the workers to the jobs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
 * Example 2:
 * <p>
 * Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == difficulty.length
 * n == profit.length
 * m == worker.length
 * 1 <= n, m <= 104
 * 1 <= difficulty[i], profit[i], worker[i] <= 105
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
 * @medium
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @NetEase
 * @Nutanix
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link SolutionUsingCache}
 */

public class MostProfitAssigningWork_826 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{2, 4, 6, 8, 10}, new int[]{10, 20, 30, 40, 50}, new int[]{4, 5, 6, 7}, 100));
        tests.add(test(new int[]{85, 47, 57}, new int[]{24, 66, 99}, new int[]{40, 25, 25}, 0));
        tests.add(test(new int[]{68, 35, 52, 47, 86}, new int[]{67, 17, 1, 81, 3}, new int[]{92, 10, 85, 84, 82}, 324));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] difficulty, int[] profit, int[] workers, int expected) {

        CommonMethods.printTest(new String[]{"difficulty", "profit", "workers", "Expected"}, true, difficulty, profit, workers, expected);

        int output ;
        boolean pass, finalPass = true;

        SolutionBinarySearch solutionBinarySearch = new SolutionBinarySearch();
        output = solutionBinarySearch.maxProfitAssignment(difficulty, profit, workers);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionUsingCache solutionUsingCache = new SolutionUsingCache();
        output = solutionUsingCache.maxProfitAssignment(difficulty, profit, workers);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"solutionUsingCache", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(n*log(n) + m*log(n)) / O(n)
    static class SolutionBinarySearch {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {

            int n = difficulty.length;
            int m = workers.length;

            int[][] diffProfit = new int[n][2];

            //O(n)
            for (int i = 0; i < n; i++) {
                diffProfit[i][0] = difficulty[i];
                diffProfit[i][1] = profit[i];
            }

            // O(n*log(n))
            Arrays.sort(diffProfit, Comparator.comparingInt(a -> a[0]));

            //precompute the max profit at each index
            int[] maxProfit = new int[n];
            maxProfit[0] = diffProfit[0][1];

            //O(n)
            for (int i = 1; i < n; i++) {
                maxProfit[i] = Math.max(maxProfit[i - 1], diffProfit[i][1]);
            }

            //sort workers based on their capacity wrt to difficulty
            Arrays.sort(workers);
            int totalProfit = 0;

            //find the best profit rich job for every worker
            // O(m*log(n))
            for (int ability : workers) {

                int index = binarySearch(diffProfit, ability);

                if (index != -1) {// a job found

                    //get max profit at this job
                    totalProfit += maxProfit[index];
                }
            }

            return totalProfit;

        }

        //O(log(n))
        int binarySearch(int[][] diffProfit, int target) {
            int low = 0, high = diffProfit.length - 1;
            int index = -1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (diffProfit[mid][0] <= target) {
                    index = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return index;
        }
    }

    //O(m + max) / O(max) where max is the maxDifficulty
    static class SolutionUsingCache {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {

            int n = difficulty.length;
            int m = workers.length;

            //since the range of n is just 10^4 we can cache them : O(n)
            int maxDifficulty = 0;
            for (int diff : difficulty) {
                maxDifficulty = Math.max(maxDifficulty, diff);
            }

            //this will keep the max profit can be obtained up to this difficulty[i]
            int[] maxDifficultyToProfitCache = new int[maxDifficulty + 1];

            // O(n)
            for (int i = 0; i < n; i++) {
                int diff = difficulty[i];
                int current = maxDifficultyToProfitCache[diff];

                maxDifficultyToProfitCache[diff] = Math.max(current, profit[i]);
            }

            //commutative max at each index : O(max)
            for (int i = 1; i <= maxDifficulty; i++) { //1 <= difficulty[i]
                maxDifficultyToProfitCache[i] = Math.max(maxDifficultyToProfitCache[i], maxDifficultyToProfitCache[i - 1]);
            }

            int totalProfit = 0;
            // O(m)
            for (int ability : workers) {

                if (ability >= maxDifficulty) {
                    totalProfit += maxDifficultyToProfitCache[maxDifficulty];
                } else {

                    totalProfit += maxDifficultyToProfitCache[ability];
                }
            }

            return totalProfit;

        }
    }
}
