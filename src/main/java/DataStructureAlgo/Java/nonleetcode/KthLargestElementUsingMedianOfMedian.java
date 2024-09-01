package DataStructureAlgo.Java.nonleetcode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
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
public class KthLargestElementUsingMedianOfMedian {

    public static void test1() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26}; //3,4,5,7,12,19,26
        int k = 3;

        int[] sortedCopy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedCopy);
        int kthLargestElement = kthLargestElement(arr, arr.length, k);
        System.out.println("kthLargestElement:" + kthLargestElement + " expected : " + sortedCopy[arr.length - k]);
        int kthSmallestElement = ktSmallestElement(arr, arr.length, k);
        System.out.println("kthSmallestElement:" + kthSmallestElement + " expected : " + sortedCopy[k - 1]);

    }

    private static int kthLargestElement(int[] arr, int n, int k) {

        //K cannot greater than the arr length
        if (k > n)
            return Integer.MIN_VALUE;

        //If the size is too small, then sort and give back a required result
        if (n <= 5) {
            Arrays.sort(arr);
            return arr[n - k];
        }


        return kthSmallestElementUtil(arr, 0, n - 1, n - k + 1);


    }

    private static int ktSmallestElement(int[] arr, int n, int k) {

        //K cannot greater than the arr length
        if (k >= n)
            return Integer.MIN_VALUE;

        //If the size is too small, then sort and give back a required result
        if (n <= 5) {
            Arrays.sort(arr);
            return arr[n - k];
        }


        return kthSmallestElementUtil(arr, 0, n - 1, k);


    }

    private static int kthSmallestElementUtil(int[] arr, int low, int high, int k) {


        if (k > 0 && k <= high - low + 1) {

            int n = high - low + 1;

            //divide the array of size 5, each part will have its own median
            int[] median = new int[(n + 4) / 5];

            int i;

            //get all median of each part
            for (i = 0; i < n / 5; i++)
                median[i] = findMedian(arr, low + i * 5, 5);


            //if last part has less than 5 elements
            if (i * 5 < n) {
                median[i] = findMedian(arr, low + i * 5, n % 5);
                i++;
            }


            int medianOfMedian = (i == 1)  //if there were only one part,
                    ? median[i - 1]  // hence median of median would be the first element
                    : kthSmallestElementUtil(median, 0, i - 1, i / 2); //else recursively mom

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
    static int partition(int[] arr, int low,
                         int high, int pivotElement) {

        // Search for x in arr[l..r] and move it to end
        int pivotIndex = low;

        //get pivot index
        while (pivotIndex <= high) {
            if (arr[pivotIndex] == pivotElement)
                break;
            pivotIndex++;

        }

        //swap it with high
        swap(arr, pivotIndex, low);

        // Standard 2-way partition algorithm

        int boundary = low;
        int l = low + 1;

        while (l <= high) {

            if (arr[l] <= pivotElement) {
                boundary++; // move boundary
                swap(arr, l, boundary);
            }
            l++;
        }
        swap(arr, boundary, low);
        return boundary;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int findMedian(int arr[], int i, int n) {
        Arrays.sort(arr, i, i + n);
        return arr[i + n / 2]; // Return a middle element

    }


    public static void main(String[] args) {
        test1();
    }
}
