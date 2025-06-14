package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._1942;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 10/13/2024
 * Question Category: 1942. The Number of the Smallest Unoccupied Chair
 * Description: https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/description/
 * There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.
 * <p>
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
 * When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.
 * <p>
 * You are given a 0-indexed 2D integer array times when times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.
 * <p>
 * Return the chair number that the friend numbered targetFriend will sit on.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * Output: 1
 * Explanation:
 * - Friend 0 arrives at time 1 and sits on chair 0.
 * - Friend 1 arrives at time 2 and sits on chair 1.
 * - Friend 1 leaves at time 3 and chair 1 becomes empty.
 * - Friend 0 leaves at time 4 and chair 0 becomes empty.
 * - Friend 2 arrives at time 4 and sits on chair 0.
 * Since friend 1 sat on chair 1, we return 1.
 * Example 2:
 * <p>
 * Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * Output: 2
 * Explanation:
 * - Friend 1 arrives at time 1 and sits on chair 0.
 * - Friend 2 arrives at time 2 and sits on chair 1.
 * - Friend 0 arrives at time 3 and sits on chair 2.
 * - Friend 1 leaves at time 5 and chair 0 becomes empty.
 * - Friend 2 leaves at time 6 and chair 1 becomes empty.
 * - Friend 0 leaves at time 10 and chair 2 becomes empty.
 * Since friend 0 sat on chair 2, we return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == times.length
 * 2 <= n <= 104
 * times[i].length == 2
 * 1 <= arrivali < leavingi <= 105
 * 0 <= targetFriend <= n - 1
 * Each arrivali time is distinct.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._2406.DivideIntervalsIntoMinimumNumberOfGroups_2406}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._253.MeetingRoomII_253}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._2402.MeetingRoomIII_2402}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @Heap(PriorityQueue) <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Editorial https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/editorial/
 * @OptimalSoltuion {@link Solution_PQ_Optimized_V2} {@link SolutionPQ_Optimized}
 */
