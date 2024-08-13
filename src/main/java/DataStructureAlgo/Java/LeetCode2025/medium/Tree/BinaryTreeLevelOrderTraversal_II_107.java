package DataStructureAlgo.Java.LeetCode2025.medium.Tree;

import DataStructureAlgo.Java.helpers.GenericPrinter;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/13/2024
 * Question Category: 107. Binary Tree Level Order Traversal II
 * Description: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 * <p>
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link TreeTraversalRecursive#levelOrder2(TreeNode)}
 * <p>
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @Breadth-FirstSearch
 * @BinaryTree <p>
 * Company Tags
 * -----
 * @Amazon
 *
 * @Editorial <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii/solutions/5631775/logic-building-solutinos">...</a>
 */
public class BinaryTreeLevelOrderTraversal_II_107 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 9, 20, null, null, 15, 7}, new Integer[][]{{15, 7}, {9, 20}, {3}});
        test &= test(new Integer[]{1}, new Integer[][]{{1}});
        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] input, Integer[][] expected) {
        System.out.println("---------------------------------");
        System.out.println("\n Input :" + Arrays.toString(input));
        System.out.print("\n expected :");
        GenericPrinter.print(expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        System.out.println("\n Input Tree preOrder:" + TreeTraversalRecursive.preOrder(root));

        SolutionUsingLevelOrderReverse solutionUsingLevelOrderReverse = new SolutionUsingLevelOrderReverse();
        SolutionUsingLevelOrder solutionUsingLevelOrder = new SolutionUsingLevelOrder();
        SolutionRecursive solutionRecursive = new SolutionRecursive();


        List<List<Integer>> resultUsingLevelOrderReverse = solutionUsingLevelOrderReverse.levelOrderBottom(root);
        List<List<Integer>> resultUsingLevelOrder = solutionUsingLevelOrder.levelOrderBottom(root);
        List<List<Integer>> resultRecursive = solutionRecursive.levelOrderBottom(root);

        System.out.println(" resultUsingLevelOrderReverse :" + resultUsingLevelOrderReverse);
        System.out.println(" resultUsingLevelOrder :" + resultUsingLevelOrder);
        System.out.println(" resultRecursive :" + resultRecursive);

        boolean testResultUsingLevelOrderReverse = test(expected, resultUsingLevelOrderReverse);
        boolean testResultUsingLevelOrder = test(expected, resultUsingLevelOrder);
        boolean testResultRecursive = test(expected, resultRecursive);


        System.out.println("\n testResultUsingLevelOrderReverse :" + testResultUsingLevelOrderReverse);
        System.out.println("\n testResultUsingLevelOrder :" + testResultUsingLevelOrder);
        System.out.println("\n testResultRecursive :" + testResultRecursive);
        return testResultUsingLevelOrder && testResultUsingLevelOrderReverse && testResultRecursive;


    }

    private static boolean test(Integer[][] expected, List<List<Integer>> result) {
        boolean test = true;

        if(expected.length != result.size() )
            return false;

        for (int i = 0; i < expected.length; i++) {
            if(expected[i].length != result.get(i).size())
                return false;
            for (int j = 0; j < expected[i].length; j++) {
                test &= (expected[i][j].equals(result.get(i).get(j)));
            }
        }
        return test;
    }


    /**
     * this is same as {@link SolutionUsingLevelOrder} but recursive
     * Inspired by {@link TreeTraversalRecursive#levelOrder(NArrayTreeNode)}
     * 
     */
    static class SolutionRecursive {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> levelOrderBottom = new LinkedList<>();

            if (root == null)
                return levelOrderBottom;

            levelOrderBottom(root, levelOrderBottom, 0);
            return levelOrderBottom;

        }

        public void levelOrderBottom(TreeNode root, List<List<Integer>> levelOrderBottom, int level) {
            if (root == null)
                return;

            if (level == levelOrderBottom.size())
                levelOrderBottom.add(0,new LinkedList<>());

            levelOrderBottom.get(levelOrderBottom.size() - level - 1).add(root.val);
            levelOrderBottom(root.left, levelOrderBottom, level + 1);
            levelOrderBottom(root.right, levelOrderBottom, level + 1);
        }
    }

    static class SolutionUsingLevelOrderReverse {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> levelOrder = levelOrder(root);
            Collections.reverse(levelOrder);
            return levelOrder;
        }

        /**
         * {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#levelOrder2(TreeNode)}
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levelOrder = new LinkedList<>();
            if (root == null)
                return levelOrder;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int level = 0;

            while (!queue.isEmpty()) {

                int levelSize = queue.size();
                levelOrder.add(new LinkedList<>());
                while (levelSize > 0) {

                    TreeNode current = queue.remove();

                    levelOrder.get(level).add(current.val);

                    if (current.left != null)
                        queue.add(current.left);
                    if (current.right != null)
                        queue.add(current.right);

                    levelSize--;
                }

                level++;
            }
            return levelOrder;

        }
    }




    static class SolutionUsingLevelOrder {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            return levelOrder(root);

        }

        /**
         * {@link DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive#levelOrder2(TreeNode)}
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levelOrder = new LinkedList<>();
            if (root == null)
                return levelOrder;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {

                int levelSize = queue.size();
                levelOrder.add(0, new LinkedList<>());
                while (levelSize > 0) {

                    TreeNode current = queue.remove();

                    levelOrder.get(0).add(current.val);

                    if (current.left != null)
                        queue.add(current.left);
                    if (current.right != null)
                        queue.add(current.right);

                    levelSize--;
                }


            }
            return levelOrder;

        }
    }
}
