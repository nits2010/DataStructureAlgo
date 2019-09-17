package Java.LeetCode.intervalRelatedProblems.meeting.room;

import Java.LeetCode.intervalRelatedProblems.Interval;

import java.util.*;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * Example1
 * <p>
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 * Example2
 * <p>
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 * <p>
 * Input intervals = [[0,10], [11,30], [5,15]
 * output : 3
 * http://www.learn4master.com/algorithms/leetcode-meeting-rooms-ii-java
 * [FACEBOOK], [GOOGLE] , [SNAPCHAT], [UBER]
 */

public class MeetingRoomII {

    public static void main(String []args) {

        System.out.println("\n\nMeeting Room II tests");
        MeetingRoomII meetingRoom = new MeetingRoomII();

        /**
         * Given [[0, 30],[5, 10],[15, 20]],
         * return 2.
         */
        test(meetingRoom, Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)));

        /**
         * Given [[0,30] , [ 5,10], [15,25]
         * return 2
         */
        test(meetingRoom, Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 25)));


        /**
         * Given [[0,10] , [ 15,20], [11,14]
         * return 1
         */
        test(meetingRoom, Arrays.asList(new Interval(0, 10), new Interval(15, 20), new Interval(11, 14)));

        /**
         * Given [[0,10] , [ 5,15], [11,30]
         * return 3
         */
        test(meetingRoom, Arrays.asList(new Interval(0, 10), new Interval(5, 15), new Interval(11, 30)));

        /**
         * Given [0,10]
         * return 1
         */
        test(meetingRoom, Arrays.asList(new Interval(0, 10)));
    }


    private static void test(MeetingRoomII meetingRoom, List<Interval> intervals) {
        System.out.println("Using List: ");
        System.out.println("Testing intervals : " + intervals + " Person can attend all meeting :" + meetingRoom.minimumMeetingRoomUsingList(intervals));

        System.out.println("\n Using heap [optimized]: ");
        System.out.println("Testing intervals : " + intervals + " Person can attend all meeting :" + meetingRoom.minimumMeetingRooms(intervals));


    }


    /**
     * Since we need to find the "minimum" rooms for meeting. For that we need to find how many are overlapping to each other,
     * if they overlap then we need another room but if don't then we can have same room running two meeting at different time.
     * <p>
     * Algo:
     * 1. Sort by start time
     * 2. Find no. of overlapping intervals
     * 3. that will be your output
     * <p>
     * Overlapping: [s1,e1] , [s2,e2]
     * if e1 > s2 or s1<=s2 && e1>=e2
     * <p>
     * we'll have a min heap of end times only for end time find
     *
     * @param intervals
     * @return O(nlogn)
     */

    public int minimumMeetingRooms(List<Interval> intervals) {

        if (intervals == null || intervals.isEmpty())
            return 0;

        /**
         * Sort the interval by start time
         */
        Collections.sort(intervals, ((o1, o2) -> {
            if (o1.start == o2.start)
                return o1.end - o2.end;
            return o1.start - o2.start;
        }));


        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {

            int lastEnd = minHeap.peek();
            int currentStart = intervals.get(i).start;

            if (overlap(lastEnd, currentStart))
                minHeap.offer(intervals.get(i).end);
            else {
                minHeap.poll();
                minHeap.offer(intervals.get(i).end);

            }

        }

        return minHeap.size();
    }


    /**
     * Since we need to find the "minimum" rooms for meeting. For that we need to find how many are overlapping to each other,
     * if they overlap then we need another room but if don't then we can have same room running two meeting at different time.
     * <p>
     * Algo:
     * 1. Sort by start time
     * 2. Find no. of overlapping intervals
     * 3. that will be your output
     * <p>
     * Overlapping: [s1,e1] , [s2,e2]
     * if e1 > s2 or s1<=s2 && e1>=e2
     * <p>
     * we'll have a List of intervals and iterate over it to find what was the last room we can assign
     *
     * @param intervals
     * @return O(n * n)
     */
    public int minimumMeetingRoomUsingList(final List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty())
            return 0;

        /**
         * Sort the interval by start time
         */
        Collections.sort(intervals, ((o1, o2) -> {
            if (o1.start == o2.start)
                return o1.end - o2.end;
            return o1.start - o2.start;
        }));

        List<Interval> lastIntervals = new ArrayList<>();
        lastIntervals.add(new Interval(intervals.get(0).start, intervals.get(0).end));

        for (int i = 1; i < intervals.size(); i++) {
            int idx = findOverLappingInterval(lastIntervals, intervals.get(i));

            if (idx < 0)
                lastIntervals.add(new Interval(intervals.get(i).start, intervals.get(i).end));
            else
                lastIntervals.get(idx).end = intervals.get(i).end;
        }

        return lastIntervals.size();
    }

    //O(n)
    private int findOverLappingInterval(List<Interval> lastIntervals, Interval interval) {

        for (int i = 0; i < lastIntervals.size(); i++)
            if (lastIntervals.get(i).end <= interval.start)
                return i;

        return -1;
    }

    private boolean overlap(int end, int start) {
        if (end > start)
            return true;
        return false;
    }
}
