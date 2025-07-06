package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming.HouseRobber._337;

import DataStructureAlgo.Java.LeetCode.tree.TreeBuilder;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Knapsack;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/22/2024
 * Question Category: 337. House Robber III
 * Description: https://leetcode.com/problems/house-robber-iii/
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * <p>
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * *      3
 * *     / \
 * *    2   3
 * *     \   \
 * *      3   1
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * *      3
 * *     / \
 * *    4   5
 * *   / \   \
 * *  1   3   1
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * Example :
 * *         4
 * *        /
 * *       1
 * *      /
 * *     2
 * *    /
 * *   3
 * Input: root = [4,1,null,2,null,3]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 4 + 3 = 7.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 104
 * File references
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.adjacent.houserobber.HouseRobberIII}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @DynamicProgramming
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google
 * @Adobe
 * @Facebook
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class HouseRobberIII_337 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{4, 1, null, 2, null, 3}, 7);
        test &= test(new Integer[]{3, 2, 3, null, 3, null, 1}, 7);
        test &= test(new Integer[]{3, 4, 5, 1, 3, null, 1}, 9);
        test &= test(new Integer[]{3, 2, 3, null, 3, null, 1, 11, 34, 224, null, null, 872}, 1135);
        CommonMethods.printAllTestOutCome(test);

    }

    private static boolean test(Integer[] nums, int expected) {
        System.out.println("-------------------------------------------------");
        System.out.println("Nums : " + Arrays.toString(nums) + " | Expected : " + expected);
        TreeNode root = TreeBuilder.arrayToTree(nums);
        System.out.println("Level Order traversal of tree : " + TreeTraversalRecursive.levelOrderWithNull(root));

        Solution sol = new Solution();
        int actual = sol.rob(root);
        boolean pass = actual == expected;
        System.out.println("Actual : " + actual + " Pass : " + (pass ? "PASS" : "FAIL"));
        System.out.println("-------------------------------------------------");
        return pass;
    }

    /**
     * Approach
     */
    static class Solution {

        static class Pair {
            int exclude, include;

            Pair(int e, int i) {
                this.exclude = e;
                this.include = i;
            }
        }

        public int rob(TreeNode root) {
            Pair out = robPostOrder(root);
            //either excluding the root or including the root
            return Math.max(out.exclude, out.include);

        }

        public Pair robPostOrder(TreeNode root) {

            if (root == null)
                return new Pair(0, 0);

            //if this is a child node, then exclude = 0, include = root.val
            if (root.left == null && root.right == null)
                return new Pair(0, root.val);

            //do postorder dfs
            Pair left = robPostOrder(root.left);
            Pair right = robPostOrder(root.right);


            //if we are including current root, then we have to exclude the left and right subtree
            int include = root.val + left.exclude + right.exclude;

            //if we are excluding the current root, then we have to choose the max subtree from both left and right and sum them for choosing left and right.
            int exclude = Math.max(left.exclude, left.include) + Math.max(right.exclude, right.include);

            return new Pair(exclude, include);


        }
    }
}
