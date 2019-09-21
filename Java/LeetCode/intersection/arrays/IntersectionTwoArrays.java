package Java.LeetCode.intersection.arrays;

import Java.HelpersToPrint.GenericPrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/intersection-of-two-arrays/
 * 349. Intersection of Two Arrays
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 * <p>
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionTwoArrays {

    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2});
        test(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{9, 4});

    }

    private static void test(int[] nums1, int[] nums2, int[] expected) {
        System.out.println("\n Input; nums1 :" + GenericPrinter.toString(nums1) + " nums2 :" + GenericPrinter.toString(nums2) + " expected :" + GenericPrinter.toString(expected));
        System.out.println("Set Obtained :" + GenericPrinter.toString(intersection(nums1, nums2)));
    }


    //Runtime: 2 ms, faster than 98.27% of Java online submissions for Intersection of Two Arrays.
    //Memory Usage: 37.3 MB, less than 89.19% of Java online submissions for Intersection of Two Arrays.
    public static int[] intersection(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0)
            return new int[0];

        if (nums2 == null || nums2.length == 0)
            return new int[0];

        Set<Integer> set = new HashSet<>();
        List<Integer> response = new ArrayList<>();

        for (int i : nums1)
            set.add(i);

        for (int i : nums2)
            if (set.contains(i)) {
                response.add(i);
                set.remove(i);
            }

        int n = response.size();
        int output[] = new int[n];
        int x = 0;
        for (Integer i : response)
            output[x++] = i;

        return output;
    }
}
