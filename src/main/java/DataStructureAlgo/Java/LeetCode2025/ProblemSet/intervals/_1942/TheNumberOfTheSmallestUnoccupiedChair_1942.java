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
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.
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
 * Similar {@link}
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
 * @OptimalSoltuion {@link SolutionPQ2}
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

        SolutionBruteForce SolutionBruteForce = new SolutionBruteForce();
        output = SolutionBruteForce.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Output BruteForce: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        SolutionPQ SolutionPQ = new SolutionPQ();
        output = SolutionPQ.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Output PQ: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

        SolutionPQ2 SolutionPQ2 = new SolutionPQ2();
        output = SolutionPQ2.smallestChair(CommonMethods.copyOf(times), targetFriend);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Output PQ2: " + output + " Pass : " + (pass ? "Pass" : "Fail"));

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
     * we can optimize above solution a bit by dynamically managing available chairs using a chair counter.
     * 1. When there is no chair available, then we increase the counter and give that chair to the frnd
     * 2. if there is a chair available, then take that chair to assign this frnd
     * 2. When a friend leaves the chair, then we add it to an available chair.
     */
    static class SolutionPQ2 {
        public int smallestChair(int[][] times, int targetFriend) {
            if (targetFriend >= times.length)
                return -1;

            int[] targetFrnd = times[targetFriend];

            //The First element represents the time at frnd leaves and second which chair is being occupied
            PriorityQueue<int[]> occupiedChairs = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            //available chairs
            PriorityQueue<Integer> availableChairs = new PriorityQueue<>();

            int chair = 0;
            //sort times by earliest arrival time
            Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

            for (int[] time : times) {

                //is any frnd leaving the chairs ?
                while (!occupiedChairs.isEmpty() && occupiedChairs.peek()[0] <= time[0]) {
                    int[] c = occupiedChairs.poll(); //this find can leave the chair
                    //add this chair to available chairs set
                    availableChairs.offer(c[1]);
                }

                int availableChair = chair;

                if (!availableChairs.isEmpty())
                    availableChair = availableChairs.poll();
                else
                    chair++;

                occupiedChairs.add(new int[]{time[1], availableChair});

                if (Arrays.equals(time, targetFrnd))
                    return availableChair;


            }
            return -1;
        }
    }
}
