package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1885;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/15/2025
 * Question Title: 1885. Count Pairs in Two Arrays 🔒
 * Link: https://leetcode.com/problems/count-pairs-in-two-arrays
 * https://github.com/nits2010/leetcode-all-problem/blob/main/solution/1800-1899/1885.Count%20Pairs%20in%20Two%20Arrays/README_EN.md
 * Description: Given two integer arrays nums1 and nums2 of length n, count the pairs of indices (i, j) such that i < j and nums1[i] + nums1[j] > nums2[i] + nums2[j].
 * <p>
 * Return the number of pairs satisfying the condition.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [2,1,2,1], nums2 = [1,2,1,2]
 * Output: 1
 * Explanation: The pairs satisfying the condition are:
 * - (0, 2) where 2 + 2 > 1 + 1.
 * Example 2:
 * <p>
 * Input: nums1 = [1,10,6,2], nums2 = [1,4,1,5]
 * Output: 5
 * Explanation: The pairs satisfying the condition are:
 * - (0, 1) where 1 + 10 > 1 + 4.
 * - (0, 2) where 1 + 6 > 1 + 1.
 * - (1, 2) where 10 + 6 > 4 + 1.
 * - (1, 3) where 10 + 2 > 4 + 5.
 * - (2, 3) where 6 + 2 > 1 + 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= 105
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
 * @Sorting
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CountPairsInTwoArrays_1885 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{2, 1, 2, 1}, new int[]{1, 2, 1, 2}, 1));
        tests.add(test(new int[]{1, 10, 6, 2}, new int[]{1, 4, 1, 5}, 5));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums1, int[] nums2, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums1", "nums2", "Expected"}, true, nums1, nums2, expected);

        long output = 0;
        boolean pass, finalPass = true;

        output = new Solution_LinearSearch().countPairs(nums1, nums2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Sorting-Linear search", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_BinarySearch().countPairs(nums1, nums2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Sorting-Binary search", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }

    /**
     * We need to find (i,j) such that
     * nums1[i] + nums1[j] > nums2[i] + nums2[j]
     * <p>
     * Key observations:
     * 1. Index in both array are same (i,j).
     * 2. Sum (nums1) > Sum (nums2)
     * 3. Order of element matters in both the array.
     * nums1[i] + nums1[j] > nums2[i] + nums2[j]
     * =>   nums1[1] > nums2[i] + nums2[j] - nums1[j]
     * =>   nums1[i] - nums2[i] > nums2[j] - nums1[j]
     * <p>
     * This is re-written as nums1[i] - nums2[i] > nums2[j] - nums1[j]
     * means the difference of index in both the array at i and j should follow the equation.
     * <p>
     * Approach 1: Binary search
     * 1. We can create two different arrays with nums1[i] - nums2[i] &  nums2[j] - nums1[j], say diff1 and diff2
     * 2. Now, we need to find an index j in the diff2 where its value is < diff1[i], this should be the greatest index to cover all the pairs.
     * 3. Once found, we can we need to scan through all the elements in the second array to make sure tha index are not same (i!=j) and exclude all the pair which already been calculated. This will take another O(n)
     * That makes this approach O(n * log(n) * n ) = O(n2 * logn) which is worst then brute force.
     * <p>
     * Approach 2: Binary search modified.
     * 1. The idea is greate in approach 1 but the binary search part needs to be improve such that we don't need to scan for index again.
     * We can further refine the equations as
     * =>   nums1[i] - nums2[i] > nums2[j] - nums1[i]
     * =>   nums1[i] - nums2[i] + nums1[j] -  nums2[j] > 0
     * <p>
     * => diff[i] + diff[j] > 0 where diff[x] = nums1[x] - nums2[x]
     * <p>
     * 2. Now with the equation,  diff[i] + diff[j] > 0 we need to find the pair(i,j) that satisfy this condition, which can be done using two pointer post sorting the diff array.
     * <p>
     * <p>
     * Alog:
     * 1. Create a diff array as diff[i] = nums1[i] - nums2[i]
     * 2. Sort the array.
     * 3. Apply two pointer technique to find i and j
     * <p>
     *
     * Time: O(n * log(n))
     */
    static class Solution_LinearSearch {
        public long countPairs(int[] nums1, int[] nums2) {
            int length = nums1.length;

            int[] diff = new int[length];

            //create a diff array
            for (int i = 0; i < length; i++) {
                diff[i] = nums1[i] - nums2[i];
            }

            //sort the array
            Arrays.sort(diff);

            //apply two pointer to find the index i and j
            int i = 0, j = length - 1;
            int count = 0;

            //since array is sorted, the sum at diff[i] + diff[j] would be largest and most likely > 0, hence we need to find the greatest index where
            // this condition follows, and all the element before would follow 100%

            while (i < j) {

                //find the index where diff[i] + diff[j] > 0 means find the first index where diff[i] + diff[j] <= 0, all element post that will follow condition automatically.
                while (i < j && diff[i] + diff[j] <= 0)
                    i++;
                count += j - i; // all element b/w i and j follow condition
                j--;

            }

            return count;
        }
    }


    //Same as above but with binary search
    // Time: O(n * log(n))
    static class Solution_BinarySearch {
        public long countPairs(int[] nums1, int[] nums2) {
            int length = nums1.length;

            int[] diff = new int[length];

            //create a diff array
            for (int i = 0; i < length; i++) {
                diff[i] = nums1[i] - nums2[i];
            }

            //sort the array
            Arrays.sort(diff);

            //apply two pointer to find the index i and j
            int i = 0;
            int count = 0;

            //since array is sorted, the sum at diff[i] + diff[j] would be largest and most likely > 0, hence we need to find the greatest index where
            // this condition follows, and all the element before would follow 100%
            //we can use binary search to find that index

            while (i < length - 1) {
                //diff1[i] = nums1[i] - nums2[i]
                //to find another index j such that diff2[j] = nums1[j] - nums2[j] and
                // diff1[i] + diff2[j] > 0
                // => diff2[j] > - diff1[i]
                // to have equality we need at least bigger than -diff1[i], hence add 1, element would be -diff[i] + 1
                int target = -diff[i] + 1;
                // get index of first element greater than or equal to target
                int index = binarySearch(diff, target, i + 1, length);

                //all elements after index will follow the condition
                count += length - index;

                i++;

            }


            return count;
        }

        /**
         * Return the index of first element greater than or equal to target
         *
         * @param nums
         * @param target
         * @param startIndex
         * @param n
         * @return
         */
        private int binarySearch(int[] nums, int target, int startIndex, int n) {
            int low = startIndex;
            int high = n - 1;

            //if all elements are smaller than target, return n; to make the count 0
            if (nums[high] < target) {
                return n;
            }
            while (low < high) {
                int mid = (low + high) >>> 1;
                int element = nums[mid];

                if (element >= target) {
                    //we need smaller
                    high = mid;
                } else {
                    low = mid + 1;
                }

            }
            return low;
        }
    }
}
