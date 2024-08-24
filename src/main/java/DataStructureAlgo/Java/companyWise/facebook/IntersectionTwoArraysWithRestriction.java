package DataStructureAlgo.Java.companyWise.facebook;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.LeetCode.intersection.arrays.IntersectionTwoArrays;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-02
 * Description:
 * Similar to this {@link IntersectionTwoArrays}, added restriction
 * Then they ask you to solve it under these constraints:
 * 1. O(n+m) time and O(1) space (the resulting array of intersections is not taken into consideration).
 * 2. You are told the lists are sorted.
 * <p>
 * Cases to take into consideration include:
 * duplicates, negative values, single value lists, 0's, and empty list arguments.
 * Other considerations might include sparse arrays.
 * <p>
 * [Facebook]
 * <p>
 * {@link DataStructureAlgo.Java.LeetCode.intersection.arrays.IntersectionTwoArraysII}
 */
public class IntersectionTwoArraysWithRestriction {

    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2});
        test(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{9, 4});
        test(new int[]{-2, -8, 4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{9, 4});

    }

    private static void test(int[] nums1, int[] nums2, int[] expected) {
        System.out.println("\n Input; nums1 :" + CommonMethods.toString(nums1) + " nums2 :" + CommonMethods.toString(nums2) + " expected :" + CommonMethods.toString(expected));
        System.out.println("Set Obtained :" + intersection(nums1, nums2));
    }


    public static List<Integer> intersection(int[] nums1, int[] nums2) {

        List<Integer> response = new ArrayList<>();

        //empty list arguments.
        if ((nums1 == null || nums1.length == 0) || (nums2 == null || nums2.length == 0))
            return response;


        //====== Already given sorted, but for my test cases, i sort them back in case elements are not sorted =======
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //==============================

        int n1 = 0, n2 = 0;

        while (n1 < nums1.length && n2 < nums2.length) {
            int e1 = nums1[n1];
            int e2 = nums2[n2];

            if (e1 == e2) {
                response.add(nums1[n1]);


                //avoid duplicates
                while (n1 < nums1.length && nums1[n1] == e1)
                    n1++;

                while (n2 < nums2.length && nums2[n2] == e2)
                    n2++;

            } else {

                /**
                 * You can do binary search here as well. But overall complexity would still O(n)
                 */
                if (nums1[n1] > nums2[n2])
                    while (n2 < nums2.length && nums1[n1] > nums2[n2])
                        n2++;
                else
                    while (n1 < nums1.length && nums1[n1] < nums2[n2])
                        n1++;

            }
        }


        return response;
    }
}
