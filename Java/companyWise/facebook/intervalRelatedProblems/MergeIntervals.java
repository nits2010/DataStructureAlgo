package Java.companyWise.facebook.intervalRelatedProblems;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-19
 * Description: https://aonecode.com/aplusplus/interviewctrl/getInterview/7798119161075951615
 * # There's a room with a TV and people are coming in and out to watch it. The TV is on only when there's at least a person in the room.
 * # For each person that comes in, we record the start and end time. We want to know for how long the TV has been on. In other words:
 * # Given a list of arrays of time intervals, write a function that calculates the total amount of time covered by the intervals.
 * # For example:
 * # input = [(1,4), (2,3)]
 * # > 3
 * # input = [(4,6), (1,2)]
 * # > 3
 * # input = {{1,4}, {6,8}, {2,4}, {7,9}, {10, 15}}
 * # > 11
 * <p>
 * [FACEBOOK]
 */

public class MergeIntervals {


    public static void main(String args[]) {
        List<Interval> intervals = Arrays.asList(
                new Interval(1, 4),
                new Interval(2, 3));

        List<Interval> intervals2 = Arrays.asList(
                new Interval(4, 6),
                new Interval(1, 2));

        List<Interval> intervals3 = Arrays.asList(
                new Interval(1, 4),
                new Interval(6, 8),
                new Interval(2, 4),
                new Interval(7, 9),
                new Interval(10, 15));

        System.out.println(totalTime(intervals));
        System.out.println(totalTime(intervals2));
        System.out.println(totalTime(intervals3));
    }

    private static int totalTime(List<Interval> intervals) {
        if (null == intervals || intervals.isEmpty())
            return 0;

        Collections.sort(intervals, (o1, o2) -> {
            if (o1.start == o2.start)
                return o1.end - o2.end;
            return o1.start - o2.start;
        });


        List<Interval> mergedInterval = union(intervals);

        int totalTime = 0;
        for (Interval i : mergedInterval) {

            totalTime += i.end - i.start;
        }

        return totalTime;

    }

    private static List<Interval> union(List<Interval> intervals) {



        List<Interval> mergedInterval = new ArrayList<>();
        mergedInterval.add(new Interval(intervals.get(0).start, intervals.get(0).end));


        for (int i = 1; i < intervals.size(); ++i) {

            Interval last = mergedInterval.get(mergedInterval.size() - 1);
            Interval current = intervals.get(i);

            if (last.end >= current.start && current.end >= last.start) {
                last.start = Math.min(last.start, current.start);
                last.end = Math.max(last.end, current.end);
            } else
                mergedInterval.add(new Interval(current.start, current.end));

        }
        return mergedInterval;
    }
}
