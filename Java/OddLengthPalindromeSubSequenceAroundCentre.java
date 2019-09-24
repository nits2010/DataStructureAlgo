package Java;

import Java.HelpersToPrint.GenericPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/total-number-of-odd-length-palindrome-sub-sequence-around-each-centre/
 * Given a string str, the task is to find the number of odd length palindromic sub-sequences around of str with str[i] as centre i.e. every index will be considered as the centre one by one.
 * <p>
 * Examples:
 * <p>
 * Input: str = “xyzx”
 * Output: 1 2 2 1
 * For index 0: There is only a single sub-sequence possible i.e. “x”
 * For index 1: Two sub-sequences are possible i.e. “y” and “xyx”
 * For index 2: “z” and “xzx”
 * For index 3: “x”
 * <p>
 * Input: str = “aaaa”
 * Output: 1 3 3 1
 * https://leetcode.com/discuss/interview-question/389314/Amazon-or-Total-number-of-odd-length-palindrome-sub-sequence-around-each-centre
 */
public class OddLengthPalindromeSubSequenceAroundCentre {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("xyzx", Arrays.asList(1, 2, 2, 1));
        test &= test("aaaa", Arrays.asList(1, 3, 3, 1));
        test &= test("xyzw", Arrays.asList(1, 1, 1, 1));

        System.out.println("\n Tests : " + (test ? "Passed" : "Failed"));


    }

    private static boolean test(String s, List<Integer> expected) {
        System.out.println("\nS :" + s);

        final List<Integer> recursiveObtained = OddLengthPalindromeSubSequenceAroundCentreRecursive.oddLengthPalindromeCenter(s);
        final List<Integer> dpBottomUpObtained = OddLengthPalindromeSubSequenceAroundCentreDPBottomUp.oddLengthPalindromeCenter(s);
        final List<Integer> dpTopDownObtained = OddLengthPalindromeSubSequenceAroundCentreDPTopDown.oddLengthPalindromeCenter(s);

        System.out.println("Expected                :" + expected);
        System.out.println("recursiveObtained       :" + recursiveObtained);
        System.out.println("dpBottomUpObtained      :" + dpBottomUpObtained);
        System.out.println("dpTopDownObtained       :" + dpTopDownObtained);

        return GenericPrinter.equalsValues(dpBottomUpObtained, expected, recursiveObtained, dpTopDownObtained);
    }


}


/**
 * To  count how many palindrome around each character. We need to consider each character as center.
 * For example: "xyx" here if 'y' is center then we have two palindrome as "y" and "xyx"
 * For example: "zxyxz" if 'y' is center then we have three palindrome as "y", "xyx", "zxyxz"
 * <p>
 * Which means for every center we need to look left side and right side of it and find how many more character can participate to make a palindrome essentially equal or bigger then previous one.
 * <p>
 * Since every character can form at least a single palindrome of length 1.
 * We need to look for palindrome for length > 1.
 * <p>
 * Now its clear that we need to look both left and right side of current center in order to find the number of palindrome, that makes it recursive as we need to do this for every center
 * <p>
 * To form the recurrence releation, lets take below example
 * ...x....wyp..x
 * if 'y' is center then on its left we have ...x....w and right we have p..x. Now clearly 'xyx' form a palindrome, which means we need to test every sub-sequence in order to find does they form a
 * a palindrome.
 * <p>
 * palindromCount(i,j) represent the count of palindrome where x being center [ixj]
 * <p>
 * palindromCount(i,j) = palindromCount(i-1, j) + palindromCount(i, j+1) if str[i] ==str[j]
 * *                    =  palindromCount(i-1, j) + palindromCount(i, j+1)- palindromCount(i-1, j+1) if str[i] !=str[j] {-palindromCount(i-1, j+1) because we must have count it through one of the rec. and its not forming a palindrome }
 * Base
 * i == 0 and j==n-1 then some x being center then it form a palindrome, total palindrome would be str[x] and str[i]str[x]str[j]
 * return 2
 * else
 * return 1 because only str[x] form a palindrome
 * <p>
 * palindromCount(i-1, j) means look for palindrome count left side of x
 * palindromCount(i, j+1) means look for palindrome count right side of x
 * palindromCount(i-1, j+1) means look for palindrome count both side of x
 * <p>
 * O(n*2^n)
 */
class OddLengthPalindromeSubSequenceAroundCentreRecursive {
    public static List<Integer> oddLengthPalindromeCenter(String str) {
        List<Integer> ways = new ArrayList<>();
        if (str == null || str.isEmpty())
            return ways;

        int n = str.length();

        for (int i = 0; i < n; i++) {
            ways.add(0);
            if (i == 0 || i == n - 1)
                ways.set(i, 1);
            else {
                int count = oddLengthPalindromeCenterCount(str.toCharArray(), i - 1, i + 1);
                ways.set(i, count);
            }
        }
        return ways;
    }

