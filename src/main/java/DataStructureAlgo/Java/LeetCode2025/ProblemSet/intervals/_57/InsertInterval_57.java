package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._57;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 57. Insert Interval
 * Description: https://leetcode.com/problems/insert-interval/
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array <p><p>
 * Company Tags
 * -----
 * @Google
 * @LinkedIn
 * @Amazon
 * @Facebook
 * @Robinhood
 * @Adobe
 * @Apple
 * @Dataminr
 * @Microsoft
 * @Oracle
 * @Tableau
 * @Twitter
 * @Uber
 * @Yahoo <p><p>
 * @Editorial
 */
public class InsertInterval_57 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 5}}, new int[]{1, 7}, new int[][]{{1, 7}});
        test &= test(new int[][]{{1, 5}}, new int[]{2, 3}, new int[][]{{1, 5}});
        test &= test(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}, new int[][]{{1, 5}, {6, 9}});
        test &= test(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}, new int[][]{{1, 2}, {3, 10}, {12, 16}});
        test &= test(new int[][]{{1, 1}, {6, 9}}, new int[]{2,5}, new int[][]{{1, 1}, {2, 5}, {6, 9}});
        CommonMethods.printResult(test);
    }

    private static boolean test(int[][] intervals, int[] newInterval, int[][] expected) {
        System.out.println("----------------------------------");
        System.out.println("Intervals : " + CommonMethods.toStringInline(intervals) + " Expected :" + CommonMethods.toStringInline(expected));

        int[][] result;
        boolean pass, finalPass = true;

        Solution sol = new Solution();
        result = sol.insert(intervals, newInterval);
        pass = CommonMethods.equals(result, expected);
        System.out.println("Result : " + CommonMethods.toStringInline(result) + " Pass : " + (pass ? "Pass" : "Fail"));
        finalPass &= pass;
        return finalPass;

    }

    static class Solution {

        public int[][] insert(int[][] intervals, int[] newInterval) {
            if(intervals == null || intervals.length == 0){
                return new int[][]{newInterval};
            }

            List<int[]> list = new ArrayList<>();
            int n = intervals.length;

            //add all intervals before newInterval
            int idx = 0;
            while (idx<n && intervals[idx][0] < newInterval[0] ){
                list.add(intervals[idx++]);
            }


            //now idx will be pointing an interval at which either merge is possible or a new interval needs to be added

            //if a list is empty, means new interval needs to be added first
            if(list.isEmpty()){
                list.add(newInterval);
            }

            //merge all upcoming intervals with lastInterval
            int []lastInterval = list.get(list.size()-1);

            if(lastInterval != newInterval && !merge(lastInterval, newInterval)){
                //merge is not possible
                list.add(newInterval);
                lastInterval = newInterval;
            }

            //add remaining intervals
            while (idx < n){
                if(merge(lastInterval, intervals[idx])){
                    idx++;
                }else {
                    list.add(intervals[idx]);
                    lastInterval = intervals[idx++];
                }
            }

            return list.toArray(new int[list.size()][2]);

        }



        boolean merge(int[] lastInterval, int[] newInterval) {
            if (lastInterval[1] >= newInterval[0]) {
                //merge it
                lastInterval[0] = Math.min(lastInterval[0], newInterval[0]);
                lastInterval[1] = Math.max(lastInterval[1], newInterval[1]);
                return true;
            }
            return false;
        }
    }
}
