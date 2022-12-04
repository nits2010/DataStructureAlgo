package DataStructureAlgo.Java.nonleetcode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.nonleetcode.InterviewBit.MatrixChainMultiplication;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-11
 * Description: https://www.geeksforgeeks.org/remove-minimum-elements-either-side-2min-max/
 * Given an unsorted array, trim the array such that twice of minimum is greater than maximum in the trimmed array. Elements should be removed either end of the array.
 * <p>
 * Number of removals should be minimum.
 * <p>
 * Examples:
 * <p>
 * arr[] = {4, 5, 100, 9, 10, 11, 12, 15, 200}
 * Output: 4
 * We need to remove 4 elements (4, 5, 100, 200)
 * so that 2*min becomes more than max.
 * <p>
 * <p>
 * arr[] = {4, 7, 5, 6}
 * Output: 0
 * We don't need to remove any element as
 * 4*2 > 7 (Note that min = 4, max = 8)
 * <p>
 * arr[] = {20, 7, 5, 6}
 * Output: 1
 * We need to remove 20 so that 2*min becomes
 * more than max
 * <p>
 * arr[] = {20, 4, 1, 3}
 * Output: 3
 * We need to remove any three elements from ends
 * like 20, 4, 1 or 4, 1, 3 or 20, 3, 1 or 20, 4, 1
 * <p>
 * Best solution : {@link RemoveMinimum2TimesMinGreaterThenMaxIterate}
 */
public class RemoveMinimum2TimesMinGreaterThenMax {

    public static void main(String[] args) {
        test(new int[]{4, 5, 100, 9, 10, 11, 12, 15, 200}, 4);
        test(new int[]{4, 7, 5, 6}, 0);
        test(new int[]{20, 7, 5, 6}, 1);
        test(new int[]{20, 4, 1, 3}, 3);
        test(new int[]{1, 2, 3, 4}, 2);
        test(new int[]{4, 3, 2, 1}, 2);
        test(new int[]{4, 8, 16, 32}, 3);

    }

    public static void test(int[] nums, int expected) {
        System.out.println("-------\nNums:" + GenericPrinter.toString(nums) + "\nExpected  : " + expected);
        RemoveMinimum2TimesMinGreaterThenMaxBacktrack backtrack = new RemoveMinimum2TimesMinGreaterThenMaxBacktrack();
        RemoveMinimum2TimesMinGreaterThenMaxDP dp = new RemoveMinimum2TimesMinGreaterThenMaxDP();
        RemoveMinimum2TimesMinGreaterThenMaxIterate iterate = new RemoveMinimum2TimesMinGreaterThenMaxIterate();

        System.out.println("Backtrack : " + backtrack.minRemove(nums));
        System.out.println("Top Down  : " + dp.minRemoveTopDown(nums));
        System.out.println("bottom up : " + dp.minRemoveBottomUp(nums));
        System.out.println("Iterate   : " + iterate.minRemove(nums));
    }
}


/**
 * In both dp solution, the bottle neck is extra n iteration done for finding min-max.
 * We can reduce it to O(logn) using segment tree.
 * https://www.geeksforgeeks.org/remove-minimum-elements-from-either-side-such-that-2min-becomes-more-than-max-set-2/
 * <p>
 * In earlier Dp solution "Bottom up" we went width wise to find the min-max in that width [start,end].
 * Then finding min-max in range of [start,end] alternatevlely we can keep track the min-max at the run time it self.
 * so we don't need to calculate all the time. O(1)
 * <p>
 * O(n^2)
 */
class RemoveMinimum2TimesMinGreaterThenMaxIterate {

    public int minRemove(int a[]) {
        if (null == a || a.length == 0)
            return 0;

        int min, max;
        int minRemoval = 0;

        for (int start = 0; start < a.length; start++) {

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            //keep increasing the width by adding more element on right side pointed by 'end'
            for (int end = start; end < a.length; end++) {

                //keep track of min-max
                min = Math.min(min, a[end]);
                max = Math.max(max, a[end]);

                //Do we need to remove any element in this window ?
                if (2 * min <= max) //No removal required
                    break;

                //remove is required from either of side, hence at least 1; keep track the max removal
                minRemoval = Math.max((end - start + 1), minRemoval);

            }
        }

        return (a.length - minRemoval);
    }


}


