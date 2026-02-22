package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._5;

public class LongestPalindromicSubstring_5 {
    
    

    /**
     * O(n^2)
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {

        if (null == s || s.isEmpty())
            return s;
        int n = s.length();
        int max = 1;
        int start = 0;
        int l, r;

        for (int i = 1; i < n; i++) {

            //Odd length
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {

                if (r - l + 1 > max) {
                    max = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }

            //even length
            l = i - 1;
            r = i;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {

                if (r - l + 1 > max) {
                    max = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }


        }


        return s.substring(start, start + max);
    }
}


