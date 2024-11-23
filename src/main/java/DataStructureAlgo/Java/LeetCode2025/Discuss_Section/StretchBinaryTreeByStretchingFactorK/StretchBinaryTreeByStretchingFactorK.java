package DataStructureAlgo.Java.LeetCode2025.Discuss_Section.StretchBinaryTreeByStretchingFactorK;


import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/27/2024
 * Question Category: Startup | Phone Screen | Stretch Binary Tree by Stretching Factor K
 * Description: https://leetcode.com/discuss/interview-question/967604/Startup-or-Phone-Screen-or-Stretch-Binary-Tree-by-Stretching-Factor-K
 * Replace the nodes of a binary tree with 'k' nodes of 1/k of N original node's value. The replaced node(s) of N should extend from their parent in the same direction
 * that N extends from its parent. Root node is a special case and treat its streched clones by extending towards the left. Must use recursion with optimal solution.
 * <p>
 * Detailed question:
 * Problem Explanation:
 * You have a binary tree where each node contains a value N. You are asked to replace each node with k nodes, where the following rules apply:
 * <p>
 * For any node N:
 * <p>
 * The k new nodes that replace N will have the value 1/k of the original node's value N.
 * These new nodes will be inserted in a chain-like manner, following the direction of the original node.
 * If N is the left child of its parent, then the k nodes will be linked on the left side of their parent.
 * If N is the right child of its parent, then the k nodes will be linked on the right side of their parent.
 * For the root node:
 * <p>
 * Regardless of the original structure, the root node's k clones will always extend towards the left of the root.
 * Recursive Requirement:
 * <p>
 * The problem should be solved recursively, meaning that for each node in the tree, the same logic should be applied to its children after handling the current node.
 * Visualization
 * For example, suppose we have a binary tree with root node 10, left child 20, and right child 30. If k = 3, the transformation would look like this:
 * <p>
 * The root node 10 is replaced by three nodes, each with the value 10 / 3. These nodes will extend towards the left.
 * The node 20 is replaced by three nodes, each with the value 20 / 3, extending to the left if 20 is the left child of its parent.
 * The node 30 is replaced by three nodes, each with the value 30 / 3, extending to the right if 30 is the right child of its parent.
 * <p>
 *
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 *
 * @Tree
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Editorial
 */
public class StretchBinaryTreeByStretchingFactorK {
    public static void main(String[] args) {

        boolean test = true;
//        test &= test(new Integer[]{12,81,34,null,56,19,6}, 2, new Integer[]{6, null, 6, 40, null, 17, null, 40, null, 17, 28, null, 9, null, 3, null, 28, 9, 3});


        // Construct a sample binary tree
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(new Integer[]{12,81,34,null,56,19,6});

        // Create an object of the BinaryTreeTransformation class
        Solution treeTransformation = new Solution();

        // Set value of k (number of nodes to replace with)
        int k = 2;

        // Transform the binary tree and get the new root
        TreeNode newRoot = treeTransformation.stretchTree(root, k);

        // Print the transformed tree
        List<Integer> levelOrder = TreeTraversalRecursive.levelOrder(newRoot);
        System.out.println(levelOrder);
    }

    static class Solution {

        public TreeNode stretchTree(TreeNode root, int k) {

            if (root == null)
                return null;

            return stretchTree(root, k, true);

        }

        public TreeNode stretchTree(TreeNode root, int k, boolean isLeftDirection) {

            if (root == null)
                return null;


            //stretch root
            int currentNodeValue = root.val;

            // Create a chain of k new nodes, each with value (originalValue / k)
            TreeNode[] temp = strechNode(currentNodeValue, k, isLeftDirection);

            temp[1].left = stretchTree(root.left, k, isLeftDirection);
            temp[1].right = stretchTree(root.right, k, !isLeftDirection);
            return temp[0];

        }

        private TreeNode[] strechNode(int currentNodeValue, int k, boolean isLeftDirection) {

            // Create a chain of k new nodes, each with value (originalValue / k)
            TreeNode temp = null, child = null;
            //with 'k' nodes of 1/k of N original node's value
            for (int i = 0; i < k; i++) {
                TreeNode newNode = new TreeNode(currentNodeValue / k);

                if (i == 0) {
                    temp = newNode;
                    child = newNode;
                } else {
                    //The replaced node(s) of N should extend from their parent in the same direction that N extends from its parent
                    //  Root node is a special case and treat its streched clones by extending towards the left

                    if (isLeftDirection) {
                        newNode.left = temp;
                    } else {
                        newNode.right = temp;
                    }

                    temp = newNode;
                }
            }

            return new TreeNode[]{temp, child};
        }
    }
}
