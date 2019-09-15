package Java.LeetCode.intersection.arrays;

import Java.HelpersToPrint.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * 350. Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 * <p>
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * <p>
 * 1. What if the given array is already sorted? How would you optimize your algorithm?
 * See {@link IntersectionTwoArraysIISort}
 * <p>
 * 2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * Answer:
 * if elements are sorted that it does not matter. As the loop will terminated first for smaller array.
 * If elements are not sorted, then in solution 1, find should run on smaller array to minimize the memory foot print
 * <p>
 * 3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * <p>
 * <p>
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/373046/Multiple-solution-100-beat-or-Java-or-Follow-ups
 */
public class IntersectionTwoArraysII {
    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2, 2});
        test(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{9, 4});
        test(new int[]{4, 4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{9, 4, 4});
        test(new int[]{4, 4, 9, 5, 5}, new int[]{9, 4, 9, 8, 4, 5}, new int[]{9, 4, 4, 5});
        test(new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1});
        test(new int[]{1, 1, 1, 1}, new int[]{2, 2, 2, 2}, new int[]{});
        test(new int[]{-2, -8, 4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{9, 4});

    }

    private static void test(int[] nums1, int[] nums2, int[] expected) {
        System.out.println("\n Input; nums1 :" + GenericPrinter.toString(nums1) + " nums2 :" + GenericPrinter.toString(nums2) + " expected :" + GenericPrinter.toString(expected));
        System.out.println("Map   :" + GenericPrinter.toString(IntersectionTwoArraysIIMap.intersect(nums1, nums2)));
        System.out.println("Sort 1  :" + GenericPrinter.toString(IntersectionTwoArraysIISort.intersect(nums1, nums2)));
        System.out.println("Linear  search :" + GenericPrinter.toString(IntersectionTwoArraysIILinear.intersect(nums1, nums2)));

    }


}

/**
 * Complexity: O(m*n)
 * Space: O(1)
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Intersection of Two Arrays II.
 * Memory Usage: 37.5 MB, less than 62.90% of Java online submissions for Intersection of Two Arrays II.
 */
class IntersectionTwoArraysIILinear {
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> response = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {

            if (find(nums1[i], nums2)) {
                response.add(nums1[i]);
            }
        }


        if (response.isEmpty())
            return new int[0];

        int output[] = new int[response.size()];
        int x = 0;
        for (int i : response)
            output[x++] = i;

        return output;
    }

    private static boolean find(int num, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                nums[i] = Integer.MAX_VALUE;// mark it as used
                return true;
            }
        }
        return false;
    }
}

/**
 * Follow up: What if the given array is already sorted? How would you optimize your algorithm?
 * Complexity:
 * O(m*log(m) +n*log(n) ) + O(m+n) =>  O(m*log(m) +n*log(n) )
 * if already sorted => O(m+n)
 * <p>
 * Space: O(1)
 * <p>
 * Runtime: 2 ms, faster than 91.80% of Java online submissions for Intersection of Two Arrays II.
 * Memory Usage: 37.7 MB, less than 51.61% of Java online submissions for Intersection of Two Arrays II.
 */
class IntersectionTwoArraysIISort {

    public static int[] intersect(int[] nums1, int[] nums2) {

        List<Integer> response = new ArrayList<>();

        //empty list arguments.
        if ((nums1 == null || nums1.length == 0) || (nums2 == null || nums2.length == 0))
            return new int[0];


        //====== Already given sorted, but for my test cases, i sort them back in case elements are not sorted =======
        //O(m*log(m) +n*log(n) )
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
//                while (n1 < nums1.length && nums1[n1] == e1)
                n1++;

//                while (n2 < nums2.length && nums2[n2] == e2)
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
        if (response.isEmpty())
            return new int[0];

        int output[] = new int[response.size()];
        int x = 0;
        for (int i : response)
            output[x++] = i;

        return output;
    }

}

/**
 * Complexity: O(m*n)
 * Space: O(m+n)
 * <p>
 * Runtime: 4 ms, faster than 23.24% of Java online submissions for Intersection of Two Arrays II.
 * Memory Usage: 37.7 MB, less than 48.39% of Java online submissions for Intersection of Two Arrays II.
 *
 * @return
 */
class IntersectionTwoArraysIIMap {

    public static int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < nums1.length; i++)
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);

        for (int i = 0; i < nums2.length; i++)
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);

        int size = nums1.length > nums2.length ? nums1.length : nums2.length;
        List<Integer> result = new ArrayList<>(size);

        for (int i = 0; i < nums1.length; i++) {
            int item = nums1[i];
            if (map2.containsKey(item)) {
                int freq1 = map1.get(item);
                int freq2 = map2.get(item);

                for (int x = 0; x < Math.min(freq1, freq2); x++) {
                    result.add(item);
                }

                map2.remove(item);

            }

        }

        int[] ret = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i).intValue();
        }
        return ret;


    }
}