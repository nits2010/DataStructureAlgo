package DataStructureAlgo.Java.LeetCode2025.medium.Tree.LCA;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 1650. Lowest Common Ancestor of a Binary Tree III
 * Description: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
 * https://leetcode.ca/all/1650.html
 *
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link LowestCommonAncestorOfABinaryTree_236} {@link LowestCommonAncestorOfABinaryTreeII_1644}
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 * @PremimumQuestion
 * @LeetCodeLockedProblem
 *
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Facebook
 * @LinkedIn
 * @Microsoft
 *
 * @Editorial <a href="https://leetcode.ca/2020-06-06-1650-Lowest-Common-Ancestor-of-a-Binary-Tree-III">...</a>
 */

public class LowestCommonAncestorOfABinaryTreeIII_1650 {

    class TreeNodeWithParent extends TreeNode {
        public TreeNode parent;

        public TreeNodeWithParent(int val) {
            super(val);
        }
    }

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 10, null);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 4, 5);
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 1, 3);
        test &= test(new Integer[]{1, 2}, 1, 2, 1);

        System.out.println(" test -> " + (test ? "All passed" : "Something Failed"));
    }

    private static boolean test(Integer[] tree, int p, int q, Integer expected) {
        System.out.println("Input " + Arrays.toString(tree) + " P : " + p + " Q :" + q);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
        final TreeNode pNode = CommonMethods.searchNodeByValue(root, p);
        final TreeNode qNode = CommonMethods.searchNodeByValue(root, q);
        final TreeNode expectedNode = CommonMethods.searchNodeByValue(root, expected);

        System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root) + " p : " + pNode + " q: " + qNode + " expected :" + expectedNode);

    }



    static class SolutionUsingSet {

        /**
         * O(2n)/O(n)
         */
        public TreeNode lowestCommonAncestor(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
            if (root == null)
                return null;

          return null;

        }
}
