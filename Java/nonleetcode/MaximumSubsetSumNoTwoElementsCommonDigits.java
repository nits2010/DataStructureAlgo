package Java.nonleetcode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-29
 * Description:
 * https://www.geeksforgeeks.org/maximum-subset-sum-such-that-no-two-elements-in-set-have-same-digit-in-them/
 * https://stackoverflow.com/questions/56302111/maximum-sum-of-array-with-no-repeating-digits
 * <p>
 * Given an array of N elements. Find the subset of elements which has maximum sum such that no two elements in the subset has common digit present in them.
 * <p>
 * Examples:
 * <p>
 * Input : array[] = {22, 132, 4, 45, 12, 223}
 * Output : 268
 * Maximum Sum Subset will be = {45, 223} .
 * All possible digits are present except 1.
 * But to include 1 either 2 or both 2 and 3 have
 * to be removed which result in smaller sum value.
 * <p>
 * Input : array[] = {1, 21, 32, 4, 5 }
 * Output : 42
 */
public class MaximumSubsetSumNoTwoElementsCommonDigits {

    public static void main(String []args) {
        int array[] = {1, 21, 32, 4, 5};
        System.out.println(maximumSubsetNoDigitsCommonInSet(array));
    }

    /**
     * As question asked two important aspects
     * 1. Maximum sum of the sub-set
     * 2. sub-set should not contains any two number having same digits;;
     * <p>
     * Brute-force
     * now clearly each array of n elements has 2^n sub-set. Build a sub-set out of given n elements and check does adding a new value would violate condition 2,
     * to check this we need to somehow get to know what are the digits available in a our given array so far. Since digits can be 0 to 9 only.
     * So, what we can do is, we keep having the masking [put 1 corresponding to a digit in a[i]] of a number in a subset. So whenever a new number we try to add,
     * we check does masking of this number is "kinda" present or not ( is there any place in our 0-9 mask which is already set and this number also trying to set)
     * <p>
     * Example: {} -> {22} -> {22,132} we discard 132 as in 0-9 mask, 2 is already set ->{22,14} we keep growing like this and keep having sum so far and max so far.
     * <p>
     * Complexity: n(for iterating each element in array to find sum- Keep running sum) +   2^n (for subset)
     * if you don't keep running sum then it will be n*2^n otherwise ( n+2^n ) = O(2^n) in worst case.
     * <p>
     * <p>
     * Dynamic programming intuition
     * Our main bottle neck is 2^n. As you can see, what we essentially try to do above is finding a set s.t. it has some digits present in it and next element is added only if room available.
     * Which is nothing but finding a element whose digits not present in current set.
     * If you recall, for 0-9 how many states of binary could have ? guess
     * its 2^10 = 1024 states.
     * <p>
     * now imagine that each state is represent its location in mask for Example
     * i = 10 and in 10 bits its binary is "0000001010" so at whatever index it has 1 [ in this case its 7, 9 indexes ]
     * if array number is also have same digits present in its binary represent then they collide essentially.
     * <p>
     * Hence
     * a bitmask of a number were ith bit is set if the digit i is present in the number.
     * <p>
     * We find all the number whose bitmask is subset of current ith bitmask and combine them to those number whose mask is unset for this bitmask
     * <p>
     * <p>
     * How To find, a number mask is subset of another number bitmask;
     * if you observe, a number say "01010" and another number "00010" we can say that "00010" is sub-set of "01010" right?
     * but how to find using program?
     * simple if we a "|" b == a if it returns set 'a' [ in our case "01010"] then b = "00010" is sub-set of 'a'
     * <p>
     * How to find a those number whose mask is unset for this bitmask?
     * say a number "01010" [10] and another number "10100" [20] we need to see does "20" bits are set or not in "01010"
     * if you ^ them then it will be
     * 01010 ^ 10100 = 11110
     * where as
     * 01010 ^ 01000 = 10010
     * <p>
     * <p>
     * Hence
     * dp[i] = Max  ( dp[i], a[j] + dp[i^mask(a[j])])
     *
     * @param array
     * @return
     */
    private static int maximumSubsetNoDigitsCommonInSet(int[] array) {


        final int MAX = 1024;

        int dp[] = new int[MAX];

        Arrays.fill(dp, -1);


        int maximumSum = Integer.MIN_VALUE;
        for (int i = 0; i < MAX; i++) {

            maximumSum = Math.max(maximumSum, maximumSubsetNoDigitsCommonInSet(array, dp, i));
        }


        return maximumSum;
    }

    private static int maximumSubsetNoDigitsCommonInSet(int[] array, int[] dp, int bitmask) {

        if (bitmask == 0)
            return 0;

        if (dp[bitmask] != -1)
            return dp[bitmask];

        for (int j = 0; j < array.length; j++) {

            int mask = getMask(array[j]);

            //mask is subset of another number bitmask;
            if ((bitmask | mask) == bitmask) {

                dp[bitmask] = Math.max(dp[bitmask], array[j] + Math.max(0, dp[bitmask ^ mask]));
            }
        }
        return dp[bitmask];
    }

    private static int getMask(int n) {

        if (n == 0)
            return 0;

        int mask = 0;
        while (n > 0) {

            int rem = n % 10;
            mask |= (1 << rem);
            n = n / 10;
        }
        return mask;
    }


}

