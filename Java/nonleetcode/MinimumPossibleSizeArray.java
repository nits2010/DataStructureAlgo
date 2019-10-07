package Java.nonleetcode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/01/19
 * Description:
 * https://www.geeksforgeeks.org/find-minimum-possible-size-of-array-with-given-rules-for-removal/
 */
public class MinimumPossibleSizeArray {


    /**
     * Input: arr[] = {2, 3, 4, 5, 6, 4}, k = 1
     * Output: 0
     * We can actually remove all elements.
     * First remove 4, 5, 6 => We get {2, 3, 4}
     * Now remove 2, 3, 4   => We get empty array {}
     */


    public static void main(String []args) {

        int[] arr = {2, 3, 4, 5, 6, 4};
        int k = 1;


        System.out.println(minimumPossibleSizeArray(arr, k));
    }

    private static int minimumPossibleSizeArray(int[] arr, int k) {
        /**
         * There are two possibilities for each element.
         * 1. Either we don't remove this element
         * 2. Or we remove this element ( if and only if it adhara rule of removal.
         * * if we remove;
         * ** 2.1 Either we can directly remove triplet
         * ** 2.2 Or we can't remove the triplet (example: 4,5,6,4 and index is on 5, Since 5,6,4 don't obey rule so can't remove them)
         * **   2.2.1, if we can't then we need to find a 'x' and 'y'; a[x] -a[i] = k and a[y] - a[x] = k ->
         *          such that a[i+1....x-1], a[x+1....y] can directly be remove
         */
        int dp[][] = new int[arr.length][arr.length];

        return minimumPossibleSizeArray(arr, k, 0, arr.length - 1, dp);


    }

    private static int minimumPossibleSizeArray(int[] arr, int k, int low, int high, int dp[][]) {


        //If array has only 2 element then the minimum size of array is it self only.
        if (high - low + 1 < 3)
            return high - low + 1;


        //If this problem already computed
        if (dp[low][high] != 0) {
            return dp[low][high];
        }


        //Case 1: If we don't remove the current element; then the minimum size of the array would be at least 1
        int result = 1 + minimumPossibleSizeArray(arr, k, low + 1, high, dp);


        //Case 2: if we remove the current element then we need to remove the triplet

        //Either remove the triplet directly or search for x and y
        for (int i = low + 1; i < high; i++) {

            for (int j = i + 1; j <= high; j++) {

                // Check if this triplet follows the given rules of
                // removal. And elements between 'low' and 'i' , and
                //  between 'i' and 'j' can be recursively removed.
                if (arr[i] - arr[low] == k && arr[j] - arr[i] == k &&
                        minimumPossibleSizeArray(arr, k, low + 1, i - 1, dp) == 0 &&
                        minimumPossibleSizeArray(arr, k, i + 1, j - 1, dp) == 0) {

                    System.out.println("low : " + low + " , i : " + i + " , j : " + j + " , high: " + high);
                    result = Math.min(result, minimumPossibleSizeArray(arr, k, j + 1, high, dp));
                }
            }
        }

        return dp[low][high] = result;

    }
}
