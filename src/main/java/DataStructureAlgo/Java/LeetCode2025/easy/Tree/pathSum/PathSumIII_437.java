package DataStructureAlgo.Java.LeetCode2025.easy.Tree.pathSum;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/14/2024
 * Question Category: 437. Path Sum III
 * Description: https://leetcode.com/problems/path-sum-iii/description/
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
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
 * @medium
 * @Tree
 * @Depth-FirstSearch
 * @BinaryTree <p>
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @tiktok
 * @Google
 * @DoorDash
 * @Editorial
 */
public class PathSumIII_437 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}, 8, 3);
        test &= test(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1}, 22, 3);

        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] input, int targetSum, int expected) {
        System.out.println("-----------------------");
        System.out.println("Input :" + Arrays.toString(input) + " targetSum : " + targetSum);
        System.out.println("expected :" + expected);


        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        Solution solution = new Solution();
        int output = solution.pathSum(root, targetSum);

        boolean result = expected == output;
        System.out.println("\nOutput : " + output + " Result : " + result);
        return result;

    }

    //TODO
    static class Solution {
        int paths = 0;

        public int pathSum(TreeNode root, int targetSum) {

            if (root == null)
                return 0;
            paths = 0;
//            pathsUtil(root, 0, targetSum);
            return paths;
        }


    }
}
