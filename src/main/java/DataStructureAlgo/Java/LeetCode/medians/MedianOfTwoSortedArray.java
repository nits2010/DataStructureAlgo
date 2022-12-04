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

    public static double findMedianSortedArrays(final int[] X, final int[] Y) {

        if (X.length > Y.length) {
            return findMedianSortedArrays(Y, X);
        }

        int x = X.length;
        int y = Y.length;
        int n = x + y;

        final int NEG_INF = Integer.MIN_VALUE;
        final int POS_INF = Integer.MAX_VALUE;


        /**
         * Do binary search on smaller array
         */
        int start = 0, end = x;
        while (start <= end) {

            //Find where we should partition array X
            int partitionX = start + (end - start) >> 1; //median of smaller array

            //Find where we should partition array Y such that both partition elements (partition of X + partition of Y) are equal or 1 more on left side
            // (x + y + 1) / 2 gives you the middle point when we combine both the array and since we already have 0 to partitionX element, then remove those to make them equal
            int partitionY = (x + y + 1) / 2 - partitionX;


            //If no element left on array X on left partition
            int maxLeftX = (partitionX == 0) ? NEG_INF : X[partitionX - 1];

            //If no element left on array Y on left partition
            int maxLeftY = (partitionY == 0) ? NEG_INF : Y[partitionY - 1];

            //If no element Right on array X on right partition
            int minRightX = (partitionX == x) ? POS_INF : X[partitionX];

            //If no element Right on array Y on right partition
            int minRightY = (partitionY == y) ? POS_INF : Y[partitionY];

            //If all the element on Left side of X and Y are smaller then element on right side of Y and X
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {

                if (n % 2 == 0) {
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //If we to far on left side, reduce the left space (number of element on lef side)
                end = partitionX - 1;
            } else
                start = partitionX + 1;//otherwise increase the space
        }
        return (double) NEG_INF;

    }


    //O(m+n))
    public static double findMedianSortedArraysUsingMergeProcedure(int[] A, int[] B) {

        if ((null == A || A.length == 0) && (null == B || B.length == 0))
            return -(double) Integer.MAX_VALUE;


        int x = A.length;
        int y = B.length;

        int temp[] = new int[x + y];
        int t = 0;
        int i = 0, j = 0;

        while (i < x && j < y) {

            int e1 = A[i];
            int e2 = B[j];

            if (e1 <= e2) {

                temp[t++] = e1;
                i++;
            } else {
                temp[t++] = e2;
                j++;
            }

        }

        while (i < x) {
            temp[t++] = A[i++];
        }

        while (j < y) {
            temp[t++] = B[j++];
        }

        if ((x + y) % 2 == 0) {
            int mid = (x + y) / 2;
            return (double) (temp[mid - 1] + temp[mid]) / 2;
        } else {
            return temp[(x + y) / 2];
        }


    }

}
