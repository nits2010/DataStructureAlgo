package DataStructureAlgo.Java.LeetCode.listToBST;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-01
 * Description: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example:
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <p>
 * *       0
 * *      / \
 * *    -3   9
 * *    /   /
 * *  -10  5
 * <p>
 * {@link ConvertSortedSinglyListBinarySearchTree}
 */
public class ConvertSortedArrayBinarySearchTree {


    private static int height(int n) {
        return (int) Math.ceil(Math.log(n) / Math.log(2));
    }

    private static int height(TreeNode root) {
        if (null == root)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;

    }

    public static void main(String[] args) {

        System.out.println("........");
        test(new int[]{1, 2, 3, 4, 5}, Arrays.asList(1, 2, 3, 4, 5), height(5));

        System.out.println("........");
        test(new int[]{-10, -3, 0, 5, 9}, Arrays.asList(-10, -3, 0, 5, 9), height(5));

        System.out.println("........");
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), height(10));
    }

    private static void test(int[] nums, List<Integer> inOrderTraversal, int expectedHeight) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums) + " Expected In-order traversal :" + inOrderTraversal + " Expected Height :" + expectedHeight);

        final TreeNode treeNode = sortedArrayToBST(nums);

        int height = height(treeNode);

        System.out.println("\n Singly Inorder Traversal :" + GenericPrinter.inOrder(treeNode) + " Height :" + height);
    }

    /**
     * Complexity: O(n)/O(log(n))
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
     * Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
     * same as {@link ConvertSortedSinglyListBinarySearchTree}
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {

        int size = nums.length;
        /**
         * Empty list to empty tree
         */
        if (size == 0)
            return null;


        return sortedArrayToBST(nums, new int[]{0}, 0, size);


    }

    private static TreeNode sortedArrayToBST(int nums[], int[] index, int left, int right) {
        if (left >= right)
            return null;

        //Binary Search
        int mid = (left + right) >> 1;

        TreeNode leftNode = sortedArrayToBST(nums, index, left, mid);

        TreeNode root = new TreeNode(nums[index[0]]);
        index[0]++;

        root.left = leftNode;

        root.right = sortedArrayToBST(nums, index, mid + 1, right);

        return root;
    }


}
