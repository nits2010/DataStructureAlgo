package Java.LeetCode.tree;

import Java.LeetCode.templates.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-20
 * Description:  https://leetcode.com/problems/trim-a-binary-search-tree/
 * https://medium.com/algorithms-and-leetcode/solving-tree-problems-on-leetcode-d0b7a9b4a7a4
 * 2.3 Trim the tree
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L).
 * You might need to numberOfWays the root of the tree, so the result should return the new root of the trimmed binary search tree.
 * Example 1:
 * <p>
 * Input:
 * *     1
 * *    / \
 * *   0   2
 * *
 * *   L = 1
 * *   R = 2
 * <p>
 * Output:
 * *     1
 * *       \
 * *        2
 * Example 2:
 * <p>
 * Input:
 * *     3
 * *    / \
 * *   0   4
 * *    \
 * *     2
 * *    /
 * *   1
 * <p>
 * *   L = 1
 * *   R = 3
 * <p>
 * Output:
 * *       3
 * *      /
 * *    2
 * *   /
 * *  1
 */


//  Definition for a binary tree node.


public class TrimBinarySearchTree {
    public static void main(String []args) {

        test(getBST1(), 3, 6);
        test(getBST2(), 18, 31);
    }

    private static List<Integer> inorder(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        return inorder;
    }

    private static void inorder(TreeNode root, List<Integer> inorder) {
        if (null == root)
            return;

        inorder(root.left, inorder);
        inorder.add(root.val);
        inorder(root.right, inorder);

    }

    private static void test(TreeNode root, int low, int high) {
        if (low > high)
            return;

        System.out.println("Inorder of bst : " + inorder(root) + " trim range [ " + low + " , " + high + "]");

        SolutionTrimBinarySearchTree trim = new SolutionTrimBinarySearchTree();
        TreeNode trimmed = trim.trim(root, low, high);
        System.out.println("Trim tree " + inorder(trimmed));


    }

    private static TreeNode getBST1() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        return root;
    }


    private static TreeNode getBST2() {

        TreeNode root = new TreeNode(28);

        root.left = new TreeNode(16);

        root.left.left = new TreeNode(14);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(15);

        root.left.right = new TreeNode(22);
        root.left.right.left = new TreeNode(18);
        root.left.right.right = new TreeNode(25);


        root.right = new TreeNode(32);
        root.right.left = new TreeNode(31);
        root.right.right = new TreeNode(35);

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
    public TreeNode trim(TreeNode root, int low, int high) {

        if (null == root)
            return null;

        //if this node is out of  range
        if (root.val < low) {
            //discard all left sub-tree
            root.left = null;
            return trim(root.right, low, high);
        }

        if (root.val > high) {
            root.right = null;
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