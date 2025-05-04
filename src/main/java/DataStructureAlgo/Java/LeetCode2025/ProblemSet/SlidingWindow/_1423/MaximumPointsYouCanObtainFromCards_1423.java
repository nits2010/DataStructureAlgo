package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1423;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/4/2025
 * Question Title: 1423. Maximum Points You Can Obtain from Cards
 * Link: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/?envType=problem-list-v2&envId=2kb1b49r
 * Description: There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 * <p>
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * <p>
 * Your score is the sum of the points of the cards you have taken.
 * <p>
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 * <p>
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 * <p>
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= cardPoints.length <= 105
 * 1 <= cardPoints[i] <= 104
 * 1 <= k <= cardPoints.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @SlidingWindow
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon
 * @Adobe
 * @Flipkart <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumPointsYouCanObtainFromCards_1423 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 4, 5, 6, 1}, 3, 12)); //entire right window
        tests.add(test(new int[]{2, 2, 2}, 2, 4)); //either of the windows
        tests.add(test(new int[]{9, 7, 7, 9, 7, 7, 9}, 7, 55)); //entire array
        tests.add(test(new int[]{5, 6, 1, 2, 1, 5, 2, 7, 4}, 4, 22)); // half-left, half right
        tests.add(test(new int[]{5, 6, 9, 20, 1, 5, 2, 7, 4}, 4, 40)); // entire left

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_PrefixSuffixSum solutionPrefixSuffixSum = new Solution_PrefixSuffixSum();
        output = solutionPrefixSuffixSum.maxScore(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"PrefixSuffixSum", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_SlidingWindowForward solutionSlidingWindowForward = new Solution_SlidingWindowForward();
        output = solutionSlidingWindowForward.maxScore(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindowForward", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionSlidingWindowBackward slidingWindowBackward = new SolutionSlidingWindowBackward();
        output = slidingWindowBackward.maxScore(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindowBackward", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_PrefixSuffixSum {
        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;

            int[] prefixSum = new int[n]; //entire left window of k length
            int[] suffixSum = new int[n]; //entire right window of k length
            prefixSum[0] = cardPoints[0];
            suffixSum[n - 1] = cardPoints[n - 1];

            for (int i = 1, j = n - 2; i < n; i++, j--) {
                prefixSum[i] = prefixSum[i - 1] + cardPoints[i];
                suffixSum[j] = suffixSum[j + 1] + cardPoints[j];
            }

            int leftPick = prefixSum[k - 1];
            int rightPick = suffixSum[n - k];
            int maxPoints = Math.max(leftPick, rightPick);

            int idx = n - k + 1;
            for (int i = 0; i < k - 1; i++) {
                leftPick = prefixSum[i];
                rightPick = suffixSum[idx + i];
                maxPoints = Math.max(maxPoints, leftPick + rightPick);
            }
            return maxPoints;

        }
    }

    static class Solution_SlidingWindowForward {
        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;

            int prefixSum = 0; //entire left window of k length
            int suffixSum = 0; //entire right window of k length

            for (int i = 0, j = n - 1; i < k; i++, j--) {
                prefixSum += cardPoints[i];
                suffixSum += cardPoints[j];
            }

            //get max when we take either of the windows only
            int maxPoints = Math.max(prefixSum, suffixSum);

            //now start taking a left + right window, 1 from a left, remaining right, 2 from a left , remaining right ...
            prefixSum = 0;
            int rIdx = n - k; //right window + 1 start point
            for (int i = 0; i < k - 1; i++) {
                prefixSum += cardPoints[i];
                suffixSum = suffixSum - cardPoints[rIdx + i];
                maxPoints = Math.max(maxPoints, prefixSum + suffixSum);
            }
            return maxPoints;

        }
    }

    /**
     * https://leetcode.ca/2019-10-23-1423-Maximum-Points-You-Can-Obtain-from-Cards/
     *
     */
    static class SolutionSlidingWindowBackward {
        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;

            int windowSum = 0; //entire right window of k length

            for (int i = n - k; i < n; i++) {
                windowSum += cardPoints[i];
            }


            int maxPoints = windowSum;

            //now start taking a left + right window, 1 from a left, remaining right, 2 from a left , remaining right ...
            int rIdx = n - k; //right window + 1 start point
            for (int i = 0; i < k; i++) {
                windowSum += cardPoints[i] - cardPoints[rIdx + i];
                maxPoints = Math.max(maxPoints, windowSum);
            }
            return maxPoints;

        }
    }

}
