package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._662;

import DataStructureAlgo.Java.Pair;
import DataStructureAlgo.Java.helpers.*;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/23/2025
 * Question Title: 662. Maximum Width of Binary Tree
 * Link: https://leetcode.com/problems/maximum-width-of-binary-tree/description
 * Description: Given the root of a binary tree, return the maximum width of the given tree.
 * <p>
 * The maximum width of a tree is the maximum width among all levels.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 * <p>
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.MaximumWidthBinaryTree}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Tree
 * @DepthFirstSearch
 * @BreadthFirstSearch
 * @BinaryTree <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google
 * @Microsoft
 * @Facebook
 * @Bloomberg <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumWidthOfBinaryTree_662 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new Integer[]{1, 3, 2, 5, 3, null, 9}, 4));
        tests.add(test(new Integer[]{1, 3, 2, 5, null, null, 9, 6, null, 7}, 7));
        tests.add(test(new Integer[]{1, 3, 2, 5}, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(Integer[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(nums);
        output = new Solution_BFS().widthOfBinaryTree(root);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BFS", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_DFS().widthOfBinaryTree(root);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"DFS", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_BFS {
        public int widthOfBinaryTree(TreeNode root) {

            if (null == root)
                return 0;

            int maxWidth = 1;


            final Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
            deque.offerLast(new Pair<>(root, 1)); // root, position

            while (!deque.isEmpty()) {

                int count = deque.size();
                maxWidth = Math.max(maxWidth, deque.peekLast().getValue() - deque.peekFirst().getValue() + 1);
                //evaluate current level, build next level
                while (count-- > 0) {
                    Pair<TreeNode, Integer> currentPair = deque.pollFirst();
                    TreeNode current = currentPair.getKey();
                    int position = currentPair.getValue();

                    if (current.left != null) {
                        deque.offerLast(new Pair<>(current.left, position * 2));
                    }
                    if (current.right != null) {
                        deque.offerLast(new Pair<>(current.right, position * 2 + 1));
                    }


                }
            }

            return maxWidth;


        }
    }


    /**
     * The width of binary tree solely depends on left most node and right most node.
     * <p>
     * case 1: when there is only one node in tree : Width = 1
     * case 2: When tree has either left or right child : width = 1
     * case 3: when tree has both child then width  = 2
     * <p>
     * Now to find the width of tree roted at node 'root' we need to know the left most node and right most node of this root.
     * We know that in binary tree (like in heap), if root is at 'i'th then left is on 2*i and right is on 2*i+1
     * We'll use both as index.
     * <p>
     * So for a tree has left and right node (assuming i=1) would be at left =2 and right =3 hence width = right - left + 1 = 2.
     * <p>
     * <p>
     * To find the max width, we need to keep track both left most node and right most node.
     * Or we can track only one and use current 'i' to compute overall width.
     * <p>
     * One more important aspect that the width of tree rooted at i having only a left and a right should be 1.
     * Means, at any depth, we just need to keep track on left most index.  { two node at same height }
     * <p>
     * Runtime: 2 ms, faster than 38.25% of Java online submissions for Maximum Width of Binary Tree.
     * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Maximum Width of Binary Tree.
     */
    static class Solution_DFS {
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

            width[0] = Math.max(width[0], index - map.get(depth) + 1); //the index will be like right, and at this depth what is the leftmost will be get from map

            widthOfBinaryTree(root.left, width, depth + 1, 2 * index, map);
            widthOfBinaryTree(root.right, width, depth + 1, 2 * index + 1, map);


        }
    }
}
