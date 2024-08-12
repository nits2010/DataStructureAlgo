package DataStructureAlgo.Java.LeetCode.flatten.tree;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description:
 * Driver {@link DataStructureAlgo.Java.LeetCode.flatten.tree.BinaryTreeToList}
 */

import  DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * One of the variation to convert BT to Singly list is {@link FlattenBinaryTreeLinkedList},
 * Another variation, which Works for both BST and Binary tree To LL
 */
public class BinaryTreeToSinglyList {

    public TreeNode treeToSinglyList(TreeNode root) {

        if (root == null)
            return root;

        TreeNode head[] = {null};
        treeToSinglyList(root, head);
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
    private void treeToSinglyList(TreeNode root, TreeNode[] head) {

        if (root == null)
            return;

        //Go to right most node,
        treeToSinglyList(root.right, head);

        //is this is right most node, then make it our initial head
        if (head[0] == null) {
            head[0] = root;
        } else {
            //if not, then add new node at the head of the old linked list
            root.right = head[0]; //right as next pointer
            head[0].left = null; //convert it to singly list
        }

        //update head
        head[0] = root;

        treeToSinglyList(root.left, head);
    }
}