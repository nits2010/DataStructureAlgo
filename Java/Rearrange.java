package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-25
 * Description:
 */
public class Rearrange {

    // A simple method to rearrange
    // 'arr[0..n-1]' so that 'arr[j]'
    // becomes 'i' if 'arr[i]' is 'j'
    static void rearrange(int arr[], int n)
    {
        for (int i = 0; i < n; i++) {

            // retrieving old value and
            // storing with the new one
            arr[arr[i] % n] += i * n;
        }

        for (int i = 0; i < n; i++) {

            // retrieving new value
            arr[i] /= n;
        }
    }

    // A utility function to print
    // contents of arr[0..n-1]
    static void printArray(int arr[], int n)
    {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    // Drive code
    public static void main(String[] args)
    {
        int arr[] = { 2, 0, 1, 4, 5, 3 };
        int n = arr.length;

        System.out.println("Given array is : ");
        printArray(arr, n);

        rearrange(arr, n);

        System.out.println("Modified array is :");
        printArray(arr, n);
    }
}
