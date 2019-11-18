package Java.LeetCode;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-08
 * Description: https://leetcode.com/problems/non-decreasing-array/
 * Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
 * <p>
 * We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
 * <p>
 * Example 1:
 * <p>
 * Input: [4,2,3]
 * Output: True
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2:
 * <p>
 * Input: [4,2,1]
 * Output: False
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 * Note: The n belongs to [1, 10,000].
 */
public class ConvertToNonDecreasingArray {

    public static void main(String[] args) {

        test(new int[]{3, 4, 2, 3}, true);
        test(new int[]{4, 2, 3}, true);
        test(new int[]{4, 2, 1}, false);
        test(new int[]{1, 4, 6, 18, 10, 12, 14, 11}, false);
        test(new int[]{1, 4, 6, 18, 10, 12, 14, 16}, true);
        test(new int[]{12, 10, 8, 14, 13, 12, 11, 16}, false);
        test(new int[]{20, 4, 6, 10, 12, 14, 16, 18}, true);
        test(new int[]{4, 6, 10, 12, 14, 16, 18, 10}, true);
    }

    public static void test(int nums[], boolean expected) {
        ConvertToNonDecreasingArrayUsingSlops slops = new ConvertToNonDecreasingArrayUsingSlops();
        ConvertToNonDecreasingArrayUsingSlopsV2 slopsV2 = new ConvertToNonDecreasingArrayUsingSlopsV2();

        System.out.println("\nInput ");
        GenericPrinter.print(nums);
        System.out.println("V1 " + slops.checkPossibility(nums) + " Expected :" + expected);
        System.out.println("V2 " + slopsV2.checkPossibility(nums) + " Expected :" + expected);
    }
}

/**
 * If we observe carefully, we can say that the answer depends how elements are changing the "direction" of increasing and decreasing flow.
 * Lets take an example
 * [ 1 4 6 18 10 12 14 11 ] -> We can see that element change its increasing/decreasing at first the point 10 (since 18 > 10 ) and then again on
 * 11 ( as 14 > 11 )
 * <p>
 * we can simply say that its not possible to change it to any way to make it Increasing order
 * Or
 * [ 12 10 8 14 13 12 11 16 ] -> First point 10  ( as 12 > 10 ) and 8 ( 10 > 8 ) ...and many other
 * <p>
 * Lets understand this with a graph [ 1 4 6 18 10 12 14 11 ]
 * *
 * *
 * *
 * *               14
 * *       18    12   11
 * *      6   10
 * *     4
 * *    1
 * *
 * You see the elements change direction at two different point at 10 and 11,
 * <p>
 * Rule 1: If array has more than one slopping point then its not possible to convert it to increasing order.
 * <p>
 * Now, we need to find when we can convert it to increasing order though ?
 * * [ 1 4 6 18 10 12 14 16 ]
 * *             16
 * *           14
 * *      18  12
 * *     6  10
 * *    4
 * *  1
 * Here only 1 direction change, then we might possibly can change the array to non-decreasing order.
 * <p>
 * How do we decide though ?
 * In order to change a number s.t. its in increasing order is define by the nearest element of the changing point.why?
 * Because if they form an increasing patten then we can take any of them to make array as non-decreasing pattern. Right
 * example
 * [ 1 4 6 18 10 12 14 16 ]  -> [ 1 4 6 10 10 12 14 16 ]  [ here change point is 10, neighbour were 18 and 12 and since  18 > 10 and 10 < 12 i.e. 18 > 12
 * <p>
 * <p>
 * so all its depend on neighbour elements.
 * Good enough.
 * But there is a edge case of element at the corner ( first element or last element ) as they dont have neighbour. In this case change any of them to other neighbour would work
 * [ 20 4 6 10 12 14 16 18 ] -> [ 4 4 6 10 12 14 16 18 ] { first element cause issue}
 * [ 4 6 10 12 14 16 8 ] -> [ 4 6 10 12 14 16 16 ] { last element cause issue}
 * Hence in this case its simply true;
 * <p>
 * Algo:
 * 1. Find is there any slope point
 * 2. if slope point exist change point > 1 ; return false
 * 3. if slope point exist & change point = 1 ; Check neighbour { check does the change point is first or last element }
 * 4. if slope point don't exist (change point=  0 ) then return true
 * <p>
 * Leetcode: Runtime: 1 ms, faster than 99.60% of Java online submissions for Non-decreasing Array.
 * Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Non-decreasing Array.
 */
class ConvertToNonDecreasingArrayUsingSlops {

    public boolean checkPossibility(int[] nums) {


        int changePoints = 0;
        int slopPoint = 0;

        for (int i = 1; i < nums.length; i++) {

            /**
             * they are breaking increasing order flow [ not we doesn't include = as we need to convert it to non-decreasing order ]
             */
            if (nums[i] < nums[i - 1]) {
                changePoints++;
                slopPoint = i;
            }
        }


        /**
         * Array is in increasing order already
         */
        if (changePoints == 0)
            return true;

        /**
         * Multiple slope points, not possible
         */
        if (changePoints > 1)
            return false;

        /**
         * Exist in border; note we have used i from 1 , not zero
         */
        if (slopPoint <= 1 || slopPoint == nums.length - 1)
            return true;

        /**
         * Has neighbours
         */
        if (nums[slopPoint - 1] > nums[slopPoint + 1] && nums[slopPoint] < nums[slopPoint - 2])
            return false;

        /**
         * i.e nums[slopPoint - 1]  > nums[slopPoint + 1]  And nums[slopPoint] >= nums[slopPoint - 2]
         *
         */
        return true;
    }
}


/**
 * Apply same logic as above, but break it as soon as possible we find an issue
 * Runtime: 1 ms, faster than 99.60% of Java online submissions for Non-decreasing Array.
 * Memory Usage: 39.4 MB, less than 100.00% of Java online submissions for Non-decreasing Array.
 */
class ConvertToNonDecreasingArrayUsingSlopsV2 {

    public boolean checkPossibility(int[] nums) {


        int changePoints = 0;
        int slopPoint = -1;

        for (int i = 0; i < nums.length - 1; i++) {

            /**
             * they are breaking increasing order flow [ not we doesn't include = as we need to convert it to non-decreasing order ]
             */
            if (nums[i] > nums[i + 1]) {
                changePoints++;

                /**
                 * Multiple slope points, not possible
                 */
                if (changePoints > 1)
                    return false;

                /**
                 * We have a slop point and we can't fix it.
                 * Example; 1 4 6 18 10 12 14 11
                 * i would be at 10 ( index = 4 )
                 * 4 >=1 && 4 <len-2 && 18 > 12 && 10 > 14 -> We can make it
                 *
                 * then
                 * i would be at 11 ( index 7 ) but at this point we already saw a slop point, hence we can't make it
                 *
                 */
                if (i >= 1 && i < nums.length - 2 && nums[i - 1] > nums[i + 1] && nums[i] > nums[i + 2])
                    return false;

                /**
                 * Multiple slope points, not possible
                 */
                if (slopPoint != -1)
                    return false;
                else
                    slopPoint = i;
            }
        }


        return true;
    }
}

class ConvertToNonDecreasingArrayByAlteringTheValues {

    public boolean checkPossibility(int[] nums) {

        int count = 0;
        for (int i = 0; i < nums.length - 1 && count <= 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (i > 0) {
                    if (nums[i - 1] <= nums[i + 1]) {
                        nums[i] = nums[i - 1];
                    } else {
                        nums[i + 1] = nums[i];
                    }
                }
                count++;
            }
        }

        return count <= 1;
    }
}