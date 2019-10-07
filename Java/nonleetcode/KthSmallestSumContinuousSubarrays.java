package Java.nonleetcode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-18
 * Description:https://www.geeksforgeeks.org/kth-smallest-sum-of-continuous-subarrays-of-positive-numbers/
 * <p>
 * Given an sorted array of positive numbers our tasks is to find the kth smallest sum of continuous subarray.
 * <p>
 * Examples:
 * <p>
 * Input : a[] = {1, 2, 3, 4, 5, 6}
 * k = 4
 * Output : 3
 * Explanation : List of sorted subarray sum: {1, 2, 3, 3, 4, 5, 5, 6, 6, 7, 9, 9, 10, 11, 12, 14, 15, 15, 18, 20, 21}. 4th smallest element is 3.
 * <p>
 * Input : a[] = {1, 2, 3, 4, 5, 6}
 * k = 13
 * Output: 10
 */
public class KthSmallestSumContinuousSubarrays {

    public static void main(String []args) {
        int a[] = {1, 2, 3, 4, 5, 6};

        System.out.println(kthSmallestSumContinuousSubarrays(a, 4));
        System.out.println(kthSmallestSumContinuousSubarrays(a, 13));

    }

    private static int kthSmallestSumContinuousSubarrays(int[] a, int k) {

        int prefix[] = new int[a.length];

        prefix[0] = a[0];
        for (int i = 1; i < a.length; i++)
            prefix[i] = prefix[i - 1] + a[i];

        int maxSum = prefix[a.length - 1];

        int start = 0;
        int end = maxSum;
        int ans = 0;
        while (start <= end) {

            int mid = (start + end) >> 1;

            if (rank(prefix, mid) >= k) {
                ans = mid;
                end = mid - 1;
            } else start = mid + 1;
        }

        return ans;

    }

    private static int rank(int[] prefix, int mid) {

        int sumBeforeIthIndex = 0;
        int rank = 0;
        int count = 0;
        for (int i = 0; i < prefix.length; i++) {

            count = upperBound(prefix, sumBeforeIthIndex + mid);


            count -= i;
            rank += count;
            sumBeforeIthIndex = prefix[i];
        }


        return rank;
    }

    /**
     * Return the index of element which is greater then <x> if found
     * otherwise array length.
     *
     * @param prefix
     * @param x
     * @return
     */
    private static int upperBound(int prefix[], int x) {

        int start = 0;
        int end = prefix.length - 1;
        int ans = -1;
        while (start <= end) {

            int mid = (start + end) >> 1;

            if (prefix[mid] == x) {
                ans = mid + 1;
                break;
            }

            if (prefix[mid] > x) {
                ans = mid;
                end = mid - 1;
            } else
                start = mid + 1;

        }

        return ans == -1 ? prefix.length : ans;
    }
}
