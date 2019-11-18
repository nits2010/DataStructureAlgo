package Java.LeetCode;

import Java.helpers.GenericPrinter;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/last-stone-weight/
 * <p>
 * We have a collection of rocks, each rock has a positive integer weight.
 * <p>
 * Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * <p>
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 * <p>
 * [AMAZON]
 */
public class LastStoneWeight {


    public static void main(String[] args) {
        LastStoneWeightPriorityQueue solution = new LastStoneWeightPriorityQueue();
        LastStoneWeightBucketSort bucketSort = new LastStoneWeightBucketSort();

        test(new int[]{1, 1, 1, 1, 1000}, 0);

        test(new int[]{10, 5, 4, 10, 3, 1, 7, 8}, 0);
        test(new int[]{2, 7, 4, 1, 8, 1}, 1);
        test(new int[]{9, 3, 2, 10}, 0);
        test(new int[]{2, 4, 6, 8}, 0);
        test(new int[]{2, 4, 6, 8, 10}, 2);
        test(new int[]{1, 3, 5, 7, 9}, 1);
        test(new int[]{1, 3, 5, 7}, 0);
        test(new int[]{1, 5, 9, 11}, 2);
        test(new int[]{1, 9, 17, 25}, 0);
        test(new int[]{1, 9, 17, 25, 33}, 1);
        test(new int[]{1, 5, 5, 6, 8, 8, 10, 12}, 1);
        test(new int[]{1, 1, 3, 3, 3, 5, 5, 7, 7, 7, 9, 9, 12, 12, 12}, 0);
    }

    private static void test(int num[], int expected) {
        LastStoneWeightPriorityQueue solution = new LastStoneWeightPriorityQueue();
        LastStoneWeightBucketSort bucketSort = new LastStoneWeightBucketSort();
        System.out.println("Queue : " + solution.lastStoneWeight(num) + "  expected :" + expected);
        System.out.println("bucket : " + bucketSort.lastStoneWeight(num) + "  expected :" + expected);
    }

}

/**
 * O(n*log(n))
 * Runtime: 1 ms, faster than 97.26% of Java online submissions for Last Stone Weight.
 * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Last Stone Weight.
 */
class LastStoneWeightPriorityQueue {

    public int lastStoneWeight(int[] stones) {
        if (null == stones || stones.length == 0)
            return 0;

        System.out.println(GenericPrinter.toString(stones));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < stones.length; i++)
            pq.offer(stones[i]);

        while (pq.size() >= 2) {

            int s1 = pq.poll();
            int s2 = pq.poll();

            if (s1 != s2)
                pq.offer(s1 - s2);

        }

        /*
        for (int i = 0; i < stones.length - 1; ++i)
            pq.offer(pq.poll() - pq.poll());
        return pq.poll();
         */

        return pq.isEmpty() ? 0 : pq.poll();
    }
}

/**
 * We can apply bucket sort to make it work in linear time.
 * Since
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 * Algorithm:
 * 1. Find the maximum value in the stones array.
 * 2. Create that many buckets; bucket[max]
 * 3. Count the frequency of each bucket
 * 4. Run backward from bucket for each element from max to 0
 * <p>
 * a. if this bucket has even frequency; then they will destroy each other; move back
 * b. If this bucket has odd frequency[i]; then we need to find another bucket[j] and then update the bucket whose value is [i-j]+1
 * As well decrease the frequency of this buckets as we used both of the stones.
 * <p>
 * Keep doing this, at the end the bucket which has frequency > 0 and its odd is your value.
 * <p>
 *
 * <p>
 * Complexity:
 * Space: O(max) ; since 1 <= stones[i] <= 1000 then O(1000) is constant => O(1)
 * <p>
 * Time: O(n) {bucket frequency} + the maximum value of stones= 1000 and the max length of stones is 30 [1 <= stones.length <= 30[.
 * In wost scenario; all stones are duplicate Or unique or flatten like [1,1,1,1,1000]
 * <p>
 * at max the loop will run 1000 time. Hence constant
 * <p>
 * Space: O(1)
 * Time: O(n+1000) => O(n)
 * <p>
 * * Runtime: 1 ms, faster than 97.26% of Java online submissions for Last Stone Weight.
 * * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Last Stone Weight.
 */
class LastStoneWeightBucketSort {

    public int lastStoneWeight(int[] stones) {
        if (null == stones || stones.length == 0)
            return 0;

        /**
         * Find Max
         */
        int max = 0;
        for (int i = 0; i < stones.length; i++)
            max = Math.max(max, stones[i]);

        /**
         * Create 'max' number of buckets; max+1 since array has max as value
         */
        int buckets[] = new int[max + 1];

        /**
         * Count frequency
         */
        for (int i = 0; i < stones.length; i++)
            buckets[stones[i]]++;


        int i = max;
        int lastJ = max;

        /**
         * Run backward
         */
        while (i > 0) {

            /**
             * If this weight store is present
             */
            if (buckets[i] > 0) {

                /**
                 * if frequency = even, they will destroy each other
                 */
                if (buckets[i] % 2 == 0) {
                    buckets[i] = 0;
                    i--;
                    continue;
                } else {
                    /**
                     * Otherwise find a stone whose frequency is not 0
                     * Reset j as per lasj .
                     * Case like
                     * [1,1,1,1,1000]
                     * then if we set j = i-1 then j will start again from last entry always makes it to run ~1000 times every time for i
                     */
                    int j = i - 1 > lastJ ? lastJ : i - 1;
                    while (j > 0 && buckets[j] == 0) j--;

                    /**
                     * If no bucket left, then [i] is last stone
                     */
                    if (j == 0) {
                        return i;
                    } else {
                        lastJ = j;

                        /**
                         * Utilize both of the stones
                         */
                        buckets[j]--;
                        buckets[i]--;

                        /**
                         * create a new stone of i-j difference
                         */
                        buckets[i - j]++;


                        if (i - j > j) {
                            i = i - j;
                        } else {
                            i = j;
                        }

                    }
                }
            } else
                i--;
        }


        return 0;
    }
}