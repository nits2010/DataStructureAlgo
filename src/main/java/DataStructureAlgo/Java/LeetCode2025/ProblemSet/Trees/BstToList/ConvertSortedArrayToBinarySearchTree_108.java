package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BstToList;


import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: 108. Convert Sorted Array to Binary Search Tree
 * Description: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a
 * height-balanced
 *  binary search tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 *
 *
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 *
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.listToBST.ConvertSortedArrayBinarySearchTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Array
 * @DivideandConquer
 * @Tree
 * @BinarySearchTree
 * @BinaryTree
 *
 * <p>
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */
public class ConvertSortedArrayToBinarySearchTree_108 {

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
        System.out.println("\n Input :" + CommonMethods.toString(nums) + " Expected In-order traversal : " + inOrderTraversal + " Expected Height : " + expectedHeight);

        final TreeNode treeNode = sortedArrayToBST(nums);

        int height = height(treeNode);

        System.out.println("\n Singly Inorder Traversal : " + CommonMethods.inOrder(treeNode) + " Height : " + height);
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

    private static TreeNode sortedArrayToBST(int[] nums, int[] index, int left, int right) {
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
