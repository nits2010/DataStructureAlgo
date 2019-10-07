package Java.companyWise.Adobe;

import Java.helpers.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-23
 * Description:
 * <p>
 * Question: https://www.interviewbit.com/problems/minimise-the-maximum-difference-between-the-heights/
 * <p>
 * Given an array of integers A representing heights of towers and a positive integer B.
 * It is allowed to either increase or decrease the height of every tower by B (only once).
 * <p>
 * Find and return the ***"minimum possible difference"*** between the heights of the longest
 * and the shortest tower after modifications.
 * <p>
 * Note: It is mandatory to either increase or decrease the height of every tower.
 * <p>
 * <p>
 */
public class MinimiseTheMaximumDifferenceBetweenHeightsTower {

    public static void main(String[] args) {
        test(new int[]{2, 1, 4, 3, 5}, 2, 3);
        test(new int[]{5, 17, 100, 11}, 10, 75);
        test(new int[]{1, 5, 10}, 6, 7);
        test(new int[]{1, 15, 10}, 6, 5);
        test(new int[]{1, 5, 8, 10}, 2, 5);
        test(new int[]{3, 9, 12, 16, 20}, 3, 11);
        test(new int[]{1, 10, 8, 5}, 2, 5);
        test(new int[]{3, 16, 12, 9, 20}, 3, 11);
        test(new int[]{92, 75, 95, 89, 97, 85, 40, 22, 57, 67, 10, 12, 81, 87, 4, 16, 41, 72, 7, 6, 29, 86, 46, 75, 76}, 88, 93);


    }

    private static void test(int[] height, int b, int expected) {
        System.out.println("Input :" + GenericPrinter.toString(height));
        System.out.println(" minDifference : " + MinimumDifferenceHeightTowerMinimumPossibleDifference.minDifference(height, b) + " expected " + expected);
    }


    static class MinimumDifferenceHeightTowerMinimumPossibleDifference {

        //===================================================================================

        /**
         * As question says two important things
         * 1, We need to update the value of each element; either increase it or decrease it
         * 2. We need to MINIMIZE THE DIFFERENCE between longest and shortest.
         * <p>
         * As we need to minimize the difference of (longest - shortest) then, it is necessary that
         * 1. longest should be as small as possible but > shortest
         * 2. Shortest should be as greater as possible but < longest
         * <p>
         * By saying this, we need to either
         * 1. increase an element if and only if increasing it makes the difference become less
         * 2. Decreasing an element if and only if decreasing it makes the difference become less
         * <p>
         * for input: {2, 1, 4, 3, 5} and b = 2
         * we can change this array to {(2+2),(1+2),(4-2),(3-2),(5-2)} ={4,3,2,1,3}
         * Min = 1, Max = 4; difference is 3
         * If you apply any other combination too, you;ll either increase the difference or you'll get only at min = 3 difference
         */
        //===================================================================================


        /**
         * Algorithm:
         * Since we know the maximum and minimum element in the array at any moment defines the overall difference. Then for each update
         * we need to track them in such a way that if increasing any element affecting max so far
         * or decreasing any element affecting min so far.
         * <p>
         * The one way would be try all which is O(n^2)
         * But if we sort the array then we can easily find the smallest and greatest in the window any time.
         * <p>
         * So
         * Step 1: sort the array and find the best difference max{h[n-1]} - min{h[0]}
         * Step 2: now, update both with increase and decrease; min -> increase and max -> decrease because we need to minimize the difference
         * step 3: Iterate all elements test both possibilities
         * <p>
         * 3.1 Increase it ; by increasing it if it does not change the max so far, and by decreasing it does not make change to min so far
         * then we can do any of them
         * <p>
         * 3.2: but if any of them make changes to min and max; then we need to choose the one which give the minimum difference
         *
         * @param heights
         * @param b
         * @return
         */
        private static int minDifference(int[] heights, int b) {
            if (heights == null || heights.length == 0)
                return 0;

            Arrays.sort(heights);
            int n = heights.length - 1;

            int min = heights[0];
            int max = heights[n];

            int bestDiff = max - min;

            /**
             * reduce the difference by increasing min and decreasing max;
             * Note here, by increasing to one and decreasing from other will may affect the diff and they get
             * evaluate too
             */
            int small = min + b;
            int big = max - b;

            /**
             * By change them may be change the max and min behaviou, correct it
             */
            max = Math.max(small, big);
            min = Math.min(small, big);


            /**
             * Note here, by increasing to one and decreasing from other will may affect the diff Hence they also need
             * to evaluate too; hence 0 to n-1
             */
            for (int i = 0; i < n; i++) {
                int dec = heights[i] - b;
                int inc = heights[i] + b;

                /**
                 * 3.1 Increase it ; by increasing it if it does not change the max so far,
                 * or by decreasing it does not make change to min so far
                 *      then we can do any of them
                 */
                if (inc <= max || dec >= min)
                    continue;

                //3.2: but if any of them make changes to min and max; then we need to choose the one which give the minimum difference
                int x = inc - min;// if u increase it
                int y = max - dec;// if you decrease it

                if (x >= y) //decreasing gives better result
                    min = dec;
                else  //increasing it gives better result
                    max = inc;


            }

            return Math.min(bestDiff, max - min);
        }


    }


}



