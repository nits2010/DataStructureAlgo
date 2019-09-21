package Java.companyWise.thoughtspot;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 20/09/19
 * Description:
 * <p>
 * <p>
 * Given a positive integer K, find all the arrays of positive integers such that
 * Sum of array = K
 * The product of elements of array is a power of 2 ( i.e. 1, 2, 4, 8, 16, …. )
 * If answer is large, return last 9 digits of your answer.
 * <p>
 * <p>
 * Let’s
 * K = 3
 * [1….k]
 * <p>
 * A1 = [1, 2]
 * A2= [2, 1]
 * A3 = [1,1 , 1]
 * <p>
 * Answer = 3
 * <p>
 * <p>
 * ---------------
 * K = 4
 * A1 = [4]
 * A2 = [2,2]
 * A3 = [1,2,1]
 * A4 = [1,1,2]
 * A5 = [2,1,1]
 * A6 = [1,1,1,1]
 * <p>
 * Answer = 6
 * <p>
 * Extension of {@link Java.LeetCode.combination.sum.CombinationSumIV}
 */
public class CombinationSumProduct {

    public static void main(String[] args) {
        test(5, 10);
        test(3, 3);
        test(4, 6);
        test(10, 174);
    }

    private static void test(int k, int expected) {
        System.out.println("\nK:" + k);
        System.out.println("Expected:           " + expected);
        System.out.println("Backtracking:       " + CombinationSumProductBacktracking.combinationSumProduct(k));
        System.out.println("DP TopD:            " + CombinationSumProductDPTopDown.combinationSumProduct(k));
        System.out.println("DP BottomUp:        " + CombinationSumProductDPBottomUp.combinationSumProduct(k));


    }
}

/**
 * Just like {@link Java.LeetCode.combination.sum.CombinationSumIV} #CombinationSumIVBacktracking
 * we'll keep track of current sum and product and whenever we hit the base, we return.
 * Complexity: O(k*2^k)
 */
class CombinationSumProductBacktracking {
    public static int combinationSumProduct(int k) {

        if (k <= 0)
            return 0;
        return combinationSumProduct(k, 0, 1);
    }

    private static int combinationSumProduct(int k, int currentSum, int currentProduct) {

        if (currentSum == k)
            return 1;


        //we can't include a number which is greater than k or equal to k since sum won't be k anymore
        int res = 0;
        for (int i = 1; i <= k; i *= 2) { //i => i*2 because power of 2 is only possible when i is multiple of 2
            if (currentSum + i <= k) {
                res += combinationSumProduct(k, currentSum + i, currentProduct * i);
            }
        }
        return res;
    }

    private static boolean isPowerOfTwo(int num) {

        return (num == 0 || ((num & (num - 1)) == 0));
    }

}


/**
 * Just like {@link Java.LeetCode.combination.sum.CombinationSumIV} #CombinationSumIVBacktracking we'll keep track of current sum and product and whenever we hit the base, we return.
 * Complexity: O(k*sqrt(k))
 */
class CombinationSumProductDPTopDown {

    public static int combinationSumProduct(int k) {

        if (k <= 0)
            return 0;
        int[] dp = new int[k + 1];
        Arrays.fill(dp, -1);
        return combinationSumProduct(k, 0, 1, dp);
    }

    private static int combinationSumProduct(int k, int currentSum, int currentProduct, int[] dp) {

        if (dp[currentSum] != -1)
            return dp[currentSum];


        if (currentSum == k)
            return dp[currentSum] = 1;

        int res = 0;
        for (int i = 1; i <= k; i *= 2) { // O(sqrt(k))
            if (currentSum + i <= k)
                res += combinationSumProduct(k, currentSum + i, currentProduct * i, dp); //O(k)
        }
        return dp[currentSum] = res;
    }

}

/**
 * {@link Java.LeetCode.combination.sum.CombinationSumIV} #CombinationSumIVDP
 * So we know that target is the sum of numbers in the array. Imagine we only need one more number to reach target, this number can be any one in the array, right?
 * So the # of combinations of target, comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].
 * <p>
 * In the example given, we can actually find the # of combinations of 4 with the # of combinations of 3(4 - 1), 2(4- 2) and 1(4 - 3).
 * As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
 * <p>
 * Now in order to suffice the condition of power of two, we'll choose the numbers which are in power of two hence
 * As a result, comb[8] = comb[8-1] + comb[8-2] + comb[8-4] + comb[8-8]
 * <p>
 * Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, we can set comb[0] = 1.
 * Recurrence relation
 * *     comb[target] = {
 * *         sum(comb[target - nums[i]]) target> 0 and nums[i] is multiple of two
 * *         1: when target=0,
 * *     }
 * Complexity: O(k*sqrt(k))
 */
class CombinationSumProductDPBottomUp {

    public static int combinationSumProduct(int k) {

        if (k <= 0)
            return 0;
        int[] comb = new int[k + 1];
        comb[0] = 1;

        for (int target = 1; target <= k; target++) {
            for (int p = 1; p <= k; p *= 2) {
                if (target >= p)
                    comb[target] += comb[target - p];
            }
        }

        return comb[k];

    }


}