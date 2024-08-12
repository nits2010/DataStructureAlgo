package DataStructureAlgo.Java.LeetCode2025.easy.Tree;

import DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 8/12/2024
 * Question Category: 100. Same Tree
 * Description: https://leetcode.com/problems/same-tree/description/
 *
 * <p>
 *Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 *
 *
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in both trees is in the range [0, 100].
 * -104 <= Node.val <= 104
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
 * @BinaryTree
 *
 * <p>
 * Company Tags
 * -----
 *
 * @LinkedIn
 * @Amazon
 * @Google
 * @Apple
 * @Facebook
 */
public class SameTree_100 {

    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {

            if(p == null && q == null)
                return true;
            if(p == null || q == null)
                return false;

            return ( (p.val == q.val) && (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)));
        }
    }
}
