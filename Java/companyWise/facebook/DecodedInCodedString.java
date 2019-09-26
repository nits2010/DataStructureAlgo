package Java.companyWise.facebook;

import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:  https://leetcode.com/problems/decode-string/
 * 394. Decode String [Medium]
 * https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 * An encoded string (s) is given, the task is to decode it. The pattern in which the strings are encoded is as follows.
 *
 * <count>[sub_str] ==> The substring 'sub_str' appears 'count' times.
 * Input : str[] = "1[b]"
 * Output : b
 * Input : str[] = "2[ab]"
 * Output : abab
 * Input : str[] = "2[a2[b]]"
 * Output : abbabb
 * Input : str[] = "3[b2[ca]]"
 * Output : bcacabcacabcaca
 * <p>
 * https://aonecode.com/facebook-phone-interview-questions-2019
 * My explanation: https://leetcode.com/problems/decode-string/discuss/341115/Full-thought-process-2-Algorithm-%3A-beat-100100-Explanation
 *
 * [Amazon]
 */
public class DecodedInCodedString {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("10[b12[ca]]", "bcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacacabcacacacacacacacacacacaca");
        test &= test("3[b2[ca]]", "bcacabcacabcaca");
        test &= test("2[ab]", "abab");
        test &= test("2[a2[b]]", "abbabb");
        test &= test("3[b2[ca]]", "bcacabcacabcaca");
        test &= test("1[b]", "b");
        test &= test("3[a]2[bc]", "aaabcbc");
        test &= test("3[a2[c]]", "accaccacc");
        test &= test("2[abc]3[cd]ef", "abcabccdcdcdef");
        test &= test("2[ab]", "abab");
        test &= test("2[a2[b]]", "abbabb");
        test &= test("1[b]", "b");

        System.out.println("\n Test :" + (test ? "Passed" : "Failed"));

    }

    private static boolean test(String s, String expected) {
        System.out.println("\nInput:" + s);
        System.out.println("Expected             :" + expected);
        String iterative = new DecodedInCodedStringIterative().decode(s);
        String recursive = new DecodedInCodedStringRecursive().decode(s);
        System.out.println("Iterative            :" + iterative + " Test :" + (expected.equals(iterative)));
        System.out.println("Recursive            :" + recursive + " Test :" + (expected.equals(recursive)));

        return expected.equals(iterative) && expected.equals(recursive);

    }


}


class DecodedInCodedStringRecursive {


    /**
     * Recusive stack:
     * <p>
     * If we see a number, for a full number and push it to Integer stack (timesStack)
     * if this is a character (not [ and ] ) then push it to string stack
     * if this is a [ then push it to string stack, we are about to close this once we reach a ], and we'll evaluate expression between [ and ].
     * if this is a ] then it time to evaluate ths expression made above.
     * Simply find how many times this expression need to repate using times stack top and make a new string and push it back to string stack, why? because this may be in another [ and ] that hasn't been resolved yet.
     * Gist: This algorithm works as dfs manner, first solve the inner most [...] then then keep appending it
     * <p>
     * This algorithm works as dfs manner, first solve the inner most [...] then then keep appending it
     * <p>
     * 10[b12[ca]]
     * <p>
     * Find the count, find the open and close bracket string, do recursively and repeat it.
     * <p>
     * if this is a number, form a number 'times'
     * if this is a '[' then find the ']' and solve for this sub-string i.e. b12[ca]
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

                int start = ++i;

                int totalOpen = 1, totalClose = 0;

                //Find a sub-string to make a recursive call
                while (i < in.length() && totalOpen != totalClose) {

                    if (in.charAt(i) == '[') totalOpen++;
                    if (in.charAt(i) == ']') totalClose++;

                    i++;
                }
                //as current 'i' after the loop would be at character after ']'
                --i;

                //Divide
                String repeatedString = decode(in.substring(start, i));

                //Concur; append this repeated string by count times
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

/**
 * If you notice above we are distingushing the number and string that's why we need to have two different stack.
 * The same above alog can be change to use only one stack and use times variable to repate it
 * <p>
 * Can we levrage this idea? Yes,
 * Just like above, we'll solve one by one [..] and keep appending it to previous made solution.
 * So just keep finding [ and ] s.t. we get a expression. But wait, by just doing [ and ] we may break the total string and never able to append back.
 * That's why use implicit stack.
 * <p>
 * Example:
 * As: 10[b12[ca]]
 * <p>
 * first two chars are numbers "10" known as "times".
 * We find a [, that means a potential sub-string is strated to get solve, find this sub-string.
 * that will be {b12[ca]} . not here we { and } is different then [ and ] .. use for just notation
 * now our problem become
 * b12[ca]
 */
class DecodedInCodedStringIterative {


    public String decode(String in) {

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
        StringBuilder soFarBuilder = new StringBuilder(soFar);
        for (int x = 1; x < times; x++)
            soFarBuilder.append(temp);
        soFar = soFarBuilder.toString();

        return soFar;
    }

}