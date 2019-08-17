package Java.LeetCode.pattern.match;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-08
 * Description: https://leetcode.com/problems/regular-expression-matching/
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * <p>
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * My Explanation: https://leetcode.com/problems/regular-expression-matching/discuss/354099/100-Pure-explanation-and-derivation-to-DP-with-lot-of-example-or-JAVA-or-Beat-96-and-100
 * https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
 * https://youtu.be/l3hda49XcDE
 *
 */
public class RegularExpressionMatch {

    public static void main(String[] args) {
        test("", ".*", true);
        test("aa", "*", false);
        test("aa", "a", false);
        test("aa", "a*", true);
        test("ab", ".*", true);
        test("aab", "c*a*b", true);
        test("mississippi", "mis*is*p*.", false);
    }


    public static void test(String text, String pattern, boolean expected) {
        RegularExpressionMatchDP match = new RegularExpressionMatchDP();
        System.out.println(" Text : " + text + "\nPattern : " + pattern + " \nis Matching: " + match.isMatch(text, pattern) + " Expected :" + expected);
    }
}

/**
 * Runtime: 2 ms, faster than 95.94% of Java online submissions for Regular Expression Matching.
 * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Regular Expression Matching.
 */
class RegularExpressionMatchDP {


    /**
     * Lets first understand how to approach ths and solve this, as this is not regular general question (because of "*" ) we need to try and hit few example to understand what really happen
     * whenever we try to match any two character of pattern and text.
     * <p>
     * Lets take very simple example first;
     * <p>
     * Example 1: text = "" and pattern = "" [ Both are empty or null ].
     * Approach: Its clear that if pattern is null or empty then text has to be either null or empty, then only they match.
     * Hence
     * Deduction 1: if ( p == null || p.isEmpty() ) return (text == null || text.isEmpty() )
     * <p>
     * Now onwards i'll make the example more complex but will do bit by bit so that we can find out the pattern
     * <p>
     * Example  2: text = "a" and pattern ="" [ text is not empty but patter is ]. We hit the above condition and we result to "False"
     * <p>
     * Example 3: text = "a" and pattern = "a" [ both have single character ]
     * Approach: as we can simply see both have single character and they "match" so our result is True
     * Deduction 2: if both character in pattern and text match, then we say they “match Probably” [ I’ll explain probably in  a while ]
     * <p>
     * Example 4: text=”ab” and pattern = “a”
     * Approach: We see at index 0 in both they match, but there still some character left in pattern ( not ‘.’ And ‘*’ ) so they don’t match.
     * Deduction 3: if there is some character remaining in pattern after matching complete text then they don’t match= false ; provided that remaining characters are not ‘.’ And ‘*’
     * <p>
     * I think, now you have pretty good idea about character vs character
     * <p>
     * Lets introduce some ‘.’.
     * <p>
     * Example 5: text=”ab” and pattern = “a.” [ patter has 1 ‘.’ At the last ]
     * Approach: As both first character are match we left with text=”b” and pattern=”.” , Since “.” Says it can match to any character, hence we left with text = “” and pattern=”” and we have the solution for this already. Case 1; Hence True
     * <p>
     * Deduction 4: Both character and “.” Behaves in same way, either both are character in pattern and text and they match or pattern is a “.” Also that match provided that previous text and pattern match.
     * <p>
     * Let make it more complex
     * <p>
     * <p>
     * Example 5: text=”ab” and pattern = “a..” [ patter has 2 ‘.’ At the last ]
     * Approach: We saw ‘a’ and ‘a’ match, we left with “b” and “..”. Now we hit a dot, then we’ll see does “” and “.” Match, which says No as text is empty and pattern is not. Hence False.
     * Deduction 5: Just same as Deduction 4 with minor tweak.
     * <p>
     * <p>
     * Lets introduce the killer of this question “*”
     * <p>
     * Example 6: text=”ab” and pattern = “a*” [ patter has 1 ‘*’ At the last ]
     * Approach: We know that “*” means that Either zero or at least 1 character match with “just previous to *”. [ That’s important point ]
     * Which means that we can assume that there is no character before “*” and there is at least 1 character same as previous of “*”.
     * Hence
     * In our example;
     * “ab” -> “a*” =>  “b” -> “*” =>  Don’t match Hence False
     * <p>
     * <p>
     * Example 7: text=”ab” and pattern = “ab*” [ patter has 1 ‘*’ At the last ]
     * “ab” -> “ab*” => “b” -> “b*”
     * Now we are at the critical point, what happens here?
     * There is two path possible ,
     * Either we say
     * “b” -> “” [ zero occurrence of b ] => False
     * “b” -> “b” [ 1 occurrence of b ] => True
     * “b” -> “bb” [ We don’t need to move ahead as that enough to test, in this case ]
     * <p>
     * Deduction 6: Whenever we hit star “*” we need to look back 1 or 2 character behind.
     * 2 because we are assuming that there no occurrence of previous character (of “*” ) or 1 because there at least 1 occurrence of previous character (of “*”).
     * <p>
     * <p>
     * Deduction: If you observe carefully, each time we have to look back what was the solution if don’t have the current pattern character ( either character or “.” OR ‘*” ].
     * <p>
     * Which tells us to cache the solution Hence DP [ as this tells if you know previous solution you can build this solution and they are overlap too ].
     * <p>
     * Let’s taka a final example to prove our point
     * <p>
     * Example: text = “xaabyc” pattern = “xa*b.c” [ has 1 * and 1 “.” ]
     * Lets match
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * “xaabyc” -> “xa*b.c” => “aabyc” -> “a*b.c”
     * => “abyc” -> “*b.c” OR
     * => “abyc” -> “ab.c”
     * => “byc” -> “b.c” => “yc” -> “.c” => “c” -> “c” => “” -> “” Hence Matched.
     * <p>
     * <p>
     * <p>
     * [Please note, for simplicity I did not wrote all the paths, when ever we hit “*” we have two path.]
     * <p>
     * Now lets come to more difficult pattern; where you have like this “**…*” or “.*” Or “..*” or “.**” … like this
     * We can assume it same as “.*” -> “$*” where $ represent any character, now again your problem reduce to same, isn’t it 
     * <p>
     * Last but not least, there is one special case like
     * Text =”abc” and pattern =”*df”
     * In this case we need to look before what was the solution as there is no character previous to “*”.
     * <p>
     * Let’s write a recurrence relation and base case.
     * <p>
     * Base case:
     * If both string are empty = True
     * If one of them is empty but other is not = False.
     * <p>
     * Let’s Say
     * M[i][j] Defines the our solution dp.
     * ‘true’ when we can match 0 to i character of text are tested against 0 to j character of pattern and they match
     * Otherwise ‘False’
     * <p>
     * Text[i-1] == Pattern[j-1] ; Then we need to look back what was state when current character don’t exist => M[i-1][j-1]
     * <p>
     * Text[i-1] != Pattern[j-1]; Now there could be following reason that they are not equal
     * 1.	The character at both are different like Text[i-1] = ‘A’ and Pattern[j-1] = ‘B’ => False
     * 2.	The character at pattern is ‘.’  => True
     * 3.	The character at pattern is “*” => Look back either 2 character before (assume previous character don’t exist Or 1 character before assume at least 1 occurrence is there of previous character. Now previous character could be “.” Or a character
     * If “.” Then it can match to any one Or if both character match then What was the last state of text matching pattern, which is M[i-1][j]
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * M[i][j]  = {
     * Text[i-1] == Pattern[j-1] => M[i-1][j-1]
     * Text[i-1] != Pattern[j-1] and Pattern[j-1]!=’.’ and Pattern[j-1]!=’* => False
     * Pattern[j-1] == ‘.’ => M[i-1][j-1]
     * Pattern[j-1] == ‘*’ => M[i][j-2] | (if Pattern[j-2] == ‘.’ Or Pattern[j-2] == Text[i-1]) M[i-1][j]
     *
     * 1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
     *                2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
     *                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     *                            or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
     *                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch(String text, String pattern) {

        //You can combine the bases cases in single condition it self, I left it like this so that it match what i have explained above

        /**
         * Base Case:
         *  If both string are empty = True
         */
        if ((text == null && pattern == null) || (text.isEmpty() && pattern.isEmpty()))
            return true;


