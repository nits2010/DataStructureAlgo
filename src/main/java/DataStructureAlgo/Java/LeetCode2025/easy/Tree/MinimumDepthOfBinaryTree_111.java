package DataStructureAlgo.Java.LeetCode2025.easy.Tree;

import DataStructureAlgo.Java.LeetCode.templates.TreeNode;
import DataStructureAlgo.Java.helpers.TreeBuilder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 8/12/2024
 * Question Category: 111. Minimum Depth of Binary Tree
 * Description: https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 *
 * <p>
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 * <p>
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 * <p>
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
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Facebook
 * @Bloomberg
 *
 * @Editorial <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/solutions/5626957/multiple-solutions-beats-100">...</a>
 */
public class MinimumDepthOfBinaryTree_111 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 9, 20, null, null, 15, 7}, 2);
        test &= test(new Integer[]{2, null, 3, null, 4, null, 5, null, 6}, 5);
        test &= test(new Integer[]{1, 2, 3}, 2);
        test &= test(new Integer[]{-9, -3, 2, null, 4, 4, 0, -6, null, -5}, 3);

        System.out.println("\nTest result = " + (test ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, int expected) {
        MinimumDepthOfBinaryTree_111.SolutionRecursive solutionRecursive = new MinimumDepthOfBinaryTree_111.SolutionRecursive();
        MinimumDepthOfBinaryTree_111.SolutionIterative solutionIterative = new MinimumDepthOfBinaryTree_111.SolutionIterative();
        MinimumDepthOfBinaryTree_111.SolutionIterativeUsingLevelSize solutionIterativeUsingLevelSize = new MinimumDepthOfBinaryTree_111.SolutionIterativeUsingLevelSize();
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        int outputRecursive = solutionRecursive.minDepth(root);
        int outputIterative = solutionIterative.minDepth(root);
        int outputIterativeUsingLevelSize = solutionIterativeUsingLevelSize.minDepth(root);
        System.out.println("Expected : " + expected + " Obtained Recursive: " + outputRecursive
                + " Obtained Iterative: " + outputIterative
                + " Obtained Iterative Using Level Size: " + outputIterativeUsingLevelSize);
        return (expected == outputIterative) && (expected == outputRecursive) && (expected == outputIterativeUsingLevelSize);
    }

    /**
     * MinDepth would be the depth of a tree from root node.
     * calculate the minDepth of left and right tree, and minimum of them + 1(for root) would be min depth wrt to that root.
     * however, if a tree is skewed, then minDepth of left or right would be 0.In such case, we need to handle it separate for minDepth.
     * <p>
     * T/S; O(n) / O(n)
     */
    static class SolutionRecursive {

        /**
         * @param root
         * @return minDepth
         * @postOrderTraversal
         * @dfs {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#postOrder(DataStructureAlgo.Java.nonleetcode.Tree.TreeNode)}
         */
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;

            int left = minDepth(root.left);
            int right = minDepth(root.right);

            if (left == 0)
                return right + 1;
            if (right == 0)
                return left + 1;

            return Math.min(left, right) + 1;
        }


    }

    static class SolutionIterative {

        /**
         * The idea is simply calculating the level aka height of the tree (with respect to root)
         * and as soon you find a node, that is leaf, than at that level, it's your minDepth
         *
         * @param root
         * @return minDepth
         * @levelOrderTraversal
         * @bfs {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#levelOrder(DataStructureAlgo.Java.nonleetcode.Tree.TreeNode)}
         */
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            int level = 1; //root is at level 1

            while (!queue.isEmpty()) {

                TreeNode node = queue.remove();
                if (node == null) {
                    level++;
                    queue.add(null); //for next level
                } else {
                    //leaf node, this will be always the first node, wrt to root that is leaf, hence we don't need
                    // to run forward
                    if (node.left == null && node.right == null) {
                        return level;

                    }
                    if (node.left != null)
                        queue.add(node.left);
                    if (node.right != null)
                        queue.add(node.right);
                }

            }

            return level;
        }

    }

    /**
     * The idea is similar of above, one optimization that can be done.
     * We don't need to run fully level by level and then return the minDepth. rather, we can assume that
     * at the given level only, we will have leaf node.
     * <p>
     * This is another way of calculating the height of the tree
     *
     * @param root
     * @return minDepth
     * @levelOrderTraversal
     * @bfs {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#levelOrder(DataStructureAlgo.Java.nonleetcode.Tree.TreeNode)}
     */
    static class SolutionIterativeUsingLevelSize {

        /**
         * @param root
         * @return minDepth
         * @levelOrderTraversal
         * @bfs {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#levelOrder(DataStructureAlgo.Java.nonleetcode.Tree.TreeNode)}
         */
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int level = 1; //root is at level 1

            while (!queue.isEmpty()) {

                int size = queue.size();  //size Of The Level AkA Number Of Nodes At This Level
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.remove();

                    //leaf node, this will be always the first node, wrt to root that is leaf, hence we don't need
                    // to run forward

                    if (node.left == null && node.right == null)
                        return level;

                    if (node.left != null)
                        queue.add(node.left);
                    if (node.right != null)
                        queue.add(node.right);
                }
                level++;

            }

            return level;
        }

    }


}

