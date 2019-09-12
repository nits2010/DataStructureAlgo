package Java.LeetCode.longestShortestCommon;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-31
 * Description:
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 3. Longest Substring Without Repeating Characters [ Medium ]
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * [Amazon]
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        test("abcabcbb", 3); //abc
        test("bbbbb", 1); //b
        test("pwwkew", 3); //wke or kew
        test("pwwpkekw", 4); //wpke
        test("pwwpkekwqrstuvw", 9); //ekwqrstuv
    }

    private static void test(String input, int expected) {
        System.out.println("\n Input :" + input + " expected :" + expected);

        LongestSubstringWithoutRepeatingCharactersSlidingWindow count = new LongestSubstringWithoutRepeatingCharactersSlidingWindow();
        LongestSubstringWithoutRepeatingCharactersIndex index = new LongestSubstringWithoutRepeatingCharactersIndex();

        System.out.println(" count :" + count.lengthOfLongestSubstring(input));
        System.out.println(" index :" + index.lengthOfLongestSubstring(input));
    }


}

/**
 * {@link Java.companyWise.Google.LongestSubStringKUniqueChar}
 * {@link LongestSubstringAtMostKDistinctCharacters}
 * <p>
 * <p>
 * O(2n)/O(1)
 * Runtime: 2 ms, faster than 99.85% of Java online submissions for Longest Substring Without Repeating Characters.
 * Memory Usage: 36.8 MB, less than 99.76% of Java online submissions for Longest Substring Without Repeating Characters.
 */
class LongestSubstringWithoutRepeatingCharactersSlidingWindow {

    /**
     * Apply sliding window concept, whenever you hit same character again, reduce the window from left side otherwise keep increasing on right side
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.isEmpty())
            return 0;

        int chars[] = new int[256]; //can be 128 if only ASCII permitted otherwise extended ascii

        /**
         * It holds the starting index from our potential sub-string start
         */
        int start = 0;

        /**
         * It holds the ending index from our potential sub-string ends
         */
        int end;

        /**
         * Max length
         */
        int len = 0;

        for (end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            /**
             * We have not seen this character, potential new sub-string [s,e]
             */
            if (chars[c] == 0) {
                len = Math.max(len, end - start + 1);
            }

            /**
             * Now we have seen it
             */
            chars[c]++;

            /**
             * If occurrence of it become more than 1, then reduce the window, till occurrence of it is <=1
             */
            while (start < end && chars[c] > 1) {
                chars[s.charAt(start)]--;
                start++;
            }


        }

        return len;

    }
}

/**
 * In above solution, we keep count of every character in 'chars' that made
 * us to have a while loop to reduce the count.
 * We can optimize it further by realizing the fact that once a character at 'j' occurred again, say at index 'i'
 * Then all the character from [s...i]; s<i;  can't be part of new potential sub-string. We can simply discard
 * all those chars in between and jump directly just next character of last occurrence of it which is i+1.
 * <p>
 * Example:
 * 0    1   2   3   4   5   6   7
 * p    w   w   p   k   e   k   w
 * <p>
 * Lets say
 * j=6 then we know that we have seen this at i=4 s.t. s=2 {w p k e}
 * if we include j'th char then it becomes {w p k e k} which violates the condition. Now the string from [s,i]-> {w p k}
 * can't be the part of new potential sub-string (why? because if it is so, then k will be repeated twice )
 * hence we'll consider the string from i+1 onwards {e k} as new potential string
 * <p>
 * <p>
 * O(n)/O(1)
 * Runtime: 2 ms, faster than 99.85% of Java online submissions for Longest Substring Without Repeating Characters.
 * Memory Usage: 36.9 MB, less than 99.76% of Java online submissions for Longest Substring Without Repeating Characters.
 */
class LongestSubstringWithoutRepeatingCharactersIndex {
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.isEmpty())
            return 0;

        /**
         * Will store the index of occurrence of this character
         */
        int chars[] = new int[256];

        int start = 0;
        int len = 0;

        for (int end = 0; end < s.length(); end++) {

            char c = s.charAt(end);

            // we need to find the next index at which 'c' occurred last time. Since we need to discard [s,i] string i.e. we can simply take the max out of earlier start and i+1
            start = Math.max(start, chars[c]);

            len = Math.max(len, end - start + 1);

            //when repeated, we want the next character index only. so store it as the way we want.
            chars[c] = end + 1;
        }


        return len;

    }
}