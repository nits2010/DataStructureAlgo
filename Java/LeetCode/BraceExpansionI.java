package Java.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-13
 * Description:
 * https://leetcode.com/problems/brace-expansion
 * http://leetcode.liangjiateng.cn/leetcode/brace-expansion/description
 * A string S represents a list of words.
 * <p>
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 * <p>
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * <p>
 * Return all words that can be formed in this manner, in lexicographical order.
 * <p>
 * Example 1:
 * <p>
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: ["abcd"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
public class BraceExpansionI {

    public static void main(String[] args) {
        System.out.println(braceExpansionI("{a,b}c{d,e}f"));
        System.out.println(braceExpansionI("abcd"));
    }

    public static List<String> braceExpansionI(String expression) {
        if (expression.isEmpty()) return null;


        List<String> result = new ArrayList<>();

        List<String> components = getComponents(expression.trim());
        dfs(components, result, new StringBuilder(), 0);

        Collections.sort(components);
        return result;

    }


    private static List<String> getComponents(String expression) {
        List<String> components = new ArrayList<>();

        int n = expression.length();

        for (int i = 0; i < n; i++) {

            char current = expression.charAt(i);

            /**
             * Potential component started
             */
            if (current == '{') {
                int j = i + 1;
                StringBuilder component = new StringBuilder();

                while (j < n && expression.charAt(j) != '}') {
                    if (expression.charAt(j) != ',')
                        component.append(expression.charAt(j));
                    j++;
                }

                components.add(component.toString());
                i = j;
            } else
                components.add(current + "");
        }
        return components;
    }


    private static void dfs(List<String> components, List<String> result, StringBuilder temp, int pos) {

        if (temp.length() == components.size()) {

            result.add(temp.toString());
            return;
        }

        for (char c : components.get(pos).toCharArray()) {
            temp.append(c);
            dfs(components, result, temp, pos + 1);
            temp.deleteCharAt(temp.length() - 1); //backtrack

        }
    }

}
