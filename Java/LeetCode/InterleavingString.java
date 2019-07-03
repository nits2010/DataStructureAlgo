package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-02
 * Description: https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingString {

    public static void main(String args[]) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        Solution solution = new Solution();
        System.out.println(solution.isInterleave(s1, s2, s3));

        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbbaccc";
        System.out.println(solution.isInterleave(s1, s2, s3));

        s1 = "a";
        s2 = "b";
        s3 = "a";
        System.out.println(solution.isInterleave(s1, s2, s3));


    }
}

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave1D(s1, s2, s3);
    }

    public boolean isInterleave1D(String s1, String s2, String s3) {
        if (s1 == null && s2 == null)
            return s3 == null;

        if (s1 == null && s2 != null)
            return s2.equals(s3);

        if (s1 != null && s2 == null)
            return s1.equals(s3);

        int m = s1.length(), n = s2.length();

        if (m + n != s3.length())
            return false;

        boolean interleaved[] = new boolean[n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                //if both string are empty, then they are interleaved
                if (i == 0 && j == 0)
                    interleaved[j] = true;

                    //if second string is empty, then every char of s2 should match to s3
                else if (j == 0)
                    interleaved[j] = interleaved[j] & s1.charAt(i - 1) == s3.charAt(i - 1);

                    //if first string is empty, then every char of s1 should match to s3
                else if (i == 0)
                    interleaved[j] = interleaved[j - 1] & s2.charAt(j - 1) == s3.charAt(j - 1);

                else
                    interleaved[j] = interleaved[j] & s1.charAt(i - 1) == s3.charAt(i + j - 1) |
                            interleaved[j - 1] & s2.charAt(j - 1) == s3.charAt(i + j - 1);

            }
        }
        return interleaved[n];
    }


    public boolean isInterleave2D(String s1, String s2, String s3) {
        if (s1 == null && s2 == null)
            return s3 == null;

        if (s1 == null && s2 != null)
            return s2.equals(s3);

        if (s1 != null && s2 == null)
            return s1.equals(s3);

        int m = s1.length(), n = s2.length();

        if (m + n != s3.length())
            return false;

        boolean interleaved[][] = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                //if both string are empty, then they are interleaved
                if (i == 0 && j == 0)
                    interleaved[i][j] = true;


                    //if second string is empty, then every char of s2 should match to s3
                else if (j == 0)
                    interleaved[i][j] = interleaved[i - 1][j] & s1.charAt(i - 1) == s3.charAt(i - 1);

                    //if first string is empty, then every char of s1 should match to s3
                else if (i == 0)
                    interleaved[i][j] = interleaved[i][j - 1] & s2.charAt(j - 1) == s3.charAt(j - 1);

                else
                    interleaved[i][j] = interleaved[i - 1][j] & s1.charAt(i - 1) == s3.charAt(i + j - 1) |
                            interleaved[i][j - 1] & s2.charAt(j - 1) == s3.charAt(i + j - 1);


            }
        }


        return interleaved[m][n];
    }

    public boolean isInterleaveExplained(String s1, String s2, String s3) {
        if (s1 == null && s2 == null)
            return s3 == null;

        if (s1 == null && s2 != null)
            return s2.equals(s3);

        if (s1 != null && s2 == null)
            return s1.equals(s3);

        int m = s1.length(), n = s2.length();

        if (m + n != s3.length())
            return false;

        boolean interleaved[][] = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                //if both string are empty, then they are interleaved
                if (i == 0 && j == 0)
                    interleaved[i][j] = true;


                    //if first string is empty, then every char of s2 should match to s3
                else if (i != 0 && j == 0 && s1.charAt(i - 1) == s3.charAt(i - 1))
                    interleaved[i][j] = interleaved[i - 1][j];

                    //if second string is empty, then every char of s1 should match to s3
                else if (j != 0 && i == 0 && s2.charAt(j - 1) == s3.charAt(j - 1))
                    interleaved[i][j] = interleaved[i][j - 1];

                else if (i != 0 && j != 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) != s3.charAt(i + j - 1))
                    interleaved[i][j] = interleaved[i - 1][j];

                else if (i != 0 && j != 0 && s1.charAt(i - 1) != s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                    interleaved[i][j] = interleaved[i][j - 1];

                else if (i != 0 && j != 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                    interleaved[i][j] = interleaved[i - 1][j] | interleaved[i][j - 1];


            }
        }


        return interleaved[m][n];
    }

}