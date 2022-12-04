package DataStructureAlgo.Java.LeetCode.intervalRelatedProblems;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-18
 * Description: https://leetcode.com/problems/merge-intervals/
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervalsUnionIntersection {

    public static void main(String []args) {
        int interval[][] = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        test1(interval);

        int interval2[][] = {{1, 4}, {4, 5}};
        test1(interval2);

        int interval3[][] = {{2, 6}, {1, 3}, {8, 10}, {15, 18}};
        test1(interval3);

        int interval4[][] = {{1, 10}, {2, 6}, {9, 12}, {14, 16}, {16, 17}};
        test1(interval4);
    }

    private static void test1(int[][] intervals) {
        System.out.print("\n\n\n Testing intervals [ ");
        for (int i = 0; i < intervals.length; i++)
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
        System.out.print("]\n");

        SolutionMergeIntervalsUnion merge = new SolutionMergeIntervalsUnion();


        int merged[][] = merge.union(intervals);
        System.out.print("Union intervals : [");
        for (int i = 0; i < merged.length; i++)
            System.out.print("[" + merged[i][0] + "," + merged[i][1] + "]");
        System.out.print("]");


        int intersection[][] = merge.intersection(intervals);
        System.out.print("\n Intersection intervals : [");
        for (int i = 0; i < merged.length; i++)
            System.out.print("[" + intersection[i][0] + "," + intersection[i][1] + "]");
        System.out.print("]");
    }

}

class SolutionMergeIntervalsUnion {

    /**
     * https://leetcode.com/problems/merge-intervals/
     *
     * @param intervals
     * @return
     */
    public int[][] union(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
            return intervals;

        //Sort the interval on start time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int temp[][] = new int[intervals.length][2];

        //start with first interval
        temp[0][0] = intervals[0][0];
        temp[0][1] = intervals[0][1];

        int k = 0;
        for (int i = 1; i < intervals.length; i++) {

            //if start time of previous > end time of current, then merge
            if (temp[k][1] >= intervals[i][0] && intervals[i][1] >= temp[k][0]) {
                temp[k][0] = Math.min(temp[k][0], intervals[i][0]);
                temp[k][1] = Math.max(temp[k][1], intervals[i][1]);
            } else {
                k++;
                temp[k][0] = intervals[i][0];
                temp[k][1] = intervals[i][1];

            }
        }


        if (k == intervals.length)
            return temp;

        int res[][] = new int[k + 1][2];
        for (int i = 0; i <= k; i++) {
            res[i][0] = temp[i][0];
            res[i][1] = temp[i][1];
        }

        return res;

    }


    /**
     * https://scicomp.stackexchange.com/questions/26258/the-easiest-way-to-find-intersection-of-two-intervals
     *
     * @param intervals
     * @return
     */
    public int[][] intersection(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
            return intervals;

        //Sort the interval on start time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int temp[][] = new int[intervals.length][2];

        //start with first interval
        temp[0][0] = intervals[0][0];
        temp[0][1] = intervals[0][1];

        int k = 0;
        for (int i = 1; i < intervals.length; i++) {

            //if start time of previous > end time of current, then merge
            if (temp[k][1] >= intervals[i][0] && intervals[i][1] >= temp[k][0]) {
                temp[k][0] = Math.max(temp[k][0], intervals[i][0]);
                temp[k][1] = Math.min(temp[k][1], intervals[i][1]);
            } else {
                k++;
                temp[k][0] = intervals[i][0];
                temp[k][1] = intervals[i][1];

            }
        }


        if (k == intervals.length)
            return temp;

        int res[][] = new int[k + 1][2];
        for (int i = 0; i <= k; i++) {
            res[i][0] = temp[i][0];
            res[i][1] = temp[i][1];
        }

        return res;
    }


}