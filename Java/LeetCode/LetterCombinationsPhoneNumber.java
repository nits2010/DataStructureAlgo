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
 */
public class LetterCombinationsPhoneNumber {

    public static void main(String args[]) {

        System.out.println(letterCombinations("23"));
    }
    static String[] map = {"", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};



    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty())
            return new ArrayList<>();
        return letterCombinationsIterative(digits);
    }


    public static List<String> letterCombinationsIterative(String digits){
        List<String> output = new LinkedList<>();

        Queue<String> queue = new LinkedList<>();
        queue.add("");

        while (!queue.isEmpty()){

            String str = queue.poll();

            if (str.length() == digits.length())
                output.add(str);
            else {

                int n = str.length();
                String charsOnThisDigit = map[digits.charAt(n)-'0'];
                for ( int i=0 ; i< charsOnThisDigit.length(); i++){
                    queue.add(str+charsOnThisDigit.charAt(i));
                }
            }
        }
        return output;
    }

    public static List<String> letterCombinationsRecursive(String digits) {
        List<String> output = new LinkedList<>();
        letterCombinationsRecursive(digits, 0, output, "");
        return output;
    }

    private static void letterCombinationsRecursive(String digits, int n, List<String> output, String current) {

        if (n == digits.length()) {
            output.add(current);
            return;
        }

        String string = map[digits.charAt(n)-'0'];
        for (int i = 0; i < string.length(); i++) {
            current = current + string.charAt(i);
            letterCombinationsRecursive(digits, n + 1, output, current);
            current = current.substring(0, current.length() - 1);

        }
    }
}
