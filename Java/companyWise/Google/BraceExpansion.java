package Java.companyWise.Google;

import Java.LeetCode.braceExpansion.BraceExpansionI;
import Java.LeetCode.braceExpansion.BraceExpansionII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-13
 * Description:https://leetcode.com/discuss/interview-question/354944/google-phone-screen-brace-expansion
 * <p>
 * You are given a string a_{cat,dog}_is_with_{sarah,mike}
 * Return the outputs as:
 * a_cat_is_with_sarah
 * a_cat_is_with_mike
 * a_dog_is_with_sarah
 * a_dog_is_with_mike
 * <p>
 * {@link BraceExpansionI}
 * {@link BraceExpansionII}
 */
public class BraceExpansion {
    public static void main(String[] args) {
        System.out.println(braceExpansion("a_{cat,dog}_is_with_{sarah,mike}"));
        System.out.println(braceExpansion("a{cat,dog}iswith{sarah,mike}"));
        System.out.println(braceExpansion("a_{cat_,dog_}_is_with_{sarah_,mike_}"));
        System.out.println(braceExpansion("abcd"));
        System.out.println(braceExpansion("{abcd,cdef}"));
        System.out.println(braceExpansion("t_{abcd,cdef}"));
    }

    /**
     * Assumption: Assuming it does not have nested curly braces.
     *
     * <p>
     * a_{cat,dog}_is_with_{sarah,mike}
     * <p>
     * four categories
     * 1. letter : Just like normal character
     * 2. _ : like a separator for different components
     * 3. String within {} : Can contain 1 or more components
     * 4. , : Components separator
     * <p>
     * <p>
     * Algorithm:
     * Find all the components
     * Run dfs
     *
     * @param expression
     * @return
     */
    private static List<String> braceExpansion(String expression) {
        if (expression.isEmpty())
            return Collections.EMPTY_LIST;

        List<String> result = new ArrayList<>();
        List<List<String>> components = getComponents(expression);

        dfs(components, result, new ArrayList<>(), 0);

        return result;
    }


    /**
     * a_{cat,dog}_is_with_{sarah,mike}
     * <p>
     * [[a_] , [cat, dog], [_is_with_] , [sarah, mike]]
     * <p>
     * When '{' encounter
     * 1. whenever see a { at i'th index, find the  } index.
     * 2. Split the string within {}and push them in a component
     * <p>
     * <p>
     * When a character encounter
     * 1. Keep moving right till you hit either end of string or '{' : take this as a component
     * <p>
     * <p>
     * When a '_' encounter
     * Apply same logic as when u hit a character
     *
     * @param expression
     * @return
     */
    private static List<List<String>> getComponents(String expression) {
        List<List<String>> components = new ArrayList<>();

        int n = expression.length();

        for (int i = 0; i < n; i++) {

            char c = expression.charAt(i);

            /**
             * When '{' encounter
             * 1. whenever see a { at i'th index, find the  } index.
             * 2. Split the string within {}and push them in a component
             */
            if (c == '{') { //{cat,dog}_is_with_{sarah,mike} Or {sarah,mike}

                int j = i + 1;

                while (j < n && expression.charAt(j) != '}') {
                    j++;
                }

                components.add(getSubComponents(expression, i + 1, j));
                i = j; //we have i++ in loop
            } else {
                //a_{cat,dog}_is_with_{sarah,mike}
                int j = i;
                StringBuilder temp = new StringBuilder();
                while (j < n && expression.charAt(j) != '{')
                    temp.append(expression.charAt(j++));

                components.add(Arrays.asList(temp.toString()));
                i = j - 1;
            }
        }


        return components;
    }

    private static List<String> getSubComponents(String expression, int low, int high) {

        String subExpression = expression.substring(low, high);
        String[] subComponents = subExpression.split(",");

        return Arrays.asList(subComponents);
    }


    /**
     * @param components [[a_] , [cat, dog], [_is_with_] , [sarah, mike]]
     * @param result
     * @param temp
     * @param pos
     */
    private static void dfs(List<List<String>> components, List<String> result, List<String> temp, int pos) {

        if (temp.size() == components.size()) {
            result.add(String.join("", temp));
            return;
        }

        for (String comp : components.get(pos)) {
            temp.add(comp);
            dfs(components, result, temp, pos + 1);
            temp.remove(temp.size() - 1);

        }
    }
}
