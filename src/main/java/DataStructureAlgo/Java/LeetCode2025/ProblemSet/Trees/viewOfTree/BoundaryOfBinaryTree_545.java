package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.viewOfTree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/21/2024
 * Question Category: 545. Boundary of Binary Tree
 * Description: https://leetcode.com/problems/boundary-of-binary-tree/description/ , https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1 , https://leetcode.ca/all/545.html
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 *
 * Input:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *
 * Ouput:
 * [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 *
 *
 * Example 2
 *
 * Input:
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.nonleetcode.Tree.TreeBoundaryTraversal}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 * @BinaryTree
 * @LeetCodeLockedProblem
 * @PremimumQuestion
 *
 * <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Atlassian
 * @eBay
 * @Google
 * @Microsoft
 * @Oracle
 *
 * @Editorial <a href="https://leetcode.ca/2017-05-28-545-Boundary-of-Binary-Tree">...</a>
 */
public class BoundaryOfBinaryTree_545 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10}, List.of(1, 2, 4, 7, 8, 9, 10, 6, 3));
        test &= test(new Integer[]{1, null, 2, 3, 4}, List.of(1, 3, 4, 2));
        System.out.println("=============================");
        System.out.println(test ? " All Passed " : " Something Failed ");
    }

    private static boolean test(Integer[] tree, List<Integer> expected) {
        System.out.println("--------------------------------");
        System.out.println("Input : " + Arrays.toString(tree));
        System.out.println("Expected : " + expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
        System.out.println("Tree : " + TreeTraversalRecursive.levelOrder(root));
        Solution solution = new Solution();
        List<Integer> output = solution.boundaryOfBinaryTree(root);
        boolean testResult = CommonMethods.equalsValues(output, expected);

        System.out.println("Output : " + output + " expected : " + expected + ": Test Result : " + testResult);
        return testResult;
    }


    /**
     * {@link DataStructureAlgo.Java.nonleetcode.Tree.TreeBoundaryTraversal}
     */
    static class Solution {

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            List<Integer> boundary = new ArrayList<>();
            if (root == null)
                return boundary;

            //to avoid duplicates
            List<TreeNode> leftBoundary = new ArrayList<>();
            List<TreeNode> rightBoundary = new ArrayList<>();
            List<TreeNode> leafBoundary = new ArrayList<>();

            boundary.add(root.val);


            leftBoundary(root.left, leftBoundary); // 1
            leafBoundary(root.left, leafBoundary); // 2
            leafBoundary(root.right, leafBoundary); // 2
            rightBoundary(root.right, rightBoundary); // 3

            leftBoundary.forEach(item -> boundary.add(item.val));
            leafBoundary.forEach(item -> boundary.add(item.val));
            rightBoundary.forEach(item -> boundary.add(item.val));

            return boundary;

        }

        private void leftBoundary(TreeNode root, List<TreeNode> leftBoundary) {

            if (root == null)
                return;

            //If a node in the left boundary and has a left child, then the left child is in the left boundary.
            if (root.left != null) {
                leftBoundary.add(root);
                leftBoundary(root.left, leftBoundary);
            }
            //If a node is in the left boundary, has no left child, but has a right child; then the right child is in the left boundary.
            else if (root.right != null) { //
                leftBoundary.add(root);
                leftBoundary(root.right, leftBoundary);
            }


        }

        private void leafBoundary(TreeNode root, List<TreeNode> leafBoundary) {

            if (root == null)
                return;

            //The leftmost leaf / rightmost leaf is not in the left/right boundary.
            if (root.left == null && root.right == null) {
                leafBoundary.add(root);
                return;
            }


            leafBoundary(root.left, leafBoundary);
            leafBoundary(root.right, leafBoundary);

        }

        private void rightBoundary(TreeNode root, List<TreeNode> rightBoundary) {
            if (root == null)
                return;

            //If a node is in the left boundary, has no left child, but has a right child; then the right child is in the left boundary.
            if (root.right != null) { //
                rightBoundary(root.right, rightBoundary);
                rightBoundary.add(root); // we need nodes in reverse order from bottom to top
            }
            //If a node in the left boundary and has a left child, then the left child is in the left boundary.
            else if (root.left != null) {

                rightBoundary(root.left, rightBoundary);
                rightBoundary.add(root); // we need nodes in reverse order from bottom to top
            }
        }
    }
}
