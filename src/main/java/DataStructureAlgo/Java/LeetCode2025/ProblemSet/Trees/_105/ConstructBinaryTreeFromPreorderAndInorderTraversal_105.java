package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._105;

import DataStructureAlgo.Java.helpers.*;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/10/2025
 * Question Title: 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description
 * Description: Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._106.ConstructBinaryTreeFromInorderAndPostorderTraversal_106}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._889.ConstructBinaryTreeFromPreorderAndPostorderTraversal_889}
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
 * @Uber
 * @Microsoft
 * @Bloomberg
 * @Google
 * @Amazon
 * @Adobe
 * @Apple
 * @Facebook
 * @Oracle
 * @Square
 * @Twitter
 * @Yahoo <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}, Arrays.asList(3, 9, 20, null, null, 15, 7)));
        tests.add(test(new int[]{-1}, new int[]{-1}, Arrays.asList(-1)));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] preOrder, int[] inOrder, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"preOrder", "inOrder", "Expected"}, true, preOrder, inOrder, expected);

        boolean pass, finalPass = true;

        TreeNode root = new Solution().buildTree(preOrder, inOrder);
        List<Integer> output = TreeTraversalRecursive.levelOrderWithNull(root);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = inorder.length;

            //cache the inorder index of each element, to avoid scanning for an element later
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(inorder[i], i);
            }

            return buildTree(preorder, new int[]{0}, 0, n - 1, map);
        }

        /**
         * Logic: Get an element from preorder, use that to divide the inorder to left and right subtree
         *
         * Get the element from preorder (defined by pIndex) and then search in inOrder, as inIndex. The elements on the left
         * side of inIndex from inLow are left subtree and the remaining are rightSUb-tree.
         * @param preorder preorder array
         * @param pIndex   index of a preorder element that needs to be take next
         * @param inLow     low index of inorder
         * @param inHigh    high index of inorder
         * @param map      cache for inOrder
         * @return
         */
        private TreeNode buildTree(int[] preorder, int[] pIndex, int inLow, int inHigh, Map<Integer, Integer> map) {
            if (pIndex[0] > preorder.length || inLow > inHigh)
                return null;

            //create a new with preOrder
            int value = preorder[pIndex[0]];
            pIndex[0]++; //move a pointer to next element for next turn
            TreeNode root = new TreeNode(value);

            //get inorder index
            int inIndex = map.get(value);

            root.left = buildTree(preorder, pIndex, inLow, inIndex - 1, map);
            root.right = buildTree(preorder, pIndex, inIndex + 1, inHigh, map);

            return root;
        }
    }
}
