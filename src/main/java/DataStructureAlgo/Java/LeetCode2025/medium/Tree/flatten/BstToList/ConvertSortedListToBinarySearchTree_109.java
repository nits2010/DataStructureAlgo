package DataStructureAlgo.Java.LeetCode2025.medium.Tree.flatten.BstToList;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/24/2024
 * Question Category: 109. Convert Sorted List to Binary Search Tree
 * Description: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
 * height-balanced
 *  binary search tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * Example 2:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in head is in the range [0, 2 * 104].
 * -105 <= Node.val <= 105
 *
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.listToBST.ConvertSortedSinglyListBinarySearchTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @LinkedList
 * @DivideandConquer
 * @Tree
 * @BinarySearchTree
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Microsoft
 * @Google
 * @Editorial
 */
public class ConvertSortedListToBinarySearchTree_109 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        test &= test(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3});
        test &= test(new Integer[]{1}, new Integer[]{1});
        test &= test(new Integer[]{}, new Integer[]{});
        System.out.println("===========================");
        System.out.println(test ? "All passed" : "Something Failed");
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        System.out.println("-----------------------------------------");
        System.out.println("Input :" + Arrays.toString(input) + "\nExpected :" + Arrays.toString(expected));

        Solution listToBST = new Solution();
        TreeNode root = listToBST.sortedListToBST(ListBuilder.arrayToSinglyList(input));
        List<Integer> inorder = TreeTraversalRecursive.inOrder(root);
        boolean testBottom = CommonMethods.equalsValues(inorder, Arrays.asList(expected));
        System.out.println("Output Bottom Up Inorder : " + inorder + " testBottom : " + (testBottom ? "Passed" : "Failed"));

        return testBottom;

    }

    static class Solution {
        public TreeNode sortedListToBST(ListNode head) {

            if (head == null)
                return null;

            int n = length(head);

            return sortedListToBST(new ListNode[]{head}, 0, n - 1);
        }

        private TreeNode sortedListToBST(ListNode[] head, int start, int end) {
            if (start > end)
                return null;

            int middle = (start + end) / 2;
            TreeNode left = sortedListToBST(head, start, middle - 1);

            TreeNode parent = new TreeNode(head[0].val);
            head[0] = head[0].next;
            parent.left = left;

            parent.right = sortedListToBST(head, middle + 1, end);

            return parent;
        }

        private int length(ListNode head) {
            int n = 0;
            while (head != null) {
                n++;
                head = head.next;
            }
            return n;
        }
    }
}
