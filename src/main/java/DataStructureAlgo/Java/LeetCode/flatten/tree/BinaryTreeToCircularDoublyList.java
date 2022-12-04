package DataStructureAlgo.Java.LeetCode.flatten.tree;

import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description:
 * Driver {@link DataStructureAlgo.Java.LeetCode.flatten.tree.BinaryTreeToList}
 */
public class BinaryTreeToCircularDoublyList {


    /**
     * {@link BinaryTreeToDoublyList} just attach right of tail to head
     */
    public TreeNode treeToCircularDoublyList(TreeNode root) {

        if (root == null)
            return root;

        TreeNode head[] = {null};
        TreeNode tail[] = {null};
        treeToCircularDoublyList(root, head, tail);
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
    private void treeToCircularDoublyList(TreeNode root, TreeNode[] head, TreeNode tail[]) {

        if (root == null)
            return;

        //Go to right most node,
        treeToCircularDoublyList(root.right, head, tail);

        //is this is right most node, then make it our initial head
        if (head[0] == null) {
            head[0] = root;
            tail[0] = root;

        } else {
            //if not, then add new node at the head of the old linked list
            root.right = head[0]; //right as next pointer
            head[0].left = root; //convert it to doubly list. left as previous pointer
        }

        //update head
        head[0] = root;

        tail[0].right = head[0]; //make it circular

        treeToCircularDoublyList(root.left, head, tail);
    }


}
