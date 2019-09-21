package Java;

import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-20
 * Description: https://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
 * Given a string, recursively remove adjacent duplicate characters from the string. The output string
 * should not have any adjacent duplicates. See following examples.
 * Examples:
 * <p>
 * Input: azxxzy
 * Output: ay
 * First “azxxzy” is reduced to “azzy”.
 * The string “azzy” contains duplicates,
 * so it is further reduced to “ay”.
 * <p>
 * Input: geeksforgeeg
 * Output: gksfor
 * First “geeksforgeeg” is reduced to
 * “gksforgg”. The string “gksforgg”
 * contains duplicates, so it is further
 * reduced to “gksfor”.
 * <p>
 * Input: caaabbbaacdddd
 * Output: Empty String
 * <p>
 * Input: acaaabbbacdddd
 * Output: acac
 * <p>
 * Similar to {@link Java.LeetCode.RemoveAll2AdjacentDuplicatesString}
 * Instead of two, we need to remove all if duplicate and adjacent
 */
public class RemoveAllAdjacentDuplicatesString {
    public static void main(String[] args) {
        test("abbaca", "ca");
        test("aaaaa", "");
        test("azxxzy", "ay");
        test("geeksforgeeg", "gksfor");
        test("caaabbbaacdddd", "");


    }

    private static void test(String s, String expected) {
        System.out.println("\nInput : " + s + "\nexpected : " + expected);
        IRemoveAllAdjacentDuplicatesString stack = new RemoveAllAdjacentDuplicatesStringUsingStack();
        IRemoveAllAdjacentDuplicatesString string = new RemoveAllAdjacentDuplicatesStringManipulation();
        System.out.println("Stack    : " + stack.removeDuplicates(s));
        System.out.println("String   : " + string.removeDuplicates(s));
    }


}


interface IRemoveAllAdjacentDuplicatesString {
    String removeDuplicates(String s);
}

/**
 * O(n)/O(n)
 */
class RemoveAllAdjacentDuplicatesStringUsingStack implements IRemoveAllAdjacentDuplicatesString {

    @Override
    public String removeDuplicates(String s) {
        if (s == null || s.isEmpty())
            return s;

        Stack<Character> stack = new Stack<>();
        char[] str = s.toCharArray();
        stack.push(str[0]);

        int i = 1;

        while (i < s.length()) {
            //if top element is same as current, then find all duplicates and discard them along with current
            if (!stack.isEmpty() && stack.peek() == str[i]) {

                while (i < s.length() && stack.peek() == str[i])
                    i++;

                stack.pop();

            } else //this is not duplicate, push it
                stack.push(str[i++]);
        }

        if (stack.isEmpty())
            return "";

        else { //we can use string builder too

            i = stack.size();
            int temp = stack.size() - 1;

            while (!stack.isEmpty())
                str[temp--] = stack.pop();

            return new String(str, 0, i);

        }

    }
}


/**
 * O(n)/O(n)
 */
class RemoveAllAdjacentDuplicatesStringManipulation implements IRemoveAllAdjacentDuplicatesString {

    @Override
    public String removeDuplicates(String s) {
        if (s == null || s.isEmpty())
            return s;

        final char[] str = s.toCharArray(); //we'll use this as stack

        int j = 1;
        int i = 0;

        while (j < s.length()) {

            //if top element is same as current, then find all duplicates and discard them along with current
            if (i >= 0 && str[i] == str[j]) {

                while (j < s.length() && str[i] == str[j])
                    j++;

                i--; //similar like pop
            } else
                str[++i] = str[j++]; //push
        }

        return new String(str, 0, i + 1);
    }
}