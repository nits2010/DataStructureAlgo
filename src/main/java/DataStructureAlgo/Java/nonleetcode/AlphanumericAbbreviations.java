package DataStructureAlgo.Java.nonleetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 15/02/19
 * Description:
 */
public class AlphanumericAbbreviations {

    public static void main(String []args) {
        String input = "ANKS";

        List<String> output = new LinkedList<>();

        alphanumericAbbreviations2_BottomUp(input, output, new String(), 0);

        System.out.println(output);

        output.clear();
        alphanumericAbbreviations_TopDown(input, output, new String(), 0);

        System.out.println(output);

        output.clear();

    }


    private static void alphanumericAbbreviations2_BottomUp(String input, List<String> output, String temp, int index) {

        if (index >= input.length()) {
//            System.out.println("temp" + temp);
            output.add(temp);
            return;

        }

        alphanumericAbbreviations2_BottomUp(input, output, temp + input.charAt(index), index + 1);

        int lastDigit = -1;

        if (temp.length() > 0 && Character.isDigit(temp.charAt(temp.length() - 1))) {

            lastDigit = temp.charAt(temp.length() - 1) - '0';

        }

        if (lastDigit != -1) {
            char[] t = temp.toCharArray();
            t[t.length - 1] = (char) (lastDigit + 1 + '0');
            alphanumericAbbreviations2_BottomUp(input, output, new String(t), index + 1);

        } else {
            alphanumericAbbreviations2_BottomUp(input, output, temp + 1, index + 1);
        }


    }

    private static void alphanumericAbbreviations_TopDown(String input, List<String> output, String temp, int index) {

        if (index >= input.length()) {
            //   System.out.println("temp" + temp);
            output.add(temp);
            return;

        }

        int lastDigit = -1;

        if (temp.length() > 0 && Character.isDigit(temp.charAt(temp.length() - 1))) {

            lastDigit = temp.charAt(temp.length() - 1) - '0';

        }

        if (lastDigit != -1) {
            char[] t = temp.toCharArray();
            t[t.length - 1] = (char) (lastDigit + 1 + '0');
            alphanumericAbbreviations_TopDown(input, output, new String(t), index + 1);

        } else {
            alphanumericAbbreviations_TopDown(input, output, temp + 1, index + 1);
        }

        alphanumericAbbreviations_TopDown(input, output, temp + input.charAt(index), index + 1);


    }

}
