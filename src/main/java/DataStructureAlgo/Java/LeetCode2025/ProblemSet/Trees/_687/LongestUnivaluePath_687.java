package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._687;

import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/23/2024
 * Question Category:687. Longest Univalue Path
 * Description: https://leetcode.com/problems/longest-univalue-path/description
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 * Example 2:
 *
 *
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.LongestUniValuePath}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Google
 *
 * @Editorial
 */
public class LongestUnivaluePath_687 {

    public static void main(String[] args) {
        boolean test = true;

        test &= test(new Integer[]{5,4,5,1,1,null,5}, 2);
        test &= test(new Integer[]{1,4,5,4,4,null,5}, 2);
        test &= test(new Integer[]{1,null,1,1,1,1,1,1}, 4);


        System.out.println("==========================");
        System.out.println("\nTest result = " + (test ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, int expected) {
        System.out.println("------------------------------");
        System.out.println("Input : "+ Arrays.toString(input) + "\nexpected : "+ expected);


        Solution solution = new Solution();
        int output = solution.longestUnivaluePath(TreeBuilder.buildTreeFromLevelOrder(input));

        boolean testResult = output == expected;
        System.out.println("output : "+ output + " testResult "+(testResult ? "Pass" : "Fail"));

        return testResult;
    }

    /**
     * We need to count the edges between the univalue path. THere is no edge to self-node, hence the unique path of each self-node would be 0;
     * We can start doing a post-order traversal,
     * 1. and if this is a leaf node, then its unique path length would be 0
     * 2. if this is not a leaf node, then we can compare its value to either left child or right child or both; for each equal value, it gains 1 length path.
     * 3. return maximum of either left or right to parent, as this will be a maximum edge we can add.
     * 4. Keep track of the maximum
     */
    static class Solution {
        public int longestUnivaluePath(TreeNode root) {
            if(root == null) return 0;

            int []max = {0};
            longestUnivaluePath(root, max);
            return max[0];

        }

        private int longestUnivaluePath(TreeNode root, int[]max){
            if(root == null || (root.right == null && root.left == null)) return 0;

            int leftUniValuePathLength = longestUnivaluePath(root.left, max);
            int rightUniValuePathLength = longestUnivaluePath(root.right, max);

            int leftPathLength = 0, rightPathLength = 0;
            if(root.left!=null && root.val == root.left.val)
                leftPathLength += leftUniValuePathLength + 1;
            if(root.right!=null && root.val == root.right.val)
                rightPathLength += rightUniValuePathLength + 1;

            max[0] = Math.max(max[0], leftPathLength + rightPathLength);
            return Math.max(leftPathLength, rightPathLength);
        }
    }
}
