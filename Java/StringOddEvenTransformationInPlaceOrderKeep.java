package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description: https://www.geeksforgeeks.org/an-in-place-algorithm-for-string-transformation/
 * Given a string, move all even positioned elements to end of string. While moving elements, keep the relative order of all even positioned
 * and odd positioned elements same. For example, if the given string is “a1b2c3d4e5f6g7h8i9j1k2l3m4”,
 * convert it to “abcdefghijklm1234567891234” in-place and in O(n) time complexity.
 * <p>
 * <p>
 * <p>
 * Below are the steps:
 * <p>
 * 1. Cut out the largest prefix sub-string of size of the form 3^k + 1. In this step, we find the largest non-negative integer k such
 * that 3^k+1 is smaller than or equal to n (length of string)
 * <p>
 * 2. Apply cycle leader iteration algorithm ( it has been discussed below ), starting with index 1, 3, 9…… to this sub-string.
 * Cycle leader iteration algorithm moves all the items of this sub-string to their correct positions, i.e. all the alphabets are shifted to the
 * left half of the sub-string and all the digits are shifted to the right half of this sub-string.
 * <p>
 * 3. Process the remaining sub-string recursively using steps#1 and #2.
 * <p>
 * 4. Now, we only need to join the processed sub-strings together. Start from any end ( say from left ), pick two sub-strings and apply the
 * below steps:
 * <p>
 * ….4.1 Reverse the second half of first sub-string.
 * ….4.2 Reverse the first half of second sub-string.
 * ….4.3 Reverse the second half of first sub-string and first half of second sub-string together.
 * <p>
 * 5. Repeat step#4 until all sub-strings are joined. It is similar to k-way merging where first sub-string is joined with second. The resultant
 * is merged with third and so on.
 */
public class StringOddEvenTransformationInPlaceOrderKeep {


    public static void main(String arg[]) {
        String input = "a1b2c3d4e5f6g7h8i9";

//        String[] in = {"a", "1", "b", "2", "c", "3", "d", "4", "e", "5", "f", "6", "g", "7", "h", "8", "i", "9", "j", "10", "k", "11"};

        String[] in = {"a", "1", "b", "2", "c", "3", "d", "4", "e", "5", "f", "6"};

        Arrays.stream(transform(in)).forEach(x -> System.out.print(x + " "));
    }

    private static String[] transform(String[] input) {

        String str[] = input;
        int shift = 0;
        int length = input.length;
        int partitionLength;
        int currentStringLength;

        int leftOverLength = length;

        //Iterate over string, till every partition transformed
        while (leftOverLength > 0) {
            partitionLength = getPartitionLength(leftOverLength);
            currentStringLength = partitionLength(partitionLength);
            leftOverLength = leftOverLength - currentStringLength;


            //Apply cycle leader algo
            cycleLeaderAlgorithm(str, shift, currentStringLength);

            //Reverse the second half of first substring
            reverse(str, shift / 2, shift - 1);

            //reverse the first half of second substring
            reverse(str, shift, shift + currentStringLength / 2 - 1);

            //reverse the second half of first substring and first half of second substring toegether
            reverse(str, shift / 2, shift + currentStringLength / 2 - 1);

            shift += currentStringLength;


        }
        return str;


    }

    private static void swap(String input[], int firstIndex, int secondIndex) {
        String temp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = temp;
    }

    private static void reverse(String in[], int from, int to) {

        while (from < to) {
            swap(in, from, to);
            from++;
            to--;
        }

    }

    private static void cycleLeaderAlgorithm(String[] str, int shift, int partitionLength) {


        String temp;

        for (int i = 1; i < partitionLength; i = i * 3) {

            String current = str[shift + i];
            int j = i;

            do {

                //Even index
                if (j % 2 == 0)
                    j = j / 2;

                else
                    j = partitionLength / 2 + j / 2;

                temp = str[shift + j];

                str[shift + j] = current;

                current = temp;

            } while (j != i);

        }

    }

    static int partitionLength(int partitionLength) {
        return (int) Math.pow(3, partitionLength) + 1;
    }

    static int getPartitionLength(int length) {

        int k = 0;

        while (Math.pow(3, k) + 1 <= length)
            k++;

        return k - 1;
    }
}
