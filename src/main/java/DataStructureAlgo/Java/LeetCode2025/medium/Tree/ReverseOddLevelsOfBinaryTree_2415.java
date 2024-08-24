package DataStructureAlgo.Java.LeetCode2025.medium.Tree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 8/22/2024
 * Question Category: 2415. Reverse Odd Levels of Binary Tree
 * Description: https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/description/?envType=problem-list-v2&envId=m4ly4d57
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
 * <p>
 * For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
 * Return the root of the reversed tree.
 * <p>
 * A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
 * <p>
 * The level of a node is the number of edges along the path between it and the root node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [2,3,5,8,13,21,34]
 * Output: [2,5,3,8,13,21,34]
 * Explanation:
 * The tree has only one odd level.
 * The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [7,13,11]
 * Output: [7,11,13]
 * Explanation:
 * The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
 * Example 3:
 * <p>
 * Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * Explanation:
 * The odd levels have non-zero values.
 * The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
 * The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 214].
 * 0 <= Node.val <= 105
 * root is a perfect binary tree.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium <p>
 * <p>
 * Company Tags
 * -----
 * @Editorial <a href="https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/solutions/5676222/easy-algoright-develop-logic-one-by-one-efficient-solution-level-order-and-pre-order-traversal">...</a>
 */
public class ReverseOddLevelsOfBinaryTree_2415 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{2, 3, 5, 8, 13, 21, 34}, new Integer[]{2, 5, 3, 8, 13, 21, 34});
        test &= test(new Integer[]{7, 13, 11}, new Integer[]{7, 11, 13});
        test &= test(new Integer[]{0, 1, 2, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, new Integer[]{0, 2, 1, 0, 0, 0, 0, 2, 2, 2, 2, 1, 1, 1, 1});

        System.out.println("==========================");
        System.out.println("\nTest result = " + (test ? "Pass" : "Fail"));
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        System.out.println("------------------------------");
        System.out.println("Input : "+ Arrays.toString(input) + "\nexpected : "+ Arrays.toString(expected));


        SolutionUsingLevelOrderTraversal solutionUsingLevelOrderTraversal = new SolutionUsingLevelOrderTraversal();
        SolutionUsingPreOrderTraversal solutionUsingPreOrderTraversal = new SolutionUsingPreOrderTraversal();

        TreeNode outputUsingLevelOrderTraversal = solutionUsingLevelOrderTraversal.reverseOddLevels(TreeBuilder.buildTreeFromLevelOrder(input));

        List<Integer> output = TreeTraversalRecursive.levelOrder(outputUsingLevelOrderTraversal);

        boolean testResultLevelOrder = CommonMethods.equalsValues(output, Arrays.asList(expected));
        System.out.println("outputUsingLevelOrderTraversal : "+ output + " testResultLevelOrder "+(testResultLevelOrder ? "Pass" : "Fail"));

        TreeNode outputUsingPreOrderTraversal = solutionUsingPreOrderTraversal.reverseOddLevels(TreeBuilder.buildTreeFromLevelOrder(input));
        output = TreeTraversalRecursive.levelOrder(outputUsingPreOrderTraversal);

        boolean testResultPreOrder = CommonMethods.equalsValues(output, Arrays.asList(expected));
        System.out.println("outputUsingPreOrderTraversal : "+ output + " testResultPreOrder "+(testResultPreOrder ? "Pass" : "Fail"));


        return testResultLevelOrder && testResultPreOrder;
    }




    /**
     * T/S: O(n)/O(n)
     * every node will get touch at max 2 times.
     *
     * Traverse the tree in level order traversal. However, cache the each level nodes in a list, and swap the values of the nodes in odd levels from the list.
     */
    static class SolutionUsingLevelOrderTraversal {


        public TreeNode reverseOddLevels(TreeNode root) {
            if (root == null)
                return null;

            Queue<TreeNode> queue = new LinkedList<>();
            int level = 0;
            queue.offer(root);

            while (!queue.isEmpty()) {

                int levelSize = queue.size();

                List<TreeNode> temp = new ArrayList<>();

                while (levelSize > 0) {
                    TreeNode current = queue.poll();
                    temp.add(current);

                    if (current.left != null)
                        queue.offer(current.left);

                    if (current.right != null)
                        queue.offer(current.right);

                    levelSize--;
                }

                swapValues(temp, level);
                level++;

            }

            return root;
        }

        /**
         *
         * @param temp
         * @param level
         */
        private void swapValues(List<TreeNode> temp, int level) {
            if (level % 2 != 0) {
                int start = 0, end = temp.size() - 1;
                while (start < end) {
                    int val = temp.get(start).val;
                    temp.get(start).val = temp.get(end).val;
                    temp.get(end).val = val;
                    start++;
                    end--;
                }
            }
        }
    }




    /**
     In the level order solution above, once we cache the nodes in a list, we have access to both end nodes for swapping.
     The main challenge is to identify both end nodes of each level. If there are multiple nodes, the left (start) node should move to the right (end) side, and the right (end) node should move to the left (start) side for the swap.
     This means we need two pointers to point to these nodes at any given time to perform the swap.
     Algorithm:
     1. Traverse the tree in a pre-order fashion in two directions.
     2. One traversal will move towards the left, while the other will move towards the right.
     3. Whenever the level is odd, use these two pointers to swap the nodes.
     Since we are traversing the tree in two directions, one to the left and the other to the right,
     both pointers will point to the ends of the level. To proceed with other elements in between,
     move the left pointer to the right side and the right pointer to the left side one by one.
     */
    static class SolutionUsingPreOrderTraversal {

        public TreeNode reverseOddLevels(TreeNode root) {
            if(root == null || (root.left == null && root.right == null))
                return root;

            reverseOddLevels(root.left, root.right, 1);
            return root;
        }

        private void reverseOddLevels(TreeNode leftPointer, TreeNode rightPointer, int level) {
            if(leftPointer == null || rightPointer == null)
                return ;

            if(level % 2 != 0){
                int temp = leftPointer.val;
                leftPointer.val = rightPointer.val;
                rightPointer.val = temp;
            }

            //move a left and right pointer to their left and right direction
            reverseOddLevels(leftPointer.left, rightPointer.right, level + 1);

            //move a left pointer towards right while pointer towards a left
            reverseOddLevels(leftPointer.right, rightPointer.left, level + 1);
        }
    }
}
