package Java.nonleetcode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/12/18
 * Description:
 * Question: https://www.geeksforgeeks.org/find-maximum-number-possible-by-doing-at-most-k-swaps/
 * Example:
 * Input: M = 254, K = 1
 * Output: 524
 * <p>
 * Input: M = 254, K = 2
 * Output: 542
 */
public class FindMaximumNumberWithKSwaps {

    public static String nextMaxNumber(String givenNumber, int kSwaps) {
        if (givenNumber == null || givenNumber.isEmpty() || kSwaps == 0)
            return givenNumber;


        String nextMaxNumber = new String(givenNumber);


        nextMaxNumber =  nextMaxNumberUtil(new StringBuilder(givenNumber), kSwaps, nextMaxNumber);

        System.out.println("Given: " + givenNumber + " nexMax " + nextMaxNumber);

        return nextMaxNumber;
    }

    private static void swap(StringBuilder input, int i, int j) {
        char ithChar = input.charAt(i);
        char jthChar = input.charAt(j);
        input.setCharAt(i, jthChar);
        input.setCharAt(j, ithChar);


    }

    private static String nextMaxNumberUtil(StringBuilder givenNumber, int kSwaps, String nextMaxNumber) {

        //if there is no more swaps left
        if (kSwaps == 0)
            return nextMaxNumber;


        //make a swap

        //Try all possibility for swaps
        for (int i = 0; i < givenNumber.length()-1; i++) {

            //swaps from next digit
            for (int j = i + 1; j < givenNumber.length(); j++) {

                //swap only if it can make a bigger number from previous given number
                if (givenNumber.charAt(i) < givenNumber.charAt(j)) {

                    //swap the digit
                    swap(givenNumber, i, j);

                    //choose max
                    if (givenNumber.toString().compareTo(nextMaxNumber) > 0) {
                        nextMaxNumber = givenNumber.toString();

                    }
                    //test for next k-1 swaps
                    nextMaxNumber = nextMaxNumberUtil(givenNumber, kSwaps - 1, nextMaxNumber);

                    //recur back and try other possibility
                    swap(givenNumber, i, j);
                }
            }
        }

        return nextMaxNumber;

    }

    public static void main(String []args) {
        String n1 = "254";
        String n2 = "254";
        String n3 = "68543";
        String n4 = "129814999";
        int k1 = 1;
        int k2 = 2;
        int k3 = 1;
        int k4 = 4;

        System.out.println(nextMaxNumber(n4, k4).equals("999984211"));

        System.out.println(nextMaxNumber(n1, k1).equals("524"));

        System.out.println(nextMaxNumber(n2, k2).equals("542"));

        System.out.println(nextMaxNumber(n3, k3).equals("86543"));



    }

}
