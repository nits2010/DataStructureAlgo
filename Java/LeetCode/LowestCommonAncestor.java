package Java.LeetCode;

import Java.HelpersToPrint.Printer;
import Java.LeetCode.templates.TreeNode;
import Java.LeetCode.tree.TreeBuilder;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description:
 * <p>
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * <p>
 * 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * <p>
 * {@link Java.Tree.LowestCommonAncestor}
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        test(TreeBuilder.arrayToTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5}), new TreeNode(2), new TreeNode(8), new TreeNode(6));
        test(TreeBuilder.arrayToTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5}), new TreeNode(2), new TreeNode(4), new TreeNode(2));
        test(TreeBuilder.arrayToTree(new Integer[]{2, 1}), new TreeNode(2), new TreeNode(1), new TreeNode(2));


    }

    private static void test(TreeNode root, TreeNode p, TreeNode q, TreeNode expected) {
        System.out.println(" \n Tree :" + Printer.inOrder(root) + " p : " + p + " q: " + q + " expected :" + expected);

        LowestCommonAncestorBinarySearchTree lcaBinarySearchTree = new LowestCommonAncestorBinarySearchTree();
        System.out.println("BST Rec:" + lcaBinarySearchTree.lowestCommonAncestorRecursive(root, p, q));
        System.out.println("BST Iterative:" + lcaBinarySearchTree.lowestCommonAncestorIterative(root, p, q));

        LowestCommonAncestorBinaryTree lcaBinaryTree = new LowestCommonAncestorBinaryTree();
        System.out.println("BT Rec:" + lcaBinaryTree.lowestCommonAncestorRecursive(root, p, q));
        System.out.println("BT Iterative:" + lcaBinaryTree.lowestCommonAncestorIterative(root, p, q));


    }

}

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is
 * defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a
 * descendant of itself).”
 * <p>
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * Example 1:
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * Note:
 * <p>
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the BST.
 */
class LowestCommonAncestorBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorRecursive(root, p, q);
    }

    /**
     * O(log(n))/O(n)
     * Runtime: 4 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
     * Memory Usage: 36.6 MB, less than 5.10% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {

        if (null == root)
            return null;

        if ((p.val <= root.val && root.val <= q.val) || (q.val <= root.val && root.val <= p.val))
            return root;

        if (p.val > root.val)
            return lowestCommonAncestorRecursive(root.right, p, q);

        return lowestCommonAncestorRecursive(root.left, p, q);
    }

    /**
     * O(log(n))/O(n)
     * Runtime: 4 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
     * Memory Usage: 36.6 MB, less than 5.10% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {

        if (null == root)
            return null;

        while (root != null) {

            if ((p.val <= root.val && root.val <= q.val) || (q.val <= root.val && root.val <= p.val))
                return root;

            if (p.val > root.val)
                root = root.right;
            else
                root = root.left;
        }
        return null;
    }
}

/**
 * * 236. Lowest Common Ancestor of a Binary Tree
 * * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 */
class LowestCommonAncestorBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorRecursive(root, p, q);
    }

    /**
     * Runtime: 5 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * Memory Usage: 35.2 MB, less than 5.55% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;

        if (root.val == p.val)
            return root;

        if (root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestorRecursive(root.left, p, q);
        TreeNode right = lowestCommonAncestorRecursive(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left == null ? right : left;
    }

    /**
     * Algorithm
     * <p>
     * 1. Start from the root node and traverse the tree.
     * 2. Until we find p and q both, keep storing the parent pointers in a dictionary.
     * 3. Once we have found both p and q, we get all the ancestors for p using the parent dictionary and add to a set called ancestors.
     * 4. Similarly, we traverse through ancestors for node q. If the ancestor is present in the ancestors set for p,
     * this means this is the first ancestor common between p and q (while traversing upwards) and hence this is the LCA node.
     * <p>
     * Runtime: 16 ms, faster than 7.58% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * Memory Usage: 35.2 MB, less than 5.55% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;

        if (root.val == p.val)
            return root;

        if (root.val == q.val)
            return root;


        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);

        // 1. Start from the root node and traverse the tree.
        // 2. Until we find p and q both, keep storing the parent pointers in a dictionary.
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);

            }

            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        //3. Once we have found both p and q, we get all the ancestors for p using the parent dictionary and add to a set called ancestors.
        Set<TreeNode> ancestor = new HashSet<>();
        while (p != null) {
            ancestor.add(p);
            p = parent.get(p);
        }

        while (!ancestor.contains(q))
            q = parent.get(q);

        return q;


    }

}