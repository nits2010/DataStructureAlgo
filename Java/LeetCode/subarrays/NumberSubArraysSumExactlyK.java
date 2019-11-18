package Java.LeetCode.subarrays;

import Java.helpers.GenericPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/
 * <p>
 * Given an unsorted array of integers, find the number of sub Arrays having sum exactly equal to a given number k.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = {10, 2, -2, -20, 10},
 * k = -10
 * Output : 3
 * Subarrays: arr[0...3], arr[1...4], arr[3..4]
 * have sum exactly equal to -10.
 * <p>
 * Input : arr[] = {9, 4, 20, 3, 10, 5},
 * k = 33
 * Output : 2
 * Subarrays : arr[0...2], arr[2...4] have sum
 * exactly equal to 33.
 */
public class NumberSubArraysSumExactlyK {

    public static void main(String[] args) {

        System.out.println("number of sub-array " + numberSubArraysSumExactlyK(new int[]{10, 2, -2, -20, 10}, -10));
        System.out.println("number of sub-array " + numberSubArraysSumExactlyK(new int[]{9, 4, 20, 3, 10, 5}, 33));
    }


    /**
     * store sum so far in currsum. Also maintain count of different values of currsum in a map.
     * If value of currsum is equal to desired sum at any instance increment count of subarrays by one.
     * The value of currsum exceeds from 'desired sum' by 'currsum – sum'. If this value is removed from currsum then desired sum can be obtained.
     * From the map find number of subarrays previously found having sum equal to currsum-sum. Excluding all those subarrays from current subarray,
     * gives new subarrays having desired sum. So increase count by the number of such subarrays. Note that when currsum is equal to desired sum then also check number of subarrays
     * previously having sum equal to 0. Excluding those subarrays from current subarray gives new subarrays having desired sum. Increase count by the number of subarrays
     * having sum 0 in that case
     *
     * @param nums
     * @param sum
     * @return
     */
    static int numberSubArraysSumExactlyK(int nums[], int sum) {
        System.out.println("Input sum :" + sum);
        GenericPrinter.print(nums);

        if (nums == null || nums.length == 0)
            return 0;

        /**
         * This map stores how many times a sum(key) has seen (value)
         */
        Map<Integer, Integer> map = new HashMap<>();

        int sol = 0;
        int currSum = 0;

        for (int i = 0; i < nums.length; i++) {

            currSum += nums[i];

            //if current sum is desired sum
            if (currSum == sum)
                sol++;

            if (map.containsKey(currSum - sum))
                sol += map.get(currSum - sum);


            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }


        return sol;
    }
}
