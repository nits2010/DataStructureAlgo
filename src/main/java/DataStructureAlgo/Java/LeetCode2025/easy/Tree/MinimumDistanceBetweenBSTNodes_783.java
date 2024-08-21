package DataStructureAlgo.Java.LeetCode2025.easy.Tree;

import DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date:21/08/24
 * Question Category: 783. Minimum Distance Between BST Nodes
 * Description:https://leetcode.com/problems/minimum-distance-between-bst-nodes/description
 *
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 100].
 * 0 <= Node.val <= 105
 *
 *
 * Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Bloomberg
 * @Microsoft
 *
 * @Editorial
 */

public class MinimumDistanceBetweenBSTNodes_783 {

    class Solution {

        int min = Integer.MAX_VALUE;
        TreeNode predecessor = null;
        public int minDiffInBST(TreeNode root) {
            if (root == null)
                return 0;;

            minDiffInBST(root.left);
            if(predecessor!=null)
                min = Math.min(min, root.val -  predecessor.val);
            predecessor = root;
            minDiffInBST(root.right);
            return min;
        }
    }
}
