package DataStructureAlgo.Java.LeetCode2025.medium.Tree;

import DataStructureAlgo.Java.LeetCode2025.hard.Tree.VerticalOrderTraversalOfABinaryTree_987;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/19/2024
 * Question Category: [easy | medium | hard ]
 * Description:  https://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/
 * Given a Binary Tree, find the vertical sum of the nodes that are in the same vertical line. Print all sums through different vertical lines.
 * <p>
 * Examples:
 * <p>
 * 1
 * /   \
 * 2      3
 * / \    / \
 * 4   5  6   7
 * The tree has 5 vertical lines
 * <p>
 * Vertical-Line-1 has only one node 4 => vertical sum is 4
 * Vertical-Line-2: has only one node 2=> vertical sum is 2
 * Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12
 * Vertical-Line-4: has only one node 3 => vertical sum is 3
 * Vertical-Line-5: has only one node 7 => vertical sum is 7
 * So expected output is 4, 2, 12, 3 and 7
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.hard.Tree.VerticalOrderTraversalOfABinaryTree_987}
 * <p>
 * Tags
 * -----
 * <p>
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */
public class VerticalOrderSumOfABinaryTree {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{1,2,3,4,5,6,7}, List.of(4,2,12,3,7));
        System.out.println(test ? " All Passed " : " Something Failed ");
    }

    private static boolean test(Integer[] input, List<Integer> expected) {

        System.out.println("=========================================");
        System.out.println("Input :" + Arrays.toString(input) + "\nExpected :" + expected);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        Solution solution = new Solution();
        List<Integer> output = solution.verticalOrderSum(root);

        boolean testResult = CommonMethods.equalsValues(output, expected);
        System.out.println("Output : " + output + " Test Result " + (testResult ? " Passed " : " Failed "));
        return testResult;

    }

    static class Solution {
        public List<Integer> verticalOrderSum(TreeNode root) {
            final List<Integer> verticalOrderSum = new LinkedList<>();
            if (root == null)
                return verticalOrderSum;

            final Map<Integer, Integer> verticalVsSum = new HashMap<>();
            int[] col = {Integer.MAX_VALUE, Integer.MIN_VALUE};

            verticalOrderSum(root, verticalVsSum, 0, col);

            for(int width = col[0]; width<=col[1]; width++){
                if(verticalVsSum.containsKey(width))
                    verticalOrderSum.add(verticalVsSum.get(width));
            }

            return verticalOrderSum;
        }

        private void verticalOrderSum(TreeNode root, final Map<Integer, Integer> verticalVsSum, int column, int[] col) {
            if (root == null)
                return;

            verticalVsSum.put(column, verticalVsSum.getOrDefault(column, 0) + root.val);
            col[0] = Math.min(column, col[0]);
            col[1] = Math.max(column, col[1]);
            verticalOrderSum(root.left, verticalVsSum, column -1, col);
            verticalOrderSum(root.right, verticalVsSum, column + 1, col);

        }
    }

}
