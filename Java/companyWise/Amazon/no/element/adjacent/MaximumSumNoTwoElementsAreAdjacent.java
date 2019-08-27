package Java.companyWise.Amazon.no.element.adjacent;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-27
 * Description: https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * Maximum sum such that no two elements are adjacent
 * <p>
 * Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2 numbers in the
 * sequence should be adjacent in the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or
 * 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).Answer the question in most efficient way.
 * <p>
 * Examples :
 * <p>
 * Input : arr[] = {5, 5, 10, 100, 10, 5}
 * Output : 110
 * <p>
 * Input : arr[] = {1, 2, 3}
 * Output : 4
 * <p>
 * Input : arr[] = {1, 20, 3}
 * Output : 20
 */
public class MaximumSumNoTwoElementsAreAdjacent {

    public static void main(String[] args) {
        test(new int[]{5, 5, 10, 100, 10, 5}, 110);
        test(new int[]{1, 2, 3}, 4);
        test(new int[]{1, 20, 3}, 20);
        test(new int[]{1, 20}, 20);
        test(new int[]{20}, 20);

    }

    private static void test(int[] nums, int expected) {
        System.out.println("\n Input :" + Printer.toString(nums) + " Expected :" + expected);

        MaximumSumNoTwoElementsAreAdjacentRecursive recursive = new MaximumSumNoTwoElementsAreAdjacentRecursive();
        MaximumSumNoTwoElementsAreAdjacentDPTopDown dpTopDown = new MaximumSumNoTwoElementsAreAdjacentDPTopDown();
        MaximumSumNoTwoElementsAreAdjacentDPBottomUp bottomUp = new MaximumSumNoTwoElementsAreAdjacentDPBottomUp();
        MaximumSumNoTwoElementsAreAdjacentLinear linear = new MaximumSumNoTwoElementsAreAdjacentLinear();

        System.out.println("recursive -> " + recursive.maximumSumNoTwoElementAreAdj(nums));
        System.out.println("dpTopDown -> " + dpTopDown.maximumSumNoTwoElementAreAdj(nums));
        System.out.println("bottomUp -> " + bottomUp.maximumSumNoTwoElementAreAdj(nums));
        System.out.println("linear -> " + linear.maximumSumNoTwoElementAreAdj(nums));
    }
}




/*
 Thought Process
 -----------------

 Lets examine how we approach this problem from base.

 As question stated that
 1. We need to Maximize the sum
 2. We should not choose elements which are adj

 That leads us that if we choose the 'i'th element then we can't choose 'i-1' and 'i+1' element from the array.
 Since we need to maximize the sum then we need to smartly choose 'i'th element.
 Lets take an example

 Input: [2] ; n=1
 Here we can choose only 2; Hence output 2

 Input: [2,3]; n=2
 Here we can choose  2 or 3 since 3>2; Hence output 3 {we can't choose 2 now}

 Input: [2,4,3]; n=3 Output is 5

 Case 1: if we choose 2 then
 sum = 2 , left with [3] -> Hence output is 5

 Case 2: if we choose 4 then
 sum = 4 , left with [] -> hence output is 4

 case 3: if we choose 3 then
 sum = 3; left with [2] -> output is 5

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
 * * max = Max ( nums[i] + rec(nums, i+2) , rec(nums, i+1) )
 * * <p>
 * * nums[i] + rec(nums, i+2) -> choose 'i' element
 * * rec(nums, i+1) -> don't choose i'th element
 * <p>
 * Time Complexity: As it will form a binary tree of max height 'n' {we have n choices} Hence O(n^2)
 */
class MaximumSumNoTwoElementsAreAdjacentRecursive {

    public int maximumSumNoTwoElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;


        return maximumSumNoTwoElementAreAdj(nums, 0);
    }

    /**
     * @param nums
     * @param i
     */
    private int maximumSumNoTwoElementAreAdj(int[] nums, int i) {

        /**
         * If no more element, then output would be MIN_VALUE
         */
        if (i >= nums.length)
            return Integer.MIN_VALUE;

        //Single element
        if (i == nums.length - 1)
            return nums[i];

        //two element
        if (i == nums.length - 2)
            return Math.max(nums[i], nums[i + 1]);

        int dontChoose = maximumSumNoTwoElementAreAdj(nums, i + 1);
        int choose = nums[i] + maximumSumNoTwoElementAreAdj(nums, i + 2);

        return Math.max(dontChoose, choose);


    }
}

/**
 * * Base case:
 * * n=1, then we have to choose this element only, hence it will give us best solution nums[0]
 * * <p>
 * * if n=2, then we choose the maximum of them; hence Max(nums[0], nums[1])
 * * <p>
 * * otherwise we try both
 * * <p>
 * * max = Max ( nums[i] + rec(nums, i+2) , rec(nums, i+1) )
 * * <p>
 * * nums[i] + rec(nums, i+2) -> choose 'i' element
 * * rec(nums, i+1) -> don't choose i'th element
 * <p>
 * We can see that there are many overlapping sub-problems. We can cache them to reduce the time complexity
 * Because of cache, we don't evaluate same problem again hence
 * Time complexity: O(n) / O(n)
 */
