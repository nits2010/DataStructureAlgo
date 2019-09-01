package Java.LeetCode.flatten.tree;

import Java.LeetCode.HelperDatastructure.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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
 * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 * <p>
 * The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
 * <p>
 * Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
 * <p>
 * The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 * Driver {@link Java.LeetCode.flatten.tree.BinaryTreeToList}
 */
public class BinaryTreeToDoublyList {


    /**
     * {@link BinaryTreeToSinglyList} Just update left to previous node
     */
    public TreeNode treeToDoublyList(TreeNode root) {

        if (root == null)
            return root;

        TreeNode head[] = {null};
        treeToDoublyList(root, head);
        return head[0];
    }

    /**
     * We process tree in right to left manner.
     * Initially the rightmost node would be head of our list as there is no node left.
     * But as we move on left side, we keep updating our head.
     * <p>
     * The all logic to convert tree to list is based on adding node on head of the list every time and keep updating head
     *
     * @param root
     * @param head
     */
    private void treeToDoublyList(TreeNode root, TreeNode[] head) {

        if (root == null)
            return;

        //Go to right most node,
        treeToDoublyList(root.right, head);

        //is this is right most node, then make it our initial head
        if (head[0] == null) {
            head[0] = root;
        } else {
            //if not, then add new node at the head of the old linked list
            root.right = head[0]; //right as next pointer
            head[0].left = root; //convert it to doubly list. left as previous pointer
        }

        //update head
        head[0] = root;

        treeToDoublyList(root.left, head);
    }
}
