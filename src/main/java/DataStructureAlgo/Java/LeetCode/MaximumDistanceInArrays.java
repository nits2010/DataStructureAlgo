package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:https://leetcode.com/problems/maximum-distance-in-arrays/
 * http://leetcode.liangjiateng.cn/leetcode/maximum-distance-in-arrays/description
 * <p>
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.
 * <p>
 * Example 1:
 * Input:
 * [[1,2,3],
 * [4,5],
 * [1,2,3]]
 * Output: 4
 * Explanation:
 * One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 * Note:
 * Each given array will have at least 1 number. There will be at least two non-empty arrays.
 * The total number of the integers in all the m arrays will be in the range of [2, 10000].
 * The integers in the m arrays will be in the range of [-10000, 10000].
 *
 * Article: https://leetcode.com/articles/maximum-distance-in-array/
 */
public class MaximumDistanceInArrays {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 3}, {4, 5}, {1, 2, 3}}, 4); //5-1
        test(new int[][]{{1, 2, 15}, {4, 5}, {1, 2, 3}}, 14); //15-1
        test(new int[][]{{1, 8, 16, 18}, {2, 8, 16, 32}, {4, 2, 6}, {0, 8, 32, 38}}, 37);
    }

    private static void test(int[][] list, int expected) {
        System.out.println("\n Input :" + CommonMethods.toString(list) + " expected :" + expected + "\n");

        MaximumDistanceInArraysBruteForce bruteForce = new MaximumDistanceInArraysBruteForce();
        MaximumDistanceInArraysBruteForceOptimized bruteForceOptimized = new MaximumDistanceInArraysBruteForceOptimized();
        MaximumDistanceInArraysSort sort = new MaximumDistanceInArraysSort();
        MaximumDistanceInArraysPreProcess preProcess = new MaximumDistanceInArraysPreProcess();
        MaximumDistanceInArraysPreProcessSingleScan preProcessSingleScan = new MaximumDistanceInArraysPreProcessSingleScan();


        System.out.println("bruteForce :" + bruteForce.maxDistance(list));
        System.out.println("bruteForceOptimized :" + bruteForceOptimized.maxDistance(list));
        System.out.println("sort :" + sort.maxDistance(list));
        System.out.println("preProcess :" + preProcess.maxDistance(list));
        System.out.println("preProcessSingleScan :" + preProcessSingleScan.maxDistance(list));
    }
}


/**
 * Since we need to maximize the abs[b-a] such that 'b' and 'a' are from different sets.
 * <p>
 * Algo
 * 1. Iterate over every array O(m)
 * 2. for each array, take a element {O(L)} and iterate over rest of the m-1 {O(m)} array and test the difference with last elements of them.
 * <p>
 * => O(L*m^2)/ O(1)
 */
class MaximumDistanceInArraysBruteForce {

    public int maxDistance(int[][] list) {

        int maxDiff = 0;


        //Iterate over every array O(m)
        for (int i = 0; i < list.length; i++) {

            //for each array, take a element ; O(L)
            for (int j = 0; j < list[i].length; j++) {

                //iterate over rest of the m-1 {O(m)}
                for (int x = 0; x < list.length; x++) {

                    if (i != x && list[x].length > 0) {

                        int a = list[i][j];

                        int n = list[x].length - 1;

                        int b = list[x][n];

                        maxDiff = Math.max(maxDiff, Math.abs(b - a));
                    }
                }
            }

        }

        return maxDiff;
    }
}

/**
 * We should extend the fact that the first element in the array is minimum while last is maximum.
 * So we don't need to iterate every element of the array in-order to maximize the diff.
 * i.e. we can omit O(L) computation directly.
 * <p>
 * O(m^2) / O(1)
 */
class MaximumDistanceInArraysBruteForceOptimized {
    public int maxDistance(int[][] list) {

        int maxDiff = 0;


        //Iterate over every array O(m)
        for (int i = 0; i < list.length; i++) {


            //iterate over rest of the m-1 {O(m)}
            for (int x = 0; x < list.length; x++) {

                if (i != x && list[x].length > 0) {

                    int a = list[i][0];

                    int n = list[x].length - 1;

                    int b = list[x][n];

                    maxDiff = Math.max(maxDiff, Math.abs(b - a));
                }
            }


        }

        return maxDiff;
    }
}


