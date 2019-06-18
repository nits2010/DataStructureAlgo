package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 26/04/19
 * Description:
 */
public class NumberOfWaysToDecodeDigitSeq {


    public static void main(String args[]) {

        String s = "1212";

        System.out.println(ways(s));
    }

    private static int ways(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int n = s.length();

        int count[] = new int[n + 1];

        Arrays.fill(count, 0);

        count[0] = 1; //every single character can be transform
        count[1] = 1;

        char digits[] = s.toCharArray();
        for (int i = 2; i <= n; i++) {

            if (digits[i - 1] > '0')
                count[i] += count[i - 1];

            if (digits[i - 2] == '1' || digits[i - 2] == '2' && digits[i - 1] < '7')
                count[i] += count[i - 2];
        }

        return count[n];
    }
}
