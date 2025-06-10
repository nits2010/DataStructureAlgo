package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._106;

import DataStructureAlgo.Java.helpers.*;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/10/2025
 * Question Title: 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description
 * Description: Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._105.ConstructBinaryTreeFromPreorderAndInorderTraversal_105} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._889.ConstructBinaryTreeFromPreorderAndPostorderTraversal_889}
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
 * @Shopee <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal_106 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}, Arrays.asList(3, 9, 20, null, null, 15, 7)));
        tests.add(test(new int[]{-1}, new int[]{-1}, Arrays.asList(-1)));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] inorder, int[] postorder, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"inorder",  "postorder", "Expected"}, true, inorder, postorder, expected);


        boolean pass, finalPass = true;

        TreeNode root = new Solution().buildTree(inorder, postorder);
        List<Integer> output = TreeTraversalRecursive.levelOrderWithNull(root);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int inLength = inorder.length;
            int postLength = postorder.length;

            //cache the inorder element index for faster lookup
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inLength; i++)
                map.put(inorder[i], i);

            //last element of postOrder is root of BT
            return buildTree(postorder, new int[]{postLength - 1}, 0, inLength - 1, map);
        }

        private TreeNode buildTree(int[] postOrder, int[] postIndex, int inLow, int inHigh, Map<Integer, Integer> map) {
            if (postIndex[0] < 0 || inLow > inHigh)
                return null;

            int value = postOrder[postIndex[0]];
            TreeNode root = new TreeNode(value);

            //move to next element
            postIndex[0]--;
            int inIndex = map.get(value);

            //inIndex will represent the index of value in inOrder, elements on leftSide in inorder from inIndex will represent left and remaining is on right side.
            //the postOrder traversal is given in the form of left, right, root. Hence we need to first create right then left
            root.right = buildTree(postOrder, postIndex, inIndex + 1, inHigh, map);
            root.left = buildTree(postOrder, postIndex, inLow, inIndex - 1, map);


            return root;
        }
    }
}
