package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-07
 * Description: https://www.geeksforgeeks.org/minimum-splits-in-a-binary-string-such-that-every-substring-is-a-power-of-4-or-6/
 * Given a string S composed of 0 and 1. Find the minimum splits such that the substring is a binary representation of the power of 4 or 6 with no leading zeros. Print -1 if no such partitioning is possible.
 * <p>
 * Examples:
 * <p>
 * Input: 100110110
 * Output: 3
 * The string can be split into a minimum of
 * three substrings 100(power of 4), 110
 * (power of 6) and 110(power of 6).
 * <p>
 * Input : 00000
 * Output : -1
 * 0 is not a power of  4 or 6.
 */
public class MinimumSplitsBinaryStringSubStringPowerOfN {

    public static void main(String []args) {
        SolutionMinimumSplitsBinaryStringSubStringPowerOfN sol = new SolutionMinimumSplitsBinaryStringSubStringPowerOfN();

        int powerOf[] = {4, 6};
        System.out.println(sol.minCuts("100110110", powerOf));
    }
}

class SolutionMinimumSplitsBinaryStringSubStringPowerOfN {

    /**
     * Lets take an example and understand the approach;
     * Str = 100110110
     * We need to find all the sub-string which who's equivalent value in integer is "x" is power of a number (given in array or single value).
     * <p>
     * Approach:
     * One way could be, obtain all the sub-string from given string and find its power of given number(s) or not. keep increasing the size of sub-string and check
     * and get the minimum out of them
     * Finding all sub-string is O(n^2) and from left and right its in total O(n^3) task and converting them is log(x) task, checking them is power of number(s) is O(log(x)) task => O(n^3 * log(x) )
     * <p>
     * If you observe there are many sub-problems we are solving again n again (can you see?  a sub-string we already solved (sub-part of it)) we can compute them and use them
     * <p>
     * Dynamic Programming:
     * <p>
     * lets dp[i] denotes the minimum cuts required to make sub-string str[i...n-1] as power of given numbers. from right to left
     * <p>
     * lets we find a sub-string str[i,j] is power of number(s), then if we know what is the minimum number of cuts required to make str[j+1, n-1] to make power of numbers
     * then total require cuts for str [i,j] is 1 + cuts require for str [j+1, n-1]
     * <p>
     * hence
     * dp[i] = min ( dp[i], dp[j+1] + 1 ) if dp[j+1]!=-1;
     * if any point we find a sub-string is now power of number(s) then we'll put -1
     *
     * @param input
     * @param powerOf
     * @return
     */
    public int minCuts(String input, int powerOf[]) {

        if (input == null || input.length() == 0)
            return 0;

        int n = input.length();
        int dp[] = new int[n];

        char str[] = input.toCharArray();

        char last = str[n - 1];

        // if last char is 1, then value =1 is always power of any number
        dp[n - 1] = last == '1' ? 1 : -1;


        long value = 0;
        for (int i = n - 2; i >= 0; i--) {

            if (str[i] == '0') {
                dp[i] = -1;
                continue;
            }
            value = 0;

            dp[i] = Integer.MAX_VALUE;

            for (int j = i; j < n; j++) {

                value = (value * 2) + (long) (str[j] - '0');

                if (isPowerOf(value, powerOf)) {

                    //if we reached last, then we make whole string from i to j is one sub-string as power of given number(s)
                    if (j == n - 1) {

                        dp[i] = 1;
                    } else if (dp[j + 1] != -1) {
                        dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    }
                }
            }

            if (dp[i] == Integer.MAX_VALUE)
                dp[i] = -1; //this sub-string is not power of given number(s)


        }
        return dp[0];

    }

    //tells if value is power of any of the given number
    private boolean isPowerOf(long value, int[] powerOf) {

        for (int base : powerOf) {
            if (isPowerOf(value, base))
                return true;
        }
        return false;
    }

    private boolean isPowerOf(long value, int base) {
        while (value > 1) {
            if (value % base != 0)
                return false;
            value /= base;
        }
        return true;
    }
}
