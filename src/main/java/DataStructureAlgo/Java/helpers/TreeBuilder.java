package DataStructureAlgo.Java.helpers;

import DataStructureAlgo.Java.helpers.templates.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date:11/08/24
 * Question Category:
 * Description:
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */

public class TreeBuilder {
    private static int preIndex = 0, postIndex;

    public static TreeNode buildTreeFromLevelOrder(Integer[] elements) {
        if (elements == null || elements.length == 0 || elements[0] == null)
            return null;

        TreeNode root = new TreeNode(elements[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < elements.length) {
            TreeNode current = queue.poll();

            if (elements[i] != null) {
                current.left = new TreeNode(elements[i]);
                queue.add(current.left);
            }
            i++;

            if (i < elements.length && elements[i] != null) {
                current.right = new TreeNode(elements[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    private static TreeNode insertNodeAtFirstAvailablePlace(TreeNode root, TreeNode node) {
        if (root == null)
            return node;

        if (root.left == null)
            root.left = node;
        else if (root.right == null)
            root.right = node;
        else
            insertNodeAtFirstAvailablePlace(root.left, node);

        return root;
    }


    public static TreeNode buildTreeFromInorderAndPreorder(Integer[] inorder, Integer[] preorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length)
            return null;

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeInPreOrder(preorder, inorderMap, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeInPreOrder(Integer[] preorder, Map<Integer, Integer> inorderMap, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inorderMap.get(rootVal);

        root.left = buildTreeInPreOrder(preorder, inorderMap, inStart, inIndex - 1);
        root.right = buildTreeInPreOrder(preorder, inorderMap, inIndex + 1, inEnd);

        return root;
    }


    public static TreeNode buildTreeFromInorderAndPostorder(Integer[] inorder, Integer[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;

        postIndex = postorder.length - 1;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeInPostOrder(postorder, inorderMap, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeInPostOrder(Integer[] postorder, Map<Integer, Integer> inorderMap, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inorderMap.get(rootVal);

        root.right = buildTreeInPostOrder(postorder, inorderMap, inIndex + 1, inEnd);
        root.left = buildTreeInPostOrder(postorder, inorderMap, inStart, inIndex - 1);

        return root;
    }


    public static TreeNode buildTreeFromPreorderAndPostorder(Integer[] preorder, Integer[] postorder) {
        if (preorder == null || postorder == null || preorder.length != postorder.length)
            return null;

        postIndex = postorder.length - 1;
        Map<Integer, Integer> preorderMap = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            preorderMap.put(preorder[i], i);
        }

        return buildTreePrPostOrder(preorder, postorder, preorderMap, 0, preorder.length - 1);
    }

    private static TreeNode buildTreePrPostOrder(Integer[] preorder, Integer[] postorder, Map<Integer, Integer> preorderMap, int preStart, int preEnd) {
        if (preStart > preEnd)
            return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        if (preStart == preEnd)
            return root;

        int preIndex = preorderMap.get(rootVal);

        root.right = buildTreePrPostOrder(preorder, postorder, preorderMap, preIndex + 1, preEnd);
        root.left = buildTreePrPostOrder(preorder, postorder, preorderMap, preStart, preIndex - 1);

        return root;
    }


}
