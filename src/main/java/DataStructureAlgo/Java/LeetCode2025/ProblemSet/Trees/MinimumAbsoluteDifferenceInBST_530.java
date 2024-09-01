package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees;

import DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date:21/08/24
 * Question Category: 530. Minimum Absolute Difference in BST
 * Description: https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 104].
 * 0 <= Node.val <= 105
 * <p>
 * <p>
 * Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class MinimumAbsoluteDifferenceInBST_530 {

    int min = Integer.MAX_VALUE;
    TreeNode predecessor = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null)
            return 0;


        getMinimumDifference(root.left);
        if (predecessor != null)
            min = Math.min(min, Math.abs(root.val - predecessor.val));
        predecessor = root;
        getMinimumDifference(root.right);
        return min;
    }


}
