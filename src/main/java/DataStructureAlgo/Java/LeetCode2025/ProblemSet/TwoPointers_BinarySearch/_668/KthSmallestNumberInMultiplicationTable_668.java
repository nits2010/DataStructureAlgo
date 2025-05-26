package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._668;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/26/2025
 * Question Title: 668. Kth Smallest Number in Multiplication Table
 * Link: https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
 * Description: Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 * <p>
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: m = 3, n = 3, k = 5
 * Output: 3
 * Explanation: The 5th smallest number is 3.
 * Example 2:
 * <p>
 * <p>
 * Input: m = 2, n = 3, k = 6
 * Output: 6
 * Explanation: The 6th smallest number is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 3 * 104
 * 1 <= k <= m * n
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
 * @hard
 * @Math
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Rubrik
 * @Google
 * @Uber<p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class KthSmallestNumberInMultiplicationTable_668 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(3, 3, 5, 3));
        tests.add(test(2, 3, 6, 6));
        tests.add(test(11, 13, 57, 24));
        tests.add(test(11, 13, 57, 24));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int m, int n, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"m", "n", "k", "Expected"}, true, m, n, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_BinarySearch().findKthNumber(m, n, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_BinarySearchV2().findKthNumber(m, n, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BinarySearchV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_UsingHeap().findKthNumber(m, n, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"UsingHeap", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Here the smallest number in the matrix would be 1x1 while the max would be mxn. Hence, our solution would like b/w [1, m*n]
     * This makes us think in binary search. For each mid, if we are able to find how many elements from the matrix are '<=mid' then we can adjust low and high to meet kth element
     * <p>
     * Since the matrix forms as follows
     * m=1 then its 1x1, 1x2, 1x3, ... 1xn
     * m=2 them its 2x1, 2x2...........2xn
     * ...
     * m=M than its Mx1, 2x2...........2xn
     * <p>
     * Means for each row [1,m] as i the row elements are i, 2i , 3i.... ni
     * since these rows are always sorted, we can find that how many elements are <=mid using (mid / i).
     * Example : mid = 5 then
     * m=1 ; 1, 2, 3, 4, 5 here 5 / 1 = 5 elements are <=5
     * m=2 ; 2, 4, 6..... here 5 / 2 = 2 elements are <=5
     * m= 3; 3, 6 ....
     * m=4; 4, ...
     * m=5; 5.... here 5/5 = 1 elements are <= 5
     * <p>
     * Hence, for each row its min(mid / i, n) ; we took min with n, since our last number in the row is ni, so maximum n is possible.
     *
     * TIme : O(m*log(mn)) | Space: O(1)
     */

    static class Solution_BinarySearch {
        public int findKthNumber(int m, int n, int k) {

            int low = 1, high = m * n;

            while (low < high) {

                int mid = (low + high) >>> 1;
                int counts = countNumber(m, n, k, mid);

                if (counts < k) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;

        }

        private int countNumber(int m, int n, int k, int mid) {
            int count = 0;

            for (int i = 1; i <= m; i++) {
                count += Math.min(mid / i, n);
            }
            return count;
        }

    }

    static class Solution_BinarySearchV2 {
        public int findKthNumber(int m, int n, int k) {
            int left = 1;
            int right = n * m;
            int ans = 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numsSmaller(m, n, mid, k)) {
                    right = mid - 1;
                    ans = mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }

        boolean numsSmaller(int m, int n, int target, int k) {
            int numSmaller = 0;
            for (int i = 1; i <= m; i++) {
                int contributions = Math.min(target / i, n);
                numSmaller += contributions;
                if (numSmaller >= k) {
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * Since the row is sorted and each column is sorted, we can use heap as well to keep the elements in sorted order. Just like merging sorted arrays
     * To add explore all the elements of mxn matrix, we would push elements in heap based on what we pop so that it always has the smallest elements
     * We treat the multiplication table like a sorted matrix: each row is sorted, and we can simulate merging the rows using a min heap.
     * <p>
     * 🔸 Idea
     * Initializes a min-heap with the first element from each row:
     * (val, row, col) = (row * 1, row, 1)
     * <p>
     * Pop the smallest element k times.
     * <p>
     * When popping (val, row, col), if col < n, push (row * (col + 1), row, col + 1) back into the heap.
     * <p>
     * Time: O(m) + O(k⋅ log min(m,k)) = O(k⋅log min(m,k))
     * Space: O(m)
     */
    static class Solution_UsingHeap {
        public int findKthNumber(int m, int n, int k) {

            //(val, row, col) = (row * 1, row, 1)
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            for (int i = 1; i <= m; i++) {
                pq.offer(new int[]{i, i, 1});
            }

            //extract k times
            while (k > 1) {
                int[] pop = pq.poll();
                int row = pop[1], col = pop[2];
                if (col < n) { // take the next column in heap
                    pq.offer(new int[]{row * (col + 1), row, col + 1});
                }
                k--;
            }

            return pq.poll()[0];
        }
    }
}
