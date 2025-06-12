package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._889;

import DataStructureAlgo.Java.helpers.*;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/10/2025
 * Question Title: 889. Construct Binary Tree from Preorder and Postorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description
 * Description: Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
 * <p>
 * If there exist multiple answers, you can return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 * Example 2:
 * <p>
 * Input: preorder = [1], postorder = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * All the values of preorder are unique.
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * All the values of postorder are unique.
 * It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._105.ConstructBinaryTreeFromPreorderAndInorderTraversal_105}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._106.ConstructBinaryTreeFromInorderAndPostorderTraversal_106}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @DivideandConquer
 * @Tree
 * @BinaryTree <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Facebook
 * @Google
 * @Uber
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal_889 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1}, Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        tests.add(test(new int[]{1}, new int[]{1}, Arrays.asList(1)));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] preorder, int[] postorder, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"preorder", "postorder", "Expected"}, true, preorder, postorder, expected);

        boolean pass, finalPass = true;

        TreeNode root = new Solution().constructFromPrePost(preorder, postorder);
        List<Integer> output = TreeTraversalRecursive.levelOrderWithNull(root);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Finding the Root:
     * <p>
     * In Preorder, the first element is always the root.
     * In Postorder, the last element is always the root.
     * You'll primarily use preorder[0] (or preorder[preStart]) as the root of the current subtree.
     * Determining Left/Right Subtree Boundaries: This is the crucial part that changes.
     * <p>
     * With Preorder and Inorder: You find root.val in the inorder array. Elements to its left are left subtree; elements to its right are right subtree.
     * With Preorder and Postorder:
     * The root of the left child (if it exists) is always the second element in the current preorder segment (preorder[preStart + 1]).
     * You can find this left_child_root.val in the postorder array. All elements in the postorder array up to and including this left_child_root.val constitute the left subtree.
     * The count of elements in the left subtree helps you determine the split point in both the preorder and postorder arrays for the recursive calls.
     */
    static class Solution {
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

            int preLength = preorder.length;
            int postLength = postorder.length;

            //cache the index for postOrder
            Map<Integer, Integer> postIndex = new HashMap<>();
            for (int i = 0; i < postLength; i++) {
                postIndex.put(postorder[i], i);
            }

            return buildTree(preorder, 0, preLength - 1, postorder, 0, postIndex);
        }

        private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart,
                                   Map<Integer, Integer> map) {
            //if no elements left
            if (preStart > preEnd)
                return null;

            int currentValue = preorder[preStart];
            TreeNode root = new TreeNode(currentValue);

            //if there is only one element
            if (preStart == preEnd)
                return root;

            //there are multiple elements > 1
            // Find the root of the left child in the preorder array.
            // It's the element immediately after the current root.
            int leftRoot = preorder[preStart + 1];

            //get the nextElement position in postorder
            int postIndex = map.get(leftRoot);

            //all elements from postStart to postIndex are in the left subtree
            int leftSubTreeSize = postIndex - postStart + 1;

            root.left = buildTree(
                    preorder,
                    preStart + 1, // move a pointer in pre-order to the next element
                    preStart + leftSubTreeSize, // all elements post this root will end when the entire left subtree is traversed, hence its end there
                    postorder,
                    postStart, // for the postOrder, it starts from postStart
                    map);
            root.right = buildTree(
                    preorder,
                    preStart + leftSubTreeSize + 1, // Start of right subtree in preorder
                    preEnd, // End of right subtree in preorder
                    postorder,
                    postStart + leftSubTreeSize, // Start of right subtree in postorder
                    map);

            return root;

        }
    }
}
