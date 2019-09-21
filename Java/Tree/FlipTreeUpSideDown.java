package Java.Tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-26
 * Description:
 */
public class FlipTreeUpSideDown {


    /***
     * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node)
     * or empty.
     * https://www.geeksforgeeks.org/flip-binary-tree/
     * https://medium.com/@jimdaosui/binary-tree-upside-down-77af203c79af
     * <p>
     *
     * @param root
     * @return
     */
    public static TreeNode flipTreeUpSideDown(TreeNode root) {

        return flipTreeUpSideDownIterative(root);
    }

    public static TreeNode flipTreeUpSideDownRecursive(TreeNode root) {

        if (root == null | root.getLeft() == null)
            return root;

        TreeNode flipped = flipTreeUpSideDownRecursive(root.getLeft());

        root.getLeft().setLeft(root.getRight());
        root.getLeft().setRight(root);
        root.setLeft(null);
        root.setRight(null);

        return flipped;


    }

    /**
     * In the flip operation,
     * 1. left most node becomes the root of flipped tree and its parent become its right child
     * 2. and the right sibling become its left child
     * recursively.
     *
     * @param root
     * @return
     */
    public static TreeNode flipTreeUpSideDownIterative(TreeNode root) {
        if (root == null || root.getLeft() == null)
            return root;

        TreeNode parent = null;
        TreeNode current = root;
        TreeNode right = null;
        TreeNode left;

        while (current != null) {

            //left most node becomes the root of flipped tree
            left = current.getLeft();


            //the right sibling become its left child
            current.setLeft(right);

            right = current.getRight();

            // and its parent become its right child
            current.setRight(parent);


            parent = current;
            current = left;


        }

        return parent;
    }

}

