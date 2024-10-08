package DataStructureAlgo.Java.nonleetcode;


import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Random;

public class Sorts {

    class MergeSort {
        public MergeSort() {
        }

        public void mergeSort(int[] input, int[] temp, int left, int right) {

            if (left >= right)
                return;
            int mid = left + (right - left) / 2;
            mergeSort(input, temp, left, mid);
            mergeSort(input, temp, mid + 1, right);
            merge(input, temp, left, mid + 1, right);

        }

        private void merge(int[] input, int[] temp, int left, int mid, int right) {
            if ((left == mid && mid == right) || left > right)
                return;

            int pos = left;
            int left_end = mid - 1;
            int size = right - left + 1;

            while (left <= left_end && mid <= right) {
                if (input[left] < input[mid])
                    temp[pos++] = input[left++];
                else
                    temp[pos++] = input[mid++];

            }

            while (left <= left_end)
                temp[pos++] = input[left++];

            while (mid <= right)
                temp[pos++] = input[mid++];

            for (int i = 0; i < size; i++) {
                input[right] = temp[right];
                right--;
            }

        }
    }

    static class QuickSort {
        public QuickSort() {
        }

        public void quickSort(int[] input, int low, int high) {
            if (low >= high)
                return;
            if (input == null || input.length == 0)
                return;

            int pivot = partition(input, low, high);
            if (pivot == -1)
                return;
            quickSort(input, low, pivot - 1);
            quickSort(input, pivot + 1, high);
        }

        private int partition(int[] input, int low, int high) {
            if (low >= high)
                return -1;
            if (input == null || input.length == 0)
                return -1;

            int left = low;
            int right = high;
            while (left < right) {

                while (left < high && input[left] <= input[low])
                    left++;
                while (right > low && input[right] > input[low])
                    right--;

                if (left < right) {
                    int temp = input[left];
                    input[left] = input[right];
                    input[right] = temp;
                }

            }

            int pivotItem = input[low];
            input[low] = input[right];
            input[right] = pivotItem;

            return right;
        }

    }


    static class QuickSortV2 {
        Random random = new Random();
        public QuickSortV2() {
        }

        public void quickSort(int[] input, int low, int high) {
            if (low >= high)
                return;
            if (input == null || input.length == 0)
                return;

            int pivot = _2WayPartitionV2(input, low, high);
            quickSort(input, low, pivot - 1);
            quickSort(input, pivot + 1, high);
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
        private int _2WayPartitionV2(int[] nums, int low, int high) {

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

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println("Input");
        int[] input = {2, 3, 5, 6, 4, 8, 6, 3, 8, 7, 8};
        int[] temp = new int[input.length];
        CommonMethods.print(input);


        Sorts s = new Sorts();
        MergeSort mergeSort = s.new MergeSort();

        mergeSort.mergeSort(input, temp, 0, input.length - 1);
        System.out.println("merge sort : ");
        CommonMethods.print(input);


        System.out.println("\n\nQuickSort");
        int[] input2 = {2, 3, 6, 2, 4, 8, 0, 3, 8, 7, 8};
        CommonMethods.print(input2);

        System.out.println();
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(input2, 0, input2.length - 1);
        CommonMethods.print(input2);


    }

}
