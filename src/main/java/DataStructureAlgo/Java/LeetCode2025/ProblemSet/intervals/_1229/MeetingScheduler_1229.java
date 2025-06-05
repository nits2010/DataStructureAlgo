package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._1229;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/5/2025
 * Question Title: 1229. Meeting Scheduler 🔒
 * Link: https://leetcode.com/problems/meeting-scheduler/description/
 * https://github.com/nits2010/leetcode-all-problem/blob/main/solution/1200-1299/1229.Meeting%20Scheduler/README_EN.md
 * Description: Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 * <p>
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * <p>
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 * <p>
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 * <p>
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= slots1.length, slots2.length <= 104
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 109
 * 1 <= duration <= 106
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._986.IntervalListIntersections_986}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @DoorDash
 * @Paypal
 * @pramp
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MeetingScheduler_1229 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 8, Arrays.asList(60, 68)));
        tests.add(test(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 12, Arrays.asList()));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] slots1, int[][] slots2, int duration, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"slots1", "slots2", "duration", "Expected"}, true, slots1, slots2, duration, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        output = new Solution().minAvailableDuration(slots1, slots2, duration);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * This problem is similar to {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._986.IntervalListIntersections_986}
     * Here we have two slots of two persons. Each slot is non-overlapping. We also gave a duration.
     * We need to find the earliest time slot that works for both of them and is of duration 'duration'. Means we need to find a time slot in which
     * both the persons are free and duration of that free time should be at least 'duration'.
     * <p>
     * Now, to find the free Time between two persons, we can find the intersection b/w both of them. If the duration of that intersection is greater than equal 'duration', then we have found the time slot.
     * Otherwise, we have to consider other slots.
     * <p>
     * One way to test each slot of Person 1 to person 2 to find the intersection and check the duration. That will be O(mxn) time.
     * However, since we can sort the freeTime of both the persons and then apply two pointer approach just like finding the intersection of two sorted arrays, we can do it in O(mlogn + nlogn) time.
     * Post finding the intersection, move the i pointer if the endTime of slots1[i] is < endTIme of slots2[i], else moves the j pointer.
     * <p>
     * T:  O(mlogn + nlogn)
     * S:  O(1)
     */
    static class Solution {
        public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
            //sort by start time
            Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
            Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));
            int i = 0, j = 0;
            while (i < slots1.length && j < slots2.length) {
                int[] freeTime1 = slots1[i];
                int[] freeTime2 = slots2[j];

                //intersection
                int start = Math.max(freeTime1[0], freeTime2[0]);
                int end = Math.min(freeTime1[1], freeTime2[1]);

                int freeDuration = end - start;
                if (freeDuration >= duration) {
                    //we found the slot
                    return Arrays.asList(start, start + duration);
                } else if (freeTime1[1] < freeTime2[1]) {
                    i++;
                } else {
                    j++;
                }

            }

            return new ArrayList<>();
        }
    }


}
