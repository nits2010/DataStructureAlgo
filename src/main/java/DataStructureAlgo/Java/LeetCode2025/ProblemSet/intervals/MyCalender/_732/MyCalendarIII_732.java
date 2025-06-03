package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.MyCalender._732;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/4/2025
 * Question Title: 732. My Calendar III
 * Link: https://leetcode.com/problems/my-calendar-iii/description/
 * Description: A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)
 * <p>
 * You are given some events [startTime, endTime), after each given event, return an integer k representing the maximum k-booking between all the previous events.
 * <p>
 * Implement the MyCalendarThree class:
 * <p>
 * MyCalendarThree() Initializes the object.
 * int book(int startTime, int endTime) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, 1, 1, 2, 3, 3, 3]
 * <p>
 * Explanation
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // return 1
 * myCalendarThree.book(50, 60); // return 1
 * myCalendarThree.book(10, 40); // return 2
 * myCalendarThree.book(5, 15); // return 3
 * myCalendarThree.book(5, 10); // return 3
 * myCalendarThree.book(25, 55); // return 3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= startTime < endTime <= 109
 * At most 400 calls will be made to book.
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
 * @BinarySearch
 * @Design
 * @SegmentTree
 * @PrefixSum
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MyCalendarIII_732 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10}, {25, 55}}, new Integer[]{1, 1, 2, 3, 3, 3}));
        tests.add(test(new int[][]{{10, 20}, {15, 25}, {20, 30}}, new Integer[]{1, 2, 2}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, Integer[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);


        boolean pass, finalPass = true;

        Integer[] output = new Integer[nums.length];
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        for (int i = 0; i < nums.length; i++) {
            output[i] = myCalendarThree.book(nums[i][0], nums[i][1]);
        }
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"LineSweep", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class MyCalendarThree {

        TreeMap<Integer, Integer> bookings;

        public MyCalendarThree() {
            bookings = new TreeMap<>();
        }

        public int book(int startTime, int endTime) {
            //for start, we +1 and for end -1
            bookings.merge(startTime, 1, Integer::sum);
            if (bookings.merge(endTime, -1, Integer::sum) == 0)
                bookings.remove(endTime);

            int activeBookings = 0, k = 0; // maximum k-booking between all the previous events
            for (Integer value : bookings.values()) {
                activeBookings += value;
                k = Math.max(k, activeBookings);

            }
            return k;
        }
    }


}
