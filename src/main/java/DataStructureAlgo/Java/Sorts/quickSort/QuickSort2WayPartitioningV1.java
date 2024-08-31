package DataStructureAlgo.Java.Sorts.quickSort;

import DataStructureAlgo.Java.Sorts.Sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Q
 */
public class QuickSort2WayPartitioningV1 implements Sort {

    Random random = new Random();

    @Override
    public int[] sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        int[] result = Arrays.copyOf(arr, arr.length);
        quickSort(result, 0, result.length - 1);

        return result;
    }

    private void quickSort(int[] num, int low, int high) {
        if (low >= high)
            return;

        int pivot = _2WayPartition(num, low, high);
        quickSort(num, low, pivot - 1);
        quickSort(num, pivot + 1, high);
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    /**
     * This algo works in O(n) time.
     * This is also known as 2-way partition as we are partition array at pivot on two sides.
     * <p>
     * Run flow;
     * nums  = { 4,2,3,8,2,6,9} low = 0, high = 6
     * l = 1, r = 6, pivot = 0; l < r
     * //skip all elements that are less than pivot
     * nums[l] <= nums[pivot] => nums[1] <= nums[0] => 2 < 4 => l++
     * l will eventually reach at l=3
     * <p>
     * //skip all elements that are greater than pivot
     * nums[r] > nums[pivot] => nums[6] <= nums[0] => 9 > 4 => r--
     * r will eventually reach at r = 4
     * <p>
     * here l < r => swap l,r
     * { 4,2,3,2,8,6,9}
     * l=3, r=4 =>  l = 4 & r = 3
     * <p>
     * l > r; break;
     * swap pivot , r
     * { 2,2,3,4,8,6,9}
     * partition point = 3
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private int _2WayPartition(int[] nums, int low, int high) {

        int l = low + 1;
        int r = high;

        // to avoid hitting the worst case, assume an array is sorted ascending order
        // randomize pivot/boundary
        int randomIdx = low + random.nextInt(high - low + 1); // to avoid hitting the worst case, assume an array is sorted ascending order
        swap(nums, randomIdx, low);

        int pivot = low;

        while (l < r) {

            //skip all the element towards right, which are lesser/equal to a pivot element
            while (l < high && nums[l] <= nums[pivot])
                l++;

            //skip all the element towards left, which are greater than a pivot element
            while (r > low && nums[r] > nums[pivot])
                r--;

            //if there is a gap b/w l and r, means our pivot will be siting in b/w of it. hence swap it and close the gap
            if (l < r)
                swap(nums, l, r);
        }

        //if the gap is closed, then put pivot its right position which is on r
        swap(nums, pivot, r);
        return r;

    }


}
