package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-23
 * Description: https://leetcode.com/problems/first-unique-character-in-a-string/
 * <p>
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/365135/Three-variation-or-Explanation-or-100-100-or-LEETCODE-platform-fast
 */
public class FirstUniqueCharacterString {

    public int firstUniqChar(String s) {
        // return firstUniqCharSingleIteration(s);
        // return firstUniqCharTwiceIteration(s);
        return firstUniqCharIteration(s);

    }

    /**
     * Algo 3: Iterate over a to z, find the
     * <p>
     * Find the first index and last index of this character.
     * Choose min ( min, First) So far if first = last
     * Complexity: O(26*n)
     * However this is 100% fast and run in 1 ms (supersingly )
     * <p>
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for First Unique Character in a String.
     * Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for First Unique Character in a String.
     *
     * @param s
     * @return
     */
    public int firstUniqCharIteration(String s) {
        if (s == null || s.isEmpty()) return -1;
        int min = Integer.MAX_VALUE;

        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);

            if (first != -1 && first == last) {
                min = Math.min(min, first);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    /**
     * Algo 2: Run over given string, store the "First index" of each character. If a character is repeated, then push -1.
     * Run over frequency array, and find the least index value.
     * <p>
     * Complexity: O(26+n) => O(n)
     * Runtime: 13 ms, faster than 60.34% of Java online submissions for First Unique Character in a String.
     * Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for First Unique Character in a String.
     *
     * @param s
     * @return
     */
    public int firstUniqCharSingleIteration(String s) {
        if (s == null || s.isEmpty())
            return -1;

        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - 'a';

            if (freq[x] == 0)
                freq[x] = i + 1;
            else
                freq[x] = -1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0)
                min = Math.min(min, freq[i] - 1);

        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }

    /**
     * Algo 1: Run on given string, find frequency of each element. Than run again over string,
     * the first character having frequency 1 is your answer.
     * Complexity : (2*n) => O(n)
     * <p>
     * Runtime: 5 ms, faster than 97.41% of Java online submissions for First Unique Character in a String.
     * Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for First Unique Character in a String.
     *
     * @param s
     * @return
     */
    public int firstUniqCharTwiceIteration(String s) {

        if (s == null || s.isEmpty())
            return -1;

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            int x = c - 'a';
            freq[x]++;
        }

        int i = 0;
        for (char c : s.toCharArray()) {
            int x = c - 'a';
            if (freq[x] == 1)
                return i;

            i++;
        }
        return -1;
    }

}
