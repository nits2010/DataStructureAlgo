package Java.LeetCode.subarrays;

import Java.HelpersToPrint.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/longest-sub-array-sum-k/
 * <p>
 * Given an array arr[] of size n containing integers. The problem is to find the length of the longest sub-array having sum equal to the given value k.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = { 10, 5, 2, 7, 1, 9 },
 * k = 15
 * Output : 4
 * The sub-array is {5, 2, 7, 1}.
 * <p>
 * Input : arr[] = {-5, 8, -14, 2, 4, 12},
 * k = -5
 * Output : 5
 */
public class LongestSubArraySumK {


    public static void main(String[] args) {
        System.out.println(longestSubArraySumK(new int[]{10, 5, 2, 7, 1, 9}, 15));
        System.out.println(longestSubArraySumK(new int[]{-5, 8, -14, 2, 4, 12}, -5));
    }


    static List<Integer> longestSubArraySumK(int nums[], int k) {

        if (nums == null || nums.length == 0)
            return Collections.EMPTY_LIST;

        GenericPrinter.print(nums);
        /**
         * This will tell at what index(value) we saw a sum(key)
         */
        Map<Integer, Integer> map = new HashMap<>();

        int currentSum = 0;
        int start = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            currentSum += nums[i];

            if (currentSum == k) {

                if (i - start + 1 > max)
                    max = i - start + 1;

            }

            if (map.containsKey(currentSum - k)) {

                if (i - map.get(currentSum - k) > max) {
                    start = map.get(currentSum - k) + 1;
                    max = i - start + 1;
                }
            } else
                map.put(currentSum, i);

        }

        List<Integer> subarray = new ArrayList<>();
        for (int i = 0; i < max; i++)
            subarray.add(nums[i + start]);

        return subarray;

    }
}
