package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.compression._1531;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/4/2024
 * Question Category: 1531. String Compression II
 * Description: https://leetcode.com/problems/string-compression-ii/description/
 * Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
 * <p>
 * Notice that in this problem, we are not adding '1' after single characters.
 * <p>
 * Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
 * <p>
 * Find the minimum length of the run-length encoded version of s after deleting at most k characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aaabcccd", k = 2
 * Output: 4
 * Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
 * Example 2:
 * <p>
 * Input: s = "aabbaa", k = 2
 * Output: 2
 * Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
 * Example 3:
 * <p>
 * Input: s = "aaaaaaaaaaa", k = 0
 * Output: 3
 * Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * 0 <= k <= s.length
 * s contains only lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @String
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Toptal <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class StringCompressionII_1531 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test("aaabcccd", 2, 4));
        tests.add(test("aabbaa", 2, 2));
        tests.add(test("aaaaaaaaaaa", 0, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"input", "k", "Expected"}, true, s, k, expected);

        int output;
        boolean pass, finalPass = true;

        Recursion.Solution solution = new Recursion.Solution();
        output = solution.getLengthOfOptimalCompression(s, k);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.TopDown topDown = new DynamicProgramming.TopDown();
        output = topDown.getLengthOfOptimalCompression(s, k);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"TopDown", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Recursion {

        static class Solution {

            public int getLengthOfOptimalCompression(String s, int k) {

                int n = s.length();
                return dfs(s, k, 0, ' ', 0);

            }

            private int dfs(String s, int k, int index, char prev, int prevCount) {
                if (k < 0)
                    return Integer.MAX_VALUE;

                //if the remaining string length is same as k then we can delete all the remaining characters
                //which will also be the length of the compressed string - 0
                if (index - k == s.length())
                    return 0;

                if (index == s.length())
                    return 0;

                int result;

                if (s.charAt(index) == prev) {

                    //how long the streak is being happening for this char
                    // the maximum length of s is 100 ; 1 <= s.length <= 100
                    //hence streak change from 1 to 2 or 8 to 9 does not make any length increment
                    //however, if it was 9 and then now 10 then it increase the count by 1
                    //similarly for 99 to 100

                    int count = 0;
                    if (prevCount == 1 || prevCount == 9 || prevCount == 99)
                        count = 1;

                    //we can't delete it as the char is the same
                    result = count + dfs(s, k, index + 1, prev, prevCount + 1);

                } else {

                    //delete this char; since its being different, the length will not change.
                    // "aabc" assume index at 'b' then prev 'a' != 'b' so if we delete b
                    // then "aac" will be compressed as "a2c" which does not increase the length (actually decrease in some cases.)
                    int delete = dfs(s, k - 1, index + 1, prev, prevCount);

                    //don't delete, if we don't delete, then it will increase the length by one
                    // "aabc" assume index at 'b' then prev 'a' != 'b' so if we don't delete b
                    // "aabc" -> "a2bc" will increase the length as compare to delete (a2c)
                    int dontDelete = 1 + dfs(s, k, index + 1, s.charAt(index), 1);

                    result = Math.min(delete, dontDelete);
                }

                return result;

            }


        }
    }

    static class DynamicProgramming {

        static class TopDown {

            public int getLengthOfOptimalCompression(String s, int k) {


                Map<String, Integer> dp = new HashMap<>(); //O(n^2*k)
                return dfs(s, k, 0, ' ', 0, dp);

            }

            //there are 4 changing states
            //index, k , prev and prevCount
            private String getKey(int k, int index, char prev, int prevCount) {
                return k + "," + index + "," + prev + "," + prevCount;
            }

            //O(n) -> number of characters
            // O(k) -> number of deletion state
            // O(n) -> prevCount states or prev character states
            //O(n^2*k) -> overall
            private int dfs(String s, int k, int index, char prev, int prevCount, Map<String, Integer> dp) {
                if (k < 0)
                    return Integer.MAX_VALUE;

                String key = getKey(k, index, prev, prevCount);
                if (dp.containsKey(key))
                    return dp.get(key);

                //if the remaining string length is same as k then we can delete all the remaining characters
                //which will also be the length of the compressed string - 0
                if (index - k == s.length() || index == s.length())
                    return 0;


                int result;

                if (s.charAt(index) == prev) {

                    //how long the streak is being happening for this char
                    // the maximum length of s is 100 ; 1 <= s.length <= 100
                    //hence streak change from 1 to 2 or 8 to 9 does not make any length increment
                    //however, if it was 9 and then now 10 then it increase the count by 1
                    //similarly for 99 to 100

                    int count = 0;
                    if (prevCount == 1 || prevCount == 9 || prevCount == 99)
                        count = 1;

                    //we can't delete it as the char is the same
                    result = count + dfs(s, k, index + 1, prev, prevCount + 1, dp);

                } else {

                    //delete this char; since its being different, the length will not change.
                    // "aabc" assume index at 'b' then prev 'a' != 'b' so if we delete b
                    // then "aac" will be compressed as "a2c" which does not increase the length (actually decrease in some cases.)
                    int delete = dfs(s, k - 1, index + 1, prev, prevCount, dp);

                    //don't delete, if we don't delete, then it will increase the length by one
                    // "aabc" assume index at 'b' then prev 'a' != 'b' so if we don't delete b
                    // "aabc" -> "a2bc" will increase the length as compare to delete (a2c)
                    int dontDelete = 1 + dfs(s, k, index + 1, s.charAt(index), 1, dp);

                    result = Math.min(delete, dontDelete);
                }

                dp.put(key, result);
                return result;

            }


        }
    }
}
