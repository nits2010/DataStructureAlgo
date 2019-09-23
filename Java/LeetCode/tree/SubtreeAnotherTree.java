package Java.LeetCode.tree;

import Java.HelpersToPrint.GenericPrinter;
import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 22/09/19
 * Description: https://leetcode.com/problems/subtree-of-another-tree/
 * 572. Subtree of Another Tree
 * Easy
 * <p>
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 * <p>
 * Example 1:
 * Given tree s:
 * <p>
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
 * <p>
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
 * <p>
 * https://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
 */
public class SubtreeAnotherTree {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(TreeBuilder.arrayToTree(new Integer[]{3, 4, 5, 1, 2}), TreeBuilder.arrayToTree(new Integer[]{4, 1, 2}), true);

        test &= test(TreeBuilder.arrayToTree(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0}), TreeBuilder.arrayToTree(new Integer[]{4, 1, 2}), false);

        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(TreeNode s, TreeNode t, boolean expected) {
        System.out.println("\nS: " + GenericPrinter.preOrder(s));
        System.out.println("T: " + GenericPrinter.preOrder(t));
        System.out.println("Expected:" + expected);
        boolean obtained = new SubtreeAnotherTreeRecursiveMatch().isSubtree(s, t);
        System.out.println("Obtained:" + obtained);

        return obtained == expected;
    }
}

class SubtreeAnotherTreeRecursiveMatch {

    /**
     * To test tree T is sub-tree of S, then T should be exactly present in S.
     * i.e. we can find the root where T's root start and test further
     * Time complexity:  O(m*n)  In worst case(skewed tree) traverse function takes O(m*n)
     * Space complexity:  O(n)  The depth of the recursion tree can go upto n refers to the number of nodes in
     *
     * @param s
     * @param t
     * @return if t is subtree of s
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


class SubtreeAnotherTreeSubStringMatch {

}