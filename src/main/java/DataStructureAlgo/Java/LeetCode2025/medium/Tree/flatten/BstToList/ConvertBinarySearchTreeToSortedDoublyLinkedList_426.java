package DataStructureAlgo.Java.LeetCode2025.medium.Tree.flatten.BstToList;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Description: https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/
 * https://leetcode.ca/all/426.html
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
 * <p>
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.flatten.tree.BinaryTreeToDoublyList}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @BinaryTree
 * @BinaryIndexedTree
 * @Tree <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Databricks
 * @Facebook
 * @Google
 * @Lyft
 * @Microsoft
 * @Oracle
 * @Editorial https://leetcode.ca/all/426.html
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList_426 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{4, 2, 6, 1, 3, 5, 7}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
        test &= test(new Integer[]{2, 1, 3}, new Integer[]{1, 2, 3});
        test &= test(new Integer[]{}, new Integer[]{});
        test &= test(new Integer[]{10}, new Integer[]{10});
        test &= test(new Integer[]{2, 1}, new Integer[]{1, 2});

        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        System.out.println("=========================================");
        System.out.println("Input :" + Arrays.toString(input) + "\nExpected :" + Arrays.toString(expected));

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        BstToDoublyLinkedList bstToDoublyLinkedList = new BstToDoublyLinkedList();


        TreeNode bstToList = bstToDoublyLinkedList.bstToDoublyLinkedList(TreeBuilder.buildTreeFromLevelOrder(input));

        List<Integer> forwardList = forwardList(bstToList);
        List<Integer> backwardList = backwardList(bstToList);
        List<Integer> expectedList = Arrays.asList(expected);

        boolean testResultDLL = CommonMethods.equalsValues(forwardList, expectedList);
        System.out.println("Output : forwardList = " + forwardList + " backwardList = " + backwardList + " Test Result " + (testResultDLL ? " Passed " : " Failed "));


        BstToCircularDoublyLinkedList bstToCircularDoublyLinkedList = new BstToCircularDoublyLinkedList();
        TreeNode bstToCirList = bstToCircularDoublyLinkedList.bstToCircularDoublyLinkedList(TreeBuilder.buildTreeFromLevelOrder(input));
        forwardList = forwardList(bstToCirList);
        backwardList = backwardList(bstToCirList);
        boolean testResultCDLL = CommonMethods.equalsValues(forwardList, expectedList);
        System.out.println("Output Circular : forwardList = " + forwardList + " backwardList = " + backwardList + " Test Result " + (testResultCDLL ? " Passed " : " Failed "));


        return testResultDLL && testResultCDLL;

    }

    private static List<Integer> forwardList(TreeNode root) {
        TreeNode current = root;
        List<Integer> levelOrder = new ArrayList<>();
        while (current != null) {
            levelOrder.add(current.val);
            current = current.right;

            if (current == root)
                break;
        }
        return levelOrder;
    }

    private static List<Integer> backwardList(TreeNode root) {
        TreeNode current = root;
        List<Integer> levelOrder = new ArrayList<>();

        while (current != null) {
            current = current.right;

            if (current == root)
                break;
        }
        if(current!=null)
            current = current.left;

        TreeNode tail = current;
        while (current != null) {
            levelOrder.add(current.val);
            current = current.left;

            if (current == tail)
                break;
        }
        return levelOrder;
    }

    static class BstToDoublyLinkedList {


        public TreeNode bstToDoublyLinkedList(TreeNode root) {

            if (root == null)
                return null;

            TreeNode[] head = {null};
            TreeNode[] tail = {null};

            bstToDoublyLinkedList(root, head, tail);
            return head[0];
        }

        private void bstToDoublyLinkedList(TreeNode root, TreeNode[] head, TreeNode[] tail) {
            if (root == null)
                return;

            bstToDoublyLinkedList(root.left, head, tail);

            //convert it

            //if its first node, then its head node
            if (head[0] == null) {
                head[0] = root;
                tail[0] = root;
            } else {
                //connect it with the previous node aka tail
                tail[0].right = root;
                root.left = tail[0];

                tail[0] = root;
            }

            bstToDoublyLinkedList(root.right, head, tail);
        }
    }

    static class BstToCircularDoublyLinkedList {


        public TreeNode bstToCircularDoublyLinkedList(TreeNode root) {

            if (root == null)
                return null;

            TreeNode[] head = {null};
            TreeNode[] tail = {null};

            bstToDoublyLinkedList(root, head, tail);
            head[0].left = tail[0];
            tail[0].right = head[0];
            return head[0];
        }

        private void bstToDoublyLinkedList(TreeNode root, TreeNode[] head, TreeNode[] tail) {
            if (root == null)
                return;

            bstToDoublyLinkedList(root.left, head, tail);

            //convert it

            //if its first node, then its head node
            if (head[0] == null) {
                head[0] = root;
                tail[0] = root;
            } else {
                //connect it with the previous node aka tail
                tail[0].right = root;
                root.left = tail[0];

                tail[0] = root;
            }

            bstToDoublyLinkedList(root.right, head, tail);
        }
    }
}
