package DataStructureAlgo.Java.nonleetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 26/12/18
 * Description:
 */
public class BinaryTree implements IBinaryTree {


    @Override
    public TreeNode<Integer> insert(Integer data, TreeNode<Integer> root) {


        //if tree is empty
        if (null == root) {
            root = new BinaryTreeNode<>(data);
            return root;
        }

        //if tree is not empty, then insert at right place a new node
        TreeNode<Integer> newNode = new BinaryTreeNode<>(data);

        Queue<TreeNode<Integer>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            if (null != node.getLeft())
                queue.add(node.getLeft());
            else {
                //Left node is not exist, then add a new node at left
                node.setLeft(newNode);
                return newNode;
            }

            if (null != node.getRight())
                queue.add(node.getRight());
            else {
                //right node is not exist, then add a new node at right
                node.setRight(newNode);
                return newNode;
            }


        }

        return root;
    }

    @Override
    public TreeNode<Integer> delete(Integer data, TreeNode<Integer> root) {
        return null;
    }


    @Override
    public boolean search(TreeNode<Integer> root, Integer data) {

        if (null == root)
            return false;

        return root.getData() == data || search(root.getLeft(), data) || search(root.getRight(), data);
    }

    @Override
    public int largestBSTSize(TreeNode<Integer> root) {

        return LargestBSTInBinaryTree.largestBSTSize(root);
    }


    @Override
    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> alpha, TreeNode<Integer> beta) {
        return LowestCommonAncestor.lowestCommonAncestorBT(root, alpha, beta);
    }


}
