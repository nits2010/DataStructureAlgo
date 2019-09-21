package Java.companyWise.Grab;

import javafx.util.Pair;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-18
 * Description: https://www.geeksforgeeks.org/find-two-missing-numbers-set-2-xor-based-solution/
 * Given an array of n unique integers where each element in the array is in range [1, n].
 * The array has all distinct elements and size of array is (n-2).
 * Hence Two numbers from the range are missing from this array. Find the two missing numbers.
 *
 * Examples:
 *
 * Input  : arr[] = {1, 3, 5, 6}, n = 6
 * Output : 2 4
 *
 * Input : arr[] = {1, 2, 4}, n = 5
 * Output : 3 5
 *
 * Input : arr[] = {1, 2}, n = 4
 * Output : 3 4
 */
public class MissingElements {
    public static void main (String []args){

        int arr[] = {1, 3, 5, 6};
        Pair<Integer, Integer> p1 = missingElements(arr, 6);
        System.out.println(p1);

        int arr2[] = {1, 2, 4};
        Pair<Integer, Integer> p2 = missingElements(arr2, 5);
        System.out.println(p2);

        int arr3[] = {1, 2};
        Pair<Integer, Integer> p3 = missingElements(arr3, 4);
        System.out.println(p3);


    }


    private  static Pair<Integer, Integer> missingElements(int a[], int range) {

        if (a == null || a.length == 0 || range == 0)
            return null;

        int size = a.length;

        int xor = 0;

        //xor all array elements
        for (int i = 0; i < size; i++) {
            xor ^= a[i];
        }

        //xor all range element
        for (int i = 1; i <= range; i++) {
            xor ^= i;
        }

        int setBit = xor & (~(xor - 1));

        int e1 = 0, e2 = 0;

        for (int i = 0; i < size; i++) {
            if ((a[i] & setBit) != 0)
                e1 ^= a[i];
            else
                e2 ^= a[i];

        }


        for (int i = 1; i <= range; i++) {
            if ((i & setBit) != 0)
                e1 ^= i;
            else
                e2 ^= i;

        }


        return new Pair<>(e1, e2);

    }
}
