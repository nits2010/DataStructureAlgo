package Java.companyWise.facebook;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description: https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
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

        String input1 = "10[b12[ca]]" ;
        String input2 = "2[ab]";
        String input3 = "2[a2[b]]";
        String input4 = "3[b2[ca]]";
        String input5 = "1[b]";


        String[] inputs = {input1, input2, input3, input4, input5};

        List<String> outputs = Arrays.stream(inputs).map(in -> decoded(in)).collect(Collectors.toList());

        for(String s: outputs){
            System.out.println(s);
        }



    }

    private static String decoded(String in) {

        char[] str = in.toCharArray();

        Stack<Integer> timesStack = new Stack<>();
        Stack<String> characterStack = new Stack<>();

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

                i = j;
            } else {

                if (current != ']')
                    characterStack.push(String.valueOf(current));
                else {
                    String temp = "";

                    //Pop till '[' found and create a string of that so fart
                    while (!characterStack.isEmpty() && characterStack.peek().toCharArray()[0] != '[')
                        temp = characterStack.pop() + temp;

                    //Pop the '['
                    if (!characterStack.isEmpty())
                        characterStack.pop();

                    int times = timesStack.pop();

                    String newTimeString = getTimesString(times, temp);

                    characterStack.push(newTimeString);



                }
                i++;
            }
        }
        if (!characterStack.isEmpty())
            return characterStack.pop();


        return "";
    }

    private static String getTimesString(int times, String soFar) {
        String temp = soFar;
        for (int x = 1; x < times; x++)
            soFar += temp;

        return soFar;
    }

}
