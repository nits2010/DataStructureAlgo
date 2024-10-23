package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._2641;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.nonleetcode.Tree.traversal.TreeTraversalRecursive;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/23/2024
 * Question Category: 2641. Cousins in Binary Tree II
 * Description: https://leetcode.com/problems/cousins-in-binary-tree-ii/description/?envType=daily-question&envId=2024-10-23
 * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 * <p>
 * Return the root of the modified tree.
 * <p>
 * Note that the depth of a node is the number of edges in the path from the root node to it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,9,1,10,null,7]
 * Output: [0,0,0,7,7,null,11]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
 * - Node with value 5 does not have any cousins so its sum is 0.
 * - Node with value 4 does not have any cousins so its sum is 0.
 * - Node with value 9 does not have any cousins so its sum is 0.
 * - Node with value 1 has a cousin with value 7 so its sum is 7.
 * - Node with value 10 has a cousin with value 7 so its sum is 7.
 * - Node with value 7 has cousins with values 1 and 10 so its sum is 11.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,1,2]
 * Output: [0,0,0]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
 * - Node with value 3 does not have any cousins so its sum is 0.
 * - Node with value 1 does not have any cousins so its sum is 0.
 * - Node with value 2 does not have any cousins so its sum is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class CousinsInBinaryTreeII_2641 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{5, 4, 9, 1, 10, null, 7}, new Integer[]{0, 0, 0, 7, 7, null, 11});
        test &= test(new Integer[]{3, 1, 2}, new Integer[]{0, 0, 0});
        CommonMethods.printResult(test);
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        System.out.println("-------------------------------------------------");

        System.out.println("Input: " + Arrays.toString(input) + " Expected: " + Arrays.toString(expected));

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        System.out.println("Level Order Traversal : " + TreeTraversalRecursive.levelOrderWithNull(root));

        TreeNode output;
        List<Integer> outputLevelOrder;
        boolean pass, finalPass = true;

        Solution_TwoPassBFS solutionTwoPassBFS = new Solution_TwoPassBFS();
        output = solutionTwoPassBFS.replaceValueInTree(TreeBuilder.buildTreeFromLevelOrder(input));
        outputLevelOrder = TreeTraversalRecursive.levelOrderWithNull(output);
        pass = Arrays.deepEquals(expected, outputLevelOrder.toArray());
        finalPass &= pass;
        System.out.println("Two Pass BFS : " + outputLevelOrder + " Pass : " + (pass ? "PASS" : "FAIL"));


        System.out.println("-------------------------------------------------");

        return finalPass;

    }


    static class Solution_TwoPassBFS {
        public TreeNode replaceValueInTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            List<Integer> levelSum = new ArrayList<>();

            //acquire sum
            while (!q.isEmpty()) {
                int size = q.size();

                int sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();

                    if (node.left != null)
                        q.offer(node.left);
                    if (node.right != null)
                        q.offer(node.right);

                    sum += node.val;
                }

                levelSum.add(sum);
            }

            //distribute sum
            q.offer(root);
            int level = 1;
            while (!q.isEmpty()) {
                int size = q.size();

                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();

                    int sibSum = 0;
                    //accuire sibsum
                    if (node.left != null) {
                        sibSum += node.left.val;
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        sibSum += node.right.val;
                        q.offer(node.right);
                    }

                    //replace sibSum
                    if (node.left != null) {
                        node.left.val = levelSum.get(level) - sibSum;
                    }

                    if (node.right != null) {
                        node.right.val = levelSum.get(level) - sibSum;

                    }
                }


                level++;
            }

            root.val = 0;

            return root;


        }


    }
}
