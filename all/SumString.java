/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description:
 */
public class SumString {

    public static void main(String args[]) {
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

        return (Integer.valueOf(s1) + Integer.valueOf(s2) == Integer.valueOf(s3));
    }
}
