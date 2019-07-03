package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 26/04/19
 * Description: https://leetcode.com/problems/decode-ways/submissions/
 */
public class WaysToDecodeDigitSeq {


    public static void main(String args[]) {

        String s = "1212";

        System.out.println(numDecodings(s));
    }

    public static int numDecodings(String s) {

        if (s == null || s.length() == 0)
            return 0;

        int n = s.length();

        if (s.charAt(0) == '0')
            return 0;

        //number of ways to decode string from 0 to i as count[i]
        int count[] = new int[n + 1];
        count[0] = count[1] = 1; //empty string or 1 char string can be decode only in 1 way

        for (int i = 2; i <= n; i++) {

            //1 character string
            if (s.charAt(i - 1) > '0')
                count[i] += count[i - 1]; //then this can be decode only one way, so add up the previous sum

            //if previous char is 1 i.e. 12 and current pointer is on 2, we have '1' previous char , this is one way to decode is only taking '1'
            if (s.charAt(i - 2) == '1') {
                count[i] += count[i - 2];

            } else if (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7') //means 20, 21....26 then they form a char
                count[i] += count[i - 2];
        }

        return count[n];

    }
}
