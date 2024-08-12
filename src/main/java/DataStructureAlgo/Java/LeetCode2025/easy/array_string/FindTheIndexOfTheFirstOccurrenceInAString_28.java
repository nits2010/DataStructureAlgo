package DataStructureAlgo.Java.LeetCode2025.easy.array_string;

/**
 * Author: Nitin Gupta
 * Date: 2024-07-23
 * Question Category: 28. Find the Index of the First Occurrence in a String [easy]
 * Description: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *  * <p>
 *  * <p>
 *  * <p>
 *  * Example 1:
 *  * <p>
 *  * Input: haystack = "sadbutsad", needle = "sad"
 *  * Output: 0
 *  * Explanation: "sad" occurs at index 0 and 6.
 *  * The first occurrence is at index 0, so we return 0.
 *  * Example 2:
 *  * <p>
 *  * Input: haystack = "leetcode", needle = "leeto"
 *  * Output: -1
 *  * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *  * <p>
 *  * <p>
 *  * Constraints:
 *  * <p>
 *  * 1 <= haystack.length, needle.length <= 104
 *  * haystack and needle consist of only lowercase English characters.
 *  *
 * <p>
 *
 * <p>
 * File reference
 * -----------
 * Duplicate {@link  DataStructureAlgo.Java.LeetCode.ImplementStrStrKMPRobinKarp}
 * Similar {@link}
 * extension {@link }
 *
 * Tags
 * -----
 * @easy
 * @TwoPointers
 * @String
 * @StringMatching
 *
 * Company Tags
 * -----
 */
public class FindTheIndexOfTheFirstOccurrenceInAString_28 {

    public static void main(String[] args) {
        test("sadbutsad", "sad", 0);
        test("leetcode", "leeto", -1);
        test("a", "a", 0);



    }

    private static void test(String haystack, String needle , int expectedIndex) {
        Solution solution = new Solution();
        int result = solution.strStrBuiltIn(haystack,needle);
        boolean output = result == expectedIndex;
        System.out.println("\n haystack :" + haystack + "\n needle : " + needle + " returned output : " + result + " result : " + output);

    }
    static class Solution {
        /**
         * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/submissions/1330964495/
         * O(n*m)
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr(String haystack, String needle) {
            if ((haystack == null && needle == null) || (haystack != null && haystack.isEmpty() && needle.isEmpty()))
                return 0;

            if (haystack == null || haystack.isEmpty())
                return -1;

            if (needle == null || needle.isEmpty())
                return 0;

            for (int h = 0; h <= haystack.length() - needle.length(); h++) {
                int n = 0;
                while (n < needle.length()) {
                    if (haystack.charAt(h + n) == needle.charAt(n))
                        n++;
                    else
                        break;
                }
                if (n == needle.length())
                    return h;
            }
            return -1;


        }

        /**
         * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/submissions/1330967988/
         * 0
         * ms
         * Beats
         * 100.00%
         * @param haystack
         * @param needle
         * @return
         */
        public int strStrBuiltIn(String haystack, String needle) {
            if ((haystack == null && needle == null) || (haystack != null && haystack.isEmpty() && needle.isEmpty()))
                return 0;

            if (haystack == null || haystack.isEmpty())
                return -1;

            if (needle == null || needle.isEmpty())
                return 0;

            for (int h = 0; h <= haystack.length() - needle.length(); h++) {
                if (haystack.startsWith(needle, h))
                    return h;
            }
            return -1;


        }
    }
}
