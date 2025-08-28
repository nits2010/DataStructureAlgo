package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._759;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Author: Nitin Gupta
 * Date: 6/4/2025
 * Question Title: 759. Employee Free Time
 * Link: https://leetcode.com/problems/employee-free-time/description/
 * https://leetcode.ca/all/759.html
 * Description: We are given a list schedule of employees, which represents the working time for each employee.
 * <p>
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * <p>
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * <p>
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined). Also, we wouldn’t include intervals like [5, 5] in our answer, as they have zero length.
 * <p>
 * Example 1:
 * <p>
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * <p>
 * Output: [[3,4]]
 * <p>
 * Explanation: There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * <p>
 * We discard any intervals that contain inf as they aren’t finite.
 * <p>
 * Example 2:
 * <p>
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * <p>
 * Output: [[5,6],[7,9]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
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
 * @hard
 * @Array
 * @Sorting
 * @LineSweep
 * @Heap(PriorityQueue)
 * @LeetCodeLockedProblem
 * @PremiumQuestion <p><p>
 * Company Tags
 * -----
 * @Airbnb
 * @Amazon
 * @Bloomberg
 * @DoorDash
 * @Google
 * @Intuit
 * @Microsoft
 * @Pinterest
 * @Uber
 * @Wayfair <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link Solution_LineSweep}
 */

