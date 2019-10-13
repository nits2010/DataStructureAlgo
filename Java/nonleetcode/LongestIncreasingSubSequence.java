package Java.nonleetcode;

import Java.helpers.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-21
 * Description: https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
 * <p>
 * We have discussed Overlapping Subproblems and Optimal Substructure properties.
 * <p>
 * Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem that can be solved using Dynamic Programming.
 * The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order. For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 * longest-increasing-subsequence
 * <p>
 * More Examples:
 * <p>
 * Input  : arr[] = {3, 10, 2, 1, 20}
 * Output : Length of LIS = 3
 * The longest increasing subsequence is 3, 10, 20
 * <p>
 * Input  : arr[] = {3, 2}
 * Output : Length of LIS = 1
 * The longest increasing subsequences are {3} and {2}
 * <p>
 * Input : arr[] = {50, 3, 10, 7, 40, 80}
 * Output : Length of LIS = 4
 * The longest increasing subsequence is {3, 7, 40, 80}
 * <p>
 * https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 * Longest increasing sub-sequence in O(nlogn) time
 */
public class LongestIncreasingSubSequence {


    public static void main(String []args) {
        int items[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int items2[] = {2, 5, 3, 7, 11, 8, 10, 13, 6};

        int items3[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};


        lengthOfLIS(items);
        lengthOfLIS(items2);
        lengthOfLIS(items3);
    }

    private static void lengthOfLIS(int[] items) {

        ILongestIncreasingSubSequence nlogn = new LongestIncreasingSubSequenceNLogN();
        ILongestIncreasingSubSequence poly = new LongestIncreasingSubSequencePoly();

        System.out.println("Nlogn " + nlogn.lengthOfLIS(items));
        System.out.println("Poly " +  poly.lengthOfLIS(items));

    }

}

interface ILongestIncreasingSubSequence {
    int lengthOfLIS(int nums[]);
}

/**
 * Let arr[0..n-1] be the input array and L(i) be the length of the LIS ending at index i such that arr[i] is the last element of the LIS.
 * Then, L(i) can be recursively written as:
 * L(i) = 1 + max( L(j) ) where 0 <= j < i and arr[j] < arr[i]; or
 * L(i) = 1, if no such j exists.
 * To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.
 * Thus, we see the LIS problem satisfies the optimal substructure property as the main problem can be solved using solutions to subproblems.
 */
class LongestIncreasingSubSequencePoly implements ILongestIncreasingSubSequence {

    @Override
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1)
            return 1;

        int n = nums.length;
        int lis[] = new int[n];
        int i, j, max = 0;

        /* Initialize LIS values for all indexes */

        Arrays.fill(lis, 1);

        /* Compute optimized LIS values in bottom up manner */
        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++)
                if (nums[i] > nums[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

            max = Math.max(lis[i], max);
        }

        return max;
    }
}

class LongestIncreasingSubSequenceNLogN implements ILongestIncreasingSubSequence {

    static class LIS {
        int length;
        int[] lis;
    }

    public int lengthOfLIS(int nums[]) {
        System.out.println("\n input ");
        GenericPrinter.print(nums);

        System.out.println("\n LIS -> ");
        LIS lis = lengthOfLISHelper(nums);

        GenericPrinter.print(lis.lis);

        return lis.length;
    }

    private LIS lengthOfLISHelper(int nums[]) {

        if (nums == null || nums.length == 0)
            return null;
        int n = nums.length;
        int lis[] = new int[n];
        int lisLength = 1;


        lis[0] = nums[0];

        for (int i = 1; i < n; i++) {

            int item = nums[i];

            //if smallest among, then start a new subsequence
            if (item < lis[0])
                lis[0] = nums[i];

            else if (item > lis[lisLength - 1]) {
                //if this is largest element in among last element of all lengthOfLIS, then extend this lengthOfLIS with new element
                lis[lisLength++] = item;
            } else if (item < lis[lisLength - 1]) {
                //if this is smaller then among last element of all lengthOfLIS, find the right place, copy that list,
                // replace the last element and discard the same length lengthOfLIS

                int index = ceilIndex(lis, 0, lisLength - 1, item);
                lis[index] = item;

            }
        }

        LIS obj = new LIS();
        obj.length = lisLength;
        obj.lis = lis;
        return obj;

    }

    public int ceilIndex(int[] a, int l, int r, int item) {
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