    private static int oddLengthPalindromeCenterCount(char[] str, int i, int j) {

        if (i < 0 || j >= str.length)
            return 1;

        if (i == 0 && j == str.length - 1) {
            if (str[i] == str[j])
                return 2;
            else
                return 1;
        }

        if (str[i] == str[j])
            return oddLengthPalindromeCenterCount(str, i - 1, j) + oddLengthPalindromeCenterCount(str, i, j + 1);
        return oddLengthPalindromeCenterCount(str, i - 1, j) + oddLengthPalindromeCenterCount(str, i, j + 1) - oddLengthPalindromeCenterCount(str, i - 1, j + 1);

    }
}


/**
 * Many sub-problems cache them
 * O(n*n) = O(n^2)
 */
class OddLengthPalindromeSubSequenceAroundCentreDPTopDown {
    public static List<Integer> oddLengthPalindromeCenter(String str) {
        List<Integer> ways = new ArrayList<>();
        if (str == null || str.isEmpty())
            return ways;

        int n = str.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) { //O(n)
            ways.add(0);
            if (i == 0 || i == n - 1)
                ways.set(i, 1);
            else {
                int count = oddLengthPalindromeCenterCount(str.toCharArray(), i - 1, i + 1, dp); //O(n)
                ways.set(i, count);
            }
        }
        return ways;
    }

    private static int oddLengthPalindromeCenterCount(char[] str, int i, int j, int[][] dp) {

        if (i < 0 || j >= str.length)
            return 1;

        if (i == 0 && j == str.length - 1) {
            if (str[i] == str[j])
                return dp[i][j] = 2;
            else
                return dp[i][j] = 1;
        }
        if (dp[i][j] != 0)
            return dp[i][j];


        if (str[i] == str[j])
            return dp[i][j] = oddLengthPalindromeCenterCount(str, i - 1, j, dp) + oddLengthPalindromeCenterCount(str, i, j + 1, dp);
        return dp[i][j] = oddLengthPalindromeCenterCount(str, i - 1, j, dp) + oddLengthPalindromeCenterCount(str, i, j + 1, dp) - oddLengthPalindromeCenterCount(str, i - 1, j + 1, dp);

    }
}

/**
 * <p>
 * <p>
 * dp[i][j] defines the count of odd length palindrome from (0..i-1) and (j..n-1) assuming i as center
 * <p>
 * dp[i][j] = {
 * *                dp[i-1][j] + dp[i][j+1]  if s(i)==s(j)
 * *                dp[i-1][j] + dp[i][j+1] - dp[i-1][j+1] if s(i)!=s(j)
 * *                 i == 0 and j == n-1 then dp[i][j] = 2 if s(i) == s(j) otherwise 0
 * *
 * }
 */


class OddLengthPalindromeSubSequenceAroundCentreDPBottomUp {

    public static List<Integer> oddLengthPalindromeCenter(String str) {
        List<Integer> ways = new ArrayList<>();
        if (str == null || str.isEmpty())
            return ways;

        int n = str.length();
        int[][] dp = new int[n][n];

        for (int length = n - 1; length >= 0; length--) {

            for (int i = 0; i < n - length; i++) {

                int j = i + length; //length apart i and j

                char c1 = str.charAt(i);
                char c2 = str.charAt(j);

                if (i == 0 && j == n - 1) {
                    if (c1 == c2)
                        dp[i][j] = 2;
                    else
                        dp[i][j] = 1;

                    continue;
                }


                if (c1 == c2) {

                    if (i - 1 >= 0)
                        dp[i][j] = dp[i - 1][j];

                    if (j + 1 < n)
                        dp[i][j] += dp[i][j + 1];

                    if (i - 1 < 0 || j + 1 >= n) //at boundary
                        dp[i][j] += 1;

                } else {
                    if (i - 1 >= 0)
                        dp[i][j] = dp[i - 1][j];
                    if (j + 1 < n)
                        dp[i][j] += dp[i][j + 1];

                    if (i - 1 >= 0 && j + 1 < n)
                        dp[i][j] -= dp[i - 1][j + 1];
                }
            }
        }


        for (int i = 0; i < n; i++) {

            if (i == 0 || i == n - 1)
                ways.add(1);
            else
                ways.add(dp[i - 1][i + 1]); //as i is center
        }

        return ways;

    }
}