/**
 * In {@link MaximumDistanceInArraysBruteForceOptimized} we found a fact of min and max element in list
 * after utilizing the fact of first element as minimum and last as maximum, we ended up on O(m^2).
 * <p>
 * Algo:
 * 1. To optimize it further, we can extract the minimum and maximum element in two different arrays. : O(m)
 * <p>
 * Let say Min and Max arrays.
 * 2. Now we need to optimally find the maximum element for every min element in Max array {excluding its own array max}.
 * We can sort the Max array, but after sorting, the indexes will be suffle and difficult to match index. O(m * log (m))
 * For that we'll keep another array which holds ordinal index and also get shuffle during sort.
 * <p>
 * 3. Now for every Min[i], we can test Max[j] such that i!=j and diff is maximum. O(m)
 * <p>
 * We need to take care a case when i==j, then we need to just test it with j-1 element, which make sure maximumness and uniqueness of indexes.
 * <p>
 * => O(m * log (m)) / O (m)
 */
class MaximumDistanceInArraysSort {

    public int maxDistance(int[][] list) {

        int maxDiff = 0;

        int m = list.length;

        int min[] = new int[m];
        int max[][] = new int[m][2]; //element and its index

        //Iterate over every array O(m)
        for (int i = 0; i < list.length; i++) {

            if (list[i].length > 0) {
                int n = list[i].length - 1;

                min[i] = list[i][0];
                max[i][0] = list[i][n];
                max[i][1] = i;
            }

        }


        Arrays.sort(max, (Comparator.comparingInt(o -> o[0])));

        for (int i = 0; i < m; i++) {

            if (i != max[m - 1][1]) {
                maxDiff = Math.max(maxDiff, Math.abs(min[i] - max[m - 1][0]));
            } else {
                maxDiff = Math.max(maxDiff, Math.abs(min[i] - max[m - 2][0]));
            }

        }


        return maxDiff;
    }

}

/**
 * In {@link MaximumDistanceInArraysSort} we optimize to find max element for a min element that makes second loop to run in O(m) time.
 * But do we really need to sort the data ?
 * Not really. Take an example;
 * [ [1,8,16,18]
 * [2,8,16,32]
 * [4,2,6]
 * [0,8,32,38] ]
 * <p>
 * *    0,1,2,3
 * Min [1,2,4,0]
 * Max[18,32,6,38]
 * <p>
 * For every index 'i' in min, there is a max in Max at index 'i' which tells that minimum of list[i][0] and maximum of the same list list[i][n-1].
 * Since we can't include maximum of own list, that makes we need to find maximum of remaining list.
 * <p>
 * if we optimally can find the maximum of remaining list for each index 'i', then we can improve the complexity.
 * <p>
 * One way would be iterate over list and find maximum of rest of the list which is O(m), that makes overall finding max O(m^2).
 * <p>
 * Lets see how to find maximum in remaining list efficiently
 * For Max =                    [18,32, 6, 38]
 * The result list look like    [38,38, 38, 32]
 * <p>
 * For any index at 'i', we need to see what is the maximum value after 'i''th index and before 'i'th index {excluding i'th index}.
 * <p>
 * i.e. we need to find maximum on left side and maximum on right side of every index 'i', excluding i'th index.
 * we can use {@link DataStructureAlgo.Java.companyWise.Amazon.ReplaceGreatestElementOnRightSide} as sub-routine
 * and build a array for left and for right.
 * Then take maximum of them to build final Max array.
 * <p>
 * old-Max-Array:   [18,   32,  6,  38]
 * Left-Max-Array:  [-1,   18,  32, 32]
 * Right-Max-Array: [38,   38   38, -1]
 * <p>
 * FinalMax[i] holds maximum of left[i] and right[i]
 * final Max :      [38, 38, 38, 32]
 * <p>
 * <p>
 * Sub-routine takes O(m) time build build both array. Overall it takes O(m) time to build final max array.
 */
class MaximumDistanceInArraysPreProcess {

    public int maxDistance(int[][] list) {

        int maxDiff = 0;

        int m = list.length;

        int min[] = new int[m];
        int max[] = new int[m];

        //Iterate over every array O(m)
        for (int i = 0; i < list.length; i++) {
            if (list[i].length > 0) {
                int n = list[i].length - 1;

                min[i] = list[i][0];
                max[i] = list[i][n];
            }

        }


        int finalMax[] = buildFinalMax(max);
        for (int i = 0; i < m; i++) {

            maxDiff = Math.max(maxDiff, Math.abs(min[i] - finalMax[i]));

        }


        return maxDiff;
    }


