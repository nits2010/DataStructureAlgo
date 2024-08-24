package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.LeetCode2025.medium.ValidateBinarySearchTree_98;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import  DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description: https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * *     2
 * *    / \
 * *   1   3
 * <p>
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 * <p>
 * *     5
 * *    / \
 * *   1   4
 * *      / \
 * *     3   6
 * <p>
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{2,1,3}, true);
        test &= test(new Integer[]{5,1,4,null,null,3,6}, false);
        test &= test(new Integer[]{2,2,2}, false);
        test &= test(new Integer[]{}, true);
        test &= test(new Integer[]{2147483647}, true);
        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, boolean expected) {
        System.out.println("------------------------------");
        System.out.println("Input : "+ Arrays.toString(input) + "\nexpected : "+ expected);
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        boolean output = new ValidateBinarySearchTree().isValidBST(root);
        boolean result = output == expected;
        System.out.println("output : "+ output + " testResult "+(result ? "Pass" : "Fail"));
        return result;


    }


    /**
     * Complexity: O(n) / O(n)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
     * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Validate Binary Search Tree.
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        if (root == null)
            return true;

        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (null == root)
            return true;

        if ((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
