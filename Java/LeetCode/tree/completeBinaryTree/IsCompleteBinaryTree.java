package Java.LeetCode.tree.completeBinaryTree;

import Java.LeetCode.templates.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-13
 * Description:
 */
public class IsCompleteBinaryTree {

    public static void main(String[] args) {
        System.out.println(isCompleteTree(Helper.getTree1()));
        System.out.println(isCompleteTree(Helper.getTree2()));
        System.out.println(isCompleteTree(Helper.notCompleteBinaryTree()));
    }

    public static boolean isCompleteTree(TreeNode root) {

        return isCompleteUsingByNumbering(root);
    }

    /**
     * * Do level order traversal, and see when hit a null node,
     * * if this is complete binary tree then you won't get any node after this
     * * otherwise its not.
     * * Or if you don't have left node but have right node for a root then its not.
     * <p>
     * Complexity: O(n) / O(n)
     * Runtime: 1 ms, faster than 92.99% of Java online submissions for Check Completeness of a Binary Tree.
     * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Check Completeness of a Binary Tree.
     *
     * @param root
     * @return
     */
    private static boolean isCompleteUsingBFS(TreeNode root) {

        if (null == root)
            return true;


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean nullEncounter = false;

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            if (node.left != null) {

                /**
                 * If we have encounter null already and there is node come up
                 */
                if (nullEncounter) {
                    return false;
                }
                queue.offer(node.left);
            } else {
                /**
                 * This node has no left child, then there must not be a right child
                 */
                nullEncounter = true;
            }

            if (node.right != null) {

                /**
                 * If we have encounter null already and there is node come up
                 */
                if (nullEncounter) {
                    return false;
                }
                queue.offer(node.right);
            } else {

                nullEncounter = true;
            }
        }

        return true;
    }


    /**
     * In the array representation of a binary tree, if the parent node is assigned an index of ‘i’
     * and left child gets assigned an index of ‘2*i + 1’ while the right child is assigned an index of ‘2*i + 2’.
     * If we represent the above binary tree as an array with the respective indices assigned to the different nodes of the tree above
     * from top to down and left to right.
     * <p>
     * Hence we proceed in the following manner in order to check if the binary tree is complete binary tree.
     * <p>
     * Calculate the number of nodes (count) in the binary tree.
     * Start recursion of the binary tree from the root node of the binary tree with index (i) being set as 0 and the number
     * of nodes in the binary (count).
     * If the current node under examination is NULL, then the tree is a complete binary tree. Return true.
     * If index (i) of the current node is greater than or equal to the number of nodes in the binary tree (count) i.e. (i>= count),
     * then the tree is not a complete binary. Return false.
     * Recursively check the left and right sub-trees of the binary tree for same condition. For the left sub-tree
     * use the index as (2*i + 1) while for the right sub-tree use the index as (2*i + 2).
     * <p>
     * O(n)/O(n)
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Check Completeness of a Binary Tree.
     * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Check Completeness of a Binary Tree.
     *
     * @param root
     * @return
     */
    private static boolean isCompleteUsingByNumbering(TreeNode root) {

        if (root == null)
            return true;

        int nodeCount = countNodes(root);

        return isCompleteUsingByNumbering(root, 0, nodeCount);

    }

    private static boolean isCompleteUsingByNumbering(TreeNode root, int i, int nodeCount) {
        if (root == null)
            return true;

        if (i >= nodeCount)
            return false;

        return isCompleteUsingByNumbering(root.left, 2 * i + 1, nodeCount)
                && isCompleteUsingByNumbering(root.right, 2 * i + 2, nodeCount);
    }


    /**
     * Count how many nodes are there in tree
     * O(n)/O(n)
     *
     * @param root
     * @return
     */
    private static int countNodes(TreeNode root) {
        if (root == null)
            return (0);
        return (1 + countNodes(root.left) + countNodes(root.right));
    }

}
