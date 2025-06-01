package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._2402;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/2/2025
 * Question Title:  2402. Meeting Rooms III
 * Link: https://leetcode.com/problems/meeting-rooms-iii/description/
 * Description : You are given an integer n. There are n rooms numbered from 0 to n - 1.
 * <p>
 * You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.
 * <p>
 * Meetings are allocated to rooms in the following manner:
 * <p>
 * Each meeting will take place in the unused room with the lowest number.
 * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
 * When a room becomes unused, meetings that have an earlier original start time should be given the room.
 * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
 * <p>
 * A half-closed interval [a, b) is the interval between a and b including a and not including b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * Output: 0
 * Explanation:
 * - At time 0, both rooms are not being used. The first meeting starts in room 0.
 * - At time 1, only room 1 is not being used. The second meeting starts in room 1.
 * - At time 2, both rooms are being used. The third meeting is delayed.
 * - At time 3, both rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
 * - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
 * Both rooms 0 and 1 held 2 meetings, so we return 0.
 * Example 2:
 * <p>
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * Output: 1
 * Explanation:
 * - At time 1, all three rooms are not being used. The first meeting starts in room 0.
 * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
 * - At time 3, only room 2 is not being used. The third meeting starts in room 2.
 * - At time 4, all three rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
 * - At time 6, all three rooms are being used. The fifth meeting is delayed.
 * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
 * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 1 <= meetings.length <= 105
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 105
 * All the values of starti are unique.
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
 * @HashTable
 * @Sorting
 * @Heap(PriorityQueue)
 * @Simulation <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MeetingRoomIII_2402 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}, 2, 0));
        tests.add(test(new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}}, 3, 1));
        tests.add(test(new int[][]{{0, 10}, {1, 2}, {12, 14}, {13, 15}}, 2, 0));
        tests.add(test(new int[][]{{2, 13}, {3, 127, 10}, {17, 19}, {18, 19}}, 4, 0));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] nums, int n, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "n", "Expected"}, true, nums, n, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_UsingArray().mostBooked(n, nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"UsingArray", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_UsingHeaps().mostBooked(n, nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"usingHeaps", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_UsingHeaps_V2().mostBooked(n, nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"usingHeapsV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Following important observation from question
     * 1. There are n rooms numbered from 0 -> n-1
     * 2. We need to use the unused room, with the lowest numbered room.
     * 3. If no meeting room is available, then a meeting will be delayed until a room is available. If multiple get available, then we used the lowest numbered one.
     * 4. If there is multiple meeting gets delayed and a room / multiple now available, then we pick the meeting which is start first.
     * <p>
     * At last, we need to return a room that holds the maximum meetings.
     * <p>
     * This leads to the following aspects;
     * 1. We need to process a meeting by their start time only [ by rule 4 ] : Hence we need to sort the meetings by start time.
     * 2. We need an ordered set / DS that can help us find the lowest numbered meeting room which is available [ Rule 1 & 3 ] : we can use array and keep scanning the array to find the unused room / or a room which gets available first. O(n)
     * 3. When a meeting gets delayed, then we need to find a meeting room sooner or later, whose meeting is over (means become unused) [Rule 2] : Means we need to track the end time of meeting for which the room has assigned.
     * <p>
     * Initial Approach :
     * 1. Create an array to maintain unused room. Each unused room contains the end time of meeting for which this room is booked.
     * 2. To get the highest number of meetings held in a room, we need a counter for each room to track. In the end, we can find the max in it.
     * 3. At any point of time, if we find that no meeting room is available, then we need to find a room whose end time is smallest. Scan and find index with the smallest end time.
     * 4. Maintain the end time of meeting. This can be calculated as
     * existingMeetingEndTime + (durationOfMeeting). existingMeetingEndTime = 0 if no meeting was held in that room earlier.
     */
    static class Solution_UsingArray {

        class MeetingRoom {
            long availableAt = 0;
            int noOfMeetings = 0;
        }

        public int mostBooked(int n, int[][] meetings) {
            int length = meetings.length;

            if (length == 1)
                return 0; // since there is only one meeting, we keep it in lowest numbered room only.

            MeetingRoom[] rooms = new MeetingRoom[n]; // initially, everything is 0 means all available.
            for (int i = 0; i < n; i++) {
                rooms[i] = new MeetingRoom();
            }

            //sort the meetings by start time.
            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
            int lowestIndexRoom = 0; // maintain the lowestIndexRoom with the highest Meetings
            for (int[] meeting : meetings) {

                long[] room = findUnusedRoom(rooms, meeting[0]);
                int roomIndex = (int) room[0];
                long availableAt = room[1];

                //occupy this meeting room now;
                int duration = meeting[1] - meeting[0];
                long nextAvailableAt = availableAt == 0 ? meeting[1] : availableAt + duration;
                rooms[roomIndex].availableAt = nextAvailableAt;
                rooms[roomIndex].noOfMeetings++;

                //get lowestIndexRoom with highest meetings
                lowestIndexRoom = lowestIndexRoomWithMaxMeeting(rooms, lowestIndexRoom, roomIndex);

            }

            return lowestIndexRoom;

        }

        private int lowestIndexRoomWithMaxMeeting(MeetingRoom[] rooms, int lowest, int current) {
            int lowestMeetings = rooms[lowest].noOfMeetings;
            int currentMeetings = rooms[current].noOfMeetings;

            if (lowestMeetings == currentMeetings)
                return Math.min(lowest, current);

            if (lowestMeetings < currentMeetings)
                return current;

            return lowest;
        }

        //returns [roomIndex, availableAt]
        private long[] findUnusedRoom(MeetingRoom[] rooms, int newMeetingStartTime) {
            long availableAt = Long.MAX_VALUE; // since 0 <= start < end <= 5 * 10^5 hence time can go beyond int limit
            long roomIndex = 0;
            for (int i = 0; i < rooms.length; i++) {

                //if this rooms gets unused (or 0 already-unused) before the current meeting starts, then this is our candidate
                if (rooms[i].availableAt == 0 || rooms[i].availableAt <= newMeetingStartTime) {
                    roomIndex = i;
                    availableAt = 0; // either room was unused or will be unused before the current meeting start
                    break;
                } else {
                    //find the min availableAt. Don't update when current availableAt = old availableAt since old would have lowest room Index.
                    if (availableAt > rooms[i].availableAt) {
                        availableAt = rooms[i].availableAt;
                        roomIndex = i;
                    }

                }
            }

            return new long[]{roomIndex, availableAt};
        }
    }


    /**
     * Optimization :
     * In the initial approach since we used an array to track rooms, we have to scan the room in O(n) time to get the next unused room.
     * The room gets can be considered as required one iff
     * 1. If its already unused [ no meeting held so far]
     * 2. If the existing meeting got over before the current meeting starts.
     * 3. Earliest ending meeting (minimum end time)
     * <p>
     * In all the cases, we need to see the roomIndex, which has minimum end time ( for case 1 & 2, its 0).
     * Hence, we can use minHeap to track this. Which will get this in O(1) time. However, once the room gets updated, it will be heapify which will take O(logn) time. Overall, its O(logn) time, which is
     * better than O(n)
     */
    static class Solution_UsingHeaps {

        class MeetingRoom {
            int roomId = 0; //represent room id, [0,n-1]
            long availableAt = 0;
            int noOfMeetings = 0;
        }

        private void initializeRooms(PriorityQueue<MeetingRoom> unUsedRooms, int n) {
            for (int i = 0; i < n; i++) {
                MeetingRoom room = new MeetingRoom();
                room.roomId = i;
                unUsedRooms.offer(room);
            }
        }

        public int mostBooked(int n, int[][] meetings) {
            int length = meetings.length;

            if (length == 1)
                return 0; // since there is only one meeting, we keep it in lowest numbered room only.

            //Maintain the minHeap by either roomId or availableAt if occupied.
            PriorityQueue<MeetingRoom> usedRooms = new PriorityQueue<>((a, b) -> {
                if (a.availableAt == b.availableAt)
                    return a.roomId - b.roomId;
                return Long.compare(a.availableAt, b.availableAt);
            });

            PriorityQueue<MeetingRoom> unUsedRooms = new PriorityQueue<>((a, b) -> {
                return a.roomId - b.roomId;
            });

            initializeRooms(unUsedRooms, n);

            //sort the meetings by start time.
            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
            int lowestIndexRoom = 0;
            int lowestRoomMeetings = 0;

            for (int[] meeting : meetings) {

                int start = meeting[0], end = meeting[1];
                int duration = end - start;

                //check if we can take any room from used room ? this is because lowest index room will be first available here only
                while (!usedRooms.isEmpty() && usedRooms.peek().availableAt <= start) {
                    MeetingRoom room = usedRooms.poll();
                    room.availableAt = 0; // this room is available now
                    unUsedRooms.offer(room);
                }

                //take a room from unused room
                MeetingRoom room = null;
                if (!unUsedRooms.isEmpty()) {
                    room = unUsedRooms.poll();
                    room.noOfMeetings++; //adding new meeting to this room
                    room.availableAt = end;
                } else {
                    //if no unused room present, then take a rooom from usedRoom with minimum availableAt
                    room = usedRooms.poll();
                    room.noOfMeetings++;
                    room.availableAt += duration;
                }

                usedRooms.offer(room);

                int[] temp = getLowestIndexRoom(room, lowestIndexRoom, lowestRoomMeetings);
                lowestIndexRoom = temp[0];
                lowestRoomMeetings = temp[1];

            }

            return lowestIndexRoom;

        }

        private int[] getLowestIndexRoom(MeetingRoom room, int lowest, int oldMeetings) {
            int meetings = oldMeetings;
            if (room.noOfMeetings == oldMeetings) {
                lowest = Math.min(room.roomId, lowest);
                meetings = room.noOfMeetings;

            } else if (room.noOfMeetings > oldMeetings) {
                lowest = room.roomId;
                meetings = room.noOfMeetings;
            }

            return new int[]{lowest, meetings};
        }

    }


    /**
     * Similar to using heaps, instead of an object, we used a direct array
     */
    static class Solution_UsingHeaps_V2 {

        private void initializeRooms(PriorityQueue<Integer> unUsedRooms, int n) {
            //initialize rooms
            for (int i = 0; i < n; i++) {
                unUsedRooms.offer(i);
            }
        }

        public int mostBooked(int n, int[][] meetings) {
            int length = meetings.length;

            if (length == 1)
                return 0; // since there is only one meeting, we keep it in lowest numbered room only.

            //[roomId, availableAt] MinHeap, sort based on availableAt or roomId
            PriorityQueue<long[]> usedRooms = new PriorityQueue<>(
                    (a, b) -> a[1] == b[1] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));

            PriorityQueue<Integer> unUsedRooms = new PriorityQueue<>();
            initializeRooms(unUsedRooms, n);

            //sort the array by start time / end time.
            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
            int[] meetingCount = new int[n];

            for (int[] meeting : meetings) {

                int start = meeting[0];
                int end = meeting[1];
                int duration = end - start;

                //check first in used rooms
                while (!usedRooms.isEmpty() && usedRooms.peek()[1] <= start) {
                    long[] room = usedRooms.poll();
                    unUsedRooms.offer((int) room[0]); //room id
                }

                //do we have unused room ?
                long[] room = null;

                if (!unUsedRooms.isEmpty()) {
                    int roomId = unUsedRooms.poll();

                    //held the meeting in this room
                    meetingCount[roomId]++;

                    //update the availableAt
                    room = new long[]{roomId, end};
                } else if (!usedRooms.isEmpty()) { //otehrwise take from usedroom

                    room = usedRooms.poll();
                    int roomId = (int) room[0];

                    //held the meeting in this room
                    meetingCount[roomId]++;
                    room[1] += duration;
                }

                usedRooms.offer(room);

            }

            int lowestIndexRoom = 0;
            int highestMeetingCount = 0;

            for (int i = 0; i < n; i++) {

                int count = meetingCount[i];

                if (highestMeetingCount < count) {
                    highestMeetingCount = count;
                    lowestIndexRoom = i;
                }
            }

            return lowestIndexRoom;

        }

    }


}
