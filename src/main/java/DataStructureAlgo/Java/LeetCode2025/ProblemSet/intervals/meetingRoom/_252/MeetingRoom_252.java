package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._252;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 252. Meeting Rooms
 * Description: https://leetcode.com/problems/meeting-rooms
 * https://leetcode.ca/all/252.html
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: true
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.intervalRelatedProblems.meeting.room.MeetingRoomI}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Facebook
 * @Google
 * @Microsoft
 * @Twitter <p><p>
 * @Editorial
 */
public class MeetingRoom_252 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{0, 30}, {5, 10}, {15, 20}}, false);
        test &= test(new int[][]{{7, 10}, {2, 4}}, true);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[][] intervals, boolean expected) {

        System.out.println("---------------------------------------------");
        System.out.println("Intervals : " + CommonMethods.toStringInline(intervals) + " Expected : " + expected);

        boolean result, pass, finalPass = true;

        Solution sol = new Solution();
        result = sol.canAttendMeetings(intervals);
        pass = result == expected;
        finalPass &= pass;
        System.out.println("Result : " + result + " Pass : " + (pass ? "Pass" : "Fail"));
        return finalPass;
    }

    /**
     * If a person wants to attend all meetings, then there should not be any overlapping meeting.
     * <p>
     * Algo: Sort the intervals by start time and see any overlapping intervals are there
     * Overlapping intervals: [s1, e1] & [s2, e2]
     * <p>
     * if e1 > s2 then overlapping
     *
     *
     *  O(nlogn)
     */
    //Check if there are any overlapping intervals or not, if there are no overlapping intervals then return true else return false
    static class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            if (intervals == null || intervals.length <= 1)
                return true;

            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            int i = 0;
            while (i < intervals.length - 1) {
                int[] curr = intervals[i];
                int[] next = intervals[i + 1];
                if (curr[1] > next[0])
                    return false;
                i++;
            }
            return true;
        }
    }
}
