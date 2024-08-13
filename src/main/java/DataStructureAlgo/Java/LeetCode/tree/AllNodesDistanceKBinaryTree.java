package DataStructureAlgo.Java.LeetCode.tree;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.helpers.templates.TreeNode;
import DataStructureAlgo.Java.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-24
 * Description: https://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/#_=_
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * <p>
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * <p>
 * Output: [7,4,1]
 * <p>
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 * <p>
 * <p>
 * <p>
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 * <p>
 * <p>
 * Note:
 * <p>
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 * <p>
 * [Adobe]
 */


public class AllNodesDistanceKBinaryTree {

    public static void main(String[] args) {
        test(getTree1(), 2);
        test(getTree2(), 2);
        test(getTree3(), 4);
        test(getTree3(), 1);
        test(getTree3(), 5);
        test(getTree3(), 3);
    }

    private static void test(Pair<TreeNode, TreeNode> node, int k) {
        KDistanceAwayFromNodeSolution solution1 = new KDistanceAwayFromNodeSolution();
        KDistanceAwayFromNodeSolutionCleaner solution1Cleaner = new KDistanceAwayFromNodeSolutionCleaner();

        System.out.println(CommonMethods.preOrder(node.getKey()));
        System.out.println("node :" + node.getValue().val + " k: " + k);
        System.out.println(solution1.distanceK(node.getKey(), node.getValue(), k));
        System.out.println(solution1Cleaner.distanceK(node.getKey(), node.getValue(), k));
    }

    private static Pair<TreeNode, TreeNode> getTree1() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode temp = root.left.right;

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(9);
        return new Pair<>(root, temp);
    }

    private static Pair<TreeNode, TreeNode> getTree3() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);


        root.right = new TreeNode(8);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(9);

        TreeNode temp = root.right.right;
        return new Pair<>(root, temp);
    }


    private static Pair<TreeNode, TreeNode> getTree2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        TreeNode temp = root.left.right;

        root.right = new TreeNode(3);
        return new Pair<>(root, temp);
    }


}

/**
 * O(n)
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for All Nodes Distance K in Binary Tree.
 * Memory Usage: 37.4 MB, less than 73.68% of Java online submissions for All Nodes Distance K in Binary Tree.
 */
class KDistanceAwayFromNodeSolution {

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
        } else {
            distanceKInSubTrees(root.left, k - 1, output);
            distanceKInSubTrees(root.right, k - 1, output);

        }


    }

    private int distanceK(TreeNode root, TreeNode target, int k, List<Integer> output) {
        if (null == root)
            return -1;

        //Print the subtree of target node
        if (root == target) {
            distanceKInSubTrees(target, k, output);
            return 0; // as this node is 0 distance away from target node
        }

        //Search in left subtree
        int distanceFromLeft = distanceK(root.left, target, k, output);

        //means we had found the target node in left subtree
        if (distanceFromLeft != -1) {


            //check does this node is k distance away from target;
            if (distanceFromLeft == k - 1) //(since target to this node is 1 distance away(1 edge), as root is parent of target)
                output.add(root.val);

                //otherwise, we need to find all the node which is rooted this root node
                // we need to go right side of this tree rooted at this root, as we already cover left in above calls
                // The right node would be 2 edges away from the current node
            else
                distanceKInSubTrees(root.right, k - distanceFromLeft - 2, output);


            //return the distance of this node to target node;
            return distanceFromLeft + 1;

        }


        //Search in right subtree
        int distanceFromRight = distanceK(root.right, target, k, output);

        //means we had found the target node in left subtree already
        if (distanceFromRight != -1) {


            //check does this node is k distance away from target;
            if (distanceFromRight == k - 1) //(since target to this node is 1 distance away(1 edge), as root is parent of target)
                output.add(root.val);

                //now, we need to find all the node which is rooted this root node
                // we need to go left side of this tree rooted at this root, as we already cover right in above calls
                // The left node would be 2 edges away from the current node
            else
                distanceKInSubTrees(root.left, k - distanceFromRight - 2, output);


            //return the distance of this node to target node;
            return distanceFromRight + 1;

        }

        // if both left and right says they did not found, then we did not found it
        return -1;


    }
}


/**
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for All Nodes Distance K in Binary Tree.
 * Memory Usage: 37.1 MB, less than 94.74% of Java online submissions for All Nodes Distance K in Binary Tree.
 */
class KDistanceAwayFromNodeSolutionCleaner {
    /*
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
     */

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

        //means we had found the target node in left subtree already
        if (((distance = distanceK(root.left, target, k, output)) != null
                || ((distance = distanceK(root.right, target, k, output)) != null))) {


            //check does this node is k distance away from target;
            if (distance.getKey() == k - 1) {
                output.add(root.val);
                return null;
            }


            //now, we need to find all the node which is rooted this root node of k-1 distance away
            // (since target to this node is 1 distance away, as root is parent of target)

            // we need to go left/right side of this tree rooted at this root,
            //The left/right node would be 2 edges away from the current node
            if (root.left == distance.getValue()) {
                distanceKInSubTrees(root.right, k - distance.getKey() - 2, output);
            } else {
                distanceKInSubTrees(root.left, k - distance.getKey() - 2, output);
            }

            //return the 'distance' of this 'node' to target node;
            return new Pair<>(distance.getKey() + 1, root);
        }


        // if both left and right says they did not found, then we did not found it
        return null;

    }
}
