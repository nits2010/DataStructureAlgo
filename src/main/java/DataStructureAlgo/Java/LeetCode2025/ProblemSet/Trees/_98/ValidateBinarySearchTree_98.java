package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._98;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: 98. Validate Binary Search Tree
 * Description:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left
 * subtree
 * of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.ValidateBinarySearchTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree <p>
 * CompanyTags
 * -----
 * @Amazon
 * @Bloomberg
 * @Facebook
 * @Microsoft
 * @Adobe
 * @Apple
 * @Asana
 * @CapitalOne
 * @GoldmanSachs
 * @Google
 * @LinkedIn
 * @Mathworks
 * @Oracle
 * @Salesforce
 * @TripAdvisor
 * @Uber
 * @Visa
 * @VMware
 * @WalmartLabs
 * @Yandex
 * @Editorial
 */
public class ValidateBinarySearchTree_98 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{2, 1, 3}, true);
        test &= test(new Integer[]{5, 1, 4, null, null, 3, 6}, false);
        test &= test(new Integer[]{2, 2, 2}, false);
        test &= test(new Integer[]{}, true);
        test &= test(new Integer[]{2147483647}, true);
        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, boolean expected) {
        System.out.println("------------------------------");
        System.out.println("Input : " + Arrays.toString(input) + "\nexpected : " + expected);
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        Solution1 solution = new Solution1();
        boolean output = solution.isValidBST(root);
        boolean result = output == expected;
        System.out.println("output : " + output + " testResult " + (result ? "Pass" : "Fail"));
        return result;


    }


    static class Solution1 {

        public boolean isValidBST(TreeNode root) {
            if (root == null)
                return true;

            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode root, Long min, Long max) {
            if (root == null)
                return true;

            return (
                    (root.val > min && root.val < max)
                            && isValidBST(root.left, min, (long) root.val)
                            && isValidBST(root.right, (long) root.val, max)
            );
        }
    }

    static class Solution2 {

        public boolean isValidBST(TreeNode root) {
            if (root == null)
                return true;

            return isValidBST(root, null, null);
        }

        public boolean isValidBST(TreeNode root, Integer min, Integer max) {
            if (root == null)
                return true;

            if ((min != null && root.val <= min) || (max != null && root.val >= max))
                return false;
            return (isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max));
        }
    }
}
