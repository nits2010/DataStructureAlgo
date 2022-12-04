package DataStructureAlgo.Java.LeetCode.flatten.tree;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;
import  DataStructureAlgo.Java.LeetCode.tree.TreeBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description: https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/
 * <p>
 * http://leetcode.liangjiateng.cn/leetcode/convert-binary-search-tree-to-sorted-doubly-linked-list/description
 * https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 * <p>
 * 426.Convert Binary Search Tree to Sorted Doubly Linked List
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
 * <p>
 * Let's take the following BST as an example, it may help you understand the problem better:
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 * <p>
 * The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
 * <p>
 * The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 */
public class BinaryTreeToList {

    public static void main(String[] args) {
        /**
         * *     1
         * *    / \
         * *   2   5
         * *  / \   \
         * * 3   4   6
         */
        testSingly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(3, 2, 4, 1, 5, 6));
        testDoubly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(3, 2, 4, 1, 5, 6));
        testCircularSingly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(3, 2, 4, 1, 5, 6, 1));
        testCircularDoubly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(3, 2, 4, 1, 5, 6, 1));
        System.out.println(".............");
        /**
         * *     1
         * *    / \
         * *   2   5
         * *  /     \
         * * 3       6
         */
        testSingly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, null, null, 6}), Arrays.asList(3, 2, 1, 5, 6));
        testDoubly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, null, null, 6}), Arrays.asList(3, 2, 1, 5, 6));
        testCircularSingly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, null, null, 6}), Arrays.asList(3, 2, 1, 5, 6, 3));
        testCircularDoubly(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, null, null, 6}), Arrays.asList(3, 2, 1, 5, 6, 3));
        System.out.println(".............");
        /**
         * * 1
         * *  \
         * *   2
         * *    \
         * *     3
         * *      \
         * *       4
         * *        \
         * *         5
         * *          \
         * *           6
         */
        testSingly(TreeBuilder.arrayToTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        testDoubly(TreeBuilder.arrayToTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        testCircularSingly(TreeBuilder.arrayToTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6, 1));
        testCircularDoubly(TreeBuilder.arrayToTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6, 1));
        System.out.println(".............");
        /**
         * *             1
         * *            /
         * *           2
         * *          /
         * *         3
         * *        /
         * *       4
         * *      /
         * *     5

         */
        testSingly(TreeBuilder.arrayToTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(5, 4, 3, 2, 1));
        testDoubly(TreeBuilder.arrayToTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(5, 4, 3, 2, 1));
        testCircularSingly(TreeBuilder.arrayToTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(5, 4, 3, 2, 1, 5));
        testCircularDoubly(TreeBuilder.arrayToTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(5, 4, 3, 2, 1, 5));
        System.out.println(".............");
    }


    private static void testSingly(TreeNode root, List<Integer> expected) {
        System.out.println(" \n Input " + GenericPrinter.preOrder(root) + " expected :" + expected);
        final BinaryTreeToSinglyList singly = new BinaryTreeToSinglyList();
        final TreeNode listHead = singly.treeToSinglyList(root);
        System.out.println(" \n Singly " + GenericPrinter.print(listHead));

    }

    private static void testDoubly(TreeNode root, List<Integer> expected) {
//        System.out.println(" \n Input " + Printer.preOrder(root) + " expected :" + expected);

        final BinaryTreeToDoublyList doubly = new BinaryTreeToDoublyList();
        final TreeNode listHead = doubly.treeToDoublyList(root);

        System.out.println(" \n Doubly " + GenericPrinter.print(listHead));
    }

    private static void testCircularSingly(TreeNode root, List<Integer> expected) {
//        System.out.println(" \n Input " + Printer.preOrder(root) + " expected :" + expected);
        final BinaryTreeToCircularSinglyList singlyCircular = new BinaryTreeToCircularSinglyList();
        final TreeNode listHead = singlyCircular.treeToCircularSinglyList(root);
        System.out.println(" \n Singly Circular" + GenericPrinter.printCircular(listHead));
    }

    private static void testCircularDoubly(TreeNode root, List<Integer> expected) {
        //        System.out.println(" \n Input " + Printer.preOrder(root) + " expected :" + expected);

        final BinaryTreeToCircularDoublyList doublyCircular = new BinaryTreeToCircularDoublyList();
        final TreeNode listHead = doublyCircular.treeToCircularDoublyList(root);

        System.out.println(" \n Doubly Circular" + GenericPrinter.printCircular(listHead));
    }


}

