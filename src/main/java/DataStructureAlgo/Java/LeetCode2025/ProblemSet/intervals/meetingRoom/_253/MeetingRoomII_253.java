package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._253;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/1/2025
 * Question Title:  253. Meeting Rooms II
 * Link: https://leetcode.com/problems/meeting-rooms-ii
 * https://neetcode.io/problems/meeting-schedule-ii [try out]
 * Description:
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
 * -----------.
 * <p>
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.intervalRelatedProblems.meeting.room.MeetingRoomII}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._2406.DivideIntervalsIntoMinimumNumberOfGroups_2406}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Sorting
 * @Heap(PriorityQueue)
 * @OrderedSet
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p><p>
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
 * @Yelp <p><p><p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link SolutionTwoPointer}
 */

public class MeetingRoomII_253 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 30}, {5, 10}, {15, 20}}, 2));
        tests.add(test(new int[][]{{7, 10}, {2, 4}}, 1));
        tests.add(test(new int[][]{{1, 5}, {2, 6}, {3, 7}, {4, 8}, {5, 9}}, 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionTwoPointer().minMeetingRooms(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"TwoPointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new SolutionEventsMapping_Greedy().minMeetingRooms(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"EventsMapping-Greedy", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionHeap().minMeetingRooms(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Heaps", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_LineSweep_OrderedSet().minMeetingRooms(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"LineSweep_OrderedSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_LineSweep_WithoutOrderedSet().minMeetingRooms(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"LineSweep_WithoutOrderedSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Intervals=[(1,5),(2,6),(3,7),(4,8),(5,9)]
     * we have to sort these intervals in such a way that if any two meetings start back to back then they are next to each other to check the overlapping.
     * Keeping them in the interval object isn't possible as we can sort them by either start or end time which will not solve the purpose.
     * To do so, we can get all the start time and end time in a different array.
     * Start[] = [1,2,3,4,5]
     * end[]   = [5,6,7,8,9]
     * <p>
     * And sort them individually {in the above example its sorted already}. Now we know that there are meetings starting at start time and ending at end time.
     * In a nutshell, we know the current conference room would not be available b/w [ start[i],end[j] ] as its already occupied.
     * Example ; start[0] = 1 , end[0] = 5 ; means a meeting will start at 1pm and ends at 5 pm. Hence, this room is booked and only be available post-5pm or before 1pm.
     * In such a case, we need to move to the next meeting and check again i.e., if start[i] < end[j] ; means a meeting has to start but the earlier one hasn't finished yet, hence we need
     * another meeting room.
     * However, at any point of time if start[i] = end[j] that means that a meeting will be ending at end[j] and immediately another meeting will start at start[i]
     * <p>
     * Ref: https://www.youtube.com/watch?v=FdzJmTCVyJU&ab_channel=NeetCode
     **/
    static class SolutionTwoPointer {
        public int minMeetingRooms(int[][] intervals) {
            int length = intervals.length;

            if (length == 0)
                return 0;
            if (length == 1)
                return 1;


            //draw start time and end time timeline
            int[] startTime = new int[length];
            int[] endTime = new int[length];

            //extract the start and end time
            int idx = 0;
            for (int[] interval : intervals) {
                startTime[idx] = interval[0];
                endTime[idx] = interval[1];
                idx++;
            }


            //sort the start and end time
            Arrays.sort(startTime);
            Arrays.sort(endTime);

            int conferenceRoomRequired = 0;
            int endPtr = 0;
            for (int start : startTime) {

                int end = endTime[endPtr];

                //check do we need a room or use the existing room
                if (start < end) {
                    //means a new meeting has to start, but the earlier one isn't ended yet
                    conferenceRoomRequired++;
                } else {
                    //means either a new meeting will start after the other meeting ended or start immediately once other ended. Hence no more meeting room required
                    endPtr++;
                }
            }

            return conferenceRoomRequired;
        }
    }


    //Using Heaps,
    //instead of sorting directly, we can use minheap to track the end time.
    static class SolutionHeap {

        public int minMeetingRooms(int[][] intervals) {
            int length = intervals.length;

            if (length == 0)
                return 0;
            if (length == 1)
                return 1;

            //sort the intervals with start time to process them one by one
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //to maintain end time in an ordered manner

            for (int[] interval : intervals) {

                //check if any meeting ending before this meeting starts
                if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                    //if so, then its vacating a conference room
                    minHeap.poll();
                }

                //add end time of this meeting to minHeap
                minHeap.offer(interval[1]);
            }
            return minHeap.size(); // all the meetings which is in the heap are required meeting that needs size() conference room.
        }
    }


    /**
     * Just like railway station question (from a karumanchi book), we can assume that, each interval starts and ends time as 1 or -1.
     * And then run the prefix sum on them, then the largest value is the required groups.
     * Time complexity: O(NlogN)
     */
    static class SolutionEventsMapping_Greedy {
        public int minMeetingRooms(int[][] intervals) {
            int length = intervals.length;

            if (length == 0)
                return 0;
            if (length == 1)
                return 1;

            //draw start time (as 1) and end time as (-1) in horizontal line. Create events for start and end
            List<int[]> events = new ArrayList<>();

            for (int[] interval : intervals) {
                events.add(new int[]{interval[0], 1}); //added start time and corresponding line sweep as 1
                events.add(new int[]{interval[1], -1});
            }

            //sort the events based on start, if clash then end time
            events.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

            int conferenceRoomRequired = 0;

            //sum all the numbers in the events
            int sum = 0;
            for (int[] event : events) {
                sum += event[1];
                conferenceRoomRequired = Math.max(conferenceRoomRequired, sum);

            }


            return conferenceRoomRequired;

        }


    }


    //Line sweep algorithm with an ordered set
    //https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/editorial/
    static class Solution_LineSweep_OrderedSet {

        public int minMeetingRooms(int[][] intervals) {
            int length = intervals.length;

            if (length == 0)
                return 0;
            if (length == 1)
                return 1;

            TreeMap<Integer, Integer> events = new TreeMap<>();
            for (int[] interval : intervals) {
                events.merge(interval[0], 1, Integer::sum);
                events.merge(interval[1], -1, Integer::sum); //since in this question, this has been not a valid interval [1,1] aka closed interval otherwise we need to do +1 for end, if we do not add + 1 in end, then they will overlap
            }

            //now the same as SolutionEventsMapping_Greedy
            int sum = 0;
            int conferenceRoomRequired = 0;
            for (Map.Entry<Integer, Integer> entry : events.entrySet()) {
                sum += entry.getValue();  // Update currently active intervals

                // Update max intervals
                conferenceRoomRequired = Math.max(conferenceRoomRequired, sum);
            }

            return conferenceRoomRequired;
        }
    }

    //Line sweep algorithm with  without an ordered set
    //https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/editorial/
    static class Solution_LineSweep_WithoutOrderedSet {

        public int minMeetingRooms(int[][] intervals) {
            int length = intervals.length;

            if (length == 0)
                return 0;
            if (length == 1)
                return 1;

            //instead of map, we will use buckets to keep them sort
            //find the bucket length
            int end = 0;
            for (int[] interval : intervals) {
                end = Math.max(end, interval[1]);
            }

            int[] buckets = new int[end + 2];

            //push all in buckets
            for (int[] interval : intervals) {
                buckets[interval[0]]++;
                buckets[interval[1]]--;
            }

            int sum = 0;
            int conferenceRoomRequired = 0;
            for (int i = 0; i < buckets.length; i++) {
                sum += buckets[i];
                conferenceRoomRequired = Math.max(conferenceRoomRequired, sum);
            }

            return conferenceRoomRequired;
        }
    }

}
