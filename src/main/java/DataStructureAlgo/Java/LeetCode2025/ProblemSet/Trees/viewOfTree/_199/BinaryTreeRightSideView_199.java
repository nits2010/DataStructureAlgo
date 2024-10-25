package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.viewOfTree._199;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 8/19/2024
 * Question Category: 199. Binary Tree Right Side View
 * Description: https://leetcode.com/problems/binary-tree-right-side-view/description/
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 * <p>
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
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
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Bloomberg
 * @Qualtrics
 * @Oracle
 * @Editorial
 */
public class BinaryTreeRightSideView_199 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, null, 5, null, 4}, List.of(1, 3, 4));
        test &= test(new Integer[]{1, null, 3}, List.of(1, 3));
        test &= test(new Integer[]{1, 2, 3, 4, 10, 9, 11, null, 5, null, null, null, null, null, null, null,6 }, List.of(1, 3, 11, 5, 6));
        test &= test(new Integer[]{2, 7, 5, 2, 6,null, 9, null, null, 5, 11, 4, null}, List.of(2,5,9,4));
        System.out.println("============================");
        System.out.println(test ? " All Passed " : " Something Failed ");
    }


    private static boolean test(Integer[] input, List<Integer> expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " expected :" + expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        SolutionUsingLevelOrder solutionUsingLevelOrder = new SolutionUsingLevelOrder();
        List<Integer> outputUsingLevelOrder = solutionUsingLevelOrder.rightSideView(root);
        boolean resultUsingLevelOrder = CommonMethods.equalsValues(expected, outputUsingLevelOrder);
        System.out.println("outputUsingLevelOrder : " + outputUsingLevelOrder + " resultUsingLevelOrder : " + resultUsingLevelOrder);

        SolutionUsingPreOrderTraversal solutionUsingPreOrderTraversal = new SolutionUsingPreOrderTraversal();
        List<Integer> outputPreOrder = solutionUsingPreOrderTraversal.rightSideView(root);
        boolean resultUsingPreOrder = CommonMethods.equalsValues(expected, outputPreOrder);
        System.out.println("outputUsingPreOrder : " + outputPreOrder + " resultUsingPreOrder : " + resultUsingPreOrder);

        boolean finalResult = resultUsingLevelOrder && resultUsingPreOrder;
        System.out.println("finalResult : " + (finalResult ? " PASS " : " FAIL "));
        return finalResult;
    }

    /**
     * This can be solve using level order traversal.
     * During the level order traversal, for each level the last node would be the right most node, which participate on the right side view
     * Similarly the first node on this level would participate on left side view.
     */
    static class SolutionUsingLevelOrder {
        public List<Integer> rightSideView(TreeNode root) {

            List<Integer> rightSideView = new LinkedList<>();
            if (root == null)
                return rightSideView;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {

                int size = queue.size();
                TreeNode rightNode = null;
                while (size > 0) {
                    TreeNode currentNode = queue.poll();

                    if (currentNode.left != null)
                        queue.offer(currentNode.left);

                    if (currentNode.right != null)
                        queue.offer(currentNode.right);

                    rightNode = currentNode;
                    size--;
                }

                rightSideView.add(rightNode.val);

            }
            return rightSideView;

        }
    }

    /**
     * We can alternatively solve this using pre-order traversal.
     * For each level, the first node visited (root, right, left) would participate in right side view.
     * similarly for the level, the first node visited (root, left, right) would participate in left side view
     */
    static class SolutionUsingPreOrderTraversal {
        public List<Integer> rightSideView(TreeNode root) {


            List<Integer> rightSideView = new LinkedList<>();
            if (root == null)
                return rightSideView;

            rightSideView(root, rightSideView, 0);
            return rightSideView;

        }

        private void rightSideView(TreeNode root, List<Integer> rightSideView, int level) {
            if(root == null)
                return;

            if(rightSideView.size() == level)
                rightSideView.add(root.val);

            rightSideView(root.right, rightSideView, level+1);
            rightSideView(root.left, rightSideView, level+1);
        }
    }
}
