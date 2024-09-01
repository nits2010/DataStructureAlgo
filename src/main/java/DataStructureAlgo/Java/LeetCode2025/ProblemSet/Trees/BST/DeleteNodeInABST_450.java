package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BST;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/20/2024
 * Question Category: 450. Delete Node in a BST
 * Description: https://leetcode.com/problems/delete-node-in-a-bst/description/
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * Example 3:
 * <p>
 * Input: root = [], key = 0
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -105 <= Node.val <= 105
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -105 <= key <= 105
 * <p>
 * <p>
 * Follow up: Could you solve it with time complexity O(height of tree)?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @BinarySearchTree
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Oracle
 * @Google
 * @LinkedIn <p>
 * @Editorial
 */
public class DeleteNodeInABST_450 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{5, 3, 6, 2, 4, null, 7}, 3, new Integer[]{5, 4, 6, 2, 7});
        test &= test(new Integer[]{5, 3, 6, 2, 4, null, 7}, 0, new Integer[]{5, 3, 6, 2, 4, 7});
        test &= test(new Integer[]{}, 0, new Integer[]{});
        System.out.println("============================");
        System.out.println(test ? " All Passed " : " Something Failed ");
    }

    private static boolean test(Integer[] input, int key, Integer[] expected) {

        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " key : " + key);
        System.out.println("Expected :" + Arrays.toString(expected));

        Solution.SolutionRecursive solutionRecursive = new Solution.SolutionRecursive();
        TreeNode outputRecursiveRoot = solutionRecursive.deleteNode(TreeBuilder.buildTreeFromLevelOrder(input), key);
        List<Integer> solutionRecursiveLevelOrder = TreeTraversalRecursive.levelOrder(outputRecursiveRoot);
        System.out.println("Output Recursive Tree LevelOrder:" + solutionRecursiveLevelOrder);
        boolean testResultRecursive = CommonMethods.equalsValues(solutionRecursiveLevelOrder, Arrays.asList(expected));
        System.out.println("testResultRecursive : " + testResultRecursive);
        return testResultRecursive;

    }


    static class Solution {


        /**
         * {@link DataStructureAlgo.Java.nonleetcode.Tree.BinarySearchTree#delete(Integer, DataStructureAlgo.Java.nonleetcode.Tree.TreeNode)}
         * Space: O(N) if a tree is skewed and data to be deleted on the last node. O(logN) if a tree is balanced.
         * Time: O(N) if a tree is skewed and data to be deleted on the last node. O(logN) if a tree is balanced.
         */
        static class SolutionRecursive {
            public TreeNode deleteNode(TreeNode root, int key) {

                if (root == null)
                    return null;

                //if the key lies left, then delete it and return root.
                if (key < root.val) {
                    root.left = deleteNode(root.left, key);
                    return root;
                } else if (key > root.val) { //if the key lies left, then delete it and return root.
                    root.right = deleteNode(root.right, key);
                    return root;
                } else {

                    //root is the key

                    //case 1: root is a leaf node; simply return null to its parent to reset it.
                    if (root.left == null && root.right == null) {
                        return null;
                    }

                    //case 2: root is a one-child left node, Simply return the left node to its parent to get attach it.
                    //case 3: root is a one-child right node, Simply return the right node to its parent to get attach it.
                    if (root.left == null || root.right == null) {
                        return root.left == null ? root.right : root.left;
                    }

                    /*
                    case 4: root is a two-child node. we need to find the successor to delete it.
                     since it is a BST, to replace the root node and keep the BST property intact for this subtree,
                    this root has to be replaced by the greater value than root, precisely the minimum greater value than root
                     which is an inorder successors, this will lie on the right side of this root, and over the left side of the right subtree.
                    */
                    TreeNode inOrderSuccessor = root.right;

                    while (inOrderSuccessor.left != null) {
                        inOrderSuccessor = inOrderSuccessor.left;
                    }

                    //inOrderSuccessor is now either would be right child node or none.
                    //we'll delete this node recursively
                    root.val = inOrderSuccessor.val;
                    root.right = deleteNode(root.right, inOrderSuccessor.val);
                    return root;

                }


            }


        }

    }
}
