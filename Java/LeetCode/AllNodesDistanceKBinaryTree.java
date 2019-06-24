package Java.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-24
 * Description: https://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/#_=_
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */


public class AllNodesDistanceKBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> output = new ArrayList<>();
        distanceK(root, target, K, output);
        return output;

    }

    public void distanceKInSubTrees(TreeNode root, int k, List<Integer> output) {

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

    public int distanceK(TreeNode root, TreeNode target, int k, List<Integer> output) {
        if (null == root)
            return -1;

        //Print the subtree of target node
        if (root == target) {
            distanceKInSubTrees(target, k, output);
            return 0; // as this node is 0 distance away from target node
        }

        //Search in left subtree
        int distanceFromLeft = distanceK(root.left, target, k, output);

        //means we had found the target node in left subtree already
        if (distanceFromLeft != -1) {


            //check does this node is k distance away from target;
            if (distanceFromLeft == k - 1) //(since target to this node is 1 distance away(1 edge), as root is parent of target)
                output.add(root.val);

                //now, we need to find all the node which is rooted this root node of k-1 distance away (since target to this node is 1 distance away, as root is parent of                 target)

                // we need to go right side of this tree rooted at this root, as we already conver left in above calls
                // k-disL = 1 + 1 for right subtree node (as this is two edges away) => k-dist-2
            else
                distanceKInSubTrees(root.right, k - distanceFromLeft - 2, output);


            //return the distance of this node to target node;
            return distanceFromLeft + 1;

        }


        //Search in left subtree
        int distanceFromRight = distanceK(root.right, target, k, output);

        //measn we had found the target node in left subtree already
        if (distanceFromRight != -1) {


            //check does this node is k distance away from target;
            if (distanceFromRight == k - 1) //(since target to this node is 1 distance away(1 edge), as root is parent of target)
                output.add(root.val);

                //now, we need to find all the node which is rooted this root node of k-1 distance away (since target to this node is 1 distance away, as root is parent of                 target)

                // we need to go left side of this tree rooted at this root, as we already conver right in above calls
                // k-disL = 1 + 1 for left subtree node (as this is two edges away) => k-dist-2
            else
                distanceKInSubTrees(root.left, k - distanceFromRight - 2, output);


            //return the distance of this node to target node;
            return distanceFromRight + 1;

        }

        // if both left and right says they did not found, then we did not found it
        return -1;


    }
}
