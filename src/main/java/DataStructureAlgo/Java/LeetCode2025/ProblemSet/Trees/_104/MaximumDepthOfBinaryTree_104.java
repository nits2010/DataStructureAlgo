package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._104;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date:11/08/24
 * Question Category: 104. Maximum Depth of Binary Tree @easy
 * Description: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 *
 *
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.MaximumDepthBinaryTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree
 *
 *
 * <p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Amazon
 * @Spotify
 * @Google
 * @Microsoft
 *
 * @Editorial
 */

public class MaximumDepthOfBinaryTree_104 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1,2,3,4,5,6,7}, 3);
        test &= test(new Integer[]{1,2,3,4}, 3);
        test &= test(new Integer[]{1,2,3}, 2);
        System.out.println("\nTest result = " + (test ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, int expected) {
        Solution solution = new Solution();
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        int output = solution.maxDepth(root);
        System.out.println("Expected : " + expected + " Obtained : " + output);
        return expected == solution.maxDepth(root);
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
                return maxDepthOrHeightOfBinaryTree(root);
        }

        private int maxDepthOrHeightOfBinaryTree(TreeNode root){
            return (root == null) ? 0 : Math.max(maxDepthOrHeightOfBinaryTree(root.left), maxDepthOrHeightOfBinaryTree(root.right)) + 1;
        }
    }
}
