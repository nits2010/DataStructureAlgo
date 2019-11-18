package Java.nonleetcode;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-05
 * Description: https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
 * <p>
 * Given a pattern containing only I’s and D’s. I for increasing and D for decreasing. Devise an algorithm to print the minimum number following that pattern. Digits from 1-9 and digits can’t repeat.
 * <p>
 * Examples:
 * <p>
 * Input: D        Output: 21
 * Input: I        Output: 12
 * Input: DD       Output: 321
 * Input: II       Output: 123
 * Input: DIDI     Output: 21435
 * Input: IIDDD    Output: 126543
 * Input: DDIDDIID Output: 321654798
 */
public class MinimumNumberIAndD {

    public static void main(String arg[]) {
        SolutionMinimumNumberIAndD sol = new SolutionMinimumNumberIAndD();

        System.out.println("\"IIDDD\" O(n)/O(n) "+sol.minimumNumberIDStack("IIDDD"));
        System.out.println("\"IIDDD\" O(n)/O(1) "+ sol.minimumNumberID("IIDDD"));
        System.out.println("\"DDIDDIID\" O(n)/O(n) "+sol.minimumNumberIDStack("DDIDDIID"));
        System.out.println("\"DDIDDIID\" O(n)/O(1) "+ sol.minimumNumberID("DDIDDIID"));
    }
}

class SolutionMinimumNumberIAndD {

    //O(n)/O(n)
    String minimumNumberIDStack(String input) {

        if (input == null || input.length() >= 9)
            return "";

        int n = input.length();

        char result[] = new char[n + 1];
        int x = 0;

        /**
         * Idea
         * As you see
         * IIDDD    Output: 126543
         * for every I we are increasing our counter and for every D we are decreasing.
         * Except first I , it will have 1 more integer number other will have 1 to 1 mapping.
         *
         *
         * I I D D D
         *
         * putting for first I
         * 1 2
         *
         * putting for second I
         * 3 [ 1 2 3 ]
         *
         * Now we encounter D, we need to increase the prvious I value in order to decrese for this D
         *
         * [ 1 2 4 )
         *
         * Again D
         * [1 2 5 ]
         *
         * Again D
         * [1 2 6 )
         *  now start putting values
         *  1 2 6 5 4 3
         *
         *
         *  But this will be O(n^2)
         *
         *  We'll use stack
         *  How?
         *  if you see closely the values first increasing and then from back (Last D) it start increasing again till we hit a I again
         * stack help us to process it back ward
         */

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {

            stack.push(i + 1);
            if (i == n || input.charAt(i) == 'I') {

                while (!stack.isEmpty()) {
                    result[x++] = (char) ('0' + stack.pop());
                }
            }
        }

        return new String(result);

    }


    //O(n)/O(1)
    String minimumNumberID(String input) {

        if (input == null || input.length() >= 9)
            return "";

        int n = input.length();

        char result[] = new char[n + 1];
        int x = 0;

        /**
         * Idea
         * in above solution we used stack in order to get values from in decreasing order (by first increasing and then pop would produce
         * decreasing )
         *
         * instead of stack we can do it in a loop, pushing element from back side instead of from side
         */

        int count = 1;

        for (int i = 0; i <= n; i++) {

            if (i == n || input.charAt(i) == 'I') {
                //we encounter I, push the data from back side

                for (int j = i - 1; j >= -1; j--) { // j>= -1 because we'll have extra element for first I

                    result[j + 1] = (char) ('0' + count++);

                    //if we reached to first I then we don't need to go more back
                    if(j>=0 && input.charAt(j) == 'I')
                        break;
                }

            }
        }

        return new String(result);

    }
}
