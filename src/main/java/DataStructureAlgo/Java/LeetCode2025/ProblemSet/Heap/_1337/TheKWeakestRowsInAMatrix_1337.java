package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._1337;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Question Category: 1337. The K Weakest Rows in a Matrix
 * Description: https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/description/
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
 * <p>
 * A row i is weaker than a row j if one of the following is true:
 * <p>
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: mat =
 * [[1,1,0,0,0],
 * [1,1,1,1,0],
 * [1,0,0,0,0],
 * [1,1,0,0,0],
 * [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers in each row is:
 * - Row 0: 2
 * - Row 1: 4
 * - Row 2: 1
 * - Row 3: 2
 * - Row 4: 5
 * The rows ordered from weakest to strongest are [2,0,3,1,4].
 * Example 2:
 * <p>
 * Input: mat =
 * [[1,0,0,0],
 * [1,1,1,1],
 * [1,0,0,0],
 * [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers in each row is:
 * - Row 0: 1
 * - Row 1: 4
 * - Row 2: 1
 * - Row 3: 1
 * The rows ordered from weakest to strongest are [0,2,3,1].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @BinarySearch
 * @Sorting
 * @Heap(PriorityQueue)
 * @Matrix <p>
 * Company Tags
 * -----
 * @Amazon
 * @Editorial
 */
public class TheKWeakestRowsInAMatrix_1337 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}}, 3, new int[]{2, 0, 3});
        test &= test(new int[][]{{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}}, 2, new int[]{0, 2});

        System.out.println("================================");
        System.out.println((test ? "All passed" : "Something failed"));
    }

    private static boolean test(int[][] mat, int k, int[] expected) {
        System.out.println("------------------------------------");
        System.out.println("Matrix:\n" + CommonMethods.toString(mat) + " k = " + k + " Expected Output:" + Arrays.toString(expected));

        Solution solution = new Solution();
        int[] actual = solution.kWeakestRows(mat, k);
        System.out.println("Actual Output:" + Arrays.toString(actual));
        if (!Arrays.equals(actual, expected)) {
            System.out.println("Actual Output:" + Arrays.toString(actual) + " Expected Output:" + Arrays.toString(expected));
            return false;
        }
        return true;

    }

    static class Solution {
        public int[] kWeakestRows(int[][] mat, int k) {
            if (mat == null || mat.length == 0 || mat[0].length == 0 || k > mat.length) {
                return null;
            }

            int[] result = new int[k];

//            Comparator<int[]> comparator = (o1, o2) -> {
//                if (Objects.equals(o1[0], o2[0]))
//                    //if they are equal then order by index
//                    return o1[1].compareTo(o2[1]);
//                //if they are not equal then order by value
//                return o1[0].compareTo(o2[0]);
//
//
//            };
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[0] == o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0]);

            int[][] soldiers = new int[mat.length][2];
            for (int i = 0; i < mat.length; i++) {
                soldiers[i][0] = soldiersBS(mat[i]);
                soldiers[i][1] = i;
                pq.offer(soldiers[i]);
            }

            int i = 0;
            while (k > 0 && !pq.isEmpty()) {
                result[i++] = pq.poll()[1];
                k--;
            }

            return result;

        }

        // O(n)
        private int soldiers(int[] mat) {
            int solidierCount = 0;
            for (int j = 0; j < mat.length; j++) {
                if (mat[j] == 1) {
                    solidierCount++;
                } else
                    break;
            }
            return solidierCount;
        }

        private int soldiersBS(int[] mat) {
            //find the index of first last 1 in this row
            int l = 0, r = mat.length - 1;
            while (l <= r) {

                int mid = l + (r - l) / 2;
                if (mat[mid] == 1)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return l;
        }
    }
}
