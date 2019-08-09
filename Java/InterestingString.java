package Java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-09
 * Description: https://leetcode.com/discuss/interview-question/350312/google-onsite-interesting-string
 * Given a string s consisting of digits 0-9 and lowercase English letters a-z.
 * <p>
 * A string is interesting if you can split it into several substrings, such that each substring starts with a number and this number represents the number of characters after it. Retrun true if string s is intersting, otherwise false.
 * <p>
 * Example 1:
 * <p>
 * Input: "4g12y6hunter"
 * Output: true
 * Explanation: We can split it into "4g12y" and "6hunter".
 * Example 2:
 * <p>
 * Input: "124gray6hunter"
 * Output: true
 * Explanation: We can divide it into "12", "4gray", "6hunter".
 * Example 3:
 * <p>
 * Input: "31ba2a"
 * Output: false
 */
public class InterestingString {
    public static void main(String[] args) {
        test("12345", false);
        test("4g12y6hunter", true);
        test("124gray6hunter", true);
        test("31ba2a", false);

        test("169aaaaaaaaaaa3zyx", true);
        test("129aaaaaaaaaaa3zyx", true);
        test("179aaaaaaaaaaa3zyx", false);
        test("10", true);
        test("00000", true);


    }

    private static void test(String s, boolean expected) {
        InterestingStringDFSBFS.InterestingStringDFS dfs = new InterestingStringDFSBFS.InterestingStringDFS();
        InterestingStringDFSBFS.InterestingStringBFS bfs = new InterestingStringDFSBFS.InterestingStringBFS();
        InterestingStringCache.InterestingStringMemo memo = new InterestingStringCache.InterestingStringMemo();

        System.out.println();
        System.out.println("DFS -> Input string: " + s + "  Output: " + dfs.isInterestingString(s) + " Expected :" + expected);
        System.out.println("BFS -> Input string: " + s + "  Output: " + bfs.isInterestingString(s) + " Expected :" + expected);
        System.out.println("MEMO -> Input string: " + s + "  Output: " + memo.isInterestingString(s) + " Expected :" + expected);
    }

}

/**
 * Approach: [Fails]
 * As the first thought comes in mind, is to traverse from back and count characters. As soon you hit a digit,
 * check does this digits is equal to the count of character, if so then simply remove this part and now problem reduce to
 * the remaining string
 * Lets take an example:
 * "124gray6hunter"
 * We counted "hunter" (6) and we hit a digit and we check does this equal to the count so far, which is true for this Then
 * Now it becomes "124gray"
 * We count "gray" (4) and hit 4 and equal so Now
 * Now it becomes "12", we hit first as digit only, so we look back more (either assume this is a character or a digit)
 * In case of character this result true because "12" says there is 1 character after this which true.
 * <p>
 * <p>
 * Now but this does not take the consideration when all of the character are converge at the start of string.
 * Lets take an example:
 * "169aaaaaaaaaaa3zyx" -> "169aaaaaaaaaaa"
 * We counted "aaaaaaaaaaa" (11) and next is 9 < 11, so we try to make a bigger number by joining... which is
 * now 69 > 11 ; hence we'll consider 9 as character it self, so we left with count=12 and "16" since 6 < 12 so we'll try to make
 * bigger number caused it to 16 > 12 and here string ends. Result "False"
 * <p>
 * <p>
 * <p>
 * Correction in above approach:
 * The problem we had in above approach is we don't know where to stop to make it work. Where we should say
 * that current value should be taken as "digit" or 'character'. Hence we need to try both of them.
 * <p>
 * So we can recursively try and find, Leads to Simple Backtracking.
 * <p>
 * <p>
 * Backtracking
 * <p>
 * Our Goal: Our goal is to divide the given string in multiple chunks s.t. each of them is individually forming correct string. If we can, then TRUE otherwise False
 * Our Constraints: We can't consume more character then given in string
 * Our Choices: choose each element, either assume as digit ( if it really a digit) otherwise makr it as character and try for remaining part.
 * <p>
 * Algorithm:
 * 1. Check does current character is a digit, if so, skip that many character in
 * 2. Check remaining string is follow the rule
 * 3. if Yes: True Otherwise
 * 4. Go back and try to build a bigger number (if digits are consecutive to each other) and try again.
 * <p>
 */

class InterestingStringDFSBFS {

    /**
     * * Complexity:
     * * The worst case aries when all are digits like "12345"
     * * in this case, every time we encounter a digit only an d we move forward that many character.
     * * We end up forming all the sub-string.
     * * O(n^2)
     */
    static class InterestingStringDFS {
        public boolean isInterestingString(String str) {
            if (null == str || str.isEmpty())
                return true;

            if (!Character.isDigit(str.charAt(0)))
                return false;

            return isInterestingString(str, 0);
        }

