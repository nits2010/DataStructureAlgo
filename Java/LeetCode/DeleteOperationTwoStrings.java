package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-22
 * Description:https://leetcode.com/problems/delete-operation-for-two-strings/
 * <p>
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 * <p>
 * Example 1:
 * <p>
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Note:
 * <p>
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 */
public class DeleteOperationTwoStrings {

    public static void main(String args[]) {
        System.out.println(minDistance("sea", "eat"));
    }


    /**
     * if word1[i] == word2[j] , no delete needed , so dp[i][j] = dp[i-1][j-1]
     * if word1[i] != word2[j] , we need to delete a char in word1 or word2, because we need to find the min , so dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + 1
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {

        if (word1 == null || word1.isEmpty())
            return word2.length();
        if (word2 == null || word2.isEmpty())
            return word1.length();
        return minDistanceApproach1(word1, word2);

    }

    public static int minDistanceApproach1(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int lcs[][] = new int[m + 1][n + 1];


        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {


                if (i == 0)
                    lcs[i][j] = j;
                else if (j == 0)
                    lcs[i][j] = i;

                else if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    lcs[i][j] = lcs[i - 1][j - 1];
                else
                    lcs[i][j] = 1 + Math.min(lcs[i - 1][j], lcs[i][j - 1]);


            }
        }


        return lcs[m][n];
    }

    public static int minDistance2(String word1, String word2) {

        if (word1 == null || word1.isEmpty())
            return word2.length();
        if (word2 == null || word2.isEmpty())
            return word1.length();

        int lcs = longestCommonSubSequence(word1, word2);
//        System.out.println(lcs);
        return (word1.length() - lcs) + (word2.length() - lcs);
    }

    /**
     * lcs[i][j] = length of longest common sub-sequence from 0 to i char of S1 and 0 to j char of S2.
     * <p>
     * *     lcs[i][j] = 1+ lcs[i-1][j-1] (S1(i) == S2(j) ) if current is same then optimal solution will be obtained by previous lengths
     * *     lcs[i][j] = max ( lcs[i-1][j] , lcs[i][j-1] ) (S1(i) != S2(j) )
     * <p>
     * *     lcs[i][j] = 0 if i=0 or j=0 since if either of one string is empty then lcs is not possible
     * <p>
     * Since we can observe that any any moment we are only bother about above row8 only;
     * we can translate it to below optimized solution
     *
     * @param word1
     * @param word2
     * @return
     */
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
