package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._452;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 05/06/25
 * Question Title: 452. Minimum Number of Arrows to Burst Balloons
 * Link: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 * Description: There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
 * <p>
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * <p>
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 * Example 2:
 * <p>
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * Example 3:
 * <p>
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= points.length <= 105
 * points[i].length == 2
 * -231 <= xstart < xend <= 231 - 1
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
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Quora
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link Solution_Optimal}  {@link Solution_WithoutMerge}
 */

public class MinimumNumberOfArrowsToBurstBalloons_452 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}, 2));
        tests.add(test(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}}, 4));
        tests.add(test(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] balloons, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"balloons", "Expected"}, true, balloons, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionUsingMemory().findMinArrowShots(balloons);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Merging-UsingMemory", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionWithoutMemory().findMinArrowShots(balloons);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Merging-WithoutMemory", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_WithoutMerge().findMinArrowShots(balloons);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"WithoutMerge", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_Optimal().findMinArrowShots(balloons);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Optimal", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * A arrow will only be able to shoot down those balloons which share the some intersections of existing balloons over X-axis.
     * Which implies to merge the balloons diameter (intervals) post sorting balloons by start -> end.
     * <p>
     * Algo Optimization 3: Since we are just interested in end of the interval.
     * 1. Sort balloons by end
     * 2. Start counting the arrows when there is no overlap [ Since we were only updating end when there is an overlap, sorting by end will make sure that end we have updated already and needs to change whenover there no overlapping]
     * 3. maintain the counter and increase whenever there is no overlapping.
     * 4. Return count
     */
    static class Solution_Optimal {
        public int findMinArrowShots(int[][] points) {
            int length = points.length;

            if (length <= 1)
                return 1;

            //merge the overlapping intervals 
            int count = 0;

            //sort points by start, end
            Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

            int prevEnd = points[0][1];
            for (int i = 1; i < length; i++) {
                int[] current = points[i];
                if (current[0] > prevEnd) {
                    count++;
                    prevEnd = current[1];
                }

            }
            count++; //add last ballon
            return count;
        }
    }

    /**
     * A arrow will only be able to shoot down those balloons which share the some intersections of existing balloons over X-axis.
     * Which implies to merge the balloons diameter (intervals) post sorting balloons by start -> end.
     * <p>
     * Algo Optimization 2:
     * 1. Sort balloons by start, or by end if ties
     * 2. Take a baloon and start merging with next baloon if they overlap
     * 3. if they don't overlap, then update the end as only end will change b/w these two balloons.
     * 4. maintain the counter and increase whenever there is no overlapping.
     * 5. Return count
     */
    static class Solution_WithoutMerge {
        public int findMinArrowShots(int[][] points) {
            int length = points.length;

            if (length <= 1)
                return 1;

            //merge the overlapping intervals 
            int count = 0;

            //sort points by start, end
            Arrays.sort(points, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

            int prevEnd = points[0][1];
            for (int i = 1; i < length; i++) {
                int[] current = points[i];
                if (current[0] <= prevEnd) {
                    prevEnd = Math.min(current[1], prevEnd);
                } else {
                    count++;
                    prevEnd = current[1];
                }
            }
            count++; //add last ballon
            return count;
        }

        private boolean isOverlapping(int[] prev, int[] current) {
            return current[0] <= prev[1]; //start <= end
        }
    }

    /**
     * A arrow will only be able to shoot down those balloons which share the some intersections of existing balloons over X-axis.
     * Which implies to merge the balloons diameter (intervals) post sorting balloons by start -> end.
     * <p>
     * Algo:
     * 1. Sort balloons by start, or by end if ties
     * 2. Take a baloon and start merging with next baloon if they overlap
     * 3. if they don't overlap, then add it to merged baloon and take the current as prev
     * 4. Size of list is the number of arrows we need as they have only those intervals which are not overlapping
     * <p>
     * Optimization_1: We don't need a list to hold the intervals, we can count on the fly.
     */
    static class SolutionWithoutMemory {
        public int findMinArrowShots(int[][] points) {
            int length = points.length;

            if (length <= 1)
                return 1;

            //merge the overlapping intervals 
            int count = 0;

            //sort points by start, end
            Arrays.sort(points, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

            int[] prev = points[0];
            for (int i = 1; i < length; i++) {
                int[] current = points[i];
                if (isOverlapping(prev, current)) {
                    int[] mergedPoint = new int[2];
                    mergedPoint[0] = Math.max(current[0], prev[0]);
                    mergedPoint[1] = Math.min(current[1], prev[1]);
                    prev = mergedPoint;
                } else {
                    count++;
                    prev = current;
                }
            }
            count++; //add last ballon
            return count;
        }

        private boolean isOverlapping(int[] prev, int[] current) {
            return current[0] <= prev[1]; //start <= end
        }
    }

    /**
     * A arrow will only be able to shoot down those balloons which share the some intersections of existing balloons over X-axis.
     * Which implies to merge the balloons diameter (intervals) post sorting balloons by start -> end.
     * <p>
     * Algo:
     * 1. Sort balloons by start, or by end if ties
     * 2. Take a baloon and start merging with next baloon if they overlap
     * 3. if they don't overlap, then add it to merged baloon and take the current as prev
     * 4. Size of list is the number of arrows we need as they have only those intervals which are not overlapping
     */
    static class SolutionUsingMemory {
        public int findMinArrowShots(int[][] points) {
            int length = points.length;

            if (length <= 1)
                return 1;

            //merge the overlapping intervals 
            List<int[]> merged = new ArrayList<>();

            //sort points by start, end
            Arrays.sort(points, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

            int[] prev = points[0];
            for (int i = 1; i < length; i++) {
                int[] current = points[i];
                if (isOverlapping(prev, current)) {
                    int[] mergedPoint = new int[2];
                    mergedPoint[0] = Math.max(current[0], prev[0]);
                    mergedPoint[1] = Math.min(current[1], prev[1]);
                    prev = mergedPoint;
                } else {
                    merged.add(prev);
                    prev = current;
                }
            }
            merged.add(prev); //add last ballon
            return merged.size();
        }

        private boolean isOverlapping(int[] prev, int[] current) {
            return current[0] <= prev[1]; //start <= end
        }
    }
}
