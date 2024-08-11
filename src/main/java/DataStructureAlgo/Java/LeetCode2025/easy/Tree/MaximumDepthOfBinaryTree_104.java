package DataStructureAlgo.Java.LeetCode2025.easy.Tree;

import DataStructureAlgo.Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date:11/08/24
 * Question Category: 104. Maximum Depth of Binary Tree @easy
 * Description:
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 *
 *
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.MaximumDepthBinaryTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree
 *
 *
 * <p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Amazon
 * @Spotify
 * @Google
 * @Microsoft
 *
 * @Editorial
 */

public class MaximumDepthOfBinaryTree_104 {


    static class Solution {
        public int maxDepth(TreeNode root) {
                return maxDepthOrHeightOfBinaryTree(root);
        }

        private int maxDepthOrHeightOfBinaryTree(TreeNode root){
            return (root == null ? 0: Math.max(maxDepthOrHeightOfBinaryTree(root.left) , maxDepthOrHeightOfBinaryTree(root.right) )+ 1);
        }
    }
}
