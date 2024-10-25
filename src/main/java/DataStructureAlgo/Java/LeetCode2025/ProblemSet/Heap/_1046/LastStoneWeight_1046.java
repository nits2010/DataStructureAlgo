package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._1046;

import DataStructureAlgo.Java.helpers.templates.MaxHeap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Question Category: 1046. Last Stone Weight
 * Description: https://leetcode.com/problems/last-stone-weight/description/
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * <p>
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 * <p>
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the weight of the last remaining stone. If there are no stones left, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 * Example 2:
 * <p>
 * Input: stones = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.LastStoneWeight}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @Heap(PriorityQueue) <p>
 * Company Tags
 * -----
 * @Visa
 * @Nvidia
 * @Amazon
 * @Salesforce
 * @Editorial
 */
public class LastStoneWeight_1046 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 1, 1, 1, 1000}, 996);
        test &= test(new int[]{10, 5, 4, 10, 3, 1, 7, 8}, 0);
        test &= test(new int[]{2, 7, 4, 1, 8, 1}, 1);
        test &= test(new int[]{9, 3, 2, 10}, 0);
        test &= test(new int[]{2, 4, 6, 8}, 0);
        test &= test(new int[]{2, 4, 6, 8, 10}, 2);
        test &= test(new int[]{1, 3, 5, 7, 9}, 1);
        test &= test(new int[]{1, 3, 5, 7}, 0);
        test &= test(new int[]{1, 5, 9, 11}, 2);
        test &= test(new int[]{1, 9, 17, 25}, 0);
        test &= test(new int[]{1, 9, 17, 25, 33}, 1);
        test &= test(new int[]{1, 5, 5, 6, 8, 8, 10, 12}, 1);
        test &= test(new int[]{1, 1, 3, 3, 3, 5, 5, 7, 7, 7, 9, 9, 12, 12, 12}, 0);

        System.out.println("=================================================");
        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(int[] stones, int expected) {
        System.out.println("-------------------------------");
        System.out.println("Input :" + Arrays.toString(stones) + " Expected Output :" + expected);

        SolutionPriorityQueue solutionPriorityQueue = new SolutionPriorityQueue();
        SolutionHeap solutionHeap = new SolutionHeap();
        SolutionBucketSort solutionBucketSort = new SolutionBucketSort();

        int outputUsingPriorityQueue = solutionPriorityQueue.lastStoneWeight(Arrays.copyOf(stones, stones.length));
        boolean testResultUsingPriorityQueue = outputUsingPriorityQueue == expected;

        System.out.println("Output Using Priority Queue : " + outputUsingPriorityQueue + " Test Result Using Priority Queue : " + testResultUsingPriorityQueue);


        int outputUsingHeap = solutionHeap.lastStoneWeight(Arrays.copyOf(stones, stones.length));
        boolean testResultUsingHeap = outputUsingHeap == expected;

        System.out.println("Output Using Heap : " + outputUsingHeap + " Test Result Using Heap : " + testResultUsingHeap);

        int outputUsingBucketSort = solutionBucketSort.lastStoneWeight(Arrays.copyOf(stones, stones.length));
        boolean testResultUsingBucketSort = outputUsingBucketSort == expected;

        System.out.println("Output Using Bucket : " + outputUsingBucketSort + " Test Result Using bucket : " + testResultUsingBucketSort);

        return testResultUsingPriorityQueue && testResultUsingHeap && testResultUsingBucketSort;
    }


    static class SolutionPriorityQueue {
        public int lastStoneWeight(int[] stones) {
            if (stones == null || stones.length == 0)
                return 0;

            if (stones.length == 1)
                return stones[0];

//            PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> x >= y ? 1 : -1);

            final PriorityQueue<Integer> pq = new PriorityQueue<>(stones.length, Collections.reverseOrder());

            Arrays.stream(stones).forEach(pq::offer);

            while (pq.size() >= 2) {

                int x = pq.poll();
                int y = pq.isEmpty() ? 0 : pq.poll();

                if (x != y) {
                    // x is the first element, hence always greater or equal to the next element
                    pq.offer(x - y);
                }

            }

            return pq.isEmpty() ? 0 : pq.poll();


        }
    }

    static class SolutionHeap {


        public int lastStoneWeight(int[] stones) {
            if (stones == null || stones.length == 0)
                return 0;

            if (stones.length == 1)
                return stones[0];

            MaxHeap pq = new MaxHeap(stones);

            while (pq.size() >= 2) {

                int x = pq.poll();
                int y = pq.isEmpty() ? 0 : pq.poll();

                if (x != y) {
                    // x is the first element, hence always greater or equal to the next element
                    pq.offer(x - y);
                }

            }

            return pq.isEmpty() ? 0 : pq.poll();


        }
    }

    /*
    We can apply bucket sort to achieve linear time complexity.

    Given:

    1 <= stones.length <= 30
    1 <= stones[i] <= 1000
    Algorithm:

    1. Find the maximum value in the stones array.
    2. Create an array of buckets with size equal to the maximum value.
    3. Count the frequency of each stone in the corresponding bucket.
    4. Iterate backward through the buckets from the maximum value to 0:
        a. If a bucket has an even frequency, the stones will destroy each other; move to the next bucket.
        b. If a bucket has an odd frequency, find another bucket to pair with and update the frequency of the resulting bucket. Decrease the frequency of both buckets as the stones are used.
    5. Continue this process. At the end, the bucket with a frequency greater than 0 and odd is the result.

    Complexity:

    Space: (O(max)); since 1 <= stones[i] <= 1000, (O(1000)) is constant, thus (O(1)).
    Time: (O(n)) for counting bucket frequencies + (O(1000)) for iterating through the maximum value of stones. Given the maximum length of stones is 30,
    in the worst case (all stones are duplicates or unique), the loop runs at most 1000 times, which is constant.
    Therefore, the overall complexity is:

    Space: (O(1))
    Time: (O(n + 1000) => approx O(n))
     */
    static class SolutionBucketSort {

        public int lastStoneWeight(int[] stones) {

            if (stones == null || stones.length == 0)
                return 0;

            if (stones.length == 1)
                return stones[0];

            int max = 0;
            for (int stone : stones) max = Math.max(max, stone);
            final int[] buckets = new int[max + 1];


            for (int stone : stones) buckets[stone]++;


            int i = max, lastJ = max;

            while (i > 0) {

                //skip invalid buckets, for the first run it will be the valid buckets
                if (buckets[i] == 0) {
                    i--;
                } else {

                    //if current max element has even frequency, then they will be pop up together and destroy each other
                    if (buckets[i] % 2 == 0) {
                        buckets[i] = 0; //destroy
                        i--;
                    } else {
                        //means the max element and the next max element diff.
                        int j = Math.min(i - 1, lastJ);
                        while (j > 0 && buckets[j] == 0) {
                            j--;
                        }

                        if (j == 0) {
                            // no more element left to fight against i, hence i is the answer
                            return i;
                        } else {

                            //they will fight each other and destroy each other
                            buckets[i]--;
                            buckets[j]--;

                            //if they have different weight, which should be in this case, then we need to update i-j frequency by 1
                            buckets[i - j]++;
                            lastJ = j; //cache it

                            //reset i to maximum value in buckets
                            //max value could be at i-j (due to above diff update, assume i=1000, j=1 => i-j = 999
                            // max value could be at j, because it was the second last max element in the buckets.
                            i = Math.max(i - j, j);

                        }
                    }


                }


            }

            return 0;


        }

    }
}
