package DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._1094;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/2/2025
 * Question Title: 1094. Car Pooling
 * Link: https://leetcode.com/problems/car-pooling/description/
 * Description: There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * <p>
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 * <p>
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals._2406.DivideIntervalsIntoMinimumNumberOfGroups_2406} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.intervals.meetingRoom._2402.MeetingRoomIII_2402}
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
 * @Simulation
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @DoorDash
 * @Flipkart
 * @Google
 * @Microsoft
 * @Facebook
 * @Lyft <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link Solution_PrefixSum_CountingSort}
 */

public class CarPooling_1094 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4, false));
        tests.add(test(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5, true));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] trips, int capacity, boolean expected) {
        CommonMethods.printTest(new String[]{"trips", "capacity", "Expected"}, true, trips, capacity, expected);

        boolean output;
        boolean pass, finalPass = true;

        output = new Solution_PrefixSum_CountingSort().carPooling(trips, capacity);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PrefixSum_CountingSort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionUsingMinHeap().carPooling(trips, capacity);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"MinHeap", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * In below heap solution, we maintain (numPassengers,to).
     * We can apply the same logic as line sweep / railway station needed.
     * <p>
     * For each incoming trip, we will add numPassengers.
     * For each ending trip, we will remove numPassengers.
     * Maintain this in an array. And in the end, run through an array and check, at any moment if the currentCapacity goes beyond available
     * <p>
     * O(n) / O(M) ~ O(1)
     * Here M <= 1000
     * .
     */
    static class Solution_PrefixSum_CountingSort {
        public boolean carPooling(int[][] trips, int capacity) {
            int length = trips.length;

            //if there is only one trip
            if (length == 1) {
                if (capacity >= trips[0][0]) { //and we have enough capacity
                    return true;
                } else {
                    return false; //and we need more capacity
                }
            }

            int[] carCapacity = new int[1001]; // since 1 <= trips.length <= 1000

            //process each trip
            int numPassengers, from, to;
            for (int[] trip : trips) {
                numPassengers = trip[0];
                from = trip[1];
                to = trip[2];

                //Add current trip capacity at from. Like numPassengers people onboarded at 'from' pickup
                carCapacity[from] += numPassengers;

                //Like  numPassengers, people de-boarded at 'to' stop
                carCapacity[to] -= numPassengers;
            }

            //check any time, we exceed the capacity
            int currentCapacity = 0;
            for (int i = 0; i < 1001; i++) {
                currentCapacity += carCapacity[i];
                if (currentCapacity > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * This is similar to MeetingRooms III or DivideInGroups. Where we need to find if we can assign more than 1 meeting in a meeting room.
     * <p>
     * Following observation
     * 1. We have trips[i] = [numPassengersi, fromi, toi]
     * 2. As the trip starts, it will end at some point of time.
     * 3. A trip at least 'to' will end first (minimum 'to')
     * <p>
     * Key elements :
     * From [1], before starting this trip, our currentCapacity >= numPassengersi
     * From [2], we need to process the trip based on 'from' as first. Sorting required by 'from'
     * From [2], Once the trip ends, we can increase our currentCapacity += endedTripPassendersCount.
     * From [3], we need to pick a trip which has least 'to' means we require a sorted set which can pick minimum 'to' in constant time. MinHeap is best for it.
     * From [2], we need to add ended trip passengers count, hence in minHeap we need to track the passengers onboarded in that trip.
     * <p>
     * Algo:
     * 1. Sort the trips by 'from' or by 'to' if ties.
     * 2. Process trip by 'from' and make sure before this trip starts, we remove and update the currentCapacity of all the trips which would end before 'from'
     * 3. Keep track of currentCapacity and check if we have enough space.
     * <p>
     * O(n*log(n)) / O(n)
     */
    static class SolutionUsingMinHeap {
        public boolean carPooling(int[][] trips, int capacity) {
            int length = trips.length;

            //if there is only one trip
            if (length == 1) {
                if (capacity >= trips[0][0]) { //and we have enough capacity
                    return true;
                } else {
                    return false; //and we don't have enough capacity
                }
            }

            //sort the trip by from, or by to
            Arrays.sort(trips, (a, b) -> a[1] == b[1] ? Integer.compare(a[2], b[2]) : Integer.compare(a[1], b[1]));

            //MinHeap to maintain and execute trip. [numPassengers, tripTo ]
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // min heap by trip 'to' aka end time

            //process the trip one by one
            int currentCapacity = capacity;
            int numPassengers, from, to;
            for (int[] trip : trips) {
                numPassengers = trip[0];
                from = trip[1];
                to = trip[2];

                //make trip end before this trip start
                while (!minHeap.isEmpty() && minHeap.peek()[1] <= from) {
                    //min heap top trip getting ended, hence increase the capacity
                    currentCapacity += minHeap.poll()[0];
                }

                //do we have sufficent capaicity to accomdate curent trip ?
                if (currentCapacity < numPassengers) {
                    return false;
                } else {
                    //we can take current trip, hence reduce currentCapacity
                    currentCapacity -= numPassengers;
                    minHeap.offer(new int[]{numPassengers, to});
                }

            }

            return true;
        }
    }
}