        if (text == null || pattern == null)
            return false;

        if (!text.isEmpty() && pattern.isEmpty())
            return false;


        int textLength = text.length();
        int patternLength = pattern.length();

        /**
         * For simplicity i took this
         */
        char texts[] = text.toCharArray();
        char patterns[] = pattern.toCharArray();

        //Corner case
        if (patterns[0] == '*')
            return false;

        final boolean M[][] = new boolean[textLength + 1][patternLength + 1];

        /**
         * Empty string matches to empty pattern
         */
        M[0][0] = true;

        /**
         * Last but not least, there is one special case like
         * Text =”abc” and pattern =”*df”
         * In this case we need to loop before what was the solution as there is no character previous to “*”.
         */

        for (int j = 1; j <= patternLength; j++)
            if (patterns[j - 1] == '*')
                M[0][j] = M[0][j - 2];


        for (int i = 1; i <= textLength; i++) {

            for (int j = 1; j <= patternLength; j++) {

                //Text[i-1] == Pattern[j-1]  Or Pattern[j-1] == ‘.’
                if (texts[i - 1] == patterns[j - 1] || patterns[j - 1] == '.')
                    M[i][j] = M[i - 1][j - 1];
                else if (patterns[j - 1] == '*') {

                    //The character at pattern is “*” => Look back either 2 character before (assume previous character don’t exist
                    // Or 1 character before assume at least 1 occurrence is there of previous character

                    M[i][j] = M[i][j - 2]; //As when we assume there is no previous character, then we know what was the solution previous to current pattern character

                    if (patterns[j - 2] == '.' || patterns[j - 2] == texts[i - 1])
                        M[i][j] = M[i][j - 2] | M[i - 1][j];
                } else
                    M[i][j] = false; //texts[i - 1] != patterns[j - 1] && Pattern[j-1]!=’.’ and Pattern[j-1]!=’*
            }
        }

        return M[textLength][patternLength];
    }
}