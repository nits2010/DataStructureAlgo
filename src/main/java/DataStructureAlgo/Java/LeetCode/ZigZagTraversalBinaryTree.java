package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-23
 * Description: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
 */
public class ZigZagTraversalBinaryTree {
    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(7);
        tree.left.right = new TreeNode(6);
        tree.right.left = new TreeNode(5);
        tree.right.right = new TreeNode(4);
        System.out.println("Spiral Order traversal of Binary Tree is ");

        usingHeight(tree);
        usingTwoStacks(tree);
        usingTwoStacksSwitch(tree);
        usingDequeue(tree);
    }

    private static void usingDequeue(TreeNode tree) {
        System.out.println(new UsingDequeue().zigzagLevelOrder(tree));
    }

    private static void usingTwoStacksSwitch(TreeNode tree) {
        System.out.println(new UsingTwoStacks().zigzagLevelOrderSwitch(tree));
    }

    private static void usingTwoStacks(TreeNode tree) {
        System.out.println(new UsingTwoStacks().zigzagLevelOrder(tree));
    }

    private static void usingHeight(TreeNode tree) {
        System.out.println(new UsingHeight().zigzagLevelOrder(tree));

    }


}


class UsingHeight {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (null == root)
            return Collections.EMPTY_LIST;


        List<List<Integer>> zigZag = new ArrayList<>();

        int height = height(root);
        boolean leftToRight = true;

        for (int h = 1; h <= height; h++) {
            List<Integer> traversal = getTraversal(root, h, leftToRight);
            zigZag.add(traversal);
            leftToRight = !leftToRight;
        }

        return zigZag;

    }

    private List<Integer> getTraversal(TreeNode root, int h, boolean leftToRight) {
        if (null == root)
            return Collections.EMPTY_LIST;

        List<Integer> zigzag = new ArrayList<>();
        getTraversal(root, h, zigzag, leftToRight);
        return zigzag;
    }

    private void getTraversal(TreeNode root, int h, List<Integer> zigzag, boolean leftToRight) {
        if (root == null)
            return;

        if (h == 1)
            zigzag.add(root.val);
        else if (h > 1) {

            if (leftToRight) {
                getTraversal(root.left, h - 1, zigzag, leftToRight);
                getTraversal(root.right, h - 1, zigzag, leftToRight);
            } else {
                getTraversal(root.right, h - 1, zigzag, leftToRight);
                getTraversal(root.left, h - 1, zigzag, leftToRight);
            }

        }

    }

    private int height(TreeNode root) {
        if (root == null)
            return 0;

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }
}

class UsingTwoStacks {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (null == root)
            return Collections.EMPTY_LIST;


        List<List<Integer>> zigZag = new ArrayList<>();
        Stack<TreeNode> currentStack = new Stack<>();
        Stack<TreeNode> nextStack = new Stack<>();

        currentStack.push(root);
        while (!currentStack.isEmpty() || !nextStack.isEmpty()) {

            List<Integer> zig = new ArrayList<>();
            while (!currentStack.isEmpty()) {

                TreeNode temp = currentStack.pop();
                zig.add(temp.val);

                if (temp.left != null)
                    nextStack.push(temp.left);
                if (temp.right != null)
                    nextStack.push(temp.right);
            }

            if (!zig.isEmpty())
                zigZag.add(zig);
            zig = new ArrayList<>();
            while (!nextStack.isEmpty()) {

                TreeNode temp = nextStack.pop();
                zig.add(temp.val);

                if (temp.right != null)
                    currentStack.push(temp.right);
                if (temp.left != null)
                    currentStack.push(temp.left);
            }
            if (!zig.isEmpty())
                zigZag.add(zig);
        }

        return zigZag;
    }


    public List<List<Integer>> zigzagLevelOrderSwitch(TreeNode root) {
        if (null == root)
            return Collections.EMPTY_LIST;


        List<List<Integer>> zigZag = new ArrayList<>();
        Stack<TreeNode> currentStack = new Stack<>();
        Stack<TreeNode> nextStack = new Stack<>();

        currentStack.push(root);
        boolean leftToRight = true;
        List<Integer> zig = new ArrayList<>();
        while (!currentStack.isEmpty()) {

            TreeNode temp = currentStack.pop();
            zig.add(temp.val);

            if (leftToRight) {
                if (temp.left != null)
                    nextStack.push(temp.left);
                if (temp.right != null)
                    nextStack.push(temp.right);
            } else {

                if (temp.right != null)
                    nextStack.push(temp.right);
                if (temp.left != null)
                    nextStack.push(temp.left);
            }

            if (currentStack.isEmpty()) {

                currentStack = nextStack;
                zigZag.add(zig);
                nextStack = new Stack<>();
                zig = new ArrayList<>();
                leftToRight = !leftToRight;
            }


        }

        return zigZag;

    }
}

class UsingDequeue {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (null == root)
            return Collections.EMPTY_LIST;


        List<List<Integer>> zigZag = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);

        boolean leftToRight = true;
        while (!deque.isEmpty()) {

            List<Integer> zig = new ArrayList<>();
            int levelSize = deque.size();


            int i = 0;
            if (leftToRight) {
                while (i < levelSize) {
                    TreeNode temp = deque.pollLast();
                    zig.add(temp.val);

                    if (temp.left != null)
                        deque.offerFirst(temp.left);
                    if (temp.right != null)
                        deque.offerFirst(temp.right);
                    i++;

                }
            } else {
                while (i < levelSize) {
                    TreeNode temp = deque.pollFirst();
                    zig.add(temp.val);

                    if (temp.right != null)
                        deque.offerLast(temp.right);
                    if (temp.left != null)
                        deque.offerLast(temp.left);

                    i++;

                }
            }

            zigZag.add(zig);
            leftToRight = !leftToRight;
        }

        return zigZag;
    }
}