package DataStructureAlgo.Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring/
 * <p>
 * Given a string str, find the longest repeating non-overlapping substring in it. In other words find 2 identical substrings of maximum length which do not overlap. If there exists more than one such substring return any of them.
 * <p>
 * Examples:
 * <p>
 * Input : str = "geeksforgeeks"
 * Output : geeks
 * <p>
 * Input : str = "aab"
 * Output : a
 * <p>
 * Input : str = "aabaabaaba"
 * Output : aaba
 * <p>
 * Input : str = "aaaaaaaaaaa"
 * Output : aaaaa
 * <p>
 * Input : str = "banana"
 * Output : an
 * or na
 */
public class LongestRepeatingNonOverlappingSubString {

    public static void main(String[] args) {
        test("geeksforgeeks");
        test("aabaabaaba");
        test("banana");
        test("aaaaaaaaaaa");
    }

    static void test(String str) {
        System.out.println("Naive: :" + LongestRepeatingNonOverlappingSubStringNaive.longestRepeatingNonOverlappingSubString(str));
        System.out.println("DP: " + LongestRepeatingNonOverlappingSubStringDP.longestRepeatingNonOverlappingSubString(str));
    }
}

/**
 * Naive Solution : The problem can be solved easily by taking all the possible substrings and for all the
 * substrings check it for the remaining(non-overlapping) string if there exists an identical substring. There are O(n^2)
 * total substrings and checking them against the remaining string will take O(n) time.
 * So overall time complexity of above solution is O(n^3).
 */
class LongestRepeatingNonOverlappingSubStringNaive {


    public static String longestRepeatingNonOverlappingSubString(String str) {

        if (null == str || str.isEmpty())
            return str;

        int n = str.length();

        String longest = "";
        //O(n)
        for (int i = 0; i < n; i++) {

            //O(n)
            for (int j = i + 1; j < n; j++) {

                String subString = str.substring(i, j);
                String remaining = str.substring(j);

                //O(n)
                if (remaining.contains(subString))
                    longest = longest.length() > subString.length() ? longest : subString;
            }

        }
        return longest;

    }


}

/**
 * {@link DataStructureAlgo.Java.LeetCode.longestShortestCommon.LongestCommonSubString}
 */
class LongestRepeatingNonOverlappingSubStringDP {

    public static String longestRepeatingNonOverlappingSubString(String str) {
        if (null == str || str.isEmpty())
            return str;

        int n = str.length();

        /**
         * Let dp[i][j] defines the length of longest repeating non-overlapping sub-string ending at i'th with j chars
         */
        int dp[][] = new int[n + 1][n + 1];

        dp[0][0] = 0;

        /**
         * dp[i][j] = { if s[i-1] == s[j-1] & j-i > dp[i-1][j-1] then dp[i-1][j-1] + 1
         *          otherwise 0
         *
         */

        int max = 0;
        int index = 0;
        for (int i = 1; i <= n; i++) {

            for (int j = i + 1; j <= n; j++) {

                char c1 = str.charAt(i - 1);
                char c2 = str.charAt(j - 1);
                if (c1 == c2 && (j - i > dp[i - 1][j - 1])) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        index = Math.max(index, i);
                    }
                } else
                    dp[i][j] = 0;
            }
        }


        return str.substring(index - max, index);
    }
}