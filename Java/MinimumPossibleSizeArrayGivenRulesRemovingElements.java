package Java;

import Java.HelpersToPrint.GenericPrinter;

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

    public static void main(String []args) {
        test(new int[]{2, 3, 4, 5, 6, 4}, 1, 0);
        test(new int[]{2, 3, 4, 7, 6, 4}, 1, 3);
    }

    private static void test(int a[], int k, int expected) {
        GenericPrinter.print(a);
        System.out.println(minimumPossibleSizeArrayGivenRulesRemovingElements(a, k) + " expected " + expected);
    }

    private static int minimumPossibleSizeArrayGivenRulesRemovingElements(int[] arr, int k) {
        int dp[][] = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++)
            Arrays.fill(dp[i], -1);

//        return minimumPossibleSizeArrayGivenRulesRemovingElementsBacktracking(arr, k, 0, arr.length - 1);
        return minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, 0, arr.length - 1, dp);


    }

    /**
     * For every element arr[i] there are two possibilities
     * 1) Either the element is not removed.
     * 2) OR element is removed (if it follows rules of removal). When an element is removed, there are again two possibilities.
     * …..a) It may be removed directly, i.e., initial arr[i+1] is arr[i]+k and arr[i+2] is arr[i] + 2*k.
     * …..b) There exist x and y such that arr[x] – arr[i] = k, arr[y] – arr[x] = k, and subarrays “arr[i+1…x-1]” & “arr[x+1…y-1]” can be completely removed. where x > i and y > x
     * <p>
     * Below is recursive algorithm based on above idea.
     *
     *
     * Cache it for overlapping problems
     *
     * @param arr
     * @param k
     * @param low
     * @param high
     * @param dp
     * @return
     */
    private static int minimumPossibleSizeArrayGivenRulesRemovingElements(int[] arr, int k, int low, int high, int dp[][]) {

        /**
         * if there is only less then 3 elements, we can't remove it
         */
        if (high - low + 1 < 3)
            return high - low + 1;

        /**
         * Has we solved this problem already
         */
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

                            // after removing this triplet, we will left with elements between j to high as all the element from low to j has been removed
                            size = Math.min(size, minimumPossibleSizeArrayGivenRulesRemovingElements(arr, k, j + 1, high, dp));
                        }
                    }


                }
            }
        }

        return dp[low][high] = size;


    }

    /**
     * For every element arr[i] there are two possibilities
     * 1) Either the element is not removed.
     * 2) OR element is removed (if it follows rules of removal). When an element is removed, there are again two possibilities.
     * …..a) It may be removed directly, i.e., initial arr[i+1] is arr[i]+k and arr[i+2] is arr[i] + 2*k.
     * …..b) There exist x and y such that arr[x] – arr[i] = k, arr[y] – arr[x] = k, and subarrays “arr[i+1…x-1]” & “arr[x+1…y-1]” can be completely removed. where x > i and y > x
     * <p>
     * Below is recursive algorithm based on above idea.
     *
     * @param arr
     * @param k
     * @param low
     * @param high
     * @return
     */
    private static int minimumPossibleSizeArrayGivenRulesRemovingElementsBacktracking(int[] arr, int k, int low, int high) {

        /**
         * if there is only less then 3 elements, we can't remove it
         */
        if (high - low + 1 < 3)
            return high - low + 1;


        //skip the current character
        int size = 1 + minimumPossibleSizeArrayGivenRulesRemovingElementsBacktracking(arr, k, low + 1, high);

        //consider the current character
        //Try to find all the triplet where we can remove elements

        for (int i = low + 1; i <= high; i++) {

            for (int j = i + 1; j <= high; j++) {

                //can we remove the current triplet, low, i and j
                if (arr[i] - arr[low] == k && arr[j] - arr[i] == k) {

                    //if these low, i and j are long apart from each other. Then there must be some elements in between.
                    //now in order to remove this triplet (low, i, j) we have to remove all the element in between

                    //Remove elements between low and i
                    if (minimumPossibleSizeArrayGivenRulesRemovingElementsBacktracking(arr, k, low + 1, i - 1) == 0) {

                        //if they gone, then try all elements between i and j
                        if (minimumPossibleSizeArrayGivenRulesRemovingElementsBacktracking(arr, k, i + 1, j - 1) == 0) {

                            //if we able to remove all elements in between low,i,j if any; then we can safely remove this triple;

                            // after removing this triplet, we will left with elements between j to high
                            size = Math.min(size, minimumPossibleSizeArrayGivenRulesRemovingElementsBacktracking(arr, k, j + 1, high));
                        }
                    }


                }
            }
        }

        return size;


    }

}
