package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._253;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 253. Meeting Rooms II
 * Description: https://leetcode.com/problems/meeting-rooms-ii
 * https://leetcode.ca/all/253.html
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.intervalRelatedProblems.meeting.room.MeetingRoomII}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._2406.DivideIntervalsIntoMinimumNumberOfGroups_2406}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Google
 * @Snapchat
 * @Uber
 * @Amazon
 * @Apple
 * @Atlassian
 * @Baidu
 * @Bloomberg
 * @Booking.com
 * @Cisco
 * @Citrix
 * @Drawbridge
 * @eBay
 * @Expedia
 * @GoDaddy
 * @GoldmanSachs
 * @Lyft
 * @Microsoft
 * @Nutanix
 * @Oracle
 * @Paypal
 * @Postmates
 * @Quora
 * @Visa
 * @WalmartLabs
 * @Yelp <p><p>
 * @Editorial
 */
public class MeetingRoomII_253 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{0, 30}, {5, 10}, {15, 20}}, 2);
        test &= test(new int[][]{{7, 10}, {2, 4}}, 1);
        CommonMethods.printResult(test);
    }
    private static boolean test(int[][] intervals, int expected) {

        System.out.println("---------------------------------------------");
        System.out.println("Intervals : " + CommonMethods.toStringInline(intervals) + " Expected : " + expected);

        int result;
        boolean pass, finalPass = true;

        SolutionTwoPointer solutionTwoPointer = new SolutionTwoPointer();
        result = solutionTwoPointer.minMeetingRooms(intervals);
        pass = result == expected;
        finalPass &= pass;
        System.out.println("Result Two pointer : " + result + " Pass : " + (pass ? "Pass" : "Fail"));
        return finalPass;

    }

    static class SolutionTwoPointer {
        public int minMeetingRooms(int[][] intervals) {
            if (intervals == null || intervals.length == 0)
                return 0;

            //draw start time and end time timeline
            int n = intervals.length;
            int[] start = new int[n];
            int[] end = new int[n];

            for (int i = 0; i < n; i++) {
                start[i] = intervals[i][0];
                end[i] = intervals[i][1];
            }

            //sort the start and end times, so that they form a liner line
            Arrays.sort(start);
            Arrays.sort(end);

            int meetingRoomRequired = 0, endPtr = 0;

            for (int s : start) {

                //if the start time of new intervals is greater than the end time of the previous interval,
                // then we can reuse the group.
                if (s > end[endPtr]) {
                    endPtr++;
                } else {
                    meetingRoomRequired++;
                }

            }

            return meetingRoomRequired;
        }
    }
}
