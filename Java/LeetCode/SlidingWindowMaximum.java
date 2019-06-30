package Java.LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-29
 * Description:
 */
public class SlidingWindowMaximum {

    public static void main(String args[]) {
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
        int max[] = new int[n - k+1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            if(i<k) {
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
                    deque.pollLast();

                deque.offerLast(i);
            }else {
                max[i - k] = nums[deque.peekFirst()];

                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
                    deque.pollLast();

                while (!deque.isEmpty() && deque.peekFirst() <= i - k)
                    deque.pollFirst();

                deque.offerLast(i);
            }

        }


        max[n-k] = nums[deque.peekFirst()];
        return max;

    }
}
