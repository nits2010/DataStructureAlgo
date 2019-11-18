package Java.LeetCode.longestShortestCommon;

import java.util.Arrays;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * http://leetcode.liangjiateng.cn/leetcode/longest-substring-with-at-most-k-distinct-characters/description
 * 340.Longest Substring with At Most K Distinct Characters
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 * <p>
 * This is same question {@link Java.companyWise.Google.LongestSubStringKUniqueChar}
 * [Amazon]
 */
public class LongestSubstringAtMostKDistinctCharacters {

    public static void main(String[] args) {
        test("aabbcc", 3, 6);

        test("abcdefg", 7, 7);

        test("aabbcc", 2, 4);

        test("aabbcc", 1, 2);

        test("aabbcccccc", 1, 6);

        test("aabacbebebe", 3, 7);

        test("aaabbb", 3, 0);

        test("eceba", 2, 3);

        test("aa", 1, 2);

        test("", 0, 0);

        test("", 1, 0);
    }

    private static void test(String s, int k, int expected) {
        System.out.println("\n Input : " + s + " k :" + k + "  expected :" + expected);
        System.out.println("Obtained :" + longestSubStringKDistinctCharacters(s, k));

    }

    /**
     * O(n)/O(1)
     * Apply the same logic as {@link Java.companyWise.Google.LongestSubStringKUniqueChar}
     * instead of having sub-string, find its length.
     *
     * @param s
     * @param k
     * @return
     */
    private static int longestSubStringKDistinctCharacters(String s, int k) {

        if (s == null || s.isEmpty())
            return 0;

        char str[] = s.toCharArray();

        int len = 0;
        int start = 0;
        int distinctChars = 0;
        int totalDistinctChars = 0;

        int[] chars = new int[256];

        for (int i = 0; i < str.length; i++) {
            char c = str[i];

            if (chars[c] == 0) {
                distinctChars++;
                totalDistinctChars++;
            }

            chars[c]++;

            if (distinctChars == k)

                if (len < (i - start + 1))
                    len = i - start + 1;

            while (distinctChars > k) {

                if (chars[str[start]] > 0)
                    chars[str[start]]--;

                if (chars[str[start]] == 0)
                    distinctChars--;

                start++;
            }

        }

        if (totalDistinctChars < k)
            return 0;
        return len;


    }
}
