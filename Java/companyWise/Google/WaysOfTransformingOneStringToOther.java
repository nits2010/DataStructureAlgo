package Java.companyWise.Google;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 09/04/19
 * Description:
 * https://www.geeksforgeeks.org/ways-transforming-one-string-removing-0-characters/
 *
 * Given two sequences A, B, find out number of unique ways in sequence A, to form a subsequence of A that is identical to the
 * sequence B. Transformation is meant by converting string A (by removing 0 or more characters) to string B.
 *
 * Examples:
 *
 * Input : A = "abcccdf", B = "abccdf"
 * Output : 3
 * Explanation : Three ways will be -> "ab.ccdf",
 * "abc.cdf" & "abcc.df" .
 * "." is where character is removed.
 *
 * Input : A = "aabba", B = "ab"
 * Output : 4
 * Expalnation : Four ways will be -> "a.b..",
 *  "a..b.", ".ab.." & ".a.b." .
 * "." is where characters are removed.
 * Asked in : [Google]
 */
public class WaysOfTransformingOneStringToOther {

    public static void main(String []args) {

        String a = "aabba", b = "ab";
        String x = "abcccdf", y = "abccdf";
        String w = "abcccdf", q = "abccdf";

        System.out.println("Number of ways to transform " + a + " to " + b + " is " + waysToTransform(a, b));
        System.out.println("Number of ways to transform " + x + " to " + y + " is " + waysToTransform(x, y));
        System.out.println("Number of ways to transform " + w + " to " + q + " is " + waysToTransform(w, q));
    }

    private static int waysToTransform(String x, String y) {

        int n = x.length();
        int m = y.length();

        //if y is empty then we have to delete all the chars of x, one way
        if (y.isEmpty())
            return 1;

        //If both are same then we can't make any changes
        if (x.equals(y))
            return 0;


        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) //if y is empty string
            dp[i][0] = 1; //empty to empty; since we can remove 0 or more chars, by removing 0 chars there is one way to transform it

        for (int i = 0; i < m; i++) //if x is empty string
            dp[0][i] = 0;

        dp[0][0] = 1; //both empty then remove 0 chars make again empty hence 1 way


        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                //if both are same
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    //then either ignore the current both character (i-1)(j-1) transformations
                    //or ignore the current x character (i-1)(j), since we may delete it from x
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    //if both are not same, then we left with last characters only of x which is (i-1)(j)
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }


        return dp[n][m];
    }
}
