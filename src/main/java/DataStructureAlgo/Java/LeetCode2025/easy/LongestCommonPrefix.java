package DataStructureAlgo.Java.LeetCode2025.easy;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 7/19/2024
 * Description: https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Input: {“geeksforgeeks”, “geeks”, “geek”, “geezer”}
 * Output: “gee”
 *
 * Input: {“apple”, “ape”, “april”}
 * Output: “ap”
 *
 * Constraints:
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        test(new String[]{"flower","flow","flight"}, "fl");
        test(new String[]{"dog","racecar","car"}, "");
        test(new String[]{"geeksforgeeks", "geeks", "geek", "geezer"}, "gee");
        test(new String[]{"apple", "ape", "april"}, "ap");
        test(new String[]{"apple", "ape", "zebra"}, "");
    }

    private static void test(String[] input, String expectedOutput){
        Solution solution = new Solution();
        String result = solution.longestCommonPrefixSorting(input);
        boolean output = result.equals(expectedOutput);
        System.out.println("\n Input :"+ Arrays.toString(input) + "\n expectedOutput : "+expectedOutput + " result : "+output);

    }

   static class Solution {

        /**
         * Brute force
         * https://leetcode.com/problems/longest-common-prefix/submissions/1329759019/
         * 1 ms
         * Beat 74.51%
         *Time complexity : O (MxN) where M is length of first string and N is size of input array
         *
         * 1. A slight improvement in this solution can be achieved by first finding the smallest string and consider that as candidateString
         * 2. Another improvement is that, we can pick the smallest length word, then find the next smallest length word and keep matching them.
         * When we find two word of same length, then if they match as full string, then move to next smallest word and keep matching.
         * @param strs
         * @return string
         */
        public String longestCommonPrefix(String[] strs) {

            if (strs == null || strs.length == 0)
                return "";

            if (strs.length == 1)
                return strs[0];

            final StringBuilder output = new StringBuilder();

            String candidate = strs[0];
            int candidateLength = candidate.length();
            int j = 0 ;
            boolean stop = false;
            while (j<candidateLength){ //O(min(strs[0].length))
                int i = 1;
                while (i<strs.length && j < strs[i].length()){  // O(n)
                    if (candidate.charAt(j) != strs[i].charAt(j))
                        break;
                    i++;
                }
                if (i==strs.length){
                    output.append(candidate.charAt(j));

                }else {
                    stop = true;
                }
                if(stop)
                    break;
                j++;

            }
            return output.toString();
        }

        /**
         * https://leetcode.com/problems/longest-common-prefix/submissions/1329804581/
         * 1
         * ms
         * Beats
         * 74.51%
         * The idea of this solution is inspired by the second improvement on above solution.
         * We can sort the array based on word length keeping the smallest word at first position.
         * Post that, keep matching this word to next word until they match at full otherwise pick the match part only.
         * To further optimize, we can just compare the first and last string of the array. Since, this could possible that, there are duplicate string in the array.
         * post sorting, they will close to each other. hence the last string could be either same of first string or different. IN case of different finding the common prifix
         * will give the output.
         * Time complexity;
         * O(n*log(n)) + O(minLengthOfString)
         * @param strs
         * @return
         */
        public String longestCommonPrefixSorting(String[] strs) {
            if (strs == null || strs.length == 0)
                return "";

            if (strs.length == 1)
                return strs[0];

            //this will sort string lexicographically
            //"flower","flow","flight" -> "flight", "flow", "flower"
            Arrays.sort(strs); //O(n*log(n) ) n is the length of the array

            String first = strs[0];
            String last = strs[strs.length-1];

            //find common prefix
            int firstLength = first.length();
            int lastLength = last.length();

            int runner = Math.min(firstLength, lastLength);

            int i = 0;
            while (i < runner){
                if (first.charAt(i) != last.charAt(i))
                    break;
                i++;
            }
            return first.substring(0,i);
        }
    }

}

