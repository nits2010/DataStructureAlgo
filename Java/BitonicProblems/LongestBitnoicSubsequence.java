package Java.BitonicProblems;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-22
 * Description: https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
 * Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
 * Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)
 * <p>
 * Input arr[] = {12, 11, 40, 5, 3, 1}
 * Output: 5 (A Longest Bitonic Subsequence of length 5 is 12, 11, 5, 3, 1)
 * <p>
 * Input arr[] = {80, 60, 30, 40, 20, 10}
 * Output: 5 (A Longest Bitonic Subsequence of length 5 is 80, 60, 30, 20, 10)
 */
public class LongestBitnoicSubsequence {

    public static void main(String args[]) {
        int arr1[] = {1, 11, 2, 10, 4, 5, 2, 1};
        int arr2[] = {12, 11, 40, 5, 3, 1};
        int arr3[] = {80, 60, 30, 40, 20, 10};

        int arr[] = arr1;
        System.out.println("n^2 -> " + longestBitnoicSubSequence(arr));
        System.out.println("nLogn-> " + longestBitnoicSubSequenceNlogN(arr));

        arr = arr2;
        System.out.println("n^2 -> " + longestBitnoicSubSequence(arr));
        System.out.println("nLogn-> " + longestBitnoicSubSequenceNlogN(arr));

        arr = arr3;
        System.out.println("n^2 -> " + longestBitnoicSubSequence(arr));
        System.out.println("nLogn-> " + longestBitnoicSubSequenceNlogN(arr));

    }


    /**
     * {@link Java.LongestIncreasingSubSequence}
     *
     * @param arr
     * @return O(n ^ 2)
     */
    private static int longestBitnoicSubSequence(int[] arr) {

        int n = arr.length;

        int increasing[] = new int[n];
        int decreasing[] = new int[n];

        //form increasing
        for (int i = 0; i < n; i++) {
            increasing[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && increasing[i] < increasing[j] + 1)
                    increasing[i] = increasing[j] + 1;
            }
        }


        //form decreasing
        for (int i = n - 1; i >= 0; i--) {
            decreasing[i] = 1;

            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j] && decreasing[i] < decreasing[j] + 1)
                    decreasing[i] = decreasing[j] + 1;
            }
        }

        //find max cut point
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, increasing[i] + decreasing[i] - 1);
        }

        return max;
    }

    /**
     * {@link Java.LongestIncreasingSubSequence}
     *
     * @param arr
     * @return O(nlogn)
     */

    private static int longestBitnoicSubSequenceNlogN(int[] arr) {

        int n = arr.length;

        int increasingTail[] = buildTail(arr, n);//contains length of LBS at i index
        reveresArr(arr);

        int decreasingTail[] = buildTail(arr, n);
        reveresArr(arr); //reset the array

        //find max cut point
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, increasingTail[i] + decreasingTail[i] - 1);
        }


        return max;
    }

    // function to reverse an array
    public static void reveresArr(int arr[]) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;

        }
    }

    /**
     * {@link Java.LongestIncreasingSubSequence}
     *
     * @param nums
     * @param n
     * @return
     */
    private static int[] buildTail(int[] nums, int n) {
        if (nums == null || nums.length == 0)
            return nums;

        /**
         * Represent the LIS at index i
         */
        int lis[] = new int[nums.length];

        /**
         * Represent the length of LIS at index i
         */
        int tail[] = new int[nums.length];

        int lisLength = 1;

        lis[0] = nums[0];
        tail[0] = 0;

        for (int i = 1; i < n; i++) {

            int item = nums[i];

            if (item < lis[0]) {
                lis[0] = item;
                tail[i] = 1;
            } else if (item > lis[lisLength - 1]) {
                lis[lisLength++] = item;
                tail[i] = lisLength;
            } else if (item < lis[lisLength - 1]) {
                int index = ceilIndex(lis, 0, lisLength - 1, item);
                lis[index] = item;
                tail[i] = index + 1;
            }
        }

        return tail;

    }


    public static int ceilIndex(int a[], int l, int r, int item) {
        while (l < r) {
            int mid = (l + r) >> 1;

            if (a[mid] >= item)
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }
}
