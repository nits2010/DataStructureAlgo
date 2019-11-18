package Java.LeetCode;

import Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-11
 * Description:https://leetcode.com/problems/relative-sort-array/
 * https://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/
 * <p>
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * <p>
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */
public class RelativeSortArray {

    public static void main(String[] args) {

        test(new int[]{2, 21, 43, 38, 0, 42, 33, 7, 24, 13, 12, 27, 12, 24, 5, 23, 29, 48, 30, 31}, new int[]{2, 42, 38, 0, 43, 21});
        test(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6});
        test(new int[]{2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}, new int[]{2, 1, 8, 3});
    }

    static void test(int a[], int b[]) {
        System.out.println("\nInput ");
        GenericPrinter.print(a);
        GenericPrinter.print(b);
        int[] usingMap = SortArrayAccordingToOrderDefinedAnotherArray.relativeSortArray(a, b);
        System.out.println("usingMap ");
        GenericPrinter.print(usingMap);

        int[] bucketSort = SortArrayAccordingToOrderDefinedAnotherArrayUsingBuckets.relativeSortArray(a, b);
        System.out.println("bucketSort ");
        GenericPrinter.print(bucketSort);

        int[] customSort = SortArrayAccordingToOrderDefinedAnotherArrayUsingCustomSort.relativeSortArray(a, b);
        System.out.println("customSort ");
        GenericPrinter.print(customSort);
    }
}

class SortArrayAccordingToOrderDefinedAnotherArray {
    /**
     * Runtime: 2 ms, faster than 69.70% of Java online submissions for Relative Sort Array.
     * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Relative Sort Array.
     * <p>
     * Length(arr1) = n and Length(arr2) = m and elements that are not present in arr2 is of 'p' size
     * Complexity:
     * O(n) + O(m) + O(PLogP) = As P can be up to n, as there is no elemnet in arr2 which matches to arr1. Hence we end up having same number of element left in arr1.
     * So; O(PLogP) -> O(nlogn)
     * Hence
     * O(nlogn)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {

        if (null == arr1 || arr1.length == 0)
            return arr1;

        int result[] = new int[arr1.length];

        /**
         * Key: elements
         * value: occurrence in arr1 of element
         */
        Map<Integer, Integer> countMap = new HashMap<>();

        //O(n)
        for (int i = 0; i < arr1.length; i++)
            countMap.put(arr1[i], countMap.getOrDefault(arr1[i], 0) + 1);


        int k = 0;
        //Overall; as when all elements of arr1 are same, then this loop can give at most 1 element which is available in map; for that element only its run On) time
        //for rest, its O(1)
        //So overall O(m)
        for (int i = 0; i < arr2.length; i++) { //O(m)

            if (countMap.containsKey(arr2[i])) {
                int count = countMap.get(arr2[i]);

                while (count > 0) { //this can go at max O(n) when in arr1 all elements are same
                    result[k++] = arr2[i];
                    count--;
                }

                countMap.remove(arr2[i]);
            }
        }

        //Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
        if (!countMap.isEmpty()) {

            List<Integer> sorted = new ArrayList<>();

            for (Integer key : countMap.keySet()) {
                int count = countMap.get(key);

                while (count > 0) {
                    sorted.add(key);
                    count--;
                }

            }

            Collections.sort(sorted);
            for (Integer e : sorted)
                result[k++] = e;

        }

        return result;


    }
}

/**
 * Instead of using Map, we'll use buckets as each element can be upto 1000 only
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Relative Sort Array.
 * Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Relative Sort Array.
 * <p>
 * <p>
 * Note: we can replace 1000 to max value in the array, to save space.
 */
class SortArrayAccordingToOrderDefinedAnotherArrayUsingBuckets {

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {

        if (null == arr1 || arr1.length == 0)
            return arr1;

        int buckets[] = new int[1001];

        for (int i : arr1)
            buckets[i]++;

        int result[] = new int[arr1.length];
        int k = 0;

        for (int i = 0; i < arr2.length; i++) {

            while (buckets[arr2[i]]-- > 0)
                result[k++] = arr2[i];
        }

        //Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
        for (int i = 0; i < 1001; i++) {
            if (buckets[i] > 0)
                while (buckets[i]-- > 0)
                    result[k++] = i;
        }

        return result;
    }

}

/**
 * Runtime: 34 ms, faster than 11.14% of Java online submissions for Relative Sort Array.
 * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Relative Sort Array.
 */
class SortArrayAccordingToOrderDefinedAnotherArrayUsingCustomSort {

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {

        if (null == arr1 || arr1.length == 0)
            return arr1;

        /**
         * Key: element in arr2;
         * value = index of element in arr2
         *
         * Remember arr2 elements are distinct, so index will never messup
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++)
            map.put(arr2[i], i);

        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++)
            sorted.add(arr1[i]);

        Collections.sort(sorted, (o1, o2) -> {
            int index1, index2;

            if (!map.containsKey(o1))
                index1 = o1 + 1000;
            else
                index1 = map.get(o1);

            if (!map.containsKey(o2))
                index2 = o2 + 1000;
            else
                index2 = map.get(o2);


            return index1 - index2;
        });


        int ans[] = new int[arr1.length];
        int k = 0;
        for (int i : sorted)
            ans[k++] = i;


        return ans;
    }
}