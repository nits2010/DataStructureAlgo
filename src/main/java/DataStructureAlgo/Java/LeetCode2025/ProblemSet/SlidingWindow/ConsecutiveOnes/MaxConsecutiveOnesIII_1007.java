package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.ConsecutiveOnes;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 9/5/2024
 * Question Category: 1004. Max Consecutive Ones III
 * Description:https://leetcode.com/problems/max-consecutive-ones-iii/description/
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 * <p>
 * File reference
 * --------------
 * Duplicate {@link}
 * Similar {@link}
 * Extension {@link MaxConsecutiveOnesII_487 }
 * <p>
 * Tags
 * ----
 *
 * @medium
 * @Array
 * @BinarySearch
 * @SlidingWindow
 * @PrefixSum <p><p>
 * Company Tags
 * ------------
 * @Google
 * @Facebook
 * @Microsoft
 * @Amazon
 * @ByteDance
 * @Yandex <p><p>
 * @Editorial https://leetcode.com/problems/max-consecutive-ones-iii/solutions/5742421/multiple-solutions-detailed-explanation-sliding-window-sliding-window-optimized-binary-search
 */
public class MaxConsecutiveOnesIII_1007 {

    public static void main(String[] args) {
        boolean test = true;
        test &= flipK0(new int[]{0, 0, 0, 1}, 4, 4);
        test &= flipK0(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2, 6);
        test &= flipK0(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3, 10);
        CommonMethods.printResult(test);


    }

    private static boolean flipK0(int[] nums, int k, int expected) {
        System.out.println("-----------------------------------");
        System.out.println("Input : " + Arrays.toString(nums) + " k : " + k + " Expected : " + expected);

        Solution solution = new Solution();
        SolutionSimplified solutionSimplified = new SolutionSimplified();
        SolutionBinarySearch solutionBS = new SolutionBinarySearch();

        int output = solution.longestOnes(nums, k);
        boolean result = output == expected;
        System.out.println("Output : " + output + " result : " + (result ? "Pass" : "Failed"));

        output = solutionSimplified.longestOnes(nums, k);
        boolean resultSimplified = output == expected;
        System.out.println("Output Simplified : " + output + " result : " + (resultSimplified ? "Pass" : "Failed"));

        output = solutionBS.longestOnes(nums, k);
        boolean resultBS = output == expected;
        System.out.println("Output BS : " + output + " result : " + (resultBS ? "Pass" : "Failed"));

        return result && resultSimplified && resultBS;
    }

    static class Solution {

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
        private int longestOnes(int[] nums, int k) {
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
        private int longestOnes(int[] nums, int k) {
            if (nums == null || nums.length == 0)
                return 0;

            int left = 0, current = 0;

            while (current < nums.length) {

                //count the number of 0, re-utilize k
                if (nums[current] == 0) {
                    k--;
                }

                //if k < 0 then it means that k zero has been used
                //we need to squeeze the window from the left
                //if an element at left is zero,
                // then we can move left ahead and increment k ( by 1 )
                //if the element at left is not zero, then moving left by 1 would accompany 1 zero in the window
                if (k < 0 && nums[left++] == 0)
                    k++;

                current++;

            }
            return current - left;


        }

    }

    static class SolutionBinarySearch {

        /**
         * We can solve this problem by applying binary search on the result space.
         * Intuition: Assume you have a sliding window which starts from left and end at right, [left, right], between this window, there could be X number of zero.
         * here would be 0<=X<=n; where n is the size of the array. In order to make X = k, we have to sequeeze the window either starting from left or by right.
         * means, removing the zero from the window till it hit X=k.
         * <p>
         * Algorithm:
         * 1. Start creating your search space left = 0 to right = n - 1
         * 2. Get the mid; now our window size is limited to [0,mid]
         * 3. check does the current window size {0,mid} has less than equal to k zero,
         * 3.1 if so, then we could flip either less than or equal to k zero to get the total 1's in [0,mid]
         * 3.2 if not so, then we need to count how many zero on the first half of the window {0,mid} and then find the remaining half in right side window {mid,n-1}
         * 4. keep squeezing / expand a window either from left or right.
         * <p>
         * T/S : O(n)/O(1)
         *
         * @param nums
         * @param k
         * @return maxConsecutiveOnes
         */
        private int longestOnes(int[] nums, int k) {
            if (nums == null || nums.length == 0)
                return 0;

            int left = 0, right = nums.length;
            int max = 0;

            //window b/w left and right
            while (left <= right) {

                int mid = left + (right - left) / 2;

                //check does this window has how many zeroes and can be converted to <=K zero ?
                if (validKWindow(nums, mid, k)) {

                    //if we can, then the current window is our possible solution
                    // the window would be till mid
                    max = mid;
                    left = mid + 1; //try to expand the window
                } else
                    right = mid - 1; //have to squeeze the window
            }
            return max;

        }

        private boolean validKWindow(int[] nums, int mid, int k) {

            int zero = 0;

            //check does the window has how many zero's
            int right = 0;
            for (; right < mid; right++) {
                if (nums[right] == 0)
                    zero++;
            }

            //if the zero count in the window is <=k then we are ok to have window {0,mid}
            if (zero <= k)
                return true;

            //if zeroes are more than k, find if we can shorten the window start from {0,mid,n-1}
            int left = 1;
            while (right < nums.length) {

                //if current element is zero, then its adds up zero count
                if (nums[right] == 0)
                    zero++;

                //if the window start point has 0, then it reduces as we are moving left to right
                if (nums[left - 1] == 0)
                    zero--;

                if (zero <= k)
                    return true;

                right++;
                left++;
            }

            //we can't squeeze the window to zero = k
            return false;
        }


    }
}
