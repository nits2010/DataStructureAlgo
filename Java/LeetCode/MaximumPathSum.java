package Java.LeetCode;

import Java.Tree.MinMaxObject;
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

        MinMaxObject<Integer> max = new MinMaxObject<>(Integer.MIN_VALUE);

        maximumEvenPathSum(root, max);

        return max.data;
    }

    //Pair <even, odd>
    private Pair<Integer, Integer> maximumEvenPathSum(TreeNode<Integer> root, MinMaxObject<Integer> max) {

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


            max.data = Math.max(max.data, Math.max(left.getKey() + right.getKey(), left.getValue() + right.getValue()) + root.getData());

            int even = Math.max(Math.max(left.getKey(), right.getKey()) + root.getData(), root.getData());
            int odd = Math.max(left.getValue(), right.getValue()) + root.getData();

            if (odd % 2 == 0)
                odd = 0;

            if (even % 2 != 0)
                even = 0;

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


            max.data = Math.max(max.data, left.getValue() + right.getValue() + root.getData());

            int even = root.getData() + Math.max(left.getValue(), right.getValue());
            int odd = Math.max(root.getData(), Math.max(left.getKey(), right.getKey()) + root.getData());

            if (odd % 2 == 0)
                odd = 0;
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

//https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
class MaximumPathSumLeafToLeaf {
    /**
     * @param root
     * @return
     */
    int maximumPathSumLeafToLeaf(TreeNode root) {

        if (null == root)
            return 0;

        MinMaxObject<Integer> max = new MinMaxObject<>();
        max.data = Integer.MIN_VALUE;

        maximumPathSumLeafToLeaf(root, max);

        return max.data;
    }

    int maximumPathSumLeafToLeaf(TreeNode<Integer> root, MinMaxObject<Integer> max) {

        if (root == null)
            return 0;

        if (isLeaf(root))
            return root.getData();

        int mL = maximumPathSumLeafToLeaf(root.getLeft(), max);
        int mR = maximumPathSumLeafToLeaf(root.getRight(), max);


        max.data = Math.max(max.data, mL + mR + root.getData());

        return Math.max(mL, mR) + root.getData();


    }


    boolean isLeaf(TreeNode root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null))
            return true;
        return false;
    }
}

//https://leetcode.com/problems/binary-tree-maximum-path-sum/
class MaximumPathSumAnyNode {

    int maximumPathSumAnyNode(Java.Tree.TreeNode root) {
        if (root == null)
            return 0;

        MinMaxObject<Integer> max = new MinMaxObject();
        max.data = Integer.MIN_VALUE;
        maxPathSumHelper(root, max);
        return max.data;
    }

    private int maxPathSumHelper(TreeNode<Integer> root, MinMaxObject<Integer> max) {
        if (root == null) {
            return 0;
        }

        int l = maxPathSumHelper(root.getLeft(), max);
        int r = maxPathSumHelper(root.getRight(), max);

        // compare leftSum + root val or rightSum + root val to select which value to send further
        int oneChildMax = Math.max(l, r) + root.getData();

        // if the root value is greater than any of the path
        int toReturn = Math.max(oneChildMax, root.getData());

        // if root + leftSum + rightSum is greater than any of the max till now, update the final max (though we are updating the final max, we shouldn't be propagating this further as only one of left or right path is the valid selection)
        int bothChildMax = Math.max(toReturn, root.getData() + l + r);
        max.data = Math.max(bothChildMax, max.data);

        // return the max sum till the current node that can be processed further
        return toReturn;
    }

}