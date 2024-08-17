package DataStructureAlgo.Java.LeetCode2025.medium.Tree.LCA.I;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category: 235. Lowest Common Ancestor of a Binary Search Tree
 * Description: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * <p>
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.LowestCommonAncestor}
 * Similar {@link LowestCommonAncestorOfABinaryTree_236}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Facebook
 * @Amazon
 * @Apple
 * @Google
 * @Editorial
 */

public class LowestCommonAncestorOfABinarySearchTree_235 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5}, 2, 8, 6);
        test &= test(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5}, 2, 4, 2);
        test &= test(new Integer[]{2, 1}, 2, 1, 2);
        System.out.println(test ? "\nAll passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] tree, Integer p, Integer q, Integer expected) {
        System.out.println("-------------------------");
        System.out.println("Input " + Arrays.toString(tree) + " P : " + p + " Q :" + q);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(tree);
        final TreeNode pNode = CommonMethods.searchNodeByValueBST(root, p);
        final TreeNode qNode = CommonMethods.searchNodeByValueBST(root, q);
        final TreeNode expectedNode = CommonMethods.searchNodeByValueBST(root, expected);

        System.out.println("Tree {pre-order} : " + TreeTraversalRecursive.preOrder(root) + " p : " + pNode + " q: " + qNode + " expected :" + expectedNode);
        SolutionRecursive solutionRecursive = new SolutionRecursive();
        SolutionIterative solutionIterative = new SolutionIterative();
        SolutionUsingPath solutionUsingPath = new SolutionUsingPath();

        TreeNode resultTreeRecursive = solutionRecursive.lowestCommonAncestor(root, pNode, qNode);
        TreeNode resultTreeIterative = solutionIterative.lowestCommonAncestor(root, pNode, qNode);
        TreeNode resultTreeUsingPath = solutionUsingPath.lowestCommonAncestor(root, pNode, qNode);

        boolean testResultRecursive = resultTreeRecursive == expectedNode;
        boolean testResultIterative = resultTreeIterative == expectedNode;
        boolean testResultUsingPath = resultTreeUsingPath == expectedNode;

        System.out.println("resultTreeRecursive : " + resultTreeRecursive + " result : " + testResultRecursive);
        System.out.println("resultTreeIterative : " + resultTreeIterative + " result : " + testResultIterative);
        System.out.println("resultTreeUsingPath : " + resultTreeUsingPath + " result : " + testResultUsingPath);
        return testResultRecursive && testResultIterative && testResultUsingPath;

    }


    /**
     * {@link  LowestCommonAncestorOfABinaryTree_236.SolutionRecursive}
     */
    static class SolutionRecursive {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null)
                return null;

            //if root is the root of p and q, where p or q could be of left or right side
            if ((p.val <= root.val && q.val >= root.val) ||
                    (q.val <= root.val && p.val >= root.val)) {
                return root;
            }

            //Since P and Q are not on opposite side, then they will be either
            // 1. p and q are on the left side of the root Or
            // 2. p and q are on the right side of the root
            if (p.val < root.val)
                return lowestCommonAncestor(root.left, p, q);
            else
                return lowestCommonAncestor(root.right, p, q);

        }
    }

    /**
     * {@link  LowestCommonAncestorOfABinaryTree_236.SolutionUsingParentsMap}
     */
    static class SolutionIterative {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null)
                return null;

            while (root != null) {
                if ((p.val <= root.val && q.val >= root.val) ||
                        (q.val <= root.val && p.val >= root.val)) {
                    return root;
                }

                if (p.val < root.val)
                    root = root.left;
                else
                    root = root.right;
            }

            return null;

        }

    }

    /**
     * {@link  LowestCommonAncestorOfABinaryTree_236.SolutionUsingPath}
     */
    static class SolutionUsingPath {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null)
                return null;

            List<TreeNode> pathP = new ArrayList<>();
            List<TreeNode> pathQ = new ArrayList<>();
            BSTPath(root, p, pathP);
            BSTPath(root, q, pathQ);

            if (pathQ.isEmpty() || pathP.isEmpty())
                return null; //no path found for either of the root

            int i = 0, j = 0;
            while (i < pathP.size() && j < pathQ.size() && pathP.get(i) == pathQ.get(j)) {
                i++;
                j++;
            }


            return pathP.get(i - 1);

        }

        private boolean BSTPath(TreeNode root, TreeNode node, List<TreeNode> path) {
            if (root == null)
                return false;

            path.add(root);
            if (root == node)
                return true;

            if (node.val < root.val) {
                if (BSTPath(root.left, node, path))
                    return true;
            } else {
                if (BSTPath(root.right, node, path))
                    return true;
            }

            path.remove(path.size() - 1);
            return false;

        }
    }
}