        private boolean isInterestingString(String str, int index) {

            /**
             * Our Constraints: We can't consume more character then given in string
             */
            if (index > str.length())
                return false;

            /**
             * Our Goal: Our goal is to divide the given string in multiple chunks s.t. each of them is individually forming correct string. If we can, then TRUE otherwise False
             * We consumed all
             */
            if (index == str.length()) {
                return true;
            }

            /**
             * Our Choices:
             * Try all
             */
            int n = 0;
            for (int i = index; i < str.length(); i++) {

                /**
                 * If this is a digit.
                 * Either take this and try or build bigger digit
                 ** 4 g 1 2 y 6 h u n t e   r
                 ** 0 1 2 3 4 5 6 7 8 9 10 11
                 *
                 * Stage 1:  "4g12y6hunter" -> "6hunter"  [ as n = 4 so assuming there are 4 character next to it so remaining "6hunter" ]
                 * Stage 2:   "6hunter" -> "" [ as n = 6 so assuming there are 6 character next to it so remaining "" ]
                 * Stage 3: "" index==length: true
                 *
                 *
                 */
                if (Character.isDigit(str.charAt(i))) {

//                n = n * 10 + str.charAt(i) - '0';
                    n = getInteger(str, index, i);
                    if (isInterestingString(str, i + n + 1)) //Convert
                        return true;
                }
            }

            return false;
        }


        int getInteger(String s, int from, int to) {
            int i = from;
            int n = 0;
            while (i <= to)
                n = n * 10 + s.charAt(i++) - '0';

            return n;
        }
    }

    /**
     * * Complexity:
     * * The worst case aries when all are digits like "12345"
     * * in this case, every time we encounter a digit only an d we move forward that many character.
     * * We end up forming all the sub-string.
     * * O(n^2) / O(n)
     */
    static class InterestingStringBFS {
        public boolean isInterestingString(String str) {
            if (null == str || str.isEmpty())
                return true;

            if (!Character.isDigit(str.charAt(0)))
                return false;


            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);

            /**
             * to keep track what indexes in string has been consumed
             */
            boolean visited[] = new boolean[str.length()];
            visited[0] = true;

            while (!queue.isEmpty()) {

                int index = queue.poll();

                /**
                 * From this index, try the what are the possible indexes.
                 * Example:
                 ** 4 g 1 2 y 6 h u n t e   r
                 ** 0 1 2 3 4 5 6 7 8 9 10 11
                 *
                 * at index 0 we have 4 as value and can be skipped 4 character after this so
                 **  6 h u n t e   r
                 **  5 6 7 8 9 10 11
                 * Would be 6 now
                 *
                 * So for index =0 [ 4, 6 ]
                 */

                int n = 0;
                for (int i = index; i < str.length(); i++) {

                    if (Character.isDigit(str.charAt(i))) {
                        n = n * 10 + str.charAt(i) - '0';

                        int toVisit = n + i + 1;
                        if (toVisit < str.length()) {

                            if (!visited[toVisit]) { // not used

                                visited[toVisit] = true;
                                queue.offer(toVisit);

                            }
                        }

                        //Consumed all character
                        if (toVisit == str.length())
                            return true;

                    } else
                        break;

                }
            }

            return false;

        }


    }
}


/**
 * We can solve this using DP too.
 * As for each index, we try to see does the next string is valid or not,
 * If yes then we move forward.
 * if No, we move back and again try with big number.
 * <p>
 * We keeping hitting same index multiple time to see does string from here  is valid or not.
 * We can simply cache
 */
class InterestingStringCache {

    static class InterestingStringMemo {
        public boolean isInterestingString(String str) {
            if (null == str || str.isEmpty())
                return true;

            if (!Character.isDigit(str.charAt(0)))
                return false;

            Map<Integer, Boolean> memo = new HashMap<>();
            return isInterestingString(str, 0, memo);
        }

        private boolean isInterestingString(String str, int index, Map<Integer, Boolean> memo) {

            /**
             * Our Constraints: We can't consume more character then given in string
             */
            if (index > str.length())
                return false;

            /**
             * Our Goal: Our goal is to divide the given string in multiple chunks s.t. each of them is individually forming correct string. If we can, then TRUE otherwise False
             * We consumed all
             */
            if (index == str.length()) {
                return true;
            }

            if (memo.containsKey(index))
                return memo.get(index);
            /**
             * Our Choices:
             * Try all
             */
            boolean is = false;
            for (int i = index; i < str.length() && !is; i++) {

                /**
                 * If this is a digit.
                 * Either take this and try or build bigger digit
                 ** 4 g 1 2 y 6 h u n t e   r
                 ** 0 1 2 3 4 5 6 7 8 9 10 11
                 *
                 * Stage 1:  "4g12y6hunter" -> "6hunter"  [ as n = 4 so assuming there are 4 character next to it so remaining "6hunter" ]
                 * Stage 2:   "6hunter" -> "" [ as n = 6 so assuming there are 6 character next to it so remaining "" ]
                 * Stage 3: "" index==length: true
                 *
                 *
                 */
                if (Character.isDigit(str.charAt(i))) {

//                n = n * 10 + str.charAt(i) - '0';
                    int n = getInteger(str, index, i);
                    is = is || isInterestingString(str, i + n + 1, memo);

                }
            }
            memo.put(index, is);
            return is;
        }


        int getInteger(String s, int from, int to) {
            int i = from;
            int n = 0;
            while (i <= to)
                n = n * 10 + s.charAt(i++) - '0';

            return n;
        }
    }


}