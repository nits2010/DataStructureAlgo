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
 * @OptimalSoltuion {@link BFS.Solution_OnePassBFS}
 */
public class CousinsInBinaryTreeII_2641 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{5, 4, 9, 1, 10, null, 7}, new Integer[]{0, 0, 0, 7, 7, null, 11});
        test &= test(new Integer[]{3, 1, 2}, new Integer[]{0, 0, 0});
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(Integer[] input, Integer[] expected) {
        CommonMethods.printTest(new String[]{"Input", "Expected" }, true, input, expected);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        System.out.println("Level Order Traversal : " + TreeTraversalRecursive.levelOrderWithNull(root));

        TreeNode output;
        List<Integer> outputLevelOrder;
        boolean pass, finalPass = true;

        BFS.Solution_TwoPassBFS solutionTwoPassBFS = new BFS.Solution_TwoPassBFS();
        output = solutionTwoPassBFS.replaceValueInTree(TreeBuilder.buildTreeFromLevelOrder(input));
        outputLevelOrder = TreeTraversalRecursive.levelOrderWithNull(output);
        pass = Arrays.deepEquals(expected, outputLevelOrder.toArray());
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Two Pass BFS",  "Pass"}, false, outputLevelOrder, (pass ? "Yes" : "No"));

        DFS.Solution_TwoPassDFS solutionTwoPassDFS = new DFS.Solution_TwoPassDFS();
        output = solutionTwoPassDFS.replaceValueInTree(TreeBuilder.buildTreeFromLevelOrder(input));
        outputLevelOrder = TreeTraversalRecursive.levelOrderWithNull(output);
        pass = Arrays.deepEquals(expected, outputLevelOrder.toArray());
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Two Pass DFS",  "Pass"}, false, outputLevelOrder, (pass ? "Yes" : "No"));

        BFS.Solution_OnePassBFS solutionOnePassBFS = new BFS.Solution_OnePassBFS();
        output = solutionOnePassBFS.replaceValueInTree(TreeBuilder.buildTreeFromLevelOrder(input));
        outputLevelOrder = TreeTraversalRecursive.levelOrderWithNull(output);
        pass = Arrays.deepEquals(expected, outputLevelOrder.toArray());
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"One Pass BFS",  "Pass"}, false, outputLevelOrder, (pass ? "Yes" : "No"));

        DFS.Solution_OnePassDFS solutionOnePassDFS = new DFS.Solution_OnePassDFS();
        output = solutionOnePassDFS.replaceValueInTree(TreeBuilder.buildTreeFromLevelOrder(input));
        outputLevelOrder = TreeTraversalRecursive.levelOrderWithNull(output);
        pass = Arrays.deepEquals(expected, outputLevelOrder.toArray());
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"One Pass DFS",  "Pass"}, false, outputLevelOrder, (pass ? "Yes" : "No"));

        return finalPass;

    }

    static class BFS {
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
                        //acquire sibsum
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

        /**
         * In Two pass BFS, we first acquire the sum and then distribute the sum at each node in level order. More precisely, while distributing, we are distributing a sum at the next level, as we already
         * know the sum at the next level, previously computed. Now, what if we know the sum of next level and sum of current level?
         * While doing BFS, we can always calculate the current Level sum (root.val) and the next level sum(root.left.val + root.right.val) and post every level, the nextLevelSUm will be our currentLevelSum (swap).
         * <p>
         * Now the challenge is, how we can replace the current node with its sibling sum, while we have the partial current level sum (root.val)?
         * To avoid such a situation, we can cache the sibling sum at each node and use it to replace the current node while processing the next level.
         */
        static class Solution_OnePassBFS {
            public TreeNode replaceValueInTree(TreeNode root) {
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);

                //the current level which is under consideration
                int currentLevelSum = root.val;

                //acquire sum
                while (!q.isEmpty()) {
                    int size = q.size();

                    //the next level sum of the current node
                    int nextLevelSum = 0;

                    for (int i = 0; i < size; i++) {
                        TreeNode node = q.poll();

                        //reduce node value by current levelSum, since node.val value already has the total sibling sum
                        node.val = currentLevelSum - node.val;

                        int sibSum = 0;
                        if (node.left != null) {
                            nextLevelSum += node.left.val;
                            sibSum += node.left.val;
                        }

                        if (node.right != null) {
                            nextLevelSum += node.right.val;
                            sibSum += node.right.val;
                        }

                        if (node.left != null) {
                            node.left.val = sibSum; //cache the sibSum at each node so that we can replace it
                            q.offer(node.left);
                        }

                        if (node.right != null) {
                            node.right.val = sibSum;
                            q.offer(node.right);
                        }


                    }

                    currentLevelSum = nextLevelSum;


                }


                root.val = 0;

                return root;


            }


        }
    }

    static class DFS {


        static class Solution_TwoPassDFS {
            public TreeNode replaceValueInTree(TreeNode root) {
                List<Integer> levelSum = new ArrayList<>();
                dfsAcquireSum(root, levelSum, 0);
                dfsDistributeSum(root, levelSum, 1);
                root.val = 0;
                return root;
            }

            private void dfsDistributeSum(TreeNode root, List<Integer> levelSum, int level) {
                if (root == null) return;

                int sibSum = 0;

                if (root.left != null) {
                    sibSum += root.left.val;
                }

                if (root.right != null) {
                    sibSum += root.right.val;
                }

                if (root.left != null) {
                    root.left.val = levelSum.get(level) - sibSum;
                }

                if (root.right != null) {
                    root.right.val = levelSum.get(level) - sibSum;
                }
                dfsDistributeSum(root.left, levelSum, level + 1);
                dfsDistributeSum(root.right, levelSum, level + 1);

            }

            private void dfsAcquireSum(TreeNode root, List<Integer> levelSum, int level) {
                if (root == null) return;

                if (levelSum.size() == level)
                    levelSum.add(0);

                levelSum.set(level, levelSum.get(level) + root.val);
                dfsAcquireSum(root.left, levelSum, level + 1);
                dfsAcquireSum(root.right, levelSum, level + 1);
            }
        }

        static class Solution_OnePassDFS {
            public TreeNode replaceValueInTree(TreeNode root) {

                //this list hols node of current level under processing
                List<TreeNode> levelNodes = new ArrayList<>();
                levelNodes.add(root);
                dfsAcquireDistributeSum(levelNodes, 0);
                root.val = 0;
                return root;
            }

            private void dfsAcquireDistributeSum(List<TreeNode> levelNodes, int level) {

                //there are no more nodes  to process
                if (levelNodes.isEmpty())
                    return;

                //process the current level and calculate the levelSum
                int currentLevelSum = 0;

                //cache the next level nodes to process later
                List<TreeNode> nextLevelNodes = new ArrayList<>();

                for (TreeNode node : levelNodes) {
                    if (node.left != null) {
                        currentLevelSum += node.left.val;
                        nextLevelNodes.add(node.left);
                    }
                    if (node.right != null) {
                        currentLevelSum += node.right.val;
                        nextLevelNodes.add(node.right);
                    }
                }


                //update the current level nodes with sibSum
                for (TreeNode node : levelNodes) {
                    int sibSum = 0;

                    if (node.left != null) {
                        sibSum += node.left.val;
                    }

                    if (node.right != null) {
                        sibSum += node.right.val;
                    }

                    if (node.left != null) {
                        node.left.val = currentLevelSum - sibSum;

                    }

                    if (node.right != null) {
                        node.right.val = currentLevelSum - sibSum;

                    }
                }

                dfsAcquireDistributeSum(nextLevelNodes, level + 1);
            }


        }
    }
}
