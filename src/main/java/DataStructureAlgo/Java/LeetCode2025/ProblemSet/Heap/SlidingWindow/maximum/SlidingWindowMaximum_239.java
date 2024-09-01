package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap.SlidingWindow.maximum;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/1/2024
 * Question Category: 239. Sliding Window Maximum
 * Description:
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation: https://leetcode.com/problems/sliding-window-maximum/description/
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.SlidingWindowMaximum}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @Queue
 * @SlidingWindow
 * [https://leetcode.com/tag/sliding-window/}
 * @Heap(PriorityQueue)
 * @MonotonicQueue
 *
 * <p>
 * Company Tags
 * -----
 * @Adobe
 * @Alibaba
 * @Amazon
 * @Apple
 * @Bloomberg
 * @Citadel
 * @Coursera
 * @Databricks
 * @eBay
 * @Facebook
 * @Google
 * @Lyft
 * @Microsoft
 * @Nutanix
 * @Pinterest
 * @Roblox
 * @Twitter
 * @Uber
 * @Wish
 * @Yelp
 * @Zenefits
 * @Salesforce <p>
 *
 * <p>
 * @Editorial
 * 1. https://leetcode.com/problems/sliding-window-maximum/solutions/871317/clear-thinking-process-with-picture-brute-force-to-mono-deque-python-java-javascript
 * 2. https://leetcode.com/problems/sliding-window-maximum/solutions/65881/o-n-solution-in-java-with-two-simple-pass-in-the-array
 * 3. https://leetcode.com/problems/sliding-window-maximum/solutions/458121/java-all-solutions-b-f-pq-deque-dp-with-explanation-and-complexity-
 * 4. https://algo.monster/problems/sliding_window_maximum
 *
 *
 */
public class SlidingWindowMaximum_239 {

    public static void main(String[] args) {

        boolean test = true;
        test &= test(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7});
        test &= test(new int[]{1}, 1, new int[]{1});
        test &= test(new int[]{-1, -3, -5, -7, -9}, 2, new int[]{-1, -3, -5, -7});
        test &= test(new int[]{4, -2, 2, -4, 6, -6, 8, -8}, 4, new int[]{4, 6, 6, 8, 8});
        test &= test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, new int[]{3, 4, 5, 6, 7, 8, 9});
        test &= test(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, 3, new int[]{9, 8, 7, 6, 5, 4, 3});
        test &= test(new int[]{2, 1, 5, 3, 4}, 5, new int[]{5});
        test &= test(new int[]{7, 2, 4, 6, 5}, 1, new int[]{7, 2, 4, 6, 5});
        System.out.println("======================");
        System.out.println(test ? "\n All Passed " : "\n Something Failed");

    }

    private static boolean test(int[] nums, int k, int[] expected) {
        System.out.println("-----------------------------");
        System.out.println("Input :" + Arrays.toString(nums) + " K : " + k + "\nExpected :" + Arrays.toString(expected));

        final SolutionUsingDQ solutionUsingDQ = new SolutionUsingDQ();
        int[] result = solutionUsingDQ.maxSlidingWindow(nums, k);
        boolean testResult = CommonMethods.equalsValues(expected, result);
        System.out.println("\nOutput Using DQ:" + Arrays.toString(result) + " Outcome : " + (testResult ? "Passed" : "Failed"));

        final SolutionWithLeftRightWindowMax solutionWithLeftRightWIndowMax = new SolutionWithLeftRightWindowMax();
        int[] resultUsingLeftRightWindowMax = solutionWithLeftRightWIndowMax.maxSlidingWindow(nums, k);
        boolean testResultUsingLeftRightWindowMax = CommonMethods.equalsValues(expected, result);
        System.out.println("\nOutput :" + Arrays.toString(resultUsingLeftRightWindowMax) + " Outcome : " + (testResultUsingLeftRightWindowMax ? "Passed" : "Failed"));

        return testResult && testResultUsingLeftRightWindowMax;

    }


    /**
     * T/S: O(n) / O(n)
     */
    static class SolutionUsingDQ {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (null == nums || nums.length < k)
                return new int[]{};

            if (nums.length == 1)
                return nums;

            final int[] result = new int[nums.length - k + 1];
            int index = 0;

            final Deque<Integer> dq = new ArrayDeque<>(k);

            //enqueue current window; keep a front element as maximum and all the element post that would be less than front
            //keep them in decreasing order
            int i = 0;
            while (i < k) {


                //remove all elements from the queue which are smaller than the new element
                while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                    dq.pollLast();

                //add the new element index at last, which could be smaller than the front element or maximum so far, if the queue is empty
                dq.offerLast(i);

                i++;
            }
            result[index++] = dq.isEmpty() ? Integer.MIN_VALUE : nums[dq.peekFirst()];

            //apply same logic to rest of the element
            while (i < nums.length) {

                //remove all elements from the queue which are smaller than the new element
                while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                    dq.pollLast();


                //if the front element index went out of the window, poll them
                while (!dq.isEmpty() && dq.peekFirst() <= i - k)
                    dq.pollFirst();

                //add the new element index at last, which could be smaller than the front element or maximum so far, if the queue is empty
                dq.offerLast(i);

                result[index++] = dq.isEmpty() ? Integer.MIN_VALUE : nums[dq.peekFirst()];

                i++;
            }

            return result;

        }
    }

    static class SolutionWithLeftRightWindowMax {

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (null == nums || nums.length < k)
                return new int[]{};

            if (nums.length == 1)
                return nums;

            int n = nums.length;
            final int[] result = new int[n - k + 1];
            int r = 0;

            //Partition the array in blocks of size w=4. The last block may have less then w.
            //2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|


            int[] leftMax = new int[n];
            int[] rightMax = new int[n];


            //Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
            //left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56
            //
            //Similarly calculate max in future by traversing from end to start.
            //right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56

            leftMax[0] = nums[0];
            rightMax[n - 1] = nums[n - 1];

            for (int i = 1, j = n - 2; i < n; i++, j--) {

                //Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
                //left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56
                leftMax[i] = (i % k == 0) //reset the window
                        ? nums[i]
                        : Math.max(nums[i], leftMax[i - 1]);

                //Similarly calculate max in future by traversing from end to start.
                //right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56
                rightMax[j] = (j % k == 0) //reset the window
                        ? nums[j]
                        : Math.max(nums[j], rightMax[j + 1]);
            }

            //now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
            //sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
            for (int i = 0; i + k <= nums.length; i++) {
                result[r++] = Math.max(rightMax[i], leftMax[i + k - 1]);
            }

            return result;
        }
    }

}
