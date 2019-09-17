package Java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-09
 * Description:https://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/
 * <p>
 * Given a string containing of ‘0’, ‘1’ and ‘?’ wildcard characters, generate all binary strings
 * that can be formed by replacing each wildcard character by ‘0’ or ‘1’.
 * Example :
 * <p>
 * Input str = "1??0?101"
 * Output:
 * 10000101
 * 10001101
 * 10100101
 * 10101101
 * 11000101
 * 11001101
 * 11100101
 * 11101101
 */
public class BinaryStringWildCardChar {

    public static void main(String []args) {
        String str = "1??0?101";
        SolutionBinarStringWildCardChar sol = new SolutionBinarStringWildCardChar();
        System.out.println(sol.binaryString(str));
    }
}


class SolutionBinarStringWildCardChar {

    public List<String> binaryString(String str) {

        final List<String> binaryStrings = new LinkedList<>();
        if (null == str || str.isEmpty())
            return binaryStrings;


//        binaryStringRecursive(str.length(), binaryStrings, str.toCharArray(), 0);
        binaryStringIterative(str.toCharArray(), binaryStrings);
        return binaryStrings;

    }

    //not working , dont know why, don't have time
    private void binaryStringRecursive(int len, List<String> binaryStrings, char chars[], int index) {

        if (index == len) {
            binaryStrings.add(new String(chars));
            return;
        }

        if (chars[index] == '?') {

            chars[index] = '0';
            binaryStringRecursive(len, binaryStrings, chars, index + 1);

            chars[index] = '1';
            binaryStringRecursive(len, binaryStrings, chars, index + 1);
        } else

            binaryStringRecursive(len, binaryStrings, chars, index + 1);
    }


    private void binaryStringIterative(char[] str, List<String> binaryStrings) {

        Queue<String> queue = new LinkedList<>();
        queue.offer(new String(str));

        while (!queue.isEmpty()) {

            String s = queue.poll();

            int index = s.indexOf('?');

            if (index >= 0) {
                char t[] = s.toCharArray();

                t[index] = '0';
                queue.offer(new String(t));

                t[index] = '1';
                queue.offer(new String(t));


            } else {
                binaryStrings.add(s);
            }
        }
    }


}