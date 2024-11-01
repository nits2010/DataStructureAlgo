package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._2406;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 2406. Divide Intervals Into Minimum Number of Groups
 * Description: https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/description/?envType=daily-question&envId=2024-10-12
 * You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval [lefti, righti].
 * <p>
 * You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no two intervals that are in the same group intersect each other.
 * <p>
 * Return the minimum number of groups you need to make.
 * <p>
 * Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] and [5, 8] intersect.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * Output: 3
 * Explanation: We can divide the intervals into the following groups:
 * - Group 1: [1, 5], [6, 8].
 * - Group 2: [2, 3], [5, 10].
 * - Group 3: [1, 10].
 * It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
 * Example 2:
 * <p>
 * Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
 * Output: 1
 * Explanation: None of the intervals overlap, so we can put all of them in one group.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 106
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._253.MeetingRoomII_253}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Greedy
 * @Sorting
 * @Heap(PriorityQueue)
 * @PrefixSum
 * @CountingSort
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Editorial
 * @OptimalSoltuion : {@link SolutionTwoPointer} , {@link SolutionPQ}, {@link SolutionPrefixSum}
 */
public class DivideIntervalsIntoMinimumNumberOfGroups_2406 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}}, 3);
        test &= test(new int[][]{{1, 3}, {5, 6}, {8, 10}, {11, 13}}, 1);
        test &= test(new int[][]{{1,1}}, 1);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] intervals, int expected) {
        System.out.println("----------------------------------");
        System.out.println("intervals = " + Arrays.deepToString(intervals) + " expected = " + expected);

        int output;
        boolean pass, finalPass = true;

        SolutionBruteForce solutionBruteForce = new SolutionBruteForce();
        SolutionTwoPointer solutionTwoPointer = new SolutionTwoPointer();
        SolutionPQ solutionPQ = new SolutionPQ();
        SolutionPrefixSum solutionPrefixSum = new SolutionPrefixSum();

        SolutionCountingSort solutionCountingSort = new SolutionCountingSort();


        output = solutionBruteForce.minGroups(intervals);
        pass = output == expected;
        System.out.println("Brute Force = " + output + " pass = " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        output = solutionPQ.minGroups(intervals);
        pass = output == expected;
        System.out.println("PQ = " + output + " pass = " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        output = solutionPrefixSum.minGroups(intervals);
        pass = output == expected;
        System.out.println("Prefix Sum = " + output + " pass = " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;


        output = solutionCountingSort.minGroups(intervals);
        pass = output == expected;
        System.out.println("Counting Sort = " + output + " pass = " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        output = solutionTwoPointer.minGroups(intervals);
        pass = output == expected;
        System.out.println("Two Pointer = " + output + " pass = " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;


        return finalPass;
    }

    /**
     * Keep the last interval of non-overlapping groups and check the new interval with the all last intervals
     * O(n*m) if m is the required groups, worst case m = n
     */
    static class SolutionBruteForce {
        public int minGroups(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
                return 0;

            List<int[]> groups = new ArrayList<>();

            //sort by start time
            Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));


            for (int[] interval : intervals) {

                int index = overlapping(groups, interval);
                if (index == groups.size())
                    groups.add(interval);
                groups.set(index, interval);

            }

            return groups.size();
        }

        int overlapping(List<int[]> groups, int[] interval) {

            int index = -1;
            for (int[] curr : groups) {
                index++;
                if (!(curr[1] > interval[0] || curr[1] > interval[1]))
                    return index;
            }
            return groups.size();
        }

    }


    /**
     * The idea of this solution is similar to above only, instead of checking all last groups, we use minHeap to check only the required one.
     * O(n*log(n))
     */
    static class SolutionPQ {
        public int minGroups(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
                return 0;

            //draw start time and end time timeline
            Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.offer(intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                int lastEnd = pq.isEmpty() ? -1 : pq.peek();

                int currentStart = intervals[i][0];

                //if they are not overlapping, then remove the last interval end time
                if (!(lastEnd > currentStart))
                    pq.poll();

                pq.offer(intervals[i][1]);

            }

            return pq.size();
        }


    }

    static class SolutionTwoPointer {
        public int minGroups(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
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


            int groups = 0, endPointer = 0;
            for (int startPointer : start) {

                //if the start time of new intervals is greater than the end time of the previous interval, then we can reuse the group.
                if (startPointer > end[endPointer])
                    endPointer++;
                else
                    groups++;
            }
            return groups;

        }
    }


    /**
     * Just like railway station question (from a karumanchi book), we can assume that, each interval starts and ends time as 1 or -1.
     * And then run the prefix sum on them, then the largest value is the required groups.
     * Time complexity: O(NlogN)
     */
    static class SolutionPrefixSum {

        public int minGroups(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
                return 0;

            //the first element represents the interval value time either start or end
            //second element represents the tag for it, 1 for start, -1 for end
            List<int[]> events = new ArrayList<>();

            for(int intval[] : intervals){
                events.add(new int[]{intval[0], 1});
                events.add(new int[]{intval[1] + 1, -1}); //since in this question, this is a valid interval [1,1], if we do not add + 1 in end, then they will overlap
            }

            //sort the list by time
            events.sort((a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));

            int groups = 0;
            int prefixSum = 0;
            for(int event[] : events){
                prefixSum += event[1];
                groups = Math.max(groups, prefixSum);
            }
            return groups;

        }
    }

    /**
     * Line Sweep Algorithm Without Ordered Container
     * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/editorial/
     */
    static class SolutionCountingSort {

        public int minGroups(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
                return 0;

            //find the range of buckets
            int start  = Integer.MAX_VALUE, end = -1;
            for(int[] interval : intervals){
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }

            //start and end, makes an array
            int [] buckets = new int[end + 2 ];

            //insert all the intervals in this bucket
            for(int[] interval : intervals){
                buckets[interval[0]]++; //increase the count of start time bucket
                buckets[interval[1] + 1]--; //decrease the count of end time bucket, just like prefix sum solution
            }

            int groups = 0;
            int prefixSum = 0;

            //count the number of groups
            for(int i = start; i <= end; i++){
                prefixSum += buckets[i];
                groups = Math.max(groups, prefixSum);
            }

            return groups;

        }
    }
}
