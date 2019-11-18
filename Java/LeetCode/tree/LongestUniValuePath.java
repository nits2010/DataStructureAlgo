package Java.LeetCode.tree;

import Java.LeetCode.templates.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-27
 * Description: https://leetcode.com/problems/longest-univalue-path/
 * <p>
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * <p>
 * The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * *
 * *               5
 * *              / \
 * *             4   5
 * *            / \   \
 * *           1   1   5
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input:
 * *
 * *               1
 * *              / \
 * *             4   5
 * *            / \   \
 * *           4   4   5
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUniValuePath {


    public static int longestUnivaluePath(TreeNode root) {
        int[] ans = {0};
        longestUniValuePathHelper(root, ans);
        return ans[0];
    }

    public static int longestUniValuePathHelper(TreeNode root, int[] ans) {
        if (null == root)
            return 0;

        if (root.left == null && root.right == null)
            return 0;

        int left = longestUniValuePathHelper(root.left, ans);
        int right = longestUniValuePathHelper(root.right, ans);

        int tempLeft = 0, tempRight = 0;

        //if parent gets included with left
        if (root.left != null && root.val == root.left.val) {

            tempLeft += left + 1;
        }

        //if parent gets included with right
        if (root.right != null && root.val == root.right.val) {

            tempRight += right + 1;
        }

        //path that goes through parent; kind a subtree
        ans[0] = Math.max(ans[0], tempLeft + tempRight);

        //path that does not goes through parent; kind a subtree
        return Math.max(tempLeft, tempRight);


    }


    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = longestUnivaluePath(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

}

