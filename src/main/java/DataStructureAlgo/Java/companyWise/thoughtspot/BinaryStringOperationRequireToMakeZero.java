package DataStructureAlgo.Java.companyWise.thoughtspot;

/**
 * Author: Nitin Gupta
 * Date: 20/09/19
 * Description:
 * Given a binary string of size N and a positive integer K, calculate the number of operations required to convert this string to
 * zero string by applying the following operation any number of times.
 * Operation: Let X is the index of first ‘1’ bit from left side, then Flip all the X’th, X+K, X+2K, X+3K… bits of string.
 * 1 <= N <= 10^6
 * 1 <= K <= N
 * <p>
 * Sample:
 * Input:  100010010011110,    K = 2
 * Step-1: 001000111001011
 * Step-2: 000010010011110
 * Step-3: 000000111001011
 * Step-4: 000000010011110
 * Step-5: 000000000110100
 * Step-6: 000000000011110
 * Step-7: 000000000001011
 * Step-8: 000000000000001
 * Step-9: 000000000000000
 * <p>
 * Operations = 9
 */
public class BinaryStringOperationRequireToMakeZero {

    public static void main(String[] args) {
        System.out.println(steps(new int[]{1, 0, 0, 1, 0, 1, 1}, 2));
        System.out.println(steps(new int[]{1, 0, 1, 0, 1, 1}, 2));
        System.out.println(steps(new int[]{1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    public static int steps(int a[], int k) {
        int steps = 0;
        for (int i = 0; i < k; i++) {
            int bit = 0;
            for (int j = i; j < a.length; j += k) {
                if (a[j] != bit) {
                    steps++;
                    bit = a[j];
                }
            }
        }
        return steps;
    }
}