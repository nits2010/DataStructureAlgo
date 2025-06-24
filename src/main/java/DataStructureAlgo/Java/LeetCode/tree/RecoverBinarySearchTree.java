package DataStructureAlgo.Java.LeetCode.tree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.helpers.templates.TreeNode;
import  DataStructureAlgo.Java.nonleetcode.Tree.traversal.MoriesTreeTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-29
 * Description: 99. Recover Binary Search Tree
 * https://leetcode.com/problems/recover-binary-search-tree/
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
        System.out.println("\nInput :" + CommonMethods.inOrder(root));
        System.out.println("Expected :" + CommonMethods.inOrder(expected));

//        RecoverBinarySearchTreeUsingMemory usingMemory = new RecoverBinarySearchTreeUsingMemory();
//        usingMemory.recoverTree(root);
//        System.out.println(" Using Memory :" + Printer.inOrder(root));

//        RecoverBinarySearchTreeConstantRecursive constantMemory = new RecoverBinarySearchTreeConstantRecursive();
//        constantMemory.recoverTree(root);
//        System.out.println(" Constant Memory :" + Printer.inOrder(root));


        RecoverBinarySearchTreeConstantMories constantMemoryMories = new RecoverBinarySearchTreeConstantMories();
        constantMemoryMories.recoverTree(root);
        System.out.println(" Constant Memory Mories:" + CommonMethods.inOrder(root));


    }
}

/**
 * Convert to inOrder
 * find nodes; first & mid;  when you see first time the order is not correct, then those two element as first and last.
 * then find another element that does not follow the order ,update last.
 * <p>
 * swap(first, last)
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
 * i = 2 : in[i] < in[j] : mismatch found ; first 3 and last = 2
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
 * first = 3, last = 2,
 * <p>
 * swap(first,last)
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

        TreeNode first, last;
        first = last = null;

        int i = 1, j = 0;

        for (; i < inorder.size(); i++, j++) {

            if (inorder.get(i).val < inorder.get(j).val) {

                if (first == null) {
                    first = inorder.get(j);
                    last = inorder.get(i);
                } else {
                    last = inorder.get(i);
                    break;
                }
            }
        }

        if (first == null)
            return;


        int temp = first.val;
        first.val = last.val;
        last.val = temp;


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
 * <p>
 * Runtime: 2 ms, faster than 99.76% of Java online submissions for Recover Binary Search Tree.
 * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Recover Binary Search Tree.
 */
class RecoverBinarySearchTreeConstantRecursive {


    private void swapByValue(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public void recoverTree(TreeNode root) {

        if (null == root)
            return;


        TreeNode[] swappedElements = {null, null}; //first,last
        TreeNode[] prev = {null}; //j

        recoverTree(root, swappedElements, prev);

        if (swappedElements[0] == null)
            return;
        swapByValue(swappedElements[0], swappedElements[1]);
    }


    private void recoverTree(TreeNode current, TreeNode[] swappedElements, TreeNode[] prev) {

        if (null == current)
            return;


        recoverTree(current.left, swappedElements, prev);

        if (prev[0] != null)
            if (prev[0].val > current.val) {
                if (swappedElements[0] == null) {
                    swappedElements[0] = prev[0];
                    swappedElements[1] = current;
                } else {
                    swappedElements[1] = current;
                }
            }

        prev[0] = current;

        recoverTree(current.right, swappedElements, prev);

    }

}


/**
 * WE can do mories inorder traversal to achieve the same
 * <p>
 * Runtime: 2 ms, faster than 99.76% of Java online submissions for Recover Binary Search Tree.
 * Memory Usage: 39.9 MB, less than 80.77% of Java online submissions for Recover Binary Search Tree.
 * <p>
 * {@link MoriesTreeTraversal}
 */
class RecoverBinarySearchTreeConstantMories {


    private void swapByValue(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public void recoverTree(TreeNode root) {

        if (null == root)
            return;


        TreeNode first = null;
        TreeNode last = null;

        TreeNode prev = null;


        TreeNode current = root;

        while (current != null) {

            if (current.left == null) {

                if (prev != null)
                    if (prev.val > current.val) {
                        if (first == null) {
                            first = prev;
                            last = current;
                        } else {
                            last = current;
                        }
                    }

                prev = current;

                current = current.right;

            } else {
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current)
                    pred = pred.right;

                if (pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {

                    pred.right = null;

                    if (prev != null)
                        if (prev.val > current.val) {
                            if (first == null) {
                                first = prev;
                                last = current;
                            } else {
                                last = current;
                            }
                        }

                    prev = current;

                    current = current.right;
                }
            }
        }
        if (first != null)
            swapByValue(first, last);

    }


}