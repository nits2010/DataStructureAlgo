package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._57;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Title: 57. Insert Interval
 * Link: https://leetcode.com/problems/insert-interval/
 * Description:
 * You are given an array of non-overlapping intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
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
 * @Array
 * @BinarySearch <p><p>
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
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 5}}, new int[]{1, 7}, new int[][]{{1, 7}}));
        tests.add(test(new int[][]{{1, 5}}, new int[]{2, 3}, new int[][]{{1, 5}}));
        tests.add(test(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}, new int[][]{{1, 5}, {6, 9}}));
        tests.add(test(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}, new int[][]{{1, 2}, {3, 10}, {12, 16}}));
        tests.add(test(new int[][]{{1, 1}, {6, 9}}, new int[]{2, 5}, new int[][]{{1, 1}, {2, 5}, {6, 9}}));
        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean test(int[][] intervals, int[] newInterval, int[][] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"intervals", "newInterval", "Expected"}, true, intervals, newInterval, expected);

        int[][] output;
        boolean pass, finalPass = true;

        output = new Solution_Linear().insert(intervals, newInterval);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Linear", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_BinarySearch().insert(intervals, newInterval);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Binary-search", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Instead of scanning the intervals linearly to find the insertion Point, we can use binary search to find the insertion point. Then rest same
     * O(log(n)) + O(n) -> O(n)
     */
    static class Solution_BinarySearch {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals == null || intervals.length == 0) {
                return new int[][]{newInterval};
            }

            List<int[]> list = new ArrayList<>();
            int n = intervals.length;

            int insertionPoint = getInsertPosition(intervals, newInterval);

            //add all the interval before the insertionPoint
            int idx = 0;
            while (idx < insertionPoint) {
                list.add(intervals[idx]);
                idx++;
            }

            //if a list is empty, means the first position is the insertion point
            if (list.isEmpty()) {
                list.add(newInterval);
            }

            int[] previous = list.get(list.size() - 1);

            //add remaining intervals and keep merging them if required
            int[] current = previous == newInterval ? intervals[idx++] : newInterval;
            while (idx <= n) {

                if (isOverlaps(previous, current)) {
                    previous = mergeIntervals(previous, current);
                } else {
                    list.add(current);
                    previous = current;
                }
                if (idx == n)
                    break;
                current = intervals[idx];
                idx++;
            }

            return list.toArray(new int[list.size()][2]);

        }

        private int[] mergeIntervals(int[] previous, int[] current) {
            int[] mergedInterval = previous;
            mergedInterval[1] = Math.max(current[1], previous[1]); // get the farthest end time
            mergedInterval[0] = Math.min(current[0], previous[0]); // get the smallest start time
            return mergedInterval;

        }

        private boolean isOverlaps(int[] previous, int[] current) {
            return current[0] <= previous[1];
        }

        private int getInsertPosition(int[][] intervals, int[] newInterval) {

            int newStart = newInterval[0];

            //apply binary search to get the insertion point
            int low = 0, high = intervals.length - 1;

            while (low <= high) {
                int mid = (low + high) >>> 1;

                int[] midInterval = intervals[mid];

                if (midInterval[0] < newStart) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }
    }

    /**
     * Scan the intervals and keep adding all the intervals with newInterval in sorted start time.
     * Once we found an interval whose start time is greater than 'newInterval' time (means insertion point). Then start merging interval with newInterval and lastInterva.
     * Add remaining if any
     * O(n)
     */
    static class Solution_Linear {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals == null || intervals.length == 0) {
                return new int[][]{newInterval};
            }

            List<int[]> list = new ArrayList<>();
            int n = intervals.length;

            //add all intervals before newInterval
            int idx = 0;
            while (idx < n && intervals[idx][0] < newInterval[0]) {
                list.add(intervals[idx++]);
            }

            //now idx will be pointing an interval at which either merge is possible or a new interval needs to be added

            //if a list is empty, means new interval needs to be added first
            if (list.isEmpty()) {
                list.add(newInterval);
            }

            //merge all upcoming intervals with lastInterval
            int[] previous = list.get(list.size() - 1);

            //add remaining intervals and keep merging them if required
            int[] current = previous == newInterval ? intervals[idx++] : newInterval;
            while (idx <= n) {

                if (isOverlaps(previous, current)) {
                    previous = mergeIntervals(previous, current);
                } else {
                    list.add(current);
                    previous = current;
                }
                if (idx == n)
                    break;
                current = intervals[idx];
                idx++;
            }

            return list.toArray(new int[list.size()][2]);

        }

        private int[] mergeIntervals(int[] previous, int[] current) {
            int[] mergedInterval = previous;
            mergedInterval[1] = Math.max(current[1], previous[1]); // get the farthest end time
            mergedInterval[0] = Math.min(current[0], previous[0]); // get the smallest start time
            return mergedInterval;

        }

        private boolean isOverlaps(int[] previous, int[] current) {
            return current[0] <= previous[1];
        }
    }


}
