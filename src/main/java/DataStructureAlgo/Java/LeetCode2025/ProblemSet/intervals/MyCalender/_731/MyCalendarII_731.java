package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.MyCalender._731;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/4/2025
 * Question Title: 731. My Calendar II
 * Link: https://leetcode.com/problems/my-calendar-ii/description/
 * Description: You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 * <p>
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 * <p>
 * The event can be represented as a pair of integers startTime and endTime that represents a booking on the half-open interval [startTime, endTime), the range of real numbers x such that startTime <= x < endTime.
 * <p>
 * Implement the MyCalendarTwo class:
 * <p>
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 * <p>
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
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
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch
 * @Design
 * @SegmentTree
 * @PrefixSum
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MyCalendarII_731 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10}, {25, 55}}, new Boolean[]{true, true, true, false, true, true}));
        tests.add(test(new int[][]{{10, 20}, {15, 25}, {20, 30}}, new Boolean[]{true, true, true}));
        tests.add(test(new int[][]{{47, 50}, {33, 41}, {39, 45}, {33, 42}, {25, 32}, {26, 35}, {19, 25}, {3, 8}, {8, 13}, {18, 27}}, new Boolean[]{true, true, true, false, true, true, true, true, true, false}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] events, Boolean[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"events", "Expected"}, true, events, expected);

        boolean pass, finalPass = true;

        Boolean[] output = new Boolean[expected.length];
        MyCalendarTwo_List myCalendarTwoList = new MyCalendarTwo_List();
        for (int i = 0; i < events.length; i++) {
            output[i] = myCalendarTwoList.book(events[i][0], events[i][1]);
        }
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"List", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Boolean[expected.length];
        MyCalendarTwo_LineSweep myCalendarTwoLineSweep = new MyCalendarTwo_LineSweep();
        for (int i = 0; i < events.length; i++) {
            output[i] = myCalendarTwoLineSweep.book(events[i][0], events[i][1]);
        }
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"LineSweep", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class MyCalendarTwo_List {

        class Event {
            int start, end;

            Event(int s, int e) {
                start = s;
                end = e;
            }
        }

        final List<Event> bookings; //contains all the events, singleBooked and doubleBooked
        final List<Event> doubleBookings; // contains the doubledBooked events only

        public MyCalendarTwo_List() {

            bookings = new ArrayList<>();
            doubleBookings = new ArrayList<>();

        }

        public boolean book(int startTime, int endTime) {

            Event event = new Event(startTime, endTime);
            if (bookings.isEmpty()) {
                bookings.add(event);
                return true;
            }

            //check if this is a triple booking ?
            if (isOverlappingBookings(doubleBookings, event)) {
                //this is a triple booking
                return false;
            }

            //means this event is not at least a triple booking, check for double booking.
            //if this is a double booking, then add it to doubleBooking, only the overlap parts.
            //adding overlap part will make sure that in future if any other booking comes
            //which is overlapping in double booking, then it is most likely to overlap the single booking as well and become triple booking

            //this is double booking, now add only overlapping part in double booking
            List<Event> events = getOverlappingBooking(bookings, event);
            doubleBookings.addAll(events);


            //this is a single booking, add in singleBookings
            bookings.add(event);
            return true;
        }

        //can be used binary Search to find  the insertPos and then check for overlapping but for that we need to make sure bookings are sorted.
        private boolean isOverlappingBookings(List<Event> bookings, Event event) {

            for (Event temp : bookings) {
                if (isOverlappingEvent(temp, event))
                    return true;
            }
            return false;

        }

        private boolean isOverlappingEvent(Event a, Event b) {
            if (a != null && b != null) {
                return Math.max(a.start, b.start) < Math.min(a.end, b.end); // [start,end) is a open interval
            }
            return false;
        }

        //Since the new event could overlap with multiple events, we need to find all of them and make sure they are overlapping and get a new event from them which will become doubleBooked events.
        private List<Event> getOverlappingBooking(List<Event> bookings, Event event) {
            List<Event> events = new ArrayList<>();
            for (Event temp : bookings) {
                if (isOverlappingEvent(temp, event)) {
                    events.add(getOverlappingEvent(temp, event));
                }

            }
            return events;
        }


        private Event getOverlappingEvent(Event a, Event b) {
            int start = Math.max(a.start, b.start);
            int end = Math.min(a.end, b.end);

            return new Event(start, end);
        }
    }


    //Line Sweep algo
    static class MyCalendarTwo_LineSweep {

        TreeMap<Integer, Integer> bookings;

        public MyCalendarTwo_LineSweep() {
            bookings = new TreeMap<>();
        }

        public boolean book(int startTime, int endTime) {
            //for start, we +1 and for end -1
            bookings.merge(startTime, 1, Integer::sum);
            if (bookings.merge(endTime, -1, Integer::sum) == 0)
                bookings.remove(endTime);

            //check if there is a triple booking
            int activeBookings = 0;
            for (Integer value : bookings.values()) {
                activeBookings += value;

                //there is a triple booking
                if (activeBookings > 2) {
                    //undo the changes, so that it does not affect for upcoming bookings
                    if (bookings.merge(startTime, -1, Integer::sum) == 0)
                        bookings.remove(startTime);
                    bookings.merge(endTime, 1, Integer::sum);
                    return false;
                }

                // if (bookings.higherKey(startTime) != null && bookings.higherKey(startTime) > endTime) {
                //     break; // No need to check further
                // }
            }
            return true;
        }
    }

}
