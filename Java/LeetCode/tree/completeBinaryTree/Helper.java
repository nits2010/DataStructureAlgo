package Java.LeetCode.tree.completeBinaryTree;

import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-13
 * Description:
 */
public class Helper {

    /**
     * *     1
     * *    / \
     * *   2   3
     * *  / \  /
     * * 4  5 6
     *
     * @return
     */
    public static TreeNode getTree1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        return root;
    }

    /**
     * *     1
     * *    / \
     * *   2   3
     * *  / \  /\
     * * 4  5 6 7
     *
     * @return
     */
    public static TreeNode getTree2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    /**
     * *           1
     * *          /  \
     * *         2    3
     * *         / \  /\
     * *        4  5 6 7
     * *      /  \
     * *     8    9
     *
     * @return
     */
    public static TreeNode getTree3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        return root;
    }

    /**
     * *           1
     * *          /  \
     * *         2    3
     * *        /    /
     * *       4    6
     *
     * @return
     */
    public static TreeNode notCompleteBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(6);

        return root;
    }

}
