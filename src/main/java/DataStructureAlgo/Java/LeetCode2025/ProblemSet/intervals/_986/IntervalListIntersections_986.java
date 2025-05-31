package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._986;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/31/2025
 * Question Title: 986. Interval List Intersections
 * Link: https://leetcode.com/problems/interval-list-intersections/description/
 * Description: You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * <p>
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 * <p>
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 109
 * endi < starti+1
 * 0 <= startj < endj <= 109
 * endj < startj+1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @LineSweep <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @DoorDash
 * @Facebook
 * @Google
 * @Snapchat
 * @Uber
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class IntervalListIntersections_986 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}, new int[][]{{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}}));
        tests.add(test(new int[][]{{1, 3}, {5, 9}}, new int[][]{}, new int[][]{}));
        tests.add(test(new int[][]{{3, 5}, {9, 20}}, new int[][]{{4, 5}, {7, 10}, {11, 12}, {14, 15}, {16, 20}}, new int[][]{{4, 5}, {9, 10}, {11, 12}, {14, 15}, {16, 20}}));
        tests.add(test(new int[][]{{4, 5}, {7, 10}, {11, 12}, {14, 15}, {16, 20}}, new int[][]{{3, 5}, {9, 20}}, new int[][]{{4, 5}, {9, 10}, {11, 12}, {14, 15}, {16, 20}}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] firstList, int[][] secondList, int[][] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"firstList", "secondList", "Expected"}, true, firstList, secondList, expected);

        int[][] output;
        boolean pass, finalPass = true;

        output = new Solution_MergeSort().intervalIntersection(firstList, secondList);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Keep taking intersection b/w two intervals from both lists if they overlap. Since they are sorted with start time, move the pointer (i or j) whoever end time is smaller.
     * Because an interval whose endTime is smaller is ended already.
     * I=[[0,2],[5,10],[13,23],[24,25]]
     * j=[[1,5],[8,12],[15,24],[25,26]]
     * k=[[1,2],[5,5]]
     * <p>
     * i=0,j=0 [0,2] & [1,5] => overlaps, intersection [1,2] ; 2 < 5 ; i++
     * i=1,j=0 [5,10] & [1,5] => overlaps, inter [5,5] ; 10 > 5 ; j++
     * i=1,j=1 [5,10] & [8,12] => overlaps, int [8,10]
     * i=2,j=1 [13,23]& [8,12] => non-overlaps
     * i=2,j=2 [13,23]& [15,24] => overlaps, int [15,23]
     */
    static class Solution_MergeSort {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

            int firstLength = firstList.length;
            int secondLength = secondList.length;

            int i = 0, j = 0;
            List<int[]> intersections = new ArrayList<>();

            while (i < firstLength && j < secondLength) {
                int[] first = firstList[i];
                int[] second = secondList[j];

                if (isOverlaps(first, second)) {
                    intersections.add(getIntersection(first, second));
                }

                if (first[1] < second[1]) {
                    i++;
                } else {
                    j++;
                }

            }


            return intersections.toArray(new int[intersections.size()][]);

        }

        private boolean isOverlaps(int[] previous, int[] current) {
            if (previous[0] > current[0]) {
                return isOverlaps(current, previous); //make sure they are sorted based on start time.
            }
            return current[0] <= previous[1];
        }

        private int[] getIntersection(int[] a, int[] b) {
            int[] intersection = new int[2];
            intersection[0] = Math.max(a[0], b[0]);
            intersection[1] = Math.min(a[1], b[1]);
            return intersection;
        }
    }
}
