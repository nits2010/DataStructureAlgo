package Java.LeetCode.tree;

import Java.LeetCode.templates.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-20
 * Description: https://leetcode.com/problems/binary-tree-paths/
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * Output: ["1->2->5", "1->3"]
 * <p>
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class AllRootToLeafPathBinaryTree {

    public static void main(String []args) {
        test(getTree1());
        test(getTree2());
    }

    private static void test(TreeNode root) {
        Paths paths = new Paths();
        System.out.println(paths.binaryTreePaths(root));
        System.out.println(paths.binaryTreePathsWay2(root));
    }

    private static TreeNode getTree1() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(9);
        return root;
    }


    private static TreeNode getTree2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        return root;
    }


}

class Paths {

    public List<String> binaryTreePaths(TreeNode root) {

        return binaryTreePathStackFrame(root);


    }

    private List<String> binaryTreePathStackFrame(TreeNode root) {
        List<String> paths = new ArrayList<>();

        binaryTreePaths(root, "", paths);
        return paths;
    }

    //Keep a stack frame variable "s" and do pre-order traversal and keep adding the root till you reach a leaf.
    private void binaryTreePaths(TreeNode root, String s, List<String> paths) {

        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (!s.isEmpty())
                s = s + "->" + root.val;
            else
                s = s + root.val;


            paths.add(s);
            return;
        }

        if (s.isEmpty())
            s = s + root.val;
        else
            s = s + "->" + root.val;

        binaryTreePaths(root.left, s, paths);
        binaryTreePaths(root.right, s, paths);
    }

    //Solving within
    public List<String> binaryTreePathsWay2(TreeNode root) {

        List<String> paths = new ArrayList<>();

        //If this is empty tree, return empty path list
        if (root == null) {
            return paths;
        }

        //if this is leaf, then add the path
        if (root.left == null && root.right == null) {
            paths.add("" + root.val);
            return paths;
        }


        //recur on left; find left paths
        List<String> left = binaryTreePathsWay2(root.left);
        //recur on right; find right paths
        List<String> right = binaryTreePathsWay2(root.right);

        //Merge left paths
        for (String s : left) {
            paths.add(root.val + "->" + s);
        }


        //Merge right paths
        for (String s : right) {
            paths.add(root.val + "->" + s);
        }


        return paths;
    }


}
