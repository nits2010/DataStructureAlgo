package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.MyCalender._729;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/27/2024
 * Question Category: 729. My Calendar I
 * Description: https://leetcode.com/problems/my-calendar-i/description/
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
 * <p>
 * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
 * <p>
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * Implement the MyCalendar class:
 * <p>
 * MyCalendar() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * Output
 * [null, true, false, true]
 * <p>
 * Explanation
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
 * myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= start < end <= 109
 * At most 1000 calls will be made to book.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch
 * @Design
 * @SegmentTree
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Amazon
 * @Apple
 * @Google
 * @Intuit
 * @Microsoft
 * @Uber
 * @Editorial https://leetcode.com/problems/my-calendar-i/solutions/5841322/multiple-solution-best-sol-simple-scan-binary-search-binary-search-tree-red-black-tree
 */
public class MyCalendarI_729 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{10, 20}, {15, 25}, {20, 30}}, new boolean[]{true, false, true});
        test &= test(new int[][]{{47, 50}, {33, 41}, {39, 45}, {33, 42}, {25, 32}, {26, 35}, {19, 25}, {3, 8}, {8, 13}, {18, 27}}, new boolean[]{true, true, false, false, true, false, true, true, true, false});
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] intervals, boolean[] expected) {

        System.out.println("-----------------------------------------------------------");
        System.out.println(" Interval : " + CommonMethods.toStringInline(intervals) + " expected : " + Arrays.toString(expected));

        boolean[] result;
        boolean FinalPass = true;
        boolean pass;
        int i;


        MyCalendarScan.MyCalendar myCalendar = new MyCalendarScan.MyCalendar();
        result = new boolean[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            result[i++] = myCalendar.book(interval[0], interval[1]);
        }
        pass = Arrays.equals(result, expected);
        System.out.println(" Scan : " + Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        FinalPass &= pass;

        MyCalendarBinarySearch.MyCalendar myCalendarBS = new MyCalendarBinarySearch.MyCalendar();
        result = new boolean[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            result[i++] = myCalendarBS.book(interval[0], interval[1]);
        }
        pass = Arrays.equals(result, expected);
        System.out.println(" BS : " + Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        FinalPass &= pass;


        MyCalendarBST.MyCalendar myCalendarBST = new MyCalendarBST.MyCalendar();
        result = new boolean[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            result[i++] = myCalendarBST.book(interval[0], interval[1]);
        }
        pass = Arrays.equals(result, expected);
        System.out.println(" BST : " + Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        FinalPass &= pass;

        MyCalendarTreeMap.MyCalendar myCalendarTreeMap = new MyCalendarTreeMap.MyCalendar();
        result = new boolean[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            result[i++] = myCalendarTreeMap.book(interval[0], interval[1]);
        }
        pass = Arrays.equals(result, expected);
        System.out.println(" TreeMap : " + Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));


        MyCalendarTreeSet.MyCalendar myCalendarTreeSet = new MyCalendarTreeSet.MyCalendar();
        result = new boolean[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            result[i++] = myCalendarTreeSet.book(interval[0], interval[1]);
        }
        pass = Arrays.equals(result, expected);
        System.out.println(" TreeSet : " + Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        FinalPass &= pass;


        MyCalendarLineSweep.MyCalendar myCalendarLineSweep = new MyCalendarLineSweep.MyCalendar();
        result = new boolean[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            result[i++] = myCalendarLineSweep.book(interval[0], interval[1]);
        }
        pass = Arrays.equals(result, expected);
        System.out.println(" LineSweep : " + Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        FinalPass &= pass;

        return FinalPass;

    }


}

/**
 * T/S: O(n) / O(n)
 */
class MyCalendarScan {
    static class MyCalendar {

        List<int[]> list;

        public MyCalendar() {
            list = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            if (list.isEmpty()) {
                list.add(new int[]{start, end});
                return true;
            }
            /**
             * Add at end
             * Existing:    [10 ----------- 20)
             * New:                 [15 ----------- 25)
             * Add at start
             * Existing:                        [10 ----------- 20)
             * New:                 [5 ----------------15)
             *
             *
             * Math.max(existingStart, start) = 15
             * Math.min(existingEnd, end) = 20
             * => 15 < 20 (overlap exists)
             */
            for (int[] existing : list) {
                int existingStart = existing[0];
                int existingEnd = existing[1];

                // if (start < existingEnd && existingStart < end ) return false;
                if (Math.max(existingStart, start) < Math.min(existingEnd, end)) return false;

            }

            /**
             * Existing:    [10 ----------- 20)
             * New:                            [20 ----------- 30)
             *
             * Existing:                [30 ----------- 40)
             * New:      [20 ----------- 30)
             *
             * Math.max(existingStart, start) = 20
             * Math.min(existingEnd, end) = 20
             * => 20 < 20 (no overlap)
             */

            list.add(new int[]{start, end});
            return true;
        }

    }
}

/**
 * T/S: O(n) / O(n)
 */
class MyCalendarBinarySearch {
    static class MyCalendar {

        List<int[]> sortedList;

        public MyCalendar() {
            sortedList = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            if (sortedList.isEmpty()) {
                sortedList.add(new int[]{start, end});
                return true;
            }

            int insertPos = binarySearch(start);

            //three cases
            //add at start; insertPos = 0; Then we need to make sure that they are not overlapping; means existingStart > end
            //add at end; insertPos = n-1; Then we need to make sure that they are not overlapping; means start > existingEnd
            //add in between: 0<insertPos<n-1 ; its combination of the above two


            /**
             *                      0
             * Existing:    [10 ---left-------- 20)
             * New:                 [15 ----------- 25)
             * insertPos = 0
             * => 15 < 20 (overlap exists)
             */
            //Prev event overlapping with new event
            if (insertPos > 0 && start < sortedList.get(insertPos - 1)[1])
                return false;

            /**
             *                  0                       1
             * Existing:    [10 ----------- 20)     [25 ----------- 30)
             * New:                           [21 ----------- 26)
             * insertPos = 1
             * => 25 < 26 (overlap exists)
             */
            //Next event overlapping with new event
            if (insertPos < sortedList.size() && sortedList.get(insertPos)[0] < end)
                return false;

            //O(n)
            sortedList.add(insertPos, new int[]{start, end});
            return true;
        }

        /**
         * Binary search on list, this list is sorted based on start time.
         *
         * @param targetStart
         * @param end
         * @return the position at which this start should be inserted.
         * <p>
         * T: O(log(n))
         */
        private int binarySearch(int targetStart) {
            int low = 0;
            int high = sortedList.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midStart = sortedList.get(mid)[0];

                if (midStart < targetStart) low = mid + 1;
                else high = mid - 1;
            }
            return low;
        }
    }
}

/**
 * Build a binary search tree of a given interval based on start time. [sorting start time].
 * A new interval can go either left side, if root's start > its end
 * A new interval can go either right side, if root's end < its start
 * If an interval overlaps with the root, then it won't go either left or right since either upcoming interval start is in between of current root or its end in between of current root.
 * <p>
 * T/S: O(n) as it can become skew tree, O(n)
 */
class MyCalendarBST {
    static class MyCalendar {

        static class Interval {
            int start, end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        static class BST {
            Interval data;
            BST left, right;

            public BST(Interval data) {
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }

        //Bst based on start time
        BST calender;

        public MyCalendar() {
        }

        public boolean book(int start, int end) {
            return insert(start, end);
        }

        private boolean insert(int start, int end) {
            if (calender == null) {
                calender = new BST(new Interval(start, end));
                return true;
            }

            BST root = calender;
            while (true) {

                //go left ?
                if (root.data.start >= end) { // if the current interval start is bigger than the end, then it would bigger than its start as well.


                    //if there is an empty slot
                    if (root.left == null) {
                        root.left = new BST(new Interval(start, end));
                        return true;
                    }

                    root = root.left;
                } else if (root.data.end <= start) { // if the current interval end is smaller than start, then it would smaller than its end as well.

                    if (root.right == null) {
                        root.right = new BST(new Interval(start, end));
                        return true;
                    }

                    root = root.right;
                } else {

                    //if this interval lies in between, then we can't add it.
                    return false;
                }
            }

        }


    }
}

/**
 * https://chatgpt.com/share/66f7004e-2e80-8011-9f3c-11ca7947fe4a
 * T/S: O(log(n)), O(n)
 */
class MyCalendarTreeMap {

    static class MyCalendar {


        /**
         * We'll store the events in a TreeMap where:
         * The key is the start time of the event.
         * The value is the end time of the event.
         * <p>
         * This map keeps the start time in a sorted manner
         */
        TreeMap<Integer, Integer> startVsEndMap;

        public MyCalendar() {
            startVsEndMap = new TreeMap<>();
        }

        //  O(log(n)); TreeMap is implemented as a red-black tree.
        public boolean book(int start, int end) {
            // Find the closest event that starts after the new event (ceilingEntry).
            // O(log(n))
            Integer nextEventStart = startVsEndMap.ceilingKey(start); //Finds the first event whose start time is greater than or equal to start.

            //Find the closest event that ends before the new event (floorEntry).
            // O(log(n))
            Integer prevEventStart = startVsEndMap.floorKey(start); //Finds the first event whose start time is less than or equal to start.

            // Check if there is an overlap with the next event,
            // The next event must start after the current event ends, if not its overlapped
            if (nextEventStart != null && nextEventStart < end) return false;

            // Check if there is an overlap with the previous event
            // The previous event must end before the current event starts, if not its overlapped
            if (prevEventStart != null && start < startVsEndMap.get(prevEventStart)) return false;


            // Add the new event
            // O(log(n)); TreeMap is implemented as a red-black tree.
            startVsEndMap.put(start, end);
            return true;
        }
    }


}

/**
 * https://chatgpt.com/share/66f7004e-2e80-8011-9f3c-11ca7947fe4a
 * T/S: O(log(n)), O(n)
 */
class MyCalendarTreeSet {

    static class MyCalendar {
        static class Interval {
            int start, end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        /**
         * We'll store the events in a TreeSet where:
         * An object interval with start time and end time of the event.
         * sorted by start time.
         */
        TreeSet<Interval> sortedTreeSet;

        public MyCalendar() {
            sortedTreeSet = new TreeSet<>(Comparator.comparingInt(o -> o.start));
        }

        //  O(log(n)); TreeMap is implemented as a red-black tree.
        public boolean book(int start, int end) {
            Interval newEvent = new Interval(start, end);

            // Finds the smallest event whose start time is greater than or equal to newEvent.start.
            // O(log(n))
            Interval nextEvent = sortedTreeSet.ceiling(newEvent); //Finds the first event whose start time is greater than or equal to start.

            // Finds the largest event whose start time is less than or equal to newEvent.start.
            // O(log(n))
            Interval prevEvent = sortedTreeSet.floor(newEvent); //Finds the first event whose start time is less than or equal to start.

            // Check if there is an overlap with the next event,
            // The next event must start after the current event ends, if not its overlapped
            if (nextEvent != null && nextEvent.start < end) return false;

            // Check if there is an overlap with the previous event
            // The previous event must end before the current event starts, if not its overlapped
            if (prevEvent != null && prevEvent.end > start) return false;


            // Add the new event
            // O(log(n)); TreeSet is implemented as a red-black tree.
            sortedTreeSet.add(newEvent);
            return true;
        }
    }


}


/**
 * https://chatgpt.com/share/66f7004e-2e80-8011-9f3c-11ca7947fe4a
 * T/S: O(log(n)), O(n)
 */
class MyCalendarLineSweep {

    static class MyCalendar {

        TreeMap<Integer, Integer> treeMap;

        public MyCalendar() {
            treeMap = new TreeMap<>();
        }

        //  O(log(n)); TreeMap is implemented as a red-black tree.
        public boolean book(int start, int end) {
            treeMap.merge(start, 1, Integer::sum);
            if (treeMap.merge(end, -1, Integer::sum) == 0)
                treeMap.remove(end);

            //check how many active booking at any point of time
            int activeBookings = 0;
            for (Integer value : treeMap.values()) {
                activeBookings += value;
                if (activeBookings > 1) {
                    //undo the changes, so that it does not affect for upcoming bookings
                    if (treeMap.merge(start, -1, Integer::sum) == 0)
                        treeMap.remove(start);
                    treeMap.merge(end, 1, Integer::sum);
                    return false;
                }
            }
            return true;
        }
    }


}