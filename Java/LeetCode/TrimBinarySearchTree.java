package Java.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-20
 * Description:  https://leetcode.com/problems/trim-a-binary-search-tree/
 * https://medium.com/algorithms-and-leetcode/solving-tree-problems-on-leetcode-d0b7a9b4a7a4
 * 2.3 Trim the tree
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 * Example 1:
 *
 * Input:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 *
 * Output:
 *     1
 *       \
 *        2
 * Example 2:
 *
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 */


//  Definition for a binary tree node.
class TreeBSTNode {
    int v;
    TreeBSTNode left;
    TreeBSTNode right;

    TreeBSTNode(int x) {
        v = x;
    }
}


public class TrimBinarySearchTree {
    public static void main(String args[]) {

        test(getBST1(), 3, 6);
        test(getBST2(), 18, 31);
    }

    private static List<Integer> inorder(TreeBSTNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        return inorder;
    }

    private static void inorder(TreeBSTNode root, List<Integer> inorder) {
        if (null == root)
            return;

        inorder(root.left, inorder);
        inorder.add(root.v);
        inorder(root.right, inorder);

    }

    private static void test(TreeBSTNode root, int low, int high) {
        if (low > high)
            return;

        System.out.println("Inorder of bst : " + inorder(root) + " trim range [ " + low + " , " + high + "]");

        SolutionTrimBinarySearchTree trim = new SolutionTrimBinarySearchTree();
        TreeBSTNode trimmed = trim.trim(root, low, high);
        System.out.println("Trim tree " + inorder(trimmed));


    }

    private static TreeBSTNode getBST1() {

        TreeBSTNode root = new TreeBSTNode(4);
        root.left = new TreeBSTNode(2);
        root.left.left = new TreeBSTNode(1);
        root.left.right = new TreeBSTNode(3);
        root.right = new TreeBSTNode(6);
        root.right.left = new TreeBSTNode(5);
        root.right.right = new TreeBSTNode(7);

        return root;
    }


    private static TreeBSTNode getBST2() {

        TreeBSTNode root = new TreeBSTNode(28);

        root.left = new TreeBSTNode(16);

        root.left.left = new TreeBSTNode(14);
        root.left.left.left = new TreeBSTNode(12);
        root.left.left.right = new TreeBSTNode(15);

        root.left.right = new TreeBSTNode(22);
        root.left.right.left = new TreeBSTNode(18);
        root.left.right.right = new TreeBSTNode(25);


        root.right = new TreeBSTNode(32);
        root.right.left = new TreeBSTNode(31);
        root.right.right = new TreeBSTNode(35);

        return root;
    }
}


class SolutionTrimBinarySearchTree {

    /**
     * WE can achieve this using pre-order traversal
     * 1. if node is lesser then low; all left nodes will be less. Discard left and recur right
     * 2. if node is greater then high: all right node will be high; Discard right and recur left
     * 3. if this is in range, try its left and right sub-tree
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeBSTNode trim(TreeBSTNode root, int low, int high) {

        if (null == root)
            return null;

        //if this node is out of  range
        if (root.v < low) {
            //discard all left sub-tree
            root.left = null;
            return trim(root.right, low, high);
        }

        if (root.v > high) {
            //discard all right sub-tree
            return trim(root.left, low, high);
        }

        //if this is in range; take it ; manage its left and right sub-tree ranges
        root.left = trim(root.left, low, high);
        root.right = trim(root.right, low, high);

        //this is in range, take it
        return root;
    }

}