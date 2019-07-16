package Java.companyWise.facebook;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description: https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 * <p>
 * An encoded string (s) is given, the task is to decode it. The pattern in which the strings are encoded is as follows.
 *
 * <count>[sub_str] ==> The substring 'sub_str'
 * appears count times.
 * <p>
 * Input : str[] = "1[b]"
 * Output : b
 * <p>
 * Input : str[] = "2[ab]"
 * Output : abab
 * <p>
 * Input : str[] = "2[a2[b]]"
 * Output : abbabb
 * <p>
 * Input : str[] = "3[b2[ca]]"
 * Output : bcacabcacabcaca
 */
public class DecodedIncodedString {

    public static void main(String args[]) {

        boolean pass = true &&
                invalidInputTest() &&
                multiDigitMultiCharTest() &&
                singleDigitMultiCharTest() &&
                singleDigitCharTest();

        System.out.println(pass);

    }


    private static boolean invalidInputTest() {
        String input = "10[b12[ca]";
        String[] inputs = {input};
        InvalidParameterException exception = new InvalidParameterException();

        try {
            List<String> outputs = Arrays.stream(inputs).map(in -> Decode.decoded(in)).collect(Collectors.toList());
        } catch (Exception e) {
            return e instanceof InvalidParameterException ? true : false;
        }
        return false;
    }

    private static boolean multiDigitMultiCharTest() {

        String input1 = "10[b12[ca]]";
        List<String> expected = Arrays.asList("bcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacaca");


        String[] inputs = {input1};

        List<String> outputs = Arrays.stream(inputs).map(in -> Decode.decoded(in)).collect(Collectors.toList());

        for (String s : outputs) {
            System.out.println(s);
        }

        return outputs.equals(expected);

    }

    private static boolean singleDigitMultiCharTest() {
        String input1 = "3[b2[ca]]";

        String input2 = "2[ab]";
        String input3 = "2[a2[b]]";
        String input4 = "3[b2[ca]]";
        String input5 = "1[b]";

        List<String> expected = Arrays.asList("bcacabcacabcaca", "abab", "abbabb", "bcacabcacabcaca", "b");

        String[] inputs = {input1, input2, input3, input4, input5};

        List<String> outputs = Arrays.stream(inputs).map(in -> Decode.decoded(in)).collect(Collectors.toList());

        for (String s : outputs) {
            System.out.println(s);
        }

        return expected.equals(outputs);
    }

    private static boolean singleDigitCharTest() {
        String input1 = "2[ab]";
        String input2 = "2[a2[b]]";
        String input3 = "1[b]";

        List<String> expected = Arrays.asList("abab", "abbabb", "b");
        String[] inputs = {input1, input2, input3};

        List<String> outputs = Arrays.stream(inputs).map(in -> Decode.decoded(in)).collect(Collectors.toList());

        for (String s : outputs) {
            System.out.println(s);
        }

        return expected.equals(outputs);

    }

}

class Decode {


    public static String decoded(String in) throws InvalidParameterException {

        char[] str = in.toCharArray();

        Stack<Integer> timesStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        int i = 0;

        int len = str.length;

        while (i < len) {


            char current = str[i];

            if (Character.isDigit(current)) {

                int j = i + 1;
                int value = current - '0';

                while (j < len && Character.isDigit(str[j])) {
                    value = value * 10 + str[j] - '0';
                    j++;
                }


                timesStack.push(value);

                i = --j;
            } else {

                if (current == '[')
                    stringStack.push(String.valueOf(current));
                else {
                    String temp = "";

                    //Pop till '[' found and create a string of that so fart
                    while (!stringStack.isEmpty() && !stringStack.peek().equals("["))
                        temp = stringStack.pop() + temp;

                    //Pop the '['
                    if (!stringStack.isEmpty())
                        stringStack.pop();

                    int times = timesStack.pop();

                    String newTimeString = getTimesString(times, temp);

                    stringStack.push(newTimeString);


                }

            }
            i++;
        }
        if (stringStack.size() > 1)
            throw new InvalidParameterException();

        if (!stringStack.isEmpty())
            return stringStack.pop();


        return "";
    }

    private static String getTimesString(int times, String soFar) {
        String temp = soFar;
        for (int x = 1; x < times; x++)
            soFar += temp;

        return soFar;
    }

}