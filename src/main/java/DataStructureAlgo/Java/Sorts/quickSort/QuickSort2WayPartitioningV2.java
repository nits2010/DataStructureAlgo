package DataStructureAlgo.Java.Sorts.quickSort;

import DataStructureAlgo.Java.Sorts.Sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Q
 */
public class QuickSort2WayPartitioningV2 implements Sort {

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
     * This is another way of doing two-way partition.
     * <p>
     * https://www.youtube.com/watch?v=dOytFZFYbvo
     * https://www.youtube.com/watch?v=pM-6r5xsNEY
     * <p>
     * <p>
     * num = [ 4 , 5 , 3 , 8 , 1 ], low=0, high=4
     * l = 1, boundary = 0, pivotElement = 4
     * num[l] <= pivotElement => num[1] <= 4 => 5 > 4 => no swap -> l++
     * l = 2, boundary = 0, pivotElement = 4
     * num[l] <= pivotElement => num[2] <= 4 => 3 < 4 => boundary++ = 1 -> swap (l,boundary)
     * num = [ 4 , 3 , 5 , 8 , 1 ]
     * l = 3, boundary = 1, pivotElement = 4
     * num[l] <= pivotElement => num[3] <= 4 => 8 > 4 => no swap -> l++
     * l = 4, boundary = 1, pivotElement = 4
     * num[l] <= pivotElement => num[4] <= 4 => 1 <= 4 =>  boundary++ = 2 -> swap (l,boundary)
     * num = [ 4 , 3 , 1 , 8 , 5 ]
     * l = 5, boundary = 1, pivotElement = 4 ; break
     * <p>
     * swap (low, boundary) -> swap (0, 2)
     * num = [ 1 , 3 , 4 , 8 , 5 ] pivot = 2 (boundary)
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private int _2WayPartition(int[] nums, int low, int high) {

        int l = low + 1;

        // to avoid hitting the worst case, assume an array is sorted ascending order
        // randomize pivot/boundary
        int randomIdx = low + random.nextInt(high - low + 1);
        swap(nums, randomIdx, low);

        int boundary = low; //same as pivot
        int pivotElement = nums[boundary];

        while (l <= high) {
            if (nums[l] <= pivotElement) {
                boundary++; // move boundary
                swap(nums, l, boundary);
            }
            l++;
        }
        swap(nums, boundary, low);
        return boundary;


    }


}
