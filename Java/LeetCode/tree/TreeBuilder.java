package Java.LeetCode.tree;

import Java.LeetCode.templates.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-29
 * Description:
 */
public class TreeBuilder {


    /**
     * @param nodeValues : Level order traversal of a binary tree in
     * @return
     */
    public static TreeNode arrayToTree(final Integer[] nodeValues) {

        if (nodeValues == null || nodeValues.length == 0 || nodeValues[0] == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nodeValues[0]);
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty() && i < nodeValues.length) {

            TreeNode temp = queue.peek();


            if (temp.left == null) {
                if (nodeValues[i] != null) {
                    temp.left = new TreeNode(nodeValues[i]);
                    queue.offer(temp.left);
                }
                i++;
            }

            if (i == nodeValues.length)
                return root;

            if (temp.right == null) {
                if (nodeValues[i] != null) {
                    temp.right = new TreeNode(nodeValues[i]);
                    queue.offer(temp.right);
                }
                i++;
            }

            queue.poll();

        }
        return root;


    }

}
