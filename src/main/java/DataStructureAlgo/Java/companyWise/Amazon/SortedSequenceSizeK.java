package DataStructureAlgo.Java.companyWise.Amazon;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.nonleetcode.NextGreaterElementOnRightSide;
import  DataStructureAlgo.Java.nonleetcode.SmallerElementOnLeftSide;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-11
 * Description:
 * Given an array of n integers, find the k elements such that
 * a[0] < a[1] .... < a[k-1] and indices are in increasing order
 * <p>
 * <p>
 * * Examples:
 * * <p>
 * * Input:  arr[] = {12, 11, 10, 5, 6, 2, 30} k =3
 * * Output: 5, 6, 30
 * * <p>
 * * Input:  arr[] = {12, 11, 10, 5, 6, 2, 30} k =4
 * * output: No Found
 * * Input:  arr[] = {1, 2, 3, 4}: k=3
 * * Output: 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4
 * * <p>
 * * Input:  arr[] = {4, 3, 2, 1} : k=4
 * * Output: No Found
 * * input: {12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40} : k=4
 * * output:[ 5 , 6, 30, 40] is one of the answer.
 * <p>
 * This is generalization of below question
 * {@link SortedSubSequenceSize3}
 */
public class SortedSequenceSizeK {

    public static void main(String[] args) {
        test(new int[]{12, 11, 10, 5, 6, 2, 30}, 3);
        test(new int[]{1, 2, 3, 4}, 3);
        test(new int[]{4, 3, 2, 1}, 3);
        test(new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40}, 4);
        test(new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40}, 5);
        test(new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40}, 6);


    }


    private static void test(int[] nums, int k) {

        System.out.println();
        GenericPrinter.print(nums);

        SortedSequenceSizeKUsingMemory memory = new SortedSequenceSizeKUsingMemory();

        int[] seq = memory.sortedSubSequenceSizeK(nums, k);
        if (seq.length == 0 || seq[0] == -1)
            System.out.println(" Not found");
        else
            for (int i = 0; i < seq.length; i++)
                System.out.print(nums[seq[i]] + " ");
    }

}

class SortedSequenceSizeKUsingMemory {

    /**
     * Idea is similar to {@link SortedSubSequenceSize3}.
     * The only change here we require that once we find the elements, we keep taking more greater elements until we reach k elements.
     * <p>
     * Important things to note here, k could be anything from 1...n.
     * so if we need only k=1, we can return any number or if we need k = 2, then only two numbers in increasing order
     * while for k = 4, we need to choose more number from greater[] array
     * <p>
     * <p>
     * * Algorithm:
     * * 1. smaller[i] : represent the index of element which is smaller then current element otherwise -1 [ left to Right] As 'i'
     * * 2. greater[i] : represent the index of element which is greater then current element otherwise -1 [ right to left] As 'k'
     * * 3. Iterate over array, find a index 'j' s.t. smaller and greater is not -1.
     * * 4. Pick more elements from greater [] array to fill more than 3 elements
     *
     * @param nums
     * @param size
     * @return index of elements that participate in sorted seq of 'size' Size. If not found, then array of 0 size
     * <p>
     * {@link SmallerElementOnLeftSide}
     * {@link NextGreaterElementOnRightSide}
     */
    public int[] sortedSubSequenceSizeK(int nums[], int size) {

        if (nums == null || nums.length == 0 || size > nums.length)
            return null;


        int smaller[] = SmallerElementOnLeftSide.immediateSmallerElementOnLeftSide(nums);
        int greater[] = NextGreaterElementOnRightSide.immediateGreaterElementOnRightSide(nums);


        return findKElements(nums, smaller, greater, size);
    }

    private int[] findKElements(int[] nums, int[] smaller, int[] greater, int k) {
        int n = nums.length;

        int kElements[] = new int[k];
        Arrays.fill(kElements, -1);
        int kIndex = 0;
        int j;
        for (j = 1; j < n - 1; j++) {

            //this is our j's
            if (smaller[j] != -1 && greater[j] != -1) {

                /**
                 * Choose elements based on k requirement
                 * if k is <= 3, simply choose 3 elements.
                 */

                if (k >= 1)
                    kElements[kIndex++] = smaller[j];

                if (k >= 2)
                    kElements[kIndex++] = j;

                if (k >= 3)
                    kElements[kIndex++] = greater[j];

                break;

            }


        }


        /**
         * Pick next remaining elements. Make sure duplicate elements are not get picked
         */
        while (j < n - 1 && kIndex < k) {
            if (greater[j] == -1)
                return new int[0];
            if (kElements[kIndex - 1] != greater[j])
                kElements[kIndex++] = greater[j];
            j++;
        }

        if (kIndex != k)
            return new int[0];

        return kElements;
    }
}
