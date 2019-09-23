package Java.LeetCode.tree;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-13
 * Description: https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 * <p>
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
 * while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 * Example 1:
 * <p>
 * Input: label = 14
 * Output: [1,3,4,14]
 * Example 2:
 * <p>
 * Input: label = 26
 * Output: [1,2,6,10,26]
 * <p>
 * *              1
 * *            /   \
 * *          3       2
 * *        /  \     /  \
 * *      4     5   6     7
 * *    / |    /|   |\    | \
 * *  15 14  13 12 11 10  9  8
 */
public class PathZigzagLabelledBinaryTree {

    public static void main(String[] args) {
        test(14);
        test(26);
    }

    private static void test(int label) {
        PathZigzagLabelledBinaryTreeUsingParent parent = new PathZigzagLabelledBinaryTreeUsingParent();
        System.out.println(" Parent :" + parent.pathInZigZagTree(label));

    }
}

/**
 * O(logn) where n is the level of the label
 * Explanation
 * https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/discuss/357749/Java-or-Full-explanation-or-100-beat-in-both
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Path In Zigzag Labelled Binary Tree.
 * Memory Usage: 33.5 MB, less than 100.00% of Java online submissions for Path In Zigzag Labelled Binary Tree.
 */
class PathZigzagLabelledBinaryTreeUsingParent {

    public List<Integer> pathInZigZagTree(int label) {

        if (label <= 0)
            return Collections.EMPTY_LIST;

        if (label == 1)
            return Collections.singletonList(1);


        /**
         * Find the level of this label is;
         * You can compute like 2^n > label; find the least value of 'n'
         */
        int level = (int) (Math.log(label) / Math.log(2)) + 1;
        ;

        List<Integer> path = new ArrayList<>(level + 1);
        path.add(label);

        /**
         * Backtrack from this level to the root
         *
         * O(log(level))
         */
        while (level > 1) {

//            int totalNodes = (int) (Math.pow(2, level) - 1 + Math.pow(2, level - 1));

            /**
             * Find the parent of this label;
             * ................................
             * In normal tree, the parent of any node is calculated by v/2.
             * Since the tree is zig-zag; which parents of every node has push to other side but they have symmetry
             *
             * For example: Node=14
             * For normal tree: 14/2 = 7
             * For zig-zag tree: Nodes just above this node are
             * 4 , 5 , 6 , 7
             * So if it were normal tree, the parent is on 7 while if its zig-zag then its on 4. Which is symmetrical.
             *
             * Hence,
             *
             * Find how many nodes are there at this level.
             * This level labeling would start from Math.pow(2, level - 1))
             * and end at (Math.pow(2, level) - 1
             *
             * Which is equivalent to ((1 << level) - 1) + (1 << (level - 1))
             */
            int totalNodes = ((1 << level) - 1) + (1 << (level - 1));


            //Parent of this label is same as normal tree parent.

            label = (totalNodes - label) / 2;
            path.add(label);
            level--;

        }
        Collections.reverse(path);
        return path;
    }

}