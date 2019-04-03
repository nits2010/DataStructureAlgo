package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 02/04/19
 * Description: https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-3-worst-case-linear-time/
 * Time->O(n)
 * Input: arr[] = {7, 10, 4, 3, 20, 15}
 * k = 3
 * Output: 7
 * <p>
 * Input: arr[] = {7, 10, 4, 3, 20, 15}
 * k = 4
 * Output: 10
 */
public class KthLargestElement {

    public static void test1() {
        int arr[] = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int expectedOutput = 5;

        int kthLargestElement = kthLargestElement(arr, arr.length, k);
        System.out.println("Obtained :" + kthLargestElement + " expected :" + expectedOutput);
    }

    private static int kthLargestElement(int[] arr, int n, int k) {

        //K can not greater then the arr length
        if (k >= n)
            return Integer.MIN_VALUE;

        //If size is too small, then just sort and give back required result
        if (n <= 5) {
            Arrays.sort(arr);
            return arr[k];
        }


        return kthLargestElementUtil(arr, 0, n - 1, k);


    }

    private static int kthLargestElementUtil(int[] arr, int low, int high, int k) {


        if (k > 0 && k <= high - low + 1) {
            int n = high - low + 1;
            int[] median = new int[(n + 4) / 5];
            int i;
            for (i = 0; i < n / 5; i++) {

                median[i] = findMedian(arr, low + i * 5, 5);

            }

            if (i * 5 < n) {
                median[i] = findMedian(arr, low + i * 5, n % 5);
                i++;
            }


            int medianOfMedian = (i == 1) ? median[i-1] : kthLargestElementUtil(arr, 0, i - 1, i / 2);

            int partitionPoint = partition(arr, low, high, medianOfMedian);

            if (partitionPoint - 1 == k - 1)
                return arr[partitionPoint];
            if (partitionPoint - 1 > k - 1)
                return kthLargestElementUtil(arr, low, partitionPoint - 1, k);
            else
                return kthLargestElementUtil(arr, partitionPoint + 1, high, k - partitionPoint + low - 1);


        }
        return Integer.MIN_VALUE;


    }


    // It searches for x in arr[l..r], and
// partitions the array around x.
    static int partition(int arr[], int l,
                         int r, int x)
    {
        // Search for x in arr[l..r] and move it to end
        int i;
        for (i = l; i < r; i++)
            if (arr[i] == x)
                break;
        swap(arr, i, r);

        // Standard partition algorithm
        i = l;
        for (int j = l; j <= r - 1; j++)
        {
            if (arr[j] <= x)
            {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

//    private static int partition(int[] arr, int low, int high, int pivot) {
//
//        {
//            int p;
//
//            for (p = low; p < high; p++)
//                if (arr[p] == pivot)
//                    break;
//
//            swap(arr, p, high);
//
//        }
//
//        int i = low;
//        int j = high;
//
//
//        while (i < j) { // [3,26,5,7,4,19,12 ] pivot = 12
//
//            /**
//             * oth ->  [3,26,5,7,4,19,12 ] pivot = 12
//             * 1st -> i->26, j->12 =>   [3,12,5,7,4,19,26 ] pivot = 12 ;
//             * 2st ->  i->12, j->4 => [3,4,5,7,12,19,26 ] pivot = 12
//             * 3rd -> i->12, j->12; ends here
//             */
//
//            //Find element on left side that is larger then pivot- to shift it to right
//            while (i < arr.length && arr[i] < pivot) {
//                i++;
//            }
//            //first iteration; i = 1 -> 26 that need to be shift end the end
//
//            //Find element on right side that is smaller then pivot- to shift it to left
//            while (j >= 0 && arr[j] > pivot) {
//                j--;
//            }
//
//            //first iteration; j = 6 -> 12 that need to be shift end the end
//            if (i <= j) {
//                swap(arr, i, j);
//
//            }
//        }
//
//
//        return i;
//    }

    static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    static int findMedian(int arr[], int i, int n) {
        if (i <= n)
            Arrays.sort(arr, i, n); // Sort the array
        else
            Arrays.sort(arr, n, i);
        return arr[n / 2]; // Return middle element
    }


    public static void main(String args[]) {
        test1();
    }
}
