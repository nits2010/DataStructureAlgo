package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._56;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/31/2025
 * Question Title: 56. Merge Intervals
 * Link: https://leetcode.com/problems/merge-intervals/description/
 * Description: Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.intervalRelatedProblems.MergeIntervals} {@link DataStructureAlgo.Java.LeetCode.intervalRelatedProblems.MergeIntervalsUnionIntersection}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Google
 * @Bloomberg
 * @Salesforce
 * @Adobe
 * @Alibaba
 * @Apple
 * @Atlassian
 * @Cisco
 * @Coupang
 * @CruiseAutomation
 * @Dataminr
 * @DoorDash
 * @GoDaddy
 * @GoldmanSachs
 * @Intuit
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @PalantirTechnologies
 * @Pinterest
 * @Postmates
 * @SAP
 * @Snapchat
 * @Sumologic
 * @Twitch
 * @Twitter
 * @TwoSigma
 * @Uber
 * @VMware
 * @WalmartLabs
 * @Wayfair
 * @Wish
 * @Yahoo
 * @Yandex
 * @Yelp
 * @Zenefits
 * @Zulily <p><p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MergeIntervals_56 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();

        tests.add(test(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, new int[][]{{1, 6}, {8, 10}, {15, 18}}));
        tests.add(test(new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}}, new int[][]{{1, 3}, {4, 7}}));
        tests.add(test(new int[][]{{1, 4}, {4, 5}}, new int[][]{{1, 5}}));
        tests.add(test(new int[][]{{1, 4}, {2, 3}}, new int[][]{{1, 4}}));
        tests.add(test(new int[][]{{1, 4}, {0, 4}}, new int[][]{{0, 4}}));
        tests.add(test(new int[][]{{1, 4}, {0, 1}}, new int[][]{{0, 4}}));
        tests.add(test(new int[][]{{1, 4}, {0, 0}}, new int[][]{{0, 0}, {1, 4}}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] intervals, int[][] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"intervals", "Expected"}, true, intervals, expected);

        int[][] output;
        boolean pass, finalPass = true;

        output = new Solution().merge(intervals);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {

        public int[][] merge(int[][] intervals) {

            final int length = intervals.length;

            //not sufficient intervals to merge
            if (length <= 1)
                return intervals;

            final List<int[]> mergedIntervals = new ArrayList<>();

            //sort the intervals by start time, to bring all intervals together that start around.
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

            mergedIntervals.add(intervals[0]);
            int[] lastInterval = intervals[0];

            //pull intervals and merge them as needed.
            for (int i = 1; i < length; i++) {

                int[] currentInterval = intervals[i];

                //does overlaps ?
                if (isIntervalOverlaps(lastInterval, currentInterval)) {
                    lastInterval = mergeIntervals(lastInterval, currentInterval);
                } else {
                    mergedIntervals.add(currentInterval);
                    lastInterval = currentInterval;
                }

            }
            return mergedIntervals.toArray(new int[mergedIntervals.size()][]);

        }

        /**
         * Return either mergedInterval
         */
        private int[] mergeIntervals(int[] previous, int[] current) {
            int[] mergedInterval = previous;
            mergedInterval[1] = Math.max(current[1], previous[1]); // get the farthest end time
            return mergedInterval;

        }

        private boolean isIntervalOverlaps(int[] previous, int[] current) {
            int start2 = current[0];
            int end1 = previous[1];

            return start2 <= end1;
        }
    }
}