/**
 * An upper bound on solution of above recurrence would be O(n x 2^n).
 */
class RemoveMinimum2TimesMinGreaterThenMaxBacktrack {

    public int minRemove(int[] a) {
        if (null == a || a.length == 0)
            return 0;

        return minRemove(a, 0, a.length - 1);
    }

    /**
     * Our goal: Remove minimum elements s.t. (2*min > max); Hence maximum length of array possible; minimum operation
     * Our choices: Either remove from low or high
     * Our constrains:
     * 1. Can't remove more then n elements
     * 2. only remove when 2*min > max
     * <p>
     * Time Complexity: O(n*2^n)
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    private int minRemove(int[] a, int low, int high) {

        /**
         * Our constrains:
         * 1. Can't remove more then n elements
         * Only one element left in the array of out of boundary.
         * No removal required
         */
        if (low >= high)
            return 0;

        int[] minMax = minMaxInRange(a, low, high);

        //Current array is at right portion
        if (minMax[0] * 2 > minMax[1])
            return 0;


        int removeAtLow = minRemove(a, low + 1, high);
        int removeAtHigh = minRemove(a, low, high - 1);
        return Math.min(removeAtLow, removeAtHigh) + 1;


    }

    //O(n)
    static int[] minMaxInRange(int[] a, int low, int high) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (low <= high) {

            if (min > a[low])
                min = a[low];

            if (max < a[low])
                max = a[low];

            low++;
        }

        return new int[]{min, max};
    }

}


/**
 * Overlapping sub-problems, Cache it
 * An upper bound on solution of above recurrence would be O(n*n^2) = O(n^3).
 */
class RemoveMinimum2TimesMinGreaterThenMaxDP {

    /************** Top down *****************/

    public int minRemoveTopDown(int a[]) {
        if (null == a || a.length == 0)
            return 0;

        int[][] memo = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++)
            Arrays.fill(memo[i], -1);

        return minRemove(a, 0, a.length - 1, memo);
    }

    /**
     * Our goal: Remove minimum elements s.t. 2*min > max; Hence maximum length of array possible; minimum operation
     * Our choices: Either remove from low or high
     * Our constrains:
     * 1. Can't remove more then n elements
     * 2. only remove when 2*min > max
     * <p>
     * Overlapping sub-problems, Cache it
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    private int minRemove(int[] a, int low, int high, int[][] memo) {

        /**
         * Our constrains:
         * 1. Can't remove more then n elements
         * Only one element left in the array of out of boundary.
         * No removal required
         */
        if (low >= high)
            return 0;

        if (memo[low][high] != -1)
            return memo[low][high];

        int[] minMax = minMaxInRange(a, low, high); //O(n)

        //Current array is at right portion
        if (minMax[0] * 2 > minMax[1])
            return 0;


        int removeAtLow = minRemove(a, low + 1, high, memo); //O(n)
        int removeAtHigh = minRemove(a, low, high - 1, memo); //O(n)
        return Math.min(removeAtLow, removeAtHigh) + 1;


    }

    /************** Bottom up *****************/

    /**
     * {@link MatrixChainMultiplication}
     *
     * @param a
     * @return
     */
    public int minRemoveBottomUp(int a[]) {
        if (null == a || a.length == 0)
            return 0;

        int[][] memo = new int[a.length][a.length];

        for (int width = 1; width <= a.length; width++) {

            for (int start = 0; start < a.length - width + 1; start++) {

                int end = width + start - 1;

                int[] minMax = minMaxInRange(a, start, end);

                if (minMax[0] * 2 > minMax[1])
                    continue;

                //either remove from start or end addition to this item
                memo[start][end] = Math.min(memo[start + 1][end], memo[start][end - 1]) + 1;

            }
        }

        return memo[0][a.length - 1];

    }


    static int[] minMaxInRange(int a[], int low, int high) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (low <= high) {

            if (min > a[low])
                min = a[low];

            if (max < a[low])
                max = a[low];

            low++;
        }

        return new int[]{min, max};
    }


}

