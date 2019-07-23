package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-24
 * Description: https://www.geeksforgeeks.org/find-minimum-possible-size-of-array-with-given-rules-for-removal/
 * <p>
 * Given an array of numbers and a constant k, minimize size of array with following rules for removing elements.
 * <p>
 * Exactly three elements can be removed at one go.
 * The removed three elements must be adjacent in array, i.e., arr[i], arr[i+1], arr[i+2]. And the second element must be k greater than first and third element must be k greater than second, i.e., arr[i+1] – arr[i] = k and arr[i+2]-arr[i+1] = k.
 * Example:
 * <p>
 * Input: arr[] = {2, 3, 4, 5, 6, 4}, k = 1
 * Output: 0
 * We can actually remove all elements.
 * First remove 4, 5, 6 => We get {2, 3, 4}
 * Now remove 2, 3, 4   => We get empty array {}
 * <p>
 * Input:  arr[] = {2, 3, 4, 7, 6, 4}, k = 1
 * Output: 3
 * We can only remove 2 3 4
 */
public class MinimumPossibleSizeArrayGivenRulesRemovingElements {

    public static void main(String args[]) {
        int arr[] = {2, 3, 4, 7, 6, 4}, k = 1;
        int arr2[] = {2, 3, 4, 5, 6, 4}, k2 = 1;
        int size = minimumPossibleSizeArrayGivenRulesRemovingElements(arr2, k2);
        System.out.println(size);
    }

    private static int minimumPossibleSizeArrayGivenRulesRemovingElements(int[] arr, int k) {
        int dp[][] = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++)
            Arrays.fill(dp[i], -1);

        return minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, 0, arr.length - 1, dp);


    }

    private static int minimumPossibleSizeArrayGivenRulesRemovingElements(int[] arr, int k, int low, int high, int dp[][]) {

        if (high - low + 1 < 3)
            return high - low + 1;

        if (dp[low][high] != -1)
            return dp[low][high];


        //skip the current , by not removig it
        int size = 1 + minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, low + 1, high, dp);

        //consider the current 
        //Try to find all the triplet where we can remove elements

        for (int i = low + 1; i <= high; i++) {

            for (int j = i + 1; j <= high; j++) {

                //can we remove the current triplet, low, i and j
                if (arr[i] - arr[low] == k && arr[j] - arr[i] == k) {

                    //if these low, i and j are long apart from each other. Then there must be some elements in between.
                    //now in order to remove this triplet (low, i, j) we have to remove all the element in between

                    //Remove elements between low and i
                    if (minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, low + 1, i - 1, dp) == 0) {

                        //if they gone, then try all elements between i and j
                        if (minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, i + 1, j - 1, dp) == 0) {

                            //if we able to remove all elements in between low,i,j if any; then we can safely remove this triple;

                            // after removing this triplet, we will left with elements between j to high
                            size = Math.min(size, minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, j + 1, high, dp));
                        }
                    }


                }
            }
        }

        return dp[low][high] = size;


    }

}
