package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 26/04/19
 * Description: https://leetcode.com/problems/decode-ways/submissions/
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decode as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decode as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class WaysToDecodeDigitSeq {


    public static void main(String args[]) {
        System.out.println(digitSeqDecoding("0212"));
        System.out.println(digitSeqDecoding("00212"));
        System.out.println(digitSeqDecoding("212"));
        System.out.println(digitSeqDecoding("1212"));

    }

    /**
     * Remove beginning 0
     *
     * @param s
     * @return
     */
    private static String removeBeginning0(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0')
            i++;
        if (i == s.length())
            return "";

        return s.substring(i);
    }

    public static int digitSeqDecoding(String s) {

        if (s == null || s.length() == 0)
            return 0;

        s = removeBeginning0(s);

        if (s.isEmpty())
            return 0;

        char digits[] = s.toCharArray();
        int n = s.length();

        //number of ways to decode string from 0 to i at i
        int count[] = new int[n + 1];
        count[0] = count[1] = 1; //empty string or 1 char string can be decode only in 1 way

        for (int i = 2; i <= n; i++) {

            //1 character string
            if (digits[i - 1] > '0')
                count[i] += count[i - 1]; //then this can be decode only one way, so add up the previous sum

            //if previous char is 1 i.e. 12 and current pointer is on 2, we have '1' previous char , this is one way to decode is only taking '1'
            if (digits[i - 2] == '1') {
                count[i] += count[i - 2];

            } else if (digits[i - 2] == '2' && digits[i - 1] < '7') //means 20, 21....26 then they form a char
                count[i] += count[i - 2];
        }

        return count[n];

    }
}
