package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.patternTraversal._103;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/18/2024
 * Question Category: 103. Binary Tree Zigzag Level Order Traversal
 * Description: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.ZigZagTraversalBinaryTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @Breadth-FirstSearch
 * @BinaryTree Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Bloomberg
 * @Google <p></p>
 * @Editorial <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/solutions/5656249/multiple-solutions-using-2-data-structure-2-using-1-ds-3">...</a>
 */
public class BinaryTreeZigzagLevelOrderTraversal_103 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 9, 20, null, null, 15, 7}, List.of(List.of(3), List.of(20, 9), List.of(15, 7)));
        test &= test(new Integer[]{1}, List.of(List.of(1)));
        test &= test(new Integer[]{}, List.of());
        test &= test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, null, null, null, null, 18, 19},
                List.of(List.of(1), List.of(3, 2), List.of(4, 5, 6, 7), List.of(15, 14, 13, 12, 11, 10, 9, 8), List.of(16, 18, 19)));
        System.out.println(test ? " All Passed " : " Something Failed ");
    }

    private static boolean test(Integer[] input, List<List<Integer>> expected) {
        System.out.println("=========================================");
        System.out.println("Input :" + Arrays.toString(input) + "\nExpected :" + expected);

        final TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);

        final Solution.SolutionUsingTwoDS.SolutionUsingTwoStacks solutionUsingTwoStacks = new Solution.SolutionUsingTwoDS.SolutionUsingTwoStacks();
        final Solution.SolutionUsingTwoDS.SolutionUsingStackAndQueue solutionUsingStackAndQueue = new Solution.SolutionUsingTwoDS.SolutionUsingStackAndQueue();
        final Solution.SolutionUsingOneDS.SolutionUsingDeque solutionUsingDeque = new Solution.SolutionUsingOneDS.SolutionUsingDeque();
        final Solution.SolutionUsingOneDS.SolutionUsingHeight solutionUsingHeight = new Solution.SolutionUsingOneDS.SolutionUsingHeight();
        final Solution.SolutionUsingOneDS.SolutionUsingHeight2 solutionUsingHeight2 = new Solution.SolutionUsingOneDS.SolutionUsingHeight2();
        final Solution.SolutionUsingOneDS.SolutionUsingHeightSimplified solutionUsingHeightSimplified = new Solution.SolutionUsingOneDS.SolutionUsingHeightSimplified();

        final List<List<Integer>> outputUsingTwoStacks = solutionUsingTwoStacks.zigzagLevelOrder(root);
        boolean testResultUsingTwoStack = CommonMethods.equalsValues(expected, outputUsingTwoStacks);
        System.out.println("output Using TwoS tacks :" + outputUsingTwoStacks + " testResult :" + (testResultUsingTwoStack ? " Passed " : "Failed"));

        final List<List<Integer>> outputUsingStackAndQueue = solutionUsingStackAndQueue.zigzagLevelOrder(root);
        boolean testResultUsingStackQueue = CommonMethods.equalsValues(expected, outputUsingStackAndQueue);
        System.out.println("output Using Stack & Queue :" + outputUsingStackAndQueue + " testResult :" + (testResultUsingStackQueue ? " Passed " : "Failed"));

        final List<List<Integer>> outputUsingDeque = solutionUsingDeque.zigzagLevelOrder(root);
        boolean testResultUsingDeque = CommonMethods.equalsValues(expected, outputUsingDeque);
        System.out.println("output Using Deque  :" + outputUsingDeque + " testResult :" + (testResultUsingDeque ? " Passed " : "Failed"));


        final List<List<Integer>> outputUsingHeight = solutionUsingHeight.zigzagLevelOrder(root);
        boolean testResultUsingHeight = CommonMethods.equalsValues(expected, outputUsingHeight);
        System.out.println("output Using Height  :" + outputUsingHeight + " testResult :" + (testResultUsingHeight ? " Passed " : "Failed"));


        final List<List<Integer>> outputUsingHeight2 = solutionUsingHeight2.zigzagLevelOrder(root);
        boolean testResultUsingHeight2 = CommonMethods.equalsValues(expected, outputUsingHeight2);
        System.out.println("output Using Height 2 :" + outputUsingHeight2 + " testResult :" + (testResultUsingHeight2 ? " Passed " : "Failed"));


        final List<List<Integer>> outputUsingHeightSimplified = solutionUsingHeightSimplified.zigzagLevelOrder(root);
        boolean testResultUsingHeightSimplified = CommonMethods.equalsValues(expected, outputUsingHeightSimplified);
        System.out.println("output Using Height Simplified :" + outputUsingHeightSimplified + " testResult :" + (testResultUsingHeightSimplified ? " Passed " : "Failed"));

        boolean finalTestResult = testResultUsingTwoStack
                && testResultUsingStackQueue
                && testResultUsingDeque
                && testResultUsingHeight
                && testResultUsingHeight2
                && testResultUsingHeightSimplified;
        System.out.println("\n Final Test Result " + (finalTestResult ? " Passed " : " Failed "));
        return finalTestResult;
    }


    static class Solution {

        static class SolutionUsingTwoDS {
            static class SolutionUsingTwoStacks {

                public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();

                    if (root == null)
                        return zigZag;

                    Stack<TreeNode> currentLevel = new Stack<>();
                    Stack<TreeNode> nextLevel = new Stack<>();

                    currentLevel.push(root);
                    int level = 0;
                    int zigZagDirection = 1; //+ left to right, -1 right to left

                    while (!currentLevel.isEmpty()) {

                        TreeNode current = currentLevel.pop();

                        if (zigZag.size() <= level)
                            zigZag.add(new LinkedList<>());

                        zigZag.get(level).add(current.val);

                        if (zigZagDirection > 0) {
                            addLeft(current, nextLevel);
                            addRight(current, nextLevel);
                        } else {
                            addRight(current, nextLevel);
                            addLeft(current, nextLevel);

                        }

                        if (currentLevel.isEmpty()) {
                            level++;
                            zigZagDirection = ~zigZagDirection;

                            Stack<TreeNode> temp = currentLevel;
                            currentLevel = nextLevel;
                            nextLevel = temp;
                        }
                    }

                    return zigZag;


                }

                private void addRight(TreeNode current, Stack<TreeNode> stack) {
                    if (current.right != null)
                        stack.push(current.right);
                }

                private void addLeft(TreeNode current, Stack<TreeNode> stack) {
                    if (current.left != null)
                        stack.push(current.left);
                }
            }

            static class SolutionUsingStackAndQueue {
                public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();

                    if (root == null)
                        return zigZag;

                    final Stack<TreeNode> stack = new Stack<>();
                    final Queue<TreeNode> queue = new LinkedList<>();
                    boolean leftToRight = true;
                    queue.offer(root);

                    while (!queue.isEmpty()) {

                        int size = queue.size();
                        List<Integer> zig = new LinkedList<>();

                        while (size > 0) {

                            TreeNode current = queue.poll();

                            zig.add(current.val);

                            if (leftToRight) {

                                if (current.left != null)
                                    stack.push(current.left);

                                if (current.right != null)
                                    stack.push(current.right);


                            } else {
                                if (current.right != null)
                                    stack.push(current.right);

                                if (current.left != null)
                                    stack.push(current.left);

                            }


                            size--;
                        }

                        zigZag.add(zig);
                        while (!stack.isEmpty()) {
                            queue.offer(stack.pop());
                        }

                        leftToRight = !leftToRight;


                    }

                    return zigZag;
                }


            }
        }

        static class SolutionUsingOneDS {
            /**
             * {@link SolutionUsingTwoDS.SolutionUsingStackAndQueue}
             */
            static class SolutionUsingDeque {
                public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();

                    if (root == null)
                        return zigZag;

                    // if offer first and poll first -> Stack
                    // if offer last and poll first -> queue
                    // iff offer last and poll last -> Stack
                    //
                    final Deque<TreeNode> deque = new LinkedList<>();
                    boolean leftToRight = true;

                    deque.offer(root);

                    while (!deque.isEmpty()) {

                        int size = deque.size();
                        List<Integer> zig = new LinkedList<>();

                        if (leftToRight) {

                            while (size > 0) {

                                TreeNode current = deque.pollFirst();

                                zig.add(current.val);

                                if (current.left != null)
                                    deque.offerLast(current.left);
                                if (current.right != null)
                                    deque.offerLast(current.right);


                                size--;
                            }
                        } else {
                            while (size > 0) {

                                TreeNode current = deque.pollLast();

                                zig.add(current.val);

                                if (current.right != null)
                                    deque.offerFirst(current.right);
                                if (current.left != null)
                                    deque.offerFirst(current.left);

                                size--;
                            }
                        }

                        zigZag.add(zig);
                        leftToRight = !leftToRight;
                    }


                    return zigZag;

                }
            }

            static class SolutionUsingHeight {
                public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();

                    if (root == null)
                        return zigZag;

                    int height = height(root);
                    boolean leftToRight = true;

                    for (int h = 1; h <= height; h++) {

                        List<Integer> zig = new LinkedList<>();
                        traversalAtHHeight(root, zig, leftToRight, h);
                        zigZag.add(zig);
                        leftToRight = !leftToRight;

                    }
                    return zigZag;

                }

                private void traversalAtHHeight(TreeNode root, List<Integer> zig, boolean leftToRight, int h) {
                    if (root == null)
                        return;

                    if (h == 1)
                        zig.add(root.val);
                    else {
                        if (leftToRight) {
                            traversalAtHHeight(root.left, zig, leftToRight, h - 1);
                            traversalAtHHeight(root.right, zig, leftToRight, h - 1);
                        } else {
                            traversalAtHHeight(root.right, zig, leftToRight, h - 1);
                            traversalAtHHeight(root.left, zig, leftToRight, h - 1);
                        }
                    }
                }

                private int height(TreeNode root) {

                    return (root == null ? 0 : Math.max(height(root.left), height(root.right)) + 1);

                }
            }

            static class SolutionUsingHeight2 {
                public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();

                    if (root == null)
                        return zigZag;


                    zigZag = traversal(root);
                    int height = zigZag.size();
                    boolean leftToRight = true;

                    for (int i = 0; i < height; i++) {
                        List<Integer> zig = zigZag.get(i);
                        if (!leftToRight)
                            Collections.reverse(zig);

                        leftToRight = !leftToRight;

                    }

                    return zigZag;


                }

                private List<List<Integer>> traversal(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();
                    Queue<TreeNode> queue = new LinkedList<>();
                    queue.offer(root);

                    while (!queue.isEmpty()) {

                        int size = queue.size();
                        List<Integer> zig = new LinkedList<>();
                        while (size > 0) {
                            TreeNode current = queue.poll();
                            zig.add(current.val);

                            if (current.left != null)
                                queue.offer(current.left);
                            if (current.right != null)
                                queue.offer(current.right);

                            size--;

                        }
                        zigZag.add(zig);
                    }

                    return zigZag;
                }


            }

            static class SolutionUsingHeightSimplified {
                public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                    List<List<Integer>> zigZag = new LinkedList<>();

                    if (root == null)
                        return zigZag;

                    zigzagLevelOrder(root, zigZag, true, 0);
                    return zigZag;
                }

                private void zigzagLevelOrder(TreeNode root, List<List<Integer>> zigZag, boolean leftToRight, int height) {
                    if (root == null)
                        return;

                    //if new level, then add a list for the element
                    if (zigZag.size() == height)
                        zigZag.add(new LinkedList<>());

                    List<Integer> zig = zigZag.get(height);

                    if (leftToRight)
                        zig.add(root.val);
                    else
                        zig.add(0, root.val);

                    zigzagLevelOrder(root.left, zigZag, !leftToRight, height + 1);
                    zigzagLevelOrder(root.right, zigZag, !leftToRight, height + 1);

                }
            }

        }
    }
}
