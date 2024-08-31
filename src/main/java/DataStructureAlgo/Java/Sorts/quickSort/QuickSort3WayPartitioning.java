package DataStructureAlgo.Java.Sorts.quickSort;

import DataStructureAlgo.Java.Sorts.Sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Q
 */
public class QuickSort3WayPartitioning implements Sort {

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

        int[] leftRightBoundary = _3WayPartition(num, low, high);
        quickSort(num, low, leftRightBoundary[0] - 1);
        quickSort(num, leftRightBoundary[1] + 1, high);
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    /**
     * In the 3-way partition method, we are indeed finding the left and right boundaries. Here’s a quick summary:
     * <p>
     * Left Boundary (lt): This marks the end of the section where all elements are less than the pivot.
     * Right Boundary (gt): This marks the start of the section where all elements are greater than the pivot.
     * The elements between these boundaries are equal to the pivot. This method is particularly useful when the array contains many duplicate elements, as it efficiently groups them together.
     * <p>
     * Recap of the Process
     * Initialize: lt to the start, gt to the end, and l to the element next to the pivot.
     * Traverse: Move through the array with l:
     * If nums[l] is less than the pivot, swap it with nums[lt] and move both lt and l forward.
     * If nums[l] is greater than the pivot, swap it with nums[gt] and move gt backward.
     * If nums[l] is equal to the pivot, just move l forward.
     * Result: The array is partitioned into three sections:
     * Elements less than the pivot.
     * Elements equal to the pivot.
     * Elements greater than the pivot.
     * This approach ensures that the pivot elements are grouped together, making the sorting process more efficient for arrays with many duplicates.
     * <p>
     * <p>
     * Step-by-Step Tracing with Highlighted Swaps
     * Example
     * Consider the array: [4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4] with pivot 4 (first element).
     * <p>
     * Initial array: [4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4]
     * Pivot: 4
     * Steps:
     * Start: lt = 0, gt = 12, l = 1
     * Iteration 1: nums[l] = 9 (greater than pivot)
     * Swap nums[l] and nums[gt]: [4, **4**, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, **9**]
     * Update gt = 11
     * Iteration 2: nums[l] = 4 (equal to pivot)
     * Move l to 2
     * Iteration 3: nums[l] = 4 (equal to pivot)
     * Move l to 3
     * Iteration 4: nums[l] = 4 (equal to pivot)
     * Move l to 4
     * Iteration 5: nums[l] = 1 (less than pivot)
     * Swap nums[l] and nums[lt]: [**1**, 4, 4, 4, **4**, 9, 4, 4, 9, 4, 4, 1, 9]
     * Update lt = 1, l = 5
     * Iteration 6: nums[l] = 9 (greater than pivot)
     * Swap nums[l] and nums[gt]: [1, 4, 4, 4, 4, **1**, 4, 4, 9, 4, 4, **9**, 9]
     * Update gt = 10
     * Iteration 7: nums[l] = 1 (less than pivot)
     * Swap nums[l] and nums[lt]: [1, **1**, 4, 4, 4, 4, 4, 4, 9, 4, 4, 9, 9]
     * Update lt = 2, l = 6
     * Iteration 8: nums[l] = 4 (equal to pivot)
     * Move l to 7
     * Iteration 9: nums[l] = 4 (equal to pivot)
     * Move l to 8
     * Iteration 10: nums[l] = 9 (greater than pivot)
     * Swap nums[l] and nums[gt]: [1, 1, 4, 4, 4, 4, 4, 4, **4**, 4, 9, 9, 9]
     * Update gt = 9
     * Iteration 11: nums[l] = 4 (equal to pivot)
     * Move l to 9
     * Iteration 12: nums[l] = 4 (equal to pivot)
     * Move l to 10
     * Final Array
     * [1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 9, 9, 9]
     * Pivot indices: 2 to 9
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private int[] _3WayPartition(int[] nums, int low, int high) {


        // to avoid hitting the worst case, assume an array is sorted ascending order
        // randomize pivot/boundary
        int randomIdx = low + random.nextInt(high - low + 1);
        swap(nums, randomIdx, low);

        //leftBoundary denotes the left index of the pivot element
        int leftBoundary = low;

        //rightBoundary denotes the right index of the pivot element
        int rightBoundary = high; //same as pivot

        int pivotElement = nums[leftBoundary];

        int l = low + 1;

        while (l <= rightBoundary) {

            //the current element is lesser than a pivot element, hence the left boundary needs to change ; the element should be on the left side
            if (nums[l] < pivotElement) {

                swap(nums, l, leftBoundary);

                l++;

                leftBoundary++;

            } else if (nums[l] > pivotElement) {
                //the current element is greater than a pivot element, hence the right boundary needs to change; the element should be on the right side
                swap(nums, l, rightBoundary);
                rightBoundary--;
            } else if (nums[l] == pivotElement) {
                //skip such duplicate elements, hence move to next element
                l++;
            }
        }
        return new int[]{leftBoundary, rightBoundary};


    }


}
