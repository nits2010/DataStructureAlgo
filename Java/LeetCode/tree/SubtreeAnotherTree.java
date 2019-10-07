package Java.LeetCode.tree;

import Java.helpers.GenericPrinter;
import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 22/09/19
 * Description: https://leetcode.com/problems/subtree-of-another-tree/
 * 572. Subtree of Another Tree [Easy]
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 * Example 1:
 * Given tree s:
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * Given tree t:
 * *    4
 * *   / \
 * *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * *     /
 * *    0
 * Given tree t:
 * *    4
 * *   / \
 * *  1   2
 * Return false.
 * https://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
 * <p>
 * [Amazon]
 */
public class SubtreeAnotherTree {

    public static void main(String[] args) {
        boolean test = true;

        /**
         * *      1
         * *     /
         * *    2
         * Given tree t:
         * *    2
         */
        test &= test(TreeBuilder.arrayToTree(new Integer[]{1, 2, null}),
                TreeBuilder.arrayToTree(new Integer[]{2}), true);

        /**
         * *      3
         * *     / \
         * *    4   5
         * *   / \
         * *  1   2
         * Given tree t:
         * *    4
         * *   / \
         * *  1   2
         */
        test &= test(TreeBuilder.arrayToTree(new Integer[]{3, 4, 5, 1, 2}),
                TreeBuilder.arrayToTree(new Integer[]{4, 1, 2}), true);

        /**
         Given tree s:
         * *      3
         * *     / \
         * *    4   5
         * *   / \
         * *  1   2
         * *     /
         * *    0
         * Given tree t:
         * *    4
         * *   / \
         * *  1   2
         */
        test &= test(TreeBuilder.arrayToTree(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0}),
                TreeBuilder.arrayToTree(new Integer[]{4, 1, 2}), false);

        /**
         *  Tree1
         **           1
         **         /    \
         **       2       3
         **      /
         **     4
         *
         *
         *         Tree2
         **           1
         **         /    \
         **       2       3
         **      /         \
         **     4            5
         */
        test &= test(TreeBuilder.arrayToTree(new Integer[]{1, 2, 3, 4, null}),
                TreeBuilder.arrayToTree(new Integer[]{1, 2, 3, 4, null, null, 5}), false);

        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(TreeNode s, TreeNode t, boolean expected) {
        System.out.println("\nS: " + GenericPrinter.preOrder(s));
        System.out.println("T: " + GenericPrinter.preOrder(t));
        System.out.println("Expected            :" + expected);

        boolean recursive = new SubtreeAnotherTreeRecursiveMatch().isSubtree(s, t);
        boolean subString = new SubtreeAnotherTreeSubStringMatch().isSubtree(s, t);
        boolean serialize = new SubtreeAnotherTreeSubStringMatchSerialize().isSubtree(s, t);
        System.out.println("recursive           :" + recursive);
        System.out.println("subString           :" + subString);
        System.out.println("serialize           :" + serialize);

        return GenericPrinter.equalsValues(recursive, expected, subString, serialize);
    }
}

class SubtreeAnotherTreeRecursiveMatch {

    /**
     * To test tree T is sub-tree of S, then T should be exactly present in S.
     * i.e. we can find the root where T's root start and test further
     * <p>
     * Time complexity:  O(m*n)  In worst case(skewed tree) traverse function takes O(m*n)
     * Space complexity:  O(n)  The depth of the recursion tree can go upto n refers to the number of nodes in
     *
     * @param s
     * @param t
     * @return if t is subtree of s
     * Runtime: 6 ms, faster than 93.17% of Java online submissions for Subtree of Another Tree.
     * Memory Usage: 40.1 MB, less than 97.78% of Java online submissions for Subtree of Another Tree.
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (s == null)
            return t == null;

        if (t == null)
            return false;

        if (equals(s, t)) //if tree T same as S
            return true;

        //otherwise test on left or right side
        return isSubtree(s.left, t) || isSubtree(s.right, t);


    }

    //O(m*n)
    private boolean equals(TreeNode s, TreeNode t) {
        if (s == null)
            return t == null;

        if (t == null)
            return false;

        return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
    }
}

/**
 * If we take any traversal of given tree T and S and store those traversal in String Ts, Ss.
 * If T is sub-tree of S than Ts should be a sub-string of Ss.
 * Since left and right node could be null, that may lead to wrong answer. We need to pull null for every left/right null.
 * <p>
 * Example
 * Given tree s:
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * <p>
 * Given tree t:
 * *    4
 * *   / \
 * *  1   2
 * <p>
 * Pre-order
 * Ss = [#, 3, #, 4,  # 1,  null,   null,   #, 2,  null,   null  #, 5,  null,   null]
 * Ts = [#, 4, #, 1, null, null, #, 2, null, null ]
 * Clearly, Ts is sub-string of S started at index 1
 * <p>
 * Complexity: Each tree has n nodes has n-1 null nodes. That makes both sub-string of size m^2 and n^2 where m and n are size of tree S and T.
 * Contains take O(m^2) time.
 * <p>
 * O(m^2 + n^2 + m^2)
 * <p>
 * Runtime: 5 ms, faster than 96.68% of Java online submissions for Subtree of Another Tree.
 * Memory Usage: 44.4 MB, less than 6.67% of Java online submissions for Subtree of Another Tree.
 */
class SubtreeAnotherTreeSubStringMatch {


    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return t == null;

        if (t == null)
            return false;

        StringBuilder sPreOrder = new StringBuilder();
        StringBuilder tPreOrder = new StringBuilder();

        preOrder(s, sPreOrder);
        preOrder(t, tPreOrder);

        return sPreOrder.toString().contains(tPreOrder.toString());

    }

    private void preOrder(TreeNode s, StringBuilder preOrder) {
        if (s == null) {
            preOrder.append("null");
            return;
        }

        preOrder.append("#").append(s.val);
        preOrder(s.left, preOrder);
        preOrder(s.right, preOrder);
    }
}


/**
 * Same as above, in different way
 * Runtime: 5 ms, faster than 96.68% of Java online submissions for Subtree of Another Tree.
 * Memory Usage: 40.2 MB, less than 97.78% of Java online submissions for Subtree of Another Tree
 */
class SubtreeAnotherTreeSubStringMatchSerialize {


    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return t == null;

        if (t == null)
            return false;

        StringBuilder sPreOrder = new StringBuilder();
        StringBuilder tPreOrder = new StringBuilder();

        serialize(s, sPreOrder);
        serialize(t, tPreOrder);

        return sPreOrder.toString().contains(tPreOrder.toString());

    }

    private void serialize(TreeNode root, StringBuilder preOrder) {
        if (root == null) {
            preOrder.append(",#");
            return;
        }
        preOrder.append(",").append(root.val);
        serialize(root.left, preOrder);
        serialize(root.right, preOrder);
    }
}