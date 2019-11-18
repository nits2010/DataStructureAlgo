package Java.LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-29
 * Description:https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int nums[] = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int re[] = maxSlidingWindow(nums, k);
        for (int i = 0; i < re.length; i++) {
            System.out.print(re[i] + " ");
        }

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;


        if (n == 0)
            return new int[0];
        int max[] = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            if (i < k) {
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
                    deque.pollLast();

                deque.offerLast(i);
            } else {


                max[i - k] = nums[deque.peekFirst()];

                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
                    deque.pollLast();

                while (!deque.isEmpty() && deque.peekFirst() <= i - k)
                    deque.pollFirst();

                deque.offerLast(i);
            }

        }


        max[n - k] = nums[deque.peekFirst()];
        return max;

    }
}
