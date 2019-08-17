package Java.LeetCode.longestShortestCommon;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-07
 * Description: https://leetcode.com/problems/shortest-common-supersequence/
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.
 * <p>
 * (A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 * <p>
 * <p>
 * Input:   str1 = "geek",  str2 = "eke"
 * Output: "geeke"
 * <p>
 * Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
 * Output:  "AGXGTXAYB"
 * <p>
 * Note:
 * <p>
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 * <p>
 * <p>
 * https://www.geeksforgeeks.org/shortest-common-supersequence/
 */
public class ShortestCommonSuperSequence {

    public static void main(String[] args) {

        test("abac", "cab");
        test("geek", "eke");
        test("AGGTAB", "GXTXAYB");
    }

    private static void test(String s1, String s2) {
        ShortestCommonSuperSequenceLength.ShortestCommonSuperSequenceUsingLCS length = new ShortestCommonSuperSequenceLength.ShortestCommonSuperSequenceUsingLCS();
        ShortestCommonSuperSequenceLength.ShortestCommonSuperSequenceDP dpLength = new ShortestCommonSuperSequenceLength.ShortestCommonSuperSequenceDP();
        ShortestCommonSuperSequenceString scss = new ShortestCommonSuperSequenceString();

        System.out.println();
        System.out.println("S1 : " + s1 + " S2: " + s2);
        System.out.println("LCS -> SCSS Length :" + length.lengthOfScss(s1, s2));
        System.out.println("DP -> SCSS Length :" + dpLength.lengthOfScss(s1, s2));
        System.out.println(" SCSS  :" + scss.shortestCommonSupersequence(s1, s2));

    }
}

/**
 * Input: str1 = "geek",  str2 = "eke"
 * Output: 5 [ "geeke" ]
 * <p>
 * This is a variance of longest common sub-sequence {@link LongestCommonSubSequence}.
 * <p>
 * <p>
 * 'super-sequence'.
 * -------------------------------------------------
 * Two string gives us a super-sequence which have same character (available in both string) and in the same order. Means we take all character of
 * string A and all character of string B, we check what are the character which are 'matching' as well as 'they have same order' in string.
 * <p>
 * As given in example: A-> "geek" and B->"eke" , here one of the super-sequence is 'ek'.
 * <p>
 * <p>
 * Shortest common 'super-sequence'.
 * -------------------------------------------------
 * Which is common to both and output string is shortest.
 * <p>
 * <p>
 * By saying as definition, we can see that both string will form shortest common super-sequence only when both have some common sequence.
 * <p>
 */
class ShortestCommonSuperSequenceLength {


    /**
     * * WE can find the longest common sub-sequence between them, then the length of shortest common would be reducing this longest common from total length
     * * Let us consider an example, str1 = “AGGTAB” and str2 = “GXTXAYB”. LCS of str1 and str2 is “GTAB”.
     * * The length of LCS = 4 , total length = 6 + 7 = 13 Hence SCSS = 13-4 = 9.
     * * Why?
     * * Once we find LCS, we insert characters of both strings in their order and we get “AGXGTXAYB”
     */
    static class ShortestCommonSuperSequenceUsingLCS {


        public int lengthOfScss(String str1, String str2) {
            /**
             * Any of the empty string with other non-empty string form original non-empty string as SCSS only.
             */
            if (str1 == null || str1.isEmpty())
                return (str2 == null) ? 0 : str2.length();

            if (str2 == null || str2.isEmpty())
                return (str1 == null) ? 0 : str1.length();

            return (str1.length() + str2.length() - longestCommonSubSequence(str1, str2));

        }

        private static int longestCommonSubSequence(String word1, String word2) {

            int m = word1.length();
            int n = word2.length();

            int lcs[][] = new int[2][n + 1];
            int row;
            for (int i = 0; i <= m; i++) {

                row = i & 1;

                for (int j = 0; j <= n; j++) {

                    if (i == 0 || j == 0)
                        lcs[row][j] = 0;

                    else {

                        if (word1.charAt(i - 1) == word2.charAt(j - 1))
                            lcs[row][j] = 1 + lcs[1 - row][j - 1];
                        else
                            lcs[row][j] = Math.max(lcs[1 - row][j], lcs[row][j - 1]);
                    }

                }
            }

            return lcs[m & 1][n];
        }
    }


    /**
     * We can build its own recurrence relation to find its length.
     * Lets say
     * SCSS[i][j] is the length of shortest common super-sequence of first i character of S1 and first j character of S2.
     * <p>
     * SCSS[i][j] = {  1+  SCSS[i-1][j-1]   if S1[i] == S2[j] ; If both match then we can build our SCSS from last SCSS with 1 more character
     * *                1 + Min ( SCSS[i-1][j] , SCSS[i][j-1] )  if S1[i]!=S2[j] ; if they don't match, we have a choice to take either of character,
     * *                                                which ever gives us shortest length of SCSS, new SCSS length would be 1 more character taken from either of them
     * *            }
     */
    static class ShortestCommonSuperSequenceDP {

        public int lengthOfScss(String str1, String str2) {
            /**
             * Any of the empty string with other non-empty string form original non-empty string as SCSS only.
             */
            if (str1 == null || str1.isEmpty())
                return (str2 == null) ? 0 : str2.length();

            if (str2 == null || str2.isEmpty())
                return (str1 == null) ? 0 : str1.length();

            int n = str1.length();
            int m = str2.length();

            int scss[][] = new int[n + 1][m + 1];

            //Added in below loop it self
//            /**
//             * If second string is empty
//             */
//            for (int i = 0; i <= n; i++)
//                scss[i][0] = i;
//
//            /**
//             * If first string is empty
//             */
//            for (int i = 0; i <= m; i++)
//                scss[0][i] = i;

            for (int i = 0; i <= n; i++) {

                for (int j = 0; j <= m; j++) {

                    // If second string is empty
                    if (i == 0)
                        scss[i][j] = j;

                        //If first string is empty
                    else if (j == 0)
                        scss[i][j] = i;

                        //Neither is empty
                    else {

                        if (str1.charAt(i - 1) == str2.charAt(j - 1))
                            scss[i][j] = 1 + scss[i - 1][j - 1];
                        else
                            scss[i][j] = 1 + Math.min(scss[i - 1][j], scss[i][j - 1]);
                    }
                }
            }

            return scss[n][m];
        }

    }


}

/**
 * https://www.geeksforgeeks.org/shortest-possible-combination-two-strings/
 * Back track the above solution to build the string
 * Runtime: 17 ms, faster than 62.20% of Java online submissions for Shortest Common Supersequence .
 * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Shortest Common Supersequence .
 */
class ShortestCommonSuperSequenceString {

    public String shortestCommonSupersequence(String str1, String str2) {
        /**
         * Any of the empty string with other non-empty string form original non-empty string as SCSS only.
         */
        if (str1 == null || str1.isEmpty())
            return str2;

        if (str2 == null || str2.isEmpty())
            return str1;

        int n = str1.length();
        int m = str2.length();

        int scss[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= m; j++) {

                // If second string is empty
                if (i == 0)
                    scss[i][j] = j;

                    //If first string is empty
                else if (j == 0)
                    scss[i][j] = i;

                    //Neither is empty
                else {

                    if (str1.charAt(i - 1) == str2.charAt(j - 1))
                        scss[i][j] = 1 + scss[i - 1][j - 1];
                    else
                        scss[i][j] = 1 + Math.min(scss[i - 1][j], scss[i][j - 1]);
                }
            }
        }


        int maxLength = scss[n][m];
        char result[] = new char[maxLength];

        int k = maxLength - 1;

        int i = n, j = m;

        while (i > 0 && j > 0) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                result[k--] = str1.charAt(i - 1);
                i--;
                j--;
            } else if (scss[i - 1][j] > scss[i][j - 1]) {


                result[k--] = str2.charAt(j - 1);
                j--;
            } else {
                result[k--] = str1.charAt(i - 1);
                i--;
            }

        }
        while (i > 0)
            result[k--] = str1.charAt(--i);

        while (j > 0)
            result[k--] = str2.charAt(--j);


        return new String(result);

    }


}