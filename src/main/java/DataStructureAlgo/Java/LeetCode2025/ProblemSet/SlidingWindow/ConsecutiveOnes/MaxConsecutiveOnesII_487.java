package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.ConsecutiveOnes;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 9/5/2024
 * Question Category: 487. Max Consecutive Ones II
 * Description: https://leetcode.com/problems/max-consecutive-ones-ii/description/
 * https://leetcode.ca/all/487.html
 * <p>
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * <p>
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * <p>
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000.
 * <p>
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers
 * coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 * <p>
 * Solution:
 * Zeroes Position Queue: Maintain a queue to record the positions of encountered zeroes.
 * This queue becomes instrumental in precisely determining the next position to which the left boundary should shift when the window needs to exclude a
 * zero to adhere to the k flips constraint.
 * This queue-based approach ensures that adjustments to the window’s left boundary are made with full knowledge of zeroes’ positions,
 * thus optimizing window adjustments and maintaining the integrity of the k flips constraint.
 *
 * <p>
 * File reference
 * --------------
 * Duplicate {@link}
 * Similar {@link}
 * Extension {@link}
 * <p>
 * Tags
 * ----
 *
 * @medium
 * @Array
 * @SlidingWindow
 * @medium
 * @PremimumQuestion
 * @LeetCodeLockedProblem Company Tags
 * ------------
 * @Google
 * @Editorial
 */
public class MaxConsecutiveOnesII_487 {