public class EmployeeFreeTime_759 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(List.of(List.of(new Interval(1, 3), new Interval(6, 7))), List.of(new Interval(3, 6))));
        tests.add(test(List.of(List.of(new Interval(1, 2), new Interval(5, 6))), List.of(new Interval(2, 5))));
        tests.add(test(List.of(List.of(new Interval(1, 2), new Interval(5, 6)), List.of(new Interval(1, 3)), List.of(new Interval(4, 10))), List.of(new Interval(3, 4))));
        tests.add(test(List.of(List.of(new Interval(1, 3), new Interval(6, 7)), List.of(new Interval(2, 4)), List.of(new Interval(2, 5), new Interval(9, 12))), List.of(new Interval(5, 6), new Interval(7, 9))));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(List<List<Interval>> nums, List<Interval> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        List<Interval> output;
        boolean pass, finalPass = true;

        output = new Solution_MergeOverlapping().employeeFreeTime(nums);
        pass = CommonMethods.compareResultOutCome(convert(output), convert(expected), true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"MergeOverlapping", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_LineSweep().employeeFreeTime(nums);
        pass = CommonMethods.compareResultOutCome(convert(output), convert(expected), true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"LineSweep", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static private int[][] convert(List<Interval> intervals) {
        return intervals.stream().map(interval -> new int[]{interval.start, interval.end}).collect(Collectors.toList()).toArray(new int[0][]);

    }

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }


        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }


    /**
     * As there are n number of employees based on the schedules. Each employee can have multiple work times, and each work time is non-overlapping.
     * However, the work time b/w employees could be overlapping. Which makes the overall work time b/w employees could be overlapping.
     * Since we need to find the "common" free time b/w employees, then we have to make sure that all the overlapping work time b/w employees are captured first.
     * Once we have common overlapping work time, we can find the free time b/w those overlapping work times.
     * Steps:
     * 1. Flatten all intervals from all employees.
     * 2. Sort all intervals by start time.
     * 3. Merge overlapping intervals → this gives the combined "busy" time.
     * 4. Find Gaps between merged intervals = common free time.
     * <p>
     * Let:
     * E = Number of employees
     * <p>
     * K = Average number of intervals per employee
     * <p>
     * N = Total number of intervals = E × K
     * <p>
     * T: O(N log N)  ← Due to sorting
     * S: O(N)        ← For merged intervals list
     */
    static class Solution_MergeOverlapping {
        /**
         * @param schedule: a list schedule of employees
         * @return: Return a list of finite intervals
         */
        public List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
            int length = schedules.size();

            List<Interval> allSchedule = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                allSchedule.addAll(schedules.get(i));
            }


            //merge overlapping intervals
            List<Interval> mergedSchedules = mergeOverlapping(allSchedule);
            return getFreeTime(mergedSchedules);


        }

        private List<Interval> getFreeTime(List<Interval> mergedSchedules) {
            List<Interval> freeTime = new ArrayList<>();
            //scan through all mergedSchedules and find gaps
            for (int i = 1; i < mergedSchedules.size(); i++) {
                Interval current = mergedSchedules.get(i);
                Interval previous = mergedSchedules.get(i - 1);
                //previous and current can't be overlapped, hence get the gap
                if (previous.end < current.start) // if a valid interval is found
                    freeTime.add(new Interval(previous.end, current.start));

            }
            return freeTime;
        }

        private List<Interval> mergeOverlapping(List<Interval> allSchedule) {
            //sort the work time by start otherwise by end if start is same
            allSchedule.sort((a, b) -> a.start != b.start ? Integer.compare(a.start, b.start) : Integer.compare(a.end, b.end));

            //merge overlapping intervals
            List<Interval> mergedSchedules = new ArrayList<>();
            Interval prev = allSchedule.get(0);
            for (int i = 1; i < allSchedule.size(); i++) {
                Interval current = allSchedule.get(i);

                //is prev, current overlapping ?
                if (isOverlapping(prev, current)) {
                    //merge it
                    prev.end = Math.max(prev.end, current.end);

                } else {
                    //if they are not overlapping, then add it to mergedSchedules
                    mergedSchedules.add(prev);
                    prev = current;
                }
            }
            //add last interval
            mergedSchedules.add(prev);
            return mergedSchedules;

        }

        private boolean isOverlapping(Interval previous, Interval current) {
            return current.start <= previous.end;
        }
    }

    /**
     * The idea is similar to how we solve "Meeting Rooms II" or "Skyline" problems:
     * <p>
     * We convert all start and end times into events.
     * <p>
     * We sweep through the timeline, keeping track of how many employees are currently working.
     * <p>
     * A time range is a common free time when no one is working — i.e., active count is 0.
     * <p>
     * ✅ Steps of the Line Sweep Approach
     * Convert all intervals into events:
     * <p>
     * +1 for interval start
     * <p>
     * -1 for interval end
     * <p>
     * Sort the events by time (break ties: end before start).
     * <p>
     * Sweep through the events:
     * <p>
     * Maintain a counter active for how many employees are working.
     * <p>
     * When active == 0, mark a free time start.
     * <p>
     * When active > 0 again, mark a free time end.
     * <p>
     * Let:
     * E = Number of employees
     * <p>
     * K = Average number of intervals per employee
     * <p>
     * N = Total number of intervals = E × K
     * <p>
     * T: O(N log N)  ← Due to sorting
     * S: O(N)        ← For merged intervals list
     */
    static class Solution_LineSweep {

        class Event {
            int time;
            int startEnd; // 1: start, -1: end

            public Event(int time, int startEnd) {
                this.time = time;
                this.startEnd = startEnd;
            }
        }

        /**
         * @param schedule: a list schedule of employees
         * @return: Return a list of finite intervals
         */
        public List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
            int length = schedules.size();

            List<Event> events = new ArrayList<>();
            //flatten list and convert into events
            for (List<Interval> intervals : schedules) {
                for (Interval interval : intervals) {
                    events.add(new Event(interval.start, 1));
                    events.add(new Event(interval.end, -1));
                }
            }

            //sort the events by time
            // Step 2: Sort events by time, then type (end before start)
            events.sort((a, b) -> {
                if (a.time != b.time) return Integer.compare(a.time, b.time);
                return Integer.compare(a.startEnd, b.startEnd); // end (-1) before start (+1)
            });

            //line sweep it
            int activeWorkTime = 0;
            Integer previous = null; // no previous work
            List<Interval> freeTime = new ArrayList<>();
            for (Event event : events) {
                int time = event.time;
                int startEnd = event.startEnd;

                //if this is a free time,
                // if active == 0 means no one is working
                // and previous work is finished before this time

                if (activeWorkTime == 0 && previous != null && previous < time) {
                    freeTime.add(new Interval(previous, time));
                }

                activeWorkTime += startEnd;
                if (activeWorkTime == 0) // no one is working
                    previous = time; // assign a new time
            }
            return freeTime;
        }
    }
}
