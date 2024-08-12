package DataStructureAlgo.Java.LeetCode.listToBST;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.helpers.templates.ListNode;
import  DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.helpers.ListBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description:
 */
public class ListToBinarySearchTree {

    private static int height(int n) {
        return (int) Math.ceil(Math.log(n) / Math.log(2));
    }

    public static void main(String[] args) {

        System.out.println("........");
        testSinglyListToBST(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5}), Arrays.asList(1, 2, 3, 4, 5), height(5));
        testDoublyListToBST(ListBuilder.arrayToDoublyList(new Integer[]{1, 2, 3, 4, 5}), Arrays.asList(1, 2, 3, 4, 5), height(5));

        System.out.println("........");
        testSinglyListToBST(ListBuilder.arrayToSinglyList(new Integer[]{-10, -3, 0, 5, 9}), Arrays.asList(-10, -3, 0, 5, 9), height(5));
        testDoublyListToBST(ListBuilder.arrayToDoublyList(new Integer[]{-10, -3, 0, 5, 9}), Arrays.asList(-10, -3, 0, 5, 9), height(5));

        System.out.println("........");
        testSinglyListToBST(ListBuilder.arrayToSinglyList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), height(10));
        testDoublyListToBST(ListBuilder.arrayToDoublyList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), height(10));
    }


    private static void testSinglyListToBST(ListNode head, List<Integer> inOrderTraversal, int expectedHeight) {
        System.out.println("\n Input :" + GenericPrinter.print(head) + " Expected In-order traversal :" + inOrderTraversal + " Expected Height :" + expectedHeight);

        ConvertSortedSinglyListBinarySearchTree listBinarySearchTree = new ConvertSortedSinglyListBinarySearchTree();
        final TreeNode treeNode = listBinarySearchTree.sortedListToBST(head);

        int height = height(treeNode);

        System.out.println("\n Singly Inorder Traversal :" + GenericPrinter.inOrder(treeNode) + " Height :" + height);
    }

    private static void testDoublyListToBST(TreeNode head, List<Integer> inOrderTraversal, int expectedHeight) {

//        System.out.println("\n Input :" + Printer.print(head) + " Expected In-order traversal :" + inOrderTraversal + " Expected Height :" + expectedHeight);

        ConvertSortedDoublyListBinarySearchTree listBinarySearchTree = new ConvertSortedDoublyListBinarySearchTree();
        final TreeNode treeNode = listBinarySearchTree.sortedListToBST(head);

        int height = height(treeNode);

        System.out.println("\n Doubly Inorder Traversal :" + GenericPrinter.inOrder(treeNode) + " Height :" + height);
    }


    private static int height(TreeNode root) {
        if (null == root)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;

    }
}

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * <p>
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example:
 * <p>
 * Given the sorted linked list: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <p>
 * *       0
 * *      / \
 * *    -3   9
 * *    /   /
 * *  -10  5
 * <p>
 * <p>
 * * Definition for singly-linked list.
 * * public class ListNode {
 * *     int val;
 * *     ListNode next;
 * *     ListNode(int x) { val = x; }
 * * }
 * <p>
 * * Definition for a binary tree node.
 * * public class TreeNode {
 * *     int val;
 * *     TreeNode left;
 * *     TreeNode right;
 * *     TreeNode(int x) { val = x; }
 * * }
 * <p>
 */
class ConvertSortedSinglyListBinarySearchTree {


