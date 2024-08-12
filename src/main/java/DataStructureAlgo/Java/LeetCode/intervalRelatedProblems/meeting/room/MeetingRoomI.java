package DataStructureAlgo.Java.LeetCode.intervalRelatedProblems.meeting.room;

import  DataStructureAlgo.Java.helpers.templates.Interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-20
 * Description: Leetcode: Meeting Rooms
 * https://leetcode.com/problems/meeting-rooms/
 * Meeting
 * * Question: Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 * * For example,
 * * Given [[0, 30],[5, 10],[15, 20]],
 * * return false.
 * <p>
 * http://buttercola.blogspot.com/2015/08/leetcode-meeting-rooms.html
 * <p>
 * [FACEBOOK], [GOOGLE] , [SNAPCHAT], [UBER]
 */


public class MeetingRoomI {
    public static void main(String []args) {

        System.out.println("Meeting Room I tests");
        MeetingRoomI meetingRoomI = new MeetingRoomI();

        /**
         * Given [[0, 30],[5, 10],[15, 20]],
         * return false.
         */
        test(meetingRoomI, Arrays.asList(new Interval(11, 30), new Interval(0, 10), new Interval(5, 15)));

        /**
         * Given [[0,30] , [ 5,10], [15,25]
         * return false
         */
        test(meetingRoomI, Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 25)));


        /**
         * Given [[0,10] , [ 15,20], [11,14]
         * return true
         */
        test(meetingRoomI, Arrays.asList(new Interval(0, 10), new Interval(15, 20), new Interval(11, 14)));

    }

    private static void test(MeetingRoomI meetingRoom, List<Interval> intervals) {

        System.out.println("Testing intervals : " + intervals + " Person can attend all meeting :" + meetingRoom.personCanAttendAllMetings(intervals));

    }


    /**
     * If person wants to attend all meeting then there should not be any overlapping meeting.
     * <p>
     * Algo: Sort the intervals by start time and see any overlapping intervals are there
     * Overlapping intervals: [s1, e1] & [s2, e2]
     * <p>
     * if e1 > s2 or s1<=s2 && e1>=e2
     *
     * @param intervals
     * @return O(nlogn)
     */

    public boolean personCanAttendAllMetings(final List<Interval> intervals) {

        if (intervals == null || intervals.isEmpty())
            return true;

        /**
         * Sort the interval by start time
         */
        Collections.sort(intervals, ((o1, o2) -> {
            if (o1.start == o2.start)
                return o1.end - o2.end;
            return o1.start - o2.start;
        }));

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i - 1).end > intervals.get(i).start)
                return false;
        }

        return true;
    }

}
