package Java.LeetCode;

import Java.helpers.GenericPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-06
 * Description: https://leetcode.com/problems/contiguous-array/
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * Example 1:
 * <p>
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * <p>
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 * <p>
 * https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
 * Input: arr[] = {1, 0, 1, 1, 1, 0, 0}
 * Output: 1 to 6 (Starting and Ending indexes of output subarray)
 * <p>
 * Input: arr[] = {1, 1, 1, 1}
 * Output: No such subarray
 * <p>
 * Input: arr[] = {0, 0, 1, 1, 0}
 * Output: 0 to 3 Or 1 to 4
 *
 * https://leetcode.com/articles/contiguous-array/
 */
public class ContiguousArrayZeroOne {
    public static void main(String[] args) {


        test(new int[]{0, 1}, 2);
        test(new int[]{0, 1, 0}, 2);
        test(new int[]{1, 0, 1, 1, 1, 0, 0}, 6);
        test(new int[]{1, 1, 1, 1}, 0);
        test(new int[]{0, 0, 1, 1, 0}, 4);
        test(new int[]{0, 0, 0, 0, 0}, 0);

    }

    private static void test(int[] nums, int expectedOutput) {
        System.out.print("Input: ");
        GenericPrinter.print(nums);

        LargestSubArrayWithEqualZeroAndOneBruteForce bruteForce = new LargestSubArrayWithEqualZeroAndOneBruteForce();
        LargestSubArrayWithEqualZeroAndOneUsingArray usingArray = new LargestSubArrayWithEqualZeroAndOneUsingArray();
        LargestSubArrayWithEqualZeroAndOneUsingArrayOptimized usingArrayOptimized = new LargestSubArrayWithEqualZeroAndOneUsingArrayOptimized();
        LargestSubArrayWithEqualZeroAndOneMap usingMap = new LargestSubArrayWithEqualZeroAndOneMap();


        int brute = bruteForce.findMaxLength(nums);
        int array = usingArray.findMaxLength(nums);
        int arrayOptimized = usingArrayOptimized.findMaxLength(nums);
        int map = usingMap.findMaxLength(nums);

        System.out.println("Brute :" + brute + " Array : " + array + " ArrayOptimized: " + arrayOptimized + " map : " + map + " expected :" + expectedOutput);

    }
}

/**
 * WE can simply get a sub-array and count zero and one, as soon they become equal we note the length of it
 * Complexity O(n^2)/O(1)
 */
class LargestSubArrayWithEqualZeroAndOneBruteForce {


    public int findMaxLength(int a[]) {

        if (a == null || a.length == 0 || a.length == 1)
            return 0;

        int maxLen = 0;

        for (int i = 0; i < a.length; i++) {

            int zero = 0, one = 0;
            for (int j = i; j < a.length; j++) {

                if (a[j] == 0)
                    zero++;
                else if (a[j] == 1)
                    one++;

                if (zero == one) {
                    if (maxLen < (j - i + 1)) {
                        maxLen = j - i + 1;
                    }
                }
            }
        }

        return maxLen;
    }
}


/**
 * Above solution does not take the advantage that if array contains only 0 and 1 then summing up them would make only 1 ( equal to number of 1 only ).
 * * We can assume that all 0 are -1 and we can sum-them-up, at any moment we see the sum=0 then this is the sub-array which has equal number of 0 and 1.
 * * But above is not sufficient as, the sub-array can be start from other than zero.
 * * For this case, we need to know did we saw a same sum earlier ? if so, then the element from that index to current index will also make sum = 0  ( current sum - previous sum = 0)
 * <p>
 * As the max sum of the array when all are 1 is n and min sum of the array when all are zero is -n.
 * We can create a Hash-Array which stores the index at which this particular sum occurred.
 */
class LargestSubArrayWithEqualZeroAndOneUsingArray {

    public int findMaxLength(int[] nums) {

        int[] hash = new int[2 * nums.length + 1];
        Arrays.fill(hash, -2);
        hash[nums.length] = -1;

        int maxLen = 0, currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += (nums[i] == 0 ? -1 : 1);

            if (hash[currentSum + nums.length] >= -1) {
                maxLen = Math.max(maxLen, i - hash[currentSum + nums.length]);
            } else {
                hash[currentSum + nums.length] = i;
            }

        }
        return maxLen;
    }


}

/**
 * Same as above but bit more optimized in written
 * <p>
 * Leetcode: fastest
 * Runtime: 7 ms, faster than 99.20% of Java online submissions for Contiguous Array.
 * Memory Usage: 51.1 MB, less than 100.00% of Java online submissions for Contiguous Array.
 */
class LargestSubArrayWithEqualZeroAndOneUsingArrayOptimized {

    public int findMaxLength(int[] nums) {

        int n = nums.length;
        int hash[] = new int[2 * n + 1];
        int maxLen = 0;
        int currentSum = 0;

        for (int i = 0; i < n; i++) {

            currentSum += (nums[i] == 0 ? -1 : 1);

            if (hash[currentSum + n] == 0 && currentSum != 0)
                hash[currentSum + n] = i + 1;

            maxLen = Math.max(maxLen, i + 1 - hash[currentSum + n]);
        }

        return maxLen;
    }
}


/**
 * Above solution does not take the advantage that if array contains only 0 and 1 then summing up them would make only 1 ( equal to number of 1 only ).
 * We can assume that all 0 are -1 and we can sum-them-up, at any moment we see the sum=0 then this is the sub-array which has equal number of 0 and 1.
 * But above is not sufficient as, the sub-array can be start from other than zero.
 * For this case, we need to know did we saw a same sum earlier ? if so, then the element from that index to current index will also make sum = 0  ( current sum - previous sum = 0)
 * <p>
 * WE can use hash map for storing these sum and that index at which this sum occurred. Since we need largest, then we'll store the first seen index only.
 * <p>
 * O(n)/O(n)
 */
class LargestSubArrayWithEqualZeroAndOneMap {

    public int findMaxLength(int a[]) {


        if (a == null || a.length == 0 || a.length == 1)
            return 0;

        int maxLen = 0;

        /**
         * Store the seen sum(key) with its first occurring index (value)
         */
        Map<Integer, Integer> map = new HashMap<>();

        int currentSum = 0;
        for (int i = 0; i < a.length; i++) {

            currentSum += (a[i] == 0) ? -1 : 1;

            if (currentSum == 0) {
                maxLen = Math.max(maxLen, i + 1);
            }

            /**
             * Have we saw this sum earlier ?
             */
            if (map.containsKey(currentSum)) {
                maxLen = Math.max(maxLen, i - map.get(currentSum));
            } else
                map.put(currentSum, i);
        }


        return maxLen;
    }
}
