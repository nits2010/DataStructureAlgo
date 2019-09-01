package Java.LeetCode.flatten.tree;

import Java.HelpersToPrint.Printer;
import Java.LeetCode.HelperDatastructure.TreeNode;
import Java.LeetCode.tree.TreeBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-01
 * Description: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * <p>
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example, given the following tree:
 * <p>
 * *     1
 * *    / \
 * *   2   5
 * *  / \   \
 * * 3   4   6
 * The flattened tree should look like:
 * <p>
 * * 1
 * *  \
 * *   2
 * *    \
 * *     3
 * *      \
 * *       4
 * *        \
 * *         5
 * *          \
 * *           6
 */
public class FlattenBinaryTreeLinkedList {

    public static void main(String[] args) {
        /**
         * *     1
         * *    / \
         * *   2   5
         * *  / \   \
         * * 3   4   6
         */
        testPostOrder(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        testPostOrderShort(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println();
        /**
         * *     1
         * *    / \
         * *   2   5
         * *  /     \
         * * 3       6
         */
        testPostOrder(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, null, null, 6}), Arrays.asList(1, 2, 3, 5, 6));
        testPostOrderShort(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println();
        /**
         * * 1
         * *  \
         * *   2
         * *    \
         * *     3
         * *      \
         * *       4
         * *        \
         * *         5
         * *          \
         * *           6
         */
        testPostOrder(TreeBuilder.arrayToTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        testPostOrderShort(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println();
        /**
         * *             1
         * *            /
         * *           2
         * *          /
         * *         3
         * *        /
         * *       4
         * *      /
         * *     5

         */
        testPostOrder(TreeBuilder.arrayToTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        testPostOrderShort(TreeBuilder.arrayToTree(new Integer[]{1, 2, 5, 3, 4, null, 6}), Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println();
    }

    private static void testPostOrder(TreeNode root, List<Integer> expected) {
        System.out.println(" \n Input " + Printer.preOrder(root) + " expected :" + expected);

        FlattenBinaryTreeLinkedListPostOrder postOrder = new FlattenBinaryTreeLinkedListPostOrder();
        postOrder.flatten(root);
        System.out.println("\n PostOrder  :" + Printer.preOrder(root));
    }


    private static void testPostOrderShort(TreeNode root, List<Integer> expected) {
        System.out.println(" \n Input " + Printer.preOrder(root) + " expected :" + expected);

        FlattenBinaryTreeLinkedListPostOrderShort postOrder = new FlattenBinaryTreeLinkedListPostOrderShort();
        postOrder.flatten(root);
        System.out.println("\n PostOrder short:" + Printer.preOrder(root));
    }


}

class FlattenBinaryTreeLinkedListPostOrder {


    /**
     * If you observe how the tree gets flatten then it is clear that
     * 1. root left tree become right tree
     * 2. new right tree, right most node points to root right tree
     * <p>
     * <p>
     * Before we apply above 2 process, we need to first apply them on left and right sub-tree and then apply on root.
     * Hence its a post order traversal.
     * <p>
     * Algo:
     * 1. Process left sub-tree
     * 2. Process right sub-tree
     * 3. Process root
     * *  3.1: Make left node as right node of root
     * *  3.2: make right node as right node of new right sub-tree right most node
     * <p>
     * Complexity
     * O(n)/O(n)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
     * Memory Usage: 38.8 MB, less than 29.09% of Java online submissions for Flatten Binary Tree to Linked List.
     *
     * @param root
     */
    public void flatten(TreeNode root) {

        if (root == null)
            return;

        //Process left sub-tree
        flatten(root.left);

        //Process right sub-tree
        flatten(root.right);

        TreeNode left = root.left;

        if (left != null) {

            //3.2: make right node as right node of new right sub-tree right most node
            TreeNode rightMost = left;
            while (rightMost.right != null)
                rightMost = rightMost.right;

            TreeNode right = root.right;
            rightMost.right = right;

            //3.1: Make left node as right node of root
            root.right = left;
            root.left = null;
        }

        return;

    }
}


class FlattenBinaryTreeLinkedListPostOrderShort {


    /**
     * If you observe how the tree gets flatten then it is clear that
     * 1. root left tree become right tree
     * 2. new right tree, right most node points to root right tree
     * <p>
     * <p>
     * Before we apply above 2 process, we need to first apply them on left and right sub-tree and then apply on root.
     * Hence its a post order traversal.
     * <p>
     * Algo:
     * 1. Process left sub-tree
     * 2. Process right sub-tree
     * 3. Process root
     * *  3.1: Make left node as right node of root
     * *  3.2: make right node as right node of new right sub-tree right most node
     * <p>
     * <p>
     * In {@link FlattenBinaryTreeLinkedListPostOrder}, we need right most node, that is why we have to put a while loop to find. What if we keep the track of it.
     * If we can then we can simply attach right node to tracked right's node.
     *
     * <p>
     * Complexity
     * O(n)/O(n)
     * Runtime: 1 ms, faster than 62.87% of Java online submissions for Flatten Binary Tree to Linked List.
     * Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
     *
     * @param root
     */
    public void flatten(TreeNode root) {

        if (root == null)
            return;

        flattenTree(root, new TreeNode[]{null});

        return;

    }


    public void flattenTree(TreeNode root, TreeNode[] previous) {
        if (root == null)
            return;

        //Process right sub-tree
        flattenTree(root.right, previous);

        //Process left sub-tree
        flattenTree(root.left, previous);

        root.right = previous[0];
        root.left = null;

        previous[0] = root;


    }

}
