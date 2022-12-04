package DataStructureAlgo.Java.LeetCode.tree;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-11
 * Description: https://leetcode.com/articles/equal-tree-partition/
 * http://leetcode.liangjiateng.cn/leetcode/equal-tree-partition/description
 * 663. Equal Tree Partition
 * <p>
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * *     5
 * *    / \
 * *   10 10
 * *     /  \
 * *    2   3
 * <p>
 * Output: True
 * Explanation:
 * *     5
 * *    /
 * *   10
 * <p>
 * Sum: 15
 * <p>
 * *    10
 * *   /  \
 * *  2    3
 * <p>
 * Sum: 15
 * Example 2:
 * <p>
 * Input:
 * *     1
 * *    / \
 * *   2  10
 * *     /  \
 * *    2   20
 * <p>
 * Output: False
 * Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
 * Note:
 * <p>
 * The range of tree node value is in the range of [-100000, 100000].
 * 1 <= n <= 10000
 * <p>
 * close to {@link BinaryTreeTwoHalvesSameSize}
 */
public class BinaryTreeEqualPartition {

    public static void main(String[] args) {
        test(TreeBuilder.arrayToTree(new Integer[]{0, null, 0}), true);
        test(TreeBuilder.arrayToTree(new Integer[]{5, 10, -10, null, null, -2, -3}), false);
        test(TreeBuilder.arrayToTree(new Integer[]{5, 10, 10, null, null, 2, 3}), true);
        test(TreeBuilder.arrayToTree(new Integer[]{5, 10, 10, null, 2, 1}), false);
        test(TreeBuilder.arrayToTree(new Integer[]{10, 1, 11, 21, null, null, null, null, 9, 9, null, null, 5, null, null}), false);
        test(TreeBuilder.arrayToTree(new Integer[]{10, 1, 9, 11, null, 9, 5, 21}), true);

    }

    private static void test(TreeNode root, boolean expected) {
        System.out.println("\n Input :" + GenericPrinter.preOrder(root) + " expected :" + expected);
        EqualTreePartitionFlatAndCount flatAndCount = new EqualTreePartitionFlatAndCount();
        EqualTreePartitionFlatAndCount2 flatAndCount2 = new EqualTreePartitionFlatAndCount2();
        System.out.println("flatAndCount : " + flatAndCount.checkEqualTree(root));
        System.out.println("flatAndCount2 : " + flatAndCount2.checkEqualTree(root));

    }
}

/**
 * We find the sum of nodes in tree, say 'sum'. if sum is even then only we can partition it.
 * Flat the tree in list/array of some. Find the point at which we got sum/2.
 */
class EqualTreePartitionFlatAndCount {

    public boolean checkEqualTree(TreeNode root) {

        if (null == root)
            return true;

        List<Integer> sumList = new ArrayList<>();

        int sum = sumTree(root, sumList);
        if (sum % 2 != 0)
            return false;

        int currSum = 0;
        for (int i = 0; i < sumList.size(); i++) {
            int e = sumList.get(i);
            currSum += e;

            if (currSum == sum / 2 && i + 1 != sumList.size())
                return true;
        }

        return false;
    }

    private int sumTree(TreeNode root, List<Integer> sumList) {
        if (null == root)
            return 0;


        int l = sumTree(root.left, sumList);
        sumList.add(root.val);
        int r = sumTree(root.right, sumList);

        return root.val + l + r;

    }

}


class EqualTreePartitionFlatAndCount2 {

    public boolean checkEqualTree(TreeNode root) {

        if (null == root)
            return true;

        Stack<Integer> sumList = new Stack<>();

        int sum = sumTree(root, sumList);
        if (sum % 2 != 0)
            return false;

        sumList.pop();
        int currSum = 0;
        while (!sumList.isEmpty()) {
            currSum = sumList.pop();
            if (currSum == sum / 2)
                return true;
        }

        return false;
    }

    private int sumTree(TreeNode root, Stack<Integer> sumList) {
        if (null == root)
            return 0;

        sumList.push(root.val + sumTree(root.left, sumList) + sumTree(root.right, sumList));
        return sumList.peek();

    }

}