    /**
     * Approach:
     * In order to build a 'balanced binary search tree', we need to make sure that list middle node is our root. All elements on left side of middle node
     * should be part of left balanced binary search tree, so for right.
     * <p>
     * to find middle node for every sub-tree require O(log(n)) time. Hence overall time complexity to build Balanced binary search tree using above approach
     * is O(n*log(n)) this is better than in worst case O(n^2).
     * Complexity: O(n*log(n)) / O(log(n)
     * <p>
     * Better Approach: Build tree from bottom up
     * If we build tree from bottom up then we can save computation that require to find middle node every time for left and right sub-tree.
     * To Build from bottom up, our head of linked list should be left most node and tail of the linked list should be right most node.
     * <p>
     * Since our list is sorted, we can do this using binary search. But notice here, we can't apply binary search on list directly [otherwise it become same as above approach]
     * We need to apply binary search 'virtually'.
     * <p>
     * Algo:
     * 1. find size of list.
     * 2. Assume left = 0 and right = size
     * 3. perform till left < right [ just like binary search ]
     * 4. Compute mid=(left + right)>>1;
     * 5. Go to left (left, mid); and build left-sub-tree
     * 6. When you hit, left>=right, then it means , now the time we need to create our left sub-tree node. Create it
     * 7. Apply same for right, go right (mid+1, right);
     * <p>
     * <p>
     * Complexity: O(n)/O(log(n))
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
     * Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {

        int size = size(head);
        /**
         * Empty list to empty tree
         */
        if (size == 0)
            return null;


        return sortedListToBST(new ListNode[]{head}, 0, size);


    }

    private TreeNode sortedListToBST(ListNode[] head, int left, int right) {
        if (left >= right)
            return null;

        //Binary Search
        int mid = (left + right) >> 1;

        TreeNode leftNode = sortedListToBST(head, left, mid);

        TreeNode root = new TreeNode(head[0].val);
        head[0] = head[0].next;

        root.left = leftNode;

        root.right = sortedListToBST(head, mid + 1, right);

        return root;
    }

    private int size(ListNode head) {

        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

}

/**
 * This is in-place conversion
 */
class ConvertSortedDoublyListBinarySearchTree {


    /**
     * Approach:
     * In order to build a 'balanced binary search tree', we need to make sure that list middle node is our root. All elements on left side of middle node
     * should be part of left balanced binary search tree, so for right.
     * <p>
     * to find middle node for every sub-tree require O(log(n)) time. Hence overall time complexity to build Balanced binary search tree using above approach
     * is O(n*log(n)) this is better than in worst case O(n^2).
     * Complexity: O(n*log(n)) / O(log(n)
     * <p>
     * Better Approach: Build tree from bottom up
     * If we build tree from bottom up then we can save computation that require to find middle node every time for left and right sub-tree.
     * To Build from bottom up, our head of linked list should be left most node and tail of the linked list should be right most node.
     * <p>
     * Since our list is sorted, we can do this using binary search. But notice here, we can't apply binary search on list directly [otherwise it become same as above approach]
     * We need to apply binary search 'virtually'.
     * <p>
     * Algo:
     * 1. find size of list.
     * 2. Assume left = 0 and right = size
     * 3. perform till left < right [ just like binary search ]
     * 4. Compute mid=(left + right)>>1;
     * 5. Go to left (left, mid); and build left-sub-tree
     * 6. When you hit, left>=right, then it means , now the time we need to create our left sub-tree node. Create it
     * 7. Apply same for right, go right (mid+1, right);
     * <p>
     * <p>
     * Complexity: O(n)/O(log(n))
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
     * Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(TreeNode head) {

        int size = size(head);
        /**
         * Empty list to empty tree
         */
        if (size == 0)
            return null;


        return sortedListToBST(new TreeNode[]{head}, 0, size);


    }

    private TreeNode sortedListToBST(TreeNode[] head, int left, int right) {
        if (left >= right)
            return null;

        //Binary Search
        int mid = (left + right) >> 1;

        TreeNode leftNode = sortedListToBST(head, left, mid);

        TreeNode root = head[0];
        head[0] = head[0].right;

        root.left = leftNode;

        root.right = sortedListToBST(head, mid + 1, right);

        return root;
    }

    private int size(TreeNode head) {

        int size = 0;
        while (head != null) {
            size++;
            head = head.right;
        }

        return size;
    }

}


