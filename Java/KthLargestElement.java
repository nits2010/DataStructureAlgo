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
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;

        int kthLargestElement = kthLargestElement(arr, arr.length, k);
        int kthSmallestElement = ktSmallestElement(arr, arr.length, k);
        System.out.println("kthSmallestElement:" + kthSmallestElement);
        System.out.println("kthLargestElement:" + kthLargestElement);
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


        return kthSmallestElementUtil(arr, 0, n - 1, n - k + 1);


    }

    private static int ktSmallestElement(int[] arr, int n, int k) {

        //K can not greater then the arr length
        if (k >= n)
            return Integer.MIN_VALUE;

        //If size is too small, then just sort and give back required result
        if (n <= 5) {
            Arrays.sort(arr);
            return arr[k];
        }


        return kthSmallestElementUtil(arr, 0, n - 1, k);


    }

    private static int kthSmallestElementUtil(int[] arr, int low, int high, int k) {


        if (k > 0 && k <= high - low + 1) {
            int n = high - low + 1;
            int[] median = new int[(n + 4) / 5];
            int i;

            for (i = 0; i < n / 5; i++)
                median[i] = findMedian(arr, low + i * 5, 5);


            if (i * 5 < n) {
                median[i] = findMedian(arr, low + i * 5, n % 5);
                i++;
            }


            int medianOfMedian = (i == 1) ? median[i - 1] : kthSmallestElementUtil(median, 0, i - 1, i / 2);

            int partitionPoint = partition(arr, low, high, medianOfMedian);

            if (partitionPoint - low == k - 1)
                return arr[partitionPoint];
            if (partitionPoint - low > k - 1)
                return kthSmallestElementUtil(arr, low, partitionPoint - 1, k);
            else // as k-1> partitionPoint - low => k > partitionPoint - low + 1 => k <  -partitionPoint + low - 1 => k-partitionPoint + low - 1
                return kthSmallestElementUtil(arr, partitionPoint + 1, high, k - partitionPoint + low - 1);


        }
        return Integer.MIN_VALUE;


    }


    // It searches for x in arr[l..r], and
// partitions the array around x.
    static int partition(int arr[], int l,
                         int r, int x) {
        // Search for x in arr[l..r] and move it to end
        int i;
        for (i = l; i < r; i++)
            if (arr[i] == x)
                break;
        swap(arr, i, r);

        // Standard partition algorithm
        i = l;
        for (int j = l; j < r; j++) {
            if (arr[j] <= x) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

    static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    static int findMedian(int arr[], int i, int n) {
        Arrays.sort(arr, i, i + n);
        return arr[i + n / 2]; // Return middle element

    }


    public static void main(String []args) {
        test1();
    }
}
