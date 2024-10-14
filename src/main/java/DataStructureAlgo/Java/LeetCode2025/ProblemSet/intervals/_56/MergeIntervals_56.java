package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._56;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 56. Merge Intervals
 * Description: https://leetcode.com/problems/merge-intervals/description/
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
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
 *
 * @Editorial
 */
public class MergeIntervals_56 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, new int[][]{{1, 6}, {8, 10}, {15, 18}});
        test &= test(new int[][]{{1, 4}, {4, 5}}, new int[][]{{1, 5}});
        test &= test(new int[][]{{1, 4}, {2, 3}}, new int[][]{{1, 4}});
        test &= test(new int[][]{{1, 4}, {0, 4}}, new int[][]{{0, 4}});
        test &= test(new int[][]{{1, 4}, {0, 1}}, new int[][]{{0, 4}});
        test &= test(new int[][]{{1, 4}, {0, 0}}, new int[][]{{0, 0}, {1, 4}});
        CommonMethods.printResult(test);
    }


    private static boolean test(int[][] intervals, int[][] expected) {
        System.out.println("----------------------------------");
        System.out.println("Input: intervals = " + Arrays.deepToString(intervals) + ", expected = " + Arrays.deepToString(expected));
        int[][] output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.merge(intervals);
        pass = Arrays.deepEquals(output, expected);
        finalPass &= pass;
        System.out.println("Output1: " + Arrays.deepToString(output) + "Pass : " + (pass ? "Pass" : "Fail"));

        Solution2 solution2 = new Solution2();
        output = solution2.merge(intervals);
        pass = Arrays.deepEquals(output, expected);
        finalPass &= pass;
        System.out.println("Output2: " + Arrays.deepToString(output) + "Pass : " + (pass ? "Pass" : "Fail"));
        return finalPass;
    }

    static class Solution {
        public int[][] merge(int[][] intervals) {

            //sort by start time
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            List<int[]> outputList = new ArrayList<>();
            outputList.add(intervals[0]);

            for (int i = 1; i < intervals.length; i++) {
                int[] curr = intervals[i];
                int[] lastInterval = outputList.get(outputList.size() - 1);

                //overlaps
                if (lastInterval[1] >= curr[0]) {
                    lastInterval[1] = Math.max(curr[1], lastInterval[1]);
                } else {
                    outputList.add(curr);
                }
            }
            // System.out.println(outputList);

            return outputList.toArray(new int[outputList.size()][]);


        }
    }

    static class Solution2 {
        public int[][] merge(int[][] intervals) {

            //sort by start time
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            List<int[]> outputList = new ArrayList<>();
            outputList.add(intervals[0]);
            int[] lastInterval = intervals[0];

            for (int i = 1; i < intervals.length; i++) {
                int[] curr = intervals[i];

                //overlaps
                if (lastInterval[1] >= curr[0]) {
                    lastInterval[1] = Math.max(curr[1], lastInterval[1]);
                } else {
                    outputList.add(curr);
                    lastInterval = curr;
                }
            }
            // System.out.println(outputList);

//            return outputList.toArray(new int[outputList.size()][]);
            return outputList.toArray(int[][]::new);


        }
    }
}
