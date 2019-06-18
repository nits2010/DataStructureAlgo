package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
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
