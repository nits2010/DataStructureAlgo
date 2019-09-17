package Java;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 07/01/19
 * Description:
 * https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
 */
public class FormMinimumNumberFromGivenSequence {

    private static void print(int[] out) {
        Arrays.stream(out).forEach(e -> System.out.print(e + " "));
    }


    private static void print(String seq) {
        System.out.println("\nM1");
        int[] output = formNumberM1(seq);
        System.out.print("Seq: " + seq + " ouptut: ");
        print(output);
    }

    private static void printM2(String seq) {
        System.out.println("\nM2");
        int[] output = formNumberM2(seq);
        System.out.print("Seq: " + seq + " ouptut: ");
        print(output);
    }

    private static void printM3(String seq) {
        System.out.println("\nM3");
        int[] output = formNumberM3(seq);
        System.out.print("Seq: " + seq + " ouptut: ");
        print(output);
    }

    public static void main(String []args) {


        String seq = "IIDDD";
        print(seq);
        printM2(seq);
        printM3(seq);

        seq = "DIDI";
        print(seq);
        printM2(seq);
        printM3(seq);


        seq = "DDIDDIID";
        print(seq);
        printM2(seq);
        printM3(seq);


        seq = "DD";
        print(seq);
        printM2(seq);
        printM3(seq);


        seq = "II";
        print(seq);
        printM2(seq);
        printM3(seq);


        seq = "I";
        print(seq);
        printM2(seq);
        printM3(seq);


        seq = "D";
        print(seq);
        printM2(seq);
        printM3(seq);


    }


    //O(n)/O(n)
    static int[] formNumberM3(final String sequence) {
        Stack<Integer> stack = new Stack<>();
        int output[] = new int[sequence.length() + 1];
        int runner = 0;

        //We'll iterate to all character one by one, and whenever we
        //1. encounter 'D' we'll push a new greater element
        //2. encounter 'I', we'll pop all
        for (int i = 0; i <= sequence.length(); i++) {

            stack.push(i + 1);

            if (i == sequence.length() || sequence.charAt(i) == 'I')
                while (!stack.isEmpty())
                    output[runner++] = stack.pop();
        }

        return output;
    }

    //O(n^2)
    static int[] formNumberM2(final String sequence) {
        if (null == sequence || sequence.length() == 0)
            return new int[0];

        //there always 1 more then integer then character in sequence
        int[] output = new int[sequence.length() + 1];
        int runner = 0;
        char sequences[] = sequence.toCharArray();
        int minVal;
        int pos = 0;

        //Process the first character

        //if the first character is I, then put increasing seq from 1
        if (sequences[0] == 'I') {
            output[runner++] = 1;
            output[runner++] = 2;

            //record the next possible minimum value
            minVal = 3;

            //keep this position of i
            pos = 1;
        } else {
            //if the first character is D, then put decreasing seq from 1

            output[runner++] = 2;
            output[runner++] = 1;

            //record the next possible minimum value
            minVal = 3;

            pos = 0;


        }

        //for rest of the chars
        int i = 1;
        while (i < sequences.length) {

            //if this is I then continue the sequence
            if (sequences[i] == 'I') {

                output[runner++] = minVal;
                minVal++;
                pos = i + 1;
            } else {
                //if this is D then then back track the sequence and increase everything by one to suffice the current value at i
                output[runner++] = output[i]; //pull the last value, runner  = i+1 always

                //back track
                for (int j = pos; j <= i; j++)
                    output[j]++;

                minVal++;

            }
            i++;

        }
        return output;

    }

    //O(n^2)
    static int[] formNumberM1(final String sequence) {
        if (null == sequence || sequence.length() == 0)
            return new int[0];

        //there always 1 more then integer then character in sequence
        int[] output = new int[sequence.length() + 1];


        //To keep track the max number printed so far
        int currMax = 0;

        //how many D's are there
        int noOfD = 0;

        int entry = 0;

        int j = 0;

        int runner = 0;

        char sequences[] = sequence.toCharArray();

        for (int i = 0; i < sequences.length; i++) {

            noOfD = 0;

            switch (sequences[i]) {


                case 'I':
                    //if this letter is "I", then count how many D's are there followed by this I
                    j = i + 1;

                    while (j < sequences.length && sequences[j] == 'D') {
                        noOfD++;
                        j++;
                    }

                    //If this "I" is the first character of the sequence
                    if (i == 0) {

                        //then maximum number can be printed from here is the numD + 2;
                        currMax = noOfD + 2;

                        //Since "I is the first character then for the first character we need to print two integer
                        output[runner++] = ++entry;
                        output[runner++] = currMax;

                        entry = currMax;

                    } else {
                        //If "I" is not the first character , then count the max to go
                        currMax = currMax + noOfD + 1;
                        entry = currMax;
                        output[runner++] = entry;
                    }

                    //Carry the remaining D's and move the cursor ahead
                    for (int k = 0; k < noOfD; k++) {
                        output[runner++] = --entry;
                        i++;
                    }

                    break;

                case 'D':
                    //if this letter is "D",

                    if (i == 0) {
                        //if this is the first letter then we need to treat it differently
                        // as we need to increase the currMax such that further values satisfy

                        //count how many D's are there ahead
                        j = i + 1;
                        while (j < sequences.length && sequences[j] == 'D') {
                            noOfD++;
                            j++;
                        }

                        //The number of D's ahead we need to have the current max that much ahead, since we need to print 2 number for a first letter then + 2
                        currMax = noOfD + 2;

                        output[runner++] = currMax;
                        output[runner++] = currMax - 1; //since its decreasing and we need to carry this max for further values

                        entry = currMax - 1; // get the entry so that unique number are appended
                    } else {

                        //if this is not the first letter then we need to carry the entry and print it
                        output[runner++] = --entry;
                    }
                    break;
            }

        }

        return output;
    }
}
