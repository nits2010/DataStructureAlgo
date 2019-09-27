package Java.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-24
 * Description:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * <p>
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * [Amazon]
 */

public class LetterCombinationsPhoneNumber {

    public static void main(String []args) {

        System.out.println(letterCombinations("23"));
    }


    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return new ArrayList<>();
        return CombinationIterative.letterCombinationsIterative(digits);
    }


    //Faster
    public static List<String> letterCombinationsRecursive(String digits) {
        List<String> output = new LinkedList<>();
        CombinationIterativeRecursive.letterCombinationsRecursive(digits, 0, output, "");
        return output;
    }


    //Using String builder
    public static List<String> letterCombinationsRecursiveStringBuilder(String digits) {
        List<String> output = new LinkedList<>();
        CombinationIterativeRecursive.letterCombinationsRecursive(digits, 0, output, new StringBuilder());
        return output;
    }

}

interface Combination {
     String[] map = {"", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};
}

class CombinationIterativeRecursive implements Combination {


    /**
     * Using normal string
     * @param digits
     * @param n
     * @param output
     * @param current
     */
    public static void letterCombinationsRecursive(String digits, int n, List<String> output, String current) {

        if (n == digits.length()) {
            output.add(current);
            return;
        }

        String string = map[digits.charAt(n) - '0'];
        for (int i = 0; i < string.length(); i++) {
            current = current + string.charAt(i);
            letterCombinationsRecursive(digits, n + 1, output, current);
            current = current.substring(0, current.length() - 1);

        }
    }

    /**
     * Using string builder
     * @param digits
     * @param index
     * @param output
     * @param current
     */

    public static void letterCombinationsRecursive(String digits, int index, List<String> output, StringBuilder current) {

        if (index == digits.length()) {
            output.add(current.toString());
            return;
        }

        String string = map[digits.charAt(index) - '0'];
        for (int i = 0; i < string.length(); i++) {
            current.append(string.charAt(i));
            letterCombinationsRecursive(digits, index + 1, output, current);
            current.deleteCharAt(current.length() - 1);

        }
    }
}

class CombinationIterative implements Combination {


    public static List<String> letterCombinationsIterative(String digits) {
        List<String> output = new LinkedList<>();

        Queue<String> queue = new LinkedList<>();
        queue.add("");

        while (!queue.isEmpty()) {

            String str = queue.poll();

            if (str.length() == digits.length())
                output.add(str);
            else {

                int n = str.length();
                String charsOnThisDigit = map[digits.charAt(n) - '0'];
                for (int i = 0; i < charsOnThisDigit.length(); i++) {
                    queue.add(str + charsOnThisDigit.charAt(i));
                }
            }
        }
        return output;
    }


}
