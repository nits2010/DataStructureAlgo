package Java.LeetCode.completeBinaryTree;

import Java.LeetCode.HelperDatastructure.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-13
 * Description:
 * <p>
 * Given a complete binary tree, you need find a node at which the next node need to be inserted, in the least time complexity.
 * Each node in binary tree is contains left, right and value only.
 * <p>
 * * 		     5
 * *          /    \
 * * 	    4	    8
 * * 	  /
 * * 	3
 * the next node is going to be insert in right side of 4.
 * <p>
 * {@link CountNodesInCompleteBinaryTree}
 * {@link IsCompleteBinaryTree}
 */
public class GetLastNodeInCompleteBinaryTree {
    public static void main(String[] args) {

        LastNodeInCompleteBinaryTree binaryTree = new LastNodeInCompleteBinaryTree();
        /**
         * *     1
         * *    / \
         * *   2   3
         * *  / \  /
         * * 4  5 6
         *
         * @return
         */
        System.out.println(binaryTree.getLastNodeInCompleteBinaryTree(Helper.getTree1()));

        /**
         * *     1
         * *    / \
         * *   2   3
         * *  / \  /\
         * * 4  5 6 7
         *
         * @return
         */
        System.out.println(binaryTree.getLastNodeInCompleteBinaryTree(Helper.getTree2()));


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
        System.out.println(binaryTree.getLastNodeInCompleteBinaryTree(Helper.getTree3()));


        /**
         * *           1
         * *          /  \
         * *         2    3
         * *        /    /
         * *       4    6
         *
         * @return
         */
        System.out.println(binaryTree.getLastNodeInCompleteBinaryTree(Helper.notCompleteBinaryTree()));
    }
}

class LastNodeInCompleteBinaryTree {

    /**
     * O(log(n)^2)
     *
     * @param root
     * @return
     */
    public TreeNode getLastNodeInCompleteBinaryTree(TreeNode root) {

        /**
         * if root is null, then add at root it self
         * O(1)
         */
        if (null == root)
            return root;


        /**
         * if root don't have left child, then add at left side
         * O(1)
         */
        if (root.left == null)
            return root;

        /**
         * if root don't have right child but has left child, then add at right side
         *
         * O(1)
         */
        if (root.right == null)
            return root;


        /**
         * check does tree rooted at this node has left and right height same.
         * If yes, then it will only possible when this tree is full binary tree.
         * Then we can simply add a node at the left side of leftmost node
         */

        if (leftRightHeightAreSame(root)) { //O(log(n))

            return getLeftMostNode(root); //O(log(n))

        } else if (leftRightHeightAreSame(root.left)) { //O(log(n))
            /**
             * if left and right are not same but its true for left sub-tree
             * i.e. left is full binary tree [keep in mind we talk about the complete binary as input]
             * we need to add at right sub-tree
             */
            return getLastNodeInCompleteBinaryTree(root.right);  //O(log(n))
        } else
            return getLastNodeInCompleteBinaryTree(root.left); //O(log(n))

    }

    /**
     * O(log(n))
     *
     * @param root
     * @return
     */
    private boolean leftRightHeightAreSame(TreeNode root) {
        if (root == null)
            return true;

        if (root.left == null && root.right == null)
            return true;

        return leftHeight(root) == rightHeight(root);
    }


    /**
     * O(log(n))
     *
     * @param root
     * @return
     */
    private int leftHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + leftHeight(root.left);
    }

    /**
     * O(log(n))
     *
     * @param root
     * @return
     */
    private int rightHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + rightHeight(root.right);
    }

    /**
     * O(log(n))
     *
     * @param root
     * @return
     */
    private TreeNode getLeftMostNode(TreeNode root) {

        while (root != null && root.left != null)
            root = root.left;

        return root;

    }
}