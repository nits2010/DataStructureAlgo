package Java.LeetCode.tree;

import Java.HelpersToPrint.GenericPrinter;
import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-11
 * Description: https://www.geeksforgeeks.org/number-of-ways-to-divide-a-binary-tree-into-two-halves/
 * Number of ways to divide a Binary tree into two halves
 * <p>
 * Given a binary tree, the task is to count the number of ways to remove a single edge from the tree such that the tree
 * gets divided into two halves with equal sum.
 * <p>
 * Examples:
 * <p>
 * Input:
 * *           1
 * *         /   \
 * *       -1     -1
 * *                \
 * *                 1
 * Output: 1
 * Only way to do this will be to remove the edge from the right of the root.
 * After that we will get 2 sub-trees with sum = 0.
 * *    1
 * *   /
 * * -1
 * <p>
 * and
 * <p>
 * * -1
 * *   \
 * *    1
 * will be the two sub-trees.
 * <p>
 * Input:
 * *           1
 * *         /   \
 * *       -1     -1
 * *                \
 * *                 -1
 * Output: 2
 * Extension of {@link BinaryTreeEqualPartition}
 */
public class NumberOfBinaryTreeEqualSumPartition {

    public static void main(String[] args) {
        /**
         * *           1
         * *         /   \
         * *       -1     -1
         * *                \
         * *                 1
         */
        test(TreeBuilder.arrayToTree(new Integer[]{1, -1, -1, null, null, null, 1}), 1);

        /**
         * *           1
         * *         /   \
         * *       -1     -1
         * *                \
         * *                 -1
         *
         * output
         *       * *                   1 <- [left =-1 , right=-1 (include) ]
         *          * *                 \
         *          * *       -1        -1
         *          * *                  \
         *          * *                  -1
         *
         * And *       * *                  1
         *          *          * *         /   \
         *          *          * *       -1     -1 <- [left =-1(include) , right= -1 ]
         *          *          * *
         *
         *          *          * *                 -1
         */

        test(TreeBuilder.arrayToTree(new Integer[]{1, -1, -1, null, null, null, -1}), 2);
    }

    private static void test(TreeNode root, int expected) {
        System.out.println("\n Tree :" + GenericPrinter.preOrder(root) + " expected :" + expected);
        System.out.println("Obtained :" + partitions(root));
    }

    /**
     * To find number of partition, we need to know how many ways we can partition an array of integers. Providing partition is only when one partition sum is half of overall
     * TO find a index is potential partition, we need to see what is the sum either left side or right side is equal to half of the sum or not.
     * <p>
     * Algo:
     * 1. Compute the overall sum; if odd sum return 0
     * 2. See for each node, is it a partition point by seeing left and right. Whenever find, increase the number
     *
     * @param root
     * @return
     */
    public static int partitions(TreeNode root) {

        if (null == root)
            return 0;

        final int sum = sum(root);

        if (sum % 2 != 0)
            return 0;


        int partitions[] = {0};

        partitions(root, sum, partitions);

        return partitions[0];


    }

    private static int partitions(TreeNode root, int sum, int[] partitions) {
        if (root == null)
            return 0;

        int left = 0, right = 0;
        if (root.left != null) {
            left = partitions(root.left, sum, partitions);

            if (left == sum / 2)
                partitions[0]++;
        }

        if (root.right != null) {
            right = partitions(root.right, sum, partitions);

            if (right == sum / 2)
                partitions[0]++;
        }

        return left + right + root.val;
    }

    private static int sum(TreeNode root) {
        if (null == root)
            return 0;

        return root.val + sum(root.left) + sum(root.right);

    }
}
