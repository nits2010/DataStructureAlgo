package DataStructureAlgo.Java.LeetCode2025.medium.Tree.pathSum;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/13/2024
 * Question Category: 112. Path Sum
 * Description: https://leetcode.com/problems/path-sum/description/
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * Example 3:
 *
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree
 *
 *
 * <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Microsoft
 * @Amazon
 * @Oracle
 * @WalmartGlobalTech
 *
 * @Editorial
 */
public class PathSum_112 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1},22, true);
        test &= test(new Integer[]{1,2,3}, 5,false);
        test &= test(new Integer[]{}, 0,false);

        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }
    private static boolean test(Integer[] input, int targetSum, boolean expected){
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " targetSum : "+targetSum);
        System.out.println("expected :" + expected);
        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        Solution solution = new Solution();
        boolean output = solution.hasPathSum(root,targetSum);
        System.out.println("Output : "+output);
        return output == expected;

    }

    static class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {

            if(root==null)
                return false;


            if(root.left == null && root.right == null)
                return targetSum == root.val;

            return (hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val));

        }
    }
}
