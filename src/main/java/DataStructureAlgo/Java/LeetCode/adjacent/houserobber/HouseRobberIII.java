package DataStructureAlgo.Java.LeetCode.adjacent.houserobber;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.helpers.templates.TreeNode;
import  DataStructureAlgo.Java.LeetCode.tree.TreeBuilder;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description: https://leetcode.com/problems/house-robber-iii/
 * <p>
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3,null,3,null,1]
 * <p>
 * *      3
 * *     / \
 * *    2   3
 * *     \   \
 * *      3   1
 * <p>
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * <p>
 * Input: [3,4,5,1,3,null,1]
 * <p>
 * *      3
 * *     / \
 * *    4   5
 * *   / \   \
 * *  1   3   1
 * <p>
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {

    public static void main(String[] args) {
        test(TreeBuilder.arrayToTree(new Integer[]{3, 2, 3, null, 3, null, 1}), 7);
        test(TreeBuilder.arrayToTree(new Integer[]{3, 4, 5, 1, 3, null, 1}), 9);
        test(TreeBuilder.arrayToTree(new Integer[]{3, 2, 3, null, 3, null, 1, 11, 34, 224, null, null, 872}), 1135);

    }

    private static void test(TreeNode root, int expected) {
        System.out.println("\n Input " + GenericPrinter.preOrder(root) + " expected :" + expected);

        HouseRobberIIIPostOrder postOrder = new HouseRobberIIIPostOrder();
        System.out.println("Max rob :" + postOrder.rob(root));
    }


}

class HouseRobberIIIPostOrder {
    public int rob(TreeNode root) {

        if (root == null)
            return 0;

        int maxRob[] = maxRob(root);

        return Math.max(maxRob[0], maxRob[1]);
    }

    private int[] maxRob(TreeNode root) {

        if (root == null)
            return new int[]{0, 0};

        if (root.left == null && root.right == null)
            return new int[]{root.val, 0};

        int[] left = maxRob(root.left);
        int[] right = maxRob(root.right);

        int include = root.val + left[1] + right[1];
        int exclude = left[0] + right[0];

        return new int[]{include, exclude};
    }
}
