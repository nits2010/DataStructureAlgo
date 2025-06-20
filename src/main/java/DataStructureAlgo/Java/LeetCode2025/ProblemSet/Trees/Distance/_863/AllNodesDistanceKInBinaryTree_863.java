package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees.Distance._863;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.TreeBuilder;
import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 8/22/2024
 * Question Category: 863. All Nodes Distance K in Binary Tree
 * Description: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * Example 2:
 * <p>
 * Input: root = [1], target = 1, k = 3
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.tree.AllNodesDistanceKBinaryTree}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @HashTable
 * @Tree
 * @Depth-FirstSearch
 * @Breadth-FirstSearch
 * @BinaryTree Company Tags
 * -----
 * @Editorial <a href="https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/solutions/318883/solution-in-java-easy-to-understand-logic-building">...</a>
 */
public class AllNodesDistanceKInBinaryTree_863 {


    public static void main(String[] args) {
        boolean test = true;
        test &= test(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 2, new Integer[]{7, 4, 1});
        test &= test(new Integer[]{1}, 1, 3, new Integer[]{});
        System.out.println("=================================================");
        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(Integer[] input, int target, int k, Integer[] expected) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Input: " + Arrays.toString(input) + " target: " + target + " k: " + k);

        TreeNode root = TreeBuilder.buildTreeFromLevelOrder(input);
        TreeNode targetNode = CommonMethods.searchNodeByValue(root, target);

        Solution1 solution1 = new Solution1();
        List<Integer> output = solution1.distanceK(root, targetNode, k);

        boolean testResult1 = CommonMethods.equalsValues(output, List.of(expected));
        System.out.println("Output: " + output + "\nExpected: " + Arrays.toString(expected) + " Test Pass " + (testResult1 ? "All passed" : "Failed"));

        Solution2 solution2 = new Solution2();
        output = solution2.distanceK(root, targetNode, k);

        boolean testResult2 = CommonMethods.equalsValues(output, List.of(expected));
        System.out.println("Output2: " + output + "\nExpected: " + Arrays.toString(expected) + " Test Pass " + (testResult1 ? "All passed" : "Failed"));
        return testResult1 && testResult2;

    }


    static class Solution1 {
        /**
         * # Approach
         * To find all nodes at a distance ( k ) from a given node, we need to consider nodes in both the left and right subtrees rooted at the target node. Additionally, nodes can also be in the parent’s left or right subtree, depending on which side the target node is located.
         * <p>
         * ## Identify the Target Node:
         * First, locate the target node. The distance from the target node to itself is always 0, so we can explore its subtree for nodes at distance ( k ).
         * <p>
         * ## Determine Distance from Parent:
         * To find nodes at distance ( $$k$$ ) with respect to the parent (which is now $$( k - 1 )$$), we need to know the distance from the parent to the target node. This information can be obtained during any traversal, such as in-order traversal.
         * <p>
         * # Algorithm:
         * 1. Find the Target Node: Locate the target node and then search for all nodes at distance ( $$k$$ ) within its subtree.
         * 2. Return Distance to Parent: Return 0 as the distance from the target node to its parent.
         * 3. Explore Parent’s Subtree: The first parent of the target node will always be $$( k - 1 )$$ distance away.
         * If the target node is in the left subtree, explore the right subtree with $$( k - 2 )$$ distance on either side (i.e., $$( k - 1 )$$
         * for target to root and 1 for root to its left/right child).
         *
         * @param root
         * @param target
         * @param k
         * @return
         */
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            List<Integer> output = new ArrayList<>();
            if (root == null) return output;

            distanceK(root, target, k, output);
            return output;
        }

        private int distanceK(TreeNode root, TreeNode target, int k, List<Integer> output) {
            if (root == null)
                return Integer.MAX_VALUE;  // means target node not found and root is INF distance from target


            //if target found, then we need to explore the subtree with k distance
            if (root == target) {
                distanceKInSubtree(root, target, k, output);
                return 0; // this will be 0 distance from root which is target.
            }

            int distanceFromTargetAtLeftSide = distanceK(root.left, target, k, output);

            // means target node found, and root is x distance from target
            if (distanceFromTargetAtLeftSide != Integer.MAX_VALUE) {

                //if the distance b/w root and child is the required one, then add it
                if (distanceFromTargetAtLeftSide == k - 1) {
                    output.add(root.val);
                    return distanceFromTargetAtLeftSide + 1; // 1 add for the distance wrt to root.
                }

                //otherwise we have to explore the k-2 (2 because of edge b/w root and target, and edge b/w root to its right child distance on otherside of tree
                distanceKInSubtree(root.right, target, k - distanceFromTargetAtLeftSide - 2, output);
                return distanceFromTargetAtLeftSide + 1; // 1 add for the distance wrt to root.

            }


            int distanceFromTargetAtRightSide = distanceK(root.right, target, k, output);

            // means target node found, and root is x distance from target
            if (distanceFromTargetAtRightSide != Integer.MAX_VALUE) {

                //if the distance b/w root and child is the required one, then add it
                if (distanceFromTargetAtRightSide == k - 1) {
                    output.add(root.val);
                    return distanceFromTargetAtRightSide + 1; // 1 add for the distance wrt to root.
                }

                //otherwise we have to explore the k-2 (2 because of edge b/w root and target, and edge b/w root to its left child distance on otherside of tree
                distanceKInSubtree(root.left, target, k - distanceFromTargetAtRightSide - 2, output);
                return distanceFromTargetAtRightSide + 1; // 1 add for the distance wrt to root.

            }

            return Integer.MAX_VALUE;
        }

        private void distanceKInSubtree(TreeNode root, TreeNode target, int k, List<Integer> output) {

            if (root == null)
                return;

            if (k == 0) {
                output.add(root.val);
                return;
            }

            distanceKInSubtree(root.left, target, k - 1, output);
            distanceKInSubtree(root.right, target, k - 1, output);
        }

    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    static class Solution2 {

        class Pair<K, V> {
            private K key;

            public K getKey() {
                return key;
            }

            private V value;

            public V getValue() {
                return value;
            }

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            List<Integer> output = new ArrayList<>();
            distanceK(root, target, K, output);
            return output;

        }

        private void distanceKInSubTrees(TreeNode root, int k, List<Integer> output) {

            if (root == null || k < 0)
                return;

            if (k == 0) {
                output.add(root.val);
                return;
            } else {
                distanceKInSubTrees(root.left, k - 1, output);
                distanceKInSubTrees(root.right, k - 1, output);

            }


        }

        private Pair<Integer, TreeNode> distanceK(TreeNode root, TreeNode target, int k, List<Integer> output) {
            if (null == root)
                return null;

            //Print the subtree of target node
            if (root == target) {
                distanceKInSubTrees(target, k, output);
                return new Pair<>(0, root);// as this node is 0 distance away from target node
            }

            Pair<Integer, TreeNode> distance;

            if (((distance = distanceK(root.left, target, k, output)) != null
                    || ((distance = distanceK(root.right, target, k, output)) != null))) {


                if (distance.getKey() == k - 1) {
                    output.add(root.val);
                    return null;
                }

                if (root.left == distance.getValue()) {
                    distanceKInSubTrees(root.right, k - distance.getKey() - 2, output);
                } else {
                    distanceKInSubTrees(root.left, k - distance.getKey() - 2, output);
                }

                return new Pair<>(distance.getKey() + 1, root);
            }


            return null;

        }
    }
}
