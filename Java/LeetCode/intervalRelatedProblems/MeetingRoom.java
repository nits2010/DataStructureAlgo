package Java.LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-20
 * Description: Leetcode: Meeting Rooms
 * Meeting Room :1 https://leetcode.com/problems/meeting-rooms/
 * Meeting Room :2 https://leetcode.com/problems/meeting-rooms-ii/
 *
 * [FACEBOOK], [GOOGLE] , [SNAPCHAT], [UBER]
 */
public class MeetingRoom {

    public static void main(String args[]) {

        testMeetingRoomI();
        testMeetingRoomII();
    }

    private static void testMeetingRoomII() {
    }

    private static void testMeetingRoomI() {
        MeetingRoomI meetingRoomI = new MeetingRoomI();

        testMeetingRoomI(meetingRoomI, Arrays.asList(new Interval(11, 30), new Interval(0, 10), new Interval(5, 15)));
        testMeetingRoomI(meetingRoomI, Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 25)));
        testMeetingRoomI(meetingRoomI, Arrays.asList(new Interval(0, 10), new Interval(15, 20), new Interval(11, 14)));

    }

    private static void testMeetingRoomI(MeetingRoomI meetingRoomI, List<Interval> intervals) {

        System.out.println("Testing intervals : " + intervals + " Person can attend all meeting :" + meetingRoomI.personCanAttendAllMetings(intervals));

    }
}


/**
 * https://leetcode.com/problems/meeting-rooms/
 * Meeting
 * * Question: Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * * For example,
 * * Given [[0, 30],[5, 10],[15, 20]],
 * * return false.
 *
 * http://buttercola.blogspot.com/2015/08/leetcode-meeting-rooms.html
 */
class MeetingRoomI {

    /**
     * If person wants to attend all meeting then there should not be any overlapping meeting.
     * <p>
     * Algo: Sort the intervals by start time and see any overlapping intervals are there
     * Overlapping intervals: [s1, e1] & [s2, e2]
     * <p>
     * if e1 > s2 or s1<=s2 && e1>=e2
     *
     * @param intervals
     * @return
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

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * Example1
 *
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 * Example2
 *
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 */
class MeetingRoomII{

}