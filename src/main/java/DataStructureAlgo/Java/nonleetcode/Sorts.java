package DataStructureAlgo.Java.nonleetcode;


import DataStructureAlgo.Java.helpers.CommonMethods;

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

    class QuickSort {
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
            int pivot = low;
            int item = input[pivot];
            int right = high;
            while (left < right) {

                while (left < high && input[left] <= input[pivot])
                    left++;
                while (right > 0 && input[right] > input[pivot])
                    right--;

                if (left < right) {
                    int temp = input[left];
                    input[left] = input[right];
                    input[right] = temp;
                }

            }
            input[low] = input[right];
            input[right] = item;

            return right;
        }

    }

    public static void main(String[] args) {
        System.out.println("SDF");
        int[] input = {2, 3, 5, 6, 4, 8, 6, 3, 8, 7, 8};
        int[] temp = new int[input.length];
        CommonMethods.print(input);


        Sorts s = new Sorts();
        MergeSort mergeSort = s.new MergeSort();

        mergeSort.mergeSort(input, temp, 0, input.length - 1);
        System.out.println();
        CommonMethods.print(input);


        System.out.println("\n\nQuickSort");
        int[] input2 = {2, 3, 6, 2, 4, 8, 0, 3, 8, 7, 8};
        CommonMethods.print(input2);

        System.out.println();
        QuickSort quickSort = s.new QuickSort();
        quickSort.quickSort(input2, 0, input2.length - 1);
        CommonMethods.print(input2);


    }

}
