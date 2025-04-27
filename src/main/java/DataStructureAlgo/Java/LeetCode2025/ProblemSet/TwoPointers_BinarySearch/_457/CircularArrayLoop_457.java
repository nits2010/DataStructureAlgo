package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._457;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/9/2025
 * Question Title: 457. Circular Array Loop
 * Link: https://leetcode.com/problems/circular-array-loop/description/
 * Description: You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:
 * <p>
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move nums[i] steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.
 * <p>
 * A cycle in the array consists of a sequence of indices seq of length k where:
 * <p>
 * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative.
 * k > 1
 * Return true if there is a cycle in nums, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [2,-1,1,2,2]
 * Output: true
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * We can see the cycle 0 --> 2 --> 3 --> 0 --> ..., and all of its nodes are white (jumping in the same direction).
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [-1,-2,-3,-4,-5,6]
 * Output: false
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * The only cycle is of size 1, so we return false.
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,-1,5,1,4]
 * Output: true
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * We can see the cycle 0 --> 1 --> 0 --> ..., and while it is of size > 1, it has a node jumping forward and a node jumping backward, so it is not a cycle.
 * We can see the cycle 3 --> 4 --> 3 --> ..., and all of its nodes are white (jumping in the same direction).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 * <p>
 * <p>
 * Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._141.LinkedListCycle_141}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @TwoPointers <p><p>
 * Company Tags
 * -----
 * @GoldmanSachs
 * @Google
 * @Microsoft
 * @Nutanix <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CircularArrayLoop_457 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{2, -1, 1, 2, 2}, true));
        tests.add(test(new int[]{-1, -2, -3, -4, -5, 6}, false));
        tests.add(test(new int[]{1, -1, 5, 1, 4}, true));
        tests.add(test(new int[]{-1, 2, 1, 2, 2}, false));
        tests.add(test(new int[]{-2, 1, -1, -2, -2}, false));
        tests.add(test(new int[]{2, 2, 2, 2, 2, 4, 7}, false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "Expected"}, true, nums, expected);

        boolean output ;
        boolean pass, finalPass = true;

        SolutionPolynomial solutionPolynomial = new SolutionPolynomial();
        SolutionUsingVisited solutionUsingVisited = new SolutionUsingVisited();
        SolutionUsingArray solutionUsingArray = new SolutionUsingArray();
        SolutionUsingArray_FollowUp solutionUsingArrayFollowUp = new SolutionUsingArray_FollowUp();


        output = solutionPolynomial.circularArrayLoop(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Polynomial", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionUsingVisited.circularArrayLoop(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"UsingVisited", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionUsingArray.circularArrayLoop(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"UsingArray", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionUsingArrayFollowUp.circularArrayLoop(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Followup", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * O(n^2) / O(1)
     */
    static class SolutionPolynomial {
        public boolean circularArrayLoop(int[] nums) {

            int slow, fast; //represent the index of element being visited
            int dir;
            //consider all elements as potential start point
            for (int i = 0; i < nums.length; i++) {

                //consider i as start point
                slow = i;
                fast = i;
                dir = nums[i] > 0 ? 1 : -1;

                //run cycle detection algorithm
                do {
                    fast = next(nums, dir, fast);

                    if (fast == -1)
                        break;

                    fast = next(nums, dir, fast);
                    slow = next(nums, dir, slow);

                } while (slow != fast && slow != -1 && fast != -1);

                if (slow == fast && slow != -1) {
                    return true;
                }
            }
            return false;
        }

        private int next(int[] nums, int dir, int i) {

            int cDir = nums[i] < 0 ? -1 : 1;

            //if both -nev, ans +ve, if both pos, ans +ve, one neg and other pos, ans -ve
            if (dir * cDir < 0)
                return -1; //not possible index

            int n = nums.length;
            int next = (i + nums[i]) % n;

            if (next < 0) {
                next = n + next;
            }

            //if we get the index back then its one element cycle, which is incorrect
            return next == i ? -1 : next;

        }
    }

    /**
     * O(n) / O(n)
     * visited[] make sure that every index will visit only constant number of times
     * 1. when already visited, hence never process again - O(n)
     * below happen only one time per index, since we mark them later visited
     * 2. when first time fast move (assume its +ve only) - constant
     * 3. when second time fast move (assume its +ve only) - constant
     * 4. when first time slow move (assume its +ve only) - constant
     * <p>
     * Key Point:
     * You're marking visited[i] = true for every slow and fast pointer movement.
     * <p>
     * This ensures that:
     * <p>
     * No index is re-processed in future outer loop iterations.
     * <p>
     * Once visited, we skip it.
     * <p>
     * Thus, every element is processed at most once in total across all iterations.
     */
    static class SolutionUsingVisited {
        public boolean circularArrayLoop(int[] nums) {

            int slow = 0, fast = 0; //represent the index of element being visited
            int dir = 1;
            boolean[] visited = new boolean[nums.length];

            //consider all elements as potential start point
            for (int i = 0; i < nums.length; i++) {

                if (visited[i])
                    continue;

                //consider i as start point
                slow = i;
                fast = i;
                dir = nums[i] > 0 ? 1 : -1;
                visited[i] = true;

                //run cycle detection algorithm
                do {
                    fast = next(nums, dir, fast);

                    if (fast == -1)
                        break;

                    fast = next(nums, dir, fast);
                    slow = next(nums, dir, slow);

                    if (fast > 0)
                        visited[fast] = true;
                    if (slow > 0)
                        visited[slow] = true;

                } while (slow != fast && slow != -1 && fast != -1);

                if (slow == fast && slow != -1) {
                    return true;
                }
            }
            return false;
        }

        private int next(int[] nums, int dir, int i) {

            int cdir = nums[i] < 0 ? -1 : 1;

            //if both -nev, ans +ve, if both pos, ans +ve, one neg and other pos, ans -ve
            if (dir * cdir < 0)
                return -1; //not possible index

            int n = nums.length;
            int next = (i + nums[i]) % n;

            if (next < 0) {
                next = n + next;
            }

            //if we get the index back then its one element cycle, which is incorrect
            return next == i ? -1 : next;

        }
    }

    /**
     * Worst Case:
     * Imagine all elements are valid for movement but no cycle exists.
     * <p>
     * Each time you pick i, you might traverse O(n) elements before marking just nums[i] = 0.
     * <p>
     * Hence, total work done could be O(n²) in worst-case.
     */
    static class SolutionUsingArray {
        public boolean circularArrayLoop(int[] nums) {

            int slow = 0, fast = 0; //represent the index of element being visited
            int dir = 1;

            //consider all elements as potential start point
            for (int i = 0; i < nums.length; i++) {

                if (nums[i] == 0)
                    continue;

                //consider i as start point
                slow = i;
                fast = i;
                dir = nums[i] > 0 ? 1 : -1;

                //run cycle detection algorithm
                do {
                    fast = next(nums, dir, fast);

                    if (fast == -1)
                        break;

                    fast = next(nums, dir, fast);
                    slow = next(nums, dir, slow);

                    if (fast == -1 || slow == -1 || nums[fast] == 0 || nums[slow] == 0)
                        break;

                } while (slow != fast);

                if (slow == fast && slow != -1) {
                    return true;
                }

                nums[i] = 0; //visited
            }
            return false;
        }

        private int next(int[] nums, int dir, int i) {

            int cdir = nums[i] < 0 ? -1 : 1;

            //if both -nev, ans +ve, if both pos, ans +ve, one neg and other pos, ans -ve
            if (dir * cdir < 0)
                return -1; //not possible index

            int n = nums.length;
            int next = (i + nums[i]) % n;

            if (next < 0) {
                next = n + next;
            }

            //if we get the index back then its one element cycle, which is incorrect
            return next == i ? -1 : next;

        }
    }


    /**
     * After checking for a cycle starting at index i,
     * you are marking all elements visited in that traversal as 0 (not just nums[i]).
     * <p>
     * O(n) / O(1)
     */
    static class SolutionUsingArray_FollowUp {
        public boolean circularArrayLoop(int[] nums) {

            int slow = 0, fast = 0; //represent the index of element being visited
            int dir = 1;

            //consider all elements as potential start point
            for (int i = 0; i < nums.length; i++) {

                if (nums[i] == 0)
                    continue;

                //consider i as start point
                slow = i;
                fast = i;
                dir = nums[i] > 0 ? 1 : -1;

                //run cycle detection algorithm
                do {
                    fast = next(nums, dir, fast);

                    if (fast == -1)
                        break;

                    fast = next(nums, dir, fast);
                    slow = next(nums, dir, slow);

                    if (fast == -1 || slow == -1 || nums[fast] == 0 || nums[slow] == 0)
                        break;

                } while (slow != fast);

                if (slow == fast && slow != -1) {
                    return true;
                }

                // Mark all elements visited in this path as 0
                slow = i;
                int valDir = nums[i] > 0 ? 1 : -1;
                while (true) {
                    int nextIdx = next(nums, valDir, slow);
                    if (nextIdx == -1)
                        break;
                    nums[slow] = 0;
                    slow = nextIdx;
                }
            }
            return false;
        }

        private int next(int[] nums, int dir, int i) {

            int cdir = nums[i] < 0 ? -1 : 1;

            //if both -nev, ans +ve, if both pos, ans +ve, one neg and other pos, ans -ve
            if (dir * cdir < 0)
                return -1; //not possible index

            int n = nums.length;
            int next = (i + nums[i]) % n;

            if (next < 0) {
                next = n + next;
            }

            //if we get the index back then its one element cycle, which is incorrect
            return next == i ? -1 : next;

        }
    }


}
