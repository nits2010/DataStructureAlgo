package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._2109;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 12/3/2024
 * Question Title: 2109. Adding Spaces to a String
 * Link: https://leetcode.com/problems/adding-spaces-to-a-string/description/
 * Description:You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the indices in the original string where spaces will be added. Each space should be inserted before the character at the given index.
 * <p>
 * For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces before 'Y' and 'C', which are at indices 5 and 9 respectively. Thus, we obtain "Enjoy Your Coffee".
 * Return the modified string after the spaces have been added.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
 * Output: "Leetcode Helps Me Learn"
 * Explanation:
 * The indices 8, 13, and 15 correspond to the underlined characters in "LeetcodeHelpsMeLearn".
 * We then place spaces before those characters.
 * Example 2:
 * <p>
 * Input: s = "icodeinpython", spaces = [1,5,7,9]
 * Output: "i code in py thon"
 * Explanation:
 * The indices 1, 5, 7, and 9 correspond to the underlined characters in "icodeinpython".
 * We then place spaces before those characters.
 * Example 3:
 * <p>
 * Input: s = "spacing", spaces = [0,1,2,3,4,5,6]
 * Output: " s p a c i n g"
 * Explanation:
 * We are also able to place spaces before the first character of the string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists only of lowercase and uppercase English letters.
 * 1 <= spaces.length <= 3 * 105
 * 0 <= spaces[i] <= s.length - 1
 * All the values of spaces are strictly increasing.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @String
 * @Simulation <p><p>
 * Company Tags
 * -----
 * @Amazon <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class AddingSpacesToAString_2109 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{8, 13, 15}, "LeetcodeHelpsMeLearn", "Leetcode Helps Me Learn"));
        tests.add(test(new int[]{1, 5, 7, 9}, "icodeinpython", "i code in py thon"));
        tests.add(test(new int[]{0, 1, 2, 3, 4, 5, 6}, "spacing", " s p a c i n g"));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] spaces, String input, String expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Spaces", "Input", "Expected"}, true, spaces, input, expected);

        String output;
        boolean pass, finalPass = true;

        Solution_StringBuilder solutionStringBuilder = new Solution_StringBuilder();
        output = solutionStringBuilder.addSpaces(input, spaces);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"StringBuilder", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_Array solutionArray = new Solution_Array();
        output = solutionArray.addSpaces(input, spaces);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Array", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class Solution_StringBuilder {
        public String addSpaces(String s, int[] spaces) {
            int n = spaces.length;
            int size = s.length();
            int sIndex = 0, spIndex = 0;
            StringBuilder result = new StringBuilder();

            while (sIndex < size) {

                if (spIndex < n && spaces[spIndex] == sIndex) {

                    result.append(' ');
                    spIndex++;
                }

                result.append(s.charAt(sIndex));
                sIndex++;
            }

            return result.toString();

        }
    }

    static class Solution_Array {
        public String addSpaces(String s, int[] spaces) {
            int n = spaces.length;
            int size = s.length();
            char[] result = new char[n + size];
            char[] string = s.toCharArray();
            int ri = 0;
            int sIndex = 0, spIndex = 0;

            while (sIndex < size) {

                if (spIndex < n && sIndex == spaces[spIndex]) {
                    result[ri] = ' ';
                    ri++;
                    spIndex++;
                }

                result[ri++] = string[sIndex++];

            }

            return new String(result);


        }
    }
}
