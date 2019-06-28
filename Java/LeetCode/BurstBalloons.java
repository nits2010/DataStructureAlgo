package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-28
 * Description:
 * https://leetcode.com/problems/burst-balloons/
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * Video help:https://www.youtube.com/watch?v=IFNibRVgFBo
 */
public class BurstBalloons {

    public static void main(String args[]) {
        int nums[] = {3, 1, 5, 8};
        System.out.println(maxCoinsSimplify(nums));

    }


    /**
     * Lets understand the problem first. Problem clearly stating that we need to maximize the coins
     * 1. First insight-> we find max of all the possible solution
     * <p>
     * In order to maximize we need to find the correct order of the bursting the ballons
     * 2. Second insight -> there should be order to maximize the coins collect
     * <p>
     * After two insight, we if we simply try to bust a ballon and find out what is the max coin we can collect by bursting this bollon and find
     * out the maximum out of all of them we are done.
     * BruteForce:
     * 1. skip the current balloon and burst the next balloon, then find the max coins collected by busting this balloon, to find we need to find what is the max on LEFT side and RIGHT side
     * <p>
     * Max ( max (left side) + max (right side) + a[i-1] * a[i] * a[i+1]
     * <p>
     * we can see we are solving same problem again n again in terms of left and right for each i.
     * So we can apply Dynamic problem here
     * <p>
     * <p>
     * now, how do we apply, to get we need to know both left and right first then only we can find for the ith balloon right.
     * <p>
     * lets consider the bellow example
     * <p>
     * { 3,1,5,8}
     * Lets say what is the coins we collect if we simply burst the current balloon
     * it would be
     * { 3, 15, 40, 40} -> we found bursting the current balloon the cost. [ EQUATION 1 ]
     * <p>
     * now consider instead of 1 balloon only, we have two balloons { 3,1}
     * [ {3, 1}, 5, 8 ]
     * <p>
     * now we need to find which balloon to burst out of 3 and 1 in order to get max coins; we'll try both and find the max out of it [ just like bruteforce]
     * <p>
     * now if we try to burst 3, what is the max on left , max on right and max on bursting it self
     * max on left: 0 [ nothing on left side ]
     * max on right: at right side we have balloon at index 1 (element ->1) if we burst the balloon what we get [ we already computed it above [ EQUATION 1 ] ] = 15
     * bursting it self:
     * 1 [no balloon on left side] * 3 [it self] * 5 [ since 1 is already busted when we finding the max on right side ] = 15
     * So total is 30 ( 0 + 15 + 15 ) for 3 index 0
     * <p>
     * Lets try to burst 1;
     * max on left side: we have only one balloon left side, if we burst it we get 3 [ EQUATION 1 ]
     * max on right side: we don't have on right side, so 0
     * bursting it self:
     * 1 [since 3 already busted for max on left side] * 1 [it self] * 5 [since there is nothing on right side and we are bursting 1] = 8
     * So total is 3 + 0 + 1*1*5 = 8 for  1 index 1
     * <p>
     * Max ( 30,8) = 30 hence bursting balloon 3 would give you max coins.
     * <p>
     * <p>
     * lets drive dp equation for this;
     * <p>
     * dp[i][j] represent max coins on bursting ballons between i and j (inclusive)
     * <p>
     * dp[i][j] = Max ( (bursting on left side + bursting on right side + bursting it self) for all k between i<=k<=j )
     * <p>
     * dp[i][j] = Max ( (dp[i][k-1] + dp[k+1][j]+ (a[i-1] * a[i] * a[j+1])) for all k between i<=k<=j )
     * <p>
     * <p>
     * closely look into the solution, its a matrix chain multiplication derivation.
     * Complexity O(n^3)
     *
     * @param nums
     * @return
     */

    public static int maxCoins(int nums[]) {

        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int n = nums.length;

        int dp[][] = new int[n + 1][n + 1];


        for (int len = 1; len <= n; len++) {

            for (int i = 0; i < n - len + 1; i++) {

                int j = i + len - 1;
                int max = 0;
                for (int k = i; k <= j; k++) {

                    int leftSide = 0, rightSide = 0, byItSelf = nums[k];

                    if (k - 1 >= i)
                        leftSide = dp[i][k - 1];

                    if (k + 1 <= j)
                        rightSide = dp[k + 1][j];

                    if (i != 0) // if they are at boundary
                        byItSelf *= nums[i - 1];

                    if (j != n - 1) // if they are at boundary
                        byItSelf *= nums[j + 1];

                    max = Math.max(max, leftSide + rightSide + byItSelf);

                }
                dp[i][j] = max;
            }
        }


        return dp[0][n - 1];
    }


    public static int maxCoinsSimplify(int nums[]) {

        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int n = nums.length;

        int temp[] = new int[n + 2];

        temp[0] = 1;
        for (int i = 0; i < n; i++)
            temp[i + 1] = nums[i];
        temp[n + 1] = 1;

        int dp[][] = new int[n + 2][n + 2];


        for (int len = 1; len <= n; len++) {

            for (int i = 1; i <= n - len + 1; i++) {

                int j = i + len - 1;
                for (int k = i; k <= j; k++)

                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + temp[i - 1] * temp[k] * temp[j + 1]);


            }
        }


        return dp[1][n];
    }
}