class MaximumSumNoTwoElementsAreAdjacentDPTopDown {

    public int maximumSumNoTwoElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int memo[] = new int[nums.length];
        Arrays.fill(memo, -1);
        return maximumSumNoTwoElementAreAdj(nums, 0, memo);
    }

    /**
     * @param nums
     * @param i
     */
    private int maximumSumNoTwoElementAreAdj(int[] nums, int i, int memo[]) {

        /**
         * If no more element, then output would be MIN_VALUE
         */
        if (i >= nums.length)
            return Integer.MIN_VALUE;

        /**
         * If we have solved this problem, then return it
         */
        if (memo[i] != -1)
            return memo[i];

        //Single element
        if (i == nums.length - 1)
            return nums[i];

        //two element
        if (i == nums.length - 2)
            return Math.max(nums[i], nums[i + 1]);

        int dontChoose = maximumSumNoTwoElementAreAdj(nums, i + 1, memo);
        int choose = nums[i] + maximumSumNoTwoElementAreAdj(nums, i + 2, memo);

        return memo[i] = Math.max(dontChoose, choose); //choose max and cache it


    }
}

/**
 * We can solve this using bottom up ;
 * * Base case:
 * * n=1, then we have to choose this element only, hence it will give us best solution nums[0]
 * * <p>
 * * if n=2, then we choose the maximum of them; hence Max(nums[0], nums[1])
 * * <p>
 * * otherwise we try both
 * * <p>
 * * dp[i] = Max ( dp[i-2] + nums[i] , dp[i-1] )
 * * <p>
 * * dp[i-2] + nums[i] -> choose 'i' element
 * * dp[i-1] -> don't choose i'th element
 * <p>
 * Complexity : O(n)/ O(n)
 */
class MaximumSumNoTwoElementsAreAdjacentDPBottomUp {

    public int maximumSumNoTwoElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int n = nums.length;
        int memo[] = new int[n];
        Arrays.fill(memo, -1);

        //Base case:
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {

            memo[i] = Math.max(memo[i - 1], nums[i] + memo[i - 2]);
        }

        return memo[n - 1];
    }
}


/**
 * Problem exhibits the two things
 * 1. Include this element
 * 2. don't include this element
 * <p>
 * we can do this without even storing the values.
 * Notice the recurrence relation
 * <p>
 * dp[i] = Max ( dp[i-2] + nums[i] , dp[i-1] )
 * <p>
 * At any moment 'i' we are just touching 'i-2' and 'i-1' only. Which is nothing but 2 previous values. All the previous (i-3....0} are not been touch.
 * Hence instead of storing all values we can just store this two values and apply the same logic.
 * <p>
 * 'including' -> dp[i-2]
 * 'excluding' -> dp[i-1]
 * <p>
 * to calculate for next 'i'
 * Max sum at any moment is max = Max(including, excluding)
 * <p>
 * if we include
 * -> then we need to discard the last element and take the current element 'nums[i]' + previous to previous, which is nothing but excluding the last element hence 'excluding'
 * => including = excluding + nums[i]
 * <p>
 * if we exclude
 * -> then we need to take the maximum of previous choice {like in dp, it was dp[i-1]} which is nothing but Max(previous choices) -> Max(old_including, excluding)
 * excluding = max
 * <p>
 * Sample Run
 * arr[] = {5,  5, 10, 40, 50, 35}
 * <p>
 * incl = 5
 * excl = 0
 * <p>
 * For i = 1 (current element is 5)
 * incl =  (excl + arr[i])  = 5
 * excl =  max(5, 0) = 5
 * <p>
 * For i = 2 (current element is 10)
 * incl =  (excl + arr[i]) = 15
 * excl =  max(5, 5) = 5
 * <p>
 * For i = 3 (current element is 40)
 * incl = (excl + arr[i]) = 45
 * excl = max(5, 15) = 15
 * <p>
 * For i = 4 (current element is 50)
 * incl = (excl + arr[i]) = 65
 * excl =  max(45, 15) = 45
 * <p>
 * For i = 5 (current element is 35)
 * incl =  (excl + arr[i]) = 80
 * excl =  max(65, 45) = 65
 * <p>
 * And 35 is the last element. So, answer is max(incl, excl) =  80
 */
class MaximumSumNoTwoElementsAreAdjacentLinear {


    public int maximumSumNoTwoElementAreAdj(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        /**
         * including holds values of i-2
         */
        int including = 0;

        /**
         * including holds values of i-1
         */
        int excluding = 0;

        //Base case: n=1; then we'll include only
        including = nums[0];


        int max;

        for (int i = 1; i < nums.length; i++) {

            max = Math.max(including, excluding);

            including = excluding + nums[i]; //dp[i-2] + nums[i]
            excluding = max; //dp[i-1]

        }


        return Math.max(including, excluding);
    }
}