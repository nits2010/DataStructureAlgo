package DataStructureAlgo.Java.LeetCode.medians;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-19
 * Description: https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 4. Median of Two Sorted Arrays
 * here are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArray {

    public static void main(String[] args) {
        int nums1[] = {3, 4};
        int nums2[] = {1, 2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /*
    O(log(min(m,n)))
     */

    public static double findMedianSortedArrays(final int[] nums1, final int[] nums2) {

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
            int partitionX = low + (high - low) >> 1; //median of a smaller array

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
        return NEG_INF;

    }


    //O(m+n))
    public static double findMedianSortedArraysUsingMergeProcedure(int[] nums1, int[] nums2) {

        if ((null == nums1 || nums1.length == 0) && (null == nums2 || nums2.length == 0))
            return -(double) Integer.MAX_VALUE;


        int x = nums1.length;
        int y = nums2.length;

        int temp[] = new int[x + y];
        int t = 0;
        int i = 0, j = 0;

        while (i < x && j < y) {
            temp[t++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }

        while (i < x) {
            temp[t++] = nums1[i++];
        }

        while (j < y) {
            temp[t++] = nums2[j++];
        }

        if ((x + y) % 2 == 0) {
            int mid = (x + y) / 2;
            return (double) (temp[mid - 1] + temp[mid]) / 2;
        } else {
            return temp[(x + y) / 2];
        }


    }

}