public class TheNumberOfTheSmallestUnoccupiedChair_1942 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1, 1);
        test &= test(new int[][]{{3, 10}, {1, 5}, {2, 6}}, 0, 2);
        test &= test(new int[][]{{3, 10}, {1, 5}, {2, 6}}, 3, -1);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] times, int targetFriend, int expected) {
        System.out.println("----------------------------------");
        System.out.println("Input: times = " + Arrays.deepToString(times) + ", targetFriend = " + targetFriend + ", expected = " + expected);
        int output;
        boolean pass, finalPass = true;

        SolutionBruteForce solutionBruteForce = new SolutionBruteForce();
        output = solutionBruteForce.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Output BruteForce: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        SolutionPQ solutionPQ = new SolutionPQ();
        output = solutionPQ.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Output PQ: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        SolutionPQ_Optimized solutionPQOptimized = new SolutionPQ_Optimized();
        output = solutionPQOptimized.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Optimized PQ: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        Solution_PQ_Optimized_V2 solution_pq_optimized_v2 = new Solution_PQ_Optimized_V2();
        output = solution_pq_optimized_v2.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Optimized PQV2: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        return finalPass;
    }

    /**
     * Find the first available chair for current frnd and make it sit on it.
     * Once the frnd leaves, make chair available.
     */
    static class SolutionBruteForce {
        public int smallestChair(int[][] times, int targetFriend) {
            if (targetFriend >= times.length)
                return -1;
            int[] targetFrnd = times[targetFriend];

            int[] chair = new int[times.length];

            Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

            //O(n^2)
            for (int[] time : times) {
                //find the smallest chair
                for (int c = 0; c < chair.length; c++) {

                    //if the chair becomes empty by this time by earlier frnd.
                    //O(n)
                    if (chair[c] != 0 && chair[c] <= time[0])
                        chair[c] = 0;

                    if (chair[c] == 0) {
                        chair[c] = time[1];

                        //if this is the target friend
                        if (Arrays.equals(time, targetFrnd))
                            return c;

                        break;
                    }
                }
            }
            return -1; //if none of the chairs are available


        }
    }

    /**
     * Find the first available chair for current frnd and make it sit on it.
     * Once the frnd leaves, make chair available.
     * <p>
     * In the above solution, we have to scan again n again the chair to find the first smallest numbered chair. This can be translated to
     * Min heap. Means, we can keep a min-heap for available chairs in the sorted order.
     * <p>
     * Just like to find a chair is getting vacated or not we used time at leave, for that we can also use min-heap.
     */
    static class SolutionPQ {
        public int smallestChair(int[][] times, int targetFriend) {
            if (targetFriend >= times.length)
                return -1;

            int[] targetFrnd = times[targetFriend];

            //The First element represents the time at frnd leaves and second which chair is being occupied
            PriorityQueue<int[]> occupiedChairs = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            //available chairs
            PriorityQueue<Integer> availableChairs = new PriorityQueue<>();

            //there will be a max n chair only
            int n = times.length;

            //add all available chairs
            for (int i = 0; i < n; i++) {
                availableChairs.add(i);
            }

            //sort times by earliest arrival time
            Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

            for (int[] time : times) {

                //is any frnd leaving the chairs ?
                while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[0] <= time[0]) {
                    int[] c = occupiedChairs.poll(); //this find can leave the chair
                    //add this chair to available chairs set
                    availableChairs.offer(c[1]);
                }

                int availableChair = availableChairs.poll();
                occupiedChairs.add(new int[]{time[1], availableChair});

                if (Arrays.equals(time, targetFrnd))
                    return availableChair;


            }
            return -1;
        }


    }

    /**
     * We can optimize above a solution a bit by dynamically managing available chairs using a chair counter.
     * 1. When there is no chair available, then we increase the counter and give that chair to the frnd
     * 2. If there is a chair available, then take that chair to assign this frnd
     * 2. When a friend leaves the chair, then we add it to an available chair.
     */
    static class SolutionPQ_Optimized {
        public int smallestChair(int[][] times, int targetFriend) {
            int friendsCount = times.length;

            if (targetFriend > friendsCount-1) // its 0-based index
                return -1;
            //if there is only one friend, and he is the target, then 0 is the chair number
            if (targetFriend == 0 && friendsCount == 1)
                return 0;

            int[] target = times[targetFriend];

            //Sort the friends array based on their arriaval.
            Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

            //minHeap [chairNumber,leaving] ordered by leaving time
            PriorityQueue<int[]> occupiedChairs = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            PriorityQueue<Integer> unOccupiedChairs = new PriorityQueue<>();

            //fill up the chairs available at start
            int chairNumber = 0; // current available Chair number, we did not fill pq as n ranges till 10^4 or infinite

            int i = 0;
            while (i < friendsCount) {
                int[] friend = times[i];
                int friendArrivalTime = friend[0];
                int friendLeavingTime = friend[1];

                //vacate all the chairs for those frinds who are leaving
                while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[1] <= friendArrivalTime) {
                    unOccupiedChairs.offer(occupiedChairs.poll()[0]);
                }

                //get an unOccupiedChair
                int availableChair = chairNumber; // at least 'chairNumber' is available to be occupied

                //check if any of the unOccupiedChairs chair available ? this is to make sure we assign smallest number chair first
                if (!unOccupiedChairs.isEmpty()) {
                    availableChair = unOccupiedChairs.poll();
                } else {
                    // we need more chair, increase a chair for next friend
                    chairNumber++;
                }

                // Is this the target friend ?
                // Since All arrival times are distinct. If arrival time is not distinct but the pair is distinct then we can do Arrays.equals(target, friend)
                if (target[0] == friendArrivalTime) {
                    return availableChair;
                }

                //occupy this chair
                occupiedChairs.offer(new int[]{availableChair, friendLeavingTime});

                i++; //next friend
            }
            return chairNumber;
        }
    }


    static class Solution_PQ_Optimized_V2 {
        public int smallestChair(int[][] times, int targetFriend) {
            int friendsCount = times.length;

            if (targetFriend > friendsCount-1)
                return -1;
            //if there is only one friend and he is the target, then 0 is the chair number
            if (targetFriend == 0 && friendsCount == 1)
                return 0;

            // contains [friendNumber, arrival, leaving] time
            int[][] friends = new int[friendsCount][3];

            for (int i = 0; i < times.length; i++) {
                friends[i][0] = i;
                friends[i][1] = times[i][0];
                friends[i][2] = times[i][1];
            }

            //Sort the friends array based on their arriaval.
            Arrays.sort(friends, Comparator.comparingInt(a -> a[1]));

            //minHeap [chairNumber,leaving] ordered by leaving time
            PriorityQueue<int[]> occupiedChairs = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            PriorityQueue<Integer> unOccupiedChairs = new PriorityQueue<>();

            //fill up the chairs available at start
            int chairNumber = 0; // current available Chair number, we did not fill pq as n ranges till 10^4 or infinite

            int i = 0;
            while (i < friendsCount) {
                int[] friend = friends[i];
                int friendId = friend[0];
                int friendArrivalTime = friend[1];
                int friendLeavingTime = friend[2];

                //vacate all the chairs for those frinds who are leaving
                while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[1] <= friendArrivalTime) {
                    unOccupiedChairs.offer(occupiedChairs.poll()[0]);
                }

                //get an unOccupiedChair
                int availableChair = chairNumber; // at least 'chairNumber' is available to be occupied

                //check if any of the unOccupiedChairs chair available ? this is to make sure we assign smallest number chair first
                if (!unOccupiedChairs.isEmpty()) {
                    availableChair = unOccupiedChairs.poll();
                } else {
                    // we don't have enough chair, increase a chair for next friend
                    chairNumber++;
                }

                //is this the target friend ?
                if (friendId == targetFriend) {
                    return availableChair;
                }

                //occupy this chair
                occupiedChairs.offer(new int[]{availableChair, friendLeavingTime});

                i++; //next friend
            }
            return chairNumber;
        }
    }
}
