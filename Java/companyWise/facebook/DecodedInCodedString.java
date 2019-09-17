package Java.companyWise.facebook;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:  https://leetcode.com/problems/decode-string/
 *
 * https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 * <p>
 * An encoded string (s) is given, the task is to decode it. The pattern in which the strings are encoded is as follows.
 *
 * <count>[sub_str] ==> The substring 'sub_str' appears 'count' times.
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
 * <p>
 * https://aonecode.com/facebook-phone-interview-questions-2019
 *
 * https://leetcode.com/problems/decode-string/discuss/341115/Full-thought-process-2-Algorithm-%3A-beat-100100-Explanation
 *
 */
public class DecodedInCodedString {

    public static void main(String []args) {
        testUsingStacks();
        testUsingImplicitStack();

    }

    private static void testUsingImplicitStack() {
        System.out.println("Testing on Implicit Stacks");
        IDecode decode = new DecodeFaster();
        boolean pass = true &&
                multiDigitMultiCharTest(decode) &&
                singleDigitMultiCharTest(decode) &&
                singleDigitCharTest(decode);

        System.out.println(pass);
    }

    private static void testUsingStacks() {
        System.out.println("Testing on explicit Stacks");
        IDecode decode = new Decode();
        boolean pass = true &&
                multiDigitMultiCharTest(decode) &&
                singleDigitMultiCharTest(decode) &&
                singleDigitCharTest(decode);

        System.out.println(pass);
    }


    private static boolean multiDigitMultiCharTest(IDecode decode) {

        String input1 = "10[b12[ca]]";
        List<String> expected = Arrays.asList("bcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacaca");


        String[] inputs = {input1};

        List<String> outputs = Arrays.stream(inputs).map(in -> decode.decode(in)).collect(Collectors.toList());

        for (String s : outputs) {
            System.out.println(s);
        }

        return outputs.equals(expected);

    }

    private static boolean singleDigitMultiCharTest(IDecode decode) {
        String input1 = "3[b2[ca]]";

        String input2 = "2[ab]";
        String input3 = "2[a2[b]]";
        String input4 = "3[b2[ca]]";
        String input5 = "1[b]";
        String input6 = "3[a]2[bc]";
        String input7 = "3[a2[c]]";
        String input8 = "2[abc]3[cd]ef";


        List<String> expected = Arrays.asList("bcacabcacabcaca", "abab", "abbabb", "bcacabcacabcaca", "b", "aaabcbc", "accaccacc", "abcabccdcdcdef");

        String[] inputs = {input1, input2, input3, input4, input5, input6, input7, input8};

        List<String> outputs = Arrays.stream(inputs).map(in -> decode.decode(in)).collect(Collectors.toList());

        for (String s : outputs) {
            System.out.println(s);
        }

        return expected.equals(outputs);
    }

    private static boolean singleDigitCharTest(IDecode decode) {
        String input1 = "2[ab]";
        String input2 = "2[a2[b]]";
        String input3 = "1[b]";

        List<String> expected = Arrays.asList("abab", "abbabb", "b");
        String[] inputs = {input1, input2, input3};

        List<String> outputs = Arrays.stream(inputs).map(in -> decode.decode(in)).collect(Collectors.toList());

        for (String s : outputs) {
            System.out.println(s);
        }

        return expected.equals(outputs);

    }

}

interface IDecode {
    String decode(String in);
}

class DecodeFaster implements IDecode {


    /**
     * 10[b12[ca]]
     * <p>
     * Find the count, find the open and close bracket string, do recursively and repeat it.
     * <p>
     * if this is a number, form a number 'times'
     * if this is a '[' then keep going and find the ']' and solve for this sub-string i.e. b12[ca]
     * if this is a char, append in our result.
     *
     * @param in
     * @return
     */
    public String decode(String in) {

        StringBuilder result = new StringBuilder();

        int i = 0;
        int times = 0;

        while (i < in.length()) {

            char current = in.charAt(i);

            if (Character.isDigit(current)) {

                times = times * 10 + current - '0';//form a number

            } else if (current == '[') { // if this is a close, then find the sub-string to recurse

                int start = i + 1;
                i++;

                int totalOpen = 1, totalClose = 0;

                //Find a sub-string to make a recursive call
                while (i < in.length() && totalOpen != totalClose) {

                    if (in.charAt(i) == '[') totalOpen++;
                    if (in.charAt(i) == ']') totalClose++;

                    i++;
                }


                //as this character might not be correct at i
                --i;

                //Divide
                String repeatedString = decode(in.substring(start, i));

                //Concur
                //append this repeated string by count times
                for (int k = 0; k < times; k++)
                    result.append(repeatedString); //this would be suffix and this is sub-part of string

                times = 0;


            } else
                result.append(current);


            i++;
        }

        return result.toString();
    }
}

class Decode implements IDecode {


    public String decode(String in) {

        System.out.println("Running " + in);
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

                else if (current == ']') {

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


                } else
                    stringStack.push(String.valueOf(current));

            }
            i++;
        }


        String result = "";
        while (!stringStack.isEmpty()) {


            result = stringStack.pop() + result;
        }


        return result;
    }

    private static String getTimesString(int times, String soFar) {
        String temp = soFar;
        for (int x = 1; x < times; x++)
            soFar += temp;

        return soFar;
    }

}