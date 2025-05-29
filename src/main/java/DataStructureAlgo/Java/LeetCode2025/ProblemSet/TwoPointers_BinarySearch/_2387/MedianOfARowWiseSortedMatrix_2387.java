package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2387;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/29/2025
 * Question Title: 2387. Median of a Row Wise Sorted Matrix
 * Link: https://leetcode.com/problems/median-of-a-row-wise-sorted-matrix/description/
 * https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2387.Median%20of%20a%20Row%20Wise%20Sorted%20Matrix/README_EN.md
 * Description: Given an m x n matrix grid containing an odd number of integers where each row is sorted in non-decreasing order, return the median of the matrix.
 * <p>
 * You must solve the problem in less than O(m * n) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[1,1,2],[2,3,3],[1,3,4]]
 * Output: 2
 * Explanation: The elements of the matrix in sorted order are 1,1,1,2,2,3,3,3,4. The median is 2.
 * Example 2:
 * <p>
 * Input: grid = [[1,1,3,3,4]]
 * Output: 3
 * Explanation: The elements of the matrix in sorted order are 1,1,3,3,4. The median is 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * m and n are both odd.
 * 1 <= grid[i][j] <= 106
 * grid[i] is sorted in non-decreasing order.
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
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MedianOfARowWiseSortedMatrix_2387 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 1, 2}, {2, 3, 3}, {1, 3, 4}}, 2));
        tests.add(test(new int[][]{{1, 1, 3, 3, 4}}, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_BinarySearch().matrixMedian(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_BinarySearch {
        public int matrixMedian(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int medianPoint = (m * n + 1) >>> 1;

            int low = 0, high = 1000010;   // 1 <= grid[i][j] <= 10^6
            while (low < high) {
                int mid = (low + high) >>> 1;

                if (counts(grid, mid) < medianPoint) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        int counts(int[][] grid, int target) {
            int counts = 0;
            for (var row : grid) {

                //apply binary search on each row to see how many elements are there less than mid
                counts += binarySearch(row, target);

            }

            return counts;
        }

        int binarySearch(int[] row, int target) {
            int low = 0, high = row.length;

            while (low < high) {
                int mid = (low + high) >>> 1;

                if (row[mid] <= target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }

    }
}
