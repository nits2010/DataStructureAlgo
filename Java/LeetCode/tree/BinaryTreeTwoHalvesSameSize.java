package Java.LeetCode.tree;

import Java.HelpersToPrint.GenericPrinter;
import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-11
 * Description: https://www.geeksforgeeks.org/check-if-removing-an-edge-can-divide-a-binary-tree-in-two-halves/
 * Check if removing an edge can divide a Binary Tree in two halves
 * <p>
 * Given a Binary Tree, find if there exist edge whose removal creates two trees of equal size.
 * <p>
 * Examples:
 * <p>
 * Input : root of following tree
 * *            5
 * *          /   \
 * *        1      6
 * *       /      /  \
 * *      3      7    4
 * Output : true
 * Removing edge 5-6 creates two trees of equal size
 * <p>
 * <p>
 * Input : root of following tree
 * *            5
 * *          /   \
 * *        1      6
 * *             /  \
 * *            7    4
 * *          /  \    \
 * *         3    2    8
 * Output : false
 * There is no edge whose removal creates two trees
 * of equal size.
 * <p>
 * {@link BinaryTreeEqualPartition}
 */
public class BinaryTreeTwoHalvesSameSize {

    public static void main(String[] args) {
        test(TreeBuilder.arrayToTree(new Integer[]{5, 1, 6, 3, null, 7, 4}), new int[]{5, 6});
        test(TreeBuilder.arrayToTree(new Integer[]{5, 1, 6, null, null, 7, 4, 3, 2, null, 8}), null);
        test(TreeBuilder.arrayToTree(new Integer[]{5, 1, 6, null, null, 7, 4, 3, 2, null}), null);
    }

    public static int size(TreeNode root) {
        if (null == root)
            return 0;
        return size(root.left) + size(root.right) + 1;
    }

    private static void test(TreeNode root, int nodeAtBreak[]) {
        System.out.println("\n Input tree :" + GenericPrinter.preOrder(root) + " expected Size break :" + GenericPrinter.toString(nodeAtBreak));

        final TreeNode[] treeNodes = binaryTreeTwoHalvesSameSize(root);

        if (treeNodes[0] == null)
            System.out.println(" No break " + -1);
        else {
            System.out.println("Obtained [" + treeNodes[0] + " ," + treeNodes[1] + "]");
        }
    }

    /**
     * We can solve above problem, by finding a node whose size of left sub-tree is equal to size of right sub-tree.
     * We can include the current node on either side.
     */

    public static TreeNode[] binaryTreeTwoHalvesSameSize(TreeNode root) {
        if (null == root)
            return new TreeNode[]{null, null};

        int size = size(root);

        if (size % 2 != 0)
            return new TreeNode[]{null, null};

        TreeNode[] trees = {null, null};
        binaryTreeTwoHalvesSameSize(root, trees, size);
        return trees;


    }

    private static int binaryTreeTwoHalvesSameSize(TreeNode root, TreeNode[] trees, int size) {
        if (null == root)
            return 0;

        int lSize = binaryTreeTwoHalvesSameSize(root.left, trees, size);
        int rSize = binaryTreeTwoHalvesSameSize(root.right, trees, size);


        //root lie on right side
        if (lSize > rSize && (rSize + 1 == size / 2)) {
            trees[0] = root.left;
            trees[1] = root;
        } else if (rSize > lSize && (lSize + 1 == size / 2)) {
            trees[0] = root;
            trees[1] = root.right;
        }
        return lSize + rSize + 1;
    }
}
