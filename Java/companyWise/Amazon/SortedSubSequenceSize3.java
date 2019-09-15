package Java.companyWise.Amazon;

import Java.HelpersToPrint.GenericPrinter;
import Java.NextGreaterElementOnRightSide;
import Java.SmallerElementOnLeftSide;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-11
 * Description: https://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
 * Given an array of n integers, find the 3 elements such that a[i] < a[j] < a[k] and i < j < k in 0(n) time. If there are multiple such triplets, then print any one of them.
 * Examples:
 * <p>
 * Input:  arr[] = {12, 11, 10, 5, 6, 2, 30}
 * Output: 5, 6, 30
 * <p>
 * Input:  arr[] = {1, 2, 3, 4}
 * Output: 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4
 * <p>
 * Input:  arr[] = {4, 3, 2, 1}
 * Output: No such triplet
 * <p>
 * [Amazon]
 */
public class SortedSubSequenceSize3 {
    public static void main(String[] args) {
        test(new int[]{12, 11, 10, 5, 6, 2, 30});
        test(new int[]{1, 2, 3, 4});
        test(new int[]{4, 3, 2, 1});
        test(new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40});

    }

    private static void test(int[] nums) {

        System.out.println();
        GenericPrinter.print(nums);
        int[] seq = sortedSubSequenceSize3(nums);
        if (seq.length == 0)
            System.out.println(" No such triplet");
        else
            for (int i = 0; i < seq.length; i++)
                System.out.print(seq[i] + " ");
    }


    /**
     * {12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40};
     * <p>
     * We need to find the sorted sequence (increasing order) s.t. a[i] < a[j] < a[k] where i<j<k.
     * <p>
     * As it clearly stated the those indices also should be in increasing order.
     * <p>
     * Approach 1:
     * We can take each indices as 'i' and try to find 'j' s.t. j > i and a[j] > a[i] once we find we fix and then try to find 'k' s.t. k > j and a[k] > a[j]
     * As we don't know where this element resides, we need to 3 loops for finding each indices.
     * O(n^3)
     * <p>
     * Approach 2:
     * We can see from the above question output is [5, 6 , 30 ] Or  [ 5, 6,  32 ] and many other.
     * We can fist find an indices where the current element is smaller then previous element [since we need in increasing order] We'll mark this is as 'i'.
     * Now we'll try to find an element which is greater then a[i] and mark it as 'j' and try to find 'k'. But since probably chooses j may not gurentee it that it will give us the correct
     * sequence as this j might be the largest element in the array, which means we'll never find 'k' on right side of j. Same could happen with 'i'. Which become same as approach 1.
     * <p>
     * But doing above, few thing could be noted that,
     * 1. if we know what are the element at current indices which is smaller then this and previous to current indices [ if no one we'll use -1 ] at index 'i'
     * 2. Similarly what are the element at current indices of which is greater then this  and next to current indices [ if no one we'll use -1 ] at index 'k'
     * Then finding j would be easy as the index at which there are some smaller element and greater element are exist could be our 'j' indices.
     * <p>
     * <p>
     * Algorithm:
     * 1. smaller[i] : represent the index of element which is smaller then current element otherwise -1 [ left to Right] As 'i'
     * 2. greater[i] : represent the index of element which is greater then current element otherwise -1 [ right to left] As 'k'
     * 3. Iterate over array, find a index 'j' s.t. smaller and greater is not -1.
     * <p>
     * Complexity O(2*n) / (2*n) = > O(n)/O(n)
     *
     * @param nums
     * @return Print any elements
     */
    public static int[] sortedSubSequenceSize3(int nums[]) {

        if (nums == null || nums.length == 0 || nums.length < 3)
            return nums;

        int n = nums.length;
        int smaller[] = new int[n];
        int greater[] = new int[n];


        smaller[0] = -1; //there will be no element which is smaller then current on left side
        greater[n - 1] = -1; //there will be no element which is greater then current on right side

        int small = 0;
        int bigger = n - 1;


        for (int i = 1, k = n - 2; i < n && k >= 0; i++, k--) {
            int elementAtI = nums[i];
            int elementAtk = nums[k];


            //============ smaller -> i ==============
            //elements are in increasing order
            if (nums[small] >= elementAtI) {
                small = i;
                smaller[i] = -1;
            } else {
                smaller[i] = small;
            }


            //============ greater -> k ==============
            //elements are in decreasing order
            if (elementAtk >= nums[bigger]) {
                bigger = k;
                greater[k] = -1;
            } else
                greater[k] = bigger;

        }

        for (int j = 1; j < n - 1; j++) {

            //this is our j
            if (smaller[j] != -1 && greater[j] != -1) {
                return new int[]{nums[smaller[j]], nums[j], nums[greater[j]]};
            }


        }
        return new int[0];
    }

    /**
     * Print nearest elements
     *
     * @param nums
     * @return
     */
    public static int[] sortedSubSequenceSize3V2(int nums[]) {

        if (nums == null || nums.length == 0 || nums.length < 3)
            return nums;

        int n = nums.length;
        int smaller[] = SmallerElementOnLeftSide.immediateSmallerElementOnLeftSide(nums);
        int greater[] = NextGreaterElementOnRightSide.immediateGreaterElementOnRightSide(nums);


        for (int j = 1; j < n - 1; j++) {

            //this is our j
            if (smaller[j] != -1 && greater[j] != -1) {
                return new int[]{nums[smaller[j]], nums[j], nums[greater[j]]};
            }


        }
        return new int[0];
    }
}