    /**
     * {@link DataStructureAlgo.Java.companyWise.Amazon.ReplaceGreatestElementOnRightSide}
     * <p>
     * O(n)
     *
     * @param nums
     * @return
     */
    private int[] buildFinalMax(int nums[]) {
        if (nums == null || nums.length == 0)
            return nums;

        int m = nums.length;
        /**
         *
         * FinalMax[i] holds maximum of left[i] and right[i]
         */
        int finalMax[] = new int[m];

        /**
         * rightMax[i] holds maximum on right side of nums[i] excluding nums[i].
         */
        int rightMax[] = new int[m];

        /**
         * leftMax[i] holds maximum on left side of nums[i] excluding nums[i].
         */
        int leftMax[] = new int[m];


        leftMax[0] = -1;
        rightMax[m - 1] = -1;


        for (int i = 1, j = m - 2; i < m; i++, j--) {

            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
            rightMax[j] = Math.max(rightMax[j + 1], nums[j + 1]);


        }

        for (int i = 0; i < m; i++)
            finalMax[i] = Math.max(rightMax[i], leftMax[i]);

        return finalMax;


    }


}

/**
 * In {@link MaximumDistanceInArraysPreProcess}. After separating 'Min' and 'Max'.
 * We are essentially looking for 'correct Max[i]' for every Min[i].
 * <p>
 * To find the correct Max[i], we used {@link DataStructureAlgo.Java.companyWise.Amazon.ReplaceGreatestElementOnRightSide} as sub-routine.
 * Which do nothing but find the max element in rest of the element in the array excluding current element.
 * <p>
 * What if, we can compute this information in first scan it self.
 * <p>
 * But how? how to find maximum element s.t. it exclude the current element and maximum out of all remaining ?
 * <p>
 * To find this, we need to ask few questions;
 * 1. what if  m=1
 * in this case we are having only 1 array in list. In this case our max-diff is 0. Since we can't pick two elements from
 * two different array.
 * <p>
 * 2. What if m=2; i=0, j=1
 * In this case, minimum would be from either list[i][0] or list[j][0], and max would be from either list[i][p-1] or list[j][n-1] ;
 * where 'p' is length of first array and n is length of second array
 * we can tell the max difference would be maximum of below for sure;
 * {list[i][n-1] - list[j][0] }
 * {list[j][p-1] - list[i][0]}
 * <p>
 * To be maximum, the value at left side should be maximum i.e. Max {list[i][n-1] , list[j][p-1] }
 * and the value on the right side should be minimum i.e. Min {list[j][0] ,list[i][0] }.
 * The only thing to pay attention here is max and min should not come from same list[i] or list[j].
 * <p>
 * i.e. means while calculating max, we can omit current list and take max so fare on previous lists
 * similarly for min, we can omit current list and take min so fare on previous lists
 * <p>
 * At the end, we'll have correct max and min which make the difference correct
 * <p>
 * <p>
 * Algo:
 * 1. Take the minimum and maximum form the first list. as 'min' and 'max'
 * 2. for ever other list calculate the difference as below
 * Max of
 * {list[i][n-1] - min }
 * {max- list[i][0]}
 * <p>
 * 3. Update the max and min for next iteration.
 * min = Min {min, list[i][0]}
 * max = Max {max, list[i][n-1]}
 * Note, this min and max would be use in next iteration, that max sure that we did not use the max/min of next element as base.
 *
 * <p>
 * O(m)/O(1)
 */
class MaximumDistanceInArraysPreProcessSingleScan {

    public int maxDistance(int[][] list) {
        int maxDiff = 0;

        int m = list.length;

        /**
         * Find first non-empty list; Since input can contain empty lists.
         */
        int x = 0;
        for (x = 0; x < m; x++)
            if (list[x].length > 0)
                break;


        int min = list[x][0];
        int max = list[x][1];

        for (int i = x + 1; i < m; i++) {

            //calculate the difference as below
            if (list[i].length > 0) {

                int n = list[i].length;

                int a = Math.abs(list[i][n - 1] - min);
                int b = Math.abs(max - list[i][0]);

                maxDiff = Math.max(maxDiff, Math.max(a, b));

                //Update the max and min for next iteration.
                min = Math.min(min, list[i][0]);
                max = Math.max(max, list[i][n - 1]);

            }
        }

        return maxDiff;
    }
}