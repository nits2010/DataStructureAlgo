package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BstToList._114;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Flatten Binary Tree To Linked List
 * Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * Description:
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
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FlattenBinaryTreeToLinkedList_114 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 5, 3, 4, null, 6}, new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6});
        test &= test(new Integer[]{}, new Integer[]{});

        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        System.out.println("----------------------------");
        System.out.println("Input : " + Arrays.toString(input) + "\nexpected : " + Arrays.toString(expected));

        SolutionRecursive solutionRecursive = new SolutionRecursive();



        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        solutionRecursive.flatten(root);
        List<Integer> levelOrder = TreeTraversalRecursive.levelOrderWithNull(root);
        boolean testResultRecursive = CommonMethods.equalsValues(levelOrder, Arrays.asList(expected));
        System.out.println("Tree1  : " + levelOrder + " testResultRecursive1 " + (testResultRecursive ? "Pass" : "Fail"));

        SolutionRecursive2 solutionRecursive2 = new SolutionRecursive2();
        root = TreeBuilder.buildTreeFromLevelOrder(input);
        solutionRecursive2.flatten(root);
        levelOrder = TreeTraversalRecursive.levelOrderWithNull(root);
        boolean testResultRecursive2 = CommonMethods.equalsValues(levelOrder, Arrays.asList(expected));
        System.out.println("Tree2  : " + levelOrder + " testResultRecursive2 " + (testResultRecursive2 ? "Pass" : "Fail"));

        SolutionIterativeWithoutSpace solutionIterativeWithoutSpace = new SolutionIterativeWithoutSpace();
        root = TreeBuilder.buildTreeFromLevelOrder(input);
        solutionIterativeWithoutSpace.flatten(root);
        levelOrder = TreeTraversalRecursive.levelOrderWithNull(root);
        boolean testResultWithoutSpace = CommonMethods.equalsValues(levelOrder, Arrays.asList(expected));
        System.out.println("Tree3  : " + levelOrder + " testResultWithoutSpace " + (testResultWithoutSpace ? "Pass" : "Fail"));


        return testResultRecursive && testResultRecursive2 && testResultWithoutSpace;

    }


    static class SolutionRecursive {
        public void flatten(TreeNode root) {
            if (root == null)
                return;

            flatten(root.left);
            flatten(root.right);

            TreeNode right = root.right;
            TreeNode left = root.left;

            if (left != null) {
                while (left.right != null)
                    left = left.right;

                left.right = right;
                root.right = root.left;
                root.left = null;
            }
        }
    }


    static class SolutionRecursive2 {

        public void flatten(TreeNode root) {
            if (root == null)
                return;
            flatten(root, new TreeNode[]{null});
        }

        public void flatten(TreeNode root, TreeNode[] previous) {
            if (root == null)
                return;

            flatten(root.right, previous);
            flatten(root.left, previous);

            root.right = previous[0];
            root.left = null;
            previous[0] = root;


        }
    }

    /**
     * The visit order of preorder traversal is “root, left subtree, right subtree”.
     * After the last node of the left subtree is visited, the right subtree node of the root node will be visited next.
     * <p>
     * Therefore, for the current node, if its left child node is not null,
     * we find the rightmost node of the left subtree as the predecessor node, and then assign the
     * right child node of the current node to the right child node of the predecessor node.
     * Then assign the left child node of the current node to the right child node of the current node,
     * and set the left child node of the current node to null. Then take the right child node of the current node as the
     * next node and continue processing until all nodes are processed.
     * <p>
     * The time complexity is
     * , where
     * is the number of nodes in the tree. The space complexity is
     * .
     */
    static class SolutionIterativeWithoutSpace {
        public void flatten(TreeNode root) {
            if (root == null)
                return;

            TreeNode curr = root;

            while (curr != null) {

                if (curr.left != null) {

                    TreeNode left = curr.left;
                    while (left.right != null)
                        left = left.right;

                    left.right = curr.right;
                    curr.right = curr.left;
                    curr.left = null;
                }
                curr = curr.right;
            }
        }

    }
}



