package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._4;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/28/2025
 * Question Title: 4. Median of Two Sorted Arrays
 * Link: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * Description: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.medians.MedianOfTwoSortedArray}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * <p><p>
 * Company Tags
 * -----
 *
 * @Amazon
 * @Adobe
 * @GoldmanSachs
 * @Microsoft
 * @Apple
 * @irbnb
 * @Alibaba
 * @Baidu
 * @Bloomberg
 * @DiDi
 * @Dropbox
 * @eBay
 * @Facebook
 * @Garena
 * @GoDaddy
 * @Google
 * @Houzz
 * @Hulu
 * @Oracle
 * @Rubrik
 * @Tencent
 * @TwoSigma
 * @Uber
 * @Visa
 * @VMware
 * @WalmartLabs
 * @Yahoo
 * @Zenefits
 * @Zillow
 * @Zulily <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MedianOfTwoSortedArrays_4 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{5, 7, 8, 11}, new int[]{4, 9, 12, 14}, 8.5));
        tests.add(test(new int[]{1, 3}, new int[]{2}, 2));
        tests.add(test(new int[]{1, 2}, new int[]{3, 4}, 2.5));
        tests.add(test(new int[]{5,7,8,11}, new int[]{4,9,12,14}, 8.5));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums1, int[] nums2, double expected) {

        CommonMethods.printTest(new String[]{"nums1", "nums2", "Expected"}, true, nums1, nums2, expected);

        double output = 0;
        boolean pass, finalPass = true;

        output = new Solution_MergeSort().findMedianSortedArrays(nums1, nums2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"MergeSort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_MergeSortV2().findMedianSortedArrays(nums1, nums2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"MergeSortV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_BinarySearch().findMedianSortedArrays(nums1, nums2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");
        return finalPass;

    }


    /**
     Since an arrays are sorted, and we are expected a faster solution which could either towards sqrt or log. However, array is sorted
     log solution could be a try out.

     Since array is sorted, can we think how we can divide the arrays and merge the array till the median point ?
     For details watch this video : https://www.youtube.com/watch?v=F9c7LpRZWVQ.

     This approach leverages binary search to find the correct partition between the two arrays such that:

     All elements on the left side of the partition are less than or equal to all elements on the right side.

     The two partitions together split the merged array at the median point.

     Key Idea:
     There is only one valid partition point where this condition holds. At this point:

     The number of elements on the left = (m + n + 1) / 2

     This ensures that for odd total lengths, the left partition will contain one extra element (i.e., the median itself).

     Steps:
     Always apply binary search on the smaller array to reduce complexity.

     Let partitionX be the cut in the first array, then
     partitionY = (m + n + 1) / 2 - partitionX

     Determine:

     maxLeftX = nums1[partitionX - 1] (or -∞ if partitionX == 0)

     minRightX = nums1[partitionX] (or +∞ if partitionX == length of nums1)

     maxLeftY = nums2[partitionY - 1]

     minRightY = nums2[partitionY]

     Check for the correct partition condition:


     if maxLeftX <= minRightY and maxLeftY <= minRightX:
     → Found correct partition
     If not:

     If maxLeftX > minRightY: move partitionX left

     Else: move partitionX right

     Once a valid partition is found:

     If total length is even:
     median = (max(maxLeftX, maxLeftY) + min(minRightX, minRightY)) / 2

     If total length is odd:
     median = max(maxLeftX, maxLeftY)

     */
    static class Solution_BinarySearch {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int n1 = nums1.length;
            int n2 = nums2.length;
            int n = n1 + n2;

            final int NEG_INF = Integer.MIN_VALUE;
            final int POS_INF = Integer.MAX_VALUE;

            /**
             * Do binary search on a smaller array
             */
            int low = 0, high = n1;
            while (low <= high) {

                //Find where we should partition array nums1
                int partitionX = (low + high) >>> 1; //median of a smaller array

                //Find where we should partition array nums2 such that both partition elements (partition of nums1 + partition of nums2) are equal or 1 more on the left side
                // (x + n2 + 1) / 2 gives you the middle point when we combine both the array and since we already have [0 , partitionX] element, then remove those to make them equal
                int partitionY = (n1 + n2 + 1) / 2 - partitionX;

                //If no element left on array nums1 on left partition
                int maxLeftX = (partitionX == 0) ? NEG_INF : nums1[partitionX - 1];

                //If no element left on array nums2 on left partition
                int maxLeftY = (partitionY == 0) ? NEG_INF : nums2[partitionY - 1];

                //If no element Right on array nums1 on right partition
                int minRightX = (partitionX == n1) ? POS_INF : nums1[partitionX];

                //If no element Right on array nums2 on right partition
                int minRightY = (partitionY == n2) ? POS_INF : nums2[partitionY];

                //If all the element on Left side of nums1 and nums2 are smaller than an element on the right side of nums2 and nums1
                if (maxLeftX <= minRightY && maxLeftY <= minRightX) {

                    if (n % 2 == 0) {
                        return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                    } else {
                        return Math.max(maxLeftX, maxLeftY);
                    }
                } else if (maxLeftX > minRightY) { //If we to far on the left side, reduce the left space (number of elements on lef side)
                    high = partitionX - 1;
                } else
                    low = partitionX + 1;//otherwise increase the space
            }
            return 0.0;
        }
    }



    /**
     * nums 1 [5,7,8,11]
     * nums 2 [4,9,12,14]
     * <p>
     * Merge sort like a process. Keep merging the array and once we reach the required index for median, use them to get median
     * Time : O(m+n) / O(m+n)
     * This is slightly better than completely merging the arrays.
     */
    static class Solution_MergeSortV2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;
            int total = n1 + n2;
            int[] result = new int[total];
            int r = 0;
            int i = 0, j = 0; //pointer for nums1 and nums2
            int x = Integer.MIN_VALUE;
            int y = Integer.MIN_VALUE;
            int mid = total / 2;

            while (i < n1 && j < n2) {
                result[r] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];

                if (r == mid) {
                    x = result[r];
                    y = r > 0 ? result[r - 1] : 0;
                    break; // we don't need more elements
                }

                r++;
            }

            if (x == Integer.MIN_VALUE) {

                while (i < n1) {
                    result[r] = nums1[i++];
                    if (r == mid) {
                        x = result[r];
                        y = r > 0 ? result[r - 1] : 0;
                        break; // we don't need more elements
                    }

                    r++;
                }

                while (j < n2) {
                    result[r] = nums2[j++];
                    if (r == mid) {
                        x = result[r];
                        y = r > 0 ? result[r - 1] : 0;
                        break; // we don't need more elements
                    }

                    r++;
                }
            }

            //even
            if (total % 2 == 0) {
                return (x + y) / 2.0;
            } else {
                //odd
                return x;
            }

        }
    }

    /**
     * Nums 1 [5,7,8,11]
     * nums 2 [4,9,12,14]
     * <p>
     * Merge sort like a process. Merge both arrays and then take median
     * Time : O(m+n) / O(m+n)
     */
    static class Solution_MergeSort {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;
            int total = n1 + n2;
            int[] result = new int[total];
            int r = 0;
            int i = 0, j = 0; //pointer for nums1 and nums2

            while (i < n1 && j < n2) {
                result[r++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
            }

            while (i < n1) {
                result[r++] = nums1[i++];
            }

            while (j < n2) {
                result[r++] = nums2[j++];
            }

            int x = result[total / 2];

            //even
            if (total % 2 == 0) {
                int y = result[total / 2 - 1];
                return (x + y) / 2.0;
            } else {
                //odd
                return x;
            }

        }
    }

}
