package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._74;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/21/2025
 * Question Title: 74. Search a 2D Matrix
 * Link: https://leetcode.com/problems/search-a-2d-matrix/description/
 * Description: You are given an m x n integer matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * <p>
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.matrix.search.Search2DMatrixI}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch
 * @Matrix <p><p>
 * Company Tags
 * -----
 * @Accolite
 * @MentorGraphics
 * @Adobe
 * @Amazon
 * @Directi
 * @GoldmanSachs
 * @Groupon
 * @InMobi
 * @MakeMyTrip
 * @OlaCabs
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SearchA2DMatrix_74 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3, true)); //standard matrix
        tests.add(test(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13, false)); //standard matrix
        tests.add(test(new int[][]{{1}, {3}}, 3, true)); //two rows, 1 column matrix
        tests.add(test(new int[][]{{1}, {3}}, 0, false)); //two rows, 1 column matrix
        tests.add(test(new int[][]{{1}}, 0, false)); //1 rows, 1 column matrix
        tests.add(test(new int[][]{{1}, {3}, {5}, {8}}, 3, true)); //4 rows, 1 column matrix
        tests.add(test(new int[][]{{1, 1}}, 3, false)); //1 rows, 2 column matrix

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, int target, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        boolean output;
        boolean pass, finalPass = true;

        output = new SolutionRowLinear().searchMatrix(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"RowLinear", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_RowCol().searchMatrix(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"RowCol-log(mn)", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution().searchMatrix(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"log(mn)", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution().searchMatrixV2(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"log(mn)-V2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(log(m) * log(n)) / O(1)
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {

            int m = matrix.length;
            int n = matrix[0].length;

            //instead of treating it as 2d array, treat it as 1d array, then apply binary search
            int low = 0, high = m * n - 1;

            while (low <= high) {

                int mid = (low + high) >>> 1;

                int row = mid / n; // Since each row has 'n' elements, this gives the row index of the mid element
                int col = mid % n; // The remainder gives the column index within that row

                if (matrix[row][col] == target)
                    return true;

                if (matrix[row][col] > target)
                    high = mid - 1;
                else
                    low = mid + 1;

            }
            return false;
        }


        public boolean searchMatrixV2(int[][] matrix, int target) {

            int m = matrix.length;
            int n = matrix[0].length;

            //instead of treating it as 2d array, treat it as 1d array, then apply binary search
            int low = 0, high = m * n - 1;

            while (low < high) {

                int mid = (low + high) >>> 1;

                int row = mid / n; // Since each row has 'n' elements, this gives the row index of the mid element
                int col = mid % n; // The remainder gives the column index within that row

                if (matrix[row][col] == target)
                    return true;

                if (matrix[row][col] >= target)
                    high = mid;
                else
                    low = mid + 1;

            }
            return matrix[low / n][low % n] == target;
        }

    }

    // O(m * log(n)) / O(1)
    static class SolutionRowLinear {
        public boolean searchMatrix(int[][] matrix, int target) {

            int m = matrix.length;
            int n = matrix[0].length;

            //find target row and the search in that row using BS
            //O(m)
            for (int i = 0; i < m; i++) {

                if (matrix[i][0] <= target && matrix[i][n - 1] >= target)
                    //O(log(n))
                    return binarySearch(matrix[i], target);
            }
            return false;
        }

        boolean binarySearch(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (nums[mid] == target)
                    return true;
                if (nums[mid] > target)
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            return false;
        }
    }

    //O(log(m) * log(n)) / O(1)
    static class Solution_RowCol {
        public boolean searchMatrix(int[][] matrix, int target) {

            int m = matrix.length;
            int n = matrix[0].length;

            //find target row and the search in that row using BS
            int targetRow = binarySearch(matrix, target);

            if (targetRow == -1)
                return false;

            return binarySearch(matrix[targetRow], target);
        }

        //O(log(m))
        int binarySearch(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;

            int low = 0, high = m - 1;

            while (low <= high) {

                int mid = (low + high) >>> 1;
                if (matrix[mid][0] <= target && matrix[mid][n - 1] >= target)
                    return mid;

                if (matrix[mid][0] > target)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return -1;
        }

        //O(log(n))
        boolean binarySearch(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (nums[mid] == target)
                    return true;
                if (nums[mid] > target)
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            return false;
        }
    }


}
