package Java.companyWise.facebook.intervalRelatedProblems;

import javafx.util.Pair;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-19
 * Description: https://leetcode.com/discuss/interview-experience/301164/Facebook-onsite-interview-experience-2019
 * <p>
 * You are given a schedule of tasks to work on. Each tasks has a start and an end time [start, end] where end > start. Find out for the given schedule:
 * 1. in what intervals you are working (at least 1 task on-going): https://leetcode.com/problems/merge-intervals
 * <p>
 * 2. in what intervals you are multi-tasking (at least 2 tasks on-going): https://i.imgur.com/nkY1cO9.png

 * Input: [[1, 10], [2, 6], [9, 12], [14, 16], [16, 17]]
 * Output:
 * union [[1, 12], [14, 17]]
 * intersection: [[2, 6], [9, 10]]
 * <p>
 * https://leetcode.com/discuss/interview-question/338948/facebook-onsite-schedule-of-tasks/309123
 * [FACEBOOK]
 */


class Interval {
    int start, end;


    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + ']';
    }
}

public class IntervalWorkingOnMultiTasking {


    private static List<Interval> buildInput1() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(9, 12));
        intervals.add(new Interval(14, 16));
        intervals.add(new Interval(16, 17));

        return intervals;
    }

    //[[1,10],[2,6], [7,12], [9,13], [15,18], [16,20]
    private static List<Interval> buildInput2() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(7, 12));
        intervals.add(new Interval(9, 13));
        intervals.add(new Interval(15, 18));
        intervals.add(new Interval(16, 20));

        return intervals;
    }

    //[[1,10],[2,6], [7,12], [9,13], [14.16], [15,18], [17,20]
    private static List<Interval> buildInput3() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(7, 12));
        intervals.add(new Interval(9, 13));
        intervals.add(new Interval(14, 16));
        intervals.add(new Interval(15, 18));
        intervals.add(new Interval(17, 20));

        return intervals;
    }

    //[[1,2],[3,4], [5,6], [7,8]
    private static List<Interval> buildInput4() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(3, 4));
        intervals.add(new Interval(5, 6));
        intervals.add(new Interval(7, 8));

        return intervals;
    }

    //[1,10],[2,6],[3,7],[4,8]
    private static List<Interval> buildInput5() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 7));
        intervals.add(new Interval(4, 8));

        return intervals;
    }


    public static void main(String []args) {


        test(buildInput1());
        test(buildInput2());
        test(buildInput3());
        test(buildInput4());
        test(buildInput5());
    }


    private static void test(List<Interval> intervals) {
        System.out.print("\n\nIntervals  " + intervals);

        WorkingMultiTasking sol = new WorkingMultiTasking();
        System.out.println("\nUsing (n*Logn) algorithm :" + sol.workingMultiTasking(intervals));
        System.out.println("Using (n) algorithm :" + sol.workingMultiTaskingLinear(intervals));

    }


}

class WorkingMultiTasking {


    /**
     * O(nlgon)
     * Often the trick for analyzing overlapping intervals is to make a sorted list (or tree-map) of the individual events at either end of the intervals.
     * Note that if the range of possible time-points is very small (like 0..23, for example), it would be more efficient to use a static array for this.
     * <p>
     * We iterate over the list of events, and check how each event changes the concurrency-counter:
     * <p>
     * Up to 1 or greater: create a new working interval;
     * Up to 2 or greater: create a new multitasking interval;
     * Down to 0: close the last working interval;
     * Down to 1 or 0: close the last multitasking interval.
     *
     * @param intervals
     * @return
     */
   public Pair<List<Interval>, List<Interval>> workingMultiTasking(List<Interval> intervals) {


        Map<Integer, Integer> map = new TreeMap<>();

        List<Interval> working = new ArrayList<>();
        List<Interval> multiTasking = new ArrayList<>();

        for (Interval i : intervals) {

            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }

        int next = 0;
        for (Integer event : map.keySet()) {

            int time = event;
            int prev = next;
            next += map.get(event);

            //Up to 1 or greater: create a new working interval;
            if (prev <= 0 && 1 <= next) working.add(new Interval(time, 0));

            //Up to 2 or greater: create a new multitasking interval;
            if (prev <= 1 && 2 <= next) multiTasking.add(new Interval(time, 0));

            //Down to 0: close the last working interval;
            if (prev >= 1 && 0 >= next) working.get(working.size() - 1).end = time;

            //Down to 1 or 0: close the last multitasking interval.
            if (prev >= 2 && 1 >= next) multiTasking.get(multiTasking.size() - 1).end = time;
        }
        return new Pair<>(working, multiTasking);


    }


    /**
     * O(n)
     * <p>
     * The problem we're trying to solve is that although the start-times are sorted, we can't assume that the end-times are sorted too.
     * Fortunately, we don't need to know all the end-times in order, because we don't need to know when concurrency drops from 4 tasks to 3 tasks, or goes up from 5 to 6.
     * All we care about is when concurrency changes between 0, 1 and 2 tasks.
     * That means that as we iterate over the tasks, we only need to keep track of the "last end-time we've encountered", and (if we're currently nested) "the second-to-last".
     *
     * @param intervals
     * @return
     */
   public Pair<LinkedList<Interval>, LinkedList<Interval>> workingMultiTaskingLinear(final List<Interval> intervals) {


        /**
         * This will hold a interval I was working
         */
        LinkedList<Interval> working = new LinkedList<>();

        /**
         * This will hold at what interval I was multitasking
         */
        LinkedList<Interval> multiTasking = new LinkedList<>();

        /**
         * This two will just be holding the end time of working and multitasking.
         * AS i've no task right now so its -1
         */
        int lastWorkingEndTime = -1;
        int lastMultiTaskingEndTime = -1;


        for (final Interval interval : intervals) {


            /**
             * 1. Do we have any task right now?  working.isEmpty() : No then take a task and update my endTime
             * 2. If the new task come after i finished the last working task {lastWorkingEndTime < interval.start}, then i'm starting a new task.
             */
            if (working.isEmpty() || lastWorkingEndTime < interval.start) {
                working.add(new Interval(interval.start, interval.end));
                lastWorkingEndTime = interval.end;

            } else {

                /**
                 * Since this new task come before i finished the last task?, then i'm multi-tasking for sure
                 */
                if (lastWorkingEndTime > interval.start) {

                    /**
                     * 1. Do i have any interval i was multi tasking ? multiTasking.isEmpty(); if not then add the current task as my multi tasking because we identify that i'm doing multitask
                     * 2. Is the new task start after my last multi task time ? lastMultiTaskingEndTime < interval.start means i've must started another multi task at different interval
                     */
                    if (multiTasking.isEmpty() || lastMultiTaskingEndTime < interval.start) {
                        //Intersection of intervals

                        int start = interval.start; //Note when i stared the new multi task work
                        int end = Math.min(interval.end, lastWorkingEndTime); //since i'm doing multi tasking, then the end time should be within my last working task (then only i'm doing multi task)

                        multiTasking.add(new Interval(start, end));

                        lastMultiTaskingEndTime = end;


                    } else if (lastMultiTaskingEndTime < interval.end) {

                        //Intersection of intervals
                        int end = Math.min(interval.end, lastWorkingEndTime);


                        multiTasking.getLast().end = end;
                        lastMultiTaskingEndTime = end;
                    }

                }

                //Find till what time i'm doing the task; Union of intervals
                working.getLast().end = Math.max(lastWorkingEndTime, interval.end);
                lastWorkingEndTime = working.getLast().end;
            }
        }
        return new Pair<>(working, multiTasking);


    }


}
