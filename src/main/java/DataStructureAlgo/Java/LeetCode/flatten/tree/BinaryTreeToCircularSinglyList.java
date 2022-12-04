package DataStructureAlgo.Java.LeetCode.flatten.tree;

import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description:
 * Driver {@link DataStructureAlgo.Java.LeetCode.flatten.tree.BinaryTreeToList}
 */
public class BinaryTreeToCircularSinglyList {

    /**
     * {@link BinaryTreeToSinglyList} just attach right of tail to head
     */
    public TreeNode treeToCircularSinglyList(TreeNode root) {

        if (root == null)
            return root;

        TreeNode head[] = {null};
        TreeNode tail[] = {null};
        treeToCircularSinglyList(root, head, tail);
        return head[0];
    }

    /**
     * @param root
     * @param head
     */
    private void treeToCircularSinglyList(TreeNode root, TreeNode[] head, TreeNode tail[]) {

        if (root == null)
            return;

        //Go to right most node,
        treeToCircularSinglyList(root.right, head, tail);

        //is this is right most node, then make it our initial head
        if (head[0] == null) {
            head[0] = root;
            tail[0] = root;
        } else {
            //if not, then add new node at the head of the old linked list
            root.right = head[0]; //right as next pointer

            head[0].left = null; //convert it to singly list

        }

        //update head
        head[0] = root;

        tail[0].right = head[0]; //make it circular

        treeToCircularSinglyList(root.left, head, tail);
    }
}
