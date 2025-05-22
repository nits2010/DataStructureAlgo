package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._240;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/21/2025
 * Question Title: 240. Search a 2D Matrix II
 * Link: https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 * Description: Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.matrix.search.Search2DMatrixII}
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
 * @DivideandConquer
 * @Matrix <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Bloomberg
 * @Apple
 * @Facebook
 * @Baidu
 * @Citadel
 * @Expedia
 * @GoldmanSachs
 * @Google
 * @LinkedIn
 * @Paypal
 * @Salesforce
 * @SAP
 * @Tencent
 * @WalmartLabs <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Search2DMatrixII_240 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5, true));
        tests.add(test(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20, false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, int target, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        boolean output;
        boolean pass, finalPass = true;

        output = new Solution().searchMatrix(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //Since columns and row are sorted individually ( not the entire matrix ), we can try to figure out which column would have our target.
    // Once the column is figured out, we can try to figure out which row it would be.
    // Makes a linear scan O(m+n)
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {

            int m = matrix.length;
            int n = matrix[0].length;

            int r = 0, c = n - 1;

            while (r < m && c >= 0) {
                int current = matrix[r][c];
                if (current == target)
                    return true;

                if (current > target)
                    c--;
                else
                    r++;

            }

            return false;
        }

    }

}
