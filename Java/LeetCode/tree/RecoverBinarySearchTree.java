package Java.LeetCode.tree;

import Java.HelpersToPrint.Printer;
import Java.LeetCode.HelperDatastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-29
 * Description: https://leetcode.com/problems/recover-binary-search-tree/
 * <p>
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,null,null,2]
 * <p>
 * *    1
 * *   /
 * *  3
 * *   \
 * *    2
 * <p>
 * Output: [3,1,null,null,2]
 * <p>
 * *    3
 * *   /
 * *  1
 * *   \
 * *    2
 * Example 2:
 * <p>
 * Input: [3,1,4,null,null,2]
 * <p>
 * *   3
 * *  / \
 * * 1   4
 * *    /
 * *   2
 * <p>
 * Output: [2,1,4,null,null,3]
 * <p>
 * *   2
 * *  / \
 * * 1   4
 * *    /
 * *   3
 * Follow up:
 * <p>
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {


    public static void main(String[] args) {
        test(TreeBuilder.arrayToTree(new Integer[]{1, 3, null, null, 2}), TreeBuilder.arrayToTree(new Integer[]{3, 1, null, null, 2}));
        test(TreeBuilder.arrayToTree(new Integer[]{3, 1, null, null, 2}), TreeBuilder.arrayToTree(new Integer[]{2, 1, 4, null, null, 3}));
        test(TreeBuilder.arrayToTree(new Integer[]{2, 1, 4, null, null, 3}), TreeBuilder.arrayToTree(new Integer[]{2, 1, 4, null, null, 3}));
        test(TreeBuilder.arrayToTree(new Integer[]{2, 1}), TreeBuilder.arrayToTree(new Integer[]{2, 1}));
        test(TreeBuilder.arrayToTree(new Integer[]{1, 2, 3}), TreeBuilder.arrayToTree(new Integer[]{2, 1, 3}));
    }

    private static void test(TreeNode root, TreeNode expected) {
        System.out.println("Input :" + Printer.inOrder(root));
        System.out.println("Expected :" + Printer.inOrder(expected));

//        RecoverBinarySearchTreeUsingMemory usingMemory = new RecoverBinarySearchTreeUsingMemory();
        RecoverBinarySearchTreeConstant constantMemory = new RecoverBinarySearchTreeConstant();
        constantMemory.recoverTree(root);

//        System.out.println(" Using Memory :" + Printer.inOrder(root));
        System.out.println(" Constant Memory :" + Printer.inOrder(root));

    }
}

/**
 * Convert to inOrder
 * find nodes; first & mid;  when you see first time the order is not correct, then those two element as first and mid.
 * then find another element that does not follow the order called last.
 * <p>
 * if last!=null
 * swap(first, last)
 * else
 * swap(first,mid)
 * <p>
 * input [1,3,null,null,2]
 * * *    1
 * * *   /
 * * *  3
 * * *   \
 * * *    2
 * <p>
 * inorder [3, 2, 1]
 * <p>
 * i = 3; j = 3;
 * i = 2 : in[i] < in[j] : mismatch found ; first 3 and mid = 2
 * <p>
 * i = 1 ; j=2 : in[i] < in[j] : mismatch found second time; last = 1
 * <p>
 * swap (first , last )-> [1, 2, 3]
 * <p>
 * * Input: [3,1,4,null,null,2]
 * * <p>
 * * *   3
 * * *  / \
 * * * 1   4
 * * *    /
 * * *   2
 * <p>
 * inorder[1,3,2,4]
 * first = 3, mid = 2, last=null
 * <p>
 * swap(first,mid)
 * [1,2,3,4]
 * <p>
 * O(n)/O(n)
 * Runtime: 3 ms, faster than 33.47% of Java online submissions for Recover Binary Search Tree.
 * Memory Usage: 43.8 MB, less than 65.38% of Java online submissions for Recover Binary Search Tree.
 */
class RecoverBinarySearchTreeUsingMemory {

    public void recoverTree(TreeNode root) {

        if (null == root)
            return;

        List<TreeNode> inorder = new ArrayList<>();
        inOrder(root, inorder);

        TreeNode first, last, mid;
        first = mid = last = null;

        int i = 1, j = 0;

        for (; i < inorder.size(); i++, j++) {

            if (inorder.get(i).val < inorder.get(j).val) {

                if (first == null) {
                    first = inorder.get(j);
                    mid = inorder.get(i);
                } else {
                    last = inorder.get(i);
                    break;
                }
            }
        }

        if (first == null)
            return;

        if (last == null) {
            int temp = first.val;
            first.val = mid.val;
            mid.val = temp;
        } else {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        }


    }

    private void inOrder(TreeNode root, List<TreeNode> inorder) {
        if (null == root)
            return;

        inOrder(root.left, inorder);
        inorder.add(root);
        inOrder(root.right, inorder);

    }

}

/**
 * Instead of converting tree to array, do in-order traversal on the go and check for first, mid and last
 */
class RecoverBinarySearchTreeConstant {

    private void swap(TreeNode a, TreeNode b) {

        swapByValue(a, b);

    }

    private void swapByValue(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public void recoverTree(TreeNode root) {

        if (null == root)
            return;


        TreeNode[] swappedElements = {null, null, null}; //first,mid,last
        TreeNode[] pointer = {null, null}; //i,j

        recoverTree(root, swappedElements, pointer);

        if (swappedElements[0] == null)
            return;


        if (swappedElements[2] == null)
            swap(swappedElements[0], swappedElements[1]);
        else
            swap(swappedElements[0], swappedElements[2]);
    }


    final int i = 0, j = 1;

    private void recoverTree(TreeNode root, TreeNode[] swappedElements, TreeNode[] pointer) {

        if (null == root)
            return;


        pointer[i] = root;
        recoverTree(root.left, swappedElements, pointer);
        pointer[i] = root;

        if (pointer[j] != null)
            if (pointer[j].val > pointer[i].val) {
                if (swappedElements[0] == null) {
                    swappedElements[0] = pointer[j];
                    swappedElements[1] = pointer[i];
                } else if (swappedElements[2] == null) {
                    swappedElements[2] = pointer[i];
                }
            }

        pointer[j] = pointer[i];

        recoverTree(root.right, swappedElements, pointer);

    }

}