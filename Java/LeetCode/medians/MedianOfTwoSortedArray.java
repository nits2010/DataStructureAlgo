package Java.LeetCode.medians;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-19
 * Description:
 */
public class MedianOfTwoSortedArray {

    public static void main (String []args){
        int nums1[] = {3,4};
        int nums2[] = {1,2};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int x = nums1.length;
        int y = nums2.length;
        int n = x+y;

        final int NEG_INF = Integer.MIN_VALUE;
        final int POS_INF = Integer.MAX_VALUE;

        if(x > y ){
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0, end = x;
        while (start<=end){

            int partitionX = (start + end) >> 1;
            int partitionY = (x+y+1)/2 - partitionX;

            int maxLeftX = (partitionX == 0) ? NEG_INF : nums1[partitionX-1];
            int maxLeftY = (partitionY == 0) ? NEG_INF : nums2[partitionY-1];

            int minRightX = (partitionX == x) ? POS_INF : nums1[partitionX];
            int minRightY = (partitionY == y) ? POS_INF : nums2[partitionY];

            if(maxLeftX <= minRightY && maxLeftY <= minRightX){
                if(n %2 == 0){
                    return (double) ( Math.max(maxLeftX,maxLeftY) + Math.min(minRightX,minRightY) )/2;
                }else {
                    return  Math.max(maxLeftX,maxLeftY);
                }
            }else if (maxLeftX > minRightY ){
                end = partitionX - 1;
            }else
                start = partitionX+1;
        }
        return (double)NEG_INF;

    }
}
