package Java.companyWise.facebook.intervalRelatedProblems;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-19
 * Description: https://leetcode.com/discuss/interview-experience/301164/Facebook-onsite-interview-experience-2019
 * <p>
 * You are given a shcedule of tasks to work on. Each tasks has a start and an end time [start, end] where end > start. Find out for the given schedule:
 * in what intervals you are working (at least 1 task on-going): https://leetcode.com/problems/merge-intervals
 * <p>
 * in what intervals you are multi-tasking (at least 2 tasks on-going): https://i.imgur.com/nkY1cO9.png
 * <p>
 * In other words, find union and intersection of a list of intervals. The input is sorted by start time.
 * <p>
 * Input: [[1, 10], [2, 6], [9, 12], [14, 16], [16, 17]]
 * Output:
 * union [[1, 12], [14, 17]]
 * intersection: [[2, 6], [9, 10]]
 */


class Interval implements Cloneable {
    int start, end;
    boolean isMerged = false;


    @Override
    protected Interval clone() {
        return new Interval(this.start, this.end);
    }

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


    public static void main(String args[]) {


        test(buildInput1());
        test(buildInput2());
        test(buildInput3());
        test(buildInput4());
        test(buildInput5());
    }


    private static void test(List<Interval> intervals) {
        System.out.print("\n\n\n Testing intervals  " + intervals);

        WorkingMultiTasking solll = new WorkingMultiTasking();
        solll.workingMultiTasking(intervals);

//
//        SolutionIntervalWorkingOnMultiTasking sol = new SolutionIntervalWorkingOnMultiTasking();
//
//        System.out.println("intervals you are working : " + sol.workingOn(intervals));
//        System.out.println("what intervals you are multi-tasking : " + sol.multiTasking(intervals));


    }


}

class SolutionIntervalWorkingOnMultiTasking {


    /**
     * Working on intervals would be those intervals which are overlapping and get merged (union).
     *
     * @param intervals
     * @return
     */
    public List<Interval> workingOn(List<Interval> intervals) {

        if (intervals == null || intervals.isEmpty())
            return intervals;

        /**
         * will hold those intervals we are working on.
         */
        final List<Interval> workingOnIntervals = new ArrayList<>(intervals.size());

        /**
         * Since the intervals are already sorted by start time, we can skip sorting. In case needed then uncomment the below code
         */

        // Sort them by start time / if clash then end time
//        Collections.sort(intervals, (o1, o2) -> {
//            if (o1.start == o2.start)
//                return o1.end - o2.end; //if clash then end time
//            return o1.start - o2.start; //sort them by start time
//        });


        //Pick the first interval as our solution, as this will always be there in final list [ since data is sorted by start time ]
        workingOnIntervals.add(new Interval(intervals.get(0).start, intervals.get(0).end));


        //Iterate through the list and merge the overlapping intervals
        for (int i = 1; i < intervals.size(); i++) {

            Interval previousInterval = workingOnIntervals.get(workingOnIntervals.size() - 1);
            Interval currentInterval = intervals.get(i);

            if (areOverlap(previousInterval, currentInterval)) {

                //merge this to interval: take Union;
                previousInterval.start = Math.min(previousInterval.start, currentInterval.start);
                previousInterval.end = Math.max(previousInterval.end, currentInterval.end);

                //don't need to add back to list, as this is the reference of last interval, it will automatically get reflected due to referencing.
            } else {
                //If they don't overlap, then we need to start a new interval;
                workingOnIntervals.add(new Interval(currentInterval.start, currentInterval.end));
            }

        }

        return workingOnIntervals;
    }

    /**
     * Check given intervals are overlapping intervals;
     * They overlap if
     * a.end >= b.start && b.end >= a.start
     *
     * @param a
     * @param b
     * @return
     */
    private boolean areOverlap(Interval a, Interval b) {

        return (a.end >= b.start && b.end >= a.start);

    }

