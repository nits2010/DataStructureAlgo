package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.__654;

import DataStructureAlgo.Java.helpers.*;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/12/2025
 * Question Title: 654. Maximum Binary Tree
 * Link: https://leetcode.com/problems/maximum-binary-tree/description
 * Description: You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:
 * <p>
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 * - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 * - Empty array, so no child.
 * - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 * - Empty array, so no child.
 * - Only one element, so child is a node with value 1.
 * - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 * - Only one element, so child is a node with value 0.
 * - Empty array, so no child.
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,2,1]
 * Output: [3,null,2,null,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * All integers in nums are unique.
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
 * @Tree
 * @BinaryTree
 * @DivideandConquer
 * @Stack
 * @MonotonicStack
 * @Array <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumBinaryTree_654 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 2, 1, 6, 0, 5}, Arrays.asList(6, 3, 5, null, 2, 0, null, null, 1)));
        tests.add(test(new int[]{3, 2, 1}, Arrays.asList(3, null, 2, null, 1)));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        TreeNode root = new Solution().constructMaximumBinaryTree(nums);
        output = TreeTraversalRecursive.levelOrderWithNull(root);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            int length = nums.length;
            if (length == 1)
                return new TreeNode(nums[0]);
            return construct(nums, 0, length - 1);
        }

        private TreeNode construct(int[] nums, int low, int high) {
            if (low > high || low == nums.length)
                return null;

            //if there is only one element
            if (low == high)
                return new TreeNode(nums[low]);

            int indexOfMax = getMaxIndex(nums, low, high);

            TreeNode root = new TreeNode(nums[indexOfMax]);

            root.left = construct(nums, low, indexOfMax - 1);
            root.right = construct(nums, indexOfMax + 1, high);

            return root;
        }

        private int getMaxIndex(int[] nums, int low, int high) {
            int maxIndex = low;

            for (int i = low; i <= high; i++) {
                if (nums[maxIndex] < nums[i]) {
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
    }
}
