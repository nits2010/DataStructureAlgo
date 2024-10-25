package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BST._776;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.BST._450.DeleteNodeInABST_450;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/20/2024
 * Question Category: 776. Split BST
 * Description: https://leetcode.com/problems/split-bst/
 * https://leetcode.ca/all/776.html
 *
 * Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree contains a node with value V.
 *
 * Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.
 *
 * You should output the root TreeNode of both subtrees after splitting, in any order.
 *
 * Example 1:
 *
 * Input: root = [4,2,6,1,3,5,7], V = 2
 * Output: [[2,1],[4,3,6,null,null,5,7]]
 * Explanation:
 * Note that root, output[0], and output[1] are TreeNode objects, not arrays.
 *
 * The given tree [4,2,6,1,3,5,7] is represented by the following diagram:
 *
 4
 *         /   \
 *       2      6
 *      / \    / \
 *     1   3  5   7
 *
 * while the diagrams for the outputs are:
 *
 *           4
 *         /   \
 *       3      6      and    2
 *             / \           /
 *            5   7         1
 * Note:
 *
 * The size of the BST will not exceed 50.
 * The BST is always valid and each node's value is different.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DeleteNodeInABST_450}
 * <p>
 * Tags
 * -----
 * @medium
 * @Tree
 * @BinarySearchTree
 * @BinaryTree
 * @LeetCodeLockedProblem
 * @PremimumQuestion
 *
 * <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Coupang
 * @Google
 *
 * @Editorial <a href="https://leetcode.ca/2018-01-14-776-Split-BST">...</a> <a href="https://algo.monster/liteproblems/776">...</a>
 */
public class SplitBST_776 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{4, 2, 6, 1, 3, 5, 7}, 2, new Integer[]{2, 1}, new Integer[]{4, 3, 6, 5, 7});
        test &= test(new Integer[]{4, 2, 6, 1, 3, 5, 7}, 6, new Integer[]{6, 5}, new Integer[]{4, 2, 7, 1, 3});
        test &= test(new Integer[]{4, 2, 6, 1, 3, 5, 7}, 4, new Integer[]{4, 2, 1, 3}, new Integer[]{6, 5, 7});
        test &= test(new Integer[]{4, 2, 6, 1, 3, 5, 7}, 1, new Integer[]{1}, new Integer[]{4, 2, 6, 3, 5, 7});
        test &= test(new Integer[]{4, 2, 6, 1, 3, 5, 7}, 0, new Integer[]{}, new Integer[]{4, 2, 6, 1, 3, 5, 7});
        test &= test(new Integer[]{40, 20, 50, 10, 35, 60, null, null, 25, null, 55}, 35, new Integer[]{20, 10, 35, 25}, new Integer[]{40, 55, 50, 60});
        System.out.println("============================");
        System.out.println(test ? " All Passed " : " Something Failed ");
    }

    private static boolean test(Integer[] tree, int target, Integer[] expectedSmaller, Integer[] expectedGreater) {
        System.out.println("============================");
        System.out.println("Input: tree = " + Arrays.toString(tree) + ", target = " + target);
        System.out.println("Expected: expectedSmaller = " + Arrays.toString(expectedSmaller) + ", expectedGreater = " + Arrays.toString(expectedGreater));

        Solution solutionSimplified = new Solution();
        TreeNode[] trees = solutionSimplified.splitBST(TreeBuilder.buildTreeFromLevelOrder(tree), target);
        List<List<Integer>> levelOrder = List.of(TreeTraversalRecursive.levelOrder(trees[0]), TreeTraversalRecursive.levelOrder(trees[1]));
        boolean testResultSimplified = CommonMethods.equalsValues(levelOrder.get(0), Arrays.asList(expectedSmaller))
                && CommonMethods.equalsValues(levelOrder.get(1), Arrays.asList(expectedGreater));
        System.out.println("Output Simplified : levelOrder = " + levelOrder + " Test Result " + (testResultSimplified ? " Passed " : " Failed "));

        return testResultSimplified;

    }


    /**
     * Time: if the tree is skewed then O(n), if balanced O(logn)
     * Space: if the tree is skewed then O(n), if balanced O(logn)
     */
    static class Solution {
        public TreeNode[] splitBST(TreeNode root, int target) {
            if (root == null)
                return new TreeNode[]{null, null};

            // Since the target is on left side, means one of the left side node will be detached and make a smaller tree {Nodes[0]}
            // hence all the greater element of that root, has to attach to the greater tree on left side of this root (preserve BST)
            if (target < root.val) {
                TreeNode[] nodes = splitBST(root.left, target);
                root.left = nodes[1]; // greater tree is on right side, nodes[1]
                return new TreeNode[]{nodes[0], root}; // smaller tree is on the left side, now root will hold greater elements
            } else {
                // Since the target is on the right side, means whole right subtree has to be detached and make a greater tree {Nodes[1]}
                //while all the smaller element of this root, has to attach to right of this root to preserve BST.
                TreeNode[] nodes = splitBST(root.right, target);
                root.right = nodes[0];
                return new TreeNode[]{root, nodes[1]};
            }
        }


    }


}
