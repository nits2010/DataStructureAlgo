package DataStructureAlgo.Java.LeetCode2025.medium.Tree;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/13/2024
 * Question Category: 1161. Maximum Level Sum of a Binary Tree
 * Description: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/
 *
 * <p>
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * <p>
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * Example 2:
 * <p>
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 * <p>
 * File reference
 * -----------
 * Duplicate {@link }
 * Similar {@link}
 * extension {@link TreeTraversalRecursive#levelOrder2(TreeNode)}
 * <p>
 * <p>
 * Tags
 * -----
 *
 * @medium <p>
 * Company Tags
 * -----
 * @Facebook
 *
 * @Editorial <a href="https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/editorial">...</a>
 */
public class MaximumLevelSumOfABinaryTree_1161 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new Integer[]{1, 7, 0, 7, -8, null, null}, 2);
        test &= test(new Integer[]{989, null, 10250, 98693, -89388, null, null, null, -32127}, 2);
        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] input, int expected) {
        System.out.println("---------------------------------");
        System.out.println("\n Input :" + Arrays.toString(input));
        System.out.print(" expected :" + expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        System.out.println("\n Input Tree preOrder:" + TreeTraversalRecursive.preOrder(root));

        SolutionLevelOrder solutionLevelOrder = new SolutionLevelOrder();
        SolutionRecursive solutionRecursive = new SolutionRecursive();
        int outputLevelOrder = solutionLevelOrder.maxLevelSum(root);
        int outputRecursive = solutionRecursive.maxLevelSum(root);

        System.out.println(" outputLevelOrder :" + outputLevelOrder);
        System.out.println(" outputRecursive :" + outputRecursive);

        return outputLevelOrder == expected && expected == outputRecursive;
    }


    /**
     * T/S: O(n)/O(n)
     */
    static class SolutionLevelOrder {
        public int maxLevelSum(TreeNode root) {

            if (root == null)
                return 0;

            int maxLevelSum = Integer.MIN_VALUE;
            int minLevel = Integer.MAX_VALUE;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int level = 1;

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                int currentlevelSum = 0;
                while (levelSize > 0) {
                    TreeNode current = queue.remove();

                    currentlevelSum += current.val;

                    if (current.left != null)
                        queue.add(current.left);

                    if (current.right != null)
                        queue.add(current.right);


                    levelSize--;
                }

                if (maxLevelSum < currentlevelSum) {
                    minLevel = level;
                    maxLevelSum = currentlevelSum;
                }

                level++;
            }

            return minLevel;
        }
    }

    static class SolutionRecursive {
        public int maxLevelSum(TreeNode root) {

            if (root == null)
                return 0;

            final List<Integer> levelOrderSum = new ArrayList<>();
            maxLevelSum(root, levelOrderSum, 0);

            int maxLevelSum = Integer.MIN_VALUE;
            int minLevel = Integer.MAX_VALUE;
            int currentlevelSum;

            for (int i = 0; i < levelOrderSum.size(); i++) {

                currentlevelSum = levelOrderSum.get(i);

                if (maxLevelSum < currentlevelSum) {

                    minLevel = i;

                    maxLevelSum = currentlevelSum;

                }
            }

            return minLevel + 1;

        }

        public void maxLevelSum(TreeNode root, final List<Integer> levelOrderSum, int level) {

            if (root == null)
                return;

            if (levelOrderSum.size() == level)
                levelOrderSum.add(0);


            int currentSum = levelOrderSum.get(level) + root.val;
            levelOrderSum.set(level, currentSum);

            maxLevelSum(root.left, levelOrderSum, level + 1);
            maxLevelSum(root.right, levelOrderSum, level + 1);
        }
    }
}
