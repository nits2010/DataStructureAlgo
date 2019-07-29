package Java.companyWise.facebook;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-17
 * Description: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * <p>
 * If there is no non-empty subarray with sum at least K, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 * <p>
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 * <p>
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 */
public class ShortestSubArraySumLessThenEqualN {

    public static void main(String args[]) {
        int a[] = {1};
        test(a, 1);

        int b[] = {1, 2};
        test(b, 4);

        int c[] = {2, -1, 2};
        test(c, 3);

        int d[] = {-2, -1, 3, 4, -3, 7};
        test(d, 7);

        int e[] = {2, -1, 2, 3};
        test(e, 3);


        int f[] = {-2, -1, -2, -3};
        test(f, 3);

        int g[] = {-2, -1, 0, -3};
        test(g, 1);

        int h[] = {-2, -1, 0, 1};
        test(h, 1);

        int i[] = {-2, 1, 0, -1};
        test(i, 1);

        int j[] = {-34, 37, 51, 3, -12, -50, 51, 100, -47, 99, 34, 14, -13, 89, 31, -14, -44, 23, -38, 6};
        test(j, 151);
    }

    private static void test(int[] a, int sum) {
        System.out.println("\nTesting ... \n");
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println(" For sum :" + sum);

        System.out.println("\n length is " + shortest(a, sum));

    }

    /**
     * Observation:
     * We need to find the smallest sub-array whose sum is >= sum.
     * <p>
     * Lets assume we keep adding the element form left to right and as soon as we find the current sum >= sum that means
     * we found the potential window which start from left and end at current index. [ compute the length ]
     * <p>
     * Now since we need to shirk the window as much as possible ,for that we'll start removing element from left and keep shirking but
     * doing that so, we may miss some sub-array within this window whose sum >= sum [ because array may contains negative numbers ].
     * This means we need to efficiently find from which side we should shirk the array
     * either from left side (removing front element) or right side (removing back element) and in order to find, we need to
     * try both of them, and find the minLength.
     * <p>
     * <p>
     * After closely looking into above pattern we find its like sliding window question where we need to find the maximum/minimum
     * element within the window. But since here window is dynamic that can shirk form either side (left/right).
     * <p>
     * The best data structure which provides this feature is Dequeue.
     * Now once we found the suitable data structure for our solution, we need to yet formalize the solution.
     * <p>
     * <p>
     * As at any moment we need to shirk the window from left (by removing all the left element sum from current sum ) or shirk from right (by removing all the right element sum from current sum )
     * <p>
     * To find the sum efficiently, we can pre-compute the prefix sum.
     * <p>
     * Let p[i] is the prefix sum of the array from 0 to i-1 element.
     * <p>
     * Now, at any moment our left pointer on x index and right pointer on y index; we are essentially looking for p[y] - p[x] >= sum; for all those x,y we need to find minimum of (y-x)
     * <p>
     * Since p[i] would make sure that elements are in increasing order in p. But there is a catch.
     * What about when array has negative elements in between, in this situation the prefix array might decrease and increase again which is the behaviour of monotonic array.
     * <p>
     * At any moment it happened that current prefix sum is smaller than previous prefix sum, then we won't able to extend this, so we need to shirk that element from left.
     * <p>
     * <p>
     * Now above expression lets take an example and understand;
     * [2,1,3,4,-3,7] and sum = 7;
     * prefix array would look like [0, 2, 3, 6, 10, 7, 14]
     * <p>
     * Now this is our array which need to consider;
     * *  0  1  2  3  4  5  6
     * * [0, 2, 3, 6, 10, 7, 14]
     * <p>
     * our queue = [] will contains indexes
     * let i = 0; i<prefix.length
     * <p>
     * at i = 0; our queue is empty, we'll push an element to start queue [0]
     * at i = 1; prefix[1] = 2 and   prefix[1] - prefix[queue[front]] < 7 and prefix[1] > prefix[queue[last]] i.e. they are in increasing order; Push this in queue-> [0,1]
     * at i = 2; prefix[2] = 3 and   prefix[2] - prefix[queue[front]] < 7 and prefix[1] > prefix[queue[last]] i.e. they are in increasing order; Push this in queue-> [0,1,2]
     * at i = 3; prefix[3] = 6 and   prefix[3] - prefix[queue[front]] < 7 and prefix[1] > prefix[queue[last]] i.e. they are in increasing order; Push this in queue-> [0,1,2,3]
     * at i = 4; prefix[4] = 10 and  prefix[4] - prefix[queue[front]]  = 10-0 > 7 which means our potential window is found (length=4-0=4) shirk the window till we can't make it, update queue-> [1,2,3]
     * at i = 4; prefix[4] = 10 and  prefix[4] - prefix[queue[front]]  = 10-2 > 7 which means our potential window is found (length=4-1=3) shirk the window, update queue-> [2,3]
     * at i = 4; prefix[4] = 10 and  prefix[4] - prefix[queue[front]]  = 10-3 >= 7 which means our potential window is found (length=4-2=2) shirk the window, update queue-> [3]
     * at i = 4; prefix[4] = 10 and  prefix[4] - prefix[queue[front]]  = 10-6 < 7 which means we can't shirk more and prefix[4] > prefix[queue[last]]  update queue-> [3,4]
     * at i = 5; prefix[5] = 7 and   prefix[5] - prefix[queue[front]]  < 7 but this value got decrease since prefix[5] < prefix[queue[last]]  ; so shirk form right update queue-> [3,5] -> notice the current value in original array is negative
     * at i = 6; prefix[6] = 14 and  prefix[6] - prefix[queue[front]] = 14-6  > 7 which means our potential window is found (length=6-3=3) shirk the window, update queue-> [5]
     * at i = 6; prefix[6] = 14 and  prefix[6] - prefix[queue[front]] = 14-7  >= 7 which means our potential window is found (length=6-5=1*) shirk the window, update queue-> []
     * <p>
     * ends answer is 1 :)
     * <p>
     * <p>
     * <p>
     * Formatted: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/337487/Full-thought-process-and-derivation-inspired-from-max-in-sliding-window-concept-JAVA
     *
     * @param a
     * @param sum
     * @return
     */

    private static int shortest(int a[], int sum) {

        if (a == null || a.length == 0)
            return -1;

        int prefix[] = new int[a.length + 1];

        prefix[0] = 0;
        for (int i = 1; i <= a.length; i++)
            prefix[i] = prefix[i - 1] + a[i - 1];

        final Deque<Integer> shirkingQueue = new LinkedList<>();

        int minLength = a.length + 1;

        for (int i = 0; i <= a.length; i++) {

            //prefix[i] - prefix[queue[front]] >= sum
            while (!shirkingQueue.isEmpty() && prefix[i] - prefix[shirkingQueue.peekFirst()] >= sum) {
                minLength = Math.min(minLength, i - shirkingQueue.peekFirst());
                shirkingQueue.pollFirst();
            }

            //prefix[i] < prefix[queue[front]] ; means negetive number encountered
            while (!shirkingQueue.isEmpty() && prefix[i] < prefix[shirkingQueue.peekLast()]) {
                shirkingQueue.pollLast();
            }

            //prefix[i] - prefix[queue[front]] < sum
            shirkingQueue.offerLast(i);

        }

        return minLength < a.length + 1 ? minLength : -1;

    }
}


