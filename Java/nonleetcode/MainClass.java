package Java.nonleetcode;


import Java.nonleetcode.Tree.BinaryTreeNode;
import Java.nonleetcode.Tree.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * * Date: 11/04/19
 * * Description:
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution2 {


    int ans = 0;

    public int longestUnivaluePath(TreeNode<Integer> root) {
        ans = 0;
        longestUnivaluePathHelper(root);
        return ans;


    }

    public int longestUnivaluePathHelper(TreeNode<Integer> root) {
        System.out.println("->" + (ans));
        if (null == root)
            return 0;

        if (root.getLeft() == null && root.getRight() == null)
            return 0;

        int left = longestUnivaluePathHelper(root.getLeft());
        int right = longestUnivaluePathHelper(root.getRight());

        int tempLeft = left, tempRight = right;

        //if parent gets included with left
        if (root.getLeft() != null && root.getData() == root.getLeft().getData()) {

            tempLeft += 1;
        }

        //if parent gets included with right
        if (root.getRight() != null && root.getData() == root.getRight().getData()) {

            tempRight += 1;
        }

        System.out.println("left: " + left + "right : " + right + " tL :" + tempLeft + " tr " + tempRight + " ans :" + ans);
        //path that goes through parent; kind a subtree
        ans = Math.max(ans, tempLeft + tempRight);
        System.out.println("->>>" + (ans));
        //path that does not goes through parent; kind a subtree
        return Math.max(tempLeft, tempRight);


    }
}

public class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(item));
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
                node.setLeft(new BinaryTreeNode(leftNumber));
                nodeQueue.add(node.getLeft());
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.setRight(new BinaryTreeNode(rightNumber));
                nodeQueue.add(node.getRight());
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = new Solution2().longestUnivaluePath(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}