    /**
     * Multi tasking interval would be those interval where people are multi tasking between intervals.
     * For example:
     * [1,10][2,6][9,12]
     * <p>
     * if we draw the line of intervals, we'll see during working on [1,10], we took another job at interval [2,6] {this is within this interval} and [9,12] {which is partially within this interval}
     * This drives two types of intervals;
     * 1. Those intervals which are completely within this interval like [1,10] & [2,6] ; we'll chose [2,6] but keep [1,10] till we find all the within and partially overlapping intervals.
     * 2. Those intervals which are partially overlapping with this interval like [1,10] and [9,12]; here these two interval partially overlap the we'll do intersection and discard [1,10] as this
     * interval we have find out all the overlapping and partially overlapping interval.
     * <p>
     * <p>
     * How to find the first and second intervals;  [Hint: keep in mind they are sorted by start time]
     * Say two interval 'a' and 'b';
     * 1.  if a.start <=b.start && a.end>=b.end; this means b is within the interval of 'a'
     * 2.  if a.end >= b.start and a.end<=b.end  this means they are partially overlap because a.end>=b.start which says 'b' started before 'a' ends and a.end<=b.end says 'a' gets end before
     * 'b' got end.
     *
     * @param intervals
     * @return
     */

    public List<Interval> multiTasking(List<Interval> intervals) {

        if (intervals == null || intervals.isEmpty())
            return intervals;


        List<Interval> multiTasking = new ArrayList<>(intervals.size());

        Stack<Interval> stack = new Stack<>();

        stack.push(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {

            if (!stack.isEmpty() && isWithinInterval(stack.peek(), intervals.get(i))) {
                multiTasking.add(intervals.get(i));
                continue;
            }

            if (!stack.isEmpty() && isPartiallyOverlap(stack.peek(), intervals.get(i))) {
                stack.peek().start = Math.max(stack.peek().start, intervals.get(i).start);
                stack.peek().end = Math.min(stack.peek().end, intervals.get(i).end);

                stack.peek().isMerged = true;
                if (stack.peek().start == stack.peek().end)
                    stack.pop();

                continue;
            }

            if (!stack.isEmpty() && isOutSide(stack.peek(), intervals.get(i))) {
                Interval pop = stack.pop();
                if (pop.isMerged)
                    multiTasking.add(pop);

                stack.push(intervals.get(i));


            }
        }


        return multiTasking;
    }


    /**
     * Those intervals which are completely within this interval like [1,10] & [2,6]
     * <p>
     * 1. (a.start <= b.start && a.end >= b.end);
     * Or
     * 2. a.end >= b.start && a.end >=b.end since intervals are sorted and we know that start < end
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isWithinInterval(Interval a, Interval b) {

        return (a.start <= b.start && a.end >= b.end);
    }

    /**
     * Those intervals which are partially overlapping with this interval like [1,10] and [9,12];
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isPartiallyOverlap(Interval a, Interval b) {

//        return (a.end >= b.start && a.end <= b.end); if (temp[k][1] >= intervals[i][0] && intervals[i][1] >= temp[k][0]) {
        return (a.end >= b.start && a.start <= b.end);
    }

    /**
     * are these interval out side to each other ;
     * like [2,6] and [7,12];
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isOutSide(Interval a, Interval b) {
        return (a.end < b.start);
    }


}

class WorkingMultiTasking {


    class Solution {

        List<Interval> working = new ArrayList<>();
        List<Interval> multiTasking = new ArrayList<>();

        @Override
        public String toString() {
            return "{" +
                    "working=" + working +
                    ", multiTasking=" + multiTasking +
                    '}';
        }
    }

    /**
     * Often the trick for analyzing overlapping intervals is to make a sorted list (or tree-map) of the individual events at either end of the intervals. Note that if the range of possible time-points is very small (like 0..23, for example), it would be more efficient to use a static array for this.
     * <p>
     * We iterate over the list of events, and check how each event changes the concurrency-counter:
     * <p>
     * Up to 1 or greater: create a new working interval;
     * Up to 2 or greater: create a new multitasking interval;
     * Down to 0: close the last working interval;
     * Down to 1 or 0: close the last multitasking interval.
     *
     * @param intervals
     */
    void workingMultiTasking(List<Interval> intervals) {

        Map<Integer, Integer> map = new TreeMap<>();

        for (Interval i : intervals) {

            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }

        Solution solution = new Solution();
        int next = 0;
        for (Integer event : map.keySet()) {

            int time = event;
            int prev = next;
            next += map.get(event);

            if (prev <= 0 && 1 <= next) solution.working.add(new Interval(time, 0));
            if (prev <= 1 && 2 <= next) solution.multiTasking.add(new Interval(time, 0));
            if (prev >= 1 && 0 >= next) solution.working.get(solution.working.size() - 1).end = time;
            if (prev >= 2 && 1 >= next) solution.multiTasking.get(solution.multiTasking.size() - 1).end = time;
        }

        System.out.println(solution);

    }



}
