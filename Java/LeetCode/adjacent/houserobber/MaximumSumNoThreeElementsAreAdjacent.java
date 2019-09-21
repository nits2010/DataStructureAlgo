package Java.LeetCode.adjacent.houserobber;

import Java.HelpersToPrint.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-27
 * Description: https://www.geeksforgeeks.org/maximum-subsequence-sum-such-that-no-three-are-consecutive/
 * Given a sequence of positive numbers, find the maximum sum that can be formed which has no three consecutive elements present.
 * <p>
 * Examples :
 * <p>
 * Input: arr[] = {1, 2, 3}
 * Output: 5
 * We can't take three of them, so answer is
 * 2 + 3 = 5
 * <p>
 * Input: arr[] = {3000, 2000, 1000, 3, 10}
 * Output: 5013
 * 3000 + 2000 + 3 + 10 = 5013
 * <p>
 * Input: arr[] = {100, 1000, 100, 1000, 1}
 * Output: 2101
 * 100 + 1000 + 1000 + 1 = 2101
 * <p>
 * Input: arr[] = {1, 1, 1, 1, 1}
 * Output: 4
 * <p>
 * Input: arr[] = {1, 2, 3, 4, 5, 6, 7, 8}
 * Output: 27
 * <p>
 * Extension of {@link MaximumSumNoTwoElementsAreAdjacent}
 */
public class MaximumSumNoThreeElementsAreAdjacent {

    public static void main(String[] args) {
        test(new int[]{1, 2}, 3);
        test(new int[]{1, 2, 3}, 5);
        test(new int[]{3000, 2000, 1000, 3, 10}, 5013);
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 27);

        test(new int[]{5, 5, 10, 100, 10, 5}, 120);
        test(new int[]{1, 2, 3}, 5);
        test(new int[]{1, 20, 3}, 23);
        test(new int[]{1, 20}, 21);
        test(new int[]{20}, 20);

    }

    private static void test(int[] nums, int expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums) + " Expected :" + expected);

        MaximumSumNoThreeElementsAreAdjacentRecursive recursive = new MaximumSumNoThreeElementsAreAdjacentRecursive();
        MaximumSumNoThreeElementsAreAdjacentDPTopDown dpTopDown = new MaximumSumNoThreeElementsAreAdjacentDPTopDown();
        MaximumSumNoThreeElementsAreAdjacentBottomUp bottomUp = new MaximumSumNoThreeElementsAreAdjacentBottomUp();
//        MaximumSumNoThreeElementsAreAdjacentLinear linear = new MaximumSumNoThreeElementsAreAdjacentLinear();

        System.out.println("recursive -> " + recursive.maximumSumNoThreeElementAreAdj(nums));
        System.out.println("dpTopDown -> " + dpTopDown.maximumSumNoThreeElementAreAdj(nums));
        System.out.println("bottomUp -> " + bottomUp.maximumSumNoThreeElementAreAdj(nums));
//        System.out.println("linear -> " + linear.maximumSumNoThreeElementAreAdj(nums));
    }
}



/*
 Thought Process
 -----------------

 Lets examine how we approach this problem from base.

 As question stated that
 1. We need to Maximize the sum
 2. We should not choose no three consecutive elements present

 That leads us that if we choose the 'i'th element then we can't choose 'i-2' and 'i-1' and  or 'i+1', 'i+2,
 element from the array.
 Since we need to maximize the sum then we need to smartly choose 'i'th element.
 Lets take an example

 Input: [2] ; n=1
 Here we can choose only 2; Hence output 2

 Input: [2,3]; n=2
 Here we can choose  2 or 3 since 3>2; Hence output 3 {we can't choose 2 now}

 Input: [2,4,3]; n=3 Output is 7 {4,3}

 input [2,4,3,9]
 case 1: choose 2
 2[9] => 11

  case 2: choose 4
 4[] => 4

 case 3: choose 3
 3[] => 3

 case 4: choose 9
 9[2] => 11


 Which means, we need to see choosing current element gives us maximum sum or choosing next/previous element gives maximum sum

 */


/**
 * Extending above process, we can solve this recursively by choosing 'i' the element as see does it gives us best solution
 * otherwise try i+1
 * <p>
 * we do iteratively, so we don't need to look back
 * <p>
 * * Base case:
 * * n=1, then we have to choose this element only, hence it will give us best solution nums[0]
 * * <p>
 * * if n=2, then we choose the maximum of them; hence Max(nums[0], nums[1])
 * * <p>
 * * otherwise we try both
 * * <p>
 * * max = Max ( rec(nums, i+1), nums[i] + rec(nums, i+2), nums[i] + nums[i+1] + rec(nums, i+3) )
 * * <p>
 * <p>
 * 1. don't choose 'i'th element -> rec(nums, i+1)
 * 2.choose 'i'th element ; then we have two choices
 * 2.1 exclude i+1 then we can choose i+2  ->nums[i] + rec(nums, i+2)
 * 2.2 exclude i+2 then we choose i+1 and i+3 ->  nums[i] + nums[i+1] + rec(nums, i+3)
 * <p>
 *
 * <p>
 * Time Complexity: As it will form a ternary tree of max height 'n' {we have n choices} Hence O(n^3)
 */
class MaximumSumNoThreeElementsAreAdjacentRecursive {

