package DataStructureAlgo.Java.LeetCode2025.medium.Tree.viewOfTree;

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
 * Question Category: Binary Tree left Side View
 * Description: https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
 * The Right View of a Binary Tree is a list of nodes that can be seen when the tree is viewed from the right side.
 * The Left View of a Binary Tree is a list of nodes that can be seen when the tree is viewed from the left side.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link BinaryTreeRightSideView_199}
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
public class BinaryTreeLeftSideView {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1, 2, 3, null, 5, null, 4}, List.of(1, 2, 5));
        test &= test(new Integer[]{1, null, 3}, List.of(1, 3));
        test &= test(new Integer[]{1, 2, 3, 4, 10, 9, 11, null, 5, null, null, null, null, null, null, null,6 }, List.of(1, 2, 4, 5, 6));
        test &= test(new Integer[]{2, 7, 5, 2, 6,null, 9, null, null, 5, 11, 4, null}, List.of(2,7,2,5));
        System.out.println("============================");
        System.out.println(test ? " All Passed " : " Something Failed ");
    }


    private static boolean test(Integer[] input, List<Integer> expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " expected :" + expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        SolutionUsingLevelOrder solutionUsingLevelOrder = new SolutionUsingLevelOrder();
        List<Integer> outputUsingLevelOrder = solutionUsingLevelOrder.leftSideView(root);
        boolean resultUsingLevelOrder = CommonMethods.equalsValues(expected, outputUsingLevelOrder);
        System.out.println("outputUsingLevelOrder : " + outputUsingLevelOrder + " resultUsingLevelOrder : " + resultUsingLevelOrder);

        SolutionUsingPreOrderTraversal solutionUsingPreOrderTraversal = new SolutionUsingPreOrderTraversal();
        List<Integer> outputPreOrder = solutionUsingPreOrderTraversal.leftSideView(root);
        boolean resultUsingPreOrder = CommonMethods.equalsValues(expected, outputPreOrder);
        System.out.println("outputUsingPreOrder : " + outputPreOrder + " resultUsingPreOrder : " + resultUsingPreOrder);

        boolean finalResult = resultUsingLevelOrder && resultUsingPreOrder;
        System.out.println("finalResult : " + (finalResult ? " PASS " : " FAIL "));
        return finalResult;
    }

    /**
     * This can be solved using level order traversal.
     * During the level order traversal, for each level the last node would be the right most node, which participate on the right side view
     * Similarly the first node on this level would participate on left side view.
     */
    static class SolutionUsingLevelOrder {
        public List<Integer> leftSideView(TreeNode root) {

            List<Integer> leftSideView = new LinkedList<>();
            if (root == null)
                return leftSideView;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {

                int size = queue.size();
                TreeNode leftMostNode = queue.peek();

                while (size > 0) {
                    TreeNode currentNode = queue.poll();

                    if (currentNode.left != null)
                        queue.offer(currentNode.left);

                    if (currentNode.right != null)
                        queue.offer(currentNode.right);


                    size--;
                }

                leftSideView.add(leftMostNode.val);

            }
            return leftSideView;

        }
    }

    /**
     * We can alternatively solve this using pre-order traversal.
     * For each level, the first node visited (root, right, left) would participate in right side view.
     * similarly for the level, the first node visited (root, left, right) would participate in left side view
     */
    static class SolutionUsingPreOrderTraversal {
        public List<Integer> leftSideView(TreeNode root) {


            List<Integer> leftSideView = new LinkedList<>();
            if (root == null)
                return leftSideView;

            leftSideView(root, leftSideView, 0);
            return leftSideView;

        }

        private void leftSideView(TreeNode root, List<Integer> leftSideView, int level) {
            if (root == null)
                return;

            if (leftSideView.size() == level)
                leftSideView.add(root.val);

            leftSideView(root.left, leftSideView, level + 1);
            leftSideView(root.right, leftSideView, level + 1);
        }
    }
}
