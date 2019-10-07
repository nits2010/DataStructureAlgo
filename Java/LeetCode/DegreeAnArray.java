package Java.LeetCode;

import Java.helpers.GenericPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-02
 * Description: https://leetcode.com/problems/degree-of-an-array/
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * <p>
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * <p>
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * Note:
 * <p>
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeAnArray {

    public static void main(String[] args) {

        test(new int[]{1, 2, 2, 3, 1});
        test(new int[]{1, 2, 2, 3, 1, 4, 2});
        test(new int[]{11, 2, 3, 4, 5});
        test(new int[]{1, 1, 1, 1, 1});
        test(new int[]{1, 1, 2, 2, 1});
        test(new int[]{1, 1, 1, 2, 2, 2});
    }

    private static void test(int[] nums) {
        GenericPrinter.print(nums);
        System.out.println();
        System.out.println("Simple Count -> " + DegreeAnArraySimpleCount.findShortestSubArray(nums));
        System.out.println("Single scan -> " + DegreeAnArraySingleScan.findShortestSubArray(nums));
    }
}

/**
 * Runtime: 21 ms, faster than 64.02% of Java online submissions for Degree of an Array.
 * Memory Usage: 42.5 MB, less than 16.27% of Java online submissions for Degree of an Array.
 * <p>
 * O(n)/ O(n)
 * Simply count the occurrence (max called degree) and all the indices for the items
 * Then find the shortest for highest degree item by its first and last index
 */
class DegreeAnArraySimpleCount {

    public static int findShortestSubArray(int[] nums) {

        if (null == nums || nums.length == 0)
            return 0;


        /**
         * This will contain all the item and its indices where it occurred
         */
        final Map<Integer, List<Integer>> map = new HashMap<>();

        /**
         * What is the max count of a number [ this could be same for two or more numbers too ]
         */
        int degree = 0;
        for (int i = 0; i < nums.length; i++) { //O(n)

            int e = nums[i];

            if (!map.containsKey(e))
                map.put(e, new ArrayList<>());


            map.get(e).add(i);
            degree = Math.max(degree, map.get(e).size());

        }


        int shortest = Integer.MAX_VALUE;

        for (Integer key : map.keySet()) { //(O(n)

            List<Integer> current = map.get(key);
            /**
             * If this element gives the greatest degree, find the shortest
             */
            if (degree == current.size())
                shortest = Math.min(shortest, current.get(current.size() - 1) - current.get(0) + 1);

        }

        return shortest;

    }
}


/**
 * Runtime: 21 ms, faster than 64.02% of Java online submissions for Degree of an Array.
 * Memory Usage: 42.5 MB, less than 16.27% of Java online submissions for Degree of an Array.
 * <p>
 * O(n)/ O(n)
 * In above we run two pass because we don't know that what is the end index of a highest degree element.
 * What if we find this information in first scan it self ?
 * if we get to know this information somehow, then since we already finding the degree, then based on this we can get the shortest one.
 * <p>
 * So the idea is to find degree and shortest in same loop.
 * The important observation is an element change its frequency at index "i" only, if it change its frequency
 * this frequency can be either max so far or this is the first one.
 */
class DegreeAnArraySingleScan {

    public static int findShortestSubArray(int[] nums) {
        if (null == nums || nums.length == 0)
            return 0;
        /**
         * This will tell what is the first index of a element
         */
        Map<Integer, Integer> indexOfElement = new HashMap<>();

        /**
         * This will tell what is the frequency of a element
         */
        Map<Integer, Integer> frequency = new HashMap<>();

        int degree = 0, shortest = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            int ele = nums[i];
            /**
             * Seeing this element first time
             */
            if (!indexOfElement.containsKey(ele)) {
                indexOfElement.put(ele, i);
            }

            /**
             * Count its frequency
             */
            frequency.put(ele, frequency.getOrDefault(ele, 0) + 1);

            /**
             * if this is first time we see this high degree
             */
            if (frequency.get(ele) > degree) {
                degree = frequency.get(ele);
                shortest = i - indexOfElement.get(ele) + 1; //as we see this high degree first time
            } else if (degree == frequency.get(ele)) {
                //We saw same degree (highest) again
                shortest = Math.min(shortest, i - indexOfElement.get(ele) + 1); //as we see this high degree another time
            }
        }
        return shortest;
    }
}