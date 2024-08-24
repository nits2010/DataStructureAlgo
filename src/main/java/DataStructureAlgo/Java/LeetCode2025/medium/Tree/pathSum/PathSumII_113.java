package DataStructureAlgo.Java.LeetCode2025.medium.Tree.pathSum;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/14/2024
 * Question Category: 113. Path Sum II
 * Description: https://leetcode.com/problems/path-sum-ii/description/
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 * <p>
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link PathSum_112} & {@link DataStructureAlgo.Java.LeetCode2025.easy.Tree.BinaryTreePaths_257}
 * <p>
 * Tags
 * -----
 * @medium
 * @Backtracking
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Google
 * @Microsoft
 * @Oracle
 * @Editorial
 */
public class PathSumII_113 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1}, 22, new Integer[][]{{5, 4, 11, 2}, {5, 8, 4, 5}});
        test &= test(new Integer[]{1, 2, 3}, 5, new Integer[][]{});
        test &= test(new Integer[]{1, 2}, 0, new Integer[][]{});
        test &= test(new Integer[]{}, 1, new Integer[][]{});

        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] input, int targetSum, Integer[][] expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " targetSum : " + targetSum);
        System.out.println("expected :");
        CommonMethods.print(expected);


        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        SolutionRecursive solution = new SolutionRecursive();
        List<List<Integer>> output = solution.pathSum(root, targetSum);

        boolean result = CommonMethods.equals(expected, output);
        System.out.println("\nOutput : " + output + " Result : " + result);
        return result;

    }

    static class SolutionRecursive {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> pathSum = new ArrayList<>();
            if (root == null)
                return pathSum;

            pathSum(root, pathSum, new ArrayList<>(), targetSum);
            return pathSum;

        }

        public void pathSum(TreeNode root, List<List<Integer>> pathSum, List<Integer> currentPath, int targetSum) {

            if (root == null)
                return;

            currentPath.add(root.val);

            if (root.left == null && root.right == null) {
                if (targetSum == root.val) {
                    pathSum.add(new ArrayList<>(currentPath));

                }
            } else {
                pathSum(root.left, pathSum, currentPath, targetSum - root.val);
                pathSum(root.right, pathSum, currentPath, targetSum - root.val);

            }
            currentPath.remove(currentPath.size() - 1); //remove the current node from the path.
        }
    }
}
