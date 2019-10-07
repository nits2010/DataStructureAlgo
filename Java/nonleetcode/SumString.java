package Java.nonleetcode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description: https://www.geeksforgeeks.org/check-given-string-sum-string/
 * Check if a given string is sum-string
 * Given a string of digits, determine whether it is a ‘sum-string’. A string S is called a sum-string if a rightmost
 * substring can be written as sum of two substrings before it and same is recursively true for substrings before it.
 *
 * Examples :
 *
 * “12243660” is a sum string.
 * Explanation : 24 + 36 = 60, 12 + 24 = 36
 *
 * “1111112223” is a sum string.
 * Explanation: 111+112 = 223, 1+111 = 112
 *
 * “2368” is not a sum string
 * In general a string S is called sum-string if it satisfies the following properties:
 *
 * sub-string(i, x) + sub-string(x+1, j)
 *  = sub-string(j+1, l)
 * and
 * sub-string(x+1, j)+sub-string(j+1, l)
 *  = sub-string(l+1, m)
 * and so on till end.
 */
public class SumString {

    public static void main(String []args) {
        String test1 = "12243660";
        String test2 = "1111112223";
        String test3 = "2368";


        System.out.println("Input :" + test1 + "output: " + sumString(test1));
        System.out.println("Input :" + test2 + "output: " + sumString(test2));
        System.out.println("Input :" + test3 + "output: " + sumString(test3));


    }

    private static boolean sumString(String test) {
        if (null == test || test.isEmpty())
            return true;

        int length = test.length();

        boolean isSumString = true;

        //Try combination of every subString
        for (int i = 1; i < length; i++) {

            for (int j = 1; j + i < length; j++) {
                isSumString &= sumString(test, 0, i, j);
            }
        }
        return isSumString;

    }

    //try the combination; i to x, x+1 to j and j+1, L
    private static boolean sumString(String test, int beg, int len1, int len2) {

        String s1 = test.substring(beg, (beg == len1) ? len1 + 1 : len1);
        String s2 = test.substring(len1 + beg, (len1+beg == len2) ? len2 + 1: len2);
        int s3_len = s1.length() + s2.length();

        if (s3_len < test.length() - len1 - len2 - beg) {

            String s3 = test.substring(beg + len1 + len2, s3_len + 1);
            if (sumString(s1, s2, s3)) {
                //this is sum string, try other sum-string now
                return sumString(test, len1 + beg, len2, s3_len);
            }

        }

        //if there is no more character to accomplish s3_len then there could not be any sum-string ahead
        return false;


    }

    private static boolean sumString(String s1, String s2, String s3) {

        return (Integer.parseInt(s1) + Integer.parseInt(s2) == Integer.parseInt(s3));
    }
}
