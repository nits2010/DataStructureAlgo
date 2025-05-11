package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._2106;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/11/2025
 * Question Title: 2106. Maximum Fruits Harvested After at Most K Steps
 * Link: https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps
 * Description: Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts amounti fruits at the position positioni. fruits is already sorted by positioni in ascending order, and each positioni is unique.
 * <p>
 * You are also given an integer startPos and an integer k. Initially, you are at the position startPos. From any position, you can either walk to the left or right. It takes one step to move one unit on the x-axis, and you can walk at most k steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.
 * <p>
 * Return the maximum total number of fruits you can harvest.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
 * Output: 9
 * Explanation:
 * The optimal way is to:
 * - Move right to position 6 and harvest 3 fruits
 * - Move right to position 8 and harvest 6 fruits
 * You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
 * Example 2:
 * <p>
 * <p>
 * Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
 * Output: 14
 * Explanation:
 * You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
 * The optimal way is to:
 * - Harvest the 7 fruits at the starting position 5
 * - Move left to position 4 and harvest 1 fruit
 * - Move right to position 6 and harvest 2 fruits
 * - Move right to position 7 and harvest 4 fruits
 * You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
 * Example 3:
 * <p>
 * <p>
 * Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
 * Output: 0
 * Explanation:
 * You can move at most k = 2 steps and cannot reach any position with fruits.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= fruits.length <= 105
 * fruits[i].length == 2
 * 0 <= startPos, positioni <= 2 * 105
 * positioni-1 < positioni for any i > 0 (0-indexed)
 * 1 <= amounti <= 104
 * 0 <= k <= 2 * 105
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
 * @hard
 * @Array
 * @BinarySearch
 * @SlidingWindow
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumFruitsHarvestedAfterAtMostKSteps_2106 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{2, 8}, {6, 3}, {8, 6}}, 5, 4, 9));
        tests.add(test(new int[][]{{0, 9}, {4, 1}, {5, 7}, {6, 2}, {7, 4}, {10, 9}}, 5, 4, 14));
        tests.add(test(new int[][]{{0, 3}, {6, 4}, {8, 5}}, 3, 2, 0));
        tests.add(test(new int[][]{{0, 7}, {7, 4}, {9, 10}, {12, 6}, {14, 8}, {16, 5}, {17, 8}, {19, 4}, {20, 1}, {21, 3}, {24, 3}, {25, 3}, {26, 1}, {28, 10}, {30, 9}, {31, 6}, {32, 1}, {37, 5}, {40, 9}},
                21, 30, 71));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] fruits, int startPos, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"fruits", "startPos", "k", "Expected"}, true, fruits, startPos, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionSlidingWindow().maxTotalFruits(fruits, startPos, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SlidingWindow", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new SolutionPrefixSumSlidingWindow().maxTotalFruits(fruits, startPos, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PrefixSumSlidingWindow", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /*
Key observations:
 1. We can go k steps on left side and harvest all the fruits on left side ; leftMax
 2. We can go k steps on right side and harvest all the fruits on right side ; rightMax
 3. We can go X < k steps on left side and remaining k - 2*X on right side, harvest all the fruits ;
 4. We can go X < k steps on right side and remaining k - 2*X on left side, harvest all the fruits ;

 here 0<=X<=k

The important point here is that, fruits possition are sorted by position.
Which means that, regardless where you start (startPos), you are statred from very first position of the market. WHich is same as from startPos and move left side.

So at any position, Leftpos, you can see how far it is from the startPos [abs(startPos - Leftpos)] and then how many steps you can go from left to right position {rightPos - leftPos} from start position [ abs (startPos - rightPos)].

i.e
1. abs ( startPos - leftPos) : leftDistance ; tells you how far you are from startPostition from current leftPos
2. abs (startPos - rightPos) : rightDistance ; tell you how far you are from the startPosition from the current rightPosition
3. (rightPosition - leftPosition) : stepsUsed ;  the distance b/w left and right position  (steps)

which means, we can use a sliding window which start [0,0] as [left,right] and right keep moving towards right side ( may touch startPosition in b/w)
and the windowLength (right - left) can not exceed K size

Algorithm:
1. Start from 0th index or the first position as right.
2. Keep harvesting the fruits on the way towards right end
3. Check does the windowLength [left, right] exceed k size, if so, move leftPos
    3.1 To check windowLength, we will utilize above distance equation
        Min ( leftDistance, rightDistance) + stepsUsed
        if this go beyond k, then we need to move left


*/
    static public class SolutionSlidingWindow {
        public int maxTotalFruits(int[][] fruits, int startPos, int k) {
            int n = fruits.length;
            int left = 0, right = 0;
            int total = 0;
            int maxFruits = 0;

            while (right < n) {
                //extend the window
                total += fruits[right][1]; // harvest fruit at right position

                //shrink the window
                while (left <= right && !isReachable(startPos, fruits, left, right, k)) {
                    total -= fruits[left][1];
                    left++;
                }

                //track max fruits
                maxFruits = Math.max(maxFruits, total);

                right++;
            }
            return maxFruits;
        }

        private boolean isReachable(int startPos, int[][] fruits, int left, int right, int k) {
            int leftPos = fruits[left][0];
            int rightPos = fruits[right][0];
            int leftDistance = Math.abs(startPos - leftPos);
            int rightDistance = Math.abs(startPos - rightPos);
            int stepsUsed = (rightPos - leftPos);
            int distance = Math.min(leftDistance, rightDistance) + stepsUsed;
            return distance <= k;
        }

    }

    static class SolutionPrefixSumSlidingWindow {

        public int maxTotalFruits(int[][] fruits, int startPos, int k) {
            int n = fruits.length;
            int left = 0, right = 0;
            int total = 0;
            int maxFruits = 0;
            int[] prefxSum = new int[n + 1];
            prefxSum[0] = 0;

            for (int i = 0; i < n; i++) {
                prefxSum[i + 1] = prefxSum[i] + fruits[i][1]; // harvest fruit at right position
            }

            while (right < n) {

                //make sure the widow length does not exceed k
                while (left <= right && !isReachable(startPos, fruits, left, right, k)) {
                    left++;
                }

                if (left <= right) {
                    total = prefxSum[right + 1] - prefxSum[left];
                    maxFruits = Math.max(maxFruits, total);
                }
                right++;
            }
            return maxFruits;
        }

        private boolean isReachable(int startPos, int[][] fruits, int left, int right, int k) {
            int leftPos = fruits[left][0];
            int rightPos = fruits[right][0];
            int leftDistance = Math.abs(startPos - leftPos);
            int rightDistance = Math.abs(startPos - rightPos);
            int stepsUsed = (rightPos - leftPos);
            int distance = Math.min(leftDistance, rightDistance) + stepsUsed;
            return distance <= k;
        }
    }

}
