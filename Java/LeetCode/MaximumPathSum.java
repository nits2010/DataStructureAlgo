package Java.LeetCode;

import Java.Tree.TreeNode;
import javafx.util.Pair;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description:
 */
public class MaximumPathSum {

    public static int maximumPathSum(Java.Tree.TreeNode root) {
        MaximumPathSumAnyNode sum = new MaximumPathSumAnyNode();
        return sum.maximumPathSumAnyNode(root);
    }

    public static int maximumPathSumLeafToLeaf(Java.Tree.TreeNode root) {
        MaximumPathSumLeafToLeaf sum = new MaximumPathSumLeafToLeaf();
        return sum.maximumPathSumLeafToLeaf(root);
    }

    public static int maximumEvenPathSum(Java.Tree.TreeNode root) {
        MaximumEvenPathSum sum = new MaximumEvenPathSum();
        return sum.maximumEvenPathSum(root);
    }


}


/**
 * https://www.careercup.com/question?id=5074469692375040
 * {{{
 * It should be post order traversal with below way of cal
 * <p>
 * If leaf
 * =>Item: odd
 * .      Return ( 0, value)
 * => item : even
 * .      Return ( value, 0)
 * <p>
 * If not leaf
 * => item even
 * // through both child's creating max subtree
 * .    max = MAX ( max, MAX(left.even+ right.even, left.odd+ right.odd) + value)
 * <p>
 * return (
 * even-> MAX ( MAX ( left.even, right.even) + value, value),
 * odd ->( left.odd, right.odd) + value )
 * )
 * <p>
 * => item odd
 * // through both child's creating max subtree
 * .    max = MAX ( max, left.odd +  right.odd + value)
 * <p>
 * return (
 * even-> ( left.odd, right.odd) + value),
 * odd -> MAX ( left.even, right.eve) + value, value )
 * )
 * <p>
 * if any situation the value at odd position is even, then put 0 instead
 * Like tree
 * 6 here even sum is not possible (6+2 = even, 6+4= even, 2+4+6 = even) not possible odd sum( 10,0)
 * 2  4
 * <p>
 * }}}
 */

class MaximumEvenPathSum {

    int maximumEvenPathSum(TreeNode<Integer> root) {

        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;

        maximumEvenPathSum(root, max);

        return max[0];
    }

    //Pair <even, odd>
    private Pair<Integer, Integer> maximumEvenPathSum(TreeNode<Integer> root, int[] max) {

        //its null
        if (root == null) {
            return new Pair<>(0, 0);
        }

        if (isLeaf(root)) {

            //if odd
            if (root.getData() % 2 != 0) {
                return new Pair<>(0, root.getData());
            } else //if even
                return new Pair<>(root.getData(), 0);
        }

        Pair<Integer, Integer> left = maximumEvenPathSum(root.getLeft(), max);
        Pair<Integer, Integer> right = maximumEvenPathSum(root.getRight(), max);

        //if parent is Even
        if (root.getData() % 2 == 0) {
            /**
             * * => item even
             *      * // through both child's creating max subtree
             *      * .    max = MAX ( max, MAX(left.even+ right.even, left.odd+ right.odd) + value)
             *      * <p>
             *      * return (
             *      * even-> MAX ( MAX ( left.even, right.even) + value, value),
             *      * odd ->( left.odd, right.odd) + value )
             *      * )
             *      * <p>

             */


            // max = MAX ( max, MAX(left.even+ right.even, left.odd+ right.odd) + value)
            max[0] = Math.max(max[0], Math.max(left.getKey() + right.getKey(), left.getValue() + right.getValue()) + root.getData());

            //even-> MAX ( MAX ( left.even, right.even) + value, value),
            int even = Math.max(Math.max(left.getKey(), right.getKey()) + root.getData(), root.getData());

            //odd ->( left.odd, right.odd) + value )
            int odd = Math.max(left.getValue(), right.getValue()) + root.getData();

            //if odd become even, means this sub-tree has only even nodes
            if (odd % 2 == 0)
                odd = 0;

//            if (even % 2 != 0)
//                even = 0;

            return new Pair<>(even, odd);

        } else {

            /**
             *      * => item odd
             *      * // through both child's creating max subtree
             *      * .    max = MAX ( max, left.odd +  right.odd + value)
             *      * <p>
             *      * return (
             *      * even-> ( left.odd, right.odd) + value),
             *      * odd -> MAX ( left.even, right.eve) + value, value )
             *      * )
             */


            // max = MAX ( max, (left.odd +  right.odd + value))
            max[0] = Math.max(max[0], left.getValue() + right.getValue() + root.getData());

            int even = root.getData() + Math.max(left.getValue(), right.getValue());
            int odd = Math.max(root.getData(), Math.max(left.getKey(), right.getKey()) + root.getData());

            //Not require
//            if (odd % 2 == 0)
//                odd = 0;

            //if even become odd, means this sub-tree has only 0 value nodes
            if (even % 2 != 0)
                even = 0;

            return new Pair<>(even, odd);

        }

    }

    boolean isLeaf(TreeNode root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null))
            return true;
        return false;
    }

}


/**
 * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
 * Given a binary tree in which each node element contains a number. Find the maximum possible sum from one leaf node to another.
 * The maximum sum path may or may not go through root. For example, in the following binary tree,
 * the maximum sum is 27(3 + 6 + 9 + 0 – 1 + 10). Expected time complexity is O(n).
 * <p>
 * If one side of root is empty, then function should return minus infinite (INT_MIN in case of C/C++)
 * <p>
 * tree
 */
class MaximumPathSumLeafToLeaf {
    /**
     * @param root
     * @return
     */
    int maximumPathSumLeafToLeaf(TreeNode root) {

        if (null == root)
            return 0;

        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;

        maximumPathSumLeafToLeaf(root, max);

        return max[0];
    }

    int maximumPathSumLeafToLeaf(TreeNode<Integer> root, int max[]) {

        if (root == null)
            return 0;

        if (isLeaf(root))
            return root.getData();

        int mL = maximumPathSumLeafToLeaf(root.getLeft(), max);
        int mR = maximumPathSumLeafToLeaf(root.getRight(), max);


        max[0] = Math.max(max[0], mL + mR + root.getData());

        return Math.max(mL, mR) + root.getData();


    }


    boolean isLeaf(TreeNode root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null))
            return true;
        return false;
    }
}

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Output: 6
 * Example 2:
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * Output: 42
 */
class MaximumPathSumAnyNode {

    int maximumPathSumAnyNode(Java.Tree.TreeNode root) {
        if (root == null)
            return 0;

        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        maximumPathSumAnyNode(root, max);
        return max[0];
    }

    private int maximumPathSumAnyNode(TreeNode<Integer> root, int max[]) {
        if (root == null) {
            return 0;
        }

        int l = maximumPathSumAnyNode(root.getLeft(), max);
        int r = maximumPathSumAnyNode(root.getRight(), max);

        // compare leftSum + root val or rightSum + root val to select which value to send further
        int oneChildMax = Math.max(l, r) + root.getData();

        // if the root value is greater than any of the path
        int toReturn = Math.max(oneChildMax, root.getData());

        // if root + leftSum + rightSum is greater than any of the max till now, update the final max (though we are updating the final max, we shouldn't be propagating this further as only one of left or right path is the valid selection)
        int bothChildMax = Math.max(toReturn, root.getData() + l + r);
        max[0] = Math.max(bothChildMax, max[0]);

        // return the max sum till the current node that can be processed further
        return toReturn;
    }

}