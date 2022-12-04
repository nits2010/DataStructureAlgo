package DataStructureAlgo.Java.LeetCode.tree;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.templates.TreeNode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-10
 * Description: https://leetcode.com/problems/maximum-width-of-binary-tree/
 * 662. Maximum Width of Binary Tree
 * <p>
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * <p>
 * *            1
 * *          /   \
 * *         3     2
 * *        / \     \
 * *       5   3     9
 * <p>
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 * <p>
 * Input:
 * <p>
 * *           1
 * *          /
 * *         3
 * *        / \
 * *       5   3
 * <p>
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 * <p>
 * Input:
 * <p>
 * *           1
 * *          / \
 * *         3   2
 * *        /
 * *       5
 * <p>
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 * <p>
 * Input:
 * <p>
 * *           1
 * *          / \
 * *         3   2
 * *        /     \
 * *       5       9
 * *      /         \
 * *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 * <p>
 * <p>
 * Note: Answer will in the range of 32-bit signed integer.
 */
public class MaximumWidthBinaryTree {
    public static void main(String[] args) {
        /**
         * *            1
         * *          /   \
         * *         3     2
         * *        / \     \
         * *       5   3     9
         *
         * output: 4
         */
        test(TreeBuilder.arrayToTree(new Integer[]{1, 3, 2, 5, 3, null, 9}), 4);

        /**
         * *           1
         * *          /
         * *         3
         * *        / \
         * *       5   3
         *  output: 2
         */
        test(TreeBuilder.arrayToTree(new Integer[]{1, 3, null, 5, 3, null, null}), 2);


        /**
         * *           1
         * *          / \
         * *         3   2
         * *        /
         * *       5
         */
        test(TreeBuilder.arrayToTree(new Integer[]{1, 3, 2, 5, null, null, null}), 2);


        /**
         **             2
         **            / \
         * *          1  4
         * *         /  /
         * *        3  5
         */
        test(TreeBuilder.arrayToTree(new Integer[]{2, 1, 4, 3, null, 5}), 3);
        /**
         * *           1
         * *          / \
         * *         3   2
         * *        /     \
         * *       5       9
         * *      /
         * *     6
         */
        test(TreeBuilder.arrayToTree(new Integer[]{1, 3, 2, 5, null, null, 9, 6, null, null, null, null, null, null, 7}), 4);

    }

    private static void test(TreeNode root, int expected) {
        System.out.println("\nInput : " + GenericPrinter.preOrder(root) + " expected :" + expected);

        final MaximumWidthBinaryTreeUsingMap usingMap = new MaximumWidthBinaryTreeUsingMap();
        final MaximumWidthBinaryTreeUsingList usingList = new MaximumWidthBinaryTreeUsingList();
        final MaximumWidthBinaryTreeBFS bfs = new MaximumWidthBinaryTreeBFS();
        System.out.println("usingMap :" + usingMap.widthOfBinaryTree(root));
        System.out.println("usingList :" + usingList.widthOfBinaryTree(root));
        System.out.println("bfs :" + bfs.widthOfBinaryTree(root));
    }
}


/**
 * The width of binary tree solely depends on left most node and right most node.
 * <p>
 * case 1: when there is only one node in tree : Width = 1
 * case 2: When tree has either left or right child : width = 1
 * case 3: when tree has both child then width  = 2
 * <p>
 * Now in order to find the width of tree roted at node 'root' we need to know the left most node and right most node of this root.
 * We know that in binary tree (like in heap), if root is at 'i'th then left is on 2*i and right is on 2*i+1
 * We'll use both as index.
 * <p>
 * So for a tree has left and right node (assuming i=1) would be at left =2 and right =3 hence width = right - left + 1 = 2.
 * <p>
 * <p>
 * In order to find the max width, we need to keep track both left most node and right most node.
 * Or we can track only one and use current 'i' to compute overall width.
 * <p>
 * One more important aspect that the width of tree rooted at i having only a left and a right should be 1.
 * Means, at any depth, we just need to keep track on left most index.  { two node at same height }
 * <p>
 * Runtime: 2 ms, faster than 38.25% of Java online submissions for Maximum Width of Binary Tree.
 * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Maximum Width of Binary Tree.
 */
class MaximumWidthBinaryTreeUsingMap {
    public int widthOfBinaryTree(TreeNode root) {

        if (null == root)
            return 0;

        final int width[] = {0};

        widthOfBinaryTree(root, width, 0, 1, new HashMap<>());
        return width[0];


    }

