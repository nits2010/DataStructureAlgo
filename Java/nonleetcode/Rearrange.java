package Java.nonleetcode;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-25
 * Description:
 */
public class Rearrange {

    // A simple method to rearrange
    // 'arr[0..n-1]' so that 'arr[j]'
    // becomes 'i' if 'arr[i]' is 'j'
    static void rearrange(int[] arr, int n) {
        for (int i = 0; i < n; i++) {

            // retrieving old value and
            // storing with the new one
            arr[arr[i] % n] += i * n;
        }

        for (int i = 0; i < n; i++) {
            // retrieving new value
            arr[i] /= n;
        }
    }


    // Drive code
    public static void main(String[] args) {
        int[] arr = {2, 0, 1, 4, 5, 3};
        int n = arr.length;

        System.out.println("Given array is : " + GenericPrinter.toString(arr));
        rearrange(arr, n);
        System.out.println("Modified array is :" + GenericPrinter.toString(arr));

    }
}
