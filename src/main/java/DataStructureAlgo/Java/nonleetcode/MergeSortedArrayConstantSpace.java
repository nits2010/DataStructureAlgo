package DataStructureAlgo.Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-21
 * Description: https://www.geeksforgeeks.org/efficiently-merging-two-sorted-arrays-with-o1-extra-space/
 * Efficiently merging two sorted arrays with O(1) extra space
 * Given two sorted arrays, we need to merge them in O((n+m)*log(n+m)) time with O(1) extra space into a sorted array, when n is the size of the first array, and m is the size of the second array.
 * <p>
 * Example:
 * <p>
 * Input: ar1[] = {10};
 * ar2[] = {2, 3};
 * Output: ar1[] = {2}
 * ar2[] = {3, 10}
 * <p>
 * Input: ar1[] = {1, 5, 9, 10, 15, 20};
 * ar2[] = {2, 3, 8, 13};
 * Output: ar1[] = {1, 2, 3, 5, 8, 9}
 * ar2[] = {10, 13, 15, 20}
 */
public class MergeSortedArrayConstantSpace {

    public static void main(String[] args) {

        usingInsertionSort(new int[]{10, 27, 38, 43, 82}, new int[]{3, 9});
        usingShellSort(new int[]{10, 27, 38, 43, 82}, new int[]{3, 9});
    }

    private static void usingShellSort(int[] a, int[] b) {
        System.out.println("\n\nUsing Shell sort \n");
        MergeUsingShellSort sort = new MergeUsingShellSort();


        System.out.println("Before sorting");
        print(a, b);

        sort.merge(a, b, a.length, b.length);

        System.out.println("\n\nAfter sorting");

        print(a, b);

    }

    private static void usingInsertionSort(int a[], int b[]) {
        System.out.println("Using Insertion sort");
        MergeUsingInsertionSort sort = new MergeUsingInsertionSort();


        System.out.println("Before sorting");
        print(a, b);

        sort.merge(a, b, a.length, b.length);

        System.out.println("\n\nAfter sorting");

        print(a, b);

    }

    static void print(int a[], int b[]) {
        System.out.print("First Array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();

        System.out.print("Second Array: ");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
    }
}

class MergeUsingShellSort {


    public void merge(int[] a, int[] b, int n, int m) {

        int gap = gap(n + m); //shall sort gap;
        int i, j;

        for (; gap > 0; gap = gap(gap)) {

            //Compare first array
            for (i = 0; i + gap < n; i++) {
                if (a[i] > a[i + gap])
                    swap(a, i, i + gap);
            }

            //compare element in both array
            //Find the index at which we need to compare both element;

            //the index in second array would depend on first array length and gap.
            j = gap > n ? gap - n : 0;

            for (; i < n && j < m; i++, j++) {

                if (a[i] > b[j]) {
                    swap(a, b, i, j);
                }
            }

            //if any element left to compare in second array
            if (j > 0) {

                for (j = 0; j + gap < m; j++) {
                    if (b[j] > b[j + gap])
                        swap(b, j, j + gap);
                }
            }
        }

    }

    private void swap(int a[], int b[], int i, int j) {
        int temp = a[i];
        a[i] = b[j];
        b[j] = temp;
    }

    private void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int gap(int g) {
        if (g <= 1)
            return 0;
//        return g / 2 + g % 2; //or (g+1)/2
        return (g + 1) / 2;
    }

}

class MergeUsingInsertionSort {


    public void merge(int a[], int b[], int n, int m) {

        int j = m - 1;

        while (j >= 0) {


            if (a[n - 1] > b[j]) {

                int backup = a[n - 1];
                int k = n - 2;

                while (k >= 0 && a[k] > b[j]) {
                    a[k + 1] = a[k];
                    k--;

                }

                if (k != n - 2) {
                    a[k + 1] = b[j];
                    b[j] = backup;
                }


            }


            j--;
        }
    }
}