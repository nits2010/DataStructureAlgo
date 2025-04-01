package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.matrix._363;

import DataStructureAlgo.Java.GeeksForGeeks2025.ProblemSet.Arrays.SubArrays.LongestSubArraySumK.LongestSubArrayHavingSumK;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming._baseProblems.maximumSubArray.Kadens._53.MaximumSubarray_53;
import DataStructureAlgo.Java.GeeksForGeeks2025.ProblemSet.Arrays.DynamicProgramming.matrix._27.MaximumSumRectangleInA2DMatrix_KadensAlgorithm2D_27;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/22/2024
 * Question Title: 363. Max Sum of Rectangle No Larger Than K
 * Link: https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
 * Description:Given an m x n matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * <p>
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
 * Example 2:
 * <p>
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 * <p>
 * <p>
 * Follow up: What if the number of rows is much larger than the number of columns?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link LongestSubArrayHavingSumK}
 * extension {@link MaximumSumRectangleInA2DMatrix_KadensAlgorithm2D_27} {@link MaximumSubarray_53.Kadens.KadensAlgorithm}
 * DP-BaseProblem {@link MaximumSubarray_53.Kadens.KadensAlgorithm}
 * <p><p>
 * Tags
 * -----
 *
 * @easy <p><p>
 * Company Tags
 * -----
 * @Dunzo
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaxSumOfRectangleNoLargerThanK_363 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2, 2));
        tests.add(test(new int[][]{{2, 2, -1}}, 3, 3));
        tests.add(test(new int[][]{{2, 2, -1}}, 0, -1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] matrix, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Matrix", "K", "Expected"}, true, matrix, k, expected);

        int output;
        boolean pass, finalPass = true;

        Solution_Kadens kadens = new Solution_Kadens();
        output = kadens.maxSumSubmatrix(matrix, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution_Kadens_PrefixSum kadensPrefixSum = new Solution_Kadens_PrefixSum();
        output = kadensPrefixSum.maxSumSubmatrix(matrix, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output-Optimized-Prefix sum", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    /**
     * {@link MaximumSumRectangleInA2DMatrix_KadensAlgorithm2D_27.KadensAlgorithm2D}
     */
    static class Solution_Kadens {
        public int maxSumSubmatrix(int[][] matrix, int k) {

            int row = matrix.length;
            int col = matrix[0].length;
            int maxSum = Integer.MIN_VALUE;
            int[] temp;

            for (int leftCol = 0; leftCol < col; leftCol++) {
                temp = new int[row];

                for (int rightCol = leftCol; rightCol < col; rightCol++) {

                    for (int i = 0; i < row; i++) {
                        temp[i] += matrix[i][rightCol];
                    }

                    maxSum = Math.max(maxSum, kadans1D(temp, k));
                    if (maxSum == k)
                        return k;

                }
            }

            return maxSum;

        }

        private int kadans1D(int[] temp, int k) {
            int maxSum = Integer.MIN_VALUE;
            int currentSum = 0;

            //use to find, if we have seen a value which is currentSum - k before
            // we did not use hashmap here since, we need the max value of  sum - k which is either K or greater than K, to make sure that totalSum (currentSum - x) <= k
            TreeSet<Integer> ts = new TreeSet<>();

            ts.add(0); // we always see a sun = 0

            for (int n : temp) {
                currentSum += n; //this will increase the sum

                Integer gap = ts.ceiling(currentSum - k); //binary search

                if (gap != null) {
                    //means there is a value gap, which is either currentSum - k or near to it so that totalSum <=k
                    maxSum = Math.max(maxSum, currentSum - gap);
                }
                ts.add(currentSum); //we saw this sum;
            }
            return maxSum;
        }
    }


    /**
     * {@link MaximumSumRectangleInA2DMatrix_KadensAlgorithm2D_27.KadensAlgorithm2D_Optimized_PrefixSum}
     */
    static class Solution_Kadens_PrefixSum {
        public int maxSumSubmatrix(int[][] matrix, int k) {

            int row = matrix.length;
            int col = matrix[0].length;
            int maxSum = Integer.MIN_VALUE;

            //calculate prefix sum
            for (int i = 0; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] += matrix[i][j - 1];
                }
            }

            for (int leftCol = 0; leftCol < col; leftCol++) {
                for (int rightCol = leftCol; rightCol < col; rightCol++) {

                    //use to find, if we have seen a value which is currentSum - k before
                    // we did not use hashmap here since, we need the max value of  sum - k which is either K or greater than K, to make sure that totalSum (currentSum - x) <= k
                    TreeSet<Integer> ts = new TreeSet<>();
                    ts.add(0);
                    int currentSum = 0;
                    int maxSumSub = Integer.MIN_VALUE;

                    for (int[] temp : matrix) {

                        int sum = temp[rightCol] - (leftCol == 0 ? 0 : temp[leftCol - 1]); // calculate the prefix sum at this point
                        currentSum += sum;

                        Integer gap = ts.ceiling(currentSum - k); //binary search
                        if (gap != null) {
                            //means there is a value gap, which is either currentSum - k or near to it so that totalSum <=k
                            maxSumSub = Math.max(maxSumSub, currentSum - gap);
                        }
                        ts.add(currentSum);

                    }


                    maxSum = Math.max(maxSum, maxSumSub);
                    if (maxSum == k)
                        return k;

                }
            }

            return maxSum;

        }


    }


}
