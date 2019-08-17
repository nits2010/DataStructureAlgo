package Java.LeetCode;

import Java.LeetCode.HelperDatastructure.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-27
 * Description:
 */
public class LongestUnivaluePath {


    static int ans = 0;

    public static int longestUnivaluePath(TreeNode root) {
        ans = 0;
        longestUniValuePathHelper(root);
        return ans;
    }

    public static int longestUniValuePathHelper(TreeNode root) {
        if (null == root)
            return 0;

        if (root.left == null && root.right == null)
            return 0;

        int left = longestUniValuePathHelper(root.left);
        int right = longestUniValuePathHelper(root.right);

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
        ans = Math.max(ans, tempLeft + tempRight);

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

