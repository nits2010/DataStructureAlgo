package Java.nonleetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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

        if (root.getData() == data || search(root.getLeft(), data) || search(root.getRight(), data))
            return true;

        return false;
    }

    @Override
    public Integer inOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (null == root)
            return null;

        if (null == node)
            return null;


        //Case 1: if right child is not empty
        if (null != node.getRight()) {
            return (Integer) leftMostNode(node.getRight()).getData();

        } else {
            TreeNode<Integer> successor = new BinaryTreeNode<>(null);
            //Case 2: when there is no right child, then search and backtrack
            inOrderSuccessor(root, node, successor);
            return successor.getData();

        }


    }


    private TreeNode<Integer> inOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer> successor) {
        if (null == root)
            return null;

        if (root == node)
            return root;

        TreeNode temp;
        if (((temp = inOrderSuccessor(root.getLeft(), node, successor)) != null
                || (temp = inOrderSuccessor(root.getRight(), node, successor)) != null)
                && successor.getData() == null) {
            if (successor.getData() == null)
                if (root.getLeft() == temp) {
                    ((BinaryTreeNode) successor).buildNode(root.getData(), root.getLeft(), root.getRight());

                    return null;
                }
            return root;
        }
        return null;


    }


    @Override
    public Integer inOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node) {

        if (null == root)
            return null;

        if (null == node)
            return null;


        //Case 1: if left is not null, then right most node is predecessor of node in left subtree of node
        if (null != node.getLeft()) {
            return (Integer) rightMostNode(node.getLeft()).getData();
        } else {
            TreeNode<Integer> predecessor = new BinaryTreeNode<>(null);
            //Case 2: when there is no left child, then search and backtrack
            inOrderPredecessor(root, node, predecessor);
            return predecessor.getData();
        }


    }

    private TreeNode<Integer> inOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer> predecessor) {
        if (null == root)
            return null;

        if (root == node)
            return root;

        TreeNode temp;
        if ((((temp = inOrderPredecessor(root.getLeft(), node, predecessor)) != null
                || (temp = inOrderPredecessor(root.getRight(), node, predecessor)) != null))
                && predecessor.getData() == null) {

            if (predecessor.getData() == null) {
                if (root.getRight() == temp) {
                    ((BinaryTreeNode) predecessor).buildNode(root.getData(), root.getLeft(), root.getRight());
                    return null;
                }
            }
            return root;
        }
        return null;
    }


    @Override
    public Integer preOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (null == root || null == node)
            return null;

        //Case1: if left node is not null, then this is a successor
        if (null != node.getLeft()) {
            return (Integer) node.getLeft().getData();
        } else {
            //Case2: if left is null but right node is not null, then this is a successor
            if (null != node.getRight()) {
                return (Integer) node.getRight().getData();
            }
        }

        //Case3: if left and right both are null then, successor is sibling of parent of this node; if sibling null, then move up parent of parent and so on
        TreeNode<Integer> successor = new BinaryTreeNode<>(null);
        preOrderSuccessorUtil(root, node, successor);
        return successor.getData();
    }


    private TreeNode<Integer> preOrderSuccessorUtil(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer> successor) {

        if (null == root)
            return null;

        //Search the node
        if (root == node)
            return root;

        TreeNode temp = null;

        if (((temp = preOrderSuccessorUtil(root.getLeft(), node, successor)) != null
                || (temp = preOrderSuccessorUtil(root.getRight(), node, successor)) != null)
                && (successor.getData() == null)) {

            if (successor.getData() == null) {

                if (root.getLeft() == temp) {

                    //check does it have right sibling
                    if (root.getRight() != null) {

                        //then this is a successor
                        ((BinaryTreeNode) successor).buildNode(root.getRight().getData(), root.getRight().getLeft(), root.getRight().getRight());
                        return null;

                    }
                }
            }
            //if node is on right side of parent, then we need to go up more
            return root;
        }
        return null;

    }

    @Override
    public Integer preOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (null == root || null == node)
            return null;

        //Case 1: if this node is root itself then there is no predecessor of root
        if (root == node)
            return null;


        TreeNode<Integer> predecessor = new BinaryTreeNode<>(null);
        preOrderPredecessor(root, node, predecessor);
        return predecessor.getData();


    }


    private TreeNode<Integer> preOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer> predecessor) {

        if (null == root)
            return null;


        if (root == node)
            return root;

        TreeNode temp;
        if ((((temp = preOrderPredecessor(root.getLeft(), node, predecessor)) != null || (temp = preOrderPredecessor(root.getRight(), node, predecessor)) != null))
                && (predecessor.getData() == null)) {

            //Case 2: if this node is left node of root then root is predecessor
            if (temp == root.getLeft()) {
                if (predecessor.getData() == null) {
                    predecessor.setData(root.getData());
                    predecessor.setRight(root.getRight());
                    predecessor.setLeft(root.getLeft());
                    return null;
                }


            }//Case 3: if this node is MAY be right node of root or any subtree of root, then search recursively
            else //if this node is right child of root node then predecessor must be in left subtree of root, the right aligned node; Check with example what right aligned node mean
                if (root.getRight() == temp) {

                    //If there is no left subtree of root, then root is predecessor
                    if (root.getLeft() == null) {
                        if (predecessor.getData() == null) {
                            predecessor.setData(root.getData());
                            predecessor.setRight(root.getRight());
                            predecessor.setLeft(root.getLeft());
                            return null;
                        }

                    } else {
                        // must be in left subtree of root, the right aligned node; Check with example what right aligned node mean
                        TreeNode rightAlignedNode = rightAlignedNode(root.getLeft());
                        if (predecessor.getData() == null) {
                            predecessor.setData((Integer) rightAlignedNode.getData());
                            predecessor.setRight(rightAlignedNode.getRight());
                            predecessor.setLeft(rightAlignedNode.getLeft());
                            return null;
                        }
                    }
                }

            return root;


        }


        return null;
    }


    @Override
    public Integer postOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node) {

        if (null == root || null == node)
            return null;

        /**
         * There are two cases;
         *
         * if node is parent left
         *  => successor is left aligned node in parent right; try some example to understand left aligned node
         *
         * if node is parent right
         *  => then parent is successor
         */
        TreeNode<Integer> successor = new BinaryTreeNode<>(null);
        postOrderSuccessor(root, node, successor);
        return successor.getData();


    }

    private TreeNode<Integer> postOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer> successor) {

        if (null == root)
            return null;

        if (root.getData() == node.getData())
            return root;

        TreeNode temp;

        if (((temp = postOrderSuccessor(root.getLeft(), node, successor)) != null || (temp = postOrderSuccessor(root.getRight(), node, successor)) != null)
                && (successor.getData() == null)) {

            if (successor.getData() == null) {

                //if node is parent right; then parent is successor
                if (root.getRight() == temp) {
                    ((BinaryTreeNode) successor).buildNode(root.getData(), root.getLeft(), root.getRight());

                } else if (root.getLeft() == temp) {
                    //if node is parent left
                    // 1. if right node does not exist of root , then root is successor
                    // 2. otherwise successor is left aligned node in parent right; try some example to understand left aligned node


                    if (null == root.getRight()) {
                        ((BinaryTreeNode) successor).buildNode(root.getData(), root.getLeft(), root.getRight());
                    } else {

                        TreeNode leftAlignedNode = leftAlignedNode(root.getRight());
                        ((BinaryTreeNode) successor).buildNode(leftAlignedNode.getData(), leftAlignedNode.getLeft(), leftAlignedNode.getRight());

                    }

                }
                return null;
            }
            return root;
        }

        return null;

    }


    @Override
    public Integer postOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node) {

        if (null == root || null == node)
            return null;

        /**
         * There are two cases;
         *
         * 1. if right node of node is not null, then right node is predecessor
         * 2. if right node of node is null, then left node is predecessor
         * 3. if right node of node is null and left node is also null
         * *    3.1 if its left node of its parent
         * *        go up till it become right node of its parent , then parent left node is predecessor
         * *    3.2 if its right node of its parent ???
         * Don't know
         * *
         * if node is parent left
         *  => successor is left aligned node in parent right; try some example to understand left aligned node
         *
         * if node is parent right
         *  => then parent is successor
         */
        TreeNode<Integer> predecessor = new BinaryTreeNode<>(null);
        postOrderPredecessor(root, node, predecessor);
        return predecessor.getData();

    }

    private TreeNode<Integer> postOrderPredecessor(TreeNode<Integer> root, TreeNode<Integer> node, TreeNode<Integer> predecessor) {
        return null;
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
