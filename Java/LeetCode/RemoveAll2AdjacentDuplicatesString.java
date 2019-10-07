package Java.LeetCode;

import Java.nonleetcode.RemoveAllAdjacentDuplicatesString;

import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-20
 * Description:https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * <p>
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 * <p>
 * We repeatedly make duplicate removals on S until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 *
 * {@link RemoveAllAdjacentDuplicatesString}
 */
public class RemoveAll2AdjacentDuplicatesString {

    public static void main(String[] args) {
        test("abbaca", "ca");
        test("aaaaa", "a");
        test("azxxzy", "ay");
        test("geeksforgeeg", "gksfor");
        test("caaabbbaacdddd", "cabc");


    }

    private static void test(String s, String expected) {
        IRemoveAll2AdjacentDuplicatesString stack = new RemoveAll2AdjacentDuplicatesStringUsingStack();
        IRemoveAll2AdjacentDuplicatesString string = new RemoveAll2AdjacentDuplicatesStringManipulation();
        System.out.println("Input : " + s);
        System.out.println("Stack :" + stack.removeDuplicates(s) + " expected: " + expected);
        System.out.println("string :" + string.removeDuplicates(s) + " expected: " + expected);
    }


}

interface IRemoveAll2AdjacentDuplicatesString {
    String removeDuplicates(String s);
}

/**
 * O(n)/O(n)
 * Runtime: 55 ms, faster than 41.99% of Java online submissions for Remove All Adjacent Duplicates In String.
 * Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Remove All Adjacent Duplicates In String.
 */
class RemoveAll2AdjacentDuplicatesStringUsingStack implements IRemoveAll2AdjacentDuplicatesString {

    @Override
    public String removeDuplicates(String s) {
        if (s == null || s.isEmpty())
            return s;


        Stack<Integer> stack = new Stack<>();

        stack.push(0);

        int i = 1;
        char str[] = s.toCharArray();

        while (i < s.length()) {

            if (!stack.isEmpty() && str[stack.peek()] == str[i])
                stack.pop();
            else {
                stack.push(i);
            }
            i++;
        }

        i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty())
            stringBuilder.append(s.charAt(stack.pop()));


        return stringBuilder.reverse().toString();
    }
}

/**
 * O(n)/O(1)
 * Runtime: 3 ms, faster than 99.84% of Java online submissions for Remove All Adjacent Duplicates In String.
 * Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for Remove All Adjacent Duplicates In String.
 */
class RemoveAll2AdjacentDuplicatesStringManipulation implements IRemoveAll2AdjacentDuplicatesString {

    @Override
    public String removeDuplicates(String s) {
        if (s == null || s.isEmpty())
            return s;
        int i = 0;
        int j = 1;
        char str[] = s.toCharArray();

        while (j < s.length()) {

            if (i >= 0 && str[i] == str[j]) {
                i--;
                j++;
            } else {
                str[++i] = str[j++];
            }

        }


        return new String(str, 0, i + 1);
    }
}