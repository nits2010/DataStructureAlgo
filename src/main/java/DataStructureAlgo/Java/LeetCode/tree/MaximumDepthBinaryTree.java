package DataStructureAlgo.Java.LeetCode.tree;

import  DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-18
 * Question Title: Maximum Depth Binary Tree
 * Link: TODO: Add Link
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumDepthBinaryTree {
    public int maxDepthT(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepth(root.left);

        int right = maxDepth(root.right);

        int ownHeight = Math.max(left, right) + 1;
        return ownHeight;
    }

    public int maxDepth(TreeNode root) {

        return (root == null) ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }
}
