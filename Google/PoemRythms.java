package Google;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/05/19
 * Description:
 */
public class PoemRythms {

    static int count = 0;

    public static void main(String args[]) {
        int n = 3;
        char[] str = new char[n];

        Arrays.fill(str, 'A');

        printRythms(str, n, 'A', 0);
        System.out.println(count);
    }

    private static void printRythms(char[] str, int n, char maxChar, int index) {

        if (index == n) {
            count++;
            System.out.println(new String(str));
            return;
        }

        for (char c = 'A'; c <= maxChar; c++) {
            str[index] = c;
            printRythms(str, n, (char) Math.max(maxChar, (char) (c + 1)), index + 1);
        }

    }
}
