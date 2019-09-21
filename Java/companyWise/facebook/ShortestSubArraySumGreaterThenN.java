package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-17
 * Description: https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
 * <p>
 * Given an array of integers and a number x, find the smallest subarray with sum greater than the given value.
 * <p>
 * Examples:
 * arr[] = {1, 4, 45, 6, 0, 19}
 * x  =  51
 * Output: 3
 * Minimum length subarray is {4, 45, 6}
 * <p>
 * arr[] = {1, 10, 5, 2, 7}
 * x  = 9
 * Output: 1
 * Minimum length subarray is {10}
 * <p>
 * arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}
 * x = 280
 * Output: 4
 * Minimum length subarray is {100, 1, 0, 200}
 * <p>
 * arr[] = {1, 2, 4}
 * x = 8
 * Output : Not Possible
 * Whole array sum is smaller than 8.
 */
public class ShortestSubArraySumGreaterThenN {

    public static void main(String []args) {

        int arr[] = {1, 4, 45, 6, 0, 19};
        test(arr, 51);

        int arr2[] = {1, 10, 5, 2, 7};
        test(arr2, 10);

        int arr3[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
        test(arr3, 280);

        int arr4[] = {1, 2, 4};
        test(arr4, 8);

        int arr5[] = {-8, 1, 4, 2, -6};
        test(arr5, 6);

        int arr6[] = {-8, 1, 4, -2, -6};
        test(arr6, 6);

        int arr7[] = {-8, 1, 4, -2, -6};
        test(arr7, -8);

        int arr8[] = {-8, 1, 4, -2, -6};
        test(arr8, -6);

        int arr9[] = {-8, -1, 4, -2, -6};
        test(arr9, -8);

        int arr10[] = {-1, -3, -4, -2, -6};
        test(arr10, -8);

        int arr11[] = {84, -37, 32, 40, 95};
        test(arr11, 167);

        int arr12[] = {84, -37, 32, 40, 95};
        test(arr12, 214);

        int arr13[] = {1};
        test(arr13, 1);


    }

    private static void test(int[] arr, int n) {

        System.out.println("\n\nRunning Test.. on \n");
        for (int i = 0; i < arr.length; i++)
            System.out.print(" " + arr[i]);
        System.out.print(" For sum " + n);

        int[] r = smallestSubArraySumGreaterThenM(arr, n);

        if (r.length == 0 || r[0] < 0)
            System.out.println("\n>Not possible");
        else {
            System.out.println("\nSub-array is \n");
            for (int i = r[0]; i < r[1]; i++)
                System.out.print(" " + arr[i]);
        }
    }

    /**
     * @param a
     * @param sum
     * @return The indexes of the shortest sub-array
     */
    private static int[] smallestSubArraySumGreaterThenM(int a[], int sum) {

        if (a == null || a.length == 0)
            return new int[0];

        /**
         * if Sum is negative required, then numberOfWays the sign of array and find positive sum
         */
        if (sum < 0) {
            int b[] = new int[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = -a[i];

            sum = Math.abs(sum);
            return smallestSubArraySumGreaterThenM(b, sum);
        }

        int currSum = 0; //defined the current sum in the window
        int minLength = Integer.MAX_VALUE; //defined the minimum sum > n
        int start = 0; //start of the sub-array
        int end; //end of the sub-array

        int sol[] = new int[2];
        sol[0] = sol[1] = -1;

        int i = 0;
        while (i < a.length) {


            //Add all values for current sum till it reaches at least n
            while (currSum <= sum && i < a.length) {


                //if we want positive sum only, and current gives negative sum, then reset
                if (currSum < 0 && sum > 0) {
                    start = i;
                    currSum = 0;

                }

                currSum += a[i++];
            }

            end = i;

            while (currSum > sum && start <= end) {


                //obtain the current sub-array
                if (end - start + 1 < minLength) {
                    minLength = end - start + 1;
                    sol[0] = start;
                    sol[1] = end;
                }

                currSum -= a[start++];

            }

            i++;

        }

        return sol;

    }


    /**
     * @param a
     * @param sum
     * @return The length of the shortest sub-array
     */
    private static int shortest(int a[], int sum) {

        if (a == null || a.length == 0)
            return -1;

        /**
         * if Sum is negative required, then numberOfWays the sign of array and find positive sum
         */
        if (sum < 0) {
            int b[] = new int[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = -a[i];

            sum = Math.abs(sum);
            return shortest(b, sum);
        }

        int currSum = 0; //defined the current sum in the window
        int minLength = Integer.MAX_VALUE; //defined the minimum sum > n
        int start = 0; //start of the sub-array
        int end; //end of the sub-array

        int i = 0;
        while (i < a.length) {


            //Add all values for current sum till it reaches at least n
            while (currSum <= sum && i < a.length) {


                //if we want positive sum only, and current gives negative sum, then reset
                if (currSum < 0 && sum > 0) {
                    start = i;
                    currSum = 0;

                }

                currSum += a[i++];
            }

            end = i;

            while (currSum > sum && start <= end) {


                //obtain the current sub-array
                if (end - start + 1 < minLength) {
                    minLength = end - start + 1;
                }

                currSum -= a[start++];

            }

            i++;

        }

        return minLength;

    }

}
