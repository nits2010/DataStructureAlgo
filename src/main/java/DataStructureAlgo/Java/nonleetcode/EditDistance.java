package DataStructureAlgo.Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-16
 * Description:
 */
public class EditDistance {


    public static void main(String []args) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println(editDistDP(str1, str2, str1.length(), str2.length()));
    }

    static int min(int x, int y, int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    /**
     * ED [i][j] is minimum the cost of converting string "s1" form 0 to i to string "s2" from 0 to j by doing (insert, delete, replace )
     * <p>
     * ED[i][j] = ED[i-1][j-1] if s1(i) == s2(j)
     * *        =
     * *          Min ( Ci + ED[i][j-1],       if s1(i) != s2(j) and we insert (Ci cost to insert)
     * *                Cd + ED[i-1][j],       if s1(i) != s2(j) and we delete (Cd cost to insert)
     * *                Cr + ED[i-1][j-1],       if s1(i) != s2(j) and we replace (Cr cost to insert
     * *            )
     * <p>
     * Base case: if i==0 means first string is empty, then insert all from second string
     * *        then ED[0][j] = j
     * if j == 0 means second string is empty, then delete all chars of first string
     * *        then ED[i][0] = i
     */
    public static int editDistDP(String str1, String str2, int m, int n) {
        // Create a table to store results of subproblems
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to
                // insert all characters of second string
                if (i == 0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                    // If the last character is different, consider all
                    // possibilities and find the minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1],  // Insert
                            dp[i - 1][j],  // Remove
                            dp[i - 1][j - 1]); // Replace
            }
        }

        return dp[m][n];
    }

}