    public static void main(String[] args) {
        boolean test = true;
        test &= flipOne0(new int[]{1, 1, 0, 1, 1, 1}, 6);
        test &= flipOne0(new int[]{1, 0, 1, 1, 0, 1}, 4);
        test &= flipOne0(new int[]{1, 1, 1, 1, 1}, 5);
        test &= flipOne0(new int[]{1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1}, 9);
        test &= flipOne0(new int[]{0, 0, 0, 0, 0, 0, 1}, 2);
        test &= flipOne0(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 10);
        test &= flipOne0(new int[]{1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, 2);
        test &= flipOne0(new int[]{0, 0, 0, 0, 0, 0, 0}, 1);
        test &= flipOne0(new int[]{1, 0, 1, 0}, 3);

        CommonMethods.printResult(test);

        //follow-up test
        System.out.println("-----------------Follow-up------------------------------");
        System.out.println("\n Follow-up; K = 1 ------------------------------");
        test = followUpFlipOne0(new int[]{1, 1, 0, 1, 1, 1}, 1, List.of(1, 2, 3, 4, 5, 6));
        test &= followUpFlipOne0(new int[]{1, 0, 1, 1, 0, 1}, 1, List.of(1, 2, 3, 4, 4, 4));
        test &= followUpFlipOne0(new int[]{1, 1, 1, 1, 1}, 1, List.of(1, 2, 3, 4, 5));
        test &= followUpFlipOne0(new int[]{1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1}, 1, List.of(1, 2, 3, 4, 5, 6, 6, 6, 6, 7, 8, 9));
        test &= followUpFlipOne0(new int[]{0, 0, 0, 0, 0, 0, 1}, 1, List.of(1, 1, 1, 1, 1, 1, 2));
        test &= followUpFlipOne0(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 1, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        test &= followUpFlipOne0(new int[]{1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, 1, List.of(1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2));
        test &= followUpFlipOne0(new int[]{0, 0, 0, 0, 0, 0, 0}, 1, List.of(1, 1, 1, 1, 1, 1, 1));
        test &= followUpFlipOne0(new int[]{1, 0, 1, 0}, 1, List.of(1, 2, 3, 3));
        test &= followUpFlipOne0(new int[]{0, 0, 0, 0, 0}, 1, List.of(1, 1, 1, 1, 1));

        System.out.println("\n Follow-up; K = n ------------------------------");
        test &= followUpFlipOne0(new int[]{0, 0, 0, 1}, 4, List.of(1, 2, 3, 4));
        test &= followUpFlipOne0(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2, List.of(1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 6));
        test &= followUpFlipOne0(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3, List.of(1, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10));

        CommonMethods.printResult(test);


    }

    private static boolean flipOne0(int[] nums, int expected) {
        System.out.println("-----------------------------------");
        System.out.println("Input : " + Arrays.toString(nums) + " Expected : " + expected);

        Solution solution = new Solution();
        SolutionSimplified solutionSimplified = new SolutionSimplified();
        int output = solution.findMaxConsecutiveOnes(nums);
        boolean result = output == expected;
        System.out.println("Output : " + output + " result : " + (result ? "Pass" : "Failed"));

        output = solutionSimplified.findMaxConsecutiveOnes(nums);
        boolean resultSimplified = output == expected;
        System.out.println("Output Simplified : " + output + " result : " + (resultSimplified ? "Pass" : "Failed"));
        return result && resultSimplified;
    }

    private static boolean followUpFlipOne0(int[] nums, int k, List<Integer> expected) {
        System.out.println("-----------------------------------");
        System.out.println("Input : " + Arrays.toString(nums) + " k : " + k + " Expected : " + expected);

        SolutionFollowUp.MaxOne solutionFollowUp = new SolutionFollowUp.MaxOne(k);
        List<Integer> output = new ArrayList<>();
        for (int n : nums) {
            solutionFollowUp.add(n);
            output.add(solutionFollowUp.findMaxConsecutiveOnes());
        }

        boolean result = CommonMethods.equalsValues(output, expected);
        System.out.println("Output : " + output + " result : " + result);
        return result;
    }

    static class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            return findMaxConsecutiveOnes(nums, 1);
        }

        /**
         * This is a sliding window problem. We need to find a window starting from a left and ending at a right (current-1) such that the number of zero between [left, current-1] should be only k.
         * Whenever we find such a window, we count max and then start a sequencing window starting from the left such that our zero count becomes k again
         * <p>
         * T/S : O(n)/O(1)
         *
         * @param nums
         * @param k
         * @return maxConsecutiveOnes
         */
        private int findMaxConsecutiveOnes(int[] nums, int k) {
            if (nums == null || nums.length == 0)
                return 0;

            int max = 0;
            int zero = 0;
            int left = 0, current = 0;

            while (current < nums.length) {

                //expand the window

                //count how many zeros seen so far, if not zero, then current will expand the window
                if (nums[current] == 0) {
                    zero++;

                    //if it's more than requested, than start flipping and count
                    if (zero == k + 1) {

                        //time to flip and count
                        //currently nums[current] == 0, and this zero is (k+1)th zero, so before the current (current - 1 )
                        //we had only k zero, which we can flip and count.
                        //our window is in [left,current-1]
                        max = Math.max(max, (current - 1) - left + 1);


                        //squeeze the window, till zero = k {means squeezing 1 zero from left }
                        int i = left;
                        //we need to find the next 1 post our first zero, which will be the starting point of our new window
                        while (i < current) {
                            //this is the zero to exclude it
                            if (nums[i] == 0) {
                                break;
                            }
                            i++;
                        }
                        left = i + 1;
                        zero--;
                    }
                }

                current++;
            }

            //if our window extended from left to current and last element was also 1, then we need to calculate it
            return Math.max(max, current - left);

        }

    }

    static class SolutionSimplified {

        /**
         * This is a sliding window problem. We need to find a window starting from a left and ending at a right (current-1) such that the number of zero between [left, current-1] should be only k.
         * Whenever we find such a window, we count max and then start a sequencing window starting from the left such that our zero count becomes k again
         * <p>
         * T/S : O(n)/O(1)
         *
         * @param nums
         * @param k
         * @return maxConsecutiveOnes
         */
        private int findMaxConsecutiveOnes(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            int left = 0, current = 0;
            int k = 1;

            while (current < nums.length) {

                //count the number of 0, re-utilize k
                if (nums[current] == 0) {
                    k--;
                }

                //if k < 0 then it means that k zero has been used,
                //we need to squeeze the window from the left
                //if an element at left is zero, then we can move left ahead and increment k ( by 1 )
                // otherwise left would move by one (accompany k++) as the overall window size will remain the same.
                if (k < 0 && nums[left++] == 0)
                    k++;

                current++;

            }
            return current - left;


        }

    }

    static class SolutionFollowUp {

        static class MaxOne {

            final int k;
            //maintain a queue for 0 index
            final Queue<Integer> queue;

            //maintain a window
            int left;

            //max window length so far
            int max = 0;

            //index of the coming element, 0 indexed and right of a window
            int right;

            //zero so far
            int zero = 0;


            MaxOne(int k) {
                this.k = k;
                queue = new LinkedList<>();
                left = 0;
                right = -1; // as there is no element in the stream, hence there is no window
            }

            void add(int num) {
                right++; //index of the current element

                if (num == 0) {
                    zero++;
                    queue.offer(right); //add this index to queue, this will define our left
                }
            }

            int findMaxConsecutiveOnes() {
                if (zero > k) {
                    //zero > k, we have to reduce the size from the left
                    while (zero > k && !queue.isEmpty()) {
                        int zeroIndex = queue.poll();

                        //reduce one zero as it's going out of the window
                        zero--;

                        //squeeze the window
                        if (zero == k) {
                            left = zeroIndex + 1;
                        }
                    }
                }
                return getConsecutiveOnesSize();
            }

            private int getConsecutiveOnesSize() {
                return max = Math.max(max, right - left + 1);
            }
        }
    }
}