    private void widthOfBinaryTree(TreeNode root, int[] width, int depth, int index, Map<Integer, Integer> map) {

        if (null == root)
            return;


        //at current depth; only keep left most index. Since we'll use left-root-right then first time we see this depth, then the index is
        //the leftmost
        if (!map.containsKey(depth))
            map.put(depth, index);

        width[0] = Math.max(width[0], index - map.get(depth) + 1); //index will be like right, and at this depth what is the leftmost will be get from map

        widthOfBinaryTree(root.left, width, depth + 1, 2 * index, map);
        widthOfBinaryTree(root.right, width, depth + 1, 2 * index + 1, map);


    }
}

/**
 * Same as above, But instead of map, using list
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Width of Binary Tree.
 * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Maximum Width of Binary Tree.
 */
class MaximumWidthBinaryTreeUsingList {
    public int widthOfBinaryTree(TreeNode root) {

        if (null == root)
            return 0;

        final int width[] = {0};

        widthOfBinaryTree(root, width, 0, 1, new ArrayList<>());
        return width[0];


    }

    private void widthOfBinaryTree(TreeNode root, int[] width, int depth, int index, ArrayList<Integer> map) {

        if (null == root)
            return;


        //at current depth; only keep left most index. Since we'll use left-root-right then first time we see this depth, then the index is
        //the leftmost
        if (depth >= map.size())
            map.add(index);

        width[0] = Math.max(width[0], index - map.get(depth) + 1); //index will be like right, and at this depth what is the leftmost will be get from map

        widthOfBinaryTree(root.left, width, depth + 1, 2 * index, map);
        widthOfBinaryTree(root.right, width, depth + 1, 2 * index + 1, map);


    }
}

/**
 * The obvious things comes in mind on seeing above question is Level order traversal.
 * <p>
 * We can achieve above using modified level order traversal. In modification, we'll push null nodes also in our queue.
 * Note that whenever you hit null node (the node don't exist) as you poped node, you need to push two null node in queue to cover distance between leftmost and right most node
 * * *           1
 * * *          / \
 * * *         3   2
 * * *        /     \
 * * *       5       9
 * * *      /         \
 * * *     6           7
 * <p>
 * on the last level, there are 6 nulls in between 6 and 7.
 * <p>
 * Hence when you iterating on current level, we need to build next level by pushing appropriate null nodes. Important fact here is, if we keep doing, then queue
 * will never empty (each null node push 2 more null node). Hence we need to pop those unnecessary null node from each level. For example
 * * *           1
 * * *          / \
 * * *         3   2
 * * *        /     \
 * * *       5       9
 * * *        \       \
 * * *        6       7
 * <p>
 * then at last level the queue would look like [null, 6, null, null, null, null, null, 7 ], since the first null node can't be use to compute width, we need to remove it.
 * The same could happen on other side too.
 * Hence use deque.
 * <p>
 * Algorithm:
 * 1. Push the first node in queue
 * 2. continue till its empty;
 * 2.1 Pop from front and see what is the length of queue, this will denote the current width of tree.
 * 2.2 build next level and get rid of null nodes
 * <p>
 * Runtime: 36 ms, faster than 9.61% of Java online submissions for Maximum Width of Binary Tree.
 * Memory Usage: 49.8 MB, less than 11.11% of Java online submissions for Maximum Width of Binary Tree.
 */
class MaximumWidthBinaryTreeBFS {
    public int widthOfBinaryTree(TreeNode root) {

        if (null == root)
            return 0;

        int maxWidth = 1;


        final Deque<TreeNode> deque = new LinkedList<>();
        deque.offerLast(root);

        while (!deque.isEmpty()) {

            int count = deque.size();
            //evaluate current level, build next level
            while (count > 0) {
                TreeNode current = deque.pollFirst();

                if (current == null) {
                    deque.offerLast(null);
                    deque.offerLast(null);
                } else {
                    deque.offerLast(current.left);
                    deque.offerLast(current.right);
                }
                count--;
            }

            //Fix left
            while (!deque.isEmpty() && deque.peekFirst() == null)
                deque.pollFirst();

            //Fix right
            while (!deque.isEmpty() && deque.peekLast() == null)
                deque.pollLast();

            int size = deque.size();
            maxWidth = Math.max(maxWidth, size);
        }

        return maxWidth;


    }
}