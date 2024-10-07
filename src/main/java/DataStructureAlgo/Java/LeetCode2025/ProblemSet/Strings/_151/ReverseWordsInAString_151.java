package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._151;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/8/2024
 * Question Category: 151. Reverse Words in a String
 * Description: https://leetcode.com/problems/reverse-words-in-a-string/description/
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 * <p>
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 * <p>
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 * <p>
 * <p>
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Alibaba
 * @Amazon
 * @Apple
 * @Bloomberg
 * @Cisco
 * @Citadel
 * @Huawei
 * @Microsoft
 * @Nvidia
 * @Oracle
 * @Salesforce
 * @Snapchat
 * @WalmartLabs
 * @Yandex
 * @Yelp
 * @Zillow
 * @Editorial https://leetcode.com/problems/reverse-words-in-a-string/solutions/5883726/easy-solution
 */
public class ReverseWordsInAString_151 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("the sky is blue", "blue is sky the");
        test &= test("  hello world  ", "world hello");
        test &= test("a good   example", "example good a");
        test &= test("  ", "");
        test &= test("a", "a");
        test &= test("  hello  ", "hello");
        test &= test("a  ", "a");

        CommonMethods.printResult(test);
    }

    private static boolean test(String s, String expected) {
        System.out.println("----------------------------------");
        System.out.println("Input : " + s + " expected : " + expected);

        String output;
        boolean pass, finalPass = true;

        Solution1 solution1 = new Solution1();
        output = solution1.reverseWords(s);
        pass = output.equals(expected);
        System.out.println("Output solution1 : " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        Solution2 solution2 = new Solution2();
        output = solution2.reverseWords(s);
        pass = output.equals(expected);
        System.out.println("Output solution2 : " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }

    static class Solution1 {
        public String reverseWords(String s) {
            if (s == null || s.isEmpty())
                return s;

            //reverse individual word
            String reversed = reverse(s);
            String[] rs = reversed.split(" ");
            System.out.println(Arrays.toString(rs));
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < rs.length; i++) {
                if (!rs[i].isEmpty()) {
                    rs[i] = reverse(rs[i]);
                    result.append(rs[i]);

                    if (i < rs.length - 1)
                        result.append(" ");
                }
            }


            return result.toString();


        }

        private String reverse(String s) {

            char[] st = s.toCharArray();
            int n = s.length();
            int i = 0, j = n - 1;

            //reverse the whole string
            while (i < j) {
                char temp = st[i];
                st[i] = st[j];
                st[j] = temp;
                i++;
                j--;
            }
            return new String(st);

        }


    }

    static class Solution2 {
        public String reverseWords(String s) {
            if (s == null || s.isEmpty())
                return s;

            String[] st = s.split(" ");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < st.length; i++) {
                if (!st[i].isEmpty()) {
                    result.insert(0, st[i]);

                    if (i < st.length - 1)
                        result.insert(0, " ");
                }
            }

            return result.toString();


        }


    }
}
