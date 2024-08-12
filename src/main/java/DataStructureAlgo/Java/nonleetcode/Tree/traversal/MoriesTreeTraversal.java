package DataStructureAlgo.Java.nonleetcode.Tree.traversal;

import  DataStructureAlgo.Java.nonleetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:
 */
public class MoriesTreeTraversal {


    /****************** InOrder Mories algorithm for Java.Tree Traversal- without stack *******************/
    public static List<Integer> inOrderMories(TreeNode<Integer> root) {
        List<Integer> inOrder = new LinkedList<>();

        if (null == root)
            return inOrder;


        TreeNode<Integer> temp;

        while (root != null) {

            //see if no left then process it and move to right child
            if (root.getLeft() == null) {
                inOrder.add(root.getData());
                root = root.getRight();

            } else {

                //If there is left child
                temp = root.getLeft();

                //then transform this tree to right skew tree

                //find the right most node of this "temp"
                while (temp.getRight() != null && temp.getRight() != root) {
                    temp = temp.getRight();
                }

                //If this is rightmost node, then make current root as right subtree of temp; transforming to right skew tree
                if (temp.getRight() == null) {

                    temp.setRight(root);

                    //move to the new left subtree
                    root = root.getLeft();
                } else {

                    inOrder.add(root.getData());
                    //Restore the tree
                    temp.setRight(null);

                    root = root.getRight();

                }

            }
        }
        return inOrder;

    }

    /****************** PreOrder Mories algorithm for Java.Tree Traversal- without stack *******************/

    public static List<Integer> preOrderMories(TreeNode<Integer> root) {
        List<Integer> preOrder = new LinkedList<>();

        if (null == root)
            return preOrder;


        TreeNode<Integer> temp;

        while (root != null) {

            //see if no left then process it and move to right child
            if (root.getLeft() == null) {
                preOrder.add(root.getData());
                root = root.getRight();

            } else {

                //If there is left child
                temp = root.getLeft();

                //then transform this tree to right skew tree

                //find the right most node of this "temp"
                while (temp.getRight() != null && temp.getRight() != root) {
                    temp = temp.getRight();
                }

                //If this is rightmost node, then make current root as right subtree of temp; transforming to right skew tree
                if (temp.getRight() == null) {
                    preOrder.add(root.getData());
                    temp.setRight(root);

                    //move to the new left subtree
                    root = root.getLeft();
                } else {


                    //Restore the tree
                    temp.setRight(null);

                    root = root.getRight();

                }

            }
        }
        return preOrder;

    }

    public List<Integer> postOrderMorris(TreeNode<Integer> root, List<Integer> postOrder) {

        if (root == null) return postOrder;

        TreeNode<Integer> curr = root, pred;

        while (curr != null) {
            if (curr.getLeft() == null) {
                curr = curr.getRight();
            } else {
                pred = curr.getLeft();
                while (pred.getRight() != null && pred.getRight() != curr) {
                    pred = pred.getRight();
                }

                if (pred.getRight() == null) {
                    pred.setRight(curr);
                    curr = curr.getLeft();
                } else {
                    reverseAddNodes(curr.getLeft(), pred, postOrder);
                    pred.setRight(null);
                    curr = curr.getRight();
                }
            }
        }
        return postOrder;
    }

    private static void reverseAddNodes(TreeNode<Integer> from, TreeNode<Integer> to, List<Integer> result) {
        reverseNodes(from, to);

        TreeNode<Integer> node = to;
        while (true) {
            result.add(node.getData());
            if (node == from) break;
            node = node.getRight();
        }

        reverseNodes(to, from);
    }

    private static void reverseNodes(TreeNode<Integer> from, TreeNode<Integer> to) {
        if (from == to) return;

        TreeNode<Integer> x = from, y = from.getRight(), z;
        while (x != to) {
            z = y.getRight();
            y.setRight(x);
            x = y;
            y = z;
        }
    }
}