    public int maximumSumNoThreeElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        return maximumSumNoThreeElementAreAdj(nums, 0);
    }

    private int maximumSumNoThreeElementAreAdj(int[] nums, int i) {


        if (i >= nums.length)
            return Integer.MIN_VALUE;


        //Single element
        if (i == nums.length - 1)
            return nums[i];

        //two elements
        if (i == nums.length - 2)
            return nums[i] + nums[i + 1];

        //three elements
        if (i == nums.length - 3)
            return Math.max(nums[i] + nums[i + 1], Math.max(nums[i + 1] + nums[i + 2], nums[i] + nums[i + 2]));

        // rec(nums, i+1)
        int dontChoose = maximumSumNoThreeElementAreAdj(nums, i + 1);

        // nums[i] + rec(nums, i+2)
        int chooseExcludeNext = nums[i] + maximumSumNoThreeElementAreAdj(nums, i + 2);

        // nums[i] + nums[i+1] + rec(nums, i+3)
        int chooseExcludeNextToNext = nums[i] + nums[i + 1] + maximumSumNoThreeElementAreAdj(nums, i + 3);

        int choose = Math.max(chooseExcludeNext, chooseExcludeNextToNext);

        return Math.max(dontChoose, choose);


    }


}

/**
 * Repetitive Sub-Problems: cache it
 * O(n)/O(n)
 */
class MaximumSumNoThreeElementsAreAdjacentDPTopDown {

    public int maximumSumNoThreeElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        int memo[] = new int[nums.length];
        Arrays.fill(memo, -1);
        return maximumSumNoThreeElementAreAdj(nums, 0, memo);
    }

    private int maximumSumNoThreeElementAreAdj(int[] nums, int i, int memo[]) {


        if (i >= nums.length)
            return Integer.MIN_VALUE;

        /**
         * If problem already solved, return it
         */
        if (memo[i] != -1)
            return memo[i];

        //Single element
        if (i == nums.length - 1)
            return nums[i];

        //two elements
        if (i == nums.length - 2)
            return nums[i] + nums[i + 1];

        //three elements
        if (i == nums.length - 3)
            return Math.max(nums[i] + nums[i + 1], Math.max(nums[i + 1] + nums[i + 2], nums[i] + nums[i + 2]));

        // rec(nums, i+1)
        int dontChoose = maximumSumNoThreeElementAreAdj(nums, i + 1, memo);

        // nums[i] + rec(nums, i+2)
        int chooseExcludeNext = nums[i] + maximumSumNoThreeElementAreAdj(nums, i + 2, memo);

        // nums[i] + nums[i+1] + rec(nums, i+3)
        int chooseExcludeNextToNext = nums[i] + nums[i + 1] + maximumSumNoThreeElementAreAdj(nums, i + 3, memo);

        int choose = Math.max(chooseExcludeNext, chooseExcludeNextToNext);

        return Math.max(dontChoose, choose);


    }


}





/* Translated to bottom up dp like this
 * dp[i] = Max ( dp[i-1], nums[i] + dp[i-2], nums[i] + nums[i-1] + dp[i-3] )
 * <p>
 * base case:
 * n = 1; then nums[0]
 * n= 2; then nums[0] + nums[1]
 * n = 3; then Math.max(nums[0] + nums[1] , nums[1] + nums[2], nums[0] + nums[2] )
 *
 * <p>

 * O(n)/O(n)
 *
 */

class MaximumSumNoThreeElementsAreAdjacentBottomUp {

    public int maximumSumNoThreeElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int dp[] = new int[n];


        //Single element
        if (n >= 1)
            dp[0] = nums[0];

        //two element
        if (n >= 2)
            dp[1] = nums[0] + nums[1];

        //three element: [2,4,3] -> [2,4] / [4,3] / [2,3]
        if (n >= 3)
            dp[2] = Math.max(dp[1], Math.max(nums[1] + nums[2], nums[0] + nums[2]));


        for (int i = 3; i < n; i++) {

            dp[i] = Math.max(
                    dp[i - 1], //don't choose 'i'th element -> rec(nums, i+1)
                    Math.max(
                            nums[i] + dp[i - 2],//exclude i+1 then we can choose i+2  ->nums[i] + rec(nums, i+2)
                            nums[i] + nums[i - 1] + dp[i - 3] //exclude i+2 then we choose i+1 and i+3 ->  nums[i] + nums[i+1] + rec(nums, i+3)
                    )
            );
        }

        return dp[n - 1];

    }


}

//
//class MaximumSumNoThreeElementsAreAdjacentLinear {
//
//    public int maximumSumNoThreeElementAreAdj(int nums[]) {
//
//        if (nums == null || nums.length == 0)
//            return 0;
//
//        int n = nums.length;
//        int dp[] = new int[n];
//
//
//        //rec(nums, i+1)
//        int excluding = 0;
//
//        //Max(includingExcludingNext,includingExcludingNextNextNext)
//        int including = 0;
//
//        //nums[i] + rec(nums, i+2)
//        int includingExcludingNext = nums[0];
//
//        // nums[i] + nums[i+1] + rec(nums, i+3)
//        int includingExcludingNextNextNext = nums[0];
//
//
//        if (n >= 1)
//            including = nums[0];
//
//        if (n >= 2)
//            including = nums[0] + nums[1];
//
//        if (n >= 3) {
//            excluding = nums[1] + nums[2];//nums[0];
//            includingExcludingNext = nums[0] + nums[2];
//            includingExcludingNextNextNext = nums[0] + nums[1];
//            including = Math.max(includingExcludingNext, includingExcludingNextNextNext);
//
//        }
//
//
//        int max;
//
//        //max = Max ( rec(nums, i+1), nums[i] + rec(nums, i+2), nums[i] + nums[i+1] + rec(nums, i+3) )
//        for (int i = 3; i < n; i++) {
//
//
//           max = Math.max(including, excluding);
//
//           includingExcludingNext = includingExcludingNextNextNext + nums[i];
//           includingExcludingNextNextNext =
//
//
//           excluding = max;
//
//        }
//
//
//        return Math.max(including, excluding);
//
//    }
//
//
